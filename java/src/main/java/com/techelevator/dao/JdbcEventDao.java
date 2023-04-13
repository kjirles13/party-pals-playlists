package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.Host;
import com.techelevator.model.Playlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEventDao implements EventDao {
    private JdbcTemplate jdbcTemplate;
    private PlaylistDao playlistDao;

    public JdbcEventDao(JdbcTemplate jdbcTemplate, PlaylistDao playlistDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.playlistDao = playlistDao;
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> allEvents = new ArrayList<>();
        String sql = "SELECT events.event_id, events.event_name, events.description, events.playlist_id, events.date, events.\"time\", events.theme, events.dj_id, users.username " +
                "FROM public.events " +
                "JOIN users on events.dj_id = users.user_id";

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
                hosts.add(mapRowToHost(hostResults));
            }
            event.setHosts(hosts);

            Playlist playlist = playlistDao.getPlayListById(results.getInt("playlist_id"), results.getInt("dj_id"));
            event.setPlaylist(playlist);

            allEvents.add(event);
        }

        return allEvents;
    }

    @Override
    public Event createEvent(Event event, int userId) {
        int playlistId = playlistDao.createPlaylist(event.getPlaylist()).getPlaylistId();

        String sqlEvent = "INSERT INTO public.events( " +
                "event_name, dj_id, description, playlist_id, date, time, theme) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING event_id";

        int eventId = jdbcTemplate.queryForObject(sqlEvent, int.class, event.getName(), userId, event.getDescription(), playlistId, event.getDate(), event.getTime(), event.getTheme());

        return this.getEventById(eventId);
    }

    @Override
    public Event getEventById(int eventId) {
        Event event = new Event();

        String sqlEvent = "SELECT events.event_id, events.event_name, events.description, events.playlist_id, events.date, events.\"time\", events.theme, events.dj_id, users.username " +
                "FROM public.events " +
                "JOIN users on events.dj_id = users.user_id " +
                "WHERE events.event_id = ?";

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

        Playlist playlist = playlistDao.getPlayListById(results.getInt("playlist_id"), results.getInt("dj_id"));
        event.setPlaylist(playlist);

        return event;
    }

    @Override
    public void updateEvent(Event event, int eventId) {

//        String sql = "UPDATE public.events\n" +
//                "\tSET event_name=?, description=?, date=?, time=?, theme=?\n" +
//                "\tWHERE event_id =?";
//
//        jdbcTemplate.update(sql, event.getName(), event.getDescription(), event.getDate(),
//                event.getTime(), event.getTheme(), eventId);
//
//        this.updateHosts(eventId, eve);

    }

    @Override
    public void deleteEvent(int eventId) {
    }

    @Override
    public void updateHosts(int eventId, List<Host> hosts) {
        String deleteSql = "DELETE FROM public.host_event WHERE event_id = ?";
        jdbcTemplate.update(deleteSql, eventId);

        String insertSql = "INSERT INTO public.host_event (user_id, event_id) VALUES (?, ?)";

        for (Host host : hosts) {
            jdbcTemplate.update(insertSql, host.getId(), eventId);
        }
    }

    private Event mapRowToEvent(SqlRowSet rs) {
        Event event = new Event();

        event.setId(rs.getInt("event_id"));
        event.setName(rs.getString("event_name"));
        event.setDescription(rs.getString("description"));
        event.setDate(LocalDate.parse(rs.getString("date")));
        event.setTime(rs.getTime("time"));
        event.setTheme(rs.getString("theme"));
        event.setDjUsername(rs.getString("username"));

        return event;
    }

    private Host mapRowToHost(SqlRowSet rs) {
        Host host = new Host();

        host.setId(rs.getInt("user_id"));
        host.setName(rs.getString("username"));

        return host;
    }
}
