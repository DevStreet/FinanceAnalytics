/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */

package com.opengamma.examples.simulated.loader;

import static com.opengamma.core.id.ExternalSchemes.OG_SYNTHETIC_TICKER;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.financial.convention.ConventionBundle;
import com.opengamma.financial.convention.ConventionBundleSource;
import com.opengamma.financial.convention.InMemoryConventionBundleMaster;
import com.opengamma.financial.security.index.IborIndex;
import com.opengamma.financial.security.index.OvernightIndex;
import com.opengamma.financial.tool.ToolContext;
import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.master.security.RawSecurity;
import com.opengamma.master.security.SecurityDocument;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.util.money.Currency;
import com.opengamma.util.time.Tenor;

/**
 * 
 */
public class PortfolioLoaderHelper {
  /**
   * Logger
   */
  private static final Logger s_logger = LoggerFactory.getLogger(PortfolioLoaderHelper.class);

  /**
   * Raw security type for libor rates
   */
  private static final String LIBOR_RATE_SECURITY_TYPE = "LIBOR_RATE";

  /** File name option flag */
  public static final String FILE_NAME_OPT = "f";
  /** Portfolio name option flag*/
  public static final String PORTFOLIO_NAME_OPT = "n";
  /** Run mode option flag */
  public static final String RUN_MODE_OPT = "r";
  /** Write option flag */
  public static final String WRITE_OPT = "w";
  /** Standard date-time formatter for the input */
  public static final DateTimeFormatter CSV_DATE_FORMATTER;
  /** Standard date-time formatter for the output */
  public static final DateTimeFormatter OUTPUT_DATE_FORMATTER;
  /** Command-line options */
  public static final Options OPTIONS;
  /** Standard rate formatter */
  public static final DecimalFormat RATE_FORMATTER = new DecimalFormat("0.###%");
  /** Standard notional formatter */
  public static final DecimalFormat NOTIONAL_FORMATTER = new DecimalFormat("0,000");

  static {
    DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
    builder.appendPattern("dd/MM/yyyy");
    CSV_DATE_FORMATTER = builder.toFormatter();
    builder = new DateTimeFormatterBuilder();
    builder.appendPattern("yyyy-MM-dd");
    OUTPUT_DATE_FORMATTER = builder.toFormatter();
    OPTIONS = PortfolioLoaderHelper.getOptions();
  }

  public static Options getOptions() {
    Options options = new Options();
    buildOptions(options);
    return options;
  }

  /**
   * Builds the set of options.
   * 
   * @param options  the options to add to, not null
   */
  public static void buildOptions(Options options) {
    Option filenameOption = new Option(FILE_NAME_OPT, "filename", true, "The path to the CSV file of cash details");
    filenameOption.setRequired(true);
    options.addOption(filenameOption);

    Option portfolioNameOption = new Option(PORTFOLIO_NAME_OPT, "name", true, "The name of the portfolio");
    portfolioNameOption.setRequired(true);
    options.addOption(portfolioNameOption);

    //    Option runModeOption = new Option(RUN_MODE_OPT, "runmode", true, "The run mode: shareddev, standalone");
    //    runModeOption.setRequired(true);
    //    options.addOption(runModeOption);

    Option writeOption = new Option(WRITE_OPT, "write", false, "Actually persists the portfolio to the database");
    options.addOption(writeOption);
  }

  public static void usage(String loaderName) {
    HelpFormatter helpFormatter = new HelpFormatter();
    helpFormatter.setWidth(100);
    helpFormatter.printHelp(loaderName, OPTIONS);
  }

  public static void normaliseHeaders(String[] headers) {
    for (int i = 0; i < headers.length; i++) {
      headers[i] = headers[i].toLowerCase();
    }
  }

  public static String getWithException(Map<String, String> fieldValueMap, String fieldName) {
    String result = fieldValueMap.get(fieldName);
    if (result == null) {
      System.err.println(fieldValueMap);
      throw new IllegalArgumentException("Could not find field '" + fieldName + "'");
    }
    return result;
  }

