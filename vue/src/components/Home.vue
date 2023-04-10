<template>
  <div class = "home">
      <button @click="Events">Click here for Events</button> 

  </div>
</template>

<script>

export default {
    name: 'Home',
    methods:{
        navigateToEvents(){
            this.$router.push('/Events');
        },
        navigateToPlaylists(){
            this.$router.push('/Playlists');
        },
        navigateToSongs(){
            this.$router.push('/Songs');
        },
        navigateToHome(){
            this.$router.push('/Home');
        },
    },


}
</script>

<style>
.home{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
    font-size: 2rem;
}
</style>