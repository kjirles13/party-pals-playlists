package com.techelevator.dao;

import com.techelevator.model.Artist;
import com.techelevator.model.Genre;
import com.techelevator.model.Playlist;
import com.techelevator.model.Song;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPlaylistDao implements PlaylistDao{
    private final JdbcTemplate jdbcTemplate;
    private final SongDao songDao;

    public JdbcPlaylistDao(JdbcTemplate jdbcTemplate, SongDao songDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.songDao = songDao;
    }

    @Override
    public Playlist getPlayListById(int playlistId, int userId) {
        Playlist playlist = new Playlist();

        String sql = "SELECT playlist_id, name, description, spotify_id " +
                "FROM public.playlists " +
                "WHERE playlist_id = ?";

        SqlRowSet playlistResults = jdbcTemplate.queryForRowSet(sql, playlistId);

        if (playlistResults.next()) {
            playlist = mapRowToPlaylist(playlistResults);
        }

        String sqlSong = "SELECT song_id " +
                "FROM playlist_song " +
                "WHERE playlist_id = ?";

        SqlRowSet songIdResults = jdbcTemplate.queryForRowSet(sqlSong, playlistId);

        List<Song> songs = new ArrayList<>();

        while (songIdResults.next()) {
            String songId = songIdResults.getString("song_id");

            String songSql = "SELECT songs.song_id, songs.title, songs.spotify_link, songs.preview, playlist_song.votes, dj_song.song_rating " +
                    "FROM songs " +
                    "LEFT JOIN dj_song ON songs.song_id = dj_song.song_id " +
                    "LEFT JOIN playlist_song ON songs.song_id = playlist_song.song_id " +
                    "WHERE songs.song_id = ? " +
                    "AND playlist_song.playlist_id = ? ";

            SqlRowSet songResults = jdbcTemplate.queryForRowSet(songSql, songId, playlistId);

            Song song = new Song();

            if (songResults.next()) {
                song = mapRowToSong(songResults);

                String artistSql = "SELECT artists.artist_id, artists.name " +
                        "FROM public.artists " +
                        "JOIN artist_song ON artist_song.artist_id = artists.artist_id " +
                        "WHERE artist_song.song_id = ?";

                SqlRowSet artistResults = jdbcTemplate.queryForRowSet(artistSql, songId);

                List<Artist> artists = new ArrayList<>();

                while (artistResults.next()) {
                    Artist artist = mapRowToArtist(artistResults);

                    artists.add(artist);
                }

                song.setArtists(artists);

                String genreSql = "SELECT genres.genre_id, genres.name " +
                        "FROM public.genres " +
                        "JOIN genre_song ON genre_song.genre_id = genres.genre_id " +
                        "WHERE genre_song.song_id = ?";

                SqlRowSet genreResults = jdbcTemplate.queryForRowSet(genreSql, songId);

                List<Genre> genres = new ArrayList<>();

                while (genreResults.next()) {
                    Genre genre = mapRowToGenre(genreResults);

                    genres.add(genre);
                }

                song.setGenres(genres);
                songs.add(song);
            }
        }

        playlist.setSongs(songs);

        String sqlGenres = "SELECT genres.genre_id, genres.name " +
                "FROM playlist_genre " +
                "JOIN genres ON genres.genre_id = playlist_genre.genre_id " +
                "WHERE playlist_genre.playlist_id = ?";

        SqlRowSet genreResults = jdbcTemplate.queryForRowSet(sqlGenres, playlist.getPlaylistId());

        List<Genre> genres = new ArrayList<>();

        while (genreResults.next()) {
            genres.add(mapRowToGenre(genreResults));
        }

        playlist.setGenres(genres);

        return playlist;
    }

    @Override
    public void addSongToPlaylist(int playlistId, String songId, int userId) {
        if (verifyUser(userId, playlistId)) {

            String sql = "INSERT INTO public.playlist_song(\n" +
                    "\tplaylist_id, song_id, votes)\n" +
                    "\tVALUES (?, ?, ?)";

            jdbcTemplate.update(sql, playlistId, songId, 0);
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "You are unauthorized to modify this playlist");
        }

    }

    @Override
    public void deleteSongFromPlaylist(int playlistId, String songId, int userId) {
        if (verifyUser(userId, playlistId)){

            String sql = "DELETE FROM public.playlist_song\n" +
                    "\tWHERE playlist_id = ? AND song_id = ?";

            jdbcTemplate.update(sql, playlistId, songId);

        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "You are unauthorized to modify this playlist");
        }
    }

    @Override
    public void updateVotesForSong(int playlistId, String songId, int userId) {

        String sql = "UPDATE playlist_song SET votes = votes + 1 \n" +
                "WHERE playlist_id = ? AND song_id = ?";

        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, playlistId);
            ps.setString(2, songId);
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator);

    }

    @Override
    public void updatePlaylist(int playlistId, String name, String description, int userId) {
            String sql = "UPDATE public.playlists\n" +
                    "\tSET name=?, description=?\n" +
                    "\tWHERE playlist_id=?";

            jdbcTemplate.update(sql, name, description, playlistId);

    }

    private Song mapRowToSong(SqlRowSet rs) {
        Song song = new Song();

        song.setId(rs.getString("song_id"));
        song.setName(rs.getString("title"));
        try {
            song.setRating(rs.getInt("song_rating"));
        } catch (Exception e) {
            song.setRating(0);
        }
        try {
            song.setVotes(rs.getInt("votes"));
        } catch (Exception e) {
            song.setVotes(0);
        }
        song.setPreview(rs.getString("preview"));
        song.setSpotifyUri(rs.getString("spotify_link"));

        return song;
    }

    private Playlist mapRowToPlaylist(SqlRowSet rs){
        Playlist playlist = new Playlist();

        playlist.setName(rs.getString("name"));
        playlist.setDescription(rs.getString("description"));
        playlist.setPlaylistId(rs.getInt("playlist_id"));
        playlist.setSpotifyId(rs.getString("spotify_id"));

        return playlist;
    }

    private Artist mapRowToArtist(SqlRowSet rs) {
        Artist artist = new Artist();

        artist.setId((rs.getString("artist_id")));
        artist.setName(rs.getString("name"));

        return artist;
    }

    private Genre mapRowToGenre(SqlRowSet rs) {
        Genre genre = new Genre();

        genre.setId((rs.getInt("genre_id")));
        genre.setName(rs.getString("name"));

        return genre;
    }


    private boolean verifyUser(int userId, int playlistId){
        String sql = "SELECT events.playlist_id\n" +
                "FROM events\n" +
                "WHERE dj_id = ?";

        int result = jdbcTemplate.queryForObject(sql, Integer.class, userId);

        if (result == playlistId){
            return true;
        }
            return false;
    }
}
