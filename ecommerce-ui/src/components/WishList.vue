<template>
    <div id="wishlist">
        <h2>My wishlist</h2>
        <div>
            <div v-for="product of products" :key="product.id" class="wishlist-products">
                <ProductCard :product="product.product" id="wishlist-item" :type="'wishlist'" @removeFromWishlist="removeFromWishlist(product.id)"> </ProductCard>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import ProductCard from "./ProductCard.vue";
import authHeader from "../services/auth-header.js";

export default {
    name: 'WishList',
    components: {
        ProductCard
    },
    data() {
        return {
            products: null
        }
    },
    methods: {
        fetchWishList() {
            axios
                .get('api/wishlist/', {headers: authHeader()})
                .then((res) => {
                    this.products = res.data;
                })
                .catch((err) => {
                    console.log("err", err);
                });
        },
        removeFromWishlist(itemId) {
            axios
                .delete(`api/wishlist/delete/${itemId}`, {headers: authHeader()})
                .then(async (response) => {
                    if (response.status === 200) {
                        this.fetchWishList();
                    }
                })
                .catch((error) => {
                    console.log('error', error);
                });
        }
    },
  mounted() {
    this.fetchWishList();
  }
}
</script>

<style scoped>
    #wishlist h2 {
        font-family: 'Montserrat', sans-serif;
        font-weight: 500;
        font-size: 2em;
        text-align: center;
        font-weight: normal;
        margin-bottom: 50px;
    }

    .wishlist-products {
        width: 400px;
        margin: auto;
    }

    #wishlist-item {
        margin-top: 10px;
        margin-bottom: 10px;
        border: 1px solid burlywood;
        border-radius: 20px;
        padding: 15px;
    }
</style>