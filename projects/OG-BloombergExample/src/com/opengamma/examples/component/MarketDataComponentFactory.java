/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.examples.component;

import java.util.LinkedHashMap;
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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.opengamma.activfinancial.livedata.ActivMarketDataAvailabilityUtils;
import com.opengamma.bbg.util.BloombergDataUtils;
import com.opengamma.component.ComponentInfo;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.AbstractComponentFactory;
import com.opengamma.core.security.SecuritySource;
import com.opengamma.engine.marketdata.InMemoryNamedMarketDataSpecificationRepository;
import com.opengamma.engine.marketdata.MarketDataProvider;
import com.opengamma.engine.marketdata.MarketDataProviderFactory;
import com.opengamma.engine.marketdata.NamedMarketDataSpecificationRepository;
import com.opengamma.engine.marketdata.availability.MarketDataAvailabilityProvider;
import com.opengamma.engine.marketdata.availability.UnionMarketDataAvailabilityProvider;
import com.opengamma.engine.marketdata.live.LiveMarketDataProvider;
import com.opengamma.engine.marketdata.live.LiveMarketDataProviderFactory;
import com.opengamma.engine.marketdata.spec.CombinedMarketDataSpecification;
import com.opengamma.engine.marketdata.spec.LatestHistoricalMarketDataSpecification;
import com.opengamma.engine.marketdata.spec.LiveMarketDataSpecification;
import com.opengamma.engine.marketdata.spec.MarketDataSpecification;
import com.opengamma.livedata.LiveDataClient;
import com.opengamma.livedata.client.RemoteLiveDataClientFactoryBean;
import com.opengamma.util.jms.JmsConnector;

/**
 * Component factory for market data.
 */
@BeanDefinition
public class MarketDataComponentFactory extends AbstractComponentFactory {
  
  private static final String LIVE_PROVIDER_PREFIX = "Live market data";
  
  private static final String BLOOMBERG_LIVE_SOURCE_NAME = LIVE_PROVIDER_PREFIX + " (Bloomberg)";
  private static final String ACTIV_LIVE_SOURCE_NAME = LIVE_PROVIDER_PREFIX + " (Activ)";
  private static final String BLOOMBERG_FIRST_LIVE_SOURCE_NAME = LIVE_PROVIDER_PREFIX + " (Bloomberg, Activ)";
  private static final String ACTIV_FIRST_LIVE_SOURCE_NAME = LIVE_PROVIDER_PREFIX + " (Activ, Bloomberg)";
  
  /**
   * The classifier under which to publish.
   */
  @PropertyDefinition(validate = "notNull")
  private String _classifier;
  /**
   * The security source.
   */
  @PropertyDefinition(validate = "notNull")
  private SecuritySource _securitySource;
  /**
   * The JMS connector.
   */
  @PropertyDefinition(validate = "notNull")
  private JmsConnector _jmsConnector;
  /**
   * The Bloomberg subscription topic.
   */
  @PropertyDefinition(validate = "notNull")
  private String _bbgSubscriptionTopic;
  /**
   * The Bloomberg entitlement topic.
   */
  @PropertyDefinition(validate = "notNull")
  private String _bbgEntitlementTopic;
  /**
   * The Bloomberg heartbeat topic.
   */
  @PropertyDefinition(validate = "notNull")
  private String _bbgHeartbeatTopic;
  /**
   * The Activ subscription topic.
   */
  @PropertyDefinition(validate = "notNull")
  private String _activSubscriptionTopic;
  /**
   * The Activ entitlement topic.
   */
  @PropertyDefinition(validate = "notNull")
  private String _activEntitlementTopic;
  /**
   * The Activ heartbeat topic.
   */
  @PropertyDefinition(validate = "notNull")
  private String _activHeartbeatTopic;
  /**
   * The Activ-first subscription topic.
   */
  @PropertyDefinition(validate = "notNull")
  private String _activFirstSubscriptionTopic;
  /**
   * The Activ-first entitlement topic.
   */
  @PropertyDefinition(validate = "notNull")
  private String _activFirstEntitlementTopic;
  /**
   * The Activ-first heartbeat topic.
   */
  @PropertyDefinition(validate = "notNull")
  private String _activFirstHeartbeatTopic;
  /**
   * The combined subscription topic.
   */
  @PropertyDefinition(validate = "notNull")
  private String _bbgFirstSubscriptionTopic;
  /**
   * The combined entitlement topic.
   */
  @PropertyDefinition(validate = "notNull")
  private String _bbgFirstEntitlementTopic;
  /**
   * The combined heartbeat topic.
   */
  @PropertyDefinition(validate = "notNull")
  private String _bbgFirstHeartbeatTopic;
  
  @Override
  public void init(ComponentRepository repo, LinkedHashMap<String, String> configuration) throws Exception {
    initLiveMarketDataProviderFactory(repo);
    initNamedMarketDataSpecificationRepository(repo);
  }

