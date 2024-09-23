INSERT INTO genres (id, name) VALUES (default, 'Action');
INSERT INTO genres (id, name) VALUES (default, 'Fighting');
INSERT INTO genres (id, name) VALUES (default, 'Horror');
INSERT INTO genres (id, name) VALUES (default, 'Platformers');
INSERT INTO genres (id, name) VALUES (default, 'RPG');
INSERT INTO genres (id, name) VALUES (default, 'Simulation');
INSERT INTO genres (id, name) VALUES (default, 'Sport');
INSERT INTO genres (id, name) VALUES (default, 'Strategy');

INSERT INTO videogames (id, name, release_date, company, platform) VALUES (default, 'The legend of Zelda: Echoes of wisdom','2024-09-26','Nintendo','Nintendo Switch');
INSERT INTO videogames (id, name, release_date, company, platform) VALUES (default, 'Dragon age: The Veilguard','2024-10-31','Bioware','PC, PS5');
INSERT INTO videogames (id, name, release_date, company, platform) VALUES (default, 'Dragon ball Sparking Zero','2024-10-11','Bandai Namco Entertainment','PC, PS5');
INSERT INTO videogames (id, name, release_date, company, platform) VALUES (default, 'New World Aeternum','2024-07-25','Amazon Game Studios','PS5');

INSERT INTO videogames_genres (videogame_id, genres_id) VALUES (1, 5);
INSERT INTO videogames_genres (videogame_id, genres_id) VALUES (1, 1);
INSERT INTO videogames_genres (videogame_id, genres_id) VALUES (2, 5);
INSERT INTO videogames_genres (videogame_id, genres_id) VALUES (2, 1);
INSERT INTO videogames_genres (videogame_id, genres_id) VALUES (3, 2);
INSERT INTO videogames_genres (videogame_id, genres_id) VALUES (4, 5);

/* Roles */
INSERT INTO roles (id, name) VALUES (default, 'ROLE_USER'); -- id = 1
INSERT INTO roles (id, name) VALUES (default, 'ROLE_ADMIN'); -- id = 2

/* Users */
INSERT INTO users (id, username, password) VALUES (default, 'admin', '$2a$12$j51rvviaqsaWYj1H7znOEO2zYw1Oqkk70lZAIW9DxsU9Rge2G.jQq');
INSERT INTO users (id, username, password) VALUES (default, 'nacho', '$2a$12$QXYBNWLkHObayPv9IONcJOmpskXFnRQs4q8dTM/XhgE36fNRmsRNy');


INSERT INTO roles_users (role_id, user_id) VALUES (1, 2);
INSERT INTO roles_users (role_id, user_id) VALUES (2, 1);