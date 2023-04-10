<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
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
