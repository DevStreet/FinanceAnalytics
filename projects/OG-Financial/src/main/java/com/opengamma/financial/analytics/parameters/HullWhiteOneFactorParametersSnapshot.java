/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.parameters;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.core.marketdatasnapshot.NamedSnapshot;
import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.id.ExternalId;
import com.opengamma.id.UniqueId;
import com.opengamma.util.money.Currency;

/**
 * Snapshot that contains parameters for the Hull-White one factor model.
 */
@BeanDefinition
public class HullWhiteOneFactorParametersSnapshot implements NamedSnapshot, ImmutableBean {

  /**
   * The unique id of the snapshot. Null if no id has
   * been generated.
   */
  @PropertyDefinition(overrideGet = true)
  private final UniqueId _uniqueId;

  /**
   * The name of the snapshot.
   */
  @PropertyDefinition(validate = "notNull", overrideGet = true)
  private final String _name;

  /**
   * The currency the parameters are defined for.
   */
  @PropertyDefinition(validate = "notNull")
  private final Currency _currency;

  /**
   * The mean reversion speed.
   */
  @PropertyDefinition
  private final double _meanReversion;

  @PropertyDefinition(validate = "notNull")
  private final BusinessDayConvention _businessDayConvention;

  @PropertyDefinition
  private final boolean _endOfMonth;

  @PropertyDefinition(validate = "notNull")
  private final ExternalId _region;

  /**
   * Term structure of volatilities to be used.
   */
  @PropertyDefinition(validate = "notNull")
  private final HullWhiteOneFactorVolatilityTable _volatilityTable;

