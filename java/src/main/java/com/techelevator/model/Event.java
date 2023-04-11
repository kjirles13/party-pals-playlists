package com.techelevator.model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Event {
    private int id;
    private String name;
    private String description;
    private Date date;
    private Time time;
    private int playlistId;
    private List<Host> hosts;
    private String theme;
    private int djId;


    public Event() {
    }

    public Event(int id, String name, String description, Date date, Time time, int playlistId, List<Host> hosts, String theme, int djId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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
                ", date=" + date +
                ", time=" + time +
                ", playlistId=" + playlistId +
                ", hosts=" + hosts +
                ", theme='" + theme + '\'' +
                ", djId=" + djId +
                '}';
    }
}
