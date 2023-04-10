package com.techelevator.model;

import java.util.List;

public class Song {
    private int id;
    private String name;
    private List<Artist> artists;
    private List<Genre> genres;
    private int rating;
    private int votes;
    private String preview;
    private String spotifyUri;

    public Song() {
    }

    public Song(int id, String name, List<Artist> artists, List<Genre> genres, int rating, int votes, String preview, String spotifyUri) {
        this.id = id;
        this.name = name;
        this.artists = artists;
        this.genres = genres;
        this.rating = rating;
        this.votes = votes;
        this.preview = preview;
        this.spotifyUri = spotifyUri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getSpotifyUri() {
        return spotifyUri;
    }

    public void setSpotifyUri(String spotifyUri) {
        this.spotifyUri = spotifyUri;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artists=" + artists +
                ", genres=" + genres +
                ", rating=" + rating +
                ", votes=" + votes +
                ", preview='" + preview + '\'' +
                ", spotifyUri='" + spotifyUri + '\'' +
                '}';
    }
}
