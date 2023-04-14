<template>
  <div class="Page">
    <div class="SongList">
      <div v-for="song in songs" v-bind:key="song.id" class="song">
        <div class="song-info">
          <div>
            <p5>Song: {{ song.name }}</p5>
            <div class="artist-wrapper">
              <p>Artists:</p>
              <div v-for="artist in song.artists" :key="artist.id">
                <p>{{ artist.name }}</p>
              </div>
            </div>  
          </div>
          <div class="Stars">
            <p>
              <span style="display: inline-block; text-align: right;">
                Stars:
                <img v-for="n in song.rating" :key="n" class="gold-star" src="../images\GoldStar.png" alt="Gold star">
              </span>
            </p> 
          </div>
        </div>
        <div class="song-links">
          <audio controls @play="setVolume">
            <source :src="song.preview" type="audio/mpeg">
            <p><a href="song.preview">Preview{{song.preview}}</a></p>
          </audio>
          <a :href="song.spotifyUri"><img src="../images\image-gallery-spotify-logo-21.png" alt="Spotify" width="56" height="56"></a>
        </div>
      </div>
    </div>
  </div>
</template>

<script> 
import songService from "../services/SongService"

export default {
  name: 'SongList',
  data() {
    return {
      songs: [],
      getSongs: [],
    };
  },
  created() {
    songService.getSongs().then(response => {
      if (response.status == 200) {
        this.songs = response.data;
      }
    })
  },
  methods: {
    searchSongs() {
      songService.getSongs().then(response => {
        this.getSongs = response.data;
      }).catch(error => {
        console.log(error);
      });
    },
    setVolume(event) {
      event.target.volume = 0.2; 
    }
  }
};
</script>

<style scoped>
.SongList{
  margin-top: 20px;
}
.song {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  background: linear-gradient(to bottom, #a8896cb7, #a8896c);
  border: 1px solid black;
  border-radius: 19px;
  margin-top: 8px;
  margin-bottom: 8px;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.3);
}
.Stars{
  display: flex;
  align-items: center;
  margin-left: 10;
}
.song-info {
  display: flex;
  flex-direction: row;
  align-items: center;
}
p5{
  margin-top:115px;
  margin-left: 15px;
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
.gold-star{
  width: 15px;
  height: 15px;
  margin-right: 1px;
}


audio::-webkit-media-controls-panel {
  background: linear-gradient(to bottom, #a8896c, #a8896cb7);
}
audio::-webkit-media-controls-panel {
  background-color: #535a3b;
}
audio::-webkit-media-controls-play-button {
  background-color: #a8896cb7;
  border-radius: 50%;
}
audio::-webkit-media-controls-play-button:hover {
  background-color: #a8896c;
}
audio::-webkit-media-controls-current-time-display {
  color: #fff;
}
audio::-webkit-media-controls-time-remaining-display {
  color: rgb(255, 255, 255);
}
audio::-webkit-media-controls-mute-button {
  background-color: #a8896cb7;
  border-radius: 50%;
}
audio::-webkit-media-controls-volume-slider {
  background-color: #a8896cb7;
  border-radius: 25px;
  padding-left: 8px;
  padding-right: 8px;
}

</style>