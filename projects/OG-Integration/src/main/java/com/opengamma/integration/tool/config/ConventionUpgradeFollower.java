package com.opengamma.integration.tool.config;

import com.opengamma.core.convention.impl.MockConvention;
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
import com.opengamma.id.ExternalId;
import com.opengamma.util.time.Tenor;
import com.opengamma.util.tuple.ObjectsPair;

class ConventionUpgradeFollower extends ConventionFollower<Void, ObjectsPair<ExternalId, Tenor>> {
  @Override
  public ObjectsPair<ExternalId, Tenor> followVanillaIborLegRollDateConvention(VanillaIborLegRollDateConvention convention, Void parameter) {
    if (convention.getResetTenor() == null) {
      System.err.println("VanillaIborLegRollDateConvention called " + convention.getName() + " has a null reset tenor so can't determine tenor of Ibor Index");
      return null;
    }
    return ObjectsPair.of(convention.getIborIndexConvention(), convention.getResetTenor());
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followVanillaIborLegConvention(VanillaIborLegConvention convention, Void parameter) {
    if (convention.getResetTenor() == null) {
      System.err.println("VanillaIborLegConvention called " + convention.getName() + " has a null reset tenor so can't determine tenor of Ibor Index");
      return null;
    }
    return ObjectsPair.of(convention.getIborIndexConvention(), convention.getResetTenor());
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followSwapIndexConvention(SwapIndexConvention convention, Void parameter) {
    return null;
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followSwapFixedLegConvention(SwapFixedLegConvention cnvention, Void parameter) {
    return null;
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followSwapConvention(SwapConvention convention, Void parameter) {
    return null;
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followRollDateSwapConvention(RollDateSwapConvention convention, Void parameter) {
    return null;
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followRollDateFRAConvention(RollDateFRAConvention convention, Void parameter) {
    return null; // special case, don't upgrade.
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followPriceIndexConvention(PriceIndexConvention convention, Void parameter) {
    return null;
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followOvernightIndexConvention(OvernightIndexConvention convention, Void parameter) {
    return null;
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followONCompoundedLegRollRateConvention(ONCompoundedLegRollDateConvention convention, Void parameter) {
    return ObjectsPair.of(convention.getOvernightIndexConvention(), Tenor.ON);
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followONArithmeticAverageLegConvention(ONArithmeticAverageLegConvention convention, Void parameter) {
    return ObjectsPair.of(convention.getOvernightIndexConvention(), Tenor.ON);
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followOISLegConvention(OISLegConvention convention, Void parameter) {
    return ObjectsPair.of(convention.getOvernightIndexConvention(), Tenor.ON);
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followMockConvention(MockConvention convention, Void parameter) {
    return null;
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followFloatingInterestRateSwapLegConvention(FloatingInterestRateSwapLegConvention convention, Void parameter) {
    return null;
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followFixedInterestRateSwapLegConvention(FixedInterestRateSwapLegConvention convention, Void parameter) {
    return null;
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followInflationLegConvention(InflationLegConvention convention, Void parameter) {
    return null; // ignore as no one is doing inflation pre-upgrade.
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followIborIndexConvention(IborIndexConvention convention, Void parameter) {
    return null;
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followFXSpotConvention(FXSpotConvention convention, Void parameter) {
    return null;
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followFXForwardAndSwapConvention(FXForwardAndSwapConvention convention, Void parameter) {
    return null;
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followFixedLegRollRateConvention(FixedLegRollDateConvention convention, Void parameter) {
    return null;
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followOtherExchangeTradedFutureAndOptionConvention(ExchangeTradedFutureAndOptionConvention convention, Void parameter) {
    return null;
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followInterestRateFutureConvention(InterestRateFutureConvention convention, Void parameter) {
    return ObjectsPair.of(convention.getIndexConvention(), Tenor.ON);
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followFederalFundsFutureConvention(FederalFundsFutureConvention convention, Void parameter) {
    return ObjectsPair.of(convention.getIndexConvention(), Tenor.ON);
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followDeliverablePriceQuotedSwapFutureConvention(DeliverablePriceQuotedSwapFutureConvention convention, Void parameter) {
    return null;
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followEquityConvention(EquityConvention convention, Void parameter) {
    return null;
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followDepositorConvention(DepositConvention convention, Void parameter) {
    return null;
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followCompoundingIborLegConvention(CompoundingIborLegConvention convention, Void parameter) {
    if (convention.getCompositionTenor() == null) {
      System.err.println("CompoundingIborLegConvention called " + convention.getName() + " has a null composition tenor so can't determine tenor of Ibor Index");
      return null;
    }
    return ObjectsPair.of(convention.getIborIndexConvention(), convention.getCompositionTenor());
  }

  @Override
  public ObjectsPair<ExternalId, Tenor> followCMSLegConvention(CMSLegConvention convention, Void parameter) {
    return null;
  }

}