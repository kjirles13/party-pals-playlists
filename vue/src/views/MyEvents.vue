<template>
  <div class="Events">
    <h1>My Events</h1>
    <events />
    <myEvents />  
  </div>
</template>

<script>
import myEvents from "../components/DJEventView.vue";
import events from "../components/EventList.vue";
// import songService from "../services/SongService";
import eventService from "../services/EventService";

export default {
  name: "MyEvents",

  components: {
    events,
    myEvents,
  },
  data() {
    return {
      songs: [],
    };
  },
  created() {
    // this.getSongs();
    this.getEvents();

    eventService.getDJEvents(this.$store.state.user.username).then((response) => {
      if (response.status == 200) {
        this.djEvents = response.data;
      }
    });
    
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
    // getSongs() {
    //   songService.getSongs(this.$store.state.user.username).then((response) => {
    //   if (response.status == 200) {
    //     this.songs = response.data;
    //   }
    // });
    // }
}
  
};
</script>

<style scoped>
h1 {
  text-align: center;
}
</style>