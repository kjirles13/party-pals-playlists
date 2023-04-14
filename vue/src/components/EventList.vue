<template>
    <div class="event-list">
        <h1> Event List </h1>
        <table>
            <thead>
                <tr>

                    
                </tr>
            </thead>
            <tbody>
                <tr v-for="event in $store.state.events" :key="event.event_id">
                   
                </tr>
            </tbody>
        </table>
        <div v-for="event in $store.state.events" :key="event.event_id">
      <h3>Event: {{ event.name }}</h3>
      </div>
    </div>
</template>

<script> 
import eventService from "../services/EventService"

export default{
    name: 'EventList',
    created() {
        eventService.getAllEvents().then(response => {
            if (response.status == 200) {
                this.$store.commit("SET_EVENTS", response.data);
            }
        });
    },
};
</script>

<style>
.event-list {
    margin-top: 20px;
}

table {
    border-collapse: collapse;
    width:100%;
}

th, td {
    text-align: left;
    padding: 8px;
    border-bottom: 1px solid #ddd;
}

th {
    background-color: #4caf50;
    color: white;
}
</style>