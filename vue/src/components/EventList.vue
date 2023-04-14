<template>
<div class="banner clickable">
    <img :src="require('@/images/defaultbanner.png')"/>
    <div v-for="event in $store.state.events" :key="event.event_id">
     <h3>Event: {{ event.name }}</h3>
      </div>
    <h2 v-if="event">{{ event.name }}</h2>
        <table>
            <tbody>
                <tr v-for="event in $store.state.events" :key="event.event_id">
                   <td>{{ event.name }}</td>
                   <td>{{ event.date }}</td>
                   <td> <router-link :to="{ name: 'Event Detail', params: { id: event.event_id}}">
                       View Details 
                       </router-link>
                   </td>
                </tr>
            </tbody>
        </table>
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
                if(response.data.length > 0) {
                    this.event = response.data[0];
                }
            }
        });
    },
}
</script>

<style>
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