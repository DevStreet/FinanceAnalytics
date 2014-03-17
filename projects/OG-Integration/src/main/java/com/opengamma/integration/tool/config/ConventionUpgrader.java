/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.tool.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.LocalDate;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.core.config.ConfigSource;
import com.opengamma.core.config.impl.ConfigItem;
import com.opengamma.core.convention.Convention;
import com.opengamma.core.convention.ConventionSource;
import com.opengamma.core.convention.ConventionType;
import com.opengamma.core.convention.impl.MockConvention;
import com.opengamma.core.id.ExternalSchemes;
import com.opengamma.core.security.Security;
import com.opengamma.core.security.SecuritySource;
import com.opengamma.financial.analytics.conversion.CurveNodeConverter;
import com.opengamma.financial.analytics.curve.ConventionUtils;
import com.opengamma.financial.analytics.curve.CurveNodeIdMapper;
import com.opengamma.financial.analytics.curve.CurveNodeWithIdentifierBuilder;
import com.opengamma.financial.analytics.curve.InterpolatedCurveDefinition;
import com.opengamma.financial.analytics.ircurve.strips.CashNode;
import com.opengamma.financial.analytics.ircurve.strips.CurveNode;
import com.opengamma.financial.analytics.ircurve.strips.CurveNodeWithIdentifier;
import com.opengamma.financial.analytics.ircurve.strips.FRANode;
import com.opengamma.financial.analytics.ircurve.strips.RollDateFRANode;
import com.opengamma.financial.analytics.ircurve.strips.RollDateSwapNode;
import com.opengamma.financial.convention.CMSLegConvention;
import com.opengamma.financial.convention.CompoundingIborLegConvention;
import com.opengamma.financial.convention.DeliverablePriceQuotedSwapFutureConvention;
import com.opengamma.financial.convention.DepositConvention;
import com.opengamma.financial.convention.EquityConvention;
import com.opengamma.financial.convention.ExchangeTradedFutureAndOptionConvention;
import com.opengamma.financial.convention.FXForwardAndSwapConvention;
import com.opengamma.financial.convention.FXSpotConvention;
import com.opengamma.financial.convention.FederalFundsFutureConvention;
import com.opengamma.financial.convention.FixedInterestRateSwapLegConvention;
import com.opengamma.financial.convention.FixedLegRollDateConvention;
import com.opengamma.financial.convention.FloatingInterestRateSwapLegConvention;
import com.opengamma.financial.convention.IborIndexConvention;
import com.opengamma.financial.convention.InflationLegConvention;
import com.opengamma.financial.convention.InterestRateFutureConvention;
import com.opengamma.financial.convention.OISLegConvention;
import com.opengamma.financial.convention.ONArithmeticAverageLegConvention;
import com.opengamma.financial.convention.ONCompoundedLegRollDateConvention;
import com.opengamma.financial.convention.OvernightIndexConvention;
import com.opengamma.financial.convention.PriceIndexConvention;
import com.opengamma.financial.convention.RollDateFRAConvention;
import com.opengamma.financial.convention.RollDateSwapConvention;
import com.opengamma.financial.convention.SwapConvention;
import com.opengamma.financial.convention.SwapFixedLegConvention;
import com.opengamma.financial.convention.SwapIndexConvention;
import com.opengamma.financial.convention.VanillaIborLegConvention;
import com.opengamma.financial.convention.VanillaIborLegRollDateConvention;
import com.opengamma.financial.security.index.IborIndex;
import com.opengamma.financial.security.index.Index;
import com.opengamma.financial.security.index.OvernightIndex;
import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.id.UniqueId;
import com.opengamma.master.config.ConfigMaster;
import com.opengamma.master.config.ConfigMasterUtils;
import com.opengamma.master.config.ConfigSearchRequest;
import com.opengamma.master.config.ConfigSearchResult;
import com.opengamma.master.convention.ConventionDocument;
import com.opengamma.master.convention.ConventionMaster;
import com.opengamma.master.convention.ConventionSearchRequest;
import com.opengamma.master.convention.ConventionSearchResult;
import com.opengamma.master.convention.ManageableConvention;
import com.opengamma.master.security.SecurityLoader;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.master.security.SecuritySearchRequest;
import com.opengamma.master.security.SecuritySearchResult;
import com.opengamma.util.time.Tenor;
import com.opengamma.util.tuple.ObjectsPair;

/**
 * Class to upgrade conventions to point at indices rather than other conventions.
 */