  private MarketDataProviderFactory initLiveMarketDataProviderFactory(ComponentRepository repo) {
    // REVIEW jonathan 2012-02-29 -- feels like we should be passing in one URL for each live market data server
    // (Bloomberg, Activ, ActivFirst, Combined) and this should tell us its name, JMS topics and availability provider.
    
    LiveDataClient bbgLiveDataClient = createLiveDataClient(getBbgSubscriptionTopic(), getBbgEntitlementTopic(), getBbgHeartbeatTopic());
    MarketDataAvailabilityProvider bbgAvailabilityProvider = BloombergDataUtils.createAvailabilityProvider(getSecuritySource());
    MarketDataProvider bbgProvider = new LiveMarketDataProvider(bbgLiveDataClient, getSecuritySource(), bbgAvailabilityProvider);
    
    LiveDataClient activLiveDataClient = createLiveDataClient(getActivSubscriptionTopic(), getActivEntitlementTopic(), getActivHeartbeatTopic());
    MarketDataAvailabilityProvider activAvailabilityProvider = ActivMarketDataAvailabilityUtils.createAvailabilityProvider(getSecuritySource());
    MarketDataProvider activProvider = new LiveMarketDataProvider(activLiveDataClient, getSecuritySource(), activAvailabilityProvider);
    
    LiveDataClient activFirstLiveDataClient = createLiveDataClient(getActivFirstSubscriptionTopic(), getActivFirstEntitlementTopic(), getActivFirstHeartbeatTopic());
    MarketDataAvailabilityProvider activFirstAvailabilityProvider = new UnionMarketDataAvailabilityProvider(ImmutableList.of(activAvailabilityProvider, bbgAvailabilityProvider));
    MarketDataProvider activFirstProvider = new LiveMarketDataProvider(activFirstLiveDataClient, getSecuritySource(), activFirstAvailabilityProvider);
    
    LiveDataClient bbgFirstLiveDataClient = createLiveDataClient(getBbgFirstSubscriptionTopic(), getBbgFirstEntitlementTopic(), getBbgFirstHeartbeatTopic());
    MarketDataAvailabilityProvider bbgFirstAvailabilityProvider = new UnionMarketDataAvailabilityProvider(ImmutableList.of(bbgAvailabilityProvider, activAvailabilityProvider));
    MarketDataProvider bbgFirstProvider = new LiveMarketDataProvider(bbgFirstLiveDataClient, getSecuritySource(), bbgFirstAvailabilityProvider);
    
    Map<String, MarketDataProvider> sourceToProviderMap = ImmutableMap.of(
        BLOOMBERG_LIVE_SOURCE_NAME, bbgProvider,
        ACTIV_LIVE_SOURCE_NAME, activProvider,
        BLOOMBERG_FIRST_LIVE_SOURCE_NAME, bbgFirstProvider,
        ACTIV_FIRST_LIVE_SOURCE_NAME, activFirstProvider);
    LiveMarketDataProviderFactory liveMarketDataProviderFactory = new LiveMarketDataProviderFactory(bbgProvider, sourceToProviderMap);
    ComponentInfo info = new ComponentInfo(MarketDataProviderFactory.class, getClassifier());
    repo.registerComponent(info, liveMarketDataProviderFactory);
    return liveMarketDataProviderFactory;
  }
  
  protected NamedMarketDataSpecificationRepository initNamedMarketDataSpecificationRepository(ComponentRepository repo) {
    InMemoryNamedMarketDataSpecificationRepository specRepository = new InMemoryNamedMarketDataSpecificationRepository();

    LiveMarketDataSpecification bbgFirstSpec = new LiveMarketDataSpecification(BLOOMBERG_FIRST_LIVE_SOURCE_NAME);
    specRepository.addSpecification(BLOOMBERG_FIRST_LIVE_SOURCE_NAME, bbgFirstSpec);
    
    LiveMarketDataSpecification bbgSpec = new LiveMarketDataSpecification(BLOOMBERG_LIVE_SOURCE_NAME);
    specRepository.addSpecification(BLOOMBERG_LIVE_SOURCE_NAME, bbgSpec);

    LiveMarketDataSpecification activFirstSpec = new LiveMarketDataSpecification(ACTIV_FIRST_LIVE_SOURCE_NAME);
    specRepository.addSpecification(ACTIV_FIRST_LIVE_SOURCE_NAME, activFirstSpec);
    
    LiveMarketDataSpecification activSpec = new LiveMarketDataSpecification(ACTIV_LIVE_SOURCE_NAME);
    specRepository.addSpecification(ACTIV_LIVE_SOURCE_NAME, activSpec);
    
    String bbgThenLatestHistoricalName = "Bloomberg, latest historical";
    MarketDataSpecification bbgThenLatestHistoricalSpec = new CombinedMarketDataSpecification(bbgSpec, new LatestHistoricalMarketDataSpecification());
    specRepository.addSpecification(bbgThenLatestHistoricalName, bbgThenLatestHistoricalSpec);
    
    ComponentInfo info = new ComponentInfo(NamedMarketDataSpecificationRepository.class, getClassifier());
    repo.registerComponent(info, specRepository);
    return specRepository;
  }
  
