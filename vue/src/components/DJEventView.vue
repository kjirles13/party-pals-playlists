<template>
  <div>
    <button v-if="isDJ" @click="createForm">Create Event</button>

    <div class="create" v-if="isCreating">
      <form v-on:submit.prevent="createEvent">
        <label>Event Title</label>
        <input type="text" v-model="event.name" />
        <label>Event Description</label>
        <input type="text" v-model="event.description" />
        <label>Event Time</label>
        <input type="time" v-model="event.time" />
        <label>Event Date</label>
        <input type="date" v-model="event.date" />
        <label>Event Theme</label>
        <input type="text" v-model="event.theme" />
        <label>Playlist Name</label>
        <input type="text" v-model="event.playlist.name" />
        <label>Playlist Description</label>
        <input type="text" v-model="event.playlist.description" />
        <button class="submit-created" type="submit">Submit</button>
      </form>
    </div>
      <h1>My Songs</h1>

    <div id="song-container">
      <div id="current-songs">
        <h2>Current Playlist</h2>
        <song-display
          v-for="song in this.songs"
          :key="song.song_id"
          :song="song"
        />
      </div>
      <div id="search-page">
        <h2>Add Songs</h2>
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
          </div>
          <div id="submit" v-if="isShowing">
            <p id="submit-song">Submit Song</p>
          </div>
        </div>

        <div v-if="isShowing">
          <div v-for="song in searchTracks.tracks.items" :key="song.id">
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
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import eventService from "../services/EventService";
import spotifyService from "../services/SpotifyService.js";
import songService from "../services/SongService";
import SongDisplay from "@/components/SongDisplay.vue";

export default {
  name: "event-detail",
  components: {
    SongDisplay,
  },
  data() {
    return {
      isShowing: false,
      songs: [],
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
      isCreating: false,
      user: null,
      isVisible: false,
      isLoading: true,
      isEditing: false,
      error: "",
      event: {
        name: "",
        description: "",
        time: "",
        date: "",
        theme: "",
        djUsername: this.$store.state.user.username,
        playlist: {
          name: "",
          description: "",
          spotifyId: " ",
        },
      },
    };
  },
  computed: {
    isDJ() {
      return this.$store.state.user.username === this.event.djUsername;
    },
  },
  methods: {
    getSongs() {
      songService.getSongs(this.$store.state.user.username).then((response) => {
        this.songs = response.data;
    });
    },
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
      this.songs.unshift(addedSong);
    },
    flipIsShowing() {
      this.isShowing = !this.isShowing;
    },
    createForm() {
      this.isCreating = !this.isCreating;
    },
    createEvent() {
      const date = new Date(this.event.date + " " + this.event.time);
      const militaryTime = date.getHours() + ":" + date.getMinutes() + ":00";
      this.event.time = militaryTime;
      eventService
        .createEvent(this.event)
        .then((response) => {
          if (response.status === 201) {
            this.isCreating = false;
            this.getEvents();
            this.event = {
              name: "",
              description: "",
              time: "",
              date: "",
              theme: "",
              djUsername: this.$store.state.user.username,
              playlist: {
                name: "",
                description: "",
                spotifyId: " ",
              },
            };
          }
        })
        .catch((error) => {
          this.error = error.response.data.message;
        });
    },
    getSpotifyToken() {
      spotifyService.getToken().then((response) => {
        spotifyService.setAccessToken(response.data.access_token);
      });
      songService.getGenres().then((response) => {
        if (response.status === 200) {
          this.genres = response.data;
        }
      });
    },
  },
  created() {
    this.getSpotifyToken();
    this.getSongs();
  },
};
</script>

<style scoped>
#theme-date-time {
  display: flex;
  justify-content: center;
  justify-content: space-around;
}
.host-name {
  display: inline-block;
  margin-right: 10px;
}
.event-detail {
  text-align: center;
  margin: 150px;
}
.disabled {
  opacity: 0.2;
  cursor: not-allowed;
}
.disabled img {
  filter: grayscale(1);
}
#likes {
  display: flex;
  font-size: 13px;
  justify-content: space-evenly;
}
#dislikes {
  display: flex;
  font-size: 13px;
  justify-content: space-between;
}
.thumb {
  display: inline-block;
  margin-right: 5px;
  margin-left: 5px;
}
.edit {
  display: grid;
}
.edit-cancel {
  font-family: arial;
  font-weight: bold;
  border-radius: 6px;
  cursor: pointer;
  padding: 3px 6px 3px 6px;
  font-size: 16px;
}
.submit-edit {
  font-family: arial;
  font-weight: bold;
  border-radius: 6px;
  cursor: pointer;
  padding: 3px 6px 3px 6px;
  margin-top: 10px;
  font-size: 16px;
}
label {
  font-family: arial;
  margin-top: 10px;
}
input {
  padding-top: 5px;
  padding-bottom: 5px;
  font-size: 16px;
}
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
#submit-song {
  text-align: center;
}
#save {
  display: inline-block;
  margin: 0 auto 20px auto;
}
#song-container {
  display: flex;
  justify-content: space-evenly;
}
h1 {
  text-align: center;
}
h2 {
  text-align: center;
}
</style>
