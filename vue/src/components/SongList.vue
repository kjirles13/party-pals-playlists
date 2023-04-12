<template>
    <div class="song-list">
        <h2>Song List</h2>
        <ul>
            <li v-for="song in songs" :key="song.song_id">
                {{ song.title }} </li>
        </ul>
    </div>
</template>

<script> 
import axios from 'axios';

export default {
    name: 'SongList',
    data() {
        return {
            songs: [],
        };
    },
    mounted() {
        axios.get('http://localhost:9000/songs')
        .then(response => {
            this.songs = response.data;
        })
        .catch(error => {
            console.log(error);
        });
    },
};
</script>


<style scoped>
.song-list {
    margin-top: 20px;
}
.song-list h2 {
margin-bottom: 10px;
}
.song-list ul {
    list-style: none;
    padding: 0;
}
.song-list li {
    margin-bottom: 5px;
}
</style>