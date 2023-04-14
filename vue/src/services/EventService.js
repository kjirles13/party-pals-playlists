import axios from 'axios';

export default {

getAllEvents() {
    return axios.get('/events');
},

getEventById(eventId){
    return axios.get(`/events${eventId}`)
}

}
