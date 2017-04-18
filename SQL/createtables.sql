--
--	Script to create the tables for the symphony database.
--	This script is used to setup the symphony database tables under SQL.
--
--
--	$Id:	createtables.sql, v 1.0.0.0 2001/11/12 R. Dyer
--
--	Drop tables

DROP TABLE IF EXISTS  Movements;
DROP TABLE IF EXISTS  Movement;
DROP TABLE IF EXISTS  Lastperformed;
DROP TABLE IF EXISTS  ConcertSoloist;
DROP TABLE IF EXISTS  Soloist;
DROP TABLE IF EXISTS  ConcertCompositions;
DROP TABLE IF EXISTS  Composition;
DROP TABLE IF EXISTS  Concert;
DROP TABLE IF EXISTS  Conductor;
DROP TABLE IF EXISTS  ConcertSeason;

-- TABLE: ConcertSeason
-- Each concert season has an opening date and consists
-- 	of any number of concerts
CREATE TABLE ConcertSeason (
		openingDate DATE,
	CONSTRAINT ConcertSeasonPK primary key (openingdate));


-- TABLE: Conductor
-- A conductor conducts one or many concerts, but
-- 	only one conductor per concert
CREATE TABLE Conductor (
		ID VARCHAR(50),
		name VARCHAR(50),
			CONSTRAINT ConductorPK primary key (ID)
		);


-- TABLE: Concert
-- A concert is scheduled for only one particular concert season.
-- A concert occurs in only one concert season.  A concert occurs
-- for a particular concert season if it's season
CREATE TABLE Concert (
		concertNumber INTEGER,
		concertdate DATE,
		conductorID VARCHAR(50),
		season DATE,
			CONSTRAINT ConcertPK primary key (concertNumber),
			CONSTRAINT ConcertFK1 FOREIGN KEY (season)
				REFERENCES ConcertSeason (openingdate),
			CONSTRAINT ConcertFK2 FOREIGN KEY (conductorID)
				REFERENCES Conductor (ID)
		);


-- TABLE: Composition
-- Concerts consist of a number of compositions
-- Compositions can be performed in different conerts
-- A composer creates a composition and gives it a name
CREATE TABLE Composition (
		composer VARCHAR(50),
		compositionName VARCHAR(50),
			CONSTRAINT CompositionPK primary key (composer,compositionName)
		);


-- TABLE: ConcertCompositions
-- Compsotions for a particular concert.
CREATE TABLE ConcertCompositions (
		concertNumber INTEGER,
		composer VARCHAR(50),
		compositionname VARCHAR(50),
			CONSTRAINT ConcertCompositionsPK primary key (concertNumber, composer, compositionName),
			CONSTRAINT ConcertCompositionsFK1 FOREIGN KEY (concertNumber)
				REFERENCES Concert (concertNumber),
			CONSTRAINT ConcertCompositionsFK2 FOREIGN KEY (composer, compositionName)
				REFERENCES Composition (composer, compositionName)
		);

-- TABLE: Soloist
-- A soloist performs a composition at a concert
-- The system maintains a "date last performed" for each composition
-- 	a soloist performs
CREATE TABLE Soloist (
		ID VARCHAR(50),
		name VARCHAR(50),
			CONSTRAINT SoloistPK primary key (ID)
		);

-- TABLE: ConcertSoloist
-- A soloist performs a composition at a concert
-- The system maintains a "date last performed" for each composition
-- 	a soloist performs
CREATE TABLE ConcertSoloist (
		concertNumber INTEGER,
		soloistID VARCHAR(50),
			CONSTRAINT ConcertSoloistPK primary key (concertNumber, soloistID)
		);


-- TABLE: Lastperformed
-- The system maintains a "date last performed" for each composition
-- 	a soloist performs
CREATE TABLE Lastperformed (
		soloistID VARCHAR(50),
		composer VARCHAR(50),
		compositionname VARCHAR(50),
		performancedate DATE,
			CONSTRAINT LastperformedPK
				primary key (soloistid, composer, compositionname),
			CONSTRAINT LastperformedFK1 FOREIGN KEY (soloistID)
				REFERENCES Soloist (ID),
			CONSTRAINT LastperformedFK2 FOREIGN KEY (composer, compositionname)
				REFERENCES Composition (composer, compositionname)
		);


-- TABLE: Movement
-- A composer creates a movement, gives it a number and name
CREATE TABLE Movement (
		movementNumber INTEGER,
		movementName VARCHAR(50),
			CONSTRAINT MovementPK primary key (movementNumber, movementName)
		);


--	TABLE: Movements
-- Compositions are made up of any number of movements
CREATE TABLE Movements (
		composer VARCHAR(50),
		compositionName VARCHAR(50),
		movementNumber INTEGER,
		movementName VARCHAR(50),
			CONSTRAINT MovementsPK
				PRIMARY KEY (composer,compositionName,movementNumber, movementName),
			CONSTRAINT MovementsFK1 FOREIGN KEY (composer,compositionName)
				REFERENCES Composition (composer,compositionName),
			CONSTRAINT MovementsFK2 FOREIGN KEY (movementNumber, movementName)
				REFERENCES Movement(movementNumber, movementName)
		);

--
--	End of createtables.sql
--
