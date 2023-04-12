<template>
  <div>
      <button v-on:click = "runTest">THE TEST BUTTON</button>
      <button v-on:click= "searchSongs">DISPLAY SONGS</button>
      <p v-for="line in test" v-bind:key="line.index"> {{line}} </p>

  </div>
</template>

<script>

import testService from "../services/TestService"
import songService from "/Users/Student/workspace/nlr-12-module-3-final-capstone-party-playlist/vue/src/services/SongService"

export default {
    name: 'test',
    data(){
        return{
            test: [],
            getSongs: [],

        }
    },
    methods: {
        runTest(){
            testService.test().then(response => {
                this.test = response.data;
            })
        },
        searchSongs() {
            songService.getSongs().then(response => {
                this.getSongs = response.data;
            }).catch(error => {
                console.log(error);
            });
        }
    }
    

}
</script>

<style>

</style>