ALTER TABLE userairline ADD COLUMN role VARCHAR (50);
UPDATE  userairline SET role = 'user' WHERE role_id = 1;
UPDATE  userairline SET role = 'admin' WHERE role_id = 2;
ALTER TABLE userairline DROP COLUMN role_id;

ALTER TABLE SIT ADD COLUMN levelticket VARCHAR (50);
UPDATE sit SET levelticket = 'Econom' WHERE levelticket_id=1;
UPDATE sit SET levelticket = 'Business' WHERE levelticket_id=2;
ALTER TABLE sit DROP COLUMN levelticket_id;

ALTER TABLE flightprice ADD COLUMN levelticket VARCHAR (50);
UPDATE flightprice SET levelticket = 'Econom' WHERE levelticket_id=1;
UPDATE flightprice SET levelticket = 'Business' WHERE levelticket_id=2;
ALTER TABLE flightprice DROP COLUMN levelticket_id;

DROP TABLE levelticket;
DROP TABLE role;

ALTER TABLE userairline ADD COLUMN LASTNAME VARCHAR(50);
ALTER TABLE userairline ADD COLUMN FIRSTNAME VARCHAR(50);
ALTER TABLE userairline ADD COLUMN PATRONYM VARCHAR(50);
ALTER TABLE userairline ADD COLUMN PASSPORTNUMBER VARCHAR(50);

UPDATE userairline SET LASTNAME=p.lastname FROM (SELECT * FROM passenger pas inner join userairline u on(pas.passenger_id=u.passenger_id)) as p ;
