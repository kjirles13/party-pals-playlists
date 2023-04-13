package com.techelevator.dao;

import com.techelevator.model.Playlist;

public interface PlaylistDao {
    public Playlist getPlayListById(int playlistId, int userId);

    public Playlist createPlaylist(Playlist playlist);

    public void addSongToPlaylist(int playlistId, String songId, int userId);

    public void deleteSongFromPlaylist(int playlistId, String songId, int userId);

    public void updateLikes(int playlistId, String songId, int userId);

    public void updateDislikes(int playlistId, String songId, int userId);

    public void updatePlaylist(int playlistId, String name, String description, int userId);
}
