<template>
  <div class="event">
      <event-detail></event-detail>
  </div>
</template>

<script>
import eventDetail from '../components/EventDetail.vue'
export default {
name: "event-view",
components: {
    eventDetail
}
}
</script>

<style>

</style>