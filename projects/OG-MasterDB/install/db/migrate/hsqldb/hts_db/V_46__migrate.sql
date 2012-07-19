START TRANSACTION;
  UPDATE hts_schema_version SET version_value='46' WHERE version_key='schema_patch';
    
  ALTER  TABLE hts_document ADD CONSTRAINT hts_chk_ver_corr_order CHECK (ver_from_instant <= corr_from_instant);
  
COMMIT;