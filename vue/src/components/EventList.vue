<template>
  <div>
    <div>
      <input type="text" v-model="searchText" placeholder="Search Events" />
      <br /><br />
      <table>
        <tbody>
          <tr v-for="event in filterEvents" :key="event.id">
            <td class="event-title">{{ event.name }}</td>
            <td>{{ event.date }}</td>
            <td>{{ event.id }}</td>
            <td>
              <router-link
                :to="{ name: 'event-detail', params: { id: event.id } }"
                >View Details</router-link
              >
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
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
    // isDJ() {
    //   return this.$store.state.user.authorities[0].name === "ROLE_DJ";
    // },
  },
  methods: {
    getEvents() {
      eventService.getAllEvents().then((response) => {
        this.$store.commit("SET_EVENTS", response.data);
      });
    },
  },
  // created() {
  //     this.getEvents();
  //   },
};
</script>

<style scoped>
.banner-clickable {
  position: relative;
}
.banner img {
  max-width: 100%;
}

.event-list {
  margin-top: 20px;
}

table {
  border-collapse: collapse;
  width: 100%;
}

th,
td {
  text-align: left;
  padding: 8px;
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