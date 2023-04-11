<template>
  <div class="home">
    <h1>Home</h1>
    <p>You are now authenticated</p>
    <test/>
    
</div>


</template>

<script>
import Test from '../components/Test.vue'
export default {
  name: "home",
  components: {
    Test,
  }
};
</script>

<style>

</style>
