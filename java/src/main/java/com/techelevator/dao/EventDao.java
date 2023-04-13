package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.Host;
import java.util.List;

public interface EventDao {

    public List<Event> getAllEvents();

    public Event createEvent(Event event, int userId);

    public Event getEventById(int eventId);

    public void updateEvent(Event event, int eventId);

    public void deleteEvent(int eventId);

    public void updateHosts(int eventId, List<Host> hosts);

}
