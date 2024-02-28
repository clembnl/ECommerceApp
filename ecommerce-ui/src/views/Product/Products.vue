<template>
    <Navbar/>

    <!-- v-if="role === 'admin'" -->
    <div id="admin-panel" v-if="role === 'ROLE_ADMIN'">
        <div class="container">
            <div class="row-title">
                <h1>All Products</h1>
            </div>
            <div class="row">
                <ProductCard v-for="item in this.products" :product="item" :key="item.id" :type="role" />
            </div>
        </div>
    </div>
</template>

<script>
import Navbar from '../../components/Navbar.vue';
import ProductCard from '../../components/ProductCard.vue';
import axios from 'axios';
import authHeader from "../../services/auth-header.js";

export default {
    name: 'AdminProducts',
    components: {
        ProductCard,
        Navbar,
    },
    data() {
        return {
            products: [],
            categories: [],
            token: null,
            role: this.$route.params.role
        }
    },
    methods: {
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
        async getRole() {
            if (this.token) {
                await axios
                    .get('user/role', { headers: authHeader() })
                    .then((res) => {
                        this.role = res.data.message
                    })
                    .catch((err) => console.log("err", err));
            }
        }
    },
    async mounted() {
        this.token = localStorage.getItem("token");
        await this.fetchData();
        await this.getRole();
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