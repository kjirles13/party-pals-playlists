<template>
    <div class="Playlist">
        <!-- button, name and description for playlist -->
        <button v-on:click="getPlaylist">DISPLAY PLAYLISTS</button>
        <div v-for="playlist in playlists" :key="playlist.id" class="playlist">
        <h1>Playlist Name:{{ playlist.name }}</h1>
        <p>Description:{{ playlist.description }}</p>
        </div>
        <ul>
            <!-- songs in playlist -->
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
            playlists: [{}],
        }
    },
    mounted() {
        axios.get(`http://localhost:9000/playlists/${this.$route.params.id}`)
        .then(response => {
            this.playlists = response.data;
        })
        .catch(error => {
            console.log(error);
        });
    },
    methods: {
        getPlaylist() {
            PlaylistService.get('http://localhost:9000/playlists')
            .then(response => {
                this.playlists = response.data;
            })
            .catch(error => {
                console.log(error);
            });
        },
        like(song) {
            song.likes++;
            axios.put(`http://localhost:9000/songs/${song.song_id}`, song)
            .then(response => {
                console.log(response.data);
            })
            .catch(error => {
                console.log(error);
            });
        },
        dislike(song){
            song.dislikes--;
            axios.put(`http://localhost:9000/songs/${song.song_id}`, song)
            .then(response => {
                console.log(response.data);
            })
            .catch(error => {
                console.log(error);
            });
        }
    }
}

</script>