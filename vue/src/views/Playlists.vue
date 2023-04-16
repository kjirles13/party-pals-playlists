<template>
  <div class="Playlists">
    <DJSearchSong />
    <playlist-detail />
  </div>
</template>

<script>
import DJSearchSong from '../components/DJSearchSong.vue';
import PlaylistDetail from "../components/PlaylistDetail.vue";

export default {
  name: "playlists",
  components: {
    PlaylistDetail,
    DJSearchSong
  },
  
};
</script>