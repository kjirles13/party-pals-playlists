package com.techelevator.dao;

import com.techelevator.model.Playlist;
import com.techelevator.model.Song;
import org.springframework.stereotype.Component;

@Component
public class JdbcPlaylistDao implements PlaylistDao{
    @Override
    public void updatePlaylist(Playlist playlist, String playlistId) {
        //TODO updatePlaylist
    }

    @Override
    public Song addSongToPlaylist(String playlistId, String songId) {
        //TODO addSongToPlaylist
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
