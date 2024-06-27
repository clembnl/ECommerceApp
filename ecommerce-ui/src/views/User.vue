<template>
    <Navbar @toggle="toggle" />

    <div id="user">
        <SignIn v-if="!token && displaySignIn" @toggleSignUp="toggleSignUp"></SignIn>
        <SignUp v-if="!token && displaySignUp" @toggleSignIn="toggleSignIn"></SignUp>
        <WishList v-if="token"></WishList>
        <button class="no-btn" @click="signout" v-if="token">Sign Out</button>
    </div>
</template>

<script>
import Navbar from '../components/Navbar'
import SignIn from '../components/SignIn'
import SignUp from '../components/SignUp'
import WishList from '../components/WishList'

export default {
    name: 'User',
    components: {
        Navbar,
        SignIn,
        SignUp,
        WishList
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
        toggle(showNew, showMen, showWomen, showFiltered) {
            this.$router.push({name: "Home", params: {
                collection: showNew,
                men: showMen,
                women: showWomen,
                filter: showFiltered
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
        width: 30%;
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

@media (max-width: 1050px) {
    #user {
        width: 40% !important;
    }
}

@media (max-width: 990px) {
    #user {
        width: 45% !important;
    }
}

@media (max-width: 890px) {
    #user {
        width: 50% !important;
    }
}

@media (max-width: 800px) {
    #user {
        width: 55% !important;
    }
}

@media (max-width: 730px) {
    #user {
        width: 60% !important;
    }
}

@media (max-width: 540px) {
    #user {
        width: 65% !important;
    }
}

@media (max-width: 500px) {
    #user {
        width: 70% !important;
    }
}

@media (max-width: 470px) {
    #user {
        width: 75% !important;
    }
}

@media (max-width: 430px) {
    #user {
        width: 80% !important;
    }
}

@media (max-width: 400px) {
    #user {
        width: 85% !important;
    }
}
</style>