<template>
  <div class="Events">
    <h1>Events</h1>
    <events />
  

</div>
</template>

<script>
import events from '../components/EventList.vue'
// import eventService from '../services/EventService'

export default {
  name: "Events",
 components: {
    events,
  },
  // created() {
  //   eventService.getAllEvents().then(response => {
  //     this.$store.commit("SET_EVENTS", response.data);
  //   })
  // }
};

</script>

<style>

</style>
