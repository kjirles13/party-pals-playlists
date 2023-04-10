<template>
  <div class="HomePage">
    <h1>Welcome Party Playlist Pal</h1>
    <p>Home Landing Page</p>
    <button @click="goToEvent">Event</button>
  </div>
</template>

<script>
export default {
  methods: {
    goToEvent() {
      this.$router.push('/event');
    }
  }
};
</script>

<style>
.home {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
.home h1 {
  font-size: 3rem;
  margin-bottom: 2rem;
}
.home p {
  font-size: 1.5rem;
  margin-bottom: 2rem;
}
.home button {
  padding: 1rem 2rem;
  border-radius: 5px;
  background-color: #6ca6e7;
  color: rgb(245, 179, 179);
  font-size: 1.5rem
}
</style>