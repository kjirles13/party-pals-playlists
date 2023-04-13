<template>
    <div class="event-list">
        <h1> Event List </h1>
        <table>
            <thead>
                <tr>
                    <!-- <th>Event ID</th>
                    <th>User ID</th>
                    <th>Role ID</th>
                    <th>From Date</th>
                    <th>To Date</th> -->
                    <th>Description</th>
                    <!-- <th>Playlist ID</th> -->
                    <th>Date Time</th>
                    <th>Theme</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="event in events" :key="event.event_id">
                    <!-- <td>{{ event.event_id}}</td>
                    <td>{{ event.user_id }}</td>
                    <td>{{ event.role_id }}</td>
                    <td>{{ event.from_date }}</td>
                    <td>{{ event.to_date }}</td> -->
                    <td>{{ event.description }}</td>
                    <!-- <td>{{ event.playlist_id }}</td> -->
                    <td>{{ event.date_time }}</td>
                    <td>{{ event.theme }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script> 
import eventService from "../services/EventService"

export default{
    name: 'EventList',
    data() {
        return {
            events: this.$store.state.events,
        };
    },
    created() {
        eventService.getAllEvents().then(response => {
            if (response.status == 200) {
                this.$store.commit("SET_EVENTS", response.data);
            }
        });
    },
};
</script>

<style>
.event-list {
    margin-top: 20px;
}

table {
    border-collapse: collapse;
    width:100%;
}

th, td {
    text-align: left;
    padding: 8px;
    border-bottom: 1px solid #ddd;
}

th {
    background-color: #4caf50;
    color: white;
}
</style>