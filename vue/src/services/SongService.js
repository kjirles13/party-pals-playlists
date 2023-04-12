import axios from "axios";

// const apiClient = axios.create({
//     baseURL: 'http://localhost:9000',
//     headers: {
//         Accept: 'application/json',
//         'Content-Type' : 'application/json'
//     }
// });

export default {
    getSongs() {
        return axios.get('/songs');
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