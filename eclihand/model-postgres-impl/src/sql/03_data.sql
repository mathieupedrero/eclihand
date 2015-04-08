INSERT INTO OBJ_OBJECT VALUES (1,'Super Utilisateur');
INSERT INTO OBJ_OBJECT VALUES (2,'Profil Admin');
INSERT INTO OBJ_OBJECT VALUES (3,'Profil Président');
INSERT INTO OBJ_OBJECT VALUES (4,'Profil Staff');
INSERT INTO OBJ_OBJECT VALUES (5,'Profil Joueur');
INSERT INTO OBJ_OBJECT VALUES (6,'Profil Supporter');
INSERT INTO OBJ_OBJECT VALUES (7,'Profil Visiteur');
INSERT INTO OBJ_OBJECT VALUES (8,'PLAYER_VIEW');
INSERT INTO OBJ_OBJECT VALUES (9,'PLAYER_EDIT');
INSERT INTO OBJ_OBJECT VALUES (10,'TEAM_VIEW');
INSERT INTO OBJ_OBJECT VALUES (11,'TEAM_EDIT');
INSERT INTO OBJ_OBJECT VALUES (12,'OWN_TEAM_PLAYER_VIEW');
INSERT INTO OBJ_OBJECT VALUES (13,'OWN_TEAM_PLAYER_EDIT');
INSERT INTO OBJ_OBJECT VALUES (14,'OWN_TEAM_VIEW');
INSERT INTO OBJ_OBJECT VALUES (15,'OWN_TEAM_EDIT');
INSERT INTO OBJ_OBJECT VALUES (16,'Utilisateur Invité');
INSERT INTO OBJ_OBJECT VALUES (17,'CONNECT');
INSERT INTO OBJ_OBJECT VALUES (18,'OWN_ACCOUNT_MANAGEMENT');

INSERT INTO ILL_ILLUSTRABLE VALUES (1);
INSERT INTO ILL_ILLUSTRABLE VALUES (2);
INSERT INTO ILL_ILLUSTRABLE VALUES (3);
INSERT INTO ILL_ILLUSTRABLE VALUES (4);
INSERT INTO ILL_ILLUSTRABLE VALUES (5);
INSERT INTO ILL_ILLUSTRABLE VALUES (6);
INSERT INTO ILL_ILLUSTRABLE VALUES (7);
INSERT INTO ILL_ILLUSTRABLE VALUES (16);

INSERT INTO USR_USER VALUES (1,'admin',encode(digest('admin' || digest('admin', 'sha1'), 'sha1'), 'base64'), 'ADMIN');
INSERT INTO USR_USER VALUES (16,'invite',encode(digest('invite' || digest('invite', 'sha1'), 'sha1'), 'base64'), 'GUEST');

INSERT INTO PRF_PROFILE VALUES (2,'ADMIN');
INSERT INTO PRF_PROFILE VALUES (3,'PRESIDENT');
INSERT INTO PRF_PROFILE VALUES (4,'STAFF');
INSERT INTO PRF_PROFILE VALUES (5,'JOUEUR');
INSERT INTO PRF_PROFILE VALUES (6,'SUPPORTER');
INSERT INTO PRF_PROFILE VALUES (7,'VISITEUR');

INSERT INTO USR_USER_PRF_PROFILE VALUES (1,2);
INSERT INTO USR_USER_PRF_PROFILE VALUES (16,7);

INSERT INTO AUT_AUTHORIZATION VALUES (8,'PLAYER_VIEW');
INSERT INTO AUT_AUTHORIZATION VALUES (9,'PLAYER_EDIT');
INSERT INTO AUT_AUTHORIZATION VALUES (10,'TEAM_VIEW');
INSERT INTO AUT_AUTHORIZATION VALUES (11,'TEAM_EDIT');
INSERT INTO AUT_AUTHORIZATION VALUES (12,'OWN_TEAM_PLAYER_VIEW');
INSERT INTO AUT_AUTHORIZATION VALUES (13,'OWN_TEAM_PLAYER_EDIT');
INSERT INTO AUT_AUTHORIZATION VALUES (14,'OWN_TEAM_VIEW');
INSERT INTO AUT_AUTHORIZATION VALUES (15,'OWN_TEAM_EDIT');
INSERT INTO AUT_AUTHORIZATION VALUES (17,'CONNECT');
INSERT INTO AUT_AUTHORIZATION VALUES (18,'OWN_ACCOUNT_MANAGEMENT');

INSERT INTO PRF_PROFILE_AUT_AUTHORIZATION VALUES (2,8);
INSERT INTO PRF_PROFILE_AUT_AUTHORIZATION VALUES (2,9);
INSERT INTO PRF_PROFILE_AUT_AUTHORIZATION VALUES (2,10);
INSERT INTO PRF_PROFILE_AUT_AUTHORIZATION VALUES (2,11);
INSERT INTO PRF_PROFILE_AUT_AUTHORIZATION VALUES (2,12);
INSERT INTO PRF_PROFILE_AUT_AUTHORIZATION VALUES (2,13);
INSERT INTO PRF_PROFILE_AUT_AUTHORIZATION VALUES (2,14);
INSERT INTO PRF_PROFILE_AUT_AUTHORIZATION VALUES (2,15);
INSERT INTO PRF_PROFILE_AUT_AUTHORIZATION VALUES (2,18);
INSERT INTO PRF_PROFILE_AUT_AUTHORIZATION VALUES (7,8);
INSERT INTO PRF_PROFILE_AUT_AUTHORIZATION VALUES (7,10);
INSERT INTO PRF_PROFILE_AUT_AUTHORIZATION VALUES (7,17);

ALTER SEQUENCE OBJ_OBJECT_ID_SEQ RESTART WITH 19;

commit;