import axios from 'axios';

export default {

getAllEvents() {
    return axios.get('/events');
},
getEventById(eventId){
  return axios.get(`/events/${eventId}`).then((response) => {
  return response.data;
  })
},
createEvent(event) {
  return axios.post('/events', event);
},
updateEvent(eventInfo, eventId) {
  return axios.put(`/events/${eventId}`, eventInfo);
},
deleteEvent(eventId) {
  return axios.delete(`/events/${eventId}`);
},
addHostToEvent(eventId, hosts) {
  return axios.put(`/events/${eventId}/hosts`, hosts);
},
saveLikes(song) {
  return axios.put(`/events/playlists/songs/${song.song_id}`, { votes: song.likes });
},

}

