<template>
  <div class = "home">
      

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

</style>