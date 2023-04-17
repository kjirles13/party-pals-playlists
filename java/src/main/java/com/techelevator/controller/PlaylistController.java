package com.techelevator.controller;

import com.techelevator.dao.PlaylistDao;
import com.techelevator.dao.SongDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Playlist;
import com.techelevator.model.PlaylistSongDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/playlists")
public class PlaylistController {

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

    @PutMapping("/{playlistId}/songs/{songId}")
    public void addSong(@PathVariable int playlistId, @PathVariable String songId, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        playlistDao.addSongToPlaylist(playlistId, songId, userId);
    }

    @DeleteMapping("/{playlistId}/songs/{songId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSong(@PathVariable int playlistId, @PathVariable String songId, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        playlistDao.deleteSongFromPlaylist(playlistId, songId, userId);
    }

    @PutMapping("/{playlistId}/songs/{songId}/likes")
    public void updateLikes(@PathVariable int playlistId, @PathVariable String songId, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        playlistDao.updateLikes(playlistId, songId, userId);
    }

    @DeleteMapping("/{playlistId}/songs/{songId}/likes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDislikes(@PathVariable int playlistId, @PathVariable String songId, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        playlistDao.updateDislikes(playlistId, songId, userId);
    }

    @PutMapping("/{playlistId}")
    public void updatePlaylist(@PathVariable int playlistId, @RequestBody PlaylistSongDto playlistSongDto, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        playlistDao.updatePlaylist(playlistId, playlistSongDto.getName(), playlistSongDto.getDescription(), userId);
    }
}