  @Override
  public NamedSnapshot withUniqueId(UniqueId uniqueId) {
    return toBuilder().uniqueId(uniqueId).build();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code HullWhiteOneFactorParametersSnapshot}.
   * @return the meta-bean, not null
   */
  public static HullWhiteOneFactorParametersSnapshot.Meta meta() {
    return HullWhiteOneFactorParametersSnapshot.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(HullWhiteOneFactorParametersSnapshot.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static HullWhiteOneFactorParametersSnapshot.Builder builder() {
    return new HullWhiteOneFactorParametersSnapshot.Builder();
  }

  /**
   * Restricted constructor.
   * @param builder  the builder to copy from, not null
   */
  protected HullWhiteOneFactorParametersSnapshot(HullWhiteOneFactorParametersSnapshot.Builder builder) {
    JodaBeanUtils.notNull(builder._name, "name");
    JodaBeanUtils.notNull(builder._currency, "currency");
    JodaBeanUtils.notNull(builder._businessDayConvention, "businessDayConvention");
    JodaBeanUtils.notNull(builder._region, "region");
    JodaBeanUtils.notNull(builder._volatilityTable, "volatilityTable");
    this._uniqueId = builder._uniqueId;
    this._name = builder._name;
    this._currency = builder._currency;
    this._meanReversion = builder._meanReversion;
    this._businessDayConvention = builder._businessDayConvention;
    this._endOfMonth = builder._endOfMonth;
    this._region = builder._region;
    this._volatilityTable = builder._volatilityTable;
  }

  @Override
  public HullWhiteOneFactorParametersSnapshot.Meta metaBean() {
    return HullWhiteOneFactorParametersSnapshot.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the unique id of the snapshot. Null if no id has
   * been generated.
   * @return the value of the property
   */
  @Override
  public UniqueId getUniqueId() {
    return _uniqueId;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the name of the snapshot.
   * @return the value of the property, not null
   */
  @Override
  public String getName() {
    return _name;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the currency the parameters are defined for.
   * @return the value of the property, not null
   */
  public Currency getCurrency() {
    return _currency;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the mean reversion speed.
   * @return the value of the property
   */
  public double getMeanReversion() {
    return _meanReversion;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the businessDayConvention.
   * @return the value of the property, not null
   */
  public BusinessDayConvention getBusinessDayConvention() {
    return _businessDayConvention;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the endOfMonth.
   * @return the value of the property
   */
  public boolean isEndOfMonth() {
    return _endOfMonth;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the region.
   * @return the value of the property, not null
   */
  public ExternalId getRegion() {
    return _region;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets term structure of volatilities to be used.
   * @return the value of the property, not null
   */
  public HullWhiteOneFactorVolatilityTable getVolatilityTable() {
    return _volatilityTable;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      HullWhiteOneFactorParametersSnapshot other = (HullWhiteOneFactorParametersSnapshot) obj;
      return JodaBeanUtils.equal(getUniqueId(), other.getUniqueId()) &&
          JodaBeanUtils.equal(getName(), other.getName()) &&
          JodaBeanUtils.equal(getCurrency(), other.getCurrency()) &&
          JodaBeanUtils.equal(getMeanReversion(), other.getMeanReversion()) &&
          JodaBeanUtils.equal(getBusinessDayConvention(), other.getBusinessDayConvention()) &&
          (isEndOfMonth() == other.isEndOfMonth()) &&
          JodaBeanUtils.equal(getRegion(), other.getRegion()) &&
          JodaBeanUtils.equal(getVolatilityTable(), other.getVolatilityTable());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getUniqueId());
    hash += hash * 31 + JodaBeanUtils.hashCode(getName());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCurrency());
    hash += hash * 31 + JodaBeanUtils.hashCode(getMeanReversion());
    hash += hash * 31 + JodaBeanUtils.hashCode(getBusinessDayConvention());
    hash += hash * 31 + JodaBeanUtils.hashCode(isEndOfMonth());
    hash += hash * 31 + JodaBeanUtils.hashCode(getRegion());
    hash += hash * 31 + JodaBeanUtils.hashCode(getVolatilityTable());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(288);
    buf.append("HullWhiteOneFactorParametersSnapshot{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("uniqueId").append('=').append(JodaBeanUtils.toString(getUniqueId())).append(',').append(' ');
    buf.append("name").append('=').append(JodaBeanUtils.toString(getName())).append(',').append(' ');
    buf.append("currency").append('=').append(JodaBeanUtils.toString(getCurrency())).append(',').append(' ');
    buf.append("meanReversion").append('=').append(JodaBeanUtils.toString(getMeanReversion())).append(',').append(' ');
    buf.append("businessDayConvention").append('=').append(JodaBeanUtils.toString(getBusinessDayConvention())).append(',').append(' ');
    buf.append("endOfMonth").append('=').append(JodaBeanUtils.toString(isEndOfMonth())).append(',').append(' ');
    buf.append("region").append('=').append(JodaBeanUtils.toString(getRegion())).append(',').append(' ');
    buf.append("volatilityTable").append('=').append(JodaBeanUtils.toString(getVolatilityTable())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code HullWhiteOneFactorParametersSnapshot}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code uniqueId} property.
     */
    private final MetaProperty<UniqueId> _uniqueId = DirectMetaProperty.ofImmutable(
        this, "uniqueId", HullWhiteOneFactorParametersSnapshot.class, UniqueId.class);
    /**
     * The meta-property for the {@code name} property.
     */
    private final MetaProperty<String> _name = DirectMetaProperty.ofImmutable(
        this, "name", HullWhiteOneFactorParametersSnapshot.class, String.class);
    /**
     * The meta-property for the {@code currency} property.
     */
    private final MetaProperty<Currency> _currency = DirectMetaProperty.ofImmutable(
        this, "currency", HullWhiteOneFactorParametersSnapshot.class, Currency.class);
    /**
     * The meta-property for the {@code meanReversion} property.
     */
    private final MetaProperty<Double> _meanReversion = DirectMetaProperty.ofImmutable(
        this, "meanReversion", HullWhiteOneFactorParametersSnapshot.class, Double.TYPE);
    /**
     * The meta-property for the {@code businessDayConvention} property.
     */
    private final MetaProperty<BusinessDayConvention> _businessDayConvention = DirectMetaProperty.ofImmutable(
        this, "businessDayConvention", HullWhiteOneFactorParametersSnapshot.class, BusinessDayConvention.class);
    /**
     * The meta-property for the {@code endOfMonth} property.
     */
    private final MetaProperty<Boolean> _endOfMonth = DirectMetaProperty.ofImmutable(
        this, "endOfMonth", HullWhiteOneFactorParametersSnapshot.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code region} property.
     */
    private final MetaProperty<ExternalId> _region = DirectMetaProperty.ofImmutable(
        this, "region", HullWhiteOneFactorParametersSnapshot.class, ExternalId.class);
    /**
     * The meta-property for the {@code volatilityTable} property.
     */
    private final MetaProperty<HullWhiteOneFactorVolatilityTable> _volatilityTable = DirectMetaProperty.ofImmutable(
        this, "volatilityTable", HullWhiteOneFactorParametersSnapshot.class, HullWhiteOneFactorVolatilityTable.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "uniqueId",
        "name",
        "currency",
        "meanReversion",
        "businessDayConvention",
        "endOfMonth",
        "region",
        "volatilityTable");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return _uniqueId;
        case 3373707:  // name
          return _name;
        case 575402001:  // currency
          return _currency;
        case -2016560896:  // meanReversion
          return _meanReversion;
        case -1002835891:  // businessDayConvention
          return _businessDayConvention;
        case -1344100978:  // endOfMonth
          return _endOfMonth;
        case -934795532:  // region
          return _region;
        case -2122778967:  // volatilityTable
          return _volatilityTable;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public HullWhiteOneFactorParametersSnapshot.Builder builder() {
      return new HullWhiteOneFactorParametersSnapshot.Builder();
    }

    @Override
    public Class<? extends HullWhiteOneFactorParametersSnapshot> beanType() {
      return HullWhiteOneFactorParametersSnapshot.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code uniqueId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<UniqueId> uniqueId() {
      return _uniqueId;
    }

    /**
     * The meta-property for the {@code name} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> name() {
      return _name;
    }

    /**
     * The meta-property for the {@code currency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Currency> currency() {
      return _currency;
    }

    /**
     * The meta-property for the {@code meanReversion} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> meanReversion() {
      return _meanReversion;
    }

    /**
     * The meta-property for the {@code businessDayConvention} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BusinessDayConvention> businessDayConvention() {
      return _businessDayConvention;
    }

    /**
     * The meta-property for the {@code endOfMonth} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> endOfMonth() {
      return _endOfMonth;
    }

    /**
     * The meta-property for the {@code region} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> region() {
      return _region;
    }

    /**
     * The meta-property for the {@code volatilityTable} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<HullWhiteOneFactorVolatilityTable> volatilityTable() {
      return _volatilityTable;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return ((HullWhiteOneFactorParametersSnapshot) bean).getUniqueId();
        case 3373707:  // name
          return ((HullWhiteOneFactorParametersSnapshot) bean).getName();
        case 575402001:  // currency
          return ((HullWhiteOneFactorParametersSnapshot) bean).getCurrency();
        case -2016560896:  // meanReversion
          return ((HullWhiteOneFactorParametersSnapshot) bean).getMeanReversion();
        case -1002835891:  // businessDayConvention
          return ((HullWhiteOneFactorParametersSnapshot) bean).getBusinessDayConvention();
        case -1344100978:  // endOfMonth
          return ((HullWhiteOneFactorParametersSnapshot) bean).isEndOfMonth();
        case -934795532:  // region
          return ((HullWhiteOneFactorParametersSnapshot) bean).getRegion();
        case -2122778967:  // volatilityTable
          return ((HullWhiteOneFactorParametersSnapshot) bean).getVolatilityTable();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code HullWhiteOneFactorParametersSnapshot}.
   */
  public static class Builder extends DirectFieldsBeanBuilder<HullWhiteOneFactorParametersSnapshot> {

    private UniqueId _uniqueId;
    private String _name;
    private Currency _currency;
    private double _meanReversion;
    private BusinessDayConvention _businessDayConvention;
    private boolean _endOfMonth;
    private ExternalId _region;
    private HullWhiteOneFactorVolatilityTable _volatilityTable;

    /**
     * Restricted constructor.
     */
    protected Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    protected Builder(HullWhiteOneFactorParametersSnapshot beanToCopy) {
      this._uniqueId = beanToCopy.getUniqueId();
      this._name = beanToCopy.getName();
      this._currency = beanToCopy.getCurrency();
      this._meanReversion = beanToCopy.getMeanReversion();
      this._businessDayConvention = beanToCopy.getBusinessDayConvention();
      this._endOfMonth = beanToCopy.isEndOfMonth();
      this._region = beanToCopy.getRegion();
      this._volatilityTable = beanToCopy.getVolatilityTable();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return _uniqueId;
        case 3373707:  // name
          return _name;
        case 575402001:  // currency
          return _currency;
        case -2016560896:  // meanReversion
          return _meanReversion;
        case -1002835891:  // businessDayConvention
          return _businessDayConvention;
        case -1344100978:  // endOfMonth
          return _endOfMonth;
        case -934795532:  // region
          return _region;
        case -2122778967:  // volatilityTable
          return _volatilityTable;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          this._uniqueId = (UniqueId) newValue;
          break;
        case 3373707:  // name
          this._name = (String) newValue;
          break;
        case 575402001:  // currency
          this._currency = (Currency) newValue;
          break;
        case -2016560896:  // meanReversion
          this._meanReversion = (Double) newValue;
          break;
        case -1002835891:  // businessDayConvention
          this._businessDayConvention = (BusinessDayConvention) newValue;
          break;
        case -1344100978:  // endOfMonth
          this._endOfMonth = (Boolean) newValue;
          break;
        case -934795532:  // region
          this._region = (ExternalId) newValue;
          break;
        case -2122778967:  // volatilityTable
          this._volatilityTable = (HullWhiteOneFactorVolatilityTable) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public HullWhiteOneFactorParametersSnapshot build() {
      return new HullWhiteOneFactorParametersSnapshot(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code uniqueId} property in the builder.
     * @param uniqueId  the new value
     * @return this, for chaining, not null
     */
    public Builder uniqueId(UniqueId uniqueId) {
      this._uniqueId = uniqueId;
      return this;
    }

    /**
     * Sets the {@code name} property in the builder.
     * @param name  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder name(String name) {
      JodaBeanUtils.notNull(name, "name");
      this._name = name;
      return this;
    }

    /**
     * Sets the {@code currency} property in the builder.
     * @param currency  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder currency(Currency currency) {
      JodaBeanUtils.notNull(currency, "currency");
      this._currency = currency;
      return this;
    }

    /**
     * Sets the {@code meanReversion} property in the builder.
     * @param meanReversion  the new value
     * @return this, for chaining, not null
     */
    public Builder meanReversion(double meanReversion) {
      this._meanReversion = meanReversion;
      return this;
    }

    /**
     * Sets the {@code businessDayConvention} property in the builder.
     * @param businessDayConvention  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder businessDayConvention(BusinessDayConvention businessDayConvention) {
      JodaBeanUtils.notNull(businessDayConvention, "businessDayConvention");
      this._businessDayConvention = businessDayConvention;
      return this;
    }

    /**
     * Sets the {@code endOfMonth} property in the builder.
     * @param endOfMonth  the new value
     * @return this, for chaining, not null
     */
    public Builder endOfMonth(boolean endOfMonth) {
      this._endOfMonth = endOfMonth;
      return this;
    }

    /**
     * Sets the {@code region} property in the builder.
     * @param region  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder region(ExternalId region) {
      JodaBeanUtils.notNull(region, "region");
      this._region = region;
      return this;
    }

    /**
     * Sets the {@code volatilityTable} property in the builder.
     * @param volatilityTable  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder volatilityTable(HullWhiteOneFactorVolatilityTable volatilityTable) {
      JodaBeanUtils.notNull(volatilityTable, "volatilityTable");
      this._volatilityTable = volatilityTable;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(288);
      buf.append("HullWhiteOneFactorParametersSnapshot.Builder{");
      int len = buf.length();
      toString(buf);
      if (buf.length() > len) {
        buf.setLength(buf.length() - 2);
      }
      buf.append('}');
      return buf.toString();
    }

    protected void toString(StringBuilder buf) {
      buf.append("uniqueId").append('=').append(JodaBeanUtils.toString(_uniqueId)).append(',').append(' ');
      buf.append("name").append('=').append(JodaBeanUtils.toString(_name)).append(',').append(' ');
      buf.append("currency").append('=').append(JodaBeanUtils.toString(_currency)).append(',').append(' ');
      buf.append("meanReversion").append('=').append(JodaBeanUtils.toString(_meanReversion)).append(',').append(' ');
      buf.append("businessDayConvention").append('=').append(JodaBeanUtils.toString(_businessDayConvention)).append(',').append(' ');
      buf.append("endOfMonth").append('=').append(JodaBeanUtils.toString(_endOfMonth)).append(',').append(' ');
      buf.append("region").append('=').append(JodaBeanUtils.toString(_region)).append(',').append(' ');
      buf.append("volatilityTable").append('=').append(JodaBeanUtils.toString(_volatilityTable)).append(',').append(' ');
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
