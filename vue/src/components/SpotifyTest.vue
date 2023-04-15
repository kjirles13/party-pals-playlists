<template>
  <div>
    <form v-on:submit.prevent="searchForTrack">
      <label for="input">Search for a song on Spotify by title or artist:</label
      ><br />
      <input
        type="text"
        name="input"
        placeholder="track"
        v-model="search.track"
      />
      <input type="text" placeholder="artist" v-model="search.artist">
      <br />
      <button type="submit" v-on:click.prevent="searchForTrack">Search</button>
    </form>
    
    <h3>Tracks:</h3>
    <div v-for="track in searchTracks.tracks.items" v-bind:key="track.id">
      <h4>Name: {{ track.name }}</h4>
      <h4>ID: {{ track.id }}</h4>
      <h4>Preview: {{ track.preview_url }}</h4>
      <h4>Spotify: {{ track.external_urls.spotify }}</h4>
      <ul v-for="artist in track.artists" v-bind:key="artist.id">
        <li>{{ artist.name }}</li>
      </ul>
      <br />
    </div>
  </div>
</template>

<script>
import spotifyService from "../services/SpotifyService.js";

export default {
  name: "spotify",
  data() {
    return {
      track: {},
      searchTracks: {
        tracks: {
          items: []
        },
      },
      search: {
        track: '',
        artist: ''
      },
      artist: {},
    };
  },
  methods: {
    searchForTrack() {
      // const track = this.search.track.toString;
      // const artist = this.search.artist.toString;
      spotifyService.searchTracks(`track:${this.search.track} artist:${this.search.artist}`).then((response) => {
          this.searchTracks = response.body;
      });
    },
    //`track:${this.search.track} artist:${this.search.artist}`

    // searchForTrack() {
    //   spotifyService.searchTrack(this.search).then((response) => {
    //     if (response.status === 200) {
    //       this.searchTracks = response.data;
    //     }
    //   });
    // },
    getArtist() {
      spotifyService.getArtist("1moxjboGR7GNWYIMWsRjgG").then((response) => {
        this.artist = response.body;
      });
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