<template>
  <div>
    <form v-on:submit.prevent="searchForTrack">
      <label for="input">Search for a song on Spotify by title or artist:</label
      ><br /><br />
      <input
        type="text"
        name="input"
        placeholder="Track"
        v-model="search.track"
      />
      <input type="text" placeholder="Artist" v-model="search.artist" />
      <br /><br />
      <button type="submit" v-on:click.prevent="searchForTrack">Search</button>
    </form>
    <song-display v-for="song in searchTracks.tracks.items" v-bind:key="song.id" v-bind:song="song"/>
  </div>
</template>

<script>
import spotifyService from "../services/SpotifyService.js";
import SongDisplay from "./SongDisplay.vue"

export default {
  name: "spotify",
  components: {
      SongDisplay
  },
  data() {
    return {
      track: {},
      searchTracks: {
        tracks: {
          items: [],
        },
      },
      search: {
        track: "",
        artist: "",
      }
    };
  },
  methods: {
    searchForTrack() {
      if (this.search.track && this.search.artist) {
        spotifyService.searchTracks(`track:${this.search.track} artist:${this.search.artist}`)
          .then((response) => {
            this.searchTracks = response.body;
          });
      } else if (this.search.artist) {
        spotifyService.searchTracks(`artist:${this.search.artist}`)
          .then((response) => {
            this.searchTracks = response.body;
          });
      } else if (this.search.track) {
        spotifyService.searchTracks(`track:${this.search.track}`)
          .then((response) => {
            this.searchTracks = response.body;
          });
      }
    },
  },
  created() {
    spotifyService.getToken().then((response) => {
      spotifyService.setAccessToken(response.data.access_token);
    });
  },
};
</script>

<style>
</style>