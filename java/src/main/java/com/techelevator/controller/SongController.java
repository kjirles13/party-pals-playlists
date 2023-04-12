package com.techelevator.controller;

import com.techelevator.dao.SongDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Song;
import com.techelevator.model.SongDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/songs")
public class SongController {
    private SongDao songDao;
    private UserDao userDao;

    public SongController(SongDao songDao, UserDao userDao) {
        this.songDao = songDao;
        this.userDao = userDao;
    }

    @GetMapping("")
    public List<Song> listSongs(Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        return songDao.getAllSongs(userId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Song addSong(@RequestBody Song song, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        return songDao.addSong(userId, song);
    }

    @PutMapping("")
    public ResponseEntity<Object> updateSong(@RequestBody SongDto songDto, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        songDao.updateSong(songDto, userId);
        return new ResponseEntity<>("Song updated successfully", HttpStatus.OK);
    }

    @GetMapping("/{songId}")
    public Song getSongById(@PathVariable String songId) {
        return songDao.getSongById(songId);
    }

    @DeleteMapping("/{songId}")
    public ResponseEntity<Object> deleteSong(@PathVariable String songId, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        songDao.deleteSong(songId, userId);
        return new ResponseEntity<>("Song deleted successfully", HttpStatus.OK);
    }

    //TODO implement try/catch for calls
}
