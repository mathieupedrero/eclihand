/*DROP INSTRUCTIONS*/
DROP TABLE IF EXISTS AFF_PLAYER_TEAM_AFFILIATION;
DROP TABLE IF EXISTS TEA_TEAM;
DROP TABLE IF EXISTS PLA_PLAYER;
DROP TABLE IF EXISTS PER_PERSON;

/*CREATE INSTRUCTIONS*/
CREATE TABLE PER_PERSON (
ID INTEGER,
PER_FIRST_NAME VARCHAR(64),
PER_LAST_NAME VARCHAR(64),
PER_BIRTH_DATE DATE,
PER_GENDER VARCHAR(64),
CONSTRAINT PK_PER PRIMARY KEY (ID)
);
CREATE TABLE PLA_PLAYER (
ID INTEGER,
PLA_PER_ID INTEGER,
CONSTRAINT PK_PLA PRIMARY KEY (ID),
CONSTRAINT FK_PLA__PER FOREIGN KEY (PLA_PER_ID) REFERENCES PER_PERSON (ID)
);
CREATE TABLE TEA_TEAM (
ID INTEGER,
TEA_YEAR INTEGER,
TEA_GENDER VARCHAR(64),
CONSTRAINT PK_TEA PRIMARY KEY (ID)
);
CREATE TABLE AFF_PLAYER_TEAM_AFFILIATION (
AFF_PLA_ID INTEGER,
AFF_TEA_ID INTEGER,
CONSTRAINT PK_AFF PRIMARY KEY (AFF_PLA_ID,AFF_TEA_ID),
CONSTRAINT FK_AFF__PLA FOREIGN KEY (AFF_PLA_ID) REFERENCES PLA_PLAYER (ID),
CONSTRAINT FK_AFF__TEA FOREIGN KEY (AFF_TEA_ID) REFERENCES TEA_TEAM (ID)
);

commit;