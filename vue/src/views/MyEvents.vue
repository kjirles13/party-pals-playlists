<template>
  <div class="Events">
    <h1>My Events</h1>
    <myEvents />
    <events />

  <h1>My Songs</h1>
  <song-display
     v-for="song in this.songs"
        :key="song.song_id"
        :song="song"/>
        
    
  </div>
</template>

<script>
import myEvents from "../components/DJEventView.vue";
import events from "../components/EventList.vue";
import songService from "../services/SongService";
import eventService from "../services/EventService";
import songDisplay from "../components/SongDisplay.vue";

export default {
  name: "MyEvents",

  components: {
    events,
    myEvents,
    songDisplay,
  },
  mounted() {
    document.title = "Events";
  },
  data() {
    return {
      songs: [],
    };
  },
  created() {
    songService.getSongs(this.$store.state.user.username).then((response) => {
      if (response.status == 200) {
        this.songs = response.data;
      }
    });
    this.getEvents();
    
  },

methods: {
  getEvents() {
      let allEvents;
      let currentUsername = this.$store.state.user.username;

      eventService.getAllEvents().then((response) => {
        allEvents = response.data;
      });
      const filteredEvents = allEvents.filter((event) => event.djUsername === currentUsername);
      this.$store.commit("SET_EVENTS", filteredEvents);
    }
}
  
};
</script>

<style>
</style>