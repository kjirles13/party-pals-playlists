<template>
<div>
    <button v-if="isDJ" @click="createForm"> Create Event </button>

    <div class="Create" v-if="isCreating">
    <label>Event Title</label>
    <input type="text" v-model="event.name"/>
    <label>Event Description</label>
    <input type="text" v-model="event.description"/>
    <label>Event Time</label>
    <input type="time" v-model="event.time"/>
    <label>Event Date</label>
    <input type="date" v-model="event.date"/>
    <label>Event Theme</label>
    <input type="text" v-model="event.theme"/>
    <label>Playlist Name</label>
    <input type="text" v-model="event.playlist.name"/>
   <label>Playlist Description</label>
    <input type="text" v-model="event.playlist.description"/>
    <!-- <label>Spotify Playlist ID</label>
    <input type="text" v-model="event.playlist.spotifyId"/> -->
    <button class="submit-created" @click="createEvent" type="submit">Submit</button>
    </div>
  <div>
    <input type="text" v-model="searchText" placeholder="Search Events"/>
    <br /><br />
    <div v-for="event in $store.state.events" :key="event.id">
     <h3>Event: {{ event.name }}</h3>
      </div> 
    <table>
      <tbody>
        <tr v-for="event in filterEvents" :key="event.id">
          <td>{{ event.name }}</td>
          <td>{{ event.date }}</td>
          <td>{{ event.id }}</td>
          <td>
            <router-link :to="{ name: 'event-detail', params: { id: event.id } }">View Details</router-link>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</div>
</template>

<script>
import eventService from "../services/EventService";

export default {
  name: "EventList",
  data() {
    return {
        user: null,
        isCreating: false,
      searchText: "",
      event: {
          name: "",
          description: "",
          time: "",
          date: "",
          theme: "",
          playlist: {
              name: "",
              description: "",
              spotifyId: "",
          },
          djUsername: this.$store.state.user.username,
      }
    };
  },
  computed: {
    filterEvents() {
      return this.$store.state.events.filter((events) => {
       return events.name.toLowerCase().includes(this.searchText.toLowerCase())
      });
    },
    isDJ() {
      return this.$store.state.user.authorities[0].name === "ROLE_DJ";
  },
  },
  methods: { 
   createEvent() {
       const date = new Date(this.event.date + ' ' +  this.event.time);
       const militaryTime = date.getHours() + ':' + date.getMinutes();
       this.event.time = militaryTime;
       eventService.createEvent(this.event)
       .then((response) => {
           if (response.status == 200) {
               this.$store.commit("ADD_EVENT", response.data);
               this.isCreating = false;
               this.getEvents();
           }
       })
       .catch((error) => {
           this.error = error.response.data.message;
       });
   },
  createForm() {
this.isCreating = !this.isCreating
  },
  getEvents() {
       eventService.getAllEvents().then((response) => {
      if (response.status == 200) {
        this.$store.commit("SET_EVENTS", response.data);
      }
    });
  },
  created() {
    this.getEvents();
  },
},
};
</script>

<style scoped>
.banner-clickable {
  position: relative;
}
.banner img {
  max-width: 100%;
}

.event-list {
  margin-top: 20px;
}

table {
  border-collapse: collapse;
  width: 100%;
}

th,
td {
  text-align: left;
  padding: 8px;
  border-bottom: 1px solid #ddd;
}

th {
  background-color: #4caf50;
  color: white;
}
</style>