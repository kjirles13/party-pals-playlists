import axios from 'axios';
const http = axios.create({
    baseURL: 'http://localhost:9000'
});



export default {
    getSongs(principal) {
        return http.get(`/songs?principal=${principal}`)
    },
    addSong(song, principal) {
        return http.post('/songs', song, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
            },
            params: {
                principal: principal
            }
        })
    },
    deleteSong(songId, principal) {
        return http.delete(`/songs/${songId}`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
            },
            params: {
                principal: principal
            }
        })
    },
    updateSong(playlistId, dto) {
        return http.patch(`/songs/${playlistId}`, {
            genre: dto.genre,
            rating: dto.rating
        }, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
            }
        })
    },
    getPlaylist() {
        return http.get('/playlist')
    }
}