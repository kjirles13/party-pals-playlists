<template>
  <div>
    <button v-on:click="runTest">THE TEST BUTTON</button>
    <p v-for="line in test" v-bind:key="line.index">{{ line }}</p>

  </div>
</template>

<script>
import testService from "../services/TestService";

export default {
  name: "test",
  data() {
    return {
      test: [],
    };
  },
  methods: {
    runTest() {
      testService.test().then((response) => {
        this.test = response.data;
      });
    }
  }
};
</script>

<style>
</style>