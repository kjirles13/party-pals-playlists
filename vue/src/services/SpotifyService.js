/* import SpotifyWebApi from "spotify-web-api-js";
const spotifyApi = new SpotifyWebApi({
    clientId: 'kits_clientId',
    clientSecret: 'kits_clientSecret'
});


spotifyApi.getMyPlaylists()
.then(data => {
    console.log('User playlists', data);
})
.catch(error => {
    console.error('Error fetching user playlists', error);
})

function getSongs(playlist_id, options = {}) {
    return spotifyApi.getPlaylistTracks(playlist_id, options);
}

function addSong(playlist_id, trackUri) {
    return spotifyApi.addTracksToPlaylist(playlist_id, [trackUri]);
}

function deleteSong(playlist_id, trackId) {
    return spotifyApi.removeTracksFromPlaylist(playlist_id, [{ uri: 'spotify:track:${trackId}'}]);
}

function updateSong(trackId, playlist_id, genre, rating) {
    return spotifyApi.getTrack(trackId)
    .then(track => {
        const options = {
            "uris": [track.uri],
            "positions": [0],
            "metadata": {
                "genres": [genre],
                "rating": rating
            }
        };
        return spotifyApi.changePlaylistDetails(playlist_id, options);
    });
} 
*/