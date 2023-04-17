import axios from 'axios';

export default {

    getPlaylistById(playlistId) {
        return axios.get(`/${playlistId}`)
    },

    addLikes(playlistId, songId) {
        return axios.put(`/${playlistId}/songs/${songId}/likes`)
    },

    deleteLikes(playlistId, songId) {
        return axios.delete(`/${playlistId}/songs/${songId}/likes`)
    },

    removeSongFromPlaylist(playlistId, songId) {
        return axios.delete(`/${playlistId}/songs/${songId}`)
    },

    addSongToPlaylist(playlistId, songId) {
        return axios.put(`/${playlistId}/songs/${songId}`)
    },

    updatePlaylistInfo(playlistId, playlistDto) {
        return axios.put(`/${playlistId}`, playlistDto)
    }
};