<template>
  <div class = "Events">
    <h1>Events</h1>
<ul>
  <li v-for="event in events" :key="event.event_id">
    <h2>{{ event.theme }}</h2>
    <p>{{ event.description }}</p>
    <p> From {{ event.from_date }} to {{ event.to_date }}</p>
    <p> Hosted by {{ event.host }} </p>
    <p> Playlist: {{ event.playlist }} </p>
  </li>
</ul>
  </div>    
</template>

<script>
import axios from 'axios';

const http = axios.create({
  baseURL: 'http://localhost:9000'
});

export default {
  name: 'Events',
  data() {
    return {
      events: []
    };
  },
  async created() {
    try {
      const response = await http.get('/events');
      this.events = response.data;
    } catch (error) {
      console.error(error);
    }
  }
};
// export default {
//     name: 'Events',
//     methods:{
//         navigateToEvents(){
//             this.$router.push('/Events');
//         },
//         navigateToPlaylists(){
//             this.$router.push('/Playlists');
//         },
//         navigateToSongs(){
//             this.$router.push('/Songs');
//         },
//         navigateToHome(){
//             this.$router.push('/Home');
//         },
//     },


// }
</script>

<style>

</style>