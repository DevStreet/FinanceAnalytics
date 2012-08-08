/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.security;

import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.master.security.SecuritySearchRequest;
import com.opengamma.master.security.SecuritySearchResult;
import com.opengamma.util.ArgumentChecker;

import java.util.Iterator;

public class SecurityMasterReader implements Iterable<ManageableSecurity> {

  private SecuritySearchResult _securitySearchResult;

  public SecurityMasterReader(SecurityMaster securityMaster) {
    this(securityMaster, null);
  }

  public SecurityMasterReader(SecurityMaster securityMaster, SecuritySearchRequest securitySearchRequest) {
    ArgumentChecker.notNull(securityMaster, "securityMaster");
    if (securitySearchRequest == null) {
      securitySearchRequest = new SecuritySearchRequest();
    }
    _securitySearchResult = securityMaster.search(securitySearchRequest);
  }

  @Override
  public Iterator<ManageableSecurity> iterator() {
    return _securitySearchResult.getSecurities().iterator();
  }
}
