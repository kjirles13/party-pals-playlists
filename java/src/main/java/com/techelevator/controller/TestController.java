package com.techelevator.controller;

import com.techelevator.dao.SpotifyDao;
import com.techelevator.model.SpotifyToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TestController {
    private SpotifyDao spotifyDao;

    public TestController(SpotifyDao spotifyDao){
        this.spotifyDao = spotifyDao;
    }

    @GetMapping("/test")
    public String[] testMethod(){
        return new String[] {"Hello!", "Greetings from the back end!", "Testing 1 2 3.", "Is this thing on?"};
    }

    @GetMapping("/spotify/login")
    public SpotifyToken getToken() {
        return spotifyDao.getToken();
    }
}
