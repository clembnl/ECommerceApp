<template>
    <div id="home">
        <Filters v-if="showFilters" @closeFilters="filterClick"/>

        <!-- display products -->
        <div class="container">
            <div class="row">
                <h1>New Arrivals</h1>
                <button class="btn transparent" v-if="!showFilters" @click="filterClick()">Filters</button>
            </div>
            <div class="row">
                <div v-for="index in this.productSize" :key="index">
                    <ProductCard :product="products[index-1]" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Filters from '../components/Filters.vue'
import ProductCard from '../components/ProductCard.vue'

export default {
    name: 'Home',
    components: {
        Filters,
        ProductCard,
    },
    props: ['products'],
    data() {
        return {
            productSize: 0,
            showFilters: false,
        }
    },
    methods: {
        filterClick() {
            this.showFilters = !this.showFilters;
        },
    },
    mounted() {
        this.productSize = Math.min(6, this.products.length );
        console.log("products length:", this.productSize);
    }
}
</script>

<style>
#home .container {
    width: 1200px;
    margin: auto;
}

#home .row {
    display: flex;
    justify-content: space-between;
}

#home h1 {
    font-family: 'Poppins', sans-serif;
}

.btn {
    box-shadow: O O 2em black;
    background-color: white;
    border: none;
    font-family: 'Montserrat', sans-serif;
    font-size: 1em;
}

.transparent {
    opacity: 0.5;
}
</style>