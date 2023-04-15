<template>
    <div class="search-song">
        <h1> Search Songs </h1>
        <div class="search-form">
            <label for="title">Title:</label>
            <input type="text" v-model="title" id="title"/>
            <button @click="searchSongs">Search</button>
        </div>
        <div class="song-list">
            <h2>Results:</h2>
            <ul>
                <li v-for="song in songs" :key="song.song_id" @click="showSongDetail(song)"> {{ song.title }} </li>
            </ul>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import { showModel } from '@/helpers/modalHelper';

export default {
    name: 'SearchSong',
    data() {
        return {
            title: '',
            songs: [],
            selectedSong: null,
        };
    },
    methods: {
        searchSongs() {
            axios.get('/api/songs', {
                params: {
                    title: this.title,
                },
            })
            .then(response => {
                this.songs = response.data;
            })
            .catch(error => {
                console.error(error);
            });
        },
        showSongDetail(song) {
            this.selectedSong = song;
            showModel("#song-detail-modal");
        },
    },
};
</script>
