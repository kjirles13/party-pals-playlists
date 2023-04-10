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


}
