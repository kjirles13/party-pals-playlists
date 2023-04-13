package com.techelevator.controller;

import com.techelevator.dao.EventDao;
import com.techelevator.dao.PlaylistDao;
import com.techelevator.dao.SongDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Event;
import com.techelevator.model.Host;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/events")
public class EventController {
    private UserDao userDao;
    private SongDao songDao;
    private PlaylistDao playlistDao;
    private EventDao eventDao;

    public EventController(UserDao userDao, SongDao songDao, PlaylistDao playlistDao, EventDao eventDao) {
        this.userDao = userDao;
        this.songDao = songDao;
        this.playlistDao = playlistDao;
        this.eventDao = eventDao;
    }

    @GetMapping("")
    public List<Event> getAllEvents() {
        return eventDao.getAllEvents();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestBody Event event, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        return eventDao.createEvent(event, userId);
    }

    @GetMapping("/{eventId}")
    public Event getEventById(@PathVariable int eventId) {
        return eventDao.getEventById(eventId);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Object> updateEvent(@RequestBody Event event, @PathVariable int eventId) {
        event.setId(eventId);
        eventDao.updateEvent(event, event.getId());
        return ResponseEntity.ok().body("Event updated successfully");
    }

    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable int eventId) {
        eventDao.deleteEvent(eventId);
    }

    @PostMapping("/{eventId}/hosts")
    public ResponseEntity<Object> addHostToEvent(@RequestBody List<Host> hosts, @PathVariable int eventId) {
        eventDao.addHostToEvent(eventId, hosts);
        return ResponseEntity.ok().body("Hosts added to event successfully");
    }

    @DeleteMapping("/{eventId}/hosts")
    public ResponseEntity<Object> deleteHostFromEvent(@PathVariable int eventId, @RequestBody List<Host> hosts) {
        eventDao.deleteHostFromEvent(eventId, hosts);
        return ResponseEntity.ok().body("Hosts deleted from event successfully");
    }
}
