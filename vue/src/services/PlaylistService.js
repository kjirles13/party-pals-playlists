import axios from 'axios';
const http = axios.create({
    baseURL: 'http://localhost:9000'
});

const api = {
    getSongs: async () => {
        const response = await http.get('/songs', {
            headers: {'Authorization': 'Bearer ${currentToken}'}
        });
        return response.data;
    },
    addSongs: async (song) => {
        const response = await http.post('/songs', song, {
            headers: {'Authorization': 'Bearer ${currentToken}'}
        });
        return response.data;
    },
    deleteSongs: async (songId) => {
        const response = await http.delete('/songs' + ${songId}, {
            headers: {'Authorization': 'Bearer ${currentToken}'}
        });
        return response.data;
    },
    updateSongs: async (id, dto) => {
        //Extracting genre and rating from dto Object
        const {genre, rating} = dto;
        const response = await http.patch('/songs/${id}', {genre, rating}, {
            headers: { 'Authorization': 'Bearer ${currentToken}'}
        });
        return response.data;
    },
    getPlaylist: async (playlistId) => {
        const response = await http.get('/playlists/${playlistId}',{
            headers: { 'Authorization' : 'Bearer ${currentToken}'}
        });
        return response.data;
    }
};

module.exports = api;

function getSongs(principal) {
    const url = '/songs';
}