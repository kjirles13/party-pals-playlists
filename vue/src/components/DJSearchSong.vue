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
    <div v-for="song in searchTracks.tracks.items" v-bind:key="song.id">
      <div>
        <song-display v-bind:song="song" />
        <form v-on:submit.prevent="addSong(song.id)">
          <input type="checkbox" id="add" />
          <label for="add" class="checkbox-labels"
            >Add a song to your playlist
          </label>
          <input
            type="text"
            placeholder="Rating"
            id="rating"
            v-model.number="rating"
          />
          <label for="select" id="genre-label">Choose a genre:</label>
          <select v-model="genreId" id="genre">
            <option value="">Choose a genre</option>
            <option v-for="genre in genres" :key="genre.id" v-bind:value="genre.id">
              {{ genre.name }}
            </option>
          </select>
          <button type="submit" id="save">Save</button>
        </form>
      </div>
    </div>

    <div :disabled="!isDJ">
      <p>Add host to event</p>
    </div>
  </div>
</template>

<script>
import spotifyService from "../services/SpotifyService.js";
import SongDisplay from "./SongDisplay.vue";
import songService from "../services/SongService";

export default {
  name: "spotify",
  components: {
    SongDisplay,
  },
  data() {
    return {
      searchTracks: {
        tracks: {
          items: [],
        },
      },
      search: {
        track: "",
        artist: "",
      },
      genres: [],
      genreId: '',
      rating: "",
    };
  },
  methods: {
    searchForTrack() {
      if (this.search.track && this.search.artist) {
        spotifyService
          .searchTracks(
            `track:${this.search.track} artist:${this.search.artist}`
          )
          .then((response) => {
            this.searchTracks = response.body;
          });
      } else if (this.search.artist) {
        spotifyService
          .searchTracks(`artist:${this.search.artist}`)
          .then((response) => {
            this.searchTracks = response.body;
          });
      } else if (this.search.track) {
        spotifyService
          .searchTracks(`track:${this.search.track}`)
          .then((response) => {
            this.searchTracks = response.body;
          });
      }
    },
    addSong(songId) {
      const song = this.searchTracks.tracks.items.find((track) => {
        return track.id === songId;
      });
      if (!this.rating) {
        this.rating = 0;
      }
      const genre = this.genres.find((item) => {
        return item.id === this.genreId;
      })
      console.log(genre);
      const addedSong = {
        id: song.id,
        name: song.name,
        preview_url: song.preview_url,
        spotify: song.external_urls.spotify,
        rating: this.rating,
        likes: 0,
        dislikes: 0,
        genres: [genre],
        artists: song.artists,
      };
      songService.addSongToDjList(addedSong).then((response) => {
        if (response.status === 201 || response.status === 200) {
          window.alert("Song successfully added");
        } else {
          window.alert("There was an issue adding this song to your playlist");
        }
      });
    },
  },
  created() {
    spotifyService.getToken().then((response) => {
      spotifyService.setAccessToken(response.data.access_token);
    });
    songService.getGenres().then((response) => {
      if (response.status === 200) {
        this.genres = response.data;
      }
    });
  },
  computed: {
    isDJ() {
      return this.$store.state.user.role === "ROLE_DJ";
    },
    isHost() {
      return this.user && this.user.role === "ROLE_USER";
    }
  },
};
</script>

<style scoped>
.checkbox-labels {
  font-size: 13px;
}
.add-song {
  display: inline-block;
  margin-left: 20px;
}
#add {
  margin-right: 10px;
  margin-left: 5px;
}
#save {
  height: 20px;
  font-size: 13px;
  margin-bottom: 10px;
  margin-left: 10px;
}
#rating {
  width: 40px;
  margin-left: 10px;
}
#genre-label {
  display: inline-block;
  margin-left: 10px;
  margin-right: 10px;
  font-size: 13px;
}
</style>