  public static LocalDate getDateWithException(Map<String, String> fieldValueMap, String fieldName) {
    return LocalDate.parse(getWithException(fieldValueMap, fieldName), CSV_DATE_FORMATTER);
  }

  public static void persistLiborRawSecurities(Set<Currency> currencies, ToolContext toolContext) {
    SecurityMaster securityMaster = toolContext.getSecurityMaster();
    byte[] rawData = new byte[] {0};
    StringBuilder sb = new StringBuilder();
    sb.append("Created ").append(currencies.size()).append(" libor securities:\n");
    for (Currency ccy : currencies) {
      ConventionBundle swapConvention = getSwapConventionBundle(ccy, toolContext.getConventionBundleSource());
      ConventionBundle liborConvention = getLiborConventionBundle(swapConvention, toolContext.getConventionBundleSource());
      sb.append("\t").append(liborConvention.getIdentifiers()).append("\n");
      RawSecurity rawSecurity = new RawSecurity(LIBOR_RATE_SECURITY_TYPE, rawData);
      rawSecurity.setExternalIdBundle(liborConvention.getIdentifiers());
      SecurityDocument secDoc = new SecurityDocument();
      secDoc.setSecurity(rawSecurity);
      securityMaster.add(secDoc);
    }
    s_logger.info(sb.toString());
  }

