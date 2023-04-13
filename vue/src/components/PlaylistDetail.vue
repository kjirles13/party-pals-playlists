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

export default {
    name: 'PlaylistDetail',
    props: {
        song: {
            type: Object,
            required:true
        }
    },
}
</script>
