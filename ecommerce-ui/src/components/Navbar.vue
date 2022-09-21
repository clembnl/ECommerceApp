<template>
    <nav>
        <div id="navbar-logo">
            <router-link class="navbar-brand" :to="{ name: 'Home' }">
                <img id="logo" src="../assets/logo_sneakers.png" width="200" />
            </router-link>
        </div>

        <div id="navbar-menu">
            <ul>
                <li><button @click="toggleNew">New Arrivals</button></li>
                <li><button @click="toggleMen">Men</button></li>
                <li><button @click="toggleWomen">Women</button></li>
            </ul>
        </div>

        <div id="navbar-icon">
            <router-link class="navbar-account" :to="{ name: 'User' }">
                <img id="account" src="../assets/account_logo.svg" />
            </router-link>

            <router-link class="navbar-cart" :to="{ name: 'Cart' }">
                <img id="cart" src="../assets/shopping_bag_logo.svg" />
            </router-link>
        </div>
    </nav>
</template>

<script>
export default {
    methods: {
        toggleNew() {
            this.$emit('toggleNew');
        },
        toggleMen() {
            this.$emit('toggleMen');
        },
        toggleWomen() {
            this.$emit('toggleWomen');
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
}

</style>