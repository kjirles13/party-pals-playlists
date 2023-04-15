<template>
    <div class="SongDetail">
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
      <search-song :selectedsong="selectedSong"/>
    </div>
</template>

<script>
import SearchSong from '../components/SearchSong.vue';
export default {
  components: { SearchSong },
    props: {
        song: {
            type: Object,
            required: true
        }
    }
}
</script>

<style>
</style>
