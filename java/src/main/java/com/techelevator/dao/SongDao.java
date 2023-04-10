package com.techelevator.dao;

import java.security.Principal;
import java.util.List;

public interface SongDao {
    public List<Song> getAllSongs(Principal principal);

    public Song addSong(Principal principal);

    public void updateSong(Song song);

    public void deleteSong(int songId, Principal principal);


}
