<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'homepage' }">Landing Page</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'home' }">Login</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>
