<template>
  <div class="event-detail">
    <div v-if="isLoading">Loading...</div>
    <h1>{{ event.name }}</h1>
    <p>Description: {{ event.description }}</p>
    <p>Theme: {{ event.theme }}</p>
    <p>Date: {{ event.date }}</p>
    <p>Time: {{ event.time }}</p>
    <div v-for="p in event.hosts" :key="p.hostId"> 
      <p>{{ p.name }}</p>
    </div>
    <p>{{ event.djUsername }}</p>
    <p>Playlist: {{ event.playlist.name }}</p>
    <div class="song-info">
    <song-display v-for="song in event.playlist.songs" :key="song.song_id" :song="song">
      <div style="display: flex; flex-direction: column;">
        <img src="../images/thumbs-up.png" alt="Spotify" width="15" height="15" style="margin-bottom: 10px; cursor: pointer;" @click="incrementLikes(song)" :class="{ disabled: song.clicked }">
        <span>{{ song.likes }}</span>
        <img src="../images/thumbs-down.png" alt="Spotify" width="15" height="15" style="cursor: pointer;" @click="decrementLikes(song)" :class="{ disabled: song.clicked }">
      </div>
    </song-display>
  </div>
  </div>
</template>

<script>
import eventService from '../services/EventService';
import SongDisplay from '@/components/SongDisplay.vue';

export default {
  name: "event-detail",
  components: {
    SongDisplay
  },
  data() {
    return {
      isVisible: false,
      isLoading: true,
      event: {
        name: '',
        description: '',
        date: '',
        time: '',
        playlist: {
          name: '',
          songs: [],
        },
        hosts: [],
        djUsername: '',
      },
      error: '',
    };
  },
  created() {
    const eventId = parseInt(this.$route.params.id);
    eventService.getEventById(eventId)
      .then((response) => {
        this.event = response;
        this.isLoading = false;
      })
      .catch((error) => {
        console.log(error);
        this.isLoading = false;
        this.error = 'Error loading event';
      });
  },
 methods: {
    incrementLikes(song) {
      if (!song.clicked) {
        song.likes++;
        song.clicked = true;
        this.saveLikes(song);
      }
    },
    decrementLikes(song) {
      if (!song.clicked) {
        song.likes--;
        song.clicked = true;
        this.saveLikes(song);
      }
    },
    
  },
};
</script>

<style scoped>
.event-detail {
    margin: 100px;
}
.disabled {
    opacity: 0.2;
    pointer-events: none;
  }


</style>
