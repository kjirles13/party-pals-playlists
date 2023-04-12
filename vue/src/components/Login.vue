<template>
    <div class="Login">
        <h1>Login</h1>
        <form>
            <div>
                <label for="username">Username:</label>
                <input type="text" id="username" v-model="username" />
            </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" v-model="password"/>
        </div>
        <button type="submit" @click.prevent="login">Login</button>
        <p v-if="errorMessage">{{ errorMessage }}</p>
        </form>
    </div>
</template>

<script>
export default{
    data() {
        return {
            username: '',
            password: '',
            errorMessage: '',
        };
    },
    methods: {
        async login() {
            try {
                const response = await this.$http.post('/login', {
                    username: this.username,
                    password: this.password,
                });
                const user = response.data;
                this.$store.commit('setUser', user);
                this.$router.push('/');
            } catch (error) {
                this.errorMessage = 'Invalid username or password';
            }
        },
    },
};
</script>

<style scoped>
.Login {
    max-width: 400px;
    margin: 0 auto;
}
form {
    display: flex;
    flex-direction: column;
    gap: irem;
    margin-top: irem;
}
label {
    font-weight: bold;
}
input {
    padding: 0.5rem;
    border-radius: 0.25rem;
    border: 1px solid gray;
}
button {
    padding: 00.5rem;
    border-radius: 0.25rem;
    background-color: white;
    border: none;
}
</style>
