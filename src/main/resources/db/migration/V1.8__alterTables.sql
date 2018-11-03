ALTER TABLE flight ADD COLUMN datetime TIMESTAMP;

UPDATE flight SET datetime='2018-10-01 08:05' WHERE flight_id=1;
UPDATE flight SET datetime='2018-10-02 16:40' WHERE flight_id=2;
