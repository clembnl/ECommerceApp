<template>
    <div id="home">
        <Filters />

        <!-- display products -->
        <div class="container">
            <div class="row">
                <h1>New Arrivals</h1>
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
        }
    },
    mounted() {
        //this.productSize = Math.min(6, this.products.length );
    }
}
</script>

<style>
#home .container {
    width: 1200px;
    margin: auto;
}

#home h1 {
    font-family: 'Poppins', sans-serif;
}
</style>