<template>
    <Navbar @toggle="toggle" />

    <div id="user">
        <SignIn v-if="!token" @signin="signin"></SignIn>
        <div class="container" v-if="role === 'ROLE_ADMIN'">
            <h1>Admin Panel</h1>
            <router-link :to=" { name: 'AddProduct', params: {role: role} }" >
                <button class="btn">Add Product</button>
            </router-link>
            <router-link :to="{ name: 'AdminProducts', params: {role: role} }">
                <button class="btn">Edit Products</button>
            </router-link>
        </div>
        <button class="no-btn" @click="signout" v-if="token">Sign Out</button>
    </div>
</template>

<script>
import axios from 'axios';
import Navbar from '../components/Navbar';
import SignIn from '../components/SignIn';
import authHeader from "../services/auth-header.js";

export default {
    name: 'Admin',
    components: {
        Navbar,
        SignIn
    },
    data() {
        return {
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
            this.token = localStorage.getItem("token");
        },
        getRole() {
            if (this.token) {
                axios
                .get('/api/user/role', { headers: authHeader() })
                .then((res) => {
                    this.role = res.data.message
                })
                .catch((err) => console.log("err", err));
            }
        }

    },
    mounted() {
        this.token = localStorage.getItem("token");
        this.getRole();
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