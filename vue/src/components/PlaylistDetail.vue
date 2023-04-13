<template>
  <div class="PlaylistDetail">
    <h1>Name: {{ event.playlist.name }}</h1>
    <h2>DJ: {{ event.djUsername }}</h2>
    <h3>Date: {{ event.date }}</h3>
    <h3>Time: {{ event.time }}</h3>
    <p>Description: {{ event.playlist.description }}</p>
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
        </div>
      </div>
    </ul>
  </div>
</template>

<script>
export default {
  name: "PlaylistDetail",
  data() {
    return {
      event: {},
    };
  },
  created() {
    this.event = this.$store.state.events.find((event) => {
      return event.id === 1;
    });
  },
};
</script>
