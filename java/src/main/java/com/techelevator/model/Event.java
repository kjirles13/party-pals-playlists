package com.techelevator.model;

import java.time.LocalDateTime;
import java.util.List;

public class Event {
    private int id;
    private String name;
    private String description;
    private LocalDateTime dateTime;
    private Playlist playlist;
    private List<Host> hosts;
    private String theme;

    public Event() {
    }

    public Event(int id, String name, String description, LocalDateTime dateTime, Playlist playlist, List<Host> hosts, String theme) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.playlist = playlist;
        this.hosts = hosts;
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", playlist=" + playlist +
                ", hosts=" + hosts +
                '}';
    }
}
