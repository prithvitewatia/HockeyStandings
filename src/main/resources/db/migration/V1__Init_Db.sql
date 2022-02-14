-- Creating sequences that will serve as ids for various tables
CREATE SEQUENCE hockey_tournament_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;
CREATE SEQUENCE hockey_team_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;
CREATE SEQUENCE hockey_coach_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;
CREATE SEQUENCE hockey_players_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;
CREATE SEQUENCE hockey_match_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;
-- Creating tables

-- This table is created to store basic details about a tournament
CREATE TABLE hockey_tournament(
    id int8 NOT NULL DEFAULT nextval('hockey_tournament_id_seq'),
    name VARCHAR(255) NOT NULL,
    year int4 NOT NULL,
    PRIMARY KEY(id));
-- This table is created to store basic details about a team
CREATE TABLE hockey_team(
    id int8 NOT NULL DEFAULT nextval('hockey_team_id_seq'),
    name VARCHAR(255) NOT NULL,
    owner VARCHAR(255) NOT NULL,
    PRIMARY KEY(id));

-- This table is created to store basic details about a coach and which team
-- is he/she associated with.
CREATE TABLE hockey_coach(
    id int8 NOT NULL DEFAULT nextval('hockey_coach_id_seq'),
    name VARCHAR(255) NOT NULL,
    age int4 NOT NULL,
    experience int4 NOT NULL,
    teamid int8,
    PRIMARY KEY(id));

-- This table is created to store basic details about a player and which team
-- is he/she associated with.
CREATE TABLE hockey_players(
    id int8 NOT NULL DEFAULT nextval('hockey_players_id_seq'),
    name VARCHAR(255) NOT NULL,
    age int4 NOT NULL,
    teamid int8,
    PRIMARY KEY(id));

-- This table stores the details of a particular match in a tournament
CREATE TABLE hockey_match(
    id int8 NOT NULL DEFAULT nextval('hockey_match_id_seq'),
    match_date DATE,
    homeid int8,
    awayid int8,
    tourid int8,
    scorehome int4 NOT NULL,
    scoreaway int4 NOT NULL,
    PRIMARY KEY(id));

ALTER TABLE if EXISTS hockey_players
ADD CONSTRAINT hockey_team_fk
FOREIGN KEY(teamId) REFERENCES hockey_team(id) on delete set NULL on update cascade;

ALTER TABLE if EXISTS hockey_coach
ADD CONSTRAINT hockey_team_fk
FOREIGN KEY(teamId) REFERENCES hockey_team(id) on delete set NULL ;

ALTER TABLE if EXISTS hockey_match
ADD CONSTRAINT homeId_fk
FOREIGN KEY (homeId) REFERENCES hockey_team(id) on delete set NULL on update cascade;

ALTER TABLE if EXISTS hockey_match
ADD CONSTRAINT awayId_fk
FOREIGN KEY(awayId) REFERENCES hockey_team(id) on delete set NULL on update cascade;

ALTER TABLE if EXISTS hockey_match
ADD CONSTRAINT tourId_fk
FOREIGN KEY (tourId) REFERENCES hockey_tournament(id) on delete cascade on update cascade;