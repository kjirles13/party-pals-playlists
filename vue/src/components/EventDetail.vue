<template>

    <div class="event-detail">

        <div v-if="isLoading">Loading...</div>
        <div>
        <h1>{{ this.event.name }}</h1>
        <p>Description: {{ event.description }}</p>
        <p>Theme: {{ event.theme }}</p>
        <p>Date: {{ event.date }}</p>
        <p>Time: {{ event.time }}</p>
        
        
        <div v-for="p in event.hosts" :key="p.hostId"> 
            <p>{{ p.name }}</p>
            </div>
        
        <p>{{ event.djUsername }}</p>

        <p>Playlist: {{ event.playlist.name }}</p>
        
        <button @click="isVisible = !isVisible">{{ isVisible ? 'Hide Songs' : 'Show Songs' }}</button>

        <!-- <div v-show="isVisible">

            <div>
                <ul v-for="song in event.playlist.songs" :key="song.song_id">
            <li>{{ song.name }}</li>
            <div v-for="a in song.artists" :key="a.artists">
            <li>{{a.name}}</li>
            </div>
        </ul>
            <p>hello</p>
            </div>

        </div> -->


    <!-- <div class="Page">
        <div class="song-list" v-show="isVisible">
  <div>
    <ul class="song" v-for="song in event.playlist.songs" :key="song.song_id">
      <li>{{ song.name }} - By 
        <span v-for="(artist, index) in song.artists" :key="index">
          {{ artist.name }}{{ index === song.artists.length - 1 ? '' : ', ' }}
        </span>
      </li>
    </ul>
    <p>hello</p>
  </div>
</div>
</div> -->


<div class="Page">
    <div>
        <div class="song-list" v-show="isVisible">
  <div>
    <div class="song" v-for="song in event.playlist.songs" :key="song.song_id">
      <p>{{ song.name }} </p>
      <div>
        <p v-for="(artist, index) in song.artists" :key="index"> 
          {{ artist.name }}{{ index === song.artists.length - 1 ? '' : ', ' }}
        </p>
        </div>
        <div>
        <p v-for="(genre, index) in song.genres" :key="index"> 
          {{ genre.name }}{{ index === song.genres.length - 1 ? '' : ', ' }}
        </p>
        </div>
    </div>
    </div>
    <p>hello</p>
  </div>
</div>
</div>

        

        </div>



        
    </div>
</template>

<script>
// import { mapState } from "vuex";
// import axios from 'axios';
// import songDetail from '../components/SongDetail.vue'
import eventService from '../services/EventService';
export default {
    name: "event-detail",
    // props: {Object},
    data() {
        return {
            isVisible: false,
            isLoading: true,
            event: {
                name: '',
                description: '',
                date: '',
                time: '',
                playlist: [],
                hosts: [],
                djUsername: ''
                

            },
            // song: {
            //     name: '',
            //     description: ''
            // }
        };
    },

  created() {
    const eventId = parseInt(this.$route.params.id);
    console.log(eventId)
    eventService.getEventById(eventId)
    .then((response) => {
       console.log(response)
        this.event = response;
        this.isLoading = false;
    })
    .catch((error) => {
        console.log(error);
        this.isLoading = false;
        this.error = 'Error loading event'
    })

    
  },

//   components: {
//       songDetail
//   },

};
</script>

<style scoped>
.event-detail {
    margin: 100px;
}

.song{
    display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  margin-top: 20px;
  background: linear-gradient(to bottom, #a8896cb7, #a8896c);
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.3);
  border-radius: 19px;
  margin-top: 8px;
  margin-bottom: 8px;
}
</style>
