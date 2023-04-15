// const { Client } = require("spotify-api.js");
// const client = new Client({ 
//     token: { clientID: 'id', clientSecret: 'secret' },
// })

import axios from 'axios';
import store from '../store/index';

let SpotifyWebApi = require('spotify-web-api-node');

const spotifyApi = new SpotifyWebApi({
  clientId: 'ff7f710ce2564ce19c72faebda1dfa0e',
  clientSecret: '5dfba35f85ef4c2aae0b83bfd2d1766f',
});

spotifyApi.setAccessToken('BQDy8IvOwYZHIL97XNmPpEvmjRfhAvt9UI5FLrkTaDBpdV7882arkJkDciW--N1OwbYkGfbRxl4mz-J6XgcG9e1sKjHiYPqyuYeRM7Tstz-H2nUOhCpy')

// const refreshToken = '<user_refresh_token>';
// spotifyApi.setRefreshToken(refreshToken);



// const tokenInstance = axios.create({
//     headers: {
//         'Content-Type': 'application/x-www-form-urlencoded',
//         'Authorization': 'Basic ' + new Buffer.from('ff7f710ce2564ce19c72faebda1dfa0e:5dfba35f85ef4c2aae0b83bfd2d1766f').toString('base64')
//     },
//     json: true,
//     withCredentials: true,
//     crossdomain : true
// })
// const tokenURL = "https://accounts.spotify.com/api/token";
// const data = {
//     grant_type: 'client_credentials',
// };

const authAxios = axios.create({
    baseURL: "https://api.spotify.com/v1/",
    headers: {
        'Authorization': `Bearer ${store.state.spotifyToken}`,
        'Content-Type': 'application/x-www-form-urlencoded'
    },
    withCredentials: false,
    crossDomain: true
})

export default {
    getToken() {
        spotifyApi.clientCredentialsGrant().
    then(function(result) {
        console.log('It worked! Your access token is: ' + result.body.access_token); 
    }).catch(function(err) {
        console.log('If this is printed, it probably means that you used invalid ' +
        'clientId and clientSecret values. Please check!');
        console.log('Hint: ');
        console.log(err);
    });
    },

    getTrack(trackId) {
        return authAxios.get(`tracks/${trackId}`);
    },

    getArtist(artistId) {
        return spotifyApi.getArtist(artistId)
    },

    searchTrack(params) {
        let str = [];
        for (let i in params) {
            str.push(i + "=" + encodeURIComponent(params[i]))
        }
        return authAxios.get(`search?${str.join("&")}`);
    }
}