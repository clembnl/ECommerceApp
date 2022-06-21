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
            <img id="cart" src="" />
        </router-link>
    </nav>
</template>