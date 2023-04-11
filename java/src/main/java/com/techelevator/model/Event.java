package com.techelevator.model;

import java.sql.Timestamp;
import java.util.List;

public class Event {
    private int id;
    private String name;
    private String description;
    private Timestamp dateTime;
    private int playlistId;
    private List<Host> hosts;
    private String theme;
    private int djId;


    public Event() {
    }

    public Event(int id, String name, String description, Timestamp dateTime, int playlistId, List<Host> hosts, String theme, int djId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.playlistId = playlistId;
        this.hosts = hosts;
        this.theme = theme;
        this.djId = djId;
    }

    public int getDjId() {
        return djId;
    }

    public void setDjId(int djId) {
        this.djId = djId;
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


    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
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
                ", playlistId=" + playlistId +
                ", hosts=" + hosts +
                ", theme='" + theme + '\'' +
                ", djId=" + djId +
                '}';
    }
}
