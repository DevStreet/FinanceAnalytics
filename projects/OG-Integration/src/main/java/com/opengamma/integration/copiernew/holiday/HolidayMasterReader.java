/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.holiday;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.master.config.ConfigDocument;
import com.opengamma.master.holiday.HolidayMaster;
import com.opengamma.master.holiday.HolidaySearchRequest;
import com.opengamma.master.holiday.HolidaySearchRequest;
import com.opengamma.master.holiday.HolidaySearchResult;
import com.opengamma.master.holiday.ManageableHoliday;
import com.opengamma.master.holiday.HolidayMaster;

import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.paging.PagingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HolidayMasterReader implements Iterable<ManageableHoliday> {

  private static final Logger s_logger = LoggerFactory.getLogger(HolidayMasterReader.class);

  private HolidayMaster _holidayMaster;
  private HolidaySearchRequest _holidaySearchRequestTemplate;

  public HolidayMasterReader(HolidayMaster holidayMaster) {
    this(holidayMaster, null);
  }

  public HolidayMasterReader(HolidayMaster holidayMaster, HolidaySearchRequest holidaySearchRequest) {
    this(holidayMaster, holidaySearchRequest, PagingRequest.DEFAULT_PAGING_SIZE);
  }

  public HolidayMasterReader(HolidayMaster holidayMaster, HolidaySearchRequest holidaySearchRequest, int bufferSize) {
    ArgumentChecker.notNull(holidayMaster, "holidayMaster");
    ArgumentChecker.notNegativeOrZero(bufferSize, "bufferSize");
    if (holidaySearchRequest == null) {
      holidaySearchRequest = new HolidaySearchRequest();
      holidaySearchRequest.setPagingRequest(PagingRequest.ofIndex(0, bufferSize));
    }
    _holidaySearchRequestTemplate = holidaySearchRequest;
    _holidayMaster = holidayMaster;

  }

  @Override
  public Iterator<ManageableHoliday> iterator() {
    return new Iterator<ManageableHoliday>() {
  
      private int _nextPageIndex;
      private HolidaySearchResult _holidaySearchResult;
      private Iterator<ManageableHoliday> _iterator;
  
      {
        _nextPageIndex = 0;
        turnPage();
      }
  
      @Override
      public boolean hasNext() {
        if (!_iterator.hasNext()) {
          return (_holidaySearchResult != null) && (!_holidaySearchResult.getPaging().isLastPage());
        } else {
          return true;
        }
      }
  
      @Override
      public ManageableHoliday next() {
        while (true) {
          try {
            return _iterator.next();
          } catch (NoSuchElementException e) {
            if ((_holidaySearchResult != null) && (!_holidaySearchResult.getPaging().isLastPage())) {
              turnPage();
            } else {
              throw new NoSuchElementException();
            }
          }
        }
      }
  
      @Override
      public void remove() {
        throw new OpenGammaRuntimeException("Remove is not supported on this iterator");
      }
  
      private void turnPage() {
        HolidaySearchRequest holidaySearchRequest = _holidaySearchRequestTemplate;
        holidaySearchRequest.setPagingRequest(PagingRequest.ofIndex(_nextPageIndex, _holidaySearchRequestTemplate.getPagingRequest().getPagingSize()));
        _nextPageIndex += _holidaySearchRequestTemplate.getPagingRequest().getPagingSize();
        try {
          _holidaySearchResult = _holidayMaster.search(holidaySearchRequest);
          _iterator = _holidaySearchResult.getHolidays().iterator();
        } catch (Throwable t) {
          _iterator = new ArrayList<ManageableHoliday>().iterator();
          s_logger.error("Error performing holiday master search: " + t.getMessage());
        }
      }
    };
  }
  
}
