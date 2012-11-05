/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */

package com.opengamma.integration.copiernew;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.financial.conversion.JodaBeanConverters;
import com.opengamma.financial.security.equity.EquitySecurity;
import com.opengamma.financial.security.future.EquityFutureSecurity;
import com.opengamma.financial.security.future.InterestRateFutureSecurity;
import com.opengamma.financial.security.option.EquityBarrierOptionSecurity;
import com.opengamma.financial.security.option.EquityOptionSecurity;
import com.opengamma.financial.security.option.IRFutureOptionSecurity;
import com.opengamma.financial.security.option.SwaptionSecurity;
import com.opengamma.financial.security.swap.*;
import com.opengamma.master.position.ManageablePosition;
import com.opengamma.master.position.ManageableTrade;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.master.security.ManageableSecurityLink;
import com.opengamma.util.ArgumentChecker;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.beans.BeanBuilder;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.PropertyReadWrite;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;

/**
 * A generic row parser for Joda beans that automatically identifies fields to be persisted to rows/populated from rows
 */
public abstract class JodaBeanRowUtils {

  private static final Logger s_logger = LoggerFactory.getLogger(JodaBeanRowUtils.class);

  /**
   *  Map from column name to the field's Java type
   */
  protected SortedMap<String, Class<?>> _columns = new TreeMap<String, Class<?>>();


  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Utility routines
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public String[] getColumns() {
    return _columns.keySet().toArray(new String[_columns.size()]);
  }

  public int getHashCode() {
    HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
    for (Entry<String, Class<?>> entry : _columns.entrySet()) {      
      hashCodeBuilder.append(entry.getKey());
      hashCodeBuilder.append(entry.getValue().getCanonicalName());
    }
    return hashCodeBuilder.toHashCode();
  }

  /**
   * Extract a map of column (field) names and types from the properties of the specified direct bean class.
   * Appropriate member classes (such as swap legs) are recursively traversed and their columns also extracted 
   * and added to the map.
   * @param clazz   The bean type from which to extract properties
   * @param prefix  The class membership path traced from the top-level bean class to the current class
   * @return        A map of the column names and their types
   */
  @SuppressWarnings("unchecked")
  protected SortedMap<String, Class<?>> recursiveGetColumnMap(Class<?> clazz, String prefix) {
 
    // Scan through and capture the list of relevant properties and their types
    SortedMap<String, Class<?>> columns = new TreeMap<String, Class<?>>();
    
    for (MetaProperty<?> metaProperty : JodaBeanUtils.metaBean(clazz).metaPropertyIterable()) {
        
      // Skip any undesired properties, process the rest
      if (!ignoreMetaProperty(metaProperty)) {
        
        // Add a column for the property (used either for the actual value
        // or for the class name in the case of a non-convertible bean
        columns.put(prefix + metaProperty.name(), metaProperty.propertyType());

        // If this is a bean without a converter recursively extract all 
        // columns for the metabean and all its subclasses
        if (isBean(metaProperty.propertyType()) && !isConvertible(metaProperty.propertyType())) {
          
          // This is the bean (might be an abstract class/subclassed)
          Class<DirectBean> beanClass = (Class<DirectBean>) metaProperty.propertyType().asSubclass(DirectBean.class);
          
          // Recursively extract this bean's properties
          columns.putAll(recursiveGetColumnMap(beanClass, prefix + metaProperty.name() + ":"));

          // Identify ALL subclasses of this bean and extract all their properties
          for (Class<?> subClass : getSubClasses(beanClass)) {
            columns.putAll(recursiveGetColumnMap((Class<DirectBean>) subClass, prefix + metaProperty.name() + ":"));            
          }
        }
      }
    }
    return columns;
  }
  
