import axios from 'axios';




export default {
    getSongs(principal) {
        return axios.get(`/songs?principal=${principal}`)
    },
    addSong(song, principal) {
        return axios.post('/songs', song, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
            },
            params: {
                principal: principal
            }
        })
    },
    deleteSong(songId, principal) {
        return axios.delete(`/songs/${songId}`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
            },
            params: {
                principal: principal
            }
        })
    },
    updateSong(playlistId, dto) {
        return axios.patch(`/songs/${playlistId}`, {
            genre: dto.genre,
            rating: dto.rating
        }, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
            }
        })
    },
    getPlaylist(playlistId) {
        return axios.get(`/playlists/${playlistId}`)
    }
}