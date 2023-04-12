import axios from 'axios';
const http = axios.create({
    baseURL: 'http://localhost:9000'
});
export default{
    test(){
        return http.get('/test');
    },
 getSongs() {
     return http.get('/songs');
 }
}
