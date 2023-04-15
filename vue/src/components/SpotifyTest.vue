<template>
  <div>
    <!-- <p>Name: {{ track.name }}</p>
    <p>Preview: {{ track.preview_url }}</p>
    <p>ID: {{ track.id }}</p>
    <p>URI: {{ track.uri }}</p>
    <p v-for="artist in track.artists" v-bind:key="artist.id">
      Artist: {{ artist.name }}
    </p>
    <button v-on:click="getTrack">Press for track</button>
    <br />
    <button v-on:click="setAuthToken">Press for token</button>
    <p>{{ $store.state.spotifyToken }}</p>

    <form v-on:submit.prevent="searchForTrack">
      <label for="input">Search for a song on Spotify by title or artist:</label
      ><br />
      <input
        type="text"
        name="input"
        placeholder="Search"
        v-model="search.query"
      />
      <br />
      <button type="submit" name="saveSearch">Search</button>
    </form>

    <div v-for="track in searchTracks.tracks.items" v-bind:key="track.id">
      <h2>Track:</h2>
      <h4>Name: {{ track.name }}</h4>
      <h4>ID: {{ track.id }}</h4>
      <h4>Preview: {{ track.preview_url }}</h4>
      <h4>Spotify: {{ track.external_urls.spotify }}</h4>
      <ul v-for="artist in track.artists" v-bind:key="artist.id">
        <li>{{ artist.name }}</li>
      </ul>
    </div> -->

    <button v-on:click="getArtist">Press for Artist</button>
    <p>{{artist.name}}</p>
    <p>{{$store.state.spotifyToken}}</p>
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
          items: [],
        },
      },
      search: {
        query: "",
        type: "track",
        limit: 10,
        offset: 0,
      },
      artist: {}
    };
  },
  methods: {
    getTrack() {
      spotifyService.getTrack("1moxjboGR7GNWYIMWsRjgG").then((response) => {
        this.track = response.data;
      });
    },
    setAuthToken() {
      spotifyService.getToken();
    },
    searchForTrack() {
      spotifyService.searchTrack(this.search).then((response) => {
        if (response.status === 200) {
          this.searchTracks = response.data;
        }
      });
    },
    getArtist() {
      spotifyService.getArtist('1moxjboGR7GNWYIMWsRjgG').then(response => {
        this.artist = response.body;
      });
    }
  },
  created() {
    spotifyService.getToken();
  }
};
</script>

<style>
</style>