  public static void persistIborIndexSecurities(Set<Currency> currencies, ToolContext toolContext) {
    SecurityMaster securityMaster = toolContext.getSecurityMaster();
    StringBuilder sb = new StringBuilder();
    sb.append("Created 11 libor index securities:\n");
    {

      //
      //  USD
      //
      IborIndex iborIndex = new IborIndex();
      iborIndex.setName("ICE LIBOR USD 1 Month");
      iborIndex.setIndexFamilyId(ExternalId.of("BLOOMBERG_INDEX_FAMILY", "ICE LIBOR USD"));
      iborIndex.setTenor(Tenor.ofMonths(1));
      iborIndex.setConventionId(ExternalId.of("BLOOMBERG_CONVENTION_NAME", "ICE LIBOR USD"));
      iborIndex.setExternalIdBundle(ExternalIdBundle.of(OG_SYNTHETIC_TICKER, "USDLIBORP1M"));
      SecurityDocument secDoc = new SecurityDocument();
      secDoc.setSecurity(iborIndex);
      securityMaster.add(secDoc);
      //
      iborIndex = new IborIndex();
      iborIndex.setName("ICE LIBOR USD 3 Month");
      iborIndex.setIndexFamilyId(ExternalId.of("BLOOMBERG_INDEX_FAMILY", "ICE LIBOR USD"));
      iborIndex.setTenor(Tenor.ofMonths(3));
      iborIndex.setConventionId(ExternalId.of("BLOOMBERG_CONVENTION_NAME", "ICE LIBOR USD"));
      iborIndex.setExternalIdBundle(ExternalIdBundle.of(OG_SYNTHETIC_TICKER, "USDLIBORP3M"));
      secDoc = new SecurityDocument();
      secDoc.setSecurity(iborIndex);
      securityMaster.add(secDoc);
      //
      iborIndex = new IborIndex();
      iborIndex.setName("ICE LIBOR USD 6 Month");
      iborIndex.setIndexFamilyId(ExternalId.of("BLOOMBERG_INDEX_FAMILY", "ICE LIBOR USD"));
      iborIndex.setTenor(Tenor.ofMonths(6));
      iborIndex.setConventionId(ExternalId.of("BLOOMBERG_CONVENTION_NAME", "ICE LIBOR USD"));
      iborIndex.setExternalIdBundle(ExternalIdBundle.of(OG_SYNTHETIC_TICKER, "USDLIBORP6M"));
      secDoc = new SecurityDocument();
      secDoc.setSecurity(iborIndex);
      securityMaster.add(secDoc);
      //
      //iborIndex = new IborIndex();
      //iborIndex.setName("USDISDA10P1Y");
      //iborIndex.setIndexFamilyId(ExternalId.of("BLOOMBERG_INDEX_FAMILY", "ICE LIBOR USD"));
      //iborIndex.setTenor(Tenor.ofYears(1));
      //iborIndex.setConventionId(ExternalId.of("BLOOMBERG_CONVENTION_NAME", "ICE LIBOR USD"));
      //iborIndex.setExternalIdBundle(ExternalIdBundle.of(ExternalId.of(OG_SYNTHETIC_TICKER, "USDISDA10P1Y"), ExternalId.of("OG_TICKER", "USDISDA10P1Y")));
      //secDoc = new SecurityDocument();
      //secDoc.setSecurity(iborIndex);
      //securityMaster.add(secDoc);
      ////
      //iborIndex = new IborIndex();
      //iborIndex.setName("USDISDA10P5Y");
      //iborIndex.setIndexFamilyId(ExternalId.of("BLOOMBERG_INDEX_FAMILY", "ICE LIBOR USD"));
      //iborIndex.setTenor(Tenor.ofYears(5));
      //iborIndex.setConventionId(ExternalId.of("BLOOMBERG_CONVENTION_NAME", "ICE LIBOR USD"));
      //iborIndex.setExternalIdBundle(ExternalIdBundle.of(ExternalId.of(OG_SYNTHETIC_TICKER, "USDISDA10P5Y"), ExternalId.of("OG_TICKER", "USDISDA10P5Y")));
      //secDoc = new SecurityDocument();
      //secDoc.setSecurity(iborIndex);
      //securityMaster.add(secDoc);
      ////
      //iborIndex = new IborIndex();
      //iborIndex.setName("USDISDA10P2Y");
      //iborIndex.setIndexFamilyId(ExternalId.of("BLOOMBERG_INDEX_FAMILY", "ICE LIBOR USD"));
      //iborIndex.setTenor(Tenor.ofYears(2));
      //iborIndex.setConventionId(ExternalId.of("BLOOMBERG_CONVENTION_NAME", "ICE LIBOR USD"));
      //iborIndex.setExternalIdBundle(ExternalIdBundle.of(ExternalId.of(OG_SYNTHETIC_TICKER, "USDISDA10P2Y"), ExternalId.of("OG_TICKER", "USDISDA10P2Y")));
      //secDoc = new SecurityDocument();
      //secDoc.setSecurity(iborIndex);
      //securityMaster.add(secDoc);
      //


    }
    s_logger.info(sb.toString());
  }


