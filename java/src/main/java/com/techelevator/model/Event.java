package com.techelevator.model;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public class Event {
    private int id;
    private String name;
    private String description;
    private LocalDate date;
    private Time time;
    private Playlist playlist;
    private List<Host> hosts;
    private String theme;
    private String djUsername;


    public Event() {
    }

    public Event(int id, String name, String description, LocalDate date, Time time, Playlist playlist, List<Host> hosts, String theme, String djUsername) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.playlist = playlist;
        this.hosts = hosts;
        this.theme = theme;
        this.djUsername = djUsername;
    }

    public String getDjUsername() {
        return djUsername;
    }

    public void setDjUsername(String djUsername) {
        this.djUsername = djUsername;
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", playlist=" + playlist +
                ", hosts=" + hosts +
                ", theme='" + theme + '\'' +
                ", djUsername='" + djUsername + '\'' +
                '}';
    }
}
