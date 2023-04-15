package com.techelevator.controller;

import com.techelevator.dao.SpotifyDao;
import com.techelevator.model.SpotifyToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SpotifyController {
    private SpotifyDao spotifyDao;

    public SpotifyController(SpotifyDao spotifyDao) {
        this.spotifyDao = spotifyDao;
    }

    @GetMapping("/spotify/token")
    public SpotifyToken getToken() {
        return spotifyDao.getToken();
    }
}
