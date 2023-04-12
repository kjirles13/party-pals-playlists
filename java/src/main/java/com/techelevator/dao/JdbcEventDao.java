package com.techelevator.dao;

import com.techelevator.model.Artist;
import com.techelevator.model.Event;
import com.techelevator.model.Host;
import com.techelevator.model.Playlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEventDao implements EventDao {

    private JdbcTemplate jdbcTemplate;
    private Host host;

    public JdbcEventDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> allEvents = new ArrayList<>();
        String sql = "SELECT event_id, event_name, dj_id, description, playlist_id, date, time, theme " +
                "FROM public.events";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {

            Event event = mapRowToEvent(results);

            String hostSql = "SELECT users.user_id, users.username " +
                    "FROM public.users " +
                    "JOIN host_event on users.user_id = host_event.user_id " +
                    "WHERE host_event.event_id = ?";

            SqlRowSet hostResults = jdbcTemplate.queryForRowSet(hostSql, event.getId());

            List<Host> hosts = new ArrayList<>();

            while (hostResults.next()) {
                Host host = mapRowToHost(hostResults);

                hosts.add(host);
            }

            event.setHosts(hosts);
            allEvents.add(event);
        }

        return allEvents;
    }

    @Override
    public Event createEvent(Event event, int userId) {
        return null;
    }

    @Override
    public Event getEventById(int eventId) {
        Event event = new Event();

        String sqlEvent ="SELECT event_id, event_name, dj_id, description, playlist_id, date, time, theme " +
                "FROM public.events " +
                "WHERE event_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlEvent, eventId);

        if (results.next()) {
            event = mapRowToEvent(results);
        }

        String hostSql = "SELECT users.user_id, users.username " +
                "FROM public.users " +
                "JOIN host_event on users.user_id = host_event.user_id " +
                "WHERE host_event.event_id = ?";

        SqlRowSet hostResults = jdbcTemplate.queryForRowSet(hostSql, event.getId());

        List<Host> hosts = new ArrayList<>();

        while (hostResults.next()) {
            Host host = mapRowToHost(hostResults);
            hosts.add(host);
        }
        event.setHosts(hosts);

        return event;
    }

    @Override
    public void updateEvent(int eventId) {
    }

    @Override
    public void deleteEvent(int eventId) {
    }

    @Override
    public Host addHostToEvent(int eventId, List<Host> hosts) {
        return null;
    }

    @Override
    public void deleteHostFromEvent(int eventId, List<Host> hosts) {

    }

    private Event mapRowToEvent(SqlRowSet rs) {
        Event event = new Event();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        event.setId(rs.getInt("event_id"));
        event.setName(rs.getString("event_name"));
        event.setDjId(rs.getInt("dj_id"));
        event.setDescription(rs.getString("description"));
        event.setPlaylistId(rs.getInt("playlist_id"));
        event.setDate(rs.getDate("date"));
        event.setTime(rs.getTime("time"));
        event.setTheme(rs.getString("theme"));

        return event;
    }

    private Host mapRowToHost(SqlRowSet rs) {
        Host host = new Host();

        host.setId(rs.getInt("user_id"));
        host.setName(rs.getString("username"));

        return host;
    }

}
