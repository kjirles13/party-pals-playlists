package com.techelevator.controller;

import com.techelevator.dao.PlaylistDao;
import com.techelevator.dao.SongDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Playlist;
import com.techelevator.model.Song;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class PlaylistController{

    private UserDao userDao;
    private SongDao songDao;
    private PlaylistDao playlistDao;

    public PlaylistController(UserDao userDao, SongDao songDao, PlaylistDao playlistDao) {
        this.userDao = userDao;
        this.songDao = songDao;
        this.playlistDao = playlistDao;
    }

    @PutMapping("/playlist/{id}")
    public void updatePlaylist(@RequestBody Playlist playlist, @PathVariable String id){
        try {
            playlistDao.updatePlaylist(playlist, id);
        }
        catch (ResponseStatusException e){
            e.getMessage();
        }
    }

    @PostMapping("playlist/{id}/songs")
    public Song addSongToPlaylist(@PathVariable String id, String songId){

        Playlist playlist = playlistDao.getPlayListById(id);

        Song song = songDao.getSongById(songId);

        playlist.getSongs().add(song);

        playlistDao.updatePlaylist(playlist, id);

        return song;
    }


    @DeleteMapping("/playlist/{playlistId}/songs/{songId}")
    public void deleteSongFromPlaylist(@PathVariable String playlistId, @PathVariable String songId){
        try {
            playlistDao.deleteSongFromPlaylist(playlistId, songId);
        }
        catch (ResponseStatusException e){
            e.getMessage();
        }
    }





}
