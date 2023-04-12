import axios from 'axios';
// const http = axios.create({
//     baseURL: 'http://localhost:9000'
// });
export default{
    test(){
        return axios.get('/test');
    },
 getSongs() {
     return axios.get('/songs');
 }
}
