<template>
  <div class="event-detail">
  <div v-if="isLoading">Loading...</div>
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



    <div class="song-info">
      <div class="song" v-for="song in event.playlist.songs" :key="song.song_id">
        <div class="p5"><strong> {{ song.name }} </strong></div>
        <span class="stars"><img v-for="n in song.rating" :key="n" class="gold-star" src="../images/StarRating.png" alt="Gold star"/>
        </span>
        <div class="artist-wrapper">
            <p v-for="(artist, index) in song.artists" :key="index">{{ artist.name }}</p>
        </div>
        <div class="song-links">
          <div class="likesDislikes">
            <img src="../images\thumbs-up.png" alt="Spotify" width="15" height="15" style="margin-bottom: 10px;"/>
            <img src="../images\thumbs-down.png" alt="Spotify" width="15" height="15"/>
          </div>
          <audio controls @play="setVolume">
            <source :src="song.preview_url" type="audio/mpeg" /><p>
              <a href="song.preview_url">Preview{{ song.preview_url }}</a></p>
          </audio>
          <a :href="song.spotify" target="_blank">
            <img src="../images\image-gallery-spotify-logo-21.png" alt="Spotify" width="56" height="56"/>
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import eventService from '../services/EventService';
export default {
    name: "event-detail",
    
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
            }
                
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
};
</script>

<style scoped>
.event-detail {
    margin: 100px;
}
.likesDislikes{
  display: flex;
  flex-direction: column;
}
.SongList {
  margin-top: 20px;
}
.song {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  background: linear-gradient(to bottom, #c4cbda, #dee2ec);
  /* border: 1px solid black; */
  border-radius: 19px;
  margin-top: 8px;
  margin-bottom: 8px;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.3);
}
.Stars {
  display: flex;
  flex-grow: 2;
  align-items: right;
  margin-left: 10;
}
.song-info {
 
  
  align-items: center;
}
.p5 {
  display: inline-block;
  margin-top: 15px;
  margin-left: 15px;
  margin-right: 10px;
}
.artist-wrapper {
  display: flex;
  flex-wrap: wrap;
  margin-left: 15px;
}
.song-links {
  display: flex;
  align-items: center;
}
.gold-star {
  width: 15px;
  height: 15px;
  margin-right: 1px;
}

audio::-webkit-media-controls-panel {
  background: linear-gradient(to bottom, #a6b3be, #c5cad3);
}
audio::-webkit-media-controls-play-button {
  background-color: #9099a1;
  border-radius: 50%;
}
audio::-webkit-media-controls-play-button:hover {
  background-color: #727479;
}
audio::-webkit-media-controls-current-time-display {
  color: black;
}
audio::-webkit-media-controls-time-remaining-display {
  color: black(255, 255, 255);
}
audio::-webkit-media-controls-mute-button {
  background-color: #9099a1;
  border-radius: 50%;
}
audio::-webkit-media-controls-volume-slider {
  background-color: #9099a1;
  border-radius: 25px;
  padding-left: 8px;
  padding-right: 8px;
}
</style>
