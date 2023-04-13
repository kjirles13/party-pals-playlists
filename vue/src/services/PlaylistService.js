import axios from 'axios';

const baseURL = 'http://localhost:9000';

const axiosInstance = axios.create({
    baseURL: baseURL,
    headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
    },
});
const handleErrors = (error) => {
    console.error(error);
    throw error;
};

const getSong = (songId) => {
    return axiosInstance
    .get(`/songs/${songId}`)
    .then((Response) => Response.data)
    .catch(handleErrors);
};
const getAllSongs = () => {
    return axiosInstance
    .get('/events')
    .then((Response) => Response)
    .catch(handleErrors);
};
//     getSongs(principal) {
//         return axios.get(`/songs?principal=${principal}`)
//     },

    
//     addSong(song, principal) {
//         return axios.post('/songs', song, {
//             headers: {
//                 Authorization: `Bearer ${localStorage.getItem('token')}`
//             },
//             params: {
//                 principal: principal
//             }
//         })
//     },
//     deleteSong(songId, principal) {
//         return axios.delete(`/songs/${songId}`, {
//             headers: {
//                 Authorization: `Bearer ${localStorage.getItem('token')}`
//             },
//             params: {
//                 principal: principal
//             }
//         })
//     },
//     updateSong(playlistId, dto) {
//         return axios.patch(`/songs/${playlistId}`, {
//             genre: dto.genre,
//             rating: dto.rating
//         }, {
//             headers: {
//                 Authorization: `Bearer ${localStorage.getItem('token')}`
//             }
//         })
//     },
//     getPlaylist(playlistId) {
//         return axios.get(`/playlists/${playlistId}`)
//     },
//     const getAllPlaylist() = () => {
//         return axiosInstance
//         .get('/events')
//         .then((Response) => Response)
//         .catch(handleErrors);
//     };
    
 

export default {
    // createSong,
    // updateSong,
    getSong,
    getAllSongs,
    // getSongsByUser,
    // submitSong,
    // voteOnSong,
    // vetoSong,
    // deleteSong,
};