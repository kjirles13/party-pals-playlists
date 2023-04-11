package com.techelevator.dao;

<<<<<<< HEAD
import com.techelevator.model.Song;
import com.techelevator.model.SongDto;
import org.springframework.jdbc.core.JdbcTemplate;

import java.security.Principal;
import java.util.List;

public class JdbcSongDao implements SongDao{

        private JdbcTemplate jdbcTemplate;
=======
import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSongDao implements SongDao {

    private JdbcTemplate jdbcTemplate;
    Song song = new Song();
    Playlist playlist = new Playlist();

    public JdbcSongDao() {
    }
>>>>>>> 7284964d3196e39dea7eee1ef2e4c7c0d461e6de

    public JdbcSongDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

<<<<<<< HEAD

    @Override
    public List<Song> getAllSongs(Principal principal) {
      String sql = "SELECT * FROM songs";
      return jdbcTemplate.query(sql, ((resultSet, rowNum) -> {
          Song song = new Song();
          song.setSongId(resultSet.getInt("song_id"));
          song.setTitle

        })
=======
    @Override
    public List<Song> getAllSongs(Principal principal) {

        List<Song> allSongs = new ArrayList<>();
        



        return null;
>>>>>>> 7284964d3196e39dea7eee1ef2e4c7c0d461e6de
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
<<<<<<< HEAD
=======

    @Override
    public Song getSongById(String songId) {
        return null;
    }

    private Song mapRowToSong(SqlRowSet rs) {
        Song song = new Song();

        song.setId(rs.getInt("song_id"));
        song.setName(rs.getString("title"));
        //song.setArtists(rs.getObject("artist"));

//        private int id;
//        private String name;
//        private List<Artist> artists;
//        private List<Genre> genres;
//        private int rating;
//        private int votes;
//        private String preview;
//        private String spotifyUri;

//        Song song = new Song();
//        song.setId(rs.getInt("id"));
//        song.setName(rs.getString("name"));
//        song.setRating(rs.getInt("rating"));
//        song.setVotes(rs.getInt("votes"));
//        song.setPreview(rs.getString("preview"));
//        song.setSpotifyUri(rs.getString("spotifyUri"));
//
//        List<Artist> artists = new ArrayList<>();
//        Array artistsArray = rs.getList("artists");
//        if (artistsArray != null) {
//            Object[] artistObjects = (Object[]) artistsArray.getArray();
//            for (Object artistObject : artistObjects) {
//                Artist artist = new Artist();
//                artist.setName((String) artistObject);
//                artists.add(artist);
//            }
//        }
//        song.setArtists(artists);

        return song;
    }
>>>>>>> 7284964d3196e39dea7eee1ef2e4c7c0d461e6de
}
