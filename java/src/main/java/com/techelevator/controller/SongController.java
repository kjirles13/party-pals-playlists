package com.techelevator.controller;

import com.techelevator.dao.SongDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Song;
import com.techelevator.model.SongDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

@CrossOrigin
@RestController
public class SongController {

    private SongDao songDao;
    private UserDao userDao;

    public SongController(SongDao songDao, UserDao userDao) {
        this.songDao = songDao;
        this.userDao = userDao;
    }


    @GetMapping("/songs")
    public List<Song> listSongs(Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        return songDao.getAllSongs(userId);
    }

    @PostMapping("/songs")
    public Song addSong(@RequestBody Song song, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        return songDao.addSong(userId, song);
    }

    @GetMapping("/songs/{songId}")
    public Song getSongById(@PathVariable String songId) {
        return songDao.getSongById(songId);
    }


    @PutMapping("/songs")
    public void updateSong(@RequestBody SongDto songDto) {

        try {
            songDao.updateSong(songDto);
        } catch (ResponseStatusException e) {
            e.getMessage();
        }
        //TODO test updateSong
    }

    //TODO add deleteSong
    //TODO test deleteSong
}
