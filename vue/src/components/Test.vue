<template>
  <div>
    <button v-on:click="runTest">THE TEST BUTTON</button>
    <button v-on:click="searchSongs">DISPLAY SONGS</button>
    <p v-for="line in test" v-bind:key="line.index">{{ line }}</p>
    <div v-for="song in songs" v-bind:key="song.id">
      <h2>{{ song.name }}</h2>
      <p>ID: {{ song.id }}</p>
      <h3>Artists:</h3>
      <div v-for="artist in song.artists" :key="artist.id">
        <p>ID: {{ artist.id }}</p>
        <p>Name: {{ artist.name }}</p>
      </div>
      <h3>Genres:</h3>
      <div v-for="genre in song.genres" :key="genre.id">
        <p>ID: {{ genre.id }}</p>
        <p>Name: {{ genre.name }}</p>
      </div>
      <h4>Rating: {{ song.rating }}</h4>
      <h4>Votes: {{ song.votes }}</h4>
      <h4>Preview: {{song.preview}}</h4>
      <h4>Spotify: {{song.spotifyUri}}</h4>
      <br />
    </div>
  </div>
</template>

<script>
import testService from "../services/TestService";
import songService from "../services/SongService";

export default {
  name: "test",
  data() {
    return {
      test: [],
      songs: [{}],
    };
  },
  methods: {
    runTest() {
      testService.test().then((response) => {
        this.test = response.data;
      });
    },
    searchSongs() {
      songService
        .getSongs()
        .then((response) => {
          this.songs = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style>
</style>