<template>
    <div class="PlaylistDetail">
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

export default {
    name: 'PlaylistDetail',
    props: {
        song: {
            type: Object,
            required:true
        }
    },
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
    },
    methods: {
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
