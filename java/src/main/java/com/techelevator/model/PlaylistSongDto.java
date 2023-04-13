package com.techelevator.model;

public class PlaylistSongDto {
    private String name;
    private String description;


    //TODO make this only String name and String description

    public PlaylistSongDto() {
    }

    public PlaylistSongDto(String name, String description) {
        this.name = name;
        this.description = description;
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

    @Override
    public String toString() {
        return "PlaylistSongDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
