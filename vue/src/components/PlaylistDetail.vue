<template>
  <div class="PlaylistDetail">
    <button v-if="isDJ" @click="addSong">Add Song</button>
    <button v-if="isDJ" @click="removeSong">Remove Song</button>
    <div class="Playlists">
      <div class="Playlist">
        <h2>DJ Playlist</h2>
        <ul>
          <div v-for="song in DJ.playlist" v-bind:key="song.id">
            <h3>Name: {{ song.name }}</h3>
            <button v-if="isHost" @click="addSong"> Add to event playlist</button>
            <li>ID: {{ song.id }}</li>
            <li>Preview: {{ song.preview }}</li>
            <li>DJ rating: {{ song.rating }}</li>
            <li>Spotify: {{ song.spotifyUri }}</li>
            <li>Artists:</li>
            <div v-for="artist in song.artists" v-bind:key="artist.id">
              <ul>
                <li>{{ artist.name }}</li>
              </ul>
            </div>
            <li>Genres:</li>
            <div v-for="genre in song.genres" v-bind:key="genre.id">
              <ul>
                <li>{{ genre.name }}</li>
              </ul>
            </div>
          </div>
        </ul>
      </div>
<div class="Playlist">
  <h2>Event Playlist</h2>
  <ul> 
    <div v-for="song in eventPlaylist" v-bind:key="song.id">
    <li>Name: {{ event.playlist.name }}</li>
    <li>DJ: {{ event.djUsername }}</li>
    <ul>
      <div v-for="song in event.playlist.songs" v-bind:key="song.id">
        <h3>Name: {{ song.name }}</h3>
        <li>ID: {{ song.id }}</li>
        <li>Preview: {{ song.preview }}</li>
        <li>Spotify: {{ song.spotifyUri }}</li>
        <li>DJ Rating: {{ song.rating }}</li>
        <li>Artists:</li>
        <div v-for="artist in song.artists" v-bind:key="artist.id">
          <ul>
            <li>{{ artist.name }}</li>
          </ul>
        </div>
        <li>Genres:</li>
        <div v-for="genre in event.playlist.genres" v-bind:key="genre.id">
          <ul>
            <li>
              {{ genre.name }}
            </li>
          </ul>
          </ul>
        </div>
      </div>
  </div>
</template>

<script>

export default {
  name: "PlaylistDetail",
  data() {
    return {
      event: {},
      djPlaylist: {},
    };
  },
  created() {
    this.event = this.$store.state.events.find((event) => {
      return event.id === 1;
    });
  },
  computed: {
    isDJ() {
      return this.user && this.user.role === "DJ";
    },
    isHost() {
      return this.user && this.user.role === "host";
    },
    djPlaylist() {
      const djId = this.event.djId;
      return this.$store.state.playlists.filter((playlist) => {
        return playlist.djId === djId;
      });
    },
},
methods: {

}
};
</script>
