/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.ui.beaneditor;

import com.opengamma.id.UniqueId;
import com.opengamma.util.ArgumentChecker;

/**
 * A list item that has a {@code UniqueId} and display name.
 */
public final class UniqueNamedItem implements Comparable<UniqueNamedItem> {

  /**
   * The unique identifier.
   */
  private final UniqueId _uniqueId;
  /**
   * The display name.
   */
  private final String _name;

  //-------------------------------------------------------------------------
  /**
   * Obtains an instance of {@code UniqueNamedItem}.
   * 
   * @param uniqueId  the unique identifier, not null
   * @param name  the display name, not null
   * @return the item, not null
   */
  public static UniqueNamedItem of(UniqueId uniqueId, String name) {
    return new UniqueNamedItem(uniqueId, name);
  }

  //-------------------------------------------------------------------------
  /**
   * Creates an instance.
   * 
   * @param uniqueId  the unique identifier, not null
   * @param name  the display name, not null
   */
  private UniqueNamedItem(UniqueId uniqueId, String name) {
    _uniqueId = ArgumentChecker.notNull(uniqueId, "uniqueId");
    _name = ArgumentChecker.notNull(name, "name");
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the unique identifier.
   * 
   * @return the unique identifier, not null
   */
  public UniqueId getUniqueId() {
    return _uniqueId;
  }

  /**
   * Gets the name.
   * 
   * @return the name, not null
   */
  public String getName() {
    return _name;
  }

  //-------------------------------------------------------------------------
  @Override
  public int compareTo(UniqueNamedItem other) {
    return getName().compareTo(other.getName());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof UniqueNamedItem) {
      UniqueNamedItem other = (UniqueNamedItem) obj;
      return other.getName().equals(getName()) && other.getUniqueId().equals(getUniqueId());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return getUniqueId().hashCode() ^ getName().hashCode();
  }

  @Override
  public String toString() {
    return getName();
  }

}
