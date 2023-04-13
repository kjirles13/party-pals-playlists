import axios from "axios";


export default {
    getSongs() {
        return axios.put('/songs/list', {username: "kit"});
    },
    getSong(id) {
        return axios.get(`/songs/${id}`);
    },
    createSong(song) {
        return axios.post('/songs', song);
    },
    updateSong(id, song) {
        return axios.put(`/songs/${id}`, song)
    },
    deleteSong(id) {
        return axios.delete(`/songs/${id}`);
    }
}