package com.techelevator.dao;

import com.techelevator.model.Playlist;
import com.techelevator.model.Song;

public interface PlaylistDao {

    public void updatePlaylist(int playlistId, Playlist playlist);

    public Song addSongToPlaylist(int playlistId, int songId);

    public void deleteSongFromPlaylist(int playlistId, int songId);

    public void updateVotesForSong(int eventId, int songId);

}
