-- Regular Tables --

CREATE TABLE IF NOT EXISTS Protocol (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL,
    description VARCHAR(256) NULL,
    url VARCHAR(512) NULL,
    readOnlyUrl VARCHAR(512) NULL,
    PRIMARY KEY (id)
) CHARACTER SET utf8 COLLATE utf8_bin;

CREATE TABLE IF NOT EXISTS Plenum (
    id INT NOT NULL AUTO_INCREMENT,
    reference VARCHAR(37) NOT NULL,
    name VARCHAR(32) NOT NULL,
    description VARCHAR(256) NULL,
    start DATETIME NULL,
    `end` DATETIME NULL,
    protocolId INT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (reference)
) CHARACTER SET utf8 COLLATE utf8_bin;

CREATE TABLE IF NOT EXISTS Speaker (
    id INT NOT NULL AUTO_INCREMENT,
    plenumId INT NOT NULL,
    number INT NOT NULL,
    displayName VARCHAR(64) NOT NULL,
    since DATETIME NULL,
    until DATETIME NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (plenumId) REFERENCES Plenum(id),
    UNIQUE KEY (plenumId, number),
    UNIQUE KEY (plenumId, displayName)
) CHARACTER SET utf8 COLLATE utf8_bin;

CREATE TABLE IF NOT EXISTS SpeakList (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL,
    description VARCHAR(256) NULL,
    speakingSpeakerId INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (speakingSpeakerId) REFERENCES Speaker(id)
) CHARACTER SET utf8 COLLATE utf8_bin;

CREATE TABLE IF NOT EXISTS SpeakListEntry (
    id INT NOT NULL AUTO_INCREMENT,
    speakListId INT NOT NULL,
    speakerId INT NOT NULL,
    priority INT NOT NULL DEFAULT 0,
    spoke BOOLEAN NOT NULL DEFAULT FALSE,
    `from` DATETIME NULL,
    `to` DATETIME NULL,
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



-- Views ---

CREATE OR REPLACE VIEW Plenum_Resolved AS
    SELECT
        Plenum.id AS id,
        Plenum.reference AS reference,
        Plenum.name AS name,
        Plenum.description AS description,
        Plenum.start AS start,
        Plenum.end AS end,
        Speaker.id AS speakerId,
        Speaker.plenumId AS speakerPlenumId,
        Speaker.number AS speakerNumber,
        Speaker.displayName AS speakerDisplayName,
        Speaker.since AS speakerSince,
        Speaker.until AS speakerUntil
    FROM
        Plenum
    LEFT JOIN Speaker ON Plenum.id = Speaker.plenumId
;

CREATE OR REPLACE VIEW SpeakList_Resolved AS
    SELECT
        SpeakList.id AS id,
        SpeakList.name AS name,
        SpeakList.description AS description,
        SpeakingSpeakListEntry.speakListId AS speakerSpeakListId,
        SpeakingSpeakListEntry.priority AS speakingSpeakListEntryPriority,
        SpeakingSpeakListEntry.spoke AS speakingSpeakListEntrySpoke,
        SpeakingSpeakListEntry.from AS speakingSpeakListEntryFrom,
        SpeakingSpeakListEntry.to AS speakingSpeakListEntryTo,
        SpeakingSpeakListEntry.speakerId AS speakingSpeakerSpeakListEntryId,
        SpeakingSpeaker.id AS speakingSpeakerId,
        SpeakingSpeaker.number AS speakingSpeakerNumber,
        SpeakingSpeaker.displayName AS speakingSpeakerDisplayName,
        SpeakingSpeaker.since AS speakingSpeakerSince,
        SpeakingSpeaker.until AS speakingSpeakerUntil,
        SpeakListEntry.speakListId AS speakListEntrySpeakListId,
        SpeakListEntry.id AS speakListEntryId,
        SpeakListEntry.priority AS speakListEntryPriority,
        SpeakListEntry.spoke AS speakListEntrySpoke,
        SpeakListEntry.from AS speakListEntryFrom,
        SpeakListEntry.to AS speakListEntryTo,
        SpeakListEntry.id AS speakerSpeakListEntryId,
        Speaker.id AS speakListSpeakerId,
        Speaker.number AS speakListSpeakerNumber,
        Speaker.displayName AS speakListSpeakerDisplayName,
        Speaker.since AS speakListSpeakerSince,
        Speaker.until AS speakListSpeakerUntil
    FROM
        SpeakList
    LEFT JOIN SpeakListEntry AS SpeakingSpeakListEntry ON SpeakList.id = SpeakingSpeakListEntry.speakListId
    LEFT JOIN Speaker AS SpeakingSpeaker ON SpeakingSpeakListEntry.speakerId = SpeakingSpeaker.id
    LEFT JOIN SpeakListEntry AS SpeakListEntry ON SpeakList.id = SpeakListEntry.speakListId
    LEFT JOIN Speaker AS Speaker ON SpeakListEntry.speakerId = Speaker.id
;
