<template>
    <div class="PlaylistDetail">
        <h1>{{ playlist.name }}</h1>
        <p>{{ playlist.description }}</p>
        <ul>
            <li v-for="song in playlist.songs" :key="song.song_id">
                {{ song.title }}</li>
        </ul>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'PlaylistDetail',
    data() {
        return {
            playlist: {}
        }
    },
    created() {
        axios.get(`http://localhost:9000/playlists/${this.$route.params.id}`)
        .then(response => {
            this.playlist = response.data;
        })
        .catch(error => {
            console.log(error);
        });
    }
}
</script>
