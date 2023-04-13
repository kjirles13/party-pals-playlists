<template>
    <div class="Playlist">
        <!-- button, name and description for playlist -->

        <button v-on:click="getPlaylist">DISPLAY PLAYLISTS</button>
        <!-- <div v-for="playlist in playlists" :key="playlist.id" class="playlist">
        <h1>Playlist Name:{{ playlist.name }}</h1>
        <p>Description:{{ playlist.description }}</p>
        </div> -->
        <ul>
            <!-- songs in playlist -->
            <!-- <tr v-for="event in events" :key="event.event_id"> -->
            <li v-for="playlist in playlists" :key="playlist.id">
                <p>{{ song.title }}</p>
                <p>Rating: {{ song.rating }} </p>
                <p>Votes: {{ song.votes }} </p>
                <button @click="like(song)">Like</button>
                <button @click="dislike(song)">Dislike</button>
                </li>
        </ul>
    </div>
</template>

<script> 
import axios from 'axios';

export default{
    name: 'Playlist',
    data() {
        return {
            playlist: [],
        };
    },
    async created() {
        try {
            const response = await axios.get('/playlist');
            this.events = response.data;
        } catch (error) {
            console.error(error);
        }
    },

};
</script>