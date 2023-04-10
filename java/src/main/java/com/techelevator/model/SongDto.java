package com.techelevator.model;

import java.util.List;

public class SongDto {
    private int id;
    private List<Genre> genres;
    private int rating;

    public SongDto() {
    }

    public SongDto(int id, List<Genre> genres, int rating) {
        this.id = id;
        this.genres = genres;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "SongDto{" +
                "id=" + id +
                ", genres=" + genres +
                ", rating=" + rating +
                '}';
    }
}
