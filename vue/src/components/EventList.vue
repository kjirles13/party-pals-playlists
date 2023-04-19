<template>
<body>
  <div>
    <div>
      <input type="text" v-model="searchText" placeholder="Search Events" />
      <br /><br />
      <table>
        <tbody>
          <tr v-for="event in filterEvents" :key="event.id">
            <td class="event-title">{{ event.name }}</td>
            <td>{{ event.date }}</td>
            <td>
              <router-link :to="{ name: 'event-detail', params: { id: event.id } }">View Details</router-link>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</body>
</template>

<script>
import eventService from "../services/EventService";

export default {
  name: "EventList",
  data() {
    return {
      searchText: "",
    };
  },
  computed: {
    filterEvents() {
      return this.$store.state.events.filter((event) => {
        return event.name.toLowerCase().includes(this.searchText.toLowerCase());
      });
    },
  },
  methods: {
    getEvents() {
      eventService.getAllEvents().then((response) => {
        this.$store.commit("SET_EVENTS", response.data);
      });
    },
  },
  created() {
      this.getEvents();
    },
};
</script>

<style scoped>
body {
  background: url('C:\Users\Student\workspace\nlr-12-module-3-final-capstone-party-playlist\vue\src\images\background.png') !important;
  background-repeat: no-repeat;
  background-size: cover !important;
  
}

.event-list {
  margin-top: 20px;
  margin-bottom: 50px;
}

table {
  border-collapse: collapse;
  width: 100%;
  height: 100%;
}

th,
td {
  text-align: left;
  padding: 20px;
  border-bottom: 1px solid #ddd;
}

th {
  background-color: #4caf50;
  color: white;
}
.event-title {
  font-size: 18px;
  font-weight: bold;
}
</style>