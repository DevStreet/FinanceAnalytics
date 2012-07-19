BEGIN TRAN;
  UPDATE pos_schema_version SET version_value='47' WHERE version_key='schema_patch';
    
  ALTER  TABLE pos_position ADD CONSTRAINT pos_chk_ver_corr_order CHECK (ver_from_instant <= corr_from_instant);
  
COMMIT;