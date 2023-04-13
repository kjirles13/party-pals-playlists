package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.Host;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEventDao implements EventDao {

    private JdbcTemplate jdbcTemplate;
    private Host host;
    private Event event;

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
        Event createdEvent = new Event();

        String sqlEvent = "INSERT INTO public.events( " +
                "event_name, dj_id, description, playlist_id, date, time, theme) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";

//        jdbcTemplate.update(sqlEvent, event.getName(), event.getDjId(), event.getDescription())

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
    public void updateEvent(Event event, int eventId) {

        String sql = "UPDATE public.events\n" +
                "\tSET event_name=?, description=?, date=?, time=?, theme=?\n" +
                "\tWHERE event_id =?";

        jdbcTemplate.update(sql, event.getName(), event.getDescription(), event.getDate(),
                event.getTime(), event.getTheme(), eventId);

//        String hostSql = "UPDATE public.host_event\n" +
//                "\tSET user_id=?, event_id=?\n" +
//                "\tWHERE event_id =?";

        String deleteSql = "DELETE FROM public.host_event WHERE event_id = ?";
        jdbcTemplate.update(deleteSql, eventId);

        String insertSql = "INSERT INTO public.host_event (user_id, event_id) VALUES (?, ?)";
        List<Host> hosts = event.getHosts();
        for (Host host : hosts) {
            jdbcTemplate.update(insertSql, host.getId(), eventId);
        }

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
