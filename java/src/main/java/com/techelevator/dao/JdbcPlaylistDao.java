package com.techelevator.dao;

import com.techelevator.model.Artist;
import com.techelevator.model.Genre;
import com.techelevator.model.Playlist;
import com.techelevator.model.Song;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class JdbcPlaylistDao implements PlaylistDao{
    private final JdbcTemplate jdbcTemplate;
    private final SongDao songDao;

    public JdbcPlaylistDao(JdbcTemplate jdbcTemplate, SongDao songDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.songDao = songDao;
    }
    @Override
    public void updatePlaylist(Playlist playlist, String playlistId) {
        //TODO updatePlaylist
    }

    @Override
    public Song addSongToPlaylist(Song song, int userId) {
        return null;
    }

    @Override
    public void deleteSongFromPlaylist(String playlistId, String songId) {
        //TODO deleteSongFromPlaylist
    }

    @Override
    public void updateVotesForSong(int eventId, String songId) {
        //TODO updateVotesForSong
    }

    @Override
    public Playlist getPlayListById(String id) {
        //TODO getPlaylistById
        return null;
    }
}
