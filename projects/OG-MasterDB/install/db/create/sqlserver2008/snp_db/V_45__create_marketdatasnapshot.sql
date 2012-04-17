
-- create-db-marketdatasnapshot.sql

-- MarketDataSnapshotMaster design has one document
--  snapshot
-- bitemporal versioning exists at the document level
-- each time a document is changed, a new row is written
-- with only the end instant being changed on the old row

CREATE TABLE snp_schema_version (
    version_key VARCHAR(32) NOT NULL,
    version_value VARCHAR(255) NOT NULL
);
INSERT INTO snp_schema_version (version_key, version_value) VALUES ('schema_patch', '45');

-- CREATE SEQUENCE snp_snapshot_seq
--    START WITH 1000 INCREMENT BY 1 NO CYCLE;
-- "as bigint" required by Derby/HSQL, not accepted by Postgresql
CREATE TABLE snp_snapshot_seq (
  SeqID INT identity(1,1) PRIMARY KEY,
  SeqVal VARCHAR(1)
)
CREATE PROCEDURE nextval_snp_snapshot_seq AS
BEGIN
  DECLARE @NewSeqValue INT
  SET NOCOUNT ON
  INSERT INTO snp_snapshot_seq (SeqVal) VALUES ('a')
  SET @NewSeqValue = scope_identity()
  DELETE FROM snp_snapshot_seq WITH (READPAST)
  RETURN @NewSeqValue
END


CREATE TABLE snp_snapshot (
    id bigint NOT NULL,
    oid bigint NOT NULL,
    ver_from_instant DATETIME NOT NULL,
    ver_to_instant DATETIME NOT NULL,
    corr_from_instant DATETIME NOT NULL,
    corr_to_instant DATETIME NOT NULL,
    name varchar(255) NOT NULL,
    uname AS UPPER(name),
    time_zone varchar(255),
    detail IMAGE NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT snp_chk_snapshot_ver_order CHECK (ver_from_instant <= ver_to_instant),
    CONSTRAINT snp_chk_snapshot_corr_order CHECK (corr_from_instant <= corr_to_instant)
);
CREATE INDEX ix_snp_snapshot_oid ON snp_snapshot(oid);
CREATE INDEX ix_snp_snapshot_ver_from_instant ON snp_snapshot(ver_from_instant);
CREATE INDEX ix_snp_snapshot_ver_to_instant ON snp_snapshot(ver_to_instant);
CREATE INDEX ix_snp_snapshot_corr_from_instant ON snp_snapshot(corr_from_instant);
CREATE INDEX ix_snp_snapshot_corr_to_instant ON snp_snapshot(corr_to_instant);
CREATE INDEX ix_snp_snapshot_name ON snp_snapshot(name);
CREATE INDEX ix_snp_snapshot_nameu ON snp_snapshot(uname);
