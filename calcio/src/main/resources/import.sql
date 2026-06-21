insert into team (id, name, year_foundation, city) values(nextval('team_seq'), 'A.S. Roma', 1927, 'Roma');
insert into team (id, name, year_foundation, city) values(nextval('team_seq'), 'S.S. Lazio', 1900, 'Roma');
insert into team (id, name, year_foundation, city) values(nextval('team_seq'), 'Juventus F.C.', 1897, 'Torino');
insert into team (id, name, year_foundation, city) values(nextval('team_seq'), 'A.C. Milan', 1899, 'Milano');
insert into team (id, name, year_foundation, city) values(nextval('team_seq'), 'F.C. Inter', 1908, 'Milano');

insert into player (id, name, surname, date_of_birth, "role", height, team_id) values(nextval('player_seq'), 'Dusan', 'Vlahovic', '2000-01-28', 'ATTACCANTE', 190, (select id from team where name = 'Juventus F.C.'));
insert into player (id, name, surname, date_of_birth, "role", height, team_id) values(nextval('player_seq'), 'Lorenzo', 'Pellegrini', '1996-06-19', 'CENTROCAMPISTA', 186, (select id from team where name = 'A.S. Roma'));
insert into player (id, name, surname, date_of_birth, "role", height, team_id) values(nextval('player_seq'), 'Alessandro', 'Bastoni', '1999-04-13', 'DIFENSORE', 190, (select id from team where name = 'F.C. Inter'));
insert into player (id, name, surname, date_of_birth, "role", height, team_id) values(nextval('player_seq'), 'Mike', 'Maignan', '1995-07-03', 'PORTIERE', 191, (select id from team where name = 'A.C. Milan'));
insert into player (id, name, surname, date_of_birth, "role", height, team_id) values(nextval('player_seq'), 'Mattia', 'Zaccagni', '1995-06-16', 'ATTACCANTE', 177, (select id from team where name = 'S.S. Lazio'));
insert into player (id, name, surname, date_of_birth, "role", height, team_id) values(nextval('player_seq'), 'Gianluca', 'Mancini', '1996-04-17', 'DIFENSORE', 190, (select id from team where name = 'A.S. Roma'));

insert into referee (id, name, surname, referee_code) values(nextval('referee_seq'), 'Daniele', 'Orsato', 'R-1001');
insert into referee (id, name, surname, referee_code) values(nextval('referee_seq'), 'Simone', 'Sozza', 'R-1002');

insert into tournament (id, name, "year", description) values(nextval('tournament_seq'), 'Serie A', 2026, 'Campionato nazionale di massima serie. ');
insert into tournament (id, name, "year", description) values(nextval('tournament_seq'), 'Coppa Italia', 2026, 'Competizione nazionale con gironi eliminatori e fase finale ad eliminazione diretta.');

insert into "match" (id, "date", "time", goals_home, goals_away, state, tournament_id, home_team_id, away_team_id, referee_id) values(nextval('match_seq'), '2026-03-01', '20:45:00', 0, 1, 'PLAYED', (select id from tournament where name = 'Serie A' and "year" = 2026), (select id from team where name = 'A.S. Roma'), (select id from team where name = 'S.S. Lazio'), (select id from referee where referee_code = 'R-1001'));
insert into "match" (id, "date", "time", goals_home, goals_away, state, tournament_id, home_team_id, away_team_id, referee_id) values(nextval('match_seq'), '2026-03-07', '18:00:00', 1, 1, 'PLAYED', (select id from tournament where name = 'Serie A' and "year" = 2026), (select id from team where name = 'Juventus F.C.'), (select id from team where name = 'A.C. Milan'), (select id from referee where referee_code = 'R-1002'));
insert into "match" (id, "date", "time", goals_home, goals_away, state, tournament_id, home_team_id, away_team_id, referee_id) values(nextval('match_seq'), '2026-02-15', '21:00:00', 2, 1, 'PLAYED', (select id from tournament where name = 'Coppa Italia' and "year" = 2026), (select id from team where name = 'F.C. Inter'), (select id from team where name = 'A.S. Roma'), (select id from referee where referee_code = 'R-1001'));

insert into "match" (id, "date", "time", goals_home, goals_away, state, tournament_id, home_team_id, away_team_id, referee_id) values(nextval('match_seq'), '2026-06-27', '20:45:00', 0, 0, 'SCHEDULED', (select id from tournament where name = 'Serie A' and year = 2026), (select id from team where name = 'A.C. Milan'), (select id from team where name = 'A.S. Roma'), (select id from referee where referee_code = 'R-1001'));
insert into "match" (id, "date", "time", goals_home, goals_away, state, tournament_id, home_team_id, away_team_id, referee_id) values(nextval('match_seq'), '2026-06-26', '18:00:00', 0, 0, 'SCHEDULED', (select id from tournament where name = 'Coppa Italia' and year = 2026), (select id from team where name = 'Juventus F.C.'), (select id from team where name = 'F.C. Inter'), (select id from referee where referee_code = 'R-1002'));

insert into tournament_teams (tournaments_id, teams_id) values((select id from tournament where name = 'Serie A' and year = 2026), (select id from team where name = 'A.S. Roma'));
insert into tournament_teams (tournaments_id, teams_id) values((select id from tournament where name = 'Serie A' and year = 2026), (select id from team where name = 'S.S. Lazio'));
insert into tournament_teams (tournaments_id, teams_id) values((select id from tournament where name = 'Serie A' and year = 2026), (select id from team where name = 'Juventus F.C.'));
insert into tournament_teams (tournaments_id, teams_id) values((select id from tournament where name = 'Serie A' and year = 2026), (select id from team where name = 'A.C. Milan'));
insert into tournament_teams (tournaments_id, teams_id) values((select id from tournament where name = 'Coppa Italia' and year = 2026), (select id from team where name = 'F.C. Inter'));
insert into tournament_teams (tournaments_id, teams_id) values((select id from tournament where name = 'Coppa Italia' and year = 2026), (select id from team where name = 'A.S. Roma'));

insert into users (id, name, surname, username) values(nextval('users_seq'), 'Admin', 'Calcio', 'admin');
insert into credentials (id, username, password, "role", user_id) values(nextval('credentials_seq'), 'admin', '$2b$12$rVnIQJbZcoTKuq8yQYb/mux/cZZb1XesQ07sz8/Qp8rwSgVmZ1lHW', 'ADMIN', (select id from users where username = 'admin'));
insert into users (id, name, surname, username) values(nextval('users_seq'), 'Elena', 'Bianchi', 'elena');
insert into credentials (id, username, password, "role", user_id) values(nextval('credentials_seq'), 'elena', '$2b$12$HAzrLlUmEHPduDd5.w26c.t.IBxXk2eiz3160Y8me8cXUi/jQmrL2', 'DEFAULT', (select id from users where username = 'elena'));
