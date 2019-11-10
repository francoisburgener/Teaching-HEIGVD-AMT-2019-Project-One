USE `amt-db`;

LOAD DATA INFILE '../../../docker-entrypoint-initdb.d/6-amt-trip-data.csv'
INTO TABLE 	Trip
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS
(User_idUser, Country_idCountry,Reason_idReason,visited, date);