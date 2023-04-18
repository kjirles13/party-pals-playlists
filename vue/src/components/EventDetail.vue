<template>
  <div class="event-detail">
    <button v-if="isDj || isHost" @click="editEvent" class="edit-cancel">
      {{ isEditing ? "Cancel" : "Edit Event" }}
    </button>

    <div class="edit" v-if="isEditing">
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
      <button class="submit-edit" @click="updateEventDetails" type="submit">
        Submit
      </button>
    </div>

    <h1>{{ event.name }}</h1>
    <p>{{ event.description }}</p>
    <h3 class="dj-name">DJ {{ event.djUsername }}</h3>
    <div id="theme-date-time">
      <h4>Theme: {{ event.theme }}</h4>
      <p></p>
      <h4>Date: {{ event.date }}</h4>
      <p></p>
      <h4>Time: {{ event.time }}</h4>
    </div>
    <div>
      <p v-if="event.hosts.length === 1">Your host is:</p>
      <p v-else-if="event.hosts.length > 1">Your hosts are:</p>
      <div>
        <div v-for="host in event.hosts" :key="host.hostId">
          <p class="host-name">{{ host.name }}</p>
          <span
            style="color: #8b0000; cursor: pointer"
            v-on:click="deleteHost(host.name)"
            v-if="isDj"
            >x</span
          >
        </div>
      </div>
    </div>
    <div v-if="isDj">
      <label for="host-select">Add Host:</label>
      <select id="host-select" v-model="selectedHost">
        <option
          v-for="user in availableHosts"
          :value="user.username"
          v-bind:key="user.id"
        >
          {{ user.username }}
        </option>
      </select>
      <button @click="addHost()">Add</button>
    </div>
    <h2>{{ event.playlist.name }}</h2>
     <div 
     v-for="(genre, index) in event.playlist.genres" :key="genre.id" v-html="genre.name + (index < event.playlist.genres.length - 1 ? ', ' : '')">
     </div>
    <div class="song-info">
      <song-display
        v-for="song in event.playlist.songs"
        :key="song.song_id"
        :song="song"
      >
        <div
          style="
            display: flex;
            flex-direction: column;
            justify-content: space-between;
          "
        >
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
              :class="{
                disabled: song.clicked || clickedSongs.includes(song.id),
              }"
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
              :class="{
                disabled: song.clicked || clickedSongs.includes(song.id),
              }"
            />
          </div>
          <button
            v-if="isHost"
            @click="vetoSong(song.id)"
            :disabled="song.clicked || clickedSongs.includes(song.id)"
          >
            Veto
          </button>
        </div>
      </song-display>
    </div>
  </div>
</template>

<script>
import eventService from "../services/EventService";
import playlistService from "../services/PlaylistService";
import SongDisplay from "@/components/SongDisplay.vue";
import authService from "../services/AuthService";

export default {
  name: "event-detail",
  components: {
    SongDisplay,
  },
  data() {
    return {
      isVisible: false,
      isLoading: true,
      isEditing: false,
      event: {},
      error: "",
      clickedSongs: [],
      users: [],
      selectedHost: "",
    };
  },
  created() {
    this.getEvent();
    this.getAllUsers();
  },
  computed: {
    isDj() {
      return this.$store.state.user.username === this.event.djUsername;
    },
    isHost() {
      let isHost = false;
      this.event.hosts.forEach((host) => {
        if (host.name === this.$store.state.user.username) {
          isHost = true;
        }
      });
      return isHost;
    },
    availableHosts() {
      return this.users.filter((user) => {
        if (
          this.event.hosts.filter((a) => a.name === user.username).length ===
            0 &&
          user.authorities[0].name !== "ROLE_DJ"
        ) {
          return user;
        }
      });
    },
  },
  methods: {
    vetoSong(songId) {
      this.event.playlist.songs = this.event.playlist.songs.filter((song) => {
        return song.id !== songId;
      });
    },
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
    getAllUsers() {
      authService.getAllUsers().then((response) => {
        if (response.status === 200) {
          this.users = response.data;
        }
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
      const date = new Date(this.event.date + " " + this.event.time);
      const militaryTime = date.getHours() + ":" + date.getMinutes() + ":00";
      this.event.time = militaryTime;
      const eventId = parseInt(this.$route.params.id);
      eventService
        .updateEvent(this.event, eventId)
        .then((response) => {
          console.log("Event updated successfully:", response);
          this.isEditing = false;
        })
        .catch((error) => {
          console.log("Error updating event:", error);
        });
    },
    deleteHost(hostName) {
      eventService.removeHostFromEvent(this.event.id, hostName).then(() => {
        this.getEvent();
      });
    },
    addHost() {
      eventService.addHostToEvent(this.event.id, this.selectedHost).then(() => {
        this.getEvent();
      });
    },
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
.dj-name {
    margin-top: 50px;
    margin-bottom: 50px;
}
</style>
