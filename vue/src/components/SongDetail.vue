<template>
    <div class="SongDetail">
        <h1>{{ song.title }}</h1>
        <p>{{ song.artist }}</p>
        <p>{{ song.genre }}</p>
        <p>{{ song.album }}</p>
        <p>{{ song.relaseDate }}</p>
        <p>{{ song.duration }}</p>
    </div>
</template>

<script>
export default {
    props: {
        song: {
            type: Object,
            required: true
        }
    }
}
</script>

<style>
</style>
