<template>
  <div class="Events">
    <h1>My Events</h1>
    <myEvents />
    <events />

  <h1>My Songs</h1>
  <div id="song-container">
    <div>
      <h2 style="text-align: center;">Current Playlist</h2>
    <song-display
     v-for="song in this.songs"
        :key="song.song_id"
        :song="song"/>
    </div>
    <div>
      <h2 style="text-align: center;">Add Songs</h2>
      <spotify-search />
    </div>

  </div>
    
  </div>
</template>

<script>
import myEvents from "../components/DJEventView.vue";
import events from "../components/EventList.vue";
import songService from "../services/SongService";
import eventService from "../services/EventService";
import songDisplay from "../components/SongDisplay.vue";
import SpotifySearch from "../components/DJSearchSong.vue"

export default {
  name: "MyEvents",

  components: {
    events,
    myEvents,
    songDisplay,
    SpotifySearch
  },
  // mounted() {
  //   document.title = "Events";
  // },
  data() {
    return {
      songs: [],
    };
  },
  created() {
    this.getSongs();
    this.getEvents();
  },
methods: {
  getEvents() {
      let allEvents;
      let currentUsername = this.$store.state.user.username;

      eventService.getAllEvents().then((response) => {
        allEvents = response.data;
      });

      const filteredEvents = allEvents.filter((event) => {
        if (event.djUsername === currentUsername) {
          return event;
        }});
      this.$store.commit("SET_EVENTS", filteredEvents);
    },
    getSongs() {
      songService.getSongs(this.$store.state.user.username).then((response) => {
      if (response.status == 200) {
        this.songs = response.data;
      }
    });
    }
}
  
};
</script>

<style scoped>
h1 {
  text-align: center;
}
#song-container {
  display: flex;
  justify-content: space-evenly;
}
</style>