package com.techelevator.dao;

import com.techelevator.model.Song;
import com.techelevator.model.SongDto;
import org.springframework.jdbc.core.JdbcTemplate;

import java.security.Principal;
import java.util.List;

public class JdbcSongDao implements SongDao{

        private JdbcTemplate jdbcTemplate;

    public JdbcSongDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Song> getAllSongs(Principal principal) {
      String sql = "SELECT * FROM songs";
      return jdbcTemplate.query(sql, ((resultSet, rowNum) -> {
          Song song = new Song();
          song.setSongId(resultSet.getInt("song_id"));
          song.setTitle

        })
    }

    @Override
    public Song addSong(String username, Song song) {
        return null;
    }

    @Override
    public void updateSong(SongDto songDto) {

    }

    @Override
    public void deleteSong(int songId, Principal principal) {

    }
}