  /**
   * Build a bean of the specified type by extracting property values from the supplied map of field names to 
   * values, using recursion to construct the member beans in the same manner. 
   * @param row     The map from property (or column, or field) names to values
   * @param clazz   The bean type of which to construct an instance
   * @param prefix  The class membership path traced from the top-level bean class to the current class
   * @return        The constructed security bean
   */
  protected DirectBean recursiveConstructBean(Map<String, String> row, Class<?> clazz, String prefix) {
    try {
      // Get a reference to the meta-bean
      Method metaMethod = clazz.getMethod("meta", (Class<?>[]) null);
      DirectMetaBean metaBean = (DirectMetaBean) metaMethod.invoke(null, (Object[]) null);

      // Get a new builder from the meta-bean
      @SuppressWarnings("unchecked")
      BeanBuilder<? extends DirectBean> builder = (BeanBuilder<? extends DirectBean>) metaBean.builder();

      // Populate the bean from the supplied row using the builder
      for (MetaProperty<?> metaProperty : JodaBeanUtils.metaBean(clazz).metaPropertyIterable()) {

        // Skip any undesired properties, process the rest
        if (!ignoreMetaProperty(metaProperty)) {

          // If this property is itself a bean without a converter, recurse to populate relevant fields
          if (isBean(metaProperty.propertyType()) && !isConvertible(metaProperty.propertyType())) {
  
            // Get the actual type of this bean from the relevant column
            String className = row.get((prefix + metaProperty.name()).trim().toLowerCase());
            Class<DirectBean> beanClass = getClass(className);
            
            // Recursively set properties
            builder.set(metaProperty.name(),
                recursiveConstructBean(row, beanClass, prefix + metaProperty.name() + ":"));
  
          // If not a bean, or it is a bean for which a converter exists, just set value in builder using joda convert
          } else {
            // Convert raw value in row to the target property's type
            String rawValue = row.get((prefix + metaProperty.name()).trim().toLowerCase());
            
            if (isConvertible(metaProperty.propertyType())) {
              // Set property value
              if (rawValue != null && !rawValue.equals("")) {
                builder.set(metaProperty.name(), 
                    JodaBeanUtils.stringConverter().convertFromString(metaProperty.propertyType(), rawValue));
              } else {
                s_logger.info("Skipping empty or null value for " + prefix + metaProperty.name());
              }
            } else if (List.class.isAssignableFrom(metaProperty.propertyType()) &&
                isConvertible(JodaBeanUtils.collectionType(metaProperty, metaProperty.propertyType()))) {
              builder.set(metaProperty.name(), stringToList(rawValue, JodaBeanUtils.collectionType(metaProperty, metaProperty.propertyType())));
            } else {
              throw new OpenGammaRuntimeException("Property '" + prefix + metaProperty.name() + "' (" + metaProperty.propertyType() + ") cannot be populated from a string");
            }
          }
        }
      }
      
      // Actually build the bean
      return builder.build();
  
    } catch (Throwable ex) {
      s_logger.error("Could not create a " + clazz.getSimpleName() + ": " + ex.getMessage());
      return null;
    }
  }
  
  /**
   * Extracts a map of column names to values from a supplied security bean's properties, using recursion to 
   * extract properties from any member beans. 
   * @param bean    The bean instance from which to extract property values
   * @param prefix  The class membership path traced from the top-level bean class to the current class
   * @return        A map of extracted column names and values
   */
  protected Map<String, String> recursiveConstructRow(DirectBean bean, String prefix) {
    Map<String, String> result = new HashMap<String, String>();
    
    // Populate the row from the bean's properties
    for (MetaProperty<?> metaProperty : bean.metaBean().metaPropertyIterable()) {
      
      // Skip any undesired properties, process the rest
      if (!ignoreMetaProperty(metaProperty)) {
        // If this property is itself a bean without a converter, recurse to populate relevant columns
        if (isBean(metaProperty.propertyType()) && !isConvertible(metaProperty.propertyType())) {
          
          // Store the class name in a separate column (to help identify the correct subclass during loading)
          result.put(prefix + metaProperty.name(), metaProperty.get(bean).getClass().getSimpleName());
          
          // Recursively extract bean's columns        
          result.putAll(recursiveConstructRow((DirectBean) metaProperty.get(bean), prefix + metaProperty.name() + ":"));
          
        // If not a bean, or it is a bean for which a converter exists, just extract its value using joda convert
        } else {
          // Set the column
          if (_columns.containsKey(prefix + metaProperty.name())) {
            // Can convert
            if (isConvertible(metaProperty.propertyType())) {
              result.put(prefix + metaProperty.name(), metaProperty.getString(bean));
            // Is list, needs to be decomposed
            } else if (List.class.isAssignableFrom(metaProperty.propertyType()) && 
                isConvertible(JodaBeanUtils.collectionType(metaProperty, metaProperty.propertyType()))) {
              result.put(prefix + metaProperty.name(), listToString((List<?>) metaProperty.get(bean)));
            // Cannot convert :(
            } else {
              throw new OpenGammaRuntimeException("Property '" + prefix + metaProperty.name() + "' (" + metaProperty.propertyType() + ") cannot be converted to a string");
            }
          } else {
            s_logger.info("No matching column found for property " + prefix + metaProperty.name());
          }
        }
      }
    }
    return result;
  }
  
