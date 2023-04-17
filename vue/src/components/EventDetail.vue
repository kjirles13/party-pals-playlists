<template>
  <div class="event-detail">
    
    <button class="edit-cancel" @click="editEvent">{{ isEditing ? 'Cancel' : 'Edit Event' }}</button>
    <button v-if="isHost" @click="editEvent">Edit Event</button>

    <button v-if="isDJ || isHost" @click="editEvent">
  {{ isEditing ? 'Cancel' : 'Edit Event' }}
    </button>

    <div class="edit" v-if="isEditing">
    <label>Event Title</label>
    <input type="text" v-model="event.name"/>
    <label>Event Description</label>
    <input type="text" v-model="event.description"/>
    <label>Event Time</label>
    <input type="text" v-model="event.time"/>
    <label>Event Date</label>
    <input type="text" v-model="event.date"/>
    <label>Event Theme</label>
    <input type="text" v-model="event.theme"/>
    <button class="submit-edit" @click="updateEventDetails" type="submit">Submit</button>
    </div>
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
      <song-display v-for="song in event.playlist.songs" :key="song.song_id" :song="song">
        <div style="display: flex; flex-direction: column; justify-content: space-between">
          <div id="likes">
            <span>{{ song.likes }}</span>
            <img src="../images/thumbs-up.png" alt="Likes" width="15" height="15" class="thumb" style="margin-bottom: 10px; cursor: pointer" @click="incrementLikes(song.id)" :class="{ disabled: song.clicked || clickedSongs.includes(song.id) }"/>
          </div>
          <div id="dislikes">
            <span>{{ song.dislikes }}</span>
            <img src="../images/thumbs-down.png" alt="Dislikes" width="15" height="15" class="thumb" style="margin-bottom: 10px; cursor: pointer" @click="decrementLikes(song.id)" :class="{ disabled: song.clicked || clickedSongs.includes(song.id) }"/>
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
import axios from "axios";

export default {
  name: "event-detail",
  components: {
    SongDisplay,
  },
  data() {
<<<<<<< HEAD
   return {
    user: null,
    isVisible: false,
    isLoading: true,
    isEditing: false,
    event: {},
    error: "",
    clickedSongs: [], 
  }
=======
    return {
      user: null,
      isVisible: false,
      isLoading: true,
      isEditing: false,
      event: {},
      error: "",
    }
>>>>>>> main
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
        })
        .catch((error) => {
          console.log(error);
          this.isLoading = false;
          this.error = "Error loading event";
        });
    },
    incrementLikes(songId) {
      if (this.clickedSongs.includes(songId)) {
      return;
      }
      playlistService.addLikes(this.event.playlist.playlistId, songId);
      this.clickedSongs.push(songId); 
      this.getEvent();
    },
    decrementLikes(songId) {
      if (this.clickedSongs.includes(songId)) {
      return;
      }
      playlistService.deleteLikes(this.event.playlist.playlistId, songId);
      this.clickedSongs.push(songId); 
      this.getEvent();
    },
    editEvent() {
    this.isEditing = !this.isEditing;
  },
  updateEventDetails() {
    const eventId = parseInt(this.$route.params.id);
    eventService.updateEvent(this.event, eventId)
      .then(response => {
        console.log("Event updated successfully:", response);
        this.isEditing = false;
      })
      .catch(error => {
        console.log("Error updating event:", error);
      });
  }
  },
submitSong(songId, playlistId) {
    axios.post('/api/add-song-to-playlist', {
      songId: songId,
      playlistId:playlistId
    })
    .then(response => {
      this.$store.commit(response.data);
    })
}
};
</script>

<style scoped>
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
</style>