  protected LiveDataClient createLiveDataClient(String subscriptionTopic, String entitlementTopic, String heartbeatTopic) {
    RemoteLiveDataClientFactoryBean ldcFb = new RemoteLiveDataClientFactoryBean();
    ldcFb.setJmsConnector(getJmsConnector());
    ldcFb.setSubscriptionTopic(subscriptionTopic);
    ldcFb.setEntitlementTopic(entitlementTopic);
    ldcFb.setHeartbeatTopic(heartbeatTopic);
    return ldcFb.getObjectCreating();
  }
  
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code IntegrationMarketDataComponentFactory}.
   * @return the meta-bean, not null
   */
  public static IntegrationMarketDataComponentFactory.Meta meta() {
    return IntegrationMarketDataComponentFactory.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(IntegrationMarketDataComponentFactory.Meta.INSTANCE);
  }

  @Override
  public IntegrationMarketDataComponentFactory.Meta metaBean() {
    return IntegrationMarketDataComponentFactory.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -281470431:  // classifier
        return getClassifier();
      case -702456965:  // securitySource
        return getSecuritySource();
      case -1495762275:  // jmsConnector
        return getJmsConnector();
      case -1347590997:  // bbgSubscriptionTopic
        return getBbgSubscriptionTopic();
      case 1424045641:  // bbgEntitlementTopic
        return getBbgEntitlementTopic();
      case 2142380442:  // bbgHeartbeatTopic
        return getBbgHeartbeatTopic();
      case -183104877:  // activSubscriptionTopic
        return getActivSubscriptionTopic();
      case 1045967713:  // activEntitlementTopic
        return getActivEntitlementTopic();
      case -1938455374:  // activHeartbeatTopic
        return getActivHeartbeatTopic();
      case -1527308959:  // activFirstSubscriptionTopic
        return getActivFirstSubscriptionTopic();
      case 1002606291:  // activFirstEntitlementTopic
        return getActivFirstEntitlementTopic();
      case 54793380:  // activFirstHeartbeatTopic
        return getActivFirstHeartbeatTopic();
      case -1378069943:  // bbgFirstSubscriptionTopic
        return getBbgFirstSubscriptionTopic();
      case -932242197:  // bbgFirstEntitlementTopic
        return getBbgFirstEntitlementTopic();
      case 1970096316:  // bbgFirstHeartbeatTopic
        return getBbgFirstHeartbeatTopic();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -281470431:  // classifier
        setClassifier((String) newValue);
        return;
      case -702456965:  // securitySource
        setSecuritySource((SecuritySource) newValue);
        return;
      case -1495762275:  // jmsConnector
        setJmsConnector((JmsConnector) newValue);
        return;
      case -1347590997:  // bbgSubscriptionTopic
        setBbgSubscriptionTopic((String) newValue);
        return;
      case 1424045641:  // bbgEntitlementTopic
        setBbgEntitlementTopic((String) newValue);
        return;
      case 2142380442:  // bbgHeartbeatTopic
        setBbgHeartbeatTopic((String) newValue);
        return;
      case -183104877:  // activSubscriptionTopic
        setActivSubscriptionTopic((String) newValue);
        return;
      case 1045967713:  // activEntitlementTopic
        setActivEntitlementTopic((String) newValue);
        return;
      case -1938455374:  // activHeartbeatTopic
        setActivHeartbeatTopic((String) newValue);
        return;
      case -1527308959:  // activFirstSubscriptionTopic
        setActivFirstSubscriptionTopic((String) newValue);
        return;
      case 1002606291:  // activFirstEntitlementTopic
        setActivFirstEntitlementTopic((String) newValue);
        return;
      case 54793380:  // activFirstHeartbeatTopic
        setActivFirstHeartbeatTopic((String) newValue);
        return;
      case -1378069943:  // bbgFirstSubscriptionTopic
        setBbgFirstSubscriptionTopic((String) newValue);
        return;
      case -932242197:  // bbgFirstEntitlementTopic
        setBbgFirstEntitlementTopic((String) newValue);
        return;
      case 1970096316:  // bbgFirstHeartbeatTopic
        setBbgFirstHeartbeatTopic((String) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_classifier, "classifier");
    JodaBeanUtils.notNull(_securitySource, "securitySource");
    JodaBeanUtils.notNull(_jmsConnector, "jmsConnector");
    JodaBeanUtils.notNull(_bbgSubscriptionTopic, "bbgSubscriptionTopic");
    JodaBeanUtils.notNull(_bbgEntitlementTopic, "bbgEntitlementTopic");
    JodaBeanUtils.notNull(_bbgHeartbeatTopic, "bbgHeartbeatTopic");
    JodaBeanUtils.notNull(_activSubscriptionTopic, "activSubscriptionTopic");
    JodaBeanUtils.notNull(_activEntitlementTopic, "activEntitlementTopic");
    JodaBeanUtils.notNull(_activHeartbeatTopic, "activHeartbeatTopic");
    JodaBeanUtils.notNull(_activFirstSubscriptionTopic, "activFirstSubscriptionTopic");
    JodaBeanUtils.notNull(_activFirstEntitlementTopic, "activFirstEntitlementTopic");
    JodaBeanUtils.notNull(_activFirstHeartbeatTopic, "activFirstHeartbeatTopic");
    JodaBeanUtils.notNull(_bbgFirstSubscriptionTopic, "bbgFirstSubscriptionTopic");
    JodaBeanUtils.notNull(_bbgFirstEntitlementTopic, "bbgFirstEntitlementTopic");
    JodaBeanUtils.notNull(_bbgFirstHeartbeatTopic, "bbgFirstHeartbeatTopic");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      IntegrationMarketDataComponentFactory other = (IntegrationMarketDataComponentFactory) obj;
      return JodaBeanUtils.equal(getClassifier(), other.getClassifier()) &&
          JodaBeanUtils.equal(getSecuritySource(), other.getSecuritySource()) &&
          JodaBeanUtils.equal(getJmsConnector(), other.getJmsConnector()) &&
          JodaBeanUtils.equal(getBbgSubscriptionTopic(), other.getBbgSubscriptionTopic()) &&
          JodaBeanUtils.equal(getBbgEntitlementTopic(), other.getBbgEntitlementTopic()) &&
          JodaBeanUtils.equal(getBbgHeartbeatTopic(), other.getBbgHeartbeatTopic()) &&
          JodaBeanUtils.equal(getActivSubscriptionTopic(), other.getActivSubscriptionTopic()) &&
          JodaBeanUtils.equal(getActivEntitlementTopic(), other.getActivEntitlementTopic()) &&
          JodaBeanUtils.equal(getActivHeartbeatTopic(), other.getActivHeartbeatTopic()) &&
          JodaBeanUtils.equal(getActivFirstSubscriptionTopic(), other.getActivFirstSubscriptionTopic()) &&
          JodaBeanUtils.equal(getActivFirstEntitlementTopic(), other.getActivFirstEntitlementTopic()) &&
          JodaBeanUtils.equal(getActivFirstHeartbeatTopic(), other.getActivFirstHeartbeatTopic()) &&
          JodaBeanUtils.equal(getBbgFirstSubscriptionTopic(), other.getBbgFirstSubscriptionTopic()) &&
          JodaBeanUtils.equal(getBbgFirstEntitlementTopic(), other.getBbgFirstEntitlementTopic()) &&
          JodaBeanUtils.equal(getBbgFirstHeartbeatTopic(), other.getBbgFirstHeartbeatTopic()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getClassifier());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSecuritySource());
    hash += hash * 31 + JodaBeanUtils.hashCode(getJmsConnector());
    hash += hash * 31 + JodaBeanUtils.hashCode(getBbgSubscriptionTopic());
    hash += hash * 31 + JodaBeanUtils.hashCode(getBbgEntitlementTopic());
    hash += hash * 31 + JodaBeanUtils.hashCode(getBbgHeartbeatTopic());
    hash += hash * 31 + JodaBeanUtils.hashCode(getActivSubscriptionTopic());
    hash += hash * 31 + JodaBeanUtils.hashCode(getActivEntitlementTopic());
    hash += hash * 31 + JodaBeanUtils.hashCode(getActivHeartbeatTopic());
    hash += hash * 31 + JodaBeanUtils.hashCode(getActivFirstSubscriptionTopic());
    hash += hash * 31 + JodaBeanUtils.hashCode(getActivFirstEntitlementTopic());
    hash += hash * 31 + JodaBeanUtils.hashCode(getActivFirstHeartbeatTopic());
    hash += hash * 31 + JodaBeanUtils.hashCode(getBbgFirstSubscriptionTopic());
    hash += hash * 31 + JodaBeanUtils.hashCode(getBbgFirstEntitlementTopic());
    hash += hash * 31 + JodaBeanUtils.hashCode(getBbgFirstHeartbeatTopic());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the classifier under which to publish.
   * @return the value of the property, not null
   */
  public String getClassifier() {
    return _classifier;
  }

  /**
   * Sets the classifier under which to publish.
   * @param classifier  the new value of the property, not null
   */
  public void setClassifier(String classifier) {
    JodaBeanUtils.notNull(classifier, "classifier");
    this._classifier = classifier;
  }

  /**
   * Gets the the {@code classifier} property.
   * @return the property, not null
   */
  public final Property<String> classifier() {
    return metaBean().classifier().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the security source.
   * @return the value of the property, not null
   */
  public SecuritySource getSecuritySource() {
    return _securitySource;
  }

  /**
   * Sets the security source.
   * @param securitySource  the new value of the property, not null
   */
  public void setSecuritySource(SecuritySource securitySource) {
    JodaBeanUtils.notNull(securitySource, "securitySource");
    this._securitySource = securitySource;
  }

  /**
   * Gets the the {@code securitySource} property.
   * @return the property, not null
   */
  public final Property<SecuritySource> securitySource() {
    return metaBean().securitySource().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the JMS connector.
   * @return the value of the property, not null
   */
  public JmsConnector getJmsConnector() {
    return _jmsConnector;
  }

  /**
   * Sets the JMS connector.
   * @param jmsConnector  the new value of the property, not null
   */
  public void setJmsConnector(JmsConnector jmsConnector) {
    JodaBeanUtils.notNull(jmsConnector, "jmsConnector");
    this._jmsConnector = jmsConnector;
  }

  /**
   * Gets the the {@code jmsConnector} property.
   * @return the property, not null
   */
  public final Property<JmsConnector> jmsConnector() {
    return metaBean().jmsConnector().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the Bloomberg subscription topic.
   * @return the value of the property, not null
   */
  public String getBbgSubscriptionTopic() {
    return _bbgSubscriptionTopic;
  }

  /**
   * Sets the Bloomberg subscription topic.
   * @param bbgSubscriptionTopic  the new value of the property, not null
   */
  public void setBbgSubscriptionTopic(String bbgSubscriptionTopic) {
    JodaBeanUtils.notNull(bbgSubscriptionTopic, "bbgSubscriptionTopic");
    this._bbgSubscriptionTopic = bbgSubscriptionTopic;
  }

  /**
   * Gets the the {@code bbgSubscriptionTopic} property.
   * @return the property, not null
   */
  public final Property<String> bbgSubscriptionTopic() {
    return metaBean().bbgSubscriptionTopic().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the Bloomberg entitlement topic.
   * @return the value of the property, not null
   */
  public String getBbgEntitlementTopic() {
    return _bbgEntitlementTopic;
  }

  /**
   * Sets the Bloomberg entitlement topic.
   * @param bbgEntitlementTopic  the new value of the property, not null
   */
  public void setBbgEntitlementTopic(String bbgEntitlementTopic) {
    JodaBeanUtils.notNull(bbgEntitlementTopic, "bbgEntitlementTopic");
    this._bbgEntitlementTopic = bbgEntitlementTopic;
  }

  /**
   * Gets the the {@code bbgEntitlementTopic} property.
   * @return the property, not null
   */
  public final Property<String> bbgEntitlementTopic() {
    return metaBean().bbgEntitlementTopic().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the Bloomberg heartbeat topic.
   * @return the value of the property, not null
   */
  public String getBbgHeartbeatTopic() {
    return _bbgHeartbeatTopic;
  }

  /**
   * Sets the Bloomberg heartbeat topic.
   * @param bbgHeartbeatTopic  the new value of the property, not null
   */
  public void setBbgHeartbeatTopic(String bbgHeartbeatTopic) {
    JodaBeanUtils.notNull(bbgHeartbeatTopic, "bbgHeartbeatTopic");
    this._bbgHeartbeatTopic = bbgHeartbeatTopic;
  }

  /**
   * Gets the the {@code bbgHeartbeatTopic} property.
   * @return the property, not null
   */
  public final Property<String> bbgHeartbeatTopic() {
    return metaBean().bbgHeartbeatTopic().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the Activ subscription topic.
   * @return the value of the property, not null
   */
  public String getActivSubscriptionTopic() {
    return _activSubscriptionTopic;
  }

  /**
   * Sets the Activ subscription topic.
   * @param activSubscriptionTopic  the new value of the property, not null
   */
  public void setActivSubscriptionTopic(String activSubscriptionTopic) {
    JodaBeanUtils.notNull(activSubscriptionTopic, "activSubscriptionTopic");
    this._activSubscriptionTopic = activSubscriptionTopic;
  }

  /**
   * Gets the the {@code activSubscriptionTopic} property.
   * @return the property, not null
   */
  public final Property<String> activSubscriptionTopic() {
    return metaBean().activSubscriptionTopic().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the Activ entitlement topic.
   * @return the value of the property, not null
   */
  public String getActivEntitlementTopic() {
    return _activEntitlementTopic;
  }

  /**
   * Sets the Activ entitlement topic.
   * @param activEntitlementTopic  the new value of the property, not null
   */
  public void setActivEntitlementTopic(String activEntitlementTopic) {
    JodaBeanUtils.notNull(activEntitlementTopic, "activEntitlementTopic");
    this._activEntitlementTopic = activEntitlementTopic;
  }

  /**
   * Gets the the {@code activEntitlementTopic} property.
   * @return the property, not null
   */
  public final Property<String> activEntitlementTopic() {
    return metaBean().activEntitlementTopic().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the Activ heartbeat topic.
   * @return the value of the property, not null
   */
  public String getActivHeartbeatTopic() {
    return _activHeartbeatTopic;
  }

  /**
   * Sets the Activ heartbeat topic.
   * @param activHeartbeatTopic  the new value of the property, not null
   */
  public void setActivHeartbeatTopic(String activHeartbeatTopic) {
    JodaBeanUtils.notNull(activHeartbeatTopic, "activHeartbeatTopic");
    this._activHeartbeatTopic = activHeartbeatTopic;
  }

  /**
   * Gets the the {@code activHeartbeatTopic} property.
   * @return the property, not null
   */
  public final Property<String> activHeartbeatTopic() {
    return metaBean().activHeartbeatTopic().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the Activ-first subscription topic.
   * @return the value of the property, not null
   */
  public String getActivFirstSubscriptionTopic() {
    return _activFirstSubscriptionTopic;
  }

  /**
   * Sets the Activ-first subscription topic.
   * @param activFirstSubscriptionTopic  the new value of the property, not null
   */
  public void setActivFirstSubscriptionTopic(String activFirstSubscriptionTopic) {
    JodaBeanUtils.notNull(activFirstSubscriptionTopic, "activFirstSubscriptionTopic");
    this._activFirstSubscriptionTopic = activFirstSubscriptionTopic;
  }

  /**
   * Gets the the {@code activFirstSubscriptionTopic} property.
   * @return the property, not null
   */
  public final Property<String> activFirstSubscriptionTopic() {
    return metaBean().activFirstSubscriptionTopic().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the Activ-first entitlement topic.
   * @return the value of the property, not null
   */
  public String getActivFirstEntitlementTopic() {
    return _activFirstEntitlementTopic;
  }

  /**
   * Sets the Activ-first entitlement topic.
   * @param activFirstEntitlementTopic  the new value of the property, not null
   */
  public void setActivFirstEntitlementTopic(String activFirstEntitlementTopic) {
    JodaBeanUtils.notNull(activFirstEntitlementTopic, "activFirstEntitlementTopic");
    this._activFirstEntitlementTopic = activFirstEntitlementTopic;
  }

  /**
   * Gets the the {@code activFirstEntitlementTopic} property.
   * @return the property, not null
   */
  public final Property<String> activFirstEntitlementTopic() {
    return metaBean().activFirstEntitlementTopic().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the Activ-first heartbeat topic.
   * @return the value of the property, not null
   */
  public String getActivFirstHeartbeatTopic() {
    return _activFirstHeartbeatTopic;
  }

  /**
   * Sets the Activ-first heartbeat topic.
   * @param activFirstHeartbeatTopic  the new value of the property, not null
   */
  public void setActivFirstHeartbeatTopic(String activFirstHeartbeatTopic) {
    JodaBeanUtils.notNull(activFirstHeartbeatTopic, "activFirstHeartbeatTopic");
    this._activFirstHeartbeatTopic = activFirstHeartbeatTopic;
  }

  /**
   * Gets the the {@code activFirstHeartbeatTopic} property.
   * @return the property, not null
   */
  public final Property<String> activFirstHeartbeatTopic() {
    return metaBean().activFirstHeartbeatTopic().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the combined subscription topic.
   * @return the value of the property, not null
   */
  public String getBbgFirstSubscriptionTopic() {
    return _bbgFirstSubscriptionTopic;
  }

  /**
   * Sets the combined subscription topic.
   * @param bbgFirstSubscriptionTopic  the new value of the property, not null
   */
  public void setBbgFirstSubscriptionTopic(String bbgFirstSubscriptionTopic) {
    JodaBeanUtils.notNull(bbgFirstSubscriptionTopic, "bbgFirstSubscriptionTopic");
    this._bbgFirstSubscriptionTopic = bbgFirstSubscriptionTopic;
  }

  /**
   * Gets the the {@code bbgFirstSubscriptionTopic} property.
   * @return the property, not null
   */
  public final Property<String> bbgFirstSubscriptionTopic() {
    return metaBean().bbgFirstSubscriptionTopic().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the combined entitlement topic.
   * @return the value of the property, not null
   */
  public String getBbgFirstEntitlementTopic() {
    return _bbgFirstEntitlementTopic;
  }

  /**
   * Sets the combined entitlement topic.
   * @param bbgFirstEntitlementTopic  the new value of the property, not null
   */
  public void setBbgFirstEntitlementTopic(String bbgFirstEntitlementTopic) {
    JodaBeanUtils.notNull(bbgFirstEntitlementTopic, "bbgFirstEntitlementTopic");
    this._bbgFirstEntitlementTopic = bbgFirstEntitlementTopic;
  }

  /**
   * Gets the the {@code bbgFirstEntitlementTopic} property.
   * @return the property, not null
   */
  public final Property<String> bbgFirstEntitlementTopic() {
    return metaBean().bbgFirstEntitlementTopic().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the combined heartbeat topic.
   * @return the value of the property, not null
   */
  public String getBbgFirstHeartbeatTopic() {
    return _bbgFirstHeartbeatTopic;
  }

  /**
   * Sets the combined heartbeat topic.
   * @param bbgFirstHeartbeatTopic  the new value of the property, not null
   */
  public void setBbgFirstHeartbeatTopic(String bbgFirstHeartbeatTopic) {
    JodaBeanUtils.notNull(bbgFirstHeartbeatTopic, "bbgFirstHeartbeatTopic");
    this._bbgFirstHeartbeatTopic = bbgFirstHeartbeatTopic;
  }

  /**
   * Gets the the {@code bbgFirstHeartbeatTopic} property.
   * @return the property, not null
   */
  public final Property<String> bbgFirstHeartbeatTopic() {
    return metaBean().bbgFirstHeartbeatTopic().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code IntegrationMarketDataComponentFactory}.
   */
  public static class Meta extends AbstractComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code classifier} property.
     */
    private final MetaProperty<String> _classifier = DirectMetaProperty.ofReadWrite(
        this, "classifier", IntegrationMarketDataComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code securitySource} property.
     */
    private final MetaProperty<SecuritySource> _securitySource = DirectMetaProperty.ofReadWrite(
        this, "securitySource", IntegrationMarketDataComponentFactory.class, SecuritySource.class);
    /**
     * The meta-property for the {@code jmsConnector} property.
     */
    private final MetaProperty<JmsConnector> _jmsConnector = DirectMetaProperty.ofReadWrite(
        this, "jmsConnector", IntegrationMarketDataComponentFactory.class, JmsConnector.class);
    /**
     * The meta-property for the {@code bbgSubscriptionTopic} property.
     */
    private final MetaProperty<String> _bbgSubscriptionTopic = DirectMetaProperty.ofReadWrite(
        this, "bbgSubscriptionTopic", IntegrationMarketDataComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code bbgEntitlementTopic} property.
     */
    private final MetaProperty<String> _bbgEntitlementTopic = DirectMetaProperty.ofReadWrite(
        this, "bbgEntitlementTopic", IntegrationMarketDataComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code bbgHeartbeatTopic} property.
     */
    private final MetaProperty<String> _bbgHeartbeatTopic = DirectMetaProperty.ofReadWrite(
        this, "bbgHeartbeatTopic", IntegrationMarketDataComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code activSubscriptionTopic} property.
     */
    private final MetaProperty<String> _activSubscriptionTopic = DirectMetaProperty.ofReadWrite(
        this, "activSubscriptionTopic", IntegrationMarketDataComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code activEntitlementTopic} property.
     */
    private final MetaProperty<String> _activEntitlementTopic = DirectMetaProperty.ofReadWrite(
        this, "activEntitlementTopic", IntegrationMarketDataComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code activHeartbeatTopic} property.
     */
    private final MetaProperty<String> _activHeartbeatTopic = DirectMetaProperty.ofReadWrite(
        this, "activHeartbeatTopic", IntegrationMarketDataComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code activFirstSubscriptionTopic} property.
     */
    private final MetaProperty<String> _activFirstSubscriptionTopic = DirectMetaProperty.ofReadWrite(
        this, "activFirstSubscriptionTopic", IntegrationMarketDataComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code activFirstEntitlementTopic} property.
     */
    private final MetaProperty<String> _activFirstEntitlementTopic = DirectMetaProperty.ofReadWrite(
        this, "activFirstEntitlementTopic", IntegrationMarketDataComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code activFirstHeartbeatTopic} property.
     */
    private final MetaProperty<String> _activFirstHeartbeatTopic = DirectMetaProperty.ofReadWrite(
        this, "activFirstHeartbeatTopic", IntegrationMarketDataComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code bbgFirstSubscriptionTopic} property.
     */
    private final MetaProperty<String> _bbgFirstSubscriptionTopic = DirectMetaProperty.ofReadWrite(
        this, "bbgFirstSubscriptionTopic", IntegrationMarketDataComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code bbgFirstEntitlementTopic} property.
     */
    private final MetaProperty<String> _bbgFirstEntitlementTopic = DirectMetaProperty.ofReadWrite(
        this, "bbgFirstEntitlementTopic", IntegrationMarketDataComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code bbgFirstHeartbeatTopic} property.
     */
    private final MetaProperty<String> _bbgFirstHeartbeatTopic = DirectMetaProperty.ofReadWrite(
        this, "bbgFirstHeartbeatTopic", IntegrationMarketDataComponentFactory.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "classifier",
        "securitySource",
        "jmsConnector",
        "bbgSubscriptionTopic",
        "bbgEntitlementTopic",
        "bbgHeartbeatTopic",
        "activSubscriptionTopic",
        "activEntitlementTopic",
        "activHeartbeatTopic",
        "activFirstSubscriptionTopic",
        "activFirstEntitlementTopic",
        "activFirstHeartbeatTopic",
        "bbgFirstSubscriptionTopic",
        "bbgFirstEntitlementTopic",
        "bbgFirstHeartbeatTopic");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          return _classifier;
        case -702456965:  // securitySource
          return _securitySource;
        case -1495762275:  // jmsConnector
          return _jmsConnector;
        case -1347590997:  // bbgSubscriptionTopic
          return _bbgSubscriptionTopic;
        case 1424045641:  // bbgEntitlementTopic
          return _bbgEntitlementTopic;
        case 2142380442:  // bbgHeartbeatTopic
          return _bbgHeartbeatTopic;
        case -183104877:  // activSubscriptionTopic
          return _activSubscriptionTopic;
        case 1045967713:  // activEntitlementTopic
          return _activEntitlementTopic;
        case -1938455374:  // activHeartbeatTopic
          return _activHeartbeatTopic;
        case -1527308959:  // activFirstSubscriptionTopic
          return _activFirstSubscriptionTopic;
        case 1002606291:  // activFirstEntitlementTopic
          return _activFirstEntitlementTopic;
        case 54793380:  // activFirstHeartbeatTopic
          return _activFirstHeartbeatTopic;
        case -1378069943:  // bbgFirstSubscriptionTopic
          return _bbgFirstSubscriptionTopic;
        case -932242197:  // bbgFirstEntitlementTopic
          return _bbgFirstEntitlementTopic;
        case 1970096316:  // bbgFirstHeartbeatTopic
          return _bbgFirstHeartbeatTopic;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends IntegrationMarketDataComponentFactory> builder() {
      return new DirectBeanBuilder<IntegrationMarketDataComponentFactory>(new IntegrationMarketDataComponentFactory());
    }

    @Override
    public Class<? extends IntegrationMarketDataComponentFactory> beanType() {
      return IntegrationMarketDataComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code classifier} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> classifier() {
      return _classifier;
    }

    /**
     * The meta-property for the {@code securitySource} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<SecuritySource> securitySource() {
      return _securitySource;
    }

    /**
     * The meta-property for the {@code jmsConnector} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<JmsConnector> jmsConnector() {
      return _jmsConnector;
    }

    /**
     * The meta-property for the {@code bbgSubscriptionTopic} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> bbgSubscriptionTopic() {
      return _bbgSubscriptionTopic;
    }

    /**
     * The meta-property for the {@code bbgEntitlementTopic} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> bbgEntitlementTopic() {
      return _bbgEntitlementTopic;
    }

    /**
     * The meta-property for the {@code bbgHeartbeatTopic} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> bbgHeartbeatTopic() {
      return _bbgHeartbeatTopic;
    }

    /**
     * The meta-property for the {@code activSubscriptionTopic} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> activSubscriptionTopic() {
      return _activSubscriptionTopic;
    }

    /**
     * The meta-property for the {@code activEntitlementTopic} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> activEntitlementTopic() {
      return _activEntitlementTopic;
    }

    /**
     * The meta-property for the {@code activHeartbeatTopic} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> activHeartbeatTopic() {
      return _activHeartbeatTopic;
    }

    /**
     * The meta-property for the {@code activFirstSubscriptionTopic} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> activFirstSubscriptionTopic() {
      return _activFirstSubscriptionTopic;
    }

    /**
     * The meta-property for the {@code activFirstEntitlementTopic} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> activFirstEntitlementTopic() {
      return _activFirstEntitlementTopic;
    }

    /**
     * The meta-property for the {@code activFirstHeartbeatTopic} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> activFirstHeartbeatTopic() {
      return _activFirstHeartbeatTopic;
    }

    /**
     * The meta-property for the {@code bbgFirstSubscriptionTopic} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> bbgFirstSubscriptionTopic() {
      return _bbgFirstSubscriptionTopic;
    }

    /**
     * The meta-property for the {@code bbgFirstEntitlementTopic} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> bbgFirstEntitlementTopic() {
      return _bbgFirstEntitlementTopic;
    }

    /**
     * The meta-property for the {@code bbgFirstHeartbeatTopic} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> bbgFirstHeartbeatTopic() {
      return _bbgFirstHeartbeatTopic;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
