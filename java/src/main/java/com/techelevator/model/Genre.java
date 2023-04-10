package com.techelevator.model;

public class Genre {
    private String name;
    private int id;

    public Genre() {
    }

    public Genre(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
