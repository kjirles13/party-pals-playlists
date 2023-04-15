<template>

    <div class="event-detail">

        <div v-if="isLoading">Loading...</div>
        <div>
        <h1>{{ this.event.name }}</h1>
        <p>Description: {{ event.description }}</p>
        <p>Theme: {{ event.theme }}</p>
        <p>Date: {{ event.date }}</p>
        <p>Time: {{ event.time }}</p>
        <p>Playlist: {{ event.playlist.name }}</p>
        
        <div v-for="p in event.hosts" :key="p.hostId"> 
            <p>{{ p.name }}</p>
            </div>
        
        <p>{{ event.djUsername }}</p>
        </div>

        
    </div>
</template>

<script>
// import { mapState } from "vuex";
// import axios from 'axios';
import eventService from '../services/EventService';
export default {
    name: "event-detail",
    // props: {Object},
    data() {
        return {
            isLoading: true,
            event: {
                name: '',
                description: '',
                date: '',
                time: '',
                playlist: [],
                hosts: [],
                djUsername: ''
                

            },
            // playlist: {
            //     name: '',
            //     description: ''
            // }
        };
    },

  created() {
    const eventId = parseInt(this.$route.params.id);
    console.log(eventId)
    eventService.getEventById(eventId)
    .then((response) => {
       console.log(response)
        this.event = response;
        this.isLoading = false;
    })
    .catch((error) => {
        console.log(error);
        this.isLoading = false;
        this.error = 'Error loading event'
    })

    
  },

};
</script>

<style scoped>
.event-detail {
    margin: 100px;
}
</style>
