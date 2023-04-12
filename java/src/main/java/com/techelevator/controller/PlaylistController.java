package com.techelevator.controller;

import com.techelevator.dao.PlaylistDao;
import com.techelevator.dao.SongDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Playlist;
import com.techelevator.model.PlaylistSongDto;
import com.techelevator.model.Song;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/playlist")
public class PlaylistController{

    private UserDao userDao;
    private SongDao songDao;
    private PlaylistDao playlistDao;

    public PlaylistController(UserDao userDao, SongDao songDao, PlaylistDao playlistDao) {
        this.userDao = userDao;
        this.songDao = songDao;
        this.playlistDao = playlistDao;
    }

    @GetMapping("/{playlistId}")
    public Playlist getPlaylistById(@PathVariable int playlistId, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        return playlistDao.getPlayListById(playlistId, userId);
    }

    @PutMapping("/song")
    public void addSong(@RequestBody PlaylistSongDto playlistSongDto, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        playlistDao.addSongToPlaylist(playlistSongDto.getPlaylistId(), playlistSongDto.getSongId(), userId);
    }

    @DeleteMapping("/song")
    public ResponseEntity<Object> deleteSong(@RequestBody PlaylistSongDto playlistSongDto, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        playlistDao.deleteSongFromPlaylist(playlistSongDto.getPlaylistId(), playlistSongDto.getSongId(), userId);
        return ResponseEntity.ok().body("Song deleted from playlist successfully");
    }

    @PutMapping("/song/votes")
    public ResponseEntity<Object> updateVotes(@RequestBody PlaylistSongDto playlistSongDto, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        playlistDao.updateVotesForSong(playlistSongDto.getPlaylistId(), playlistSongDto.getVotes(), userId);
        return ResponseEntity.ok().body("Votes for song updated successfully");
    }

    @PutMapping("")
    public ResponseEntity<Object> updatePlaylist(@RequestBody PlaylistSongDto playlistSongDto, Principal principal){
        int userId = userDao.findIdByUsername(principal.getName());
        try {
            playlistDao.updatePlaylist(playlistSongDto.getPlaylistId(), playlistSongDto.getSongId(), userId);
        }
        catch (ResponseStatusException e){
            e.getMessage();
        }
        return ResponseEntity.ok().body("Playlist updated successfully");
    }
}
