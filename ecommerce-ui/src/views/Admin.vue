<template>
    <Navbar @toggle="toggle" />

    <div id="user">
        <SignIn v-if="!token && displaySignIn" @signin="signin"></SignIn>
        <div class="container" v-if="role === 'admin'">
            <h1>Admin Panel</h1>
            <router-link :to="{ name: 'AdminProducts', params: {role: role} }">
                <button class="btn">Edit Products</button>
            </router-link>
        </div>
        <button class="no-btn" @click="signout" v-if="token">Sign Out</button>
    </div>
</template>

<script>
import Navbar from '../components/Navbar'
import SignIn from '../components/SignIn'

export default {
    name: 'Admin',
    components: {
        Navbar,
        SignIn
    },
    data() {
        return {
            displaySignIn: true,
            token: null,
            role: null
        }
    },
    methods: {
        signout() {
            localStorage.removeItem("token");
            this.token = null;
        },
        toggle(showNew, showMen, showWomen, showFiltered) {
            this.$router.push({name: "Home", params: {
                collection: showNew,
                men: showMen,
                women: showWomen,
                filter: showFiltered
            }})
        },
        signin(role) {
            this.role = role;
            this.displaySignIn = false
        }

    },
    mounted() {
        this.token = localStorage.getItem("token");
    }
}
</script>

<style scoped>
    #user{
        width: 30%;
        margin: auto;
        padding: 1%;
        /*border: solid darkgray 2px;*/
        border-radius: 20px;
        box-shadow: 15px 15px 20px darkgray;
        margin-top: 2%;
    }

    #user h1 {
        font-family: 'Montserrat', sans-serif;
        font-weight: 500;
        font-size: 2em;
        text-align: center;
        font-weight: normal;
        margin-bottom: 50px;
    }

    #user h2 {
        font-family: 'Montserrat', sans-serif;
        font-weight: 500;
        font-size: 2em;
        text-align: center;
        font-weight: normal;
        margin-bottom: 50px;
    }

    #user .btn {
        margin-top: 0;
        margin-bottom: 10px;
        color: white;
        background-color: black;
        font-family: 'Montserrat', sans-serif;
        font-weight: 500;
        padding: 5px 12px 5px 12px;
        border-radius: 25px;
        width: 100%;
    }

    .no-btn {
        margin-top: 10px;
        margin-bottom: 10px;
        color: black;
        border: none;
        background-color: white;
        font-family: 'Montserrat', sans-serif;
        font-weight: 500;
        padding: 5px 12px 5px 12px;
        border-radius: 25px;
        width: 100%;
    }
</style>