public class ConventionUpgrader {
  private static final Logger s_logger = LoggerFactory.getLogger(ConventionUpgrader.class);
  private SecurityMaster _securityMaster;
  private SecuritySource _securitySource;
  private ConfigMaster _configMaster;
  private ConfigSource _configSource;
  private boolean _write;
  private ConventionSource _conventionSource;
  private SecurityLoader _securityLoader;
  private ConventionMaster _conventionMaster;
  private ConfigValidationUtils _utils;
  private Map<ExternalId, Map<Tenor, Index>> _indexMap;
  private List<Index> _missingConventions;

  public ConventionUpgrader(SecurityMaster securityMaster, SecuritySource securitySource, 
                            ConfigMaster configMaster, ConfigSource configSource, 
                            ConventionMaster conventionMaster, ConventionSource conventionSource, 
                            SecurityLoader securityLoader, boolean write) {
    _securityMaster = securityMaster;
    _securitySource = securitySource;
    _configMaster = configMaster;
    _configSource = configSource;
    _conventionSource = conventionSource;
    _conventionMaster = conventionMaster;
    _securityLoader = securityLoader;
    _utils = new ConfigValidationUtils(securitySource, conventionSource, null, null);
    _write = write;
    _indexMap = new HashMap<>(); // (Bloomberg generated conventionId -> tenor -> Index)
    _missingConventions = new ArrayList<Index>();
  }
  
  
  public void upgrade() {
    System.out.println("Loading indices...");
    loadIndices();
    System.out.println("Finished loading indices.");
    System.out.println("Beginning interactive update...");
    interactiveUpdate();
    System.out.println("Interactive update complete.");
    System.out.println("Beginning remaining convention upgrade...");
    upgradeConventions();
    System.out.println("Finished remaining convention upgrade.");
    System.out.println("Beginning curve node upgrade...");
    upgradeCurveNodes();
    System.out.println("Finished curve node upgrade.");
  }
  
  private void loadIndices() {
    SecuritySearchRequest searchReq = new SecuritySearchRequest();
    searchReq.setSecurityType(IborIndex.INDEX_TYPE);
    SecuritySearchResult searchResult = _securityMaster.search(searchReq);
    if (searchResult.getSecurities().size() == 0) {
      System.err.println("No indices found.  You need to load all relevant indices via the securities section");
      System.exit(1);
    }
    // this list keeps track of when we find that multiple index/tenor pairs point to the same convention - we can not in that
    // case reliably back-infer a relationship.
    List<IborIndex> nonUniques = new ArrayList<>();
    for (Security security : searchResult.getSecurities()) {
      IborIndex iborIndex = (IborIndex) security;
      if (!_indexMap.containsKey(iborIndex.getConventionId())) {
        _indexMap.put(iborIndex.getConventionId(), new HashMap<Tenor, Index>());
      }
      if (_indexMap.get(iborIndex.getConventionId()).containsKey(iborIndex.getTenor())) {
        nonUniques.add(iborIndex);
      } else {
        _indexMap.get(iborIndex.getConventionId()).put(iborIndex.getTenor(), iborIndex);
      }
      // secondary task while we're here, check if convention exists
      if (!_utils.conventionExists(iborIndex.getConventionId())) {
        _missingConventions.add(iborIndex);
      }
    }
    // Remove anything we found non-unique entries for.
    for (IborIndex nonUnique : nonUniques) {
      _indexMap.get(nonUnique.getConventionId()).remove(nonUnique.getTenor());
    }
    
    // repeat for ON indices
    searchReq.setSecurityType(OvernightIndex.INDEX_TYPE);
    searchResult = _securityMaster.search(searchReq);
    List<OvernightIndex> nonUniquesON = new ArrayList<>();
    for (Security security : searchResult.getSecurities()) {
      OvernightIndex overnightIndex = (OvernightIndex) security;
      if (!_indexMap.containsKey(overnightIndex.getConventionId())) {
        _indexMap.put(overnightIndex.getConventionId(), new HashMap<Tenor, Index>());
      }
      if (_indexMap.get(overnightIndex.getConventionId()).containsKey(Tenor.ON)) {
        nonUniquesON.add(overnightIndex);
      } else {
        _indexMap.get(overnightIndex.getConventionId()).put(Tenor.ON, overnightIndex);
      }
      // secondary task while we're here, check if convention exists
      if (!_utils.conventionExists(overnightIndex.getConventionId())) {
        _missingConventions.add(overnightIndex);
      }
    }
    // Remove anything we found non-unique entries for.
    for (OvernightIndex nonUnique : nonUniquesON) {
      _indexMap.get(nonUnique.getConventionId()).remove(Tenor.ON);
    }
  }
  
