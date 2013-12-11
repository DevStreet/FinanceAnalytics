/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.regression;

import static com.google.common.collect.Iterators.filter;
import static com.google.common.collect.Iterators.transform;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import net.sf.saxon.event.StreamWriterToReceiver;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.Serializer.Property;

import org.fudgemsg.FudgeContext;
import org.fudgemsg.MutableFudgeMsg;
import org.fudgemsg.mapping.FudgeSerializer;
import org.fudgemsg.wire.FudgeMsgWriter;
import org.fudgemsg.wire.xml.FudgeXMLStreamWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.core.config.impl.ConfigItem;
import com.opengamma.core.marketdatasnapshot.impl.ManageableMarketDataSnapshot;
import com.opengamma.id.ObjectId;
import com.opengamma.id.UniqueIdentifiable;
import com.opengamma.id.VersionCorrection;
import com.opengamma.integration.server.RemoteServer;
import com.opengamma.master.config.ConfigDocument;
import com.opengamma.master.config.ConfigMaster;
import com.opengamma.master.config.ConfigSearchRequest;
import com.opengamma.master.config.impl.ConfigSearchIterator;
import com.opengamma.master.exchange.ExchangeDocument;
import com.opengamma.master.exchange.ExchangeMaster;
import com.opengamma.master.exchange.ExchangeSearchRequest;
import com.opengamma.master.exchange.ManageableExchange;
import com.opengamma.master.exchange.impl.ExchangeSearchIterator;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoDocument;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchRequest;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesMaster;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeries;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeriesInfo;
import com.opengamma.master.historicaltimeseries.impl.HistoricalTimeSeriesInfoSearchIterator;
import com.opengamma.master.holiday.HolidayDocument;
import com.opengamma.master.holiday.HolidayMaster;
import com.opengamma.master.holiday.HolidaySearchRequest;
import com.opengamma.master.holiday.ManageableHoliday;
import com.opengamma.master.holiday.impl.HolidaySearchIterator;
import com.opengamma.master.marketdatasnapshot.MarketDataSnapshotDocument;
import com.opengamma.master.marketdatasnapshot.MarketDataSnapshotMaster;
import com.opengamma.master.marketdatasnapshot.MarketDataSnapshotSearchRequest;
import com.opengamma.master.marketdatasnapshot.impl.MarketDataSnapshotSearchIterator;
import com.opengamma.master.orgs.ManageableOrganization;
import com.opengamma.master.orgs.OrganizationDocument;
import com.opengamma.master.orgs.OrganizationMaster;
import com.opengamma.master.orgs.OrganizationSearchRequest;
import com.opengamma.master.orgs.impl.OrganizationSearchIterator;
import com.opengamma.master.portfolio.ManageablePortfolio;
import com.opengamma.master.portfolio.PortfolioDocument;
import com.opengamma.master.portfolio.PortfolioMaster;
import com.opengamma.master.portfolio.PortfolioSearchRequest;
import com.opengamma.master.portfolio.impl.PortfolioSearchIterator;
import com.opengamma.master.position.ManageablePosition;
import com.opengamma.master.position.PositionDocument;
import com.opengamma.master.position.PositionMaster;
import com.opengamma.master.position.PositionSearchRequest;
import com.opengamma.master.position.impl.PositionSearchIterator;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.master.security.SecurityDocument;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.master.security.SecuritySearchRequest;
import com.opengamma.master.security.impl.SecuritySearchIterator;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.fudgemsg.OpenGammaFudgeContext;

/**
 * Dumps all the data required to run views from the database into Fudge XML files.
 * TODO split this up to allow a subset of data to be dumped and restored?
 */
