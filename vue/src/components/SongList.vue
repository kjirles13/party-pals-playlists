<template>
  <div class="SongList">
    <h1>Song List</h1>
    <button v-on:click="searchSongs">DISPLAY SONGS</button>
    <div v-for="song in songs" v-bind:key="song.id" class="song">
      <h3>Song: {{ song.name }}</h3>
      <div v-for="artist in song.artists" :key="artist.id">
        <h3>Artists: {{ artist.name }}</h3>
      </div>
      <div v-for="genre in song.genres" :key="genre.id">
        <h3>Genres: {{ genre.name }}</h3>
      </div>
      <h4>Rating: {{ song.rating }}</h4>
      <audio controls @play="setVolume">
        <source :src="song.preview" type="audio/mpeg">
        <h4><a href="song.preview">Preview{{song.preview}}</a></h4>
      </audio>
      <a :href="song.spotifyUri"><img src="../images\image-gallery-spotify-logo-21.png" alt="Spotify" width="56" height="56"></a>
    </div>
  </div>
</template>

<script> 
import axios from 'axios';
import songService from "../services/SongService"


export default {
    name: 'SongList',
    props: {
        song:{
            type: Object,
            required: true
        }
    },
    data() {
        return {
            songs: [{}],
            getSongs: [],
        };
    },
    mounted() {
        axios.get('http://localhost:9000/songs')
        .then(response => {
            this.songs = response.data;
        })
        .catch(error => {
            console.log(error);
        });
    },
    methods: {
        searchSongs() {
            songService.getSongs().then(response => {
                this.getSongs = response.data;
            }).catch(error => {
                console.log(error);
            });
        },
        setVolume(event) {
      event.target.volume = 0.2; 
    }
    }
};
</script>


<style scoped>

.img {
    size: 20px;
}
.song {
    background-color: rgb(166, 167, 166);
    border: 1px solid black;
    margin-bottom: 10px;
    box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.3);
    padding: 8px;
}
.song-list {
    margin-top: 20px;
}
.song-list h2 {
margin-bottom: 10px;
}
.song-list ul {
    list-style: none;
    padding: 0;
}
.song-list li {
    margin-bottom: 5px;
}
</style>