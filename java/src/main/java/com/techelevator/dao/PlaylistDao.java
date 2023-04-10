package com.techelevator.dao;

public interface PlaylistDao {

    public void updatePlaylist(int playlistId, Playlist playlist);

    public Song addSongToPlaylist(int playlistId, int songId);

    public void deleteSongFromPlaylist(int playlistId, int songId);

    public void updateVotesForSong(int eventId, int songId);

}