  /**
   * Converts a list of objects to a |-separated string of their JodaConverted string representations
   * @param i the list to be converted
   * @return  the |-separated string string
   */
  protected String listToString(List<?> i) {
    String result = "";
    for (Object o : i) {
      if (isConvertible(o.getClass())) {
        result = result + JodaBeanUtils.stringConverter().convertToString(o) + " | "; 
      } else {
        throw new OpenGammaRuntimeException("Cannot convert " + o.getClass() + " contained in list");
      }
    }
    return result.substring(0, result.lastIndexOf('|')).trim();
  }
  
  /**
   * Converts a |-separated string to a list of objects using JodaConvert.
   * @param raw the string to parse
   * @param t   the class to convert to
   * @return    the list of objects of type t
   */
  protected List<?> stringToList(String raw, Class<?> t) {
    List<Object> result = new ArrayList<Object>();
    for (String s : raw.split("\\|")) {
      result.add(JodaBeanUtils.stringConverter().convertFromString(t, s.trim()));
    }
    return result;
  }
  
  /**
   * Given a class name, look for the class in the list of packages specified by CLASS_PACKAGES and return it
   * or throw exception if not found  
   * @param className   the class name to seek
   * @return            the corresponding class 
   */
  @SuppressWarnings("unchecked")
  protected Class<DirectBean> getClass(String className) {
    Class<DirectBean> theClass = null;
    for (String prefix : getClassPackages()) {
      try {
        String fullName = prefix + "." + className;
        theClass = (Class<DirectBean>) Class.forName(fullName);
        break;
      } catch (Throwable ex) { }
    }
    if (theClass == null) {
      throw new OpenGammaRuntimeException("Could not load class " + className);
    }
    return theClass;
  }

  /**
   * Given a bean class, find its subclasses; this is current hard coded as Java can neither identify the
   * classes within a package, nor identify a class's subclasses.
   * @param beanClass
   * @return
   */
  protected abstract Collection<Class<?>> getSubClasses(Class<?> beanClass);

  /**
   * Checks whether the supplied class has a registered Joda string converter
   * @param clazz   the class to check
   * @return        the answer
   */
  private boolean isConvertible(Class<?> clazz) {
    try {
      JodaBeanUtils.stringConverter().findConverter(clazz);
      return true;
    } catch (Throwable ex) {
      return false;
    }
  }
  
  /**
   * Determines whether the supplied class is a direct bean
   * @param clazz the class in question
   * @return      the answer
   */
  private boolean isBean(Class<?> clazz) {
    return DirectBean.class.isAssignableFrom(clazz) ? true : false; 
  }

  /**
   * Checks whether the specified metaproperty is to be ignored when extracting fields
   * @param mp  the metaproperty in question
   * @return    the answer
   */
  private boolean ignoreMetaProperty(MetaProperty<?> mp) {
    if (mp.readWrite() != PropertyReadWrite.READ_WRITE) {
      return true;
    }
    String s = mp.name().trim().toLowerCase(); 
    for (String t : getIgnoreMetaproperties()) {
      if (s.equals(t.trim().toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  protected abstract String[] getIgnoreMetaproperties();

  protected abstract String[] getClassPackages();

}
