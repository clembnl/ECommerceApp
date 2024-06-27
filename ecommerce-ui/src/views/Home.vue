<template>
    <Navbar @toggle="toggle" />

    <div id="home">
        <div class="container container-filtered" v-if="showNew && !showFiltered">
            <div class="row-title">
                <h1 class="title">New Arrivals</h1>
                <button class="btn transparent" v-if="!showFilters" @click="filterClick()">Filters</button>
            </div>
            <Filters v-if="showFilters" @closeFilters="filterClick" @applyFilter="applyFilter" />
            <div class="row">
                <ProductCard v-for="item in this.newArrivals" :product="item" :key="item.id" />
            </div>
        </div>

        <div class="container container-filtered" v-if="showMen && !showFiltered">
            <div class="row-title">
                <h1 class="title">Men</h1>
                <button class="btn transparent" v-if="!showFilters" @click="filterClick()">Filters</button>
            </div>
            <Filters v-if="showFilters" @closeFilters="filterClick" @applyFilter="applyFilter" />
            <div class="row">
                <ProductCard v-for="item in this.mens" :product="item" :key="item.id" />
            </div>
        </div>

        <div class="container container-filtered" v-if="showWomen && !showFiltered">
            <div class="row-title">
                <h1 class="title">Women</h1>
                <button class="btn transparent" v-if="!showFilters" @click="filterClick()">Filters</button>
            </div>
            <Filters v-if="showFilters" @closeFilters="filterClick" @applyFilter="applyFilter" />
            <div class="row">
                <ProductCard v-for="item in this.womens" :product="item" :key="item.id" />
            </div>
        </div>

        <div class="container container-filtered" v-if="showFiltered">
            <div class="row-title">
                <h1 class="title">Search</h1>
                <button class="btn transparent" v-if="!showFilters" @click="filterClick()">Filters</button>
            </div>
            <Filters v-if="showFilters" @closeFilters="filterClick" @applyFilter="applyFilter" />
            <div class="row">
                <ProductCard v-for="item in this.filtered" :product="item" :key="item.id" />
            </div>
        </div>

        <div class="container" id="container-all">
            <div class="row-title">
                <h1 class="title">Products</h1>
                <button class="btn transparent" v-if="!showFilters" @click="filterClick()">Filters</button>
            </div>
            <Filters v-if="showFilters" @closeFilters="filterClick" @applyFilter="applyFilter" />
            <div class="row">
                <ProductCard v-for="item in this.products" :product="item" :key="item.id" />
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
    props: ['collection', 'men', 'women', 'filter'],
    data() {
        return {
            products: [],
            categories: [],
            newArrivals: [],
            mens: [],
            womens: [],
            filtered: [],
            showFilters: false,
            showNew: true,
            showMen: false,
            showWomen: false,
            showFiltered: false,
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
                .get('product/')
                .then(response => {
                    this.products = response.data;
                })
                .catch((err) => console.log("err", err));
            await axios
                .get('category/')
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
    beforeMount() {
        this.fetchData().then(() => {
            this.newArrivals = this.products.filter(product => product.categoryId === 10);
            this.mens = this.products.filter(product => product.categoryId === 11);
            this.womens = this.products.filter(product => product.categoryId === 12);
        })
        if (this.collection) {
            this.showNew = this.collection === 'true' ? true : false;
            this.showMen = this.men  === 'true' ? true : false
            this.showWomen = this.women  === 'true' ? true : false
            this.showFiltered = this.filter  === 'true' ? true : false
        }
    },
}
</script>

<style>
#home .container {
    width: 80%;
    margin: auto;
}

#container-all {
    visibility: hidden;
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
    justify-content: center;
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

@media (max-width: 1420px) {
    #home .container {
        width: 90%;
    }
}

@media (max-width: 1265px) {
    #home .container {
        width: 93%;
    }

    #home .row {
        justify-content: space-between;
    }
}

@media (max-width: 1225px) {
    #home .row {
        justify-content: center;
    }

    .card {
        width: 300px !important;
    }

    .card .card-img img {
        width: 300px !important;
        height: 300px !important;
    }
}

@media (max-width: 1064px) {
    .card {
        width: 280px !important;
    }

    .card .card-img img {
        width: 280px !important;
        height: 280px !important;
    }
}

@media (max-width: 1000px) {
    .filters {
        margin: 10px 100px 30px 100px !important;
    }

    .card {
        width: 260px !important;
    }

    .card .card-img img {
        width: 260px !important;
        height: 260px !important;
    }
}

@media (max-width: 935px) {
    .filters {
        margin: 10px 80px 30px 80px !important;
    }

    .card {
        width: 240px !important;
    }

    .card .card-img img {
        width: 240px !important;
        height: 240px !important;
    }
}

@media (max-width: 870px) {
    .filters {
        margin: 10px 25px 30px 25px !important;
    }

    .card {
        width: 210px !important;
    }

    .card .card-img img {
        width: 210px !important;
        height: 210px !important;
    }
}

@media (max-width: 760px) {
    .filters {
        margin: auto !important;
    }

    .filters ul {
        flex-direction: column;
    }

    .filters li {
        margin-top: 5px !important;
        margin-bottom: 5px !important;
        margin-left: auto !important;
        margin-right: auto !important;
    }
}

@media (max-width: 720px) {
    #navbar-menu {
        display: none !important;
    }

    .container-filtered {
        display: none !important;
    }

    #container-all {
        visibility: visible;
    }
}

@media (max-width: 512px) {
    .card {
        width: 300px !important;
    }

    .card .card-img img {
        width: 300px !important;
        height: 300px !important;
    }
}

@media (max-width: 425px) {
    #navbar-icon #user {
        margin-right: 5px !important;
    }

    #navbar-icon #cart {
        margin-left: 5px !important;
        margin-right: 0 !important;
    }
}

@media (max-width: 370px) {
    #navbar-icon #user {
        margin-left: 5px !important;
        margin-right: 0 !important;
    }

    #navbar-icon #cart {
        margin-left: 0 !important;
    }
}

@media (max-width: 360px) {
    nav {
        margin: 10px !important;
    }
}

@media (max-width: 330px) {
    nav {
        margin: 0 !important;
    }
}
</style>