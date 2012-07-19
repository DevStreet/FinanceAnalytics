BEGIN TRAN;
  UPDATE sec_schema_version SET version_value='53' WHERE version_key='schema_patch';
    
  ALTER  TABLE sec_security ADD CONSTRAINT sec_chk_ver_corr_order CHECK (ver_from_instant <= corr_from_instant);
  
COMMIT;