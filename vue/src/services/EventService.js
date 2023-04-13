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

const createEvent = (event, principal) => {
    const data = {
        ...event,
        user_id: principal.user_id,
        role_id: principal.role_id,
    };

    return axiosInstance 
    .post('/events', data)
    .then((Response) => Response.data)
    .catch(handleErrors);
};

const updateEvent = (eventId, event, principal) => {
const data = {
    ...event,
    user_id: principal.user_id,
    role_id: principal.role_id,
};
return axiosInstance
.put(`/events/${eventId}`, data)
.then((Response) => Response.data)
.catch(handleErrors);
};

const getEvent = (eventId) => {
    return axiosInstance
    .get(`/events/${eventId}`)
    .then((Response) => Response.data)
    .catch(handleErrors);
};

const getAllEvents = () => {
    return axios.get('/events')
};

const getEventsByUser = (principal) => {
    const params = {
        user_id: principal.user_id,
        role_id: principal.role_id,
    };

    return axiosInstance
    .get('/events', { params})
    .then((Response) => Response)
    .catch(handleErrors);
};

const submitSong = (song, eventId) => {
    const data = { song_id: song.song_id, event_id: eventId};
    return axiosInstance
    .post('/playlist_song', data)
    .then((Response) => Response)
    .catch(handleErrors);
};

const voteOnSong = (songId) => {
    const data = { vote:1 };

    return axiosInstance
    .put(`/playlist_song${songId}`, data)
    .then((Response) => Response)
    .catch(handleErrors);
};

const vetoSong = (songId) => {
    const data = { vote: -1 };

    return axiosInstance
    .put(`/playlist_song/${songId}`, data)
    .then((Response) => Response)
    .catch(handleErrors);
};


const deleteSong = (songId) => {
    return axiosInstance
    .delete(`/playlist_song/${songId}`)
    .then((Response) => Response)
    .catch(handleErrors);
};


export default {
    createEvent,
    updateEvent,
    getEvent,
    getAllEvents,
    getEventsByUser,
    submitSong,
    voteOnSong,
    vetoSong,
    deleteSong,
};