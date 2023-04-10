package com.techelevator.model;

import java.util.List;

public class Playlist {
    private String name;
    private String description;
    private List<Genre> genres;
    private List<Song> songs;

    public Playlist() {
    }

    public Playlist(String name, String description, List<Genre> genres, List<Song> songs) {
        this.name = name;
        this.description = description;
        this.genres = genres;
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", genres=" + genres +
                ", songs=" + songs +
                '}';
    }
}