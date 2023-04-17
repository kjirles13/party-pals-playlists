package com.techelevator.dao;

import com.techelevator.model.*;

import java.util.List;

public interface EventDao {

    List<Event> getAllEvents();

    Event createEvent(Event event, int userId);

    Event getEventById(int eventId);

    void updateEvent(EventDto eventInfo, int eventId);

    void deleteEvent(int eventId);

    void addHost(int eventId, int userId);

    void deleteHost(int eventId, int userId);

    Playlist getPlaylistForEventById(int playlistId, int userId);
}
