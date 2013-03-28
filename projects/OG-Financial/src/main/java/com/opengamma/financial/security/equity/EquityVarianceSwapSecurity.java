/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.equity;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.financial.convention.frequency.Frequency;
import com.opengamma.financial.security.FinancialSecurity;
import com.opengamma.financial.security.FinancialSecurityVisitor;
import com.opengamma.id.ExternalId;
import com.opengamma.util.money.Currency;

/**
 * A security for equity variance swaps.
 */
@BeanDefinition
public class EquityVarianceSwapSecurity extends FinancialSecurity {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The security type.
   */
  public static final String SECURITY_TYPE = "EQUITY VARIANCE SWAP";

  /**
   * The underlying identifier.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _spotUnderlyingId;
  /**
   * The currency.
   */
  @PropertyDefinition(validate = "notNull")
  private Currency _currency;
  /**
   * The strike.
   */
  @PropertyDefinition
  private double _strike;
  /**
   * The notional.
   * TODO document how the sign of the notional implies pay / receive / fixed / realized
   */
  @PropertyDefinition
  private double _notional;
  /**
   * The parameterized as variance flag.
   */
  @PropertyDefinition
  private boolean _parameterizedAsVariance;
  /**
   * The annualization factor.
   */
  @PropertyDefinition
  private double _annualizationFactor;
  /**
   * The first observation date.
   */
  @PropertyDefinition(validate = "notNull")
  private ZonedDateTime _firstObservationDate;
  /**
   * The last observation date.
   */
  @PropertyDefinition(validate = "notNull")
  private ZonedDateTime _lastObservationDate;
  /**
   * The settlement date.
   */
  @PropertyDefinition(validate = "notNull")
  private ZonedDateTime _settlementDate;
  /**
   * The region.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _regionId;
  /**
   * The observation frequency.
   */
  @PropertyDefinition(validate = "notNull")
  private Frequency _observationFrequency;

  EquityVarianceSwapSecurity() { //For builder
    super(SECURITY_TYPE);
  }

  public EquityVarianceSwapSecurity(ExternalId spotUnderlyingId, Currency currency, double strike, double notional,
      boolean parameterizedAsVariance, double annualizationFactor, ZonedDateTime firstObservationDate, ZonedDateTime lastObservationDate,
      ZonedDateTime settlementDate, ExternalId regionId, Frequency observationFrequency) {
    super(SECURITY_TYPE);
    setSpotUnderlyingId(spotUnderlyingId);
    setCurrency(currency);
    setStrike(strike);
    setNotional(notional);
    setParameterizedAsVariance(parameterizedAsVariance);
    setAnnualizationFactor(annualizationFactor);
    setFirstObservationDate(firstObservationDate);
    setLastObservationDate(lastObservationDate);
    setSettlementDate(settlementDate);
    setRegionId(regionId);
    setObservationFrequency(observationFrequency);
  }

