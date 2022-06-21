<template>
    <nav>
        <router-link class="navbar-brand" :to="{ name: 'Home' }">
            <img id="logo" src="" />
        </router-link>

        <div id="navbar-menu">
            <ul>
                <li>New Collection</li>
                <li>Men</li>
                <li>Women</li>
            </ul>
        </div>

        <router-link class="navbar-cart" :to="{ name: 'Cart' }">
            <img id="cart" src="../assets/shopping_bag_logo.svg" />
        </router-link>
    </nav>
</template>

<style>
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
</style>