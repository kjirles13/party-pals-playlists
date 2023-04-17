<template>
  <div class="event-detail">
    <button v-if="isDJ" @click="createEvent">Create Event</button>
    <button v-if="isDJ" @click="editEvent">Edit Event</button>
<button v-if="isHost" @click="editEvent">Edit Event</button>
<label>Event Title</label>
    <input v-model="event.title" :disabled="!isDJ && !isHost" />
    <label>Event Description</label>
    <input v-model="event.description" :disabled="!isDJ && !isHost" />
    <label>Event Time</label>
    <input v-model="event.time" :disabled="!isDJ && !isHost" />
    <label>Event Date</label>
    <input v-model="event.date" :disabled="!isDJ && !isHost" />
    <label>Event Theme</label>
    <input v-model="event.theme" :disabled="!isDJ && !isHost" />
    <!-- <div v-if="isLoading">Loading...</div> -->
    <h1>{{ event.name }}</h1>
    <p>{{ event.description }}</p>
    <p>Theme: {{ event.theme }}</p>
    <p>Date: {{ event.date }}</p>
    <p>Time: {{ event.time }}</p>
    <p>Your hosts are:</p>
    <div v-for="p in event.hosts" :key="p.hostId" id="hosts">
      <p>{{ p.name }}</p>
    </div>
    <p>{{ event.djUsername }}</p>
    <p>Playlist: {{ event.playlist.name }}</p>
    <div class="song-info">
      <song-display
        v-for="song in event.playlist.songs"
        :key="song.song_id"
        :song="song"
      >
        <div style="display: flex; flex-direction: column; justify-content: space-between">
          <div id="likes">
            <span>{{ song.likes }}</span>
            <img
              src="../images/thumbs-up.png"
              alt="Likes"
              width="15"
              height="15"
              class="thumb"
              style="margin-bottom: 10px; cursor: pointer"
              @click="incrementLikes(song.id)"
              :class="{ disabled: song.clicked }"
            />
          </div>
          <div id="dislikes">
            <span>{{ song.dislikes }}</span>
            <img
            src="../images/thumbs-down.png"
            alt="Dislikes"
            width="15"
            height="15"
            class="thumb"
            style="margin-bottom: 10px; cursor: pointer"
            @click="decrementLikes(song.id)"
            :class="{ disabled: song.clicked }"
          />
          </div>
        </div>
      </song-display>
    </div>
  </div>


  
</template>

<script>
import eventService from "../services/EventService";
import playlistService from "../services/PlaylistService";
import SongDisplay from "@/components/SongDisplay.vue";

export default {
  name: "event-detail",
  components: {
    SongDisplay,
  },
  data() {
    return {
      isVisible: false,
      isLoading: true,
      event: {},
      error: "",
    };
  },
  created() {
    this.getEvent();
  },
  computed: {
    isDJ() {
      return this.user && this.user.role === "DJ";
    },
    isHost() {
      return this.user && this.user.role === "host";
    }
  },
  methods: {
    getEvent() {
      const eventId = parseInt(this.$route.params.id);
      eventService
        .getEventById(eventId)
        .then((response) => {
          this.event = response.data;
          // this.isLoading = false;
        })
        .catch((error) => {
          console.log(error);
          this.isLoading = false;
          this.error = "Error loading event";
        });
    },
    incrementLikes(songId) {
      playlistService.addLikes(this.event.playlist.playlistId, songId);
      this.getEvent();
    },
    decrementLikes(songId) {
      playlistService.deleteLikes(this.event.playlist.playlistId, songId);
      this.getEvent();
    },
  },

//   components: {
//       songDetail
//   },

};
</script>

<style scoped>
.event-detail {
  text-align: center;
  margin: 100px;
}
.disabled {
  opacity: 0.2;
  pointer-events: none;
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
</style>
