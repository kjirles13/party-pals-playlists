package com.techelevator.dao;

import com.techelevator.model.Song;
import com.techelevator.model.SongDto;

import java.util.List;

public interface SongDao {
    public List<Song> getAllSongs(int userId);

    public Song addSong(int userId, Song song);

    public void updateSong(SongDto songDto, int userId);

    public void deleteSong(String songId, int userId);

    public Song getSongById(String songId);

}
