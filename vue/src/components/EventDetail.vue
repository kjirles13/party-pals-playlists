<template>
    <div class="event-detail">
        <h1>{{ event.title }}</h1>
        <p>{{ event.description }}</p>
        <p>{{ event.theme }}</p>
        <p>{{ event.date_time }}</p>
        <p>{{ event.playlist.name }}</p>
        <p>{{ event.host.username }}</p>
        <p>{{ event.role.role_name }}</p>
        <ul>
            <li v-for="song in event.playlist.songs" :key="song.song_id">{{ song.title }}</li>
        </ul>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            event: null,
        };
    },
    mounted() {
        this.getEvent();
    },
    methods: {
        getEvent() {
            const eventId = this.$route.params.id;
            axios.get(`http://localhost:9000/events/${eventId}`)
            .then(response => {
                this.event = response.data;
            })
            .catch(error => {
                console.error(error);
            });
        },
    },
};
</script>

<style scoped>
.event-detail {
    margin: 20px;
}
</style>
