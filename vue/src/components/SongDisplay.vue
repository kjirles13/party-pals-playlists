<template>
  <div class="Page">
    <div class="SongList">
      <div class="song">
        <div class="song-info">
          <div>
            <p5
              ><strong>{{ song.name }}</strong></p5
            >
            <span class="stars"
              ><img
                v-for="n in song.rating"
                :key="n"
                class="gold-star"
                src="../images/StarRating.png"
                alt="Gold star"
            /></span>

            <div class="artist-wrapper">
              <div>
                <p v-for="artist in song.artists" :key="artist.id">
                  {{ artist.name }}
                </p>
              </div>
            </div>
          </div>
          <div class="Stars"></div>
        </div>
        <div class="song-links">
          <audio controls @play="setVolume">
            <source :src="song.preview" type="audio/mpeg" />
            <p>
              <a href="song.preview">Preview{{ song.preview }}</a>
            </p>
          </audio>
          <a :href="song.spotifyUri" target="_blank"
            ><img
              src="../images\image-gallery-spotify-logo-21.png"
              alt="Spotify"
              width="56"
              height="56"
          /></a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import songService from "../services/SongService";

export default {
  name: "SongList",
  props: ["song"],
  data() {
    return {
      getSongs: [],
    };
  },
  methods: {
    searchSongs() {
      songService
        .getSongs()
        .then((response) => {
          this.getSongs = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    setVolume(event) {
      event.target.volume = 0.2;
    },
  },
};
</script>

<style scoped>
.SongList {
  margin-top: 20px;
}
.song {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  background: linear-gradient(to bottom, #c4cbda, #dee2ec);
  /* border: 1px solid black; */
  border-radius: 19px;
  margin-top: 8px;
  margin-bottom: 8px;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.3);
}
.Stars {
  display: flex;
  flex-grow: 2;
  align-items: right;
  margin-left: 10;
}
.song-info {
  display: flex;
  flex-direction: row;
  align-items: center;
}
p5 {
  display: inline-block;
  margin-top: 15px;
  margin-left: 15px;
  margin-right: 10px;
}
.artist-wrapper {
  display: flex;
  flex-wrap: wrap;
  margin-left: 15px;
}
.song-links {
  display: flex;
  align-items: center;
}
.gold-star {
  width: 15px;
  height: 15px;
  margin-right: 1px;
}

audio::-webkit-media-controls-panel {
  background: linear-gradient(to bottom, #a6b3be, #c5cad3);
}
audio::-webkit-media-controls-play-button {
  background-color: #9099a1;
  border-radius: 50%;
}
audio::-webkit-media-controls-play-button:hover {
  background-color: #727479;
}
audio::-webkit-media-controls-current-time-display {
  color: black;
}
audio::-webkit-media-controls-time-remaining-display {
  color: black(255, 255, 255);
}
audio::-webkit-media-controls-mute-button {
  background-color: #9099a1;
  border-radius: 50%;
}
audio::-webkit-media-controls-volume-slider {
  background-color: #9099a1;
  border-radius: 25px;
  padding-left: 8px;
  padding-right: 8px;
}
</style>