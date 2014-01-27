

--CREATE INSTRUCTIONS
CREATE SEQUENCE OBJ_OBJECT_ID_SEQ;

CREATE TABLE OBJ_OBJECT (
ID INTEGER NOT NULL,
OBJ_INDEX TEXT,
CONSTRAINT PK_OBJ PRIMARY KEY (ID)
);

CREATE TABLE ILL_ILLUSTRABLE (
ID INTEGER NOT NULL,
CONSTRAINT PK_ILL PRIMARY KEY (ID),
CONSTRAINT FK_ILL__OBJ FOREIGN KEY (ID) REFERENCES OBJ_OBJECT (ID)
);

CREATE TABLE PER_PERSON (
ID INTEGER NOT NULL,
PER_FIRST_NAME VARCHAR(64) NOT NULL,
PER_LAST_NAME VARCHAR(64) NOT NULL,
PER_BIRTH_DATE DATE,
PER_GENDER VARCHAR(64),
CONSTRAINT PK_PER PRIMARY KEY (ID),
CONSTRAINT FK_PER__ILL FOREIGN KEY (ID) REFERENCES ILL_ILLUSTRABLE (ID)
);

CREATE TABLE PLA_PLAYER (
ID INTEGER NOT NULL,
PLA_PER_ID INTEGER NOT NULL,
CONSTRAINT PK_PLA PRIMARY KEY (ID),
CONSTRAINT FK_PLA__PER FOREIGN KEY (PLA_PER_ID) REFERENCES PER_PERSON (ID),
CONSTRAINT FK_PLA__ILL FOREIGN KEY (ID) REFERENCES ILL_ILLUSTRABLE (ID)
);

CREATE TABLE TEA_TEAM (
ID INTEGER NOT NULL,
TEA_YEAR INTEGER NOT NULL,
TEA_GENDER VARCHAR(64) NOT NULL,
CONSTRAINT PK_TEA PRIMARY KEY (ID),
CONSTRAINT FK_TEA__ILL FOREIGN KEY (ID) REFERENCES ILL_ILLUSTRABLE (ID)
);

CREATE TABLE PLA_PLAYER_TEA_TEAM (
PLA_ID INTEGER NOT NULL,
TEA_ID INTEGER NOT NULL,
CONSTRAINT PK_PLA_TEA PRIMARY KEY (PLA_ID,TEA_ID),
CONSTRAINT FK_PLA_TEA__PLA FOREIGN KEY (PLA_ID) REFERENCES PLA_PLAYER (ID),
CONSTRAINT FK_PLA_TEA__TEA FOREIGN KEY (TEA_ID) REFERENCES TEA_TEAM (ID)
);

CREATE TABLE USR_USER (
ID INTEGER NOT NULL,
USR_LOGIN VARCHAR(32) NOT NULL,
USR_PASSWORD VARCHAR(40) NOT NULL,
CONSTRAINT PK_USR PRIMARY KEY (ID),
CONSTRAINT FK_USR__ILL FOREIGN KEY (ID) REFERENCES ILL_ILLUSTRABLE (ID)
);

CREATE TABLE PRF_PROFILE (
ID INTEGER NOT NULL,
PRF_NAME VARCHAR(16) NOT NULL,
CONSTRAINT PK_PRF PRIMARY KEY (ID),
CONSTRAINT FK_PRF__ILL FOREIGN KEY (ID) REFERENCES ILL_ILLUSTRABLE (ID)
);

CREATE TABLE USR_USER_PRF_PROFILE (
USR_ID INTEGER NOT NULL,
PRF_ID INTEGER NOT NULL,
CONSTRAINT PK_USR_PRF PRIMARY KEY (USR_ID,PRF_ID),
CONSTRAINT FK_USR_PRF__USR FOREIGN KEY (USR_ID) REFERENCES USR_USER (ID),
CONSTRAINT FK_USR_PRF__PRF FOREIGN KEY (PRF_ID) REFERENCES PRF_PROFILE (ID)
);

CREATE TABLE AUT_AUTHORIZATION (
ID INTEGER NOT NULL,
AUT_CREDENTIAL VARCHAR(32) NOT NULL,
CONSTRAINT PK_AUT PRIMARY KEY (ID),
CONSTRAINT FK_AUT__OBJ FOREIGN KEY (ID) REFERENCES OBJ_OBJECT (ID)
);

CREATE TABLE PRF_PROFILE_AUT_AUTHORIZATION (
PRF_ID INTEGER NOT NULL,
AUT_ID INTEGER NOT NULL,
CONSTRAINT PK_PRF_AUT PRIMARY KEY (PRF_ID,AUT_ID),
CONSTRAINT FK_PRF_AUT__PRF FOREIGN KEY (PRF_ID) REFERENCES PRF_PROFILE (ID),
CONSTRAINT FK_PRF_AUT__AUT FOREIGN KEY (AUT_ID) REFERENCES AUT_AUTHORIZATION (ID)
);



commit;