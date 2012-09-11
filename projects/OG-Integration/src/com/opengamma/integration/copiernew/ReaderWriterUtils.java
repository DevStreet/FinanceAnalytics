/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.integration.copier.portfolio.writer.MasterPortfolioWriter;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.integration.copiernew.config.ConfigMasterReader;
import com.opengamma.integration.copiernew.config.ConfigMasterWriter;
import com.opengamma.integration.copiernew.exchange.ExchangeMasterReader;
import com.opengamma.integration.copiernew.exchange.ExchangeMasterWriter;
import com.opengamma.integration.copiernew.historicaltimeseries.HistoricalTimeSeriesMasterReader;
import com.opengamma.integration.copiernew.historicaltimeseries.HistoricalTimeSeriesMasterWriter;
import com.opengamma.integration.copiernew.holiday.HolidayMasterReader;
import com.opengamma.integration.copiernew.holiday.HolidayMasterWriter;
import com.opengamma.integration.copiernew.portfolio.PortfolioMasterReader;
import com.opengamma.integration.copiernew.portfolio.PortfolioMasterWriter;
import com.opengamma.integration.copiernew.position.PositionMasterReader;
import com.opengamma.integration.copiernew.position.PositionMasterWriter;
import com.opengamma.integration.copiernew.security.SecurityMasterReader;
import com.opengamma.integration.copiernew.security.SecurityMasterWriter;
import com.opengamma.integration.copiernew.sheet.RowReader;
import com.opengamma.integration.copiernew.sheet.RowWriter;
import com.opengamma.integration.copiernew.sheet.SheetReader;
import com.opengamma.integration.copiernew.sheet.SheetWriter;
import com.opengamma.master.AbstractMaster;
import com.opengamma.master.AbstractSearchRequest;
import com.opengamma.master.config.ConfigMaster;
import com.opengamma.master.config.ConfigSearchRequest;
import com.opengamma.master.config.impl.RemoteConfigMaster;
import com.opengamma.master.exchange.ExchangeMaster;
import com.opengamma.master.exchange.ExchangeSearchRequest;
import com.opengamma.master.exchange.impl.RemoteExchangeMaster;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchRequest;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesMaster;
import com.opengamma.master.historicaltimeseries.impl.RemoteHistoricalTimeSeriesMaster;
import com.opengamma.master.holiday.HolidayMaster;
import com.opengamma.master.holiday.HolidaySearchRequest;
import com.opengamma.master.holiday.impl.RemoteHolidayMaster;
import com.opengamma.master.impl.AbstractRemoteMaster;
import com.opengamma.master.portfolio.PortfolioMaster;
import com.opengamma.master.portfolio.PortfolioSearchRequest;
import com.opengamma.master.portfolio.impl.RemotePortfolioMaster;
import com.opengamma.master.position.PositionMaster;
import com.opengamma.master.position.PositionSearchRequest;
import com.opengamma.master.position.impl.RemotePositionMaster;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.master.security.SecuritySearchRequest;
import com.opengamma.master.security.impl.RemoteSecurityMaster;
import com.opengamma.util.paging.PagingRequest;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public final class ReaderWriterUtils {

  private static Map<String, MasterClassInfo> _masterClassInfos = new HashMap<String, MasterClassInfo>();

  static {
    _masterClassInfos.put("config", new MasterClassInfo(ConfigMaster.class, RemoteConfigMaster.class, ConfigSearchRequest.class, ConfigMasterReader.class, ConfigMasterWriter.class, "ConfigMaster"));
    _masterClassInfos.put("exchange", new MasterClassInfo(ExchangeMaster.class, RemoteExchangeMaster.class, ExchangeSearchRequest.class, ExchangeMasterReader.class, ExchangeMasterWriter.class, "ExchangeMaster"));
    _masterClassInfos.put("historicaltimeseries", new MasterClassInfo(HistoricalTimeSeriesMaster.class, RemoteHistoricalTimeSeriesMaster.class, HistoricalTimeSeriesInfoSearchRequest.class, HistoricalTimeSeriesMasterReader.class, HistoricalTimeSeriesMasterWriter.class, "HistoricalTimeSeriesMaster"));
    _masterClassInfos.put("holiday", new MasterClassInfo(HolidayMaster.class, RemoteHolidayMaster.class, HolidaySearchRequest.class, HolidayMasterReader.class, HolidayMasterWriter.class, "HolidayMaster"));
    _masterClassInfos.put("portfolio", new MasterClassInfo(PortfolioMaster.class, RemotePortfolioMaster.class, PortfolioSearchRequest.class, PortfolioMasterReader.class, PortfolioMasterWriter.class, "PortfolioMaster"));
    _masterClassInfos.put("position", new MasterClassInfo(PositionMaster.class, RemotePositionMaster.class, PositionSearchRequest.class, PositionMasterReader.class, PositionMasterWriter.class, "PositionMaster"));
    _masterClassInfos.put("security", new MasterClassInfo(SecurityMaster.class, RemoteSecurityMaster.class, SecuritySearchRequest.class, SecurityMasterReader.class, SecurityMasterWriter.class, "SecurityMaster"));
  }

  public static class MasterClassInfo {
    private Class<?> _masterClass; // extends AbstractMaster not possible as ConfigMaster is not an AbstractMaster (!?)
    private Class<? extends AbstractRemoteMaster> _remoteMasterClass;
    private Class<? extends AbstractSearchRequest> _searchRequestClass;
    private Class<? extends Iterable> _readerClass;
    private Class<? extends Writeable> _writerClass;
    private String _uriSuffix;

    private MasterClassInfo(Class<?> masterClass,
                            Class<? extends AbstractRemoteMaster> remoteMasterClass,
                            Class<? extends AbstractSearchRequest> searchRequestClass,
                            Class<? extends Iterable> readerClass,
                            Class<? extends Writeable> writerClass,
                            String uriSuffix) {
      _masterClass = masterClass;
      _remoteMasterClass = remoteMasterClass;
      _searchRequestClass = searchRequestClass;
      _readerClass = readerClass;
      _writerClass = writerClass;
      _uriSuffix = uriSuffix;
    }

    public Class<?> getMasterClass() {
      return _masterClass;
    }

    public Class<? extends AbstractRemoteMaster> getRemoteMasterClass() {
      return _remoteMasterClass;
    }

    public Class<? extends AbstractSearchRequest> getSearchRequestClass() {
      return _searchRequestClass;
    }

    public Class<? extends Iterable> getReaderClass() {
      return _readerClass;
    }

    public Class<? extends Writeable> getWriterClass() {
      return _writerClass;
    }

    public String getUriSuffix() {
      return _uriSuffix;
    }
  }

  public static Map<String,MasterClassInfo> getMasterClassInfos() {
    return _masterClassInfos;
  }

  public static MasterClassInfo getMasterClassInfo(String type) {
    return _masterClassInfos.get(type.trim().toLowerCase());
  }

  public static Object getRemoteMaster(String baseUri, String type, String classifier) {
    MasterClassInfo info = getMasterClassInfo(type);

    Object master;
    try {
       Class<?> clazz = info.getRemoteMasterClass();
       master = clazz.getConstructor(URI.class).newInstance(URI.create(baseUri + "/jax/components/"
           + info.getUriSuffix() + "/" + classifier));
     } catch (Throwable t) {
       throw new OpenGammaRuntimeException(t.getMessage());
     }

    return master;
  }

  public static Iterable getMasterReader(String baseUri, String type, String classifier, String searchFilter) {

    MasterClassInfo info = getMasterClassInfo(type);

    Object master = getRemoteMaster(baseUri, type, classifier);

    AbstractSearchRequest searchRequest;
    try {
      Class<?> clazz = info.getSearchRequestClass();
      searchRequest = (AbstractSearchRequest) clazz.getConstructor().newInstance();
      searchRequest.setPagingRequest(PagingRequest.ofIndex(0, PagingRequest.DEFAULT_PAGING_SIZE));
    } catch (Throwable t) {
      throw new OpenGammaRuntimeException(t.getMessage());
    }

    // Special case for configurations: set search type to any
    if (type.trim().toLowerCase().equals("config")) {
      ((ConfigSearchRequest) searchRequest).setType(Object.class);
    }

    if (searchRequest.propertyNames().contains("name")) {
      searchRequest.property("name").set(searchFilter);
    }

    Iterable reader;
    try {
      Class<?> readerClazz = info.getReaderClass();
      Class<?> masterClazz = info.getMasterClass();
      reader = (Iterable) readerClazz.getConstructor(masterClazz, searchRequest.getClass()).newInstance(master, searchRequest);
    } catch (Throwable t) {
      throw new OpenGammaRuntimeException(t.getMessage());
    }

    return reader;
  }

  public static Iterable getMasterReader(String uri) {
    String[] split = getUriParts(uri);
    return getMasterReader(split[0], split[1], split[2], split[3]);
  }

  public static String[] getUriParts(String uri) {
    String[] split = uri.split("/jax/components/", 2);
    if (split.length != 2) {
      throw new OpenGammaRuntimeException("Illegal URI string");
    }
    String baseUri = split[0];
    String[] splitAgain = split[1].split("/", 3);
    if (splitAgain.length < 2) {
      throw new OpenGammaRuntimeException("Illegal URI string");
    }
    String type = splitAgain[0].replace("Master", "").toLowerCase();
    String classifier = splitAgain[1];
    String searchFilter = splitAgain.length == 3 ? splitAgain[2] : "*";

    return new String[] {baseUri, type, classifier, searchFilter};
  }

  public static Writeable getMasterWriter(String baseUri, String type, String classifier, String renameTemplate) {

    MasterClassInfo info = getMasterClassInfo(type);

    Object master = getRemoteMaster(baseUri, type, classifier);

    Writeable writer;
    try {
      Class<?> writerClazz = info.getWriterClass();
      Class<?> masterClazz = info.getMasterClass();
      writer = (Writeable) writerClazz.getConstructor(masterClazz, String.class).newInstance(master, renameTemplate);
    } catch (Throwable t) {
      throw new OpenGammaRuntimeException(t.getMessage());
    }

    return writer;
  }

  public static Writeable getMasterWriter(String uri) {

    String[] split = uri.split("/jax/components/", 2);
    if (split.length < 2) {
      throw new OpenGammaRuntimeException("Illegal URI string");
    }
    String baseUri = split[0];
    String[] splitAgain = split[1].split("/", 3);
    if (splitAgain.length < 2) {
      throw new OpenGammaRuntimeException("Illegal URI string");
    }
    String type = splitAgain[0].replace("Master", "").toLowerCase();
    String classifier = splitAgain[1];
    String renameTemplate = splitAgain.length == 3 ? splitAgain[2] : null;

    return getMasterWriter(type, classifier, baseUri, renameTemplate);
  }

}