  //-------------------------------------------------------------------------
  @Override
  public final <T> T accept(FinancialSecurityVisitor<T> visitor) {
    return visitor.visitEquityVarianceSwapSecurity(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code EquityVarianceSwapSecurity}.
   * @return the meta-bean, not null
   */
  public static EquityVarianceSwapSecurity.Meta meta() {
    return EquityVarianceSwapSecurity.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(EquityVarianceSwapSecurity.Meta.INSTANCE);
  }

  @Override
  public EquityVarianceSwapSecurity.Meta metaBean() {
    return EquityVarianceSwapSecurity.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -2099525766:  // spotUnderlyingId
        return getSpotUnderlyingId();
      case 575402001:  // currency
        return getCurrency();
      case -891985998:  // strike
        return getStrike();
      case 1585636160:  // notional
        return getNotional();
      case 1488612956:  // parameterizedAsVariance
        return isParameterizedAsVariance();
      case 663363412:  // annualizationFactor
        return getAnnualizationFactor();
      case -1644595926:  // firstObservationDate
        return getFirstObservationDate();
      case -1362285436:  // lastObservationDate
        return getLastObservationDate();
      case -295948169:  // settlementDate
        return getSettlementDate();
      case -690339025:  // regionId
        return getRegionId();
      case -213041520:  // observationFrequency
        return getObservationFrequency();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -2099525766:  // spotUnderlyingId
        setSpotUnderlyingId((ExternalId) newValue);
        return;
      case 575402001:  // currency
        setCurrency((Currency) newValue);
        return;
      case -891985998:  // strike
        setStrike((Double) newValue);
        return;
      case 1585636160:  // notional
        setNotional((Double) newValue);
        return;
      case 1488612956:  // parameterizedAsVariance
        setParameterizedAsVariance((Boolean) newValue);
        return;
      case 663363412:  // annualizationFactor
        setAnnualizationFactor((Double) newValue);
        return;
      case -1644595926:  // firstObservationDate
        setFirstObservationDate((ZonedDateTime) newValue);
        return;
      case -1362285436:  // lastObservationDate
        setLastObservationDate((ZonedDateTime) newValue);
        return;
      case -295948169:  // settlementDate
        setSettlementDate((ZonedDateTime) newValue);
        return;
      case -690339025:  // regionId
        setRegionId((ExternalId) newValue);
        return;
      case -213041520:  // observationFrequency
        setObservationFrequency((Frequency) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_spotUnderlyingId, "spotUnderlyingId");
    JodaBeanUtils.notNull(_currency, "currency");
    JodaBeanUtils.notNull(_firstObservationDate, "firstObservationDate");
    JodaBeanUtils.notNull(_lastObservationDate, "lastObservationDate");
    JodaBeanUtils.notNull(_settlementDate, "settlementDate");
    JodaBeanUtils.notNull(_regionId, "regionId");
    JodaBeanUtils.notNull(_observationFrequency, "observationFrequency");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      EquityVarianceSwapSecurity other = (EquityVarianceSwapSecurity) obj;
      return JodaBeanUtils.equal(getSpotUnderlyingId(), other.getSpotUnderlyingId()) &&
          JodaBeanUtils.equal(getCurrency(), other.getCurrency()) &&
          JodaBeanUtils.equal(getStrike(), other.getStrike()) &&
          JodaBeanUtils.equal(getNotional(), other.getNotional()) &&
          JodaBeanUtils.equal(isParameterizedAsVariance(), other.isParameterizedAsVariance()) &&
          JodaBeanUtils.equal(getAnnualizationFactor(), other.getAnnualizationFactor()) &&
          JodaBeanUtils.equal(getFirstObservationDate(), other.getFirstObservationDate()) &&
          JodaBeanUtils.equal(getLastObservationDate(), other.getLastObservationDate()) &&
          JodaBeanUtils.equal(getSettlementDate(), other.getSettlementDate()) &&
          JodaBeanUtils.equal(getRegionId(), other.getRegionId()) &&
          JodaBeanUtils.equal(getObservationFrequency(), other.getObservationFrequency()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getSpotUnderlyingId());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCurrency());
    hash += hash * 31 + JodaBeanUtils.hashCode(getStrike());
    hash += hash * 31 + JodaBeanUtils.hashCode(getNotional());
    hash += hash * 31 + JodaBeanUtils.hashCode(isParameterizedAsVariance());
    hash += hash * 31 + JodaBeanUtils.hashCode(getAnnualizationFactor());
    hash += hash * 31 + JodaBeanUtils.hashCode(getFirstObservationDate());
    hash += hash * 31 + JodaBeanUtils.hashCode(getLastObservationDate());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSettlementDate());
    hash += hash * 31 + JodaBeanUtils.hashCode(getRegionId());
    hash += hash * 31 + JodaBeanUtils.hashCode(getObservationFrequency());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the underlying identifier.
   * @return the value of the property, not null
   */
  public ExternalId getSpotUnderlyingId() {
    return _spotUnderlyingId;
  }

