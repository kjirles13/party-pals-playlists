package com.techelevator.dao;

import com.techelevator.model.Genre;
import com.techelevator.model.Song;
import com.techelevator.model.SongDto;

import java.util.List;

public interface SongDao {
    List<Song> getAllSongs(int userId);

    Song addSong(int userId, Song song);

    void updateSong(SongDto songDto, int userId);

    void deleteSong(String songId, int userId);

    Song getSongById(String songId);

    List<Genre> getGenres();
}