/* package */ class DatabaseDump {

  private static final Logger s_logger = LoggerFactory.getLogger(DatabaseDump.class);

  private final File _outputDir;
  private final SecurityMaster _securityMaster;
  private final PositionMaster _positionMaster;
  private final PortfolioMaster _portfolioMaster;
  private final ConfigMaster _configMaster;
  private final HistoricalTimeSeriesMaster _timeSeriesMaster;
  private final HolidayMaster _holidayMaster;
  private final ExchangeMaster _exchangeMaster;
  private final MarketDataSnapshotMaster _snapshotMaster;
  private final OrganizationMaster _organizationMaster;
  
  private final MasterFilterManager _masterFilterManager;
  
  private final IdMappings _idMappings;
  private final FudgeContext _ctx = new FudgeContext(OpenGammaFudgeContext.getInstance());
  private final FudgeSerializer _serializer = new FudgeSerializer(OpenGammaFudgeContext.getInstance());

  private int _nextId;


  DatabaseDump(String outputDir,
      SecurityMaster securityMaster,
      PositionMaster positionMaster,
      PortfolioMaster portfolioMaster,
      ConfigMaster configMaster,
      HistoricalTimeSeriesMaster timeSeriesMaster,
      HolidayMaster holidayMaster,
      ExchangeMaster exchangeMaster,
      MarketDataSnapshotMaster snapshotMaster,
      OrganizationMaster organizationMaster) {
    this(outputDir,
        securityMaster,
        positionMaster,
        portfolioMaster,
        configMaster,
        timeSeriesMaster,
        holidayMaster,
        exchangeMaster,
        snapshotMaster,
        organizationMaster,
        MasterFilterManager.alwaysTrue());
  }
  
  DatabaseDump(String outputDir,
      SecurityMaster securityMaster,
      PositionMaster positionMaster,
      PortfolioMaster portfolioMaster,
      ConfigMaster configMaster,
      HistoricalTimeSeriesMaster timeSeriesMaster,
      HolidayMaster holidayMaster,
      ExchangeMaster exchangeMaster,
      MarketDataSnapshotMaster snapshotMaster,
      OrganizationMaster organizationMaster,
      MasterFilterManager masterFilterManager) {
    ArgumentChecker.notNull(securityMaster, "securityMaster");
    ArgumentChecker.notNull(outputDir, "outputDir");
    ArgumentChecker.notNull(positionMaster, "positionMaster");
    ArgumentChecker.notNull(portfolioMaster, "portfolioMaster");
    ArgumentChecker.notNull(configMaster, "configMaster");
    ArgumentChecker.notNull(timeSeriesMaster, "timeSeriesMaster");
    ArgumentChecker.notNull(masterFilterManager, "masterFilterManager");
    _organizationMaster = organizationMaster;
    _snapshotMaster = snapshotMaster;
    _exchangeMaster = exchangeMaster;
    _holidayMaster = holidayMaster;
    _timeSeriesMaster = timeSeriesMaster;
    _positionMaster = positionMaster;
    _portfolioMaster = portfolioMaster;
    _configMaster = configMaster;
    _securityMaster = securityMaster;
    _masterFilterManager = masterFilterManager;

    _outputDir = new File(outputDir);

    ConfigItem<IdMappings> mappingsConfigItem = RegressionUtils.loadIdMappings(_configMaster);
    if (mappingsConfigItem != null) {
      _idMappings = mappingsConfigItem.getValue();
    } else {
      _idMappings = new IdMappings();
    }
    _nextId = _idMappings.getMaxId() + 1;
    if (!_outputDir.exists()) {
      boolean success = _outputDir.mkdirs();
      if (!success) {
        throw new OpenGammaRuntimeException("Output directory " + outputDir + " couldn't be created");
      }
      s_logger.info("Created output directory {}", _outputDir.getAbsolutePath());
    }
    s_logger.info("Dumping database to {}", _outputDir.getAbsolutePath());
  }

  public static void main(String[] args) throws IOException {
    if (args.length < 2) {
      System.err.println("arguments: dataDirectory serverUrl");
      System.exit(1);
    }
    String dataDir = args[0];
    String serverUrl = args[1];
    int exitCode = 0;
    try (RemoteServer server = RemoteServer.create(serverUrl)) {
      DatabaseDump databaseDump = new DatabaseDump(dataDir,
                                                   server.getSecurityMaster(),
                                                   server.getPositionMaster(),
                                                   server.getPortfolioMaster(),
                                                   server.getConfigMaster(),
                                                   server.getHistoricalTimeSeriesMaster(),
                                                   server.getHolidayMaster(),
                                                   server.getExchangeMaster(),
                                                   server.getMarketDataSnapshotMaster(),
                                                   server.getOrganizationMaster());
      databaseDump.dumpDatabase();
    } catch (Exception e) {
      s_logger.warn("Failed to write data", e);
      exitCode = 1;
    }
    System.exit(exitCode);
  }

  public void dumpDatabase() throws IOException {
    Map<ObjectId, Integer> ids = Maps.newHashMap(_idMappings.getIds());
    ids.putAll(writeSecurities());
    ids.putAll(writePositions());
    ids.putAll(writePortfolios());
    ids.putAll(writeConfig());
    ids.putAll(writeTimeSeries());
    ids.putAll(writeHolidays());
    ids.putAll(writeExchanges());
    ids.putAll(writeSnapshots());
    ids.putAll(writeOrganizations());
    int maxId = _idMappings.getMaxId();
    for (Integer id : ids.values()) {
      if (id > maxId) {
        maxId = id;
      }
    }
    IdMappings idMappings = new IdMappings(ids, maxId);
    writeToFudge(_outputDir, idMappings, RegressionUtils.ID_MAPPINGS_FILE);
  }

  private Map<ObjectId, Integer> writeSecurities() throws IOException {
    SecuritySearchIterator searchIterator = new SecuritySearchIterator(_securityMaster, new SecuritySearchRequest());
    Iterator<SecurityDocument> filteredDocuments = filter(searchIterator, _masterFilterManager.getSecurityFilter());
    return writeToDirectory(transform(filteredDocuments, new SecurityTransformer()), "securities", "sec");
  }

  private Map<ObjectId, Integer> writePositions() throws IOException {
    PositionSearchIterator searchIterator = new PositionSearchIterator(_positionMaster, new PositionSearchRequest());
    Iterator<PositionDocument> filteredDocuments = filter(searchIterator, _masterFilterManager.getPositionFilter());
    return writeToDirectory(transform(filteredDocuments, new PositionTransformer()), "positions", "pos");
  }

  private Map<ObjectId, Integer> writeConfig() throws IOException {
    ConfigSearchIterator<Object> searchIterator = new ConfigSearchIterator<>(_configMaster, new ConfigSearchRequest<>(Object.class));
    Iterator<ConfigDocument> filteredDocuments = filter(searchIterator, _masterFilterManager.getConfigFilter());
    return writeToDirectory(transform(filteredDocuments, new ConfigTransformer()), "configs", "cfg");
  }

  private Map<ObjectId, Integer> writePortfolios() throws IOException {
    PortfolioSearchIterator searchIterator = new PortfolioSearchIterator(_portfolioMaster, new PortfolioSearchRequest());
    Iterator<PortfolioDocument> filteredDocuments = filter(searchIterator, _masterFilterManager.getPortfolioFilter());
    return writeToDirectory(transform(filteredDocuments, new PortfolioTransformer()), "portfolios", "prt");
  }

  private Map<ObjectId, Integer> writeTimeSeries() throws IOException {
    HistoricalTimeSeriesInfoSearchIterator searchIterator = new HistoricalTimeSeriesInfoSearchIterator(_timeSeriesMaster, new HistoricalTimeSeriesInfoSearchRequest());
    Iterator<HistoricalTimeSeriesInfoDocument> filteredDocuments = filter(searchIterator, _masterFilterManager.getHtsFilter());
    return writeToDirectory(transform(filteredDocuments, new TimeSeriesTransformer()), "timeseries", "hts");
  }

  private Map<ObjectId, Integer> writeHolidays() throws IOException {
    HolidaySearchIterator searchIterator = new HolidaySearchIterator(_holidayMaster, new HolidaySearchRequest());
    Iterator<HolidayDocument> filteredDocuments = filter(searchIterator, _masterFilterManager.getHolidayFilter());
    return writeToDirectory(transform(filteredDocuments, new HolidayTransformer()), "holidays", "hol");
  }

  private Map<ObjectId, Integer> writeExchanges() throws IOException {
    ExchangeSearchIterator searchIterator = new ExchangeSearchIterator(_exchangeMaster, new ExchangeSearchRequest());
    Iterator<ExchangeDocument> filteredDocuments = filter(searchIterator, _masterFilterManager.getExchangeFilter());
    return writeToDirectory(transform(filteredDocuments, new ExchangeTransformer()), "exchanges", "exg");
  }

  private Map<ObjectId, Integer> writeSnapshots() throws IOException {
    MarketDataSnapshotSearchIterator searchIterator = new MarketDataSnapshotSearchIterator(_snapshotMaster, new MarketDataSnapshotSearchRequest());
    Iterator<MarketDataSnapshotDocument> filteredDocuments = filter(searchIterator, _masterFilterManager.getMarketDataSnapshotFilter());
    return writeToDirectory(transform(filteredDocuments, new SnapshotTransformer()), "snapshots", "snp");
  }

  private Map<ObjectId, Integer> writeOrganizations() throws IOException {
    OrganizationSearchIterator searchIterator = new OrganizationSearchIterator(_organizationMaster, new OrganizationSearchRequest());
    Iterator<OrganizationDocument> filteredDocuments = filter(searchIterator, _masterFilterManager.getOrganizationFilter());
    return writeToDirectory(transform(filteredDocuments, new OrganizationTransformer()), "organizations", "org");
  }

  private Map<ObjectId, Integer> writeToDirectory(Iterator<? extends UniqueIdentifiable> objects,
                                                  String outputSubDirName,
                                                  String prefix) throws IOException {
    File outputDir = new File(_outputDir, outputSubDirName);
    if (!outputDir.exists()) {
      boolean success = outputDir.mkdir();
      if (success) {
        s_logger.debug("Created directory {}", outputDir);
      } else {
        throw new OpenGammaRuntimeException("Failed to create directory " + outputDir);
      }
    }
    s_logger.info("Writing to {}", outputDir.getAbsolutePath());
    Map<ObjectId, Integer> ids = Maps.newHashMap();
    int count = 0;
    while (objects.hasNext()) {
      UniqueIdentifiable object = objects.next();
      ObjectId objectId = object.getUniqueId().getObjectId();
      Integer previousId = _idMappings.getId(objectId);
      int id;
      if (previousId == null) {
        id = _nextId++;
        ids.put(objectId, id);
      } else {
        id = previousId;
      }
      String fileName = prefix + id + ".xml";
      writeToFudge(outputDir, object, fileName);
      count++;
    }
    s_logger.info("Wrote {} objects to {}", count, outputDir.getAbsolutePath());
    return ids;
  }

  private void writeToFudge(File outputDir, Object object, String fileName) throws IOException {
    Processor p = new Processor(false);
    try (FileWriter writer = new FileWriter(new File(outputDir, fileName))) {
      Serializer serializer = p.newSerializer(writer);
      serializer.setOutputProperty(Property.INDENT, "yes");
      StreamWriterToReceiver xmlStreamWriter;
      try {
        xmlStreamWriter = serializer.getXMLStreamWriter();
      } catch (SaxonApiException ex) {
        throw Throwables.propagate(ex);
      }
      FudgeXMLStreamWriter streamWriter = new FudgeXMLStreamWriter(_ctx, xmlStreamWriter);
      FudgeMsgWriter fudgeMsgWriter = new FudgeMsgWriter(streamWriter);
      MutableFudgeMsg msg = _serializer.objectToFudgeMsg(object);
      FudgeSerializer.addClassHeader(msg, object.getClass());
      fudgeMsgWriter.writeMessage(msg);
      s_logger.debug("Wrote object {}", object);
      fudgeMsgWriter.flush();
    }
  }
  
  private class SecurityTransformer implements Function<SecurityDocument, ManageableSecurity> {
    @Override
    public ManageableSecurity apply(SecurityDocument input) {
      return input.getSecurity();
    }
  }
  
  private class PositionTransformer implements Function<PositionDocument, ManageablePosition> {
    @Override
    public ManageablePosition apply(PositionDocument input) {
      return input.getPosition();
    }
  }
  
  private class PortfolioTransformer implements Function<PortfolioDocument, ManageablePortfolio> {
    @Override
    public ManageablePortfolio apply(PortfolioDocument input) {
      return input.getPortfolio();
    }
  }
  
  private class ConfigTransformer implements Function<ConfigDocument, ConfigItem<?>> {

    @Override
    public ConfigItem<?> apply(ConfigDocument input) {
      return input.getConfig();
    }
  }
  
  private class TimeSeriesTransformer implements Function<HistoricalTimeSeriesInfoDocument, TimeSeriesWithInfo> {
    
    @Override
    public TimeSeriesWithInfo apply(HistoricalTimeSeriesInfoDocument infoDoc) {
      ManageableHistoricalTimeSeriesInfo info = infoDoc.getInfo();
      ManageableHistoricalTimeSeries timeSeries =
          _timeSeriesMaster.getTimeSeries(info.getTimeSeriesObjectId(), VersionCorrection.LATEST);
      TimeSeriesWithInfo timeSeriesWithInfo = new TimeSeriesWithInfo(info, timeSeries);
      return timeSeriesWithInfo;
    }
  }
  
  private class HolidayTransformer implements Function<HolidayDocument, ManageableHoliday> {
    @Override
    public ManageableHoliday apply(HolidayDocument input) {
      return input.getHoliday();
    }
  }
  
  private class ExchangeTransformer implements Function<ExchangeDocument, ManageableExchange> {
    @Override
    public ManageableExchange apply(ExchangeDocument input) {
      return input.getExchange();
    }
  }
  
  private class SnapshotTransformer implements Function<MarketDataSnapshotDocument, ManageableMarketDataSnapshot> {
    @Override
    public ManageableMarketDataSnapshot apply(MarketDataSnapshotDocument input) {
      return input.getSnapshot();
    }
  }
  
  private class OrganizationTransformer implements Function<OrganizationDocument, ManageableOrganization> {
    @Override
    public ManageableOrganization apply(OrganizationDocument input) {
      return input.getOrganization();
    }
  }
  
}
