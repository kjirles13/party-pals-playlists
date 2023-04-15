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

        <!-- <ul>
      <div v-for="playlist in event.playlists" v-bind:key="playlist.id">
        <h3>Playlist: {{ playlist.name }}</h3>
        <li>ID: {{ playlist.id }}</li>
        <li>Description: {{ playlist.description }}</li>
        <li>Created by: {{ playlist.createdBy.username }}</li>
        <li>Songs:</li>
        <div v-for="song in playlist.songs" v-bind:key="song.id">
          <ul>
            <li>{{ song.name }} by {{ song.artists.join(', ') }}</li>
          </ul>
        </div>
        <li>Genres:</li>
        <div v-for="genre in playlist.genres" v-bind:key="genre.id">
          <ul>
            <li>
              {{ genre.name }}
            </li>
          </ul>
        </div>
      </div>
    </ul> -->
 

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

    name: "EventDetail",
    props: {
        Object
    },
    data() {
        return {
            event: null,
        };
    },

    computed: {
    events() {
      return this.$store.state.events;
    },
  },

  created() {
    const eventId = this.$route.params.id;
    this.$store.dispatch("fetchEvent", eventId);
  },

//     created() {
//     const eventId = this.$route.params.id;
//     eventService.getEventById(eventId)
//       .then((response) => {
//         this.event = response.data;
//       })
//       .catch((error) => {
//         console.log(error);
//       });
//   },

//     created() {
//   console.log("EventDetails created");
//   const eventId = this.$route.params.id;
//   eventService.getEventById(eventId)
//   .then(response => {
//     if (response.status == 200) {
//       console.log("EventDetails response data", response.data);
//       this.event = response.data;
//     }
//   })
//   .catch(error => {
//     console.log(error);
//   });
// },

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

    //     viewDetails(eventId) {
    //   this.$emit("view-details", eventId);
    // },
  

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
