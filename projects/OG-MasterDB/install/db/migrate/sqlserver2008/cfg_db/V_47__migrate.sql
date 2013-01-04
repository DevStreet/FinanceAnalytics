BEGIN TRAN;

  UPDATE cfg_schema_version SET version_value='47' WHERE version_key='schema_patch';

  CREATE TABLE cfg_type (
    config_type VARCHAR(255) PRIMARY KEY NOT NULL,
    CONSTRAINT cfg_fk_type2type FOREIGN KEY (config_type) REFERENCES cfg_type (config_type)
  ) WITHOUT oids;
  
  INSERT INTO cfg_type (config_type) (SELECT DISTINCT config_type FROM cfg_config);
COMMIT;