  /**
   * Sets the underlying identifier.
   * @param spotUnderlyingId  the new value of the property, not null
   */
  public void setSpotUnderlyingId(ExternalId spotUnderlyingId) {
    JodaBeanUtils.notNull(spotUnderlyingId, "spotUnderlyingId");
    this._spotUnderlyingId = spotUnderlyingId;
  }

  /**
   * Gets the the {@code spotUnderlyingId} property.
   * @return the property, not null
   */
  public final Property<ExternalId> spotUnderlyingId() {
    return metaBean().spotUnderlyingId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the currency.
   * @return the value of the property, not null
   */
  public Currency getCurrency() {
    return _currency;
  }

  /**
   * Sets the currency.
   * @param currency  the new value of the property, not null
   */
  public void setCurrency(Currency currency) {
    JodaBeanUtils.notNull(currency, "currency");
    this._currency = currency;
  }

  /**
   * Gets the the {@code currency} property.
   * @return the property, not null
   */
  public final Property<Currency> currency() {
    return metaBean().currency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the strike.
   * @return the value of the property
   */
  public double getStrike() {
    return _strike;
  }

  /**
   * Sets the strike.
   * @param strike  the new value of the property
   */
  public void setStrike(double strike) {
    this._strike = strike;
  }

  /**
   * Gets the the {@code strike} property.
   * @return the property, not null
   */
  public final Property<Double> strike() {
    return metaBean().strike().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the notional.
   * TODO document how the sign of the notional implies pay / receive / fixed / realized
   * @return the value of the property
   */
  public double getNotional() {
    return _notional;
  }

  /**
   * Sets the notional.
   * TODO document how the sign of the notional implies pay / receive / fixed / realized
   * @param notional  the new value of the property
   */
  public void setNotional(double notional) {
    this._notional = notional;
  }

  /**
   * Gets the the {@code notional} property.
   * TODO document how the sign of the notional implies pay / receive / fixed / realized
   * @return the property, not null
   */
  public final Property<Double> notional() {
    return metaBean().notional().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the parameterized as variance flag.
   * @return the value of the property
   */
  public boolean isParameterizedAsVariance() {
    return _parameterizedAsVariance;
  }

  /**
   * Sets the parameterized as variance flag.
   * @param parameterizedAsVariance  the new value of the property
   */
  public void setParameterizedAsVariance(boolean parameterizedAsVariance) {
    this._parameterizedAsVariance = parameterizedAsVariance;
  }

  /**
   * Gets the the {@code parameterizedAsVariance} property.
   * @return the property, not null
   */
  public final Property<Boolean> parameterizedAsVariance() {
    return metaBean().parameterizedAsVariance().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the annualization factor.
   * @return the value of the property
   */
  public double getAnnualizationFactor() {
    return _annualizationFactor;
  }

  /**
   * Sets the annualization factor.
   * @param annualizationFactor  the new value of the property
   */
  public void setAnnualizationFactor(double annualizationFactor) {
    this._annualizationFactor = annualizationFactor;
  }

  /**
   * Gets the the {@code annualizationFactor} property.
   * @return the property, not null
   */
  public final Property<Double> annualizationFactor() {
    return metaBean().annualizationFactor().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the first observation date.
   * @return the value of the property, not null
   */
  public ZonedDateTime getFirstObservationDate() {
    return _firstObservationDate;
  }

  /**
   * Sets the first observation date.
   * @param firstObservationDate  the new value of the property, not null
   */
  public void setFirstObservationDate(ZonedDateTime firstObservationDate) {
    JodaBeanUtils.notNull(firstObservationDate, "firstObservationDate");
    this._firstObservationDate = firstObservationDate;
  }

  /**
   * Gets the the {@code firstObservationDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTime> firstObservationDate() {
    return metaBean().firstObservationDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the last observation date.
   * @return the value of the property, not null
   */
  public ZonedDateTime getLastObservationDate() {
    return _lastObservationDate;
  }

  /**
   * Sets the last observation date.
   * @param lastObservationDate  the new value of the property, not null
   */
  public void setLastObservationDate(ZonedDateTime lastObservationDate) {
    JodaBeanUtils.notNull(lastObservationDate, "lastObservationDate");
    this._lastObservationDate = lastObservationDate;
  }

  /**
   * Gets the the {@code lastObservationDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTime> lastObservationDate() {
    return metaBean().lastObservationDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the settlement date.
   * @return the value of the property, not null
   */
  public ZonedDateTime getSettlementDate() {
    return _settlementDate;
  }

  /**
   * Sets the settlement date.
   * @param settlementDate  the new value of the property, not null
   */
  public void setSettlementDate(ZonedDateTime settlementDate) {
    JodaBeanUtils.notNull(settlementDate, "settlementDate");
    this._settlementDate = settlementDate;
  }

  /**
   * Gets the the {@code settlementDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTime> settlementDate() {
    return metaBean().settlementDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the region.
   * @return the value of the property, not null
   */
  public ExternalId getRegionId() {
    return _regionId;
  }

  /**
   * Sets the region.
   * @param regionId  the new value of the property, not null
   */
  public void setRegionId(ExternalId regionId) {
    JodaBeanUtils.notNull(regionId, "regionId");
    this._regionId = regionId;
  }

  /**
   * Gets the the {@code regionId} property.
   * @return the property, not null
   */
  public final Property<ExternalId> regionId() {
    return metaBean().regionId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the observation frequency.
   * @return the value of the property, not null
   */
  public Frequency getObservationFrequency() {
    return _observationFrequency;
  }

  /**
   * Sets the observation frequency.
   * @param observationFrequency  the new value of the property, not null
   */
  public void setObservationFrequency(Frequency observationFrequency) {
    JodaBeanUtils.notNull(observationFrequency, "observationFrequency");
    this._observationFrequency = observationFrequency;
  }

  /**
   * Gets the the {@code observationFrequency} property.
   * @return the property, not null
   */
  public final Property<Frequency> observationFrequency() {
    return metaBean().observationFrequency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code EquityVarianceSwapSecurity}.
   */
  public static class Meta extends FinancialSecurity.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code spotUnderlyingId} property.
     */
    private final MetaProperty<ExternalId> _spotUnderlyingId = DirectMetaProperty.ofReadWrite(
        this, "spotUnderlyingId", EquityVarianceSwapSecurity.class, ExternalId.class);
    /**
     * The meta-property for the {@code currency} property.
     */
    private final MetaProperty<Currency> _currency = DirectMetaProperty.ofReadWrite(
        this, "currency", EquityVarianceSwapSecurity.class, Currency.class);
    /**
     * The meta-property for the {@code strike} property.
     */
    private final MetaProperty<Double> _strike = DirectMetaProperty.ofReadWrite(
        this, "strike", EquityVarianceSwapSecurity.class, Double.TYPE);
    /**
     * The meta-property for the {@code notional} property.
     */
    private final MetaProperty<Double> _notional = DirectMetaProperty.ofReadWrite(
        this, "notional", EquityVarianceSwapSecurity.class, Double.TYPE);
    /**
     * The meta-property for the {@code parameterizedAsVariance} property.
     */
    private final MetaProperty<Boolean> _parameterizedAsVariance = DirectMetaProperty.ofReadWrite(
        this, "parameterizedAsVariance", EquityVarianceSwapSecurity.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code annualizationFactor} property.
     */
    private final MetaProperty<Double> _annualizationFactor = DirectMetaProperty.ofReadWrite(
        this, "annualizationFactor", EquityVarianceSwapSecurity.class, Double.TYPE);
    /**
     * The meta-property for the {@code firstObservationDate} property.
     */
    private final MetaProperty<ZonedDateTime> _firstObservationDate = DirectMetaProperty.ofReadWrite(
        this, "firstObservationDate", EquityVarianceSwapSecurity.class, ZonedDateTime.class);
    /**
     * The meta-property for the {@code lastObservationDate} property.
     */
    private final MetaProperty<ZonedDateTime> _lastObservationDate = DirectMetaProperty.ofReadWrite(
        this, "lastObservationDate", EquityVarianceSwapSecurity.class, ZonedDateTime.class);
    /**
     * The meta-property for the {@code settlementDate} property.
     */
    private final MetaProperty<ZonedDateTime> _settlementDate = DirectMetaProperty.ofReadWrite(
        this, "settlementDate", EquityVarianceSwapSecurity.class, ZonedDateTime.class);
    /**
     * The meta-property for the {@code regionId} property.
     */
    private final MetaProperty<ExternalId> _regionId = DirectMetaProperty.ofReadWrite(
        this, "regionId", EquityVarianceSwapSecurity.class, ExternalId.class);
    /**
     * The meta-property for the {@code observationFrequency} property.
     */
    private final MetaProperty<Frequency> _observationFrequency = DirectMetaProperty.ofReadWrite(
        this, "observationFrequency", EquityVarianceSwapSecurity.class, Frequency.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "spotUnderlyingId",
        "currency",
        "strike",
        "notional",
        "parameterizedAsVariance",
        "annualizationFactor",
        "firstObservationDate",
        "lastObservationDate",
        "settlementDate",
        "regionId",
        "observationFrequency");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -2099525766:  // spotUnderlyingId
          return _spotUnderlyingId;
        case 575402001:  // currency
          return _currency;
        case -891985998:  // strike
          return _strike;
        case 1585636160:  // notional
          return _notional;
        case 1488612956:  // parameterizedAsVariance
          return _parameterizedAsVariance;
        case 663363412:  // annualizationFactor
          return _annualizationFactor;
        case -1644595926:  // firstObservationDate
          return _firstObservationDate;
        case -1362285436:  // lastObservationDate
          return _lastObservationDate;
        case -295948169:  // settlementDate
          return _settlementDate;
        case -690339025:  // regionId
          return _regionId;
        case -213041520:  // observationFrequency
          return _observationFrequency;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends EquityVarianceSwapSecurity> builder() {
      return new DirectBeanBuilder<EquityVarianceSwapSecurity>(new EquityVarianceSwapSecurity());
    }

    @Override
    public Class<? extends EquityVarianceSwapSecurity> beanType() {
      return EquityVarianceSwapSecurity.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code spotUnderlyingId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> spotUnderlyingId() {
      return _spotUnderlyingId;
    }

    /**
     * The meta-property for the {@code currency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Currency> currency() {
      return _currency;
    }

    /**
     * The meta-property for the {@code strike} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> strike() {
      return _strike;
    }

    /**
     * The meta-property for the {@code notional} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> notional() {
      return _notional;
    }

    /**
     * The meta-property for the {@code parameterizedAsVariance} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> parameterizedAsVariance() {
      return _parameterizedAsVariance;
    }

    /**
     * The meta-property for the {@code annualizationFactor} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> annualizationFactor() {
      return _annualizationFactor;
    }

    /**
     * The meta-property for the {@code firstObservationDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTime> firstObservationDate() {
      return _firstObservationDate;
    }

    /**
     * The meta-property for the {@code lastObservationDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTime> lastObservationDate() {
      return _lastObservationDate;
    }

    /**
     * The meta-property for the {@code settlementDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTime> settlementDate() {
      return _settlementDate;
    }

    /**
     * The meta-property for the {@code regionId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> regionId() {
      return _regionId;
    }

    /**
     * The meta-property for the {@code observationFrequency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Frequency> observationFrequency() {
      return _observationFrequency;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