  private List<IborIndexConvention> getAllIborIndexConventions() {
    List<IborIndexConvention> results = new ArrayList<>();
    ConventionSearchRequest searchReq = new ConventionSearchRequest();
    searchReq.setConventionType(IborIndexConvention.TYPE);
    ConventionSearchResult searchResult = _conventionMaster.search(searchReq);
    for (ManageableConvention convention : searchResult.getConventions()) {
      results.add((IborIndexConvention) convention);
    }
    return results;
  }
  
  private List<OvernightIndexConvention> getAllOvernightIndexConventions() {
    List<OvernightIndexConvention> results = new ArrayList<>();
    ConventionSearchRequest searchReq = new ConventionSearchRequest();
    searchReq.setConventionType(OvernightIndexConvention.TYPE);
    ConventionSearchResult searchResult = _conventionMaster.search(searchReq);
    for (ManageableConvention convention : searchResult.getConventions()) {
      results.add((OvernightIndexConvention) convention);
    }
    return results;
  }
  
  private void interactiveUpdate() {
    // these variables hold missing ids for conventions, plus an example source index.
    Map<ExternalId, IborIndex> missingIborIndexConventionIds = new HashMap<>();
    Map<ExternalId, OvernightIndex> missingOvernightIndexConventionIds = new HashMap<>();
    for (Index index : _missingConventions) {
      if (index instanceof IborIndex) {
        IborIndex iborIndex = (IborIndex) index;
        missingIborIndexConventionIds.put(iborIndex.getConventionId(), iborIndex); // yes this will overwrite it, we just want an example to show the user       
      } else if (index instanceof OvernightIndex) {
        OvernightIndex overnightIndex = (OvernightIndex) index;
        missingOvernightIndexConventionIds.put(overnightIndex.getConventionId(), overnightIndex); // yes this will overwrite it, we just want an example to show the user
      } else {
        throw new OpenGammaRuntimeException("unexpected index of type " + index.getClass());
      }
    }
    try (Scanner scanner = new Scanner(System.in)) {
      List<IborIndexConvention> allIborIndexConventions = getAllIborIndexConventions();
      List<IborIndexConvention> iborIndexConventionAddOrUpdates = interactiveUpdateTIndexConventions(scanner, allIborIndexConventions, missingIborIndexConventionIds);
      List<OvernightIndexConvention> allOvernightIndexConventions = getAllOvernightIndexConventions();
      List<OvernightIndexConvention> overnightIndexConventionAddOrUpdates = interactiveUpdateTIndexConventions(scanner, allOvernightIndexConventions, missingOvernightIndexConventionIds);
      if (iborIndexConventionAddOrUpdates.size() > 0 || overnightIndexConventionAddOrUpdates.size() > 0) {
        System.err.println("Would you like to commit these changes?");
        String yn = scanner.next(Pattern.compile("Y|y|N|n"));
        if (yn.toLowerCase().equals("y")) {
          saveOrUpdateConventions(iborIndexConventionAddOrUpdates);
          saveOrUpdateConventions(overnightIndexConventionAddOrUpdates);
          System.out.println("Commit complete.");
        }
      } else {
        System.out.println("No conventions to upgrade here.");
      }
    }
  }
  
  private <T extends ManageableConvention> void saveOrUpdateConventions(List<T> conventions) {
    for (ManageableConvention convention : conventions) {
      ConventionDocument document = new ConventionDocument(convention);
      if (convention.getUniqueId() == null) {
        System.out.println((_write ? "" : "[write flag not specified] ") + "Adding new " + convention.getConventionType() + " convention " + convention.getName());
        if (_write) {
          _conventionMaster.add(document);
        }
      } else {
        System.out.println((_write ? "" : "[write flag not specified] ") + "Updating existing " + convention.getConventionType() + " convention " + convention.getName());
        if (_write) {
          _conventionMaster.update(document);
        }
      }
    }
  }

