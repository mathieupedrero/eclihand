DELETE FROM PLA_PLAYER_TEA_TEAM;
DELETE FROM TEA_TEAM;
DELETE FROM PLA_PLAYER;
DELETE FROM PER_PERSON;

INSERT INTO OBJ_OBJECT VALUES (1,'MathieuPedrero1986-06-23MASC');
INSERT INTO OBJ_OBJECT VALUES (2,'ThomasOursel1986-12-11MASC');
INSERT INTO OBJ_OBJECT VALUES (3,'Jean-BaptisteZigrand1986-06-02MASC');
INSERT INTO OBJ_OBJECT VALUES (4,'GwenninoGomes1988-09-23MASC');
INSERT INTO OBJ_OBJECT VALUES (5,'LaureAlliÃ¨s1986-06-23FEM');
INSERT INTO OBJ_OBJECT VALUES (6,'MarionPicard1986-12-11FEM');
INSERT INTO OBJ_OBJECT VALUES (7,'');
INSERT INTO OBJ_OBJECT VALUES (8,'');
INSERT INTO OBJ_OBJECT VALUES (9,'');
INSERT INTO OBJ_OBJECT VALUES (10,'');
INSERT INTO OBJ_OBJECT VALUES (11,'');
INSERT INTO OBJ_OBJECT VALUES (12,'');
INSERT INTO OBJ_OBJECT VALUES (13,'2012MASC');
INSERT INTO OBJ_OBJECT VALUES (14,'2011MASC');
INSERT INTO OBJ_OBJECT VALUES (15,'2010MASC');
INSERT INTO OBJ_OBJECT VALUES (16,'2009MASC');
INSERT INTO OBJ_OBJECT VALUES (17,'2008MASC');
INSERT INTO OBJ_OBJECT VALUES (18,'2012FEM');
INSERT INTO OBJ_OBJECT VALUES (19,'2011FEM');
INSERT INTO OBJ_OBJECT VALUES (20,'2010FEM');
INSERT INTO OBJ_OBJECT VALUES (21,'2009FEM');
INSERT INTO OBJ_OBJECT VALUES (22,'2008FEM');

INSERT INTO ILL_ILLUSTRABLE VALUES (1);
INSERT INTO ILL_ILLUSTRABLE VALUES (2);
INSERT INTO ILL_ILLUSTRABLE VALUES (3);
INSERT INTO ILL_ILLUSTRABLE VALUES (4);
INSERT INTO ILL_ILLUSTRABLE VALUES (5);
INSERT INTO ILL_ILLUSTRABLE VALUES (6);
INSERT INTO ILL_ILLUSTRABLE VALUES (7);
INSERT INTO ILL_ILLUSTRABLE VALUES (8);
INSERT INTO ILL_ILLUSTRABLE VALUES (9);
INSERT INTO ILL_ILLUSTRABLE VALUES (10);
INSERT INTO ILL_ILLUSTRABLE VALUES (11);
INSERT INTO ILL_ILLUSTRABLE VALUES (12);
INSERT INTO ILL_ILLUSTRABLE VALUES (13);
INSERT INTO ILL_ILLUSTRABLE VALUES (14);
INSERT INTO ILL_ILLUSTRABLE VALUES (15);
INSERT INTO ILL_ILLUSTRABLE VALUES (16);
INSERT INTO ILL_ILLUSTRABLE VALUES (17);
INSERT INTO ILL_ILLUSTRABLE VALUES (18);
INSERT INTO ILL_ILLUSTRABLE VALUES (19);
INSERT INTO ILL_ILLUSTRABLE VALUES (20);
INSERT INTO ILL_ILLUSTRABLE VALUES (21);
INSERT INTO ILL_ILLUSTRABLE VALUES (22);

INSERT INTO PER_PERSON VALUES (1,'Mathieu','Pedrero','1986-06-23','MASC');
INSERT INTO PER_PERSON VALUES (2,'Thomas','Oursel','1986-12-11','MASC');
INSERT INTO PER_PERSON VALUES (3,'Jean-Baptiste','Zigrand','1986-06-02','MASC');
INSERT INTO PER_PERSON VALUES (4,'Gwennino','Gomes','1988-09-23','MASC');
INSERT INTO PER_PERSON VALUES (5,'Laure','AlliÃ¨s','1986-06-23','FEM');
INSERT INTO PER_PERSON VALUES (6,'Marion','Picard','1986-12-11','FEM');

INSERT INTO PLA_PLAYER VALUES (7,1);
INSERT INTO PLA_PLAYER VALUES (8,2);
INSERT INTO PLA_PLAYER VALUES (9,3);
INSERT INTO PLA_PLAYER VALUES (10,4);
INSERT INTO PLA_PLAYER VALUES (11,5);
INSERT INTO PLA_PLAYER VALUES (12,6);

INSERT INTO TEA_TEAM VALUES (13,2012,'MASC');
INSERT INTO TEA_TEAM VALUES (14,2011,'MASC');
INSERT INTO TEA_TEAM VALUES (15,2010,'MASC');
INSERT INTO TEA_TEAM VALUES (16,2009,'MASC');
INSERT INTO TEA_TEAM VALUES (17,2008,'MASC');
INSERT INTO TEA_TEAM VALUES (18,2012,'FEM');
INSERT INTO TEA_TEAM VALUES (19,2011,'FEM');
INSERT INTO TEA_TEAM VALUES (20,2010,'FEM');
INSERT INTO TEA_TEAM VALUES (21,2009,'FEM');
INSERT INTO TEA_TEAM VALUES (22,2008,'FEM');

INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (8,14);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (10,14);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (7,15);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (8,15);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (9,15);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (10,15);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (7,16);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (8,16);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (9,16);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (10,16);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (7,17);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (8,17);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (9,17);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (12,19);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (11,20);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (12,20);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (11,21);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (12,21);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (11,22);
INSERT INTO PLA_PLAYER_TEA_TEAM VALUES (12,22);

ALTER SEQUENCE OBJ_OBJECT_ID_SEQ RESTART WITH 23;