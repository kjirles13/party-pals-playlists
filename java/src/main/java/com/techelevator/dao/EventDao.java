package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.EventDto;
import com.techelevator.model.Host;
import java.util.List;

public interface EventDao {

    List<Event> getAllEvents();

    Event createEvent(Event event, int userId);

    Event getEventById(int eventId);

    void updateEvent(EventDto eventInfo, int eventId);

    void deleteEvent(int eventId);

    void updateHosts(int eventId, List<Host> hosts);
}