  @SuppressWarnings("unchecked")
  private <T extends ManageableConvention, I extends Index> List<T> interactiveUpdateTIndexConventions(Scanner scanner, List<T> allTIndexConventions, Map<ExternalId, I> missingTIndexConventionIds) {
    if (missingTIndexConventionIds.size() == 0) {
      System.out.println("No missing conventions here");
      return Collections.EMPTY_LIST;
    }
    List<T> conventionsToAddOrUpdate = new ArrayList<T>();
    printIndexList(allTIndexConventions);
    System.out.println("Please map each of the following to one of the above available conventions (type the number and press enter)");
    for (Map.Entry<ExternalId, I> entry : missingTIndexConventionIds.entrySet()) {
      ExternalId externalId = entry.getKey();
      I index = entry.getValue();
      System.out.println(externalId.toString() + " an example index with this is " + index.toString());
      System.out.println(" s) to skip, l) to list again");
      T selection = getSelection(scanner, allTIndexConventions);
      if (selection != null) {
        ExternalId existingConventionNameId = selection.getExternalIdBundle().getExternalId(ExternalSchemes.BLOOMBERG_CONVENTION_NAME);
        if (existingConventionNameId == null) {
          selection.setExternalIdBundle(selection.getExternalIdBundle().withExternalId(externalId));
          conventionsToAddOrUpdate.add(selection);
          System.out.println("Updated convention added to pending changes list");
        } else if (existingConventionNameId != externalId) {
          System.err.println("You've chosen a convention that already has a BLOOMBERG_CONVENTION_NAME id of " + existingConventionNameId);
          System.err.println("Would you like to create a copy of it with a new name (Y/N) (N will skip this entry)?");
          String yn = scanner.next(Pattern.compile("Y|y|N|n"));
          if (yn.toLowerCase().equals("y")) {
            String validConventionName = getValidConventionName(scanner, IborIndexConvention.TYPE);
            ManageableConvention clone = selection.clone();
            clone.setName(validConventionName);
            clone.setUniqueId(null);
            ExternalId cloneConventionId = ExternalId.of("CONVENTION", validConventionName);
            ExternalIdBundle cloneBundle = ExternalIdBundle.of(cloneConventionId, externalId);
            clone.setExternalIdBundle(cloneBundle);
            conventionsToAddOrUpdate.add((T) clone);
          }
        } else {
          throw new OpenGammaRuntimeException("This should never happen, the convention should not be on the missing list");
        }
      } // else user chose to skip this convention.
    }
    return conventionsToAddOrUpdate;
  }
  
  private String getValidConventionName(Scanner scanner, ConventionType conventionType) {
    System.out.println("Enter new convention name (string will be used for both name and CONVENTION scheme id):");
    String conventionName = null;
    do {
      conventionName = scanner.nextLine();
      if (conventionExists(conventionType, conventionName)) {
        System.err.println("A convention with this name/id already exists, please try another name");
      }
    } while (conventionExists(conventionType, conventionName));
    return conventionName;
  }
  
  private boolean conventionExists(ConventionType type, String name) {
    ConventionSearchRequest searchReq = new ConventionSearchRequest();
    searchReq.setName(name);
    searchReq.setConventionType(type);
    ConventionSearchResult searchResult = _conventionMaster.search(searchReq);
    if (searchResult.getConventions().size() == 0) {
      try { // check there isn't a convention id with this name
        _conventionSource.getSingle(ExternalId.of("CONVENTION", name));
      } catch (Exception e) {
        return false;
      }
    }
    return true;
  }
  
  /**
   * Prompts the user to choose a convention from a list (passed in), allowing the user to 
   * skip (press 's<ENTER>') or redisplay the list (press 'l<ENTER>') or type the number of the
   * item they want to select
   * @param scanner in input stream scanner
   * @param allTIndexConventions a list of all the conventions of type <T>
   * @return the chosen convention of type T
   */
  private <T extends Convention> T getSelection(Scanner scanner, List<T> allTIndexConventions) {
    Integer selection = null;
    do {
      String input = scanner.nextLine();
      if (input.toLowerCase().equals("s")) {
        // skip
        return null;
      } else if (input.toLowerCase().equals("l")) {
        printIndexList(allTIndexConventions);
      } else {
        try {
          selection = Integer.parseInt(input);
        } catch (NumberFormatException npe) {
          System.err.println("Number in invalid format, try again");
        }
      }
      if (selection != null && selection >= allTIndexConventions.size()) {
        System.err.println("Number must be in range from 0-" + allTIndexConventions.size());
      }
    } while (selection == null || selection >= allTIndexConventions.size());    
    return allTIndexConventions.get(selection);
  }
  
