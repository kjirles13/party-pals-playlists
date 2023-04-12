package com.techelevator.dao;

import com.techelevator.model.Playlist;
import com.techelevator.model.Song;

public interface PlaylistDao {
    public Playlist getPlayListById(int playlistId, int userId);

    public void addSongToPlaylist(int playlistId, String songId, int userId);

    public void deleteSongFromPlaylist(int playlistId, String songId, int userId);

    public void updateVotesForSong(int playlistId, String songId, int userId);

    public void updatePlaylist(int playlistId, String songId, int userId);
}
