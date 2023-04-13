<template>
  <div class="Events">
    <h1>Events</h1>
    
    <p>Events Listed here</p>
    <test/>
    <events/>
  

</div>
</template>

<script>
import axios from 'axios';
import events from '../components/EventList.vue'
const http = axios.create({
    baseURL: 'http://localhost:9000'
});
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
  
  methods: {
    async getAllEvents() {
      try{
        const response = await http.get('/songs');
        this.songs = response.data;
      } catch (error) {
        console.log(error);
      }
    }
  },
  mounted() {
    this.getAllEvents();
  }
};

</script>

<style>

</style>
