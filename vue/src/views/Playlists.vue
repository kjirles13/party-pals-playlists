<template>
  <div class="Playlists">
    <playlist-detail />
  </div>
</template>

<script>
import PlaylistDetail from "../components/PlaylistDetail.vue";

export default {
  name: "playlists",
  components: {
    PlaylistDetail,
  },
  
};
</script>