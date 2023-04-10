package com.techelevator.dao;

import java.security.Principal;
import java.util.List;

public interface SongDao {
    public List<Song> getAllSongs(Principal principal);

    public Song addSong(Principal principal);

    
}
