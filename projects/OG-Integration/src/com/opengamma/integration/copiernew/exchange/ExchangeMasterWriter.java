package com.opengamma.integration.copiernew.exchange;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.id.ExternalIdSearch;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.master.exchange.ExchangeMaster;
import com.opengamma.master.exchange.*;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.beancompare.BeanCompare;
import com.opengamma.util.beancompare.BeanDifference;

import javax.time.calendar.ZonedDateTime;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kevin
 * Date: 6/25/12
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExchangeMasterWriter implements Writeable<ManageableExchange> {

  ExchangeMaster _exchangeMaster;
  private BeanCompare _beanCompare;

  public ExchangeMasterWriter(ExchangeMaster exchangeMaster) {
    ArgumentChecker.notNull(exchangeMaster, "exchangeMaster");
    _exchangeMaster = exchangeMaster;
    _beanCompare = new BeanCompare();
  }

  @Override
  public ManageableExchange addOrUpdate(ManageableExchange exchange) {
    ArgumentChecker.notNull(exchange, "exchange");

    ExchangeSearchRequest searchReq = new ExchangeSearchRequest();
    ExternalIdSearch idSearch = new ExternalIdSearch(exchange.getExternalIdBundle());  // match any one of the IDs
    searchReq.setVersionCorrection(VersionCorrection.ofVersionAsOf(ZonedDateTime.now())); // valid now
    searchReq.setExternalIdSearch(idSearch);
    searchReq.setSortOrder(ExchangeSearchSortOrder.VERSION_FROM_INSTANT_DESC);
    ExchangeSearchResult searchResult = _exchangeMaster.search(searchReq);
    ManageableExchange foundExchange = searchResult.getFirstExchange();
    if (foundExchange != null) {
      List<BeanDifference<?>> differences;
      try {
        differences = _beanCompare.compare(foundExchange, exchange);
      } catch (Exception e) {
        throw new OpenGammaRuntimeException("Error comparing exchanges with ID bundle " + exchange.getExternalIdBundle(), e);
      }
      if (differences.size() == 1 && differences.get(0).getProperty().propertyType() == UniqueId.class) {
        // It's already there, don't update or add it
        return foundExchange;
      } else {
        ExchangeDocument updateDoc = new ExchangeDocument(exchange);
        updateDoc.setUniqueId(foundExchange.getUniqueId());
        ExchangeDocument result = _exchangeMaster.update(updateDoc);
        return result.getExchange();
      }
    } else {
      // Not found, so add it
      ExchangeDocument addDoc = new ExchangeDocument(exchange);
      ExchangeDocument result = _exchangeMaster.add(addDoc);
      return result.getExchange();
    }
  }

  @Override
  public void flush() throws IOException {
    // No action
  }
}
