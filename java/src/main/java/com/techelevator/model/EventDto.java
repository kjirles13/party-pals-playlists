package com.techelevator.model;

import java.sql.Time;
import java.time.LocalDate;

public class EventDto {
    private String name;
    private String description;
    private LocalDate date;
    private Time time;
    private String theme;

    public EventDto() {
    }

    public EventDto(String name, String description, LocalDate date, Time time, String theme) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.theme = theme;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", theme='" + theme + '\'' +
                '}';
    }
}
