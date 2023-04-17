<template>
  <div>
    <input type="text" v-model="searchText" placeholder="Search Events"/>
    <br /><br />
    <div v-for="event in $store.state.events" :key="event.id">
     <h3>Event: {{ event.name }}</h3>
      </div> 
    <table>
      <tbody>
        <tr v-for="event in filterEvents" :key="event.id">
          <td>{{ event.name }}</td>
          <td>{{ event.date }}</td>
          <td>{{ event.id }}</td>
          <td>
            <router-link :to="{ name: 'event-detail', params: { id: event.id } }">View Details</router-link>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import eventService from "../services/EventService";
import playlistService from "../services/PlaylistService";

import axios from "axios";
import authService from "../services/AuthService";

export default {
  name: "event-detail",
  components: {
  
  },
  data() {
    return {
      user: null,
      isVisible: false,
      isLoading: true,
      isEditing: false,
      event: {},
      error: "",
      clickedSongs: [],
    };
  },
  created() {
    this.getEvent();
  },
  computed: {
    isDJ() {
      return this.$store.state.user.username === this.event.djUsername;
    },
    isHost() {
      let isHost = false;
      this.event.hosts.forEach((host) => {
        if (host.name === this.$store.state.user.username) {
          isHost = true;
        }
      })
      return isHost;
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
      eventService
        .removeHostFromEvent(this.event.id, hostName)
        .then(() => {
            this.getEvent();
        });
    },
    addHost(name) {
      eventService.addHostToEvent(this.event.id, name).then(() => {
        this.getEvent();
      })
    }
  },
  submitSong(songId, playlistId) {
    axios
      .post("/api/add-song-to-playlist", {
        songId: songId,
        playlistId: playlistId,
      })
      .then((response) => {
        this.$store.commit(response.data);
      });
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
</style>
