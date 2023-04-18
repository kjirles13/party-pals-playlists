<template>
  <div>
      <div class="header">

      <button @click="isChangingPassword">Change Password</button>
      <form class="password-change" v-if="isClicked" method="POST" action="/">
  <label for="current-password">Current Password:</label>
  <input type="password" id="current-password" name="current-password">

  <label for="new-password">New Password:</label>
  <input type="password" id="new-password" name="new-password">

  <label for="confirm-password">Confirm New Password:</label>
  <input type="password" id="confirm-password" name="confirm-password">

  <button class="password-submit-button" type="submit">Submit</button>
  <button type="button" @click="cancelChangePassword">Cancel</button>
</form>
</div>
  </div>
</template>

<script>
export default {
name: 'account',
data(){
    return {
        isClicked: false,
    }
},
methods: {
    isChangingPassword(){
this.isClicked = !this.isClicked;
    },
    cancelChangePassword(){
        this.isClicked = false;
    }
}
}
</script>

<style>

.password-change {
    display: grid;
}

.password-submit-button {
    padding: 0;
}



</style>