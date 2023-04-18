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
    
  </div>
</template>

<script>
import eventService from "../services/EventService";

export default {
  name: "event-detail",
  components: {},
  data() {
    return {
      isCreating: false,
      user: null,
      isVisible: false,
      isLoading: true,
      isEditing: false,
      error: "",
      clickedSongs: [],
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
    getDJEvents() {
      eventService.getDJEvents().then((response) => {
        this.djEvents = response.data;
      });
    },
    getEvents() {
      let allEvents;
      let currentUsername = this.$store.state.user.username;

      eventService.getAllEvents().then((response) => {
        allEvents = response.data;
      });
      const filteredEvents = allEvents.filter((event) => event.djUsername === currentUsername);
      this.$store.commit("SET_EVENTS", filteredEvents);
    },
  },
  created() {
    this.getEvents();
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
