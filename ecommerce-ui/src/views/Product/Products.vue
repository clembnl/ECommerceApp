<template>
    <Navbar/>

    <!-- v-if="role === 'admin'" -->
    <div id="admin-panel">
        <div class="container">
            <div class="row-title">
                <router-link :to=" { name: 'AddProduct', params: { role: 'admin' } }" >
                    <button class="btn">Add Product</button>
                </router-link>
                <h1>All Products</h1>
            </div>
            <div class="row">
                <ProductCard v-for="item in this.products" :product="item" :key="item.id" :type="'admin'" />
            </div>
        </div>
    </div>
</template>

<script>
import Navbar from '../../components/Navbar.vue'
import ProductCard from '../../components/ProductCard.vue'
import axios from 'axios'

export default {
    name: 'AdminProducts',
    components: {
        ProductCard,
        Navbar,
    },
    props: ['role'],
    data() {
        return {
            products: [],
            categories: [],
        }
    },
    methods: {
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
    },
    async beforeMount() {
        await this.fetchData();
    }
}
</script>

<style scoped>
#admin-panel .container {
    width: 80%;
    margin: auto;
}

#admin-panel .row-title {
    margin-left: 8px;
    margin-right: 6px;
}

#admin-panel .btn {
    border: 1px solid black;
    margin-top: 20px;
    margin-bottom: 10px;
    color: black;
    background-color: white;
    font-family: 'Montserrat', sans-serif;
    font-weight: 500;
    text-align: start;
    padding: 5px 12px 5px 12px;
    border-radius: 25px;
}

#admin-panel h1 {
    display: flex;
    justify-content: center;
    font-family: 'Poppins', sans-serif;
}

#admin-panel .row {
    display: flex;
    flex-wrap: wrap;
}
</style>