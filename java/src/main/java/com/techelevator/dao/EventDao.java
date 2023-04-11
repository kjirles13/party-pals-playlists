package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.Host;

import java.security.Principal;
import java.util.List;

public interface EventDao {

    public List<Event> getAllEvents(int userId);

    public Event createEvent(Event event, int userId);

    public Event getEventById(int eventId);

    public void updateEvent(int eventId);

    public void deleteEvent(int eventId);

    public Host addHostToEvent(int eventId, Host host);

    public void deleteHostFromEvent(int eventId, Host host);

}