  public static void persistOvernightIndexSecurities(Set<Currency> currencies, ToolContext toolContext) {
    SecurityMaster securityMaster = toolContext.getSecurityMaster();
    StringBuilder sb = new StringBuilder();
    sb.append("Created ").append(currencies.size()).append(" overnight index securities:\n");
    {
      //
      // EONIA
      //
      OvernightIndex overnightIndex = new OvernightIndex();
      overnightIndex.setName("Effective Overnight Index Aver");
      overnightIndex.setIndexFamilyId(ExternalId.of("BLOOMBERG_INDEX_FAMILY", "Effective"));
      overnightIndex.setConventionId(ExternalId.of("BLOOMBERG_CONVENTION_NAME", "Effective"));
      overnightIndex.setExternalIdBundle(ExternalIdBundle.of(ExternalId.of("BLOOMBERG_BUID", "NI379965"), ExternalId.of("BLOOMBERG_TICKER", "EONIA Index"), ExternalId.of("OG_TICKER", "EONIA")));
      SecurityDocument secDoc = new SecurityDocument();
      secDoc.setSecurity(overnightIndex);
      securityMaster.add(secDoc);
      //
      overnightIndex = new OvernightIndex();
      overnightIndex.setName("Federal Funds Effective Rate U");
      overnightIndex.setIndexFamilyId(ExternalId.of("BLOOMBERG_INDEX_FAMILY", "Federal Funds Effective Rate U"));
      overnightIndex.setConventionId(ExternalId.of("BLOOMBERG_CONVENTION_NAME", "Federal Funds Effective Rate U"));
      overnightIndex.setExternalIdBundle(ExternalIdBundle.of(OG_SYNTHETIC_TICKER, "FEDL01 Index"));
      secDoc = new SecurityDocument();
      secDoc.setSecurity(overnightIndex);
      securityMaster.add(secDoc);
      //
      overnightIndex = new OvernightIndex();
      overnightIndex.setName("FRBA Cash Rate Overnight");
      overnightIndex.setIndexFamilyId(ExternalId.of("BLOOMBERG_INDEX_FAMILY", "RBA Cash Rate"));
      overnightIndex.setConventionId(ExternalId.of("BLOOMBERG_CONVENTION_NAME", "RBA Cash Rate"));
      overnightIndex.setExternalIdBundle(ExternalIdBundle.of(ExternalId.of("BLOOMBERG_BUID", "NI7564392"), ExternalId.of("BLOOMBERG_TICKER", "RBACOR Index"), ExternalId.of("OG_TICKER", "RBACOR")));
      secDoc = new SecurityDocument();
      secDoc.setSecurity(overnightIndex);
      securityMaster.add(secDoc);
      //
      overnightIndex = new OvernightIndex();
      overnightIndex.setName("SONIA O/N DEPOSIT RATES SWAP");
      overnightIndex.setIndexFamilyId(ExternalId.of("BLOOMBERG_INDEX_FAMILY", "S"));
      overnightIndex.setConventionId(ExternalId.of("BLOOMBERG_CONVENTION_NAME", "S"));
      overnightIndex.setExternalIdBundle(ExternalIdBundle.of(ExternalId.of("BLOOMBERG_BUID", "NI1317898"), ExternalId.of("BLOOMBERG_TICKER", "SONIO/N Index"), ExternalId.of("OG_TICKER", "SONIA")));
      secDoc = new SecurityDocument();
      secDoc.setSecurity(overnightIndex);
      securityMaster.add(secDoc);
      //
      overnightIndex = new OvernightIndex();
      overnightIndex.setName("Bank of Canada Overnight Repo");
      overnightIndex.setIndexFamilyId(ExternalId.of("BLOOMBERG_INDEX_FAMILY", "Bank of Canada"));
      overnightIndex.setConventionId(ExternalId.of("BLOOMBERG_CONVENTION_NAME", "Bank of Canada"));
      overnightIndex.setExternalIdBundle(ExternalIdBundle.of(ExternalId.of("BLOOMBERG_BUID", "NI1454712"), ExternalId.of("BLOOMBERG_TICKER", "CAONREPO Index"), ExternalId.of("OG_TICKER", "CADCORRA")));
      secDoc = new SecurityDocument();
      secDoc.setSecurity(overnightIndex);
      securityMaster.add(secDoc);
    }
    s_logger.info(sb.toString());
  }

  private static ConventionBundle getSwapConventionBundle(Currency ccy, ConventionBundleSource conventionSource) {
    ConventionBundle swapConvention = conventionSource.getConventionBundle(ExternalId.of(InMemoryConventionBundleMaster.SIMPLE_NAME_SCHEME, ccy.getCode() + "_SWAP"));
    if (swapConvention == null) {
      throw new OpenGammaRuntimeException("Couldn't get swap convention for " + ccy.getCode());
    }
    return swapConvention;
  }

  private static ConventionBundle getLiborConventionBundle(ConventionBundle swapConvention, ConventionBundleSource conventionSource) {
    ConventionBundle liborConvention = conventionSource.getConventionBundle(swapConvention.getSwapFloatingLegInitialRate());
    if (liborConvention == null) {
      throw new OpenGammaRuntimeException("Couldn't get libor convention for " + swapConvention.getSwapFloatingLegInitialRate());
    }
    return liborConvention;
  }
}
