package com.techelevator.model;

import java.util.List;

public class Song {
    private int id;
    private String name;
    private String artist;
    private List<Genre> genres;
    private int rating;
    private int votes;

    public Song() {
    }

    public Song(int id, String name, String artist, List<Genre> genres, int rating, int votes) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.genres = genres;
        this.rating = rating;
        this.votes = votes;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
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

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", genres=" + genres +
                ", rating=" + rating +
                ", votes=" + votes +
                '}';
    }
}
