BEGIN TRAN;
  UPDATE prt_schema_version SET version_value='47' WHERE version_key='schema_patch';
    
  ALTER  TABLE prt_portfolio ADD CONSTRAINT prt_chk_ver_corr_order CHECK (ver_from_instant <= corr_from_instant);
  
COMMIT;