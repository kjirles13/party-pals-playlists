package com.techelevator.model;

public class PlaylistSongDto {
    private int playlistId;
    private String songId;
    private int votes;

    public PlaylistSongDto() {
    }

    public PlaylistSongDto(int playlistId, String songId, int votes) {
        this.playlistId = playlistId;
        this.songId = songId;
        this.votes = votes;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "PlaylistSongDto{" +
                "playlistId=" + playlistId +
                ", songId=" + songId +
                ", votes=" + votes +
                '}';
    }
}
