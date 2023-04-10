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

    public Event() {
    }

    public Event(int id, String name, String description, LocalDateTime dateTime, Playlist playlist, List<Host> hosts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.playlist = playlist;
        this.hosts = hosts;
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
