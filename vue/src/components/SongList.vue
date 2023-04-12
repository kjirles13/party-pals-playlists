<template>
    <div class="song-list">
        <h1>Song List</h1>
        <button v-on:click="searchSongs">DISPLAY SONGS</button>
    <p v-for="line in test" v-bind:key="line.index">{{ line }}</p>

    <div v-for="song in songs" v-bind:key="song.id">
      <h3>Song: {{ song.name }}</h3>
      <!-- <p>ID: {{ song.id }}</p> -->
      
      <div v-for="artist in song.artists" :key="artist.id">
        <!-- <p>ID: {{ artist.id }}</p> -->
        <h3>Artists: {{ artist.name }}</h3>
        <!-- <p>Name: </p> -->
      </div>
      
      <div v-for="genre in song.genres" :key="genre.id">
          <h3>Genres: {{ genre.name }}</h3>
        <!-- <p>ID: {{ genre.id }}</p>
        <p>Name: </p> -->
      </div>
      <h4>Rating: {{ song.rating }}</h4>
      <!-- <h4>Votes: {{ song.votes }}</h4> -->
      <audio controls>
          <source :src="song.preview" type="audio/mpeg">
      <h4> <a href="song.preview">Preview{{song.preview}}</a></h4>
      </audio>
      <h4>Spotify: {{song.spotifyUri}}</h4>
      <br />
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
        }
    }
};
</script>


<style scoped>
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