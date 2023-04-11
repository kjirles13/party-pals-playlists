package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSongDao implements SongDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSongDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Song> getAllSongs(int userId) {
        List<Song> allSongs = new ArrayList<>();

        String songSql = "SELECT songs.song_id, songs.title, songs.spotify_link, songs.preview, dj_song.song_rating " +
                "FROM public.songs " +
                "LEFT JOIN dj_song ON dj_song.song_id = songs.song_id " +
                "LEFT JOIN playlist_song ON playlist_song.song_id = songs.song_id " +
                "WHERE dj_song.dj_id = ?;";

        SqlRowSet songResults = jdbcTemplate.queryForRowSet(songSql, userId);

        while (songResults.next()) {
            Song song = mapRowToSong(songResults);

            String songId = songResults.getString("song_id");

            String artistSql = "SELECT artist.artist_id, artist.name " +
                    "FROM public.artist " +
                    "JOIN artist_song ON artist_song.artist_id = artist.artist_id " +
                    "WHERE artist_song.song_id = ?";

            SqlRowSet artistResults = jdbcTemplate.queryForRowSet(artistSql, songId);

            if (artistResults.next()) {
                song.setArtists(mapRowToArtists(artistResults));
            }

            String genreSql = "SELECT genres.genre_id, genres.name " +
                    "FROM public.genres " +
                    "JOIN genre_song ON genre_song.genre_id = genres.genre_id " +
                    "WHERE genre_song.song_id = ?";

            SqlRowSet genreResults = jdbcTemplate.queryForRowSet(genreSql, songId);

            if (genreResults.next()) {
                song.setGenres(mapRowToGenres(genreResults));
            }

            allSongs.add(song);
        }

        // TODO implement getAllSongs
        return allSongs;
    }

    @Override
    public Song addSong(int userId, Song song) {
        // TODO implement addSong
        return null;
    }

    @Override
    public void updateSong(SongDto songDto) {
        //TODO implement updateSong

    }

    @Override
    public void deleteSong(int songId, Principal principal) {
        //TODO implement deleteSong
    }

    @Override
    public Song getSongById(String songId) {
        //TODO implement getSongById
        return null;
    }

    private Song mapRowToSong(SqlRowSet rs) {
        //TODO implement mapRowToSong
        Song song = new Song();

        song.setId(rs.getInt("song_id"));
        song.setName(rs.getString("title"));
        song.setRating(rs.getInt("rating"));
//        song.setVotes(rs.getInt("votes"));
        song.setPreview(rs.getString("preview"));
        song.setSpotifyUri(rs.getString("spotify_link"));

        return song;

//        private int id;
//        private String name;
//        private List<Artist> artists;
//        private List<Genre> genres;
//        private int rating;
//        private int votes;
//        private String preview;
//        private String spotifyUri;

//        Song song = new Song();
//        song.setId(rs.getInt("id"));
//        song.setName(rs.getString("name"));
//        song.setRating(rs.getInt("rating"));
//        song.setVotes(rs.getInt("votes"));
//        song.setPreview(rs.getString("preview"));
//        song.setSpotifyUri(rs.getString("spotifyUri"));
//
//        List<Artist> artists = new ArrayList<>();
//        Array artistsArray = rs.getList("artists");
//        if (artistsArray != null) {
//            Object[] artistObjects = (Object[]) artistsArray.getArray();
//            for (Object artistObject : artistObjects) {
//                Artist artist = new Artist();
//                artist.setName((String) artistObject);
//                artists.add(artist);
//            }
//        }
//        song.setArtists(artists);
    }

    private List<Artist> mapRowToArtists(SqlRowSet rs) {
        List<Artist> artists = new ArrayList<>();

        while (rs.next()) {
            Artist artist = new Artist();
            artist.setId((rs.getString("artist_id")));
            artist.setName(rs.getString("name"));
            artists.add(artist);
        }

        return artists;
    }

    private List<Genre> mapRowToGenres(SqlRowSet rs) {
        List<Genre> genres = new ArrayList<>();

        while (rs.next()) {
            Genre genre = new Genre();
            genre.setId((rs.getInt("genre_id")));
            genre.setName(rs.getString("name"));
            genres.add(genre);
        }

        return genres;
    }
}
