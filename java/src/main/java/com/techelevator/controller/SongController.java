package com.techelevator.controller;

import com.techelevator.dao.SongDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Song;
import com.techelevator.model.SongDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
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
    public List<Song> listSongs(Principal principal){
        return songDao.getAllSongs(principal);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/songs")
    public Song addSong(Principal principal, @RequestBody Song song){
            song.setId(0);

            Song addedSong = songDao.addSong(principal.getName(), song);

            return addedSong;
    }

    @PutMapping("/songs")
    public void updateSong(@RequestBody SongDto songDto){

      try {
          songDao.updateSong(songDto);
      }
      catch (ResponseStatusException e){
          e.getMessage();
      }


    }



}