  /**
   * Prints a list of all the index conventions passed, with asterisks indicating they are already linked to indices.
   * @param allIndexConventions the list of all current indices of type <T>
   */
  private <T extends Convention> void printIndexList(List<T> allIndexConventions) {
    int i = 0;
    for (T convention : allIndexConventions) {
      String completeIndicator = convention.getExternalIdBundle().getValue(ExternalSchemes.BLOOMBERG_CONVENTION_NAME) != null ? "*" : "";
      System.out.println("  " + i + ") " + convention.getName() + completeIndicator);
      i++;
    }
    System.out.println("  * indicates already has a BLOOMBERG_CONVENTION_NAME id, so choosing them below will prompt to name a clone as each convention can only link to one");
  }
  
  private void upgradeConventions() {
    try {
      ConventionSearchRequest searchReq = new ConventionSearchRequest();
      ConventionSearchResult result = _conventionMaster.search(searchReq);
      for (ConventionDocument document : result.getDocuments()) {
        ManageableConvention convention = document.getConvention();
        ConventionUpgradeFollower conventionUpgradeFollower = new ConventionUpgradeFollower();
        ObjectsPair<ExternalId, Tenor> pair = conventionUpgradeFollower.followConvention(convention, null);
        if (pair != null) {
          ExternalId originalConventionId = pair.getFirst();
          Tenor legacyTenor = pair.getSecond();
          Map<Tenor, Index> map = _indexMap.get(originalConventionId);
          if (map != null) {
            Index index = map.get(legacyTenor);
            ExternalId externalId = index.getExternalIdBundle().getExternalId(ExternalSchemes.BLOOMBERG_TICKER);
            if (legacyTenor.equals(Tenor.ON)) {
              if (convention.propertyNames().contains("overnightIndexConvention")) {
                convention.property("overnightIndexConvention").set(externalId);
              } else if (convention.propertyNames().contains("indexConvention")) {
                convention.property("indexConvention").set(externalId);
              } else {
                System.err.println("No property found to set on convention " + convention + " skipping");
                continue;
              }
            } else {
              if (convention.propertyNames().contains("iborIndexConvention")) {
                convention.property("iborIndexConvention").set(externalId);
              } else if (convention.propertyNames().contains("indexConvention")) {
                convention.property("indexConvention").set(externalId);
              } else {
                System.err.println("No property found to set on convention " + convention + " skipping");
                continue;
              }
            }
            System.out.println((_write ? "" : "[write flag not specified] ") + "Updating convention " + convention.getName());
            if (_write) {
              _conventionMaster.update(document);
            }
          } else if (originalConventionId.getScheme().equals("CONVENTION")) {
            System.err.println("Couldn't find index present for " + originalConventionId + " with tenor " + legacyTenor);
            System.err.println("Map keys were " + _indexMap.keySet());
            System.err.println("This will require loading manually via the security loader");
          }
        }
      }
    } catch (IllegalArgumentException | SecurityException ex) {
      System.err.println("Problem upgrading conventions");
      ex.printStackTrace();
      System.exit(1);
    }
  }
  
  /**
   * Loads either an IborIndexConvention or OvernightIndexConvention from the convention source.
   * Importantly it returns null rather than throws an exception if the convention is not found.
   * @param indexId
   * @return the convention or null if not found or exception occurred.
   */
  private Convention resolveIborOrOvernightIndexConvention(ExternalId indexId) {
    Security sec = _securitySource.getSingle(indexId.toBundle());
    if (sec instanceof IborIndex) {
      IborIndex iborIndex = (IborIndex) sec;
      try {
        IborIndexConvention single = _conventionSource.getSingle(iborIndex.getConventionId(), IborIndexConvention.class);
        return single;
      } catch (Exception e) {
        return null;
      }
    } else if (sec instanceof OvernightIndex) {
      OvernightIndex overnightIndex = (OvernightIndex) sec;
      try {
        OvernightIndexConvention single2 = _conventionSource.getSingle(overnightIndex.getConventionId(), OvernightIndexConvention.class);
        return single2;
      } catch (Exception e) {
        return null;
      }
      
    }
    return null;
  }

  /**
   * Loads a DepositConvention from the convention source.
   * Importantly it returns null rather than throws an exception if the convention is not found.
   * @param indexId
   * @return the convention or null if not found or exception occurred.
   */
  private <T extends ManageableConvention> T resolveConvention(ExternalId conventionId, Class<T> clazz) {
    try {
      T single = _conventionSource.getSingle(conventionId, clazz);
      return single;
    } catch (Exception e) {
      return null;
    }
  }
  
