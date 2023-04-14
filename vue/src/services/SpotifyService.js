// const { Client } = require("spotify-api.js");
// const client = new Client({ 
//     token: { clientID: 'id', clientSecret: 'secret' },
// })


import axios from 'axios';
import store from '../store/index';

const tokenInstance = axios.create({
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
    },
    withCredentials: true,
    crossdomain : true
})
const tokenURL = "https://accounts.spotify.com/api/token";
const data = {
    'grant_type': 'client_credentials',
    'client_id': 'ff7f710ce2564ce19c72faebda1dfa0e',
    'client_secret': '5dfba35f85ef4c2aae0b83bfd2d1766f'
};

const authAxios = axios.create({
    baseURL: "https://api.spotify.com/v1/",
    headers: {
        'Authorization': `Bearer ${store.state.spotifyToken}`,
        'Content-Type': 'application/x-www-form-urlencoded'
    },
    withCredentials: false,
    crossdomain: true,
})

export default {
    getToken() {
        return tokenInstance.post(tokenURL, data);
    },

    getTrack(trackId) {
        return authAxios.get(`tracks/${trackId}`);
    },

    searchTrack(params) {
        let str = [];
        for (let i in params) {
            str.push(i + "=" + encodeURIComponent(params[i]))
        }
        return authAxios.get(`search?${str.join("&")}`);
    }
}