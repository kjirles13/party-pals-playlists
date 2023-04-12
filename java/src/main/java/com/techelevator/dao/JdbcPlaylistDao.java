package com.techelevator.dao;

import com.techelevator.model.Artist;
import com.techelevator.model.Genre;
import com.techelevator.model.Playlist;
import com.techelevator.model.Song;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

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
        String sql = "SELECT playlist_id, name, description, spotify_id\n" +
                "\tFROM public.playlists\n" +
                "\tWHERE playlist_id = ?";

        SqlRowSet playlistResults = jdbcTemplate.queryForRowSet(sql, playlistId);

        if (playlistResults.next()){
            playlist = mapRowToPlaylist(playlistResults);

            String artistSql = "SELECT artists.artist_id, artists.name " +
                    "FROM public.artists " +
                    "JOIN artist_song ON artist_song.artist_id = artists.artist_id " +
                    "WHERE artist_song.song_id = ?";

        }

        return null;
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
    public void updateVotesForSong(int playlistId, int votes, int userId) {

    }

    @Override
    public void updatePlaylist(int playlistId, String songId, int userId) {

    }

    private Playlist mapRowToPlaylist(SqlRowSet rs){
        Playlist playlist = new Playlist();
        playlist.setName(rs.getString("name"));
        playlist.setDescription(rs.getString("description"));
        playlist.setPlaylistId(rs.getInt("playlist_id"));

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
