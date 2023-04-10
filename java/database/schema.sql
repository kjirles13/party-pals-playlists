BEGIN TRANSACTION;

DROP TABLE IF EXISTS user_role, genre_song, playlist_genre, 
artist_song, playlist_song, dj_song, songs, genres, playlists, 
artist, host_event, events, roles, users CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE roles (
	role_id SERIAL PRIMARY KEY,
	role_name VARCHAR(50) NOT NULL
);

CREATE TABLE user_role (
user_id int NOT NULL,
role_id int NOT null,
PRIMARY KEY (user_id, role_id),
FOREIGN KEY (user_id) REFERENCES users(user_id),
FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE playlists(
id int NOT NULL PRIMARY KEY,
name char(100) NOT NULL,
description varchar(200)
);

CREATE TABLE songs (
	song_id int NOT NULL PRIMARY KEY,
	title char(50) NOT NULL,
	spotify_link char(250),
	preview char(250)
);

CREATE TABLE artist (
artist_id int NOT NULL PRIMARY KEY,
name char(50) NOT NULL
);

CREATE TABLE genres (
genre_id int NOT NULL PRIMARY KEY,
name char(50) NOT NULL
);

CREATE TABLE artist_song (
artist_id int NOT NULL,
song_id int NOT NULL,
PRIMARY KEY (artist_id, song_id),
FOREIGN KEY (artist_id) REFERENCES artist (artist_id),
FOREIGN KEY (song_id) REFERENCES songs (song_id)
);

CREATE TABLE events (
event_id int NOT NULL PRIMARY KEY,
user_id int NOT NULL,
role_id int NOT NULL,
from_date DATE NOT NULL,
to_date DATE NOT NULL,
description char(250) NOT NULL,
playlist_id int NOT NULL,
date_time TIMESTAMP NOT NULL,
theme char(100),
FOREIGN KEY (user_id) REFERENCES users(user_id),
FOREIGN KEY (role_id) REFERENCES roles(role_id),
FOREIGN KEY (playlist_id) REFERENCES playlists (id)
);

CREATE TABLE host_event (
user_id int NOT NULL,
event_id int NOT NULL,
PRIMARY KEY (user_id, event_id),
FOREIGN KEY (user_id) REFERENCES users (user_id),
FOREIGN KEY (event_id) REFERENCES events (event_id)
);

CREATE TABLE playlist_song (
playlist_id int NOT NULL,
song_id int NOT NULL,
votes int NOT NULL,
PRIMARY KEY (playlist_id, song_id),
FOREIGN KEY (playlist_id) REFERENCES playlists (id),
FOREIGN KEY (song_id) REFERENCES songs (song_id)
);

CREATE TABLE dj_song (
song_id int NOT NULL,
user_id int NOT NULL,
song_rating int NOT NULL,
PRIMARY KEY (song_id, user_id),
FOREIGN KEY (song_id) REFERENCES songs (song_id),
FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE genre_song (
song_id int NOT NULL,
genre_id int NOT NULL,
PRIMARY KEY (song_id, genre_id),
FOREIGN KEY (song_id) REFERENCES songs (song_id),
FOREIGN KEY (genre_id) REFERENCES genres (genre_id)
);

CREATE TABLE playlist_genre (
playlist_id int NOT NULL,
genre_id int NOT NULL,
PRIMARY KEY (playlist_id, genre_id),
FOREIGN KEY (playlist_id) REFERENCES playlists (id),
FOREIGN KEY (genre_id) REFERENCES genres (genre_id)
);

COMMIT TRANSACTION;
