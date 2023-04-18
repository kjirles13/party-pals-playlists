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
    eventService.getDJEvents(this.$store.state.user.username).then((response) => {
      if (response.status == 200) {
        this.djEvents = response.data;
      }
    });
  },
methods: {
}
  
};
</script>

<style scoped>
h1 {
  text-align: center;
}
</style>