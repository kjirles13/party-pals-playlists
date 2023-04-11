package com.techelevator.dao;

import com.techelevator.model.Playlist;
import com.techelevator.model.Song;

public interface PlaylistDao {

    public void updatePlaylist(Playlist playlist, String playlistId);

    //don't think we need this method
    public Song addSongToPlaylist(String playlistId, String songId);

    public void deleteSongFromPlaylist(String playlistId, String songId);

    public void updateVotesForSong(int eventId, String songId);

    public Playlist getPlayListById(String id);

}
