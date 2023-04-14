<template>



    <div class="event-detail">

        <h1>{{ event.name }}</h1>
        <p>{{ event.description }}</p>
        <p>{{ event.theme }}</p>
        <p>{{ event.date }}</p>
        <p>{{ event.time }}</p>
        <p>{{ event.playlist.name }}</p>
        <p>{{ event.host.username }}</p>
        <p>{{ event.role.role_name }}</p>

        <!-- <h1>{{ event.title }}</h1>
        <p>{{ event.description }}</p>
        <p>{{ event.theme }}</p>
        <p>{{ event.date_time }}</p>
        <p>{{ event.playlist.name }}</p>
        <p>{{ event.host.username }}</p>
        <p>{{ event.role.role_name }}</p> -->
        <ul>
            <li v-for="song in event.playlist.songs" :key="song.song_id">{{ song.title }}</li>
        </ul>
    </div>
</template>

<script>
import axios from 'axios';
import eventService from '../services/EventService';
export default {

    name: "EventDetails",

    data() {
        return {
            event: null,
        };
    },

    created() {
  console.log("EventDetails created");
  const eventId = this.$route.params.id;
  eventService.getEventById(eventId)
  .then(response => {
    if (response.status == 200) {
      console.log("EventDetails response data", response.data);
      this.event = response.data;
    }
  })
  .catch(error => {
    console.log(error);
  });
},

    // created(){
    //     const eventId = this.$route.params.id;
    //     this.event = this.$store.state.events.find(
    //         (event) => event.event_id === eventId
    //     )
    // },

    // mounted() {
    //     this.getEvent();
    // },
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

        

        getEventById() {
    const eventId = this.$route.params.id;
    eventService.getEventById(eventId)
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
