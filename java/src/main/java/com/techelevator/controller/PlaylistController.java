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

    @PutMapping("/{playlistId}/song/{songId}")
    public void addSong(@PathVariable int playlistId, @PathVariable String songId, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        playlistDao.addSongToPlaylist(playlistId, songId, userId);
    }

    @DeleteMapping("/{playlistId}/song{songId}")
    public ResponseEntity<Object> deleteSong(@PathVariable int playlistId, @PathVariable String songId, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        playlistDao.deleteSongFromPlaylist(playlistId, songId, userId);
        return ResponseEntity.ok().body("Song deleted from playlist successfully");
    }

    @PutMapping("/{playlistId}/song/{songId}/votes")
    public ResponseEntity<Object> updateVotes(@PathVariable int playlistId, @PathVariable String songId, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        playlistDao.updateVotesForSong(playlistId, songId, userId);
        return ResponseEntity.ok().body("Votes for song updated successfully");
    }

    @PutMapping("/{playlistId}")
    public ResponseEntity<Object> updatePlaylist(@RequestBody PlaylistSongDto playlistSongDto, Principal principal){
        int userId = userDao.findIdByUsername(principal.getName());
        try {
            playlistDao.updatePlaylist(playlistSongDto.getPlaylistId(), playlistSongDto.getSongId(), userId);
        }
        catch (ResponseStatusException e){
            e.getMessage();
        }
        return ResponseEntity.ok().body("Playlist updated successfully");
        //TODO update path variable mapping
    }
}