  /**
   * Tries to load an index if it's not present, takes a set of ids that it updates with each try
   * to avoid excessive Bloomberg loader activity.
   * @param identifier the ExternalId of the index to load
   * @param attemptedLoad the current set of attempts to load, which is checked and updated
   */
  private void ensurePotentialIndexLoaded(ExternalId identifier, Set<ExternalId> attemptedLoad) {
    if (!attemptedLoad.contains(identifier)) {
      attemptedLoad.add(identifier);
      Security sec = _securitySource.getSingle(identifier.toBundle());
      if (sec == null) {
        s_logger.info("Trying to load potential index using identifier {}", identifier);
        try {
          UniqueId secId = _securityLoader.loadSecurity(identifier.toBundle());
          if (secId != null) {
            s_logger.info("appears load was succesful");
          }
        } catch (OpenGammaRuntimeException ogre) {
          s_logger.error("Couldn't load potential index {}", identifier);
        }

      } else {
        if (sec instanceof Index) {
          s_logger.info("Potential index {} is already in security master and is index", identifier);
        } else {
          s_logger.info("Potential index {} is already in security master but is not an index", identifier);
        }
      }
    }
  }
  
  /**
   * Iterate over interpolated curve definitions, changing the cash curve nodes to point at indices where appropriate
   * and attempting to load missing indices.
   */
  public void upgradeCurveNodes() {
    Set<ExternalId> attemptedLoad = new HashSet<>();
    ConfigSearchRequest<InterpolatedCurveDefinition> request = new ConfigSearchRequest<InterpolatedCurveDefinition>(InterpolatedCurveDefinition.class);
    ConfigSearchResult<InterpolatedCurveDefinition> searchResult = _configMaster.search(request);
    for (ConfigItem<InterpolatedCurveDefinition> curveDefItem : searchResult.getValues()) {
      boolean changed = false;
      InterpolatedCurveDefinition curveDefinition = curveDefItem.getValue();
      for (CurveNode node : curveDefinition.getNodes()) {
        CurveNodeIdMapper curveNodeIdMapper = _configSource.getLatestByName(CurveNodeIdMapper.class, node.getCurveNodeIdMapperName());
        CurveNodeWithIdentifierBuilder idBuilder = new CurveNodeWithIdentifierBuilder(LocalDate.now(), curveNodeIdMapper);
        CurveNodeWithIdentifier nodeWithIdentifier = node.accept(idBuilder);
        ExternalId id = nodeWithIdentifier.getIdentifier();
        if (node instanceof CashNode) {
          CashNode cashNode = (CashNode) node;
          DepositConvention depositConvention = resolveConvention(cashNode.getConvention(), DepositConvention.class); // try to resolve it as a deposit convention.
          if (depositConvention == null) {
            Convention indexConvention = resolveIborOrOvernightIndexConvention(cashNode.getConvention());           
            if (indexConvention == null) {
              ensurePotentialIndexLoaded(nodeWithIdentifier.getIdentifier(), attemptedLoad);
              indexConvention = resolveIborOrOvernightIndexConvention(id);
              if (indexConvention != null) {
                ((CashNode) node).setConvention(id);
                changed = true;
              }
            }
          }
//      } else if (node instanceof RollDateFRANode) {
//        RollDateFRANode rollDateFRANode = (RollDateFRANode) node;
//        RollDateFRAConvention rollDateFRAConvention = resolveConvention(rollDateFRANode.getRollDateFRAConvention(), RollDateFRAConvention.class);
//        // do nothing, these only point at the convention.
//      } else if (node instanceof RollDateSwapNode) {
        } else if (node instanceof FRANode) {
          FRANode fraNode = (FRANode) node;
          DepositConvention depositConvention = resolveConvention(fraNode.getConvention(), DepositConvention.class); // try to resolve it as a deposit convention.
          if (depositConvention == null) {
            Convention indexConvention = resolveIborOrOvernightIndexConvention(fraNode.getConvention());           
            if (indexConvention == null) {
              ensurePotentialIndexLoaded(nodeWithIdentifier.getIdentifier(), attemptedLoad);
              indexConvention = resolveIborOrOvernightIndexConvention(nodeWithIdentifier.getIdentifier());
              if (indexConvention != null) {
                ((CashNode) node).setConvention(nodeWithIdentifier.getIdentifier());
                changed = true;
              }
            }
          }          
        }
      }
      if (changed && _write) {
        System.out.println("Updating curve definition " + curveDefinition.getName());
        ConfigMasterUtils.storeByName(_configMaster, curveDefItem);
      } else if (changed) {
        System.out.println("[write flag 4not specified] Updating curve definition " + curveDefinition.getName());
      }
    }
  }
}
