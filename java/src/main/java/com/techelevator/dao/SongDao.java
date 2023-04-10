package com.techelevator.dao;

import com.techelevator.model.Song;
import com.techelevator.model.SongDto;

import java.security.Principal;
import java.util.List;

public interface SongDao {
    public List<Song> getAllSongs(Principal principal);

    public Song addSong(String username, Song song);

    public void updateSong(SongDto songDto);

    public void deleteSong(int songId, Principal principal);


}
