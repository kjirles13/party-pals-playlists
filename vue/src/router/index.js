import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Events from '../views/Events.vue'
import Playlists from '../views/Playlists.vue'
import EventDetail from '../components/EventDetail.vue'
import Spotify from '../views/SpotifyTestView.vue'
import MyEvents from '../views/MyEvents.vue'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: "/login",
      name: "login",
      component: Login,
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
    },
    {
      path: "/events",
      name: "events",
      component: Events,
    },
    {
      path: "/playlists",
      name: "playlists",
      component: Playlists,
    },
    {
      path: "/register",
      name: "register",
      component: Register,
    },
    {
      path: "/events/:id",
      name: "event-detail",
      component: EventDetail,
    },
    {
      path: "/spotify",
      name: "spotify",
      component: Spotify,
    },
    {
      path: "/MyEvents",
      name: "myevents",
      component: MyEvents,
    },
  ]
})

router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    next();
  }
});

export default router;