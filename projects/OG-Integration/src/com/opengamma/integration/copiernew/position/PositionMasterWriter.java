package com.opengamma.integration.copiernew.position;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.id.ExternalIdSearch;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.master.position.PositionDocument;
import com.opengamma.master.position.PositionMaster;
import com.opengamma.master.position.PositionSearchRequest;
import com.opengamma.master.position.PositionSearchResult;
import com.opengamma.master.position.ManageablePosition;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.beancompare.BeanCompare;
import com.opengamma.util.beancompare.BeanDifference;

import javax.time.calendar.ZonedDateTime;
import java.io.IOException;
import java.util.List;

public class PositionMasterWriter implements Writeable<ManageablePosition> {

  PositionMaster _positionMaster;
  private BeanCompare _beanCompare;

  public PositionMasterWriter(PositionMaster positionMaster) {
    ArgumentChecker.notNull(positionMaster, "positionMaster");
    _positionMaster = positionMaster;
    _beanCompare = new BeanCompare();
  }

  @Override
  public ManageablePosition addOrUpdate(ManageablePosition position) {
    ArgumentChecker.notNull(position, "position");

    PositionSearchRequest searchReq = new PositionSearchRequest();
    ExternalIdSearch idSearch = new ExternalIdSearch(position.getSecurity().getExternalIdBundle());  // match any one of the IDs
    searchReq.setVersionCorrection(VersionCorrection.ofVersionAsOf(ZonedDateTime.now())); // valid now
    searchReq.setSecurityIdSearch(idSearch);

    // Try to match by provider id, otherwise look for positions w/ same quantity (not necessarily good)
    if (position.getProviderId() != null) {
      searchReq.setPositionProviderId(position.getProviderId());
    } else {
      searchReq.setMaxQuantity(position.getQuantity());
      searchReq.setMinQuantity(position.getQuantity());
    }

    PositionSearchResult searchResult = _positionMaster.search(searchReq);
    ManageablePosition foundPosition = searchResult.getFirstPosition();
    if (foundPosition != null) {
      List<BeanDifference<?>> differences;
      try {
        differences = _beanCompare.compare(foundPosition, position);
      } catch (Exception e) {
        throw new OpenGammaRuntimeException("Error comparing positions (" + position.getName() + ")", e);
      }
      if (differences.size() == 1 && differences.get(0).getProperty().propertyType() == UniqueId.class) {
        // It's already there, don't update or add it
        return foundPosition;
      } else {
        PositionDocument updateDoc = new PositionDocument(position);
        updateDoc.setUniqueId(foundPosition.getUniqueId());
        PositionDocument result = _positionMaster.update(updateDoc);
        return result.getPosition();
      }
    } else {
      // Not found, so add it
      PositionDocument addDoc = new PositionDocument(position);
      PositionDocument result = _positionMaster.add(addDoc);
      return result.getPosition();
    }
  }

  @Override
  public void flush() throws IOException {
    // No action
  }
}
