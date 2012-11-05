package com.opengamma.integration.copiernew.security;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.id.ExternalIdSearch;
import com.opengamma.id.ExternalIdSearchType;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.master.security.*;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.beancompare.BeanCompare;
import com.opengamma.util.beancompare.BeanDifference;
import org.hsqldb.lib.ClosableByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.time.calendar.ZonedDateTime;
import java.io.Flushable;
import java.io.IOException;
import java.util.List;

public class SecurityMasterWriter implements Writeable<ManageableSecurity> {

  private static final Logger s_logger = LoggerFactory.getLogger(Writeable.class);

  private static final String TEMPLATE_NAME = "<name>";

  SecurityMaster _securityMaster;
  private BeanCompare _beanCompare;
  String _nameTemplate;

  public SecurityMasterWriter(SecurityMaster securityMaster) {
    this(securityMaster, null);
  }

  public SecurityMasterWriter(SecurityMaster securityMaster, String nameTemplate) {
    ArgumentChecker.notNull(securityMaster, "securityMaster");
    _securityMaster = securityMaster;
    _beanCompare = new BeanCompare();
    _nameTemplate = nameTemplate;
    if (_nameTemplate != null && !_nameTemplate.contains(TEMPLATE_NAME)) {
      _nameTemplate += TEMPLATE_NAME;
    }
  }

  @Override
  public void addOrUpdate(ManageableSecurity security) {
    if (security == null) {
      return;
    }

    // Clear unique ID (should really be done in reader)
    security.setUniqueId(null);

    // Rename exchange as per supplied template
    if (_nameTemplate != null) {
      security.setName(_nameTemplate.replace(TEMPLATE_NAME, security.getName()));
    }

    SecuritySearchRequest searchReq = new SecuritySearchRequest();
    ExternalIdSearch idSearch = new ExternalIdSearch(security.getExternalIdBundle());  // match any one of the IDs
    idSearch.setSearchType(ExternalIdSearchType.EXACT); // TODO should we match ALL external IDs?
    searchReq.setExternalIdSearch(idSearch);
    searchReq.setVersionCorrection(VersionCorrection.ofVersionAsOf(ZonedDateTime.now())); // valid now
    searchReq.setName(security.getName()); // TODO should we require a security name match or not?
    searchReq.setSecurityType(security.getSecurityType());
    searchReq.setFullDetail(true);
    searchReq.setSortOrder(SecuritySearchSortOrder.VERSION_FROM_INSTANT_DESC);
    SecuritySearchResult searchResult = _securityMaster.search(searchReq);
    ManageableSecurity foundSecurity = searchResult.getFirstSecurity();
    if (foundSecurity != null) {
      List<BeanDifference<?>> differences;
      try {
        differences = _beanCompare.compare(foundSecurity, security);
      } catch (Exception e) {
        throw new OpenGammaRuntimeException("Error comparing securities with ID bundle " + security.getExternalIdBundle(), e);
      }
      // Problem since details are not considered here
      if (differences.size() == 0 || (differences.size() == 1 && differences.get(0).getProperty().propertyType() == UniqueId.class)) {
        // It's already there, don't update or add it
        s_logger.info("Not updating existing " + foundSecurity);
      } else {
        s_logger.info("Updating " + foundSecurity + " to " + security);
        SecurityDocument updateDoc = new SecurityDocument(security);
        updateDoc.setUniqueId(foundSecurity.getUniqueId());
        SecurityDocument result = _securityMaster.update(updateDoc);
      }
    } else {
      // Not found, so add it
      s_logger.info("Adding " + security);
      SecurityDocument addDoc = new SecurityDocument(security);
      SecurityDocument result = _securityMaster.add(addDoc);
    }
  }

  @Override
  public void addOrUpdate(Iterable<ManageableSecurity> data) {
    for (ManageableSecurity datum : data) {
      addOrUpdate(datum);
    }
  }

  @Override
  public void flush() throws IOException {
    // No action
  }
}
