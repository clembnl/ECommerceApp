<template>
    <Navbar @toggleMen="toggleMen"
            @toggleWomen="toggleWomen"
            @toggleNew="toggleNew" />

    <div id="user">
        <SignIn v-if="!token && displaySignIn" @toggleSignUp="toggleSignUp"></SignIn>
        <SignUp v-if="!token && displaySignUp" @toggleSignIn="toggleSignIn"></SignUp>
        <div class="signedIn" v-if="token">
            <h2>Already signed in !</h2>
            <router-link :to="{ name: 'Home' }">
                <button class="btn">Home</button>
            </router-link>
            <button class="no-btn" @click="signout">Sign Out</button>
        </div>
    </div>
</template>

<script>
import Navbar from '../components/Navbar'
import SignIn from '../components/SignIn'
import SignUp from '../components/SignUp'

export default {
    name: 'User',
    components: {
        Navbar,
        SignIn,
        SignUp
    },
    data() {
        return {
            displaySignIn: true,
            displaySignUp: false,
            token: null
        }
    },
    methods: {
        toggleSignIn() {
            this.displaySignIn = true;
            this.displaySignUp = false;
        },
        toggleSignUp() {
            this.displaySignIn = false;
            this.displaySignUp = true;
        },
        signout() {
            localStorage.removeItem("token");
            this.token = null;
        },
        toggleNew() {
            this.$router.push({name: "Home", params: {
                showNew: true,
                showMen: false,
                showWomen: false
            }})
        },
        toggleMen() {
            this.$router.push({name: "Home", params: {
                showNew: false,
                showMen: true,
                showWomen: false
            }})
        },
        toggleWomen() {
            this.$router.push({name: "Home", params: {
                showNew: false,
                showMen: false,
                showWomen: true
            }})
        }
    },
    mounted() {
        this.token = localStorage.getItem("token");
    }
}
</script>

<style scoped>
    #user{
        width: 33%;
        margin: auto;
        padding: 1%;
        /*border: solid darkgray 2px;*/
        border-radius: 20px;
        box-shadow: 15px 15px 20px darkgray;
        margin-top: 2%;
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

    #user .no-btn {
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