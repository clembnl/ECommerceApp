<template>
    <nav>
        <router-link class="navbar-brand" :to="{ name: 'Home' }">
            <img id="logo" src="" />
        </router-link>

        <div id="navbar-menu">
            <ul>
                <li><button @click="toggleNew">New Arrivals</button></li>
                <li><button @click="toggleMen">Men</button></li>
                <li><button @click="toggleWomen">Women</button></li>
            </ul>
        </div>

        <router-link class="navbar-cart" :to="{ name: 'Cart' }">
            <img id="cart" src="../assets/shopping_bag_logo.svg" />
        </router-link>
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
}

#navbar-menu ul li{
    padding: 20px;
}

#cart {
    padding-top: 15px;
}

button {
    background-color: transparent;
    border: none;
    font-size: 1.2em;
}

</style>