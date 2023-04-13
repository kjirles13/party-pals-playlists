<template>
  <div class="Events">
    <h1>Events</h1>
    
    <p>Events Listed here</p>
    <test/>
    <events/>
  

</div>
</template>

<script>
import events from '../components/EventList.vue'

export default {
  name: "Events",
 components: {
    events,
  },
  
  data() {
    return {
      events:[]
    }
  },
  mounted() {
    this.getAllEvents();
  }
};

</script>

<style>

</style>
