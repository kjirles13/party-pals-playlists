package com.techelevator.dao;

import com.techelevator.model.Playlist;
import com.techelevator.model.Song;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
        return null;
    }

    @Override
    public Song addSongToPlaylist(int playlistId, String songId, int userId) {
        return null;
    }

    @Override
    public void deleteSongFromPlaylist(int playlistId, String songId, int userId) {

    }

    @Override
    public void updateVotesForSong(int playlistId, int votes, int userId) {

    }

    @Override
    public void updatePlaylist(int playlistId, String songId, int userId) {

    }
}
