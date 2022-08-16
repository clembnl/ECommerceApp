<template>
    <Navbar @toggleMen="toggleMen"
            @toggleWomen="toggleWomen"
            @toggleNew="toggleNew" />

    <div id="home">

        <div class="container" v-if="showNew">
            <div class="row-title">
                <h1>New Arrivals</h1>
                <button class="btn transparent" v-if="!showFilters" @click="filterClick()">Filters</button>
            </div>
            <Filters v-if="showFilters" @closeFilters="filterClick"/>
            <div class="row">
                <ProductCard v-for="item in this.newArrivals" :product="item" :key="item.id" />
            </div>
        </div>

        <div class="container" v-if="showMen">
            <div class="row">
                <h1>Men</h1>
                <button class="btn transparent" v-if="!showFilters" @click="filterClick()">Filters</button>
            </div>
            <div class="row">
                <ProductCard v-for="item in this.men" :product="item" :key="item.id" />
            </div>
        </div>

        <div class="container" v-if="showWomen">
            <div class="row">
                <h1>Women</h1>
                <button class="btn transparent" v-if="!showFilters" @click="filterClick()">Filters</button>
            </div>
            <div class="row">
                <ProductCard v-for="item in this.women" :product="item" :key="item.id" />
            </div>
        </div>
    </div>
</template>

<script>
import Navbar from '../components/Navbar.vue'
import Filters from '../components/Filters.vue'
import ProductCard from '../components/ProductCard.vue'
import axios from 'axios'

export default {
    name: 'Home',
    components: {
        Filters,
        ProductCard,
        Navbar,
    },
    data() {
        return {
            products: [],
            newArrivals: [],
            men: [],
            women: [],
            showFilters: false,
            showNew: true,
            showMen: false,
            showWomen: false,
        }
    },
    methods: {
        toggleNew() {
            this.showNew = true;
            this.showMen = false;
            this.showWomen = false;
        },
        toggleMen() {
            this.showNew = false;
            this.showMen = true;
            this.showWomen = false;
        },
        toggleWomen() {
            this.showNew = false;
            this.showMen = false;
            this.showWomen = true;
        },
        filterClick() {
            this.showFilters = !this.showFilters;
        },
        async fetchData() {
            await axios
                .get('api/product/')
                .then(response => {
                    this.products = response.data;
                })
                .catch((err) => console.log("err", err));
            },
    },
    async mounted() {
        await this.fetchData();
        this.newArrivals = this.products.filter(product => product.categoryId === 16);
        this.men = this.products.filter(product => product.categoryId === 17);
        this.women = this.products.filter(product => product.categoryId === 18);
    },
}
</script>

<style>
#home .container {
    width: 1200px;
    margin: auto;
}

#home .row-title {
    display: flex;
    justify-content: space-between;
}

#home .row {
    display: flex;
}

#home h1 {
    font-family: 'Poppins', sans-serif;
}

.btn {
    box-shadow: O O 2em black;
    background-color: white;
    border: none;
    font-family: 'Montserrat', sans-serif;
    font-size: 1.2em;
}

.transparent {
    opacity: 0.5;
}
</style>