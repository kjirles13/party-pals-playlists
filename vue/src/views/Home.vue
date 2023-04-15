<template>
  <div class="home">
    <h1>Home</h1>
    <p>You are now authenticated</p>

    <about-us> </about-us>
</div>


</template>

<script>
import AboutUs from '../components/AboutUs.vue';


export default {
  name: 'home',
  components: {
    AboutUs

  },
};
</script>

<style>

</style>
