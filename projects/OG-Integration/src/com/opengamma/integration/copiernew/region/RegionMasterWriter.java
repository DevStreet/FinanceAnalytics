package com.opengamma.integration.copiernew.region;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.id.ExternalIdSearch;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.master.region.RegionDocument;
import com.opengamma.master.region.RegionMaster;
import com.opengamma.master.region.RegionSearchRequest;
import com.opengamma.master.region.RegionSearchResult;
import com.opengamma.master.region.ManageableRegion;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.beancompare.BeanCompare;
import com.opengamma.util.beancompare.BeanDifference;

import javax.time.calendar.ZonedDateTime;
import java.io.IOException;
import java.util.List;

public class RegionMasterWriter implements Writeable<ManageableRegion> {

  RegionMaster _regionMaster;
  private BeanCompare _beanCompare;

  public RegionMasterWriter(RegionMaster regionMaster) {
    ArgumentChecker.notNull(regionMaster, "regionMaster");
    _regionMaster = regionMaster;
    _beanCompare = new BeanCompare();
  }

  @Override
  public void addOrUpdate(ManageableRegion region) {
    ArgumentChecker.notNull(region, "region");

    // Clean unique id (should really be done in reader)
    region.setUniqueId(null);

    RegionSearchRequest searchReq = new RegionSearchRequest();
    searchReq.setName(region.getName());
    searchReq.setVersionCorrection(VersionCorrection.ofVersionAsOf(ZonedDateTime.now())); // valid now
    ExternalIdSearch regionIdSearch = new ExternalIdSearch(region.getExternalIdBundle());  // match any one of the IDs
    searchReq.setExternalIdSearch(regionIdSearch);
    RegionSearchResult searchResult = _regionMaster.search(searchReq);

    if (searchResult.getDocuments().size() == 1) {
      ManageableRegion foundRegion = searchResult.getFirstRegion();
      List<BeanDifference<?>> differences;
      try {
        differences = _beanCompare.compare(foundRegion, region);
      } catch (Exception e) {
        throw new OpenGammaRuntimeException("Error comparing regions " + region, e);
      }
      if (differences.size() == 0 || (differences.size() == 1 && differences.get(0).getProperty().propertyType() == UniqueId.class)) {
        // It's already there, don't update or add it
      } else {
        RegionDocument updateDoc = new RegionDocument(region);
        updateDoc.setUniqueId(foundRegion.getUniqueId());
        RegionDocument result = _regionMaster.update(updateDoc);
      }
    } else {
      // Not found or ambiguous, so add it
      RegionDocument addDoc = new RegionDocument(region);
      RegionDocument result = _regionMaster.add(addDoc);
    }
  }

  @Override
  public void addOrUpdate(Iterable<ManageableRegion> data) {
    for (ManageableRegion datum : data) {
      addOrUpdate(datum);
    }
  }

  @Override
  public void flush() throws IOException {
    // No action
  }
}
