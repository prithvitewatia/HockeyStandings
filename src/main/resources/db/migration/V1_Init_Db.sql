-- Creating Database
CREATE database hockeyStandings;
use hockeyStandings;

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
    year int8 NOT NULL,
    PRIMARY KEY(id));
-- This table is created to store basic details about a team
CREATE TABLE hockey_team(
    id int8 NOT NULL DEFAULT nextval('hockey_team_id'),
    name VARCHAR(255) NOT NULL,
    owner VARCHAR(255) NOT NULL,
    PRIMARY KEY(id));

-- This table is created to store basic details about a coach and which team
-- is he/she associated with.
CREATE TABLE hockey_coach(
    id int8 NOT NULL DEFAULT nextval('hockey_coach_id_seq'),
    name VARCHAR(255) NOT NULL,
    age int8 NOT NULL,
    experience int8 NOT NULL DEFAULT,
    teamId int8 ,
    PRIMARY KEY(id));

-- This table is created to store basic details about a player and which team
-- is he/she associated with.
CREATE TABLE hockey_players(
    id int8 NOT NULL DEFAULT nextval('hockey_players_id_seq'),
    name VARCHAR(255) NOT NULL,
    age int8 NOT NULL,
    teamId int8,
    PRIMARY KEY(id));

-- This table stores the winner of each tournament
CREATE TABLE hockey_tournament_winner(
    tourId int8 NOT NULL,
    teamId int8 NOT NULL,
    PRIMARY KEY(tourId,teamId));

-- This table stores the details of a particular match in a tournament
CREATE TABLE hockey_match(
    id int8 NOT NULL DEFAULT nextval('hockey_match_id_seq'),
    match_date DATE,
    homeId int8 NOT NULL,
    awayId int8 NOT NULL,
    tourId int8 NOT NULL,
    scoreHome int8 NOT NULL DEFAULT,
    scoreAway int8 NOT NULL DEFAULT
    PRIMARY KEY(id));

-- This table actually creates standings of teams in a tournament.
CREATE TABLE hockey_tournament_result(
    teamId int8 NOT NULL,
    tourId int8 NOT NULL,
    place int8,
    wins int8,
    losses int8,
    draws int8,
    goals_scored int8,
    goals_conceded int8,
    PRIMARY KEY(tourId,teamId));

ALTER TABLE if EXISTS hockey_players
ADD CONSTRAINT hockey_team_fk
FOREIGN KEY(teamId) REFERENCES hockey_team(id) on delete set NULL on update cascade;

ALTER TABLE if EXISTS hockey_coach
ADD CONSTRAINT hockey_team_fk
FOREIGN KEY(teamId) REFERENCES hockey_team(id) on delete set NULL ;

ALTER TABLE if EXISTS hockey_tournament_winner
ADD CONSTRAINT hockey_tour_fk
FOREIGN KEY (tourId) REFERENCES hockey_tournament(id) on delete cascade on update cascade;

ALTER TABLE if EXISTS hockey_tournament_winner
ADD CONSTRAINT hockey_team_fk
FOREIGN KEY (teamId) REFERENCES hockey_team(id) on delete set NULL on update cascade;

ALTER TABLE if EXISTS hockey_match
ADD CONSTRAINT homeId_fk
FOREIGN KEY (homeId) REFERENCES hockey_team(id) on delete set NULL on update cascade;

ALTER TABLE if EXISTS hockey_match
ADD CONSTRAINT awayId_fk
FOREIGN KEY(awayId) REFERENCES hockey_team(id) on delete set NULL on update cascade;

ALTER TABLE if EXISTS hockey_match
ADD CONSTRAINT tourId_fk
FOREIGN KEY (tourId) REFERENCES hockey_tournament(id) on delete cascade on update cascade;

ALTER TABLE if EXISTS hockey_tournament_result
ADD CONSTRAINT teamId_fk
FOREIGN KEY (teamId) REFERENCES hockey_team(id) on delete set NULL on update cascade;

ALTER TABLE if EXISTS hockey_tournament_result
ADD CONSTRAINT tourId_fk
FOREIGN KEY (tourId) REFERENCES hockey_tournament(id) on delete cascade on update cascade;