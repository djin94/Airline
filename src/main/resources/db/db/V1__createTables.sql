CREATE TABLE AIRLINE (
  AIRLINE_ID SERIAL PRIMARY KEY,
  NAME       VARCHAR(250)
);

CREATE TABLE PLANE (
  PLANE_ID SERIAL PRIMARY KEY,
  NAME     VARCHAR(100),
  CAPACITY INTEGER
);

CREATE TABLE PASSENGER (
  PASSENGER_ID   SERIAL PRIMARY KEY,
  LASTNAME       VARCHAR(50),
  FIRSTNAME      VARCHAR(50),
  PATRONYM       VARCHAR(50),
  PASSPORTNUMBER VARCHAR(50)
);

CREATE TABLE AIRPORT (
  AIRPORT_ID SERIAL PRIMARY KEY,
  NAME       VARCHAR(250)
);

CREATE TABLE FLIGHT (
  FLIGHT_ID            SERIAL PRIMARY KEY,
  NUMBER               VARCHAR(50),
  PLANE_ID             INTEGER REFERENCES PLANE (PLANE_ID),
  DEPARTURE_AIRPORT_ID INTEGER REFERENCES AIRPORT (AIRPORT_ID),
  ARRIVAL_AIRPORT_ID   INTEGER REFERENCES AIRPORT (AIRPORT_ID),
  AIRLINE_ID           INTEGER REFERENCES AIRLINE (AIRLINE_ID),
  DATE                 DATE,
  TIME                 TIME
);

CREATE TABLE FLIGHTPRICE (
  FLIGHTPRICE_ID SERIAL PRIMARY KEY,
  FLIGHT_ID      INTEGER REFERENCES FLIGHT (FLIGHT_ID),
  LEVEL          VARCHAR(20),
  PRICE          INTEGER
);

CREATE TABLE TICKET (
  NUMBER         SERIAL PRIMARY KEY,
  FLIGHT_ID      INTEGER REFERENCES FLIGHT (FLIGHT_ID),
  FLIGHTPRICE_ID INTEGER REFERENCES FLIGHTPRICE (FLIGHTPRICE_ID),
  PLACE          VARCHAR(20),
  PASSENGER_ID   INTEGER REFERENCES PASSENGER (PASSENGER_ID)
);

