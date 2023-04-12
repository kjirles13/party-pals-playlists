BEGIN TRANSACTION;
-- ROLLBACK TRANSACTION
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('kit','$2a$10$quBSSxGQenP59LD1J5ZHIuizWw0dqso/cAtn6LNryNotTm/aMmEw2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('ari','$2a$10$uDyopGzbawG8kag13M6qK.LwYvxUwnM7mcZwbZ5cy0y8FNk2OovHa','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('dan','$2a$10$f4LoPR0O9Y9.DUoQ5jvYmePIBzSa1g0/2tHEgjqzpA.5F7cCT6yH.','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('blake','$2a$10$4.ovOGPlkY5whyJ8l7s3pulDLfZdJc/tb./AHzpBnGdhoZvUwqwea','ROLE_USER');

INSERT INTO artists (artist_id, name) VALUES ('1moxjboGR7GNWYIMWsRjgG', 'Florence and the Machine');
INSERT INTO artists (artist_id, name) VALUES ('4V8LLVI7PbaPR0K2TGSxFF', 'Tyler, The Creator');
INSERT INTO artists (artist_id, name) VALUES ('1U1el3k54VvEUzo3ybLPlM', 'Kali Uchis');

INSERT INTO songs (song_id, title, spotify_link, preview) VALUES ('1YLJVmuzeM2YSUkCCaTNUB', 'Dog Days Are Over', 'https://open.spotify.com/track/1YLJVmuzeM2YSUkCCaTNUB', 'https://p.scdn.co/mp3-preview/d1c97f83c052039d9e5105ed70261a5c59fd7f53?cid=ff7f710ce2564ce19c72faebda1dfa0e');
INSERT INTO songs (song_id, title, spotify_link, preview) VALUES ('7H7SHw3YWXhb4zYqyoPNa1', 'Free', 'https://open.spotify.com/track/7H7SHw3YWXhb4zYqyoPNa1', 'https://p.scdn.co/mp3-preview/a6b318f3834a1a35d91717821b786a9c3d65f132?cid=ff7f710ce2564ce19c72faebda1dfa0e');
INSERT INTO songs (song_id, title, spotify_link, preview) VALUES ('5XSU59wtE5CRCAEyHmmGy4', 'Stand By Me', 'https://open.spotify.com/track/5XSU59wtE5CRCAEyHmmGy4', 'https://p.scdn.co/mp3-preview/e889b6fd78612ec98fa14edf37569834a82be8f7?cid=ff7f710ce2564ce19c72faebda1dfa0e');
INSERT INTO songs (song_id, title, spotify_link, preview) VALUES ('31ABtQJdfloNaGz7ZhXaN5', 'King', 'https://open.spotify.com/track/31ABtQJdfloNaGz7ZhXaN5', 'https://p.scdn.co/mp3-preview/adf1c266b2ab30657fc0ae7db35af71e2428b805?cid=ff7f710ce2564ce19c72faebda1dfa0e');
INSERT INTO songs (song_id, title, spotify_link, preview) VALUES ('7KA4W4McWYRpgf0fWsJZWB', 'See You Again (feat. Kali Uchis)', 'https://open.spotify.com/track/7KA4W4McWYRpgf0fWsJZWB', 'https://p.scdn.co/mp3-preview/c703198293891e3b276800ea6b187cf7951d3d7d?cid=ff7f710ce2564ce19c72faebda1dfa0e');
INSERT INTO songs (song_id, title, spotify_link, preview) VALUES ('0judK8TqYfvwUohBngUxrE', 'DOGTOOTH', 'https://open.spotify.com/track/0judK8TqYfvwUohBngUxrE', 'https://p.scdn.co/mp3-preview/5de4c8c30108bc114f9f38a28ac0832936a852bf?cid=ff7f710ce2564ce19c72faebda1dfa0e');
INSERT INTO songs (song_id, title, spotify_link, preview) VALUES ('410ZZP746AQeiywhKvXWCo', 'SORRY NOT SORRY', 'https://open.spotify.com/track/410ZZP746AQeiywhKvXWCo', 'https://p.scdn.co/mp3-preview/70416bd945586a23d770d318303e04936a04bdad?cid=ff7f710ce2564ce19c72faebda1dfa0e');
INSERT INTO songs (song_id, title, spotify_link, preview) VALUES ('1nwkSqzTyXBk6XF796EOav', 'Yonkers', 'https://open.spotify.com/track/1nwkSqzTyXBk6XF796EOav', 'https://p.scdn.co/mp3-preview/d5be2099d4df6b4d09ee66ecbcb2caaf08e198ff?cid=ff7f710ce2564ce19c72faebda1dfa0e');

INSERT INTO artist_song (artist_id, song_id) VALUES ('1moxjboGR7GNWYIMWsRjgG', '1YLJVmuzeM2YSUkCCaTNUB');
INSERT INTO artist_song (artist_id, song_id) VALUES ('1moxjboGR7GNWYIMWsRjgG', '7H7SHw3YWXhb4zYqyoPNa1');
INSERT INTO artist_song (artist_id, song_id) VALUES ('1moxjboGR7GNWYIMWsRjgG', '5XSU59wtE5CRCAEyHmmGy4');
INSERT INTO artist_song (artist_id, song_id) VALUES ('1moxjboGR7GNWYIMWsRjgG', '31ABtQJdfloNaGz7ZhXaN5');
INSERT INTO artist_song (artist_id, song_id) VALUES ('4V8LLVI7PbaPR0K2TGSxFF', '7KA4W4McWYRpgf0fWsJZWB');
INSERT INTO artist_song (artist_id, song_id) VALUES ('4V8LLVI7PbaPR0K2TGSxFF', '0judK8TqYfvwUohBngUxrE');
INSERT INTO artist_song (artist_id, song_id) VALUES ('4V8LLVI7PbaPR0K2TGSxFF', '410ZZP746AQeiywhKvXWCo');
INSERT INTO artist_song (artist_id, song_id) VALUES ('4V8LLVI7PbaPR0K2TGSxFF', '1nwkSqzTyXBk6XF796EOav');
INSERT INTO artist_song (artist_id, song_id) VALUES ('1U1el3k54VvEUzo3ybLPlM', '7KA4W4McWYRpgf0fWsJZWB');


INSERT INTO dj_song (song_id, dj_id, song_rating) VALUES ('1YLJVmuzeM2YSUkCCaTNUB', 3, 5);
INSERT INTO dj_song (song_id, dj_id, song_rating) VALUES ('7H7SHw3YWXhb4zYqyoPNa1', 3, 3);
INSERT INTO dj_song (song_id, dj_id, song_rating) VALUES ('7KA4W4McWYRpgf0fWsJZWB', 3, 4);
INSERT INTO dj_song (song_id, dj_id, song_rating) VALUES ('5XSU59wtE5CRCAEyHmmGy4', 3, 3);
INSERT INTO dj_song (song_id, dj_id, song_rating) VALUES ('0judK8TqYfvwUohBngUxrE', 3, 2);
INSERT INTO dj_song (song_id, dj_id, song_rating) VALUES ('1nwkSqzTyXBk6XF796EOav', 3, 4);
INSERT INTO dj_song (song_id, dj_id, song_rating) VALUES ('5XSU59wtE5CRCAEyHmmGy4', 4, 2);
INSERT INTO dj_song (song_id, dj_id, song_rating) VALUES ('410ZZP746AQeiywhKvXWCo', 4, 3);
INSERT INTO dj_song (song_id, dj_id, song_rating) VALUES ('7KA4W4McWYRpgf0fWsJZWB', 4, 1);
INSERT INTO dj_song (song_id, dj_id, song_rating) VALUES ('31ABtQJdfloNaGz7ZhXaN5', 4, 5);
INSERT INTO dj_song (song_id, dj_id, song_rating) VALUES ('7H7SHw3YWXhb4zYqyoPNa1', 4, 3);

INSERT INTO genres (name) VALUES ('Rock');
INSERT INTO genres (name) VALUES ('R&B');
INSERT INTO genres (name) VALUES ('Alternative');
INSERT INTO genres (name) VALUES ('Folk');
INSERT INTO genres (name) VALUES ('Bluegrass');
INSERT INTO genres (name) VALUES ('Jazz');
INSERT INTO genres (name) VALUES ('Contemporary');
INSERT INTO genres (name) VALUES ('Rap');

INSERT INTO genre_song (song_id, genre_id) VALUES ('1YLJVmuzeM2YSUkCCaTNUB', 1);
INSERT INTO genre_song (song_id, genre_id) VALUES ('1YLJVmuzeM2YSUkCCaTNUB', 3);
INSERT INTO genre_song (song_id, genre_id) VALUES ('7H7SHw3YWXhb4zYqyoPNa1', 1);
INSERT INTO genre_song (song_id, genre_id) VALUES ('7H7SHw3YWXhb4zYqyoPNa1', 3);
INSERT INTO genre_song (song_id, genre_id) VALUES ('5XSU59wtE5CRCAEyHmmGy4', 1);
INSERT INTO genre_song (song_id, genre_id) VALUES ('5XSU59wtE5CRCAEyHmmGy4', 3);
INSERT INTO genre_song (song_id, genre_id) VALUES ('31ABtQJdfloNaGz7ZhXaN5', 1);
INSERT INTO genre_song (song_id, genre_id) VALUES ('31ABtQJdfloNaGz7ZhXaN5', 3);
INSERT INTO genre_song (song_id, genre_id) VALUES ('7KA4W4McWYRpgf0fWsJZWB', 2);
INSERT INTO genre_song (song_id, genre_id) VALUES ('7KA4W4McWYRpgf0fWsJZWB', 8);
INSERT INTO genre_song (song_id, genre_id) VALUES ('0judK8TqYfvwUohBngUxrE', 2);
INSERT INTO genre_song (song_id, genre_id) VALUES ('0judK8TqYfvwUohBngUxrE', 8);
INSERT INTO genre_song (song_id, genre_id) VALUES ('410ZZP746AQeiywhKvXWCo', 2);
INSERT INTO genre_song (song_id, genre_id) VALUES ('410ZZP746AQeiywhKvXWCo', 8);
INSERT INTO genre_song (song_id, genre_id) VALUES ('1nwkSqzTyXBk6XF796EOav', 2);
INSERT INTO genre_song (song_id, genre_id) VALUES ('1nwkSqzTyXBk6XF796EOav', 8);

INSERT INTO playlists (name, description) VALUES ('Cookout Jamz', 'This is a playlist for a potluck');
INSERT INTO playlists (name, description) VALUES ('Wedding Vibes', 'Someone is getting married!');
-- INSERT INTO playlists (name, description) VALUES ('Friday Night Dance Party', 'TGIF');
-- INSERT INTO playlists (name, description) VALUES ('Game Night', 'Grab a game and stay in');

INSERT INTO playlist_song (playlist_id, song_id, votes) VALUES (1, '1YLJVmuzeM2YSUkCCaTNUB', 12);
INSERT INTO playlist_song (playlist_id, song_id, votes) VALUES (1, '5XSU59wtE5CRCAEyHmmGy4', 5);
INSERT INTO playlist_song (playlist_id, song_id, votes) VALUES (1, '7H7SHw3YWXhb4zYqyoPNa1', 6);
INSERT INTO playlist_song (playlist_id, song_id, votes) VALUES (2, '0judK8TqYfvwUohBngUxrE', 2);
INSERT INTO playlist_song (playlist_id, song_id, votes) VALUES (2, '410ZZP746AQeiywhKvXWCo', 10);
INSERT INTO playlist_song (playlist_id, song_id, votes) VALUES (2, '1nwkSqzTyXBk6XF796EOav', 8);

INSERT INTO playlist_genre (playlist_id, genre_id) VALUES (1, 1);
INSERT INTO playlist_genre (playlist_id, genre_id) VALUES (1, 3);
INSERT INTO playlist_genre (playlist_id, genre_id) VALUES (2, 8);
INSERT INTO playlist_genre (playlist_id, genre_id) VALUES (2, 2);

INSERT INTO events (dj_id, event_name, description, playlist_id, date, time, theme) VALUES (3, 'Birthday Cookout.', 'Join us for Brian''s birthday party!', 1, '2023-01-06', '14:00:00' , 'Luau');
INSERT INTO events (dj_id, event_name, description, playlist_id, date, time, theme) VALUES (4, 'Dave and Stacy''s Wedding', 'Stacy''s mom and I are getting married!', 2, '2023-06-10', '17:30:00', 'Star Wars');

INSERT INTO host_event (user_id, event_id) VALUES (6, 1);
INSERT INTO host_event (user_id, event_id) VALUES (6, 2);
INSERT INTO host_event (user_id, event_id) VALUES (5, 2);

COMMIT TRANSACTION;
