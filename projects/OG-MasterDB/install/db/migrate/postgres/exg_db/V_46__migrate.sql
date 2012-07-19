START TRANSACTION;
  UPDATE hol_schema_version SET version_value='46' WHERE version_key='schema_patch';
  UPDATE exg_schema_version SET version_value='46' WHERE version_key='schema_patch';
    
  ALTER  TABLE exg_exchange ADD CONSTRAINT exg_chk_ver_corr_order CHECK (ver_from_instant <= corr_from_instant);
  ALTER  TABLE hol_holiday ADD CONSTRAINT hol_chk_ver_corr_order CHECK (ver_from_instant <= corr_from_instant);
COMMIT;