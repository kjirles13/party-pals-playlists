package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.Host;
import com.techelevator.model.Playlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEventDao implements EventDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcEventDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Event> getAllEvents(int userId) {
        List<Event> allEvents = new ArrayList<>();
        String sql = "SELECT event_id, dj_id, description, playlist_id, date_time, theme\n" +
                "\tFROM public.events where dj_id = ?";


        return null;
    }

    @Override
    public Event createEvent(Event event, int userId) {
        return null;
    }

    @Override
    public Event getEventById(int eventId) {
        return null;
    }

    @Override
    public void updateEvent(int eventId) {

    }

    @Override
    public void deleteEvent(int eventId) {

    }

    @Override
    public Host addHostToEvent(int eventId, Host host) {
        return null;
    }

    @Override
    public void deleteHostFromEvent(int eventId, Host host) {

    }

    private Event mapRowToEvent(SqlRowSet rs){
        Event event = new Event();

        event.setId(rs.getInt("event_id"));
        event.setName(rs.getString("event_name"));
        event.setDescription(rs.getString("description"));
        event.setDateTime(rs.getTimestamp("date_time"));
        event.setPlaylistId(rs.getInt("playlist_id"));
        event.setTheme(rs.getString("theme"));
        return event;
    }


    

}
