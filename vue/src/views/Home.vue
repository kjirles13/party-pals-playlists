<template>
  <div class="home">
   <about-us/>
  </div>
</template>

<script>
import AboutUs from '../components/AboutUs.vue';

export default {
  name: 'home',
  components: {
    AboutUs,
  },
  mounted() {
    document.title = "Home";
  }
};
</script>

<style>
</style>