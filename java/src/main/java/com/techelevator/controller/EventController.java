package com.techelevator.controller;

import com.techelevator.dao.EventDao;
import com.techelevator.dao.PlaylistDao;
import com.techelevator.dao.SongDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Event;
import com.techelevator.model.Host;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    private UserDao userDao;
    private SongDao songDao;
    private PlaylistDao playlistDao;
    private EventDao eventDao;

    //TODO clean up code

    public EventController(UserDao userDao, SongDao songDao, PlaylistDao playlistDao, EventDao eventDao) {
        this.userDao = userDao;
        this.songDao = songDao;
        this.playlistDao = playlistDao;
        this.eventDao = eventDao;
    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventDao.getAllEvents();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users/{userId}/events")
    public Event createEvent(@RequestBody Event event, @PathVariable int userId) {
        return eventDao.createEvent(event, userId);
    }


    @GetMapping("/events/{eventId}")
    public Event getEventById(@PathVariable int eventId) {
        return eventDao.getEventById(eventId);
    }

    @PutMapping("/events/{eventId}")
    public void updateEvent(@RequestBody Event event, @PathVariable int eventId) {
        event.setId(eventId);
        eventDao.updateEvent(event.getId());
    }

    @DeleteMapping("/events/{eventId}")
    public void deleteEvent(@PathVariable int eventId) {
        eventDao.deleteEvent(eventId);
    }

    @PostMapping("/events/{eventId}/hosts")
    public Host addHostToEvent(@RequestBody Host host, @PathVariable int eventId) {
        return eventDao.addHostToEvent(eventId, host);
    }

    @DeleteMapping("/events/{eventId}/hosts/{hostId}")
    public void deleteHostFromEvent(@PathVariable int eventId, @PathVariable int hostId) {
        Host host = new Host();
        host.setId(hostId);
        eventDao.deleteHostFromEvent(eventId, host);
    }


}
