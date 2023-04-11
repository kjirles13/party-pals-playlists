package com.techelevator.dao;

import com.techelevator.model.Playlist;
import com.techelevator.model.Song;

public interface PlaylistDao {
    //playlist dto? // TODO playlist dto or playlist (I think we should change songs one at a time and only update title and description with this)
    public void updatePlaylist(Playlist playlist, String playlistId);

    //don't think we need this method
    public Song addSongToPlaylist(String playlistId, String songId);

    public void deleteSongFromPlaylist(String playlistId, String songId);

    public void updateVotesForSong(int eventId, String songId);

    public Playlist getPlayListById(String id);

}
