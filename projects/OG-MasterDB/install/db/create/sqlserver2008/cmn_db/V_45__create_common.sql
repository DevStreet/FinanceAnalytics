-- CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;
CREATE TABLE hibernate_sequence (
  SeqID INT identity(1,1) PRIMARY KEY,
  SeqVal VARCHAR(1)
)

