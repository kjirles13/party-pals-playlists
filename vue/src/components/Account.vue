<template>
  <div>
      <div class="header">
      <button @click="isChangingPassword">Change Password</button>
      <button @click="isChangingPassword">Change Password</button>
      </div>
      <div class="header-change-password">

      
      <form class="password-change" v-if="isClicked" method="POST" action="/">
  <label class="change-password-label" for="current-password">Current Password:</label>
  <input type="password" id="current-password" name="current-password">

  <label class="change-password-label" for="new-password">New Password:</label>
  <input type="password" id="new-password" name="new-password">

  <label class="change-password-label" for="confirm-password">Confirm New Password:</label>
  <input type="password" id="confirm-password" name="confirm-password">

  <button class="password-submit-button" type="submit">Submit</button>
  <button class="password-cancel-button" type="button" @click="cancelChangePassword">Cancel</button>
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
    max-width: 100px;
    padding: 0;
}

.password-cancel-button {
    max-width: 100px;
    padding: 0;
}

.change-password-label {
    font-weight: bold;
}

.header-change-password {
    display: flex;
    align-items: center;
    justify-content: center;
    max-width: 500px;
}

.header {
    background-color: lightgray;
    padding: 10px 10px 10px 10px;
    margin-top: -8px;
    margin-left: -23px;
    margin-right: -8px;
}



</style>