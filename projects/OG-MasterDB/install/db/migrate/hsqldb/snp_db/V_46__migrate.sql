START TRANSACTION;
  UPDATE snp_schema_version SET version_value='46' WHERE version_key='schema_patch';
    
  ALTER  TABLE snp_snapshot ADD CONSTRAINT snp_chk_ver_corr_order CHECK (ver_from_instant <= corr_from_instant);
  
COMMIT;