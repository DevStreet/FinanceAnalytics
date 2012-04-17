-- CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;
CREATE PROCEDURE nextval_hibernate_sequence AS
BEGIN
  DECLARE @NewSeqValue INT
  SET NOCOUNT ON
  INSERT INTO hibernate_sequence (SeqVal) VALUES ('a')
  SET @NewSeqValue = scope_identity()
  DELETE FROM hibernate_sequence WITH (READPAST)
  RETURN @NewSeqValue
END
CREATE TABLE hibernate_sequence (
  SeqID INT identity(1,1) PRIMARY KEY,
  SeqVal VARCHAR(1)
)

