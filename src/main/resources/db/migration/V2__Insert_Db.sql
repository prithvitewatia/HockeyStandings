insert into hockey_tournament (name,year)
values
('Friendly',2022),
('Bundesliga',2019),
('Asian Champions Trophy',2022),
('World cup',2020);

insert into hockey_team(name,owner)
values
('India','Republic of India'),
('Russia','Russian Federation'),
('Germany','Republic of Germany'),
('UK','Some retards from nowhere');

insert into hockey_coach(name,age,experience,teamid)
values
('Sunil singh',54,22,1),
('Dmitri Petrenko',41,7,2),
('Peter Muller',45,12,3),
('Stupid Mountbatten',65,2,4);

insert into hockey_players(name,age,teamid)
values
('Ram sharma',22,1),
('Ravi Prakash',21,1),
('Shivam singh',23,1),
('Reznov',22,2),
('Yuri kamarin',23,2),
('Mikhail Lenin',27,2),
('Thomas Muller',27,3),
('Manuel Neuer',25,3),
('Baby Churchill',21,4),
('Monty',25,4);

insert into hockey_match(match_date,homeid,awayid,tourid,scorehome,scoreaway)
values
('2021-10-09',3,4,1,0,2),
('2020-09-12',3,1,1,1,1);