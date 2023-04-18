package com.techelevator.dao;

import com.techelevator.model.Playlist;

public interface PlaylistDao {
    Playlist getPlayListById(int playlistId, int userId);

    Playlist createPlaylist(Playlist playlist);

    void addSongToPlaylist(int playlistId, String songId, int userId);

    void deleteSongFromPlaylist(int playlistId, String songId, int userId);

    void updateLikes(int playlistId, String songId);

    void updateDislikes(int playlistId, String songId);

    void updatePlaylist(int playlistId, String name, String description, int userId);

    void vetoSong(int playlistId, String songId, int userId);

    void submitSong(int playlistId, String songId, int userId);
}
