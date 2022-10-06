<template>
    <Navbar @toggle="toggle" />

    <div id="home">
        <div class="container" v-if="showNew && !showFiltered">
            <div class="row-title">
                <h1 class="title">New Arrivals</h1>
                <button class="btn transparent" v-if="!showFilters" @click="filterClick()">Filters</button>
            </div>
            <Filters v-if="showFilters" @closeFilters="filterClick" @applyFilter="applyFilter" />
            <div class="row">
                <ProductCard v-for="item in this.newArrivals" :product="item" :key="item.id" />
            </div>
        </div>

        <div class="container" v-if="showMen && !showFiltered">
            <div class="row-title">
                <h1 class="title">Men</h1>
                <button class="btn transparent" v-if="!showFilters" @click="filterClick()">Filters</button>
            </div>
            <Filters v-if="showFilters" @closeFilters="filterClick" @applyFilter="applyFilter" />
            <div class="row">
                <ProductCard v-for="item in this.men" :product="item" :key="item.id" />
            </div>
        </div>

        <div class="container" v-if="showWomen && !showFiltered">
            <div class="row-title">
                <h1 class="title">Women</h1>
                <button class="btn transparent" v-if="!showFilters" @click="filterClick()">Filters</button>
            </div>
            <Filters v-if="showFilters" @closeFilters="filterClick" @applyFilter="applyFilter" />
            <div class="row">
                <ProductCard v-for="item in this.women" :product="item" :key="item.id" />
            </div>
        </div>

        <div class="container" v-if="showFiltered">
            <div class="row-title">
                <h1 class="title">Search</h1>
                <button class="btn transparent" v-if="!showFilters" @click="filterClick()">Filters</button>
            </div>
            <Filters v-if="showFilters" @closeFilters="filterClick" @applyFilter="applyFilter" />
            <div class="row">
                <ProductCard v-for="item in this.filtered" :product="item" :key="item.id" />
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
            categories: [],
            newArrivals: [],
            men: [],
            women: [],
            filtered: [],
            showFilters: false,
            showNew: true,
            showMen: false,
            showWomen: false,
            showFiltered: false
        }
    },
    methods: {
        toggle(showNew, showMen, showWomen, showFiltered) {
            this.showNew = showNew;
            this.showMen = showMen;
            this.showWomen = showWomen;
            this.showFiltered = showFiltered;
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
            await axios
                .get('api/category/')
                .then(response => {
                    this.categories = response.data;
                })
                .catch((err) => console.log("err", err));
        },
        applyFilter(filters) {
            this.filtered = this.products;
            if (filters.category !== '') {
                const cat = this.categories.find(category => category.categoryName === filters.category);
                this.filtered = this.filtered.filter(product => product.categoryId === cat.id);
            }
            if (filters.range.length === 2) {
                this.filtered = this.filtered.filter(product => (product.price >= filters.range[0] && product.price <= filters.range[1]));
            }
            if (filters.brand !== '') {
                this.filtered = this.filtered.filter(product => product.brand === filters.brand);
            }
            if (filters.sort !== '') {
                if (filters.sort === 'price') {
                    this.filtered = this.filtered.sort((a, b) => {
                        let comparison = 0;
                        if (a.price > b.price) {
                            comparison = 1;
                        }
                        else if (a.price < b.price) {
                            comparison = -1;
                        }
                        return comparison;
                    })
                }
                if (filters.sort === 'brand') {
                    this.filtered = this.filtered.sort((a, b) => {
                        let comparison = 0;
                        if (a.brand > b.brand) {
                            comparison = 1;
                        }
                        else if (a.brand < b.brand) {
                            comparison = -1;
                        }
                        return comparison;
                    })
                }
            }
            this.showFiltered = true;
        }
    },
    mounted() {
        this.fetchData().then(() => {
            this.newArrivals = this.products.filter(product => product.categoryId === 16);
            this.men = this.products.filter(product => product.categoryId === 17);
            this.women = this.products.filter(product => product.categoryId === 18);
        })
    },
}
</script>

<style>
#home .container {
    width: 80%;
    margin: auto;
}

#home .row-title {
    display: flex;
    justify-content: space-between;
    margin-left: 8px;
    margin-right: 6px;
}

#home .row-title .title {
    order: 0;
}

#home .row {
    display: flex;
    flex-wrap: wrap;
    /*justify-content: space-between;*/
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