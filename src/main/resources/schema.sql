-- Regular Tables --

CREATE TABLE IF NOT EXISTS Protocol (
	id INT NOT NULL AUTO_INCREMENT,
	url VARCHAR(512) NULL,
	readOnlyUrl VARCHAR(512) NULL,
	PRIMARY KEY (id)
) CHARACTER SET utf8 COLLATE utf8_bin;

CREATE TABLE IF NOT EXISTS Plenum (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(32) NOT NULL,
	description VARCHAR(256) NULL,
	start DATE NULL,
	end DATE NULL,
	protocolId INT NULL,
	PRIMARY KEY (id)
) CHARACTER SET utf8 COLLATE utf8_bin;

CREATE TABLE IF NOT EXISTS Speaker (
	id INT NOT NULL AUTO_INCREMENT,
	plenumId INT NOT NULL,
	displayName VARCHAR(64) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (plenumId) REFERENCES Plenum(id)
) CHARACTER SET utf8 COLLATE utf8_bin;

CREATE TABLE IF NOT EXISTS SpeakList (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(32) NOT NULL,
	description VARCHAR(256) NULL,
	PRIMARY KEY (id)
) CHARACTER SET utf8 COLLATE utf8_bin;

CREATE TABLE IF NOT EXISTS SpeakListEntry (
	id INT NOT NULL AUTO_INCREMENT,
	speakListId INT NOT NULL,
	speakerId INT NOT NULL,
	priority INT NOT NULL DEFAULT 0,
	PRIMARY KEY (id),
	FOREIGN KEY (speakListId) REFERENCES SpeakList(id),
	FOREIGN KEY (speakerId) REFERENCES Speaker(id)
) CHARACTER SET utf8 COLLATE utf8_bin;



-- Reference Tables --

CREATE TABLE IF NOT EXISTS Plenum_Protocol (
	plenumId INT NOT NULL,
	protocolId INT NOT NULL,
	UNIQUE KEY (plenumId, protocolId),
	FOREIGN KEY (plenumId) REFERENCES Plenum(id),
	FOREIGN KEY (protocolId) REFERENCES Protocol(id)
) CHARACTER SET utf8 COLLATE utf8_bin;

CREATE TABLE IF NOT EXISTS Plenum_SpeakList (
	plenumId INT NOT NULL,
	speakListId INT NOT NULL,
	UNIQUE KEY (plenumId, speakListId),
	FOREIGN KEY (plenumId) REFERENCES Plenum(id),
	FOREIGN KEY (speakListId) REFERENCES SpeakList(id)
) CHARACTER SET utf8 COLLATE utf8_bin;