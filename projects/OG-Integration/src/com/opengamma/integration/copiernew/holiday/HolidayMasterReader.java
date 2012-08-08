/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.holiday;

import com.opengamma.master.holiday.HolidayMaster;
import com.opengamma.master.holiday.HolidaySearchRequest;
import com.opengamma.master.holiday.HolidaySearchResult;
import com.opengamma.master.holiday.ManageableHoliday;
import com.opengamma.util.ArgumentChecker;

import java.util.Iterator;

public class HolidayMasterReader implements Iterable<ManageableHoliday> {

  private HolidaySearchResult _holidaySearchResult;

  public HolidayMasterReader(HolidayMaster holidayMaster) {
    this(holidayMaster, null);
  }

  public HolidayMasterReader(HolidayMaster holidayMaster, HolidaySearchRequest holidaySearchRequest) {
    ArgumentChecker.notNull(holidayMaster, "holidayMaster");
    if (holidaySearchRequest == null) {
      holidaySearchRequest = new HolidaySearchRequest();
    }
    _holidaySearchResult = holidayMaster.search(holidaySearchRequest);
  }

  @Override
  public Iterator<ManageableHoliday> iterator() {
    return _holidaySearchResult.getHolidays().iterator();
  }
}
