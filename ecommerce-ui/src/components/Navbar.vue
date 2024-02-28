<template>
    <nav>
        <div id="navbar-logo">
            <router-link class="navbar-brand" :to="{ name: 'Home' }">
                <img id="logo" src="../assets/logo_sneakers.png" width="200" />
            </router-link>
        </div>

        <div id="navbar-menu">
            <ul>
                <li><button @click="toggle(true, false, false, false)">New Arrivals</button></li>
                <li><button @click="toggle(false, true, false, false)">Men</button></li>
                <li><button @click="toggle(false, false, true, false)">Women</button></li>
            </ul>
        </div>

        <div id="navbar-icon">
            <router-link class="navbar-user" :to="{ name: 'User' }">
                <img id="user" src="../assets/account_logo.svg" />
            </router-link>

            <router-link class="navbar-account" :to="{ name: 'Account' }">
                <img id="cart" src="../assets/shopping_bag_logo.svg" />
            </router-link>
        </div>
    </nav>
</template>

<script>
export default {
    methods: {
        toggle(showNew, showMen, showWomen, showFiltered) {
            this.$emit('toggle', showNew, showMen, showWomen, showFiltered);
        }
    }
}
</script>

<style scoped>
nav {
    font-family: 'Montserrat', sans-serif;
    display: flex;
    justify-content: space-between;
    margin: 20px;
    opacity: 0.5;
}

#navbar-menu ul {
    display: flex;
    list-style: none;
    padding-inline-start: 0;
}

#navbar-menu ul li{
    padding: 20px;
}

#logo {
    padding-top: 20px;
}

#account {
    padding-top: 20px;
    margin-left: 30px;
    margin-right: 15px;
}

#cart {
    padding-top: 20px;
    margin-left: 15px;
    margin-right: 20px;
}

button {
    background-color: transparent;
    border: none;
    font-size: 1.2em;
    padding: 0;
}

button:hover{
    text-shadow: 0 0 0.6px black;
}

</style>