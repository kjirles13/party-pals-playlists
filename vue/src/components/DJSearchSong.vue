<template>
  <div id="search-page" v-if="isDj">
    <div id="search-submit">
      <div id="search">
        <form v-on:submit.prevent="searchForTrack">
          <label for="input"
            >Search for a song on Spotify by title or artist:</label
          >
          <br /><br />
          <input
            type="text"
            name="input"
            placeholder="Track"
            v-model="search.track"
          />
          <input type="text" placeholder="Artist" v-model="search.artist" />
          <br /><br />
          <button type="submit" v-on:click.prevent="searchForTrack">
            Search
          </button>
        </form>
      </div >
      <div id="submit" v-if="isShowing" >
        <p id="submit-song">Submit Song</p>
        <form v-on:submit.prevent="addSong()" id="add-song">
          <input
            type="text"
            placeholder="Rating"
            id="rating"
            v-model.number="rating"
          />
          <label for="select" id="genre-label">Choose a genre:</label>
          <select v-model="genreId" id="genre">
            <option value="">Choose a genre</option>
            <option
              v-for="genre in genres"
              :key="genre.id"
              v-bind:value="genre.id"
            >
              {{ genre.name }}
            </option>
          </select>
          <button type="submit" id="save">Save</button>
        </form>
      </div>
    </div>

  <div v-if="isShowing" >
  <div v-for="song in searchTracks.tracks.items" :key="song.id" >
      <div>
        <song-display v-bind:song="song" />
        <label for="add" class="checkbox-labels"
          >Add this song to your playlist</label
        >
        <input
          type="radio"
          id="add"
          v-bind:value="song.id"
          v-model="songId"
        />
      </div>
    </div>
</div>
    
  </div>
</template>

<script>
import spotifyService from "../services/SpotifyService.js";
import SongDisplay from "@/components/SongDisplay.vue";
import songService from "../services/SongService";

export default {
  name: "spotify",
  components: {
    SongDisplay,
  },
  data() {
    return {
      isShowing: false,
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
      genreId: "",
      rating: "",
      songId: "",
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
      this.flipIsShowing();
    },
    addSong() {
      const song = this.searchTracks.tracks.items.find((track) => {
        return track.id === this.songId;
      });
      if (!this.rating) {
        this.rating = 0;
      }
      const genre = this.genres.find((item) => {
        return item.id === this.genreId;
      });
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
      this.flipIsShowing();
    },
    flipIsShowing() {
      this.isShowing = !this.isShowing;
    }
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
    isDj() {
      return this.$store.state.user.authorities[0].name === "ROLE_DJ";
    },
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
#search {
  text-align: center;
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
  margin-top: 20px;
}
#genre {
  margin-top: 5px;
}
#genre-label {
  display: inline-block;
  margin-left: 10px;
  margin-right: 10px;
  margin-bottom: 10px;
  font-size: 13px;
}
#search-submit {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}
#submit {
  display: flex;
  flex-direction: column;
  font-size: 20px;
}
#submit-song{
  text-align: center;
}
#save {
  display: block;
  margin: 0 auto 20px auto;
}
</style>