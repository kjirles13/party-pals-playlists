<template>
    <div class="Playlist">
        <h1>{{ playlist.name }}</h1>
        <p>{{ playlist.description }}</p>
        <ul>
            <li v-for="song in playlist.songs" :key="song.song_id">
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
import PlaylistService from '../services/PlaylistService'


export default {
    name: 'Playlist',
    props: {
        playlist: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            songs: [{}],
            getSongs: [],
        };
    },
    mounted() {
        axios.get('http://localhost:9000/playlists')
        .then(response => {
            this.playlists = response.data;
        })
        .catch(error => {
            console.log(error);
        });
    },
    methods: {
        getPlaylist() {
            PlaylistService.getSongs().then(response => {
                this.getSongs = response.data;
            }).catch(error => {
                console.log(error);
            });
        }
    }
};


</script>