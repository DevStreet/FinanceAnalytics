package com.opengamma.integration.copiernew.holiday;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.id.ExternalIdSearch;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.master.holiday.HolidayDocument;
import com.opengamma.master.holiday.HolidayMaster;
import com.opengamma.master.holiday.HolidaySearchRequest;
import com.opengamma.master.holiday.HolidaySearchResult;
import com.opengamma.master.holiday.HolidaySearchSortOrder;
import com.opengamma.master.holiday.ManageableHoliday;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.beancompare.BeanCompare;
import com.opengamma.util.beancompare.BeanDifference;

import javax.time.calendar.ZonedDateTime;
import java.io.IOException;
import java.util.List;

public class HolidayMasterWriter implements Writeable<ManageableHoliday> {

  HolidayMaster _holidayMaster;
  private BeanCompare _beanCompare;

  public HolidayMasterWriter(HolidayMaster holidayMaster) {
    ArgumentChecker.notNull(holidayMaster, "holidayMaster");
    _holidayMaster = holidayMaster;
    _beanCompare = new BeanCompare();
  }

  @Override
  public void addOrUpdate(ManageableHoliday holiday) {
    ArgumentChecker.notNull(holiday, "holiday");

    HolidaySearchRequest searchReq = new HolidaySearchRequest();
    ExternalIdSearch exchangeIdSearch = new ExternalIdSearch(holiday.getExchangeExternalId());  // match any one of the IDs
    searchReq.setVersionCorrection(VersionCorrection.ofVersionAsOf(ZonedDateTime.now())); // valid now
    searchReq.setExchangeExternalIdSearch(exchangeIdSearch);
    searchReq.setSortOrder(HolidaySearchSortOrder.VERSION_FROM_INSTANT_DESC);
    searchReq.setCurrency(holiday.getCurrency());
    ExternalIdSearch regionIdSearch = new ExternalIdSearch(holiday.getRegionExternalId());  // match any one of the IDs
    searchReq.setRegionExternalIdSearch(regionIdSearch);
    HolidaySearchResult searchResult = _holidayMaster.search(searchReq);
    ManageableHoliday foundHoliday = searchResult.getFirstHoliday();
    if (foundHoliday != null) {
      List<BeanDifference<?>> differences;
      try {
        differences = _beanCompare.compare(foundHoliday, holiday);
      } catch (Exception e) {
        throw new OpenGammaRuntimeException("Error comparing holidays " + holiday, e);
      }
      if (differences.size() == 1 && differences.get(0).getProperty().propertyType() == UniqueId.class) {
        // It's already there, don't update or add it
      } else {
        HolidayDocument updateDoc = new HolidayDocument(holiday);
        updateDoc.setUniqueId(foundHoliday.getUniqueId());
        HolidayDocument result = _holidayMaster.update(updateDoc);
      }
    } else {
      // Not found, so add it
      HolidayDocument addDoc = new HolidayDocument(holiday);
      HolidayDocument result = _holidayMaster.add(addDoc);
    }
  }

  @Override
  public void addOrUpdate(Iterable<ManageableHoliday> data) {
    for (ManageableHoliday datum : data) {
      addOrUpdate(datum);
    }
  }

  @Override
  public void flush() throws IOException {
    // No action
  }
}
