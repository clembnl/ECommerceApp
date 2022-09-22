<template>
    <div id="wishlist">
        <h2>My wishlist</h2>
        <div>
            <div v-for="product of products" :key="product.id" class="wishlist-products">
                <ProductCard :product="product"> </ProductCard>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import ProductCard from "./ProductCard.vue";

export default {
    name: 'WishList',
    components: {
        ProductCard
    },
    data() {
        return {
            token: null,
            products: null,
        }
    },
    methods: {
        fetchWishList() {
            axios
                .get(`api/wishlist/${this.token}`)
                .then((res) => {
                    this.products = res.data;
                })
                .catch((err) => {
                    console.log("err", err);
                });
        },
    },
  mounted() {
    this.token = localStorage.getItem("token");
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
        width: 300px;
        margin: auto;
    }
</style>