<template>
    <div class="card">
        <div class="card-img">
            <router-link :to=" { name: 'ShowDetails', params: { id: product.id } }" >
                <img 
                    class="card-img-top"
                    :src="product.imageURL"
                    width="350"
                    height="350"
                    alt="card image cap"
                />
            </router-link>
        </div>

        <div class="card-body">
            <router-link :to=" { name: 'ShowDetails', params: { id: product.id } }" >
                <div class="card-title">
                    <h5 class="card-name">{{ product.name }}</h5>
                    <h5 class="card-price">${{ product.price }}</h5>
                </div>
            </router-link>
            <div class="remove-from-wishlist" v-if="wishlist" @click="$emit('removeFromWishlist')">
                <h5 class="card-wishlist">Remove from Wishlist</h5>
            </div>
            <!--
            <router-link id="edit-product" :to="{ name: 'EditProduct', params: { id: product.id, product: product } }" v-show="$route.name == 'AdminProduct'">
                <button class="btn">Edit</button>
            </router-link>
            -->
        </div>
    </div>
</template>

<script>
export default {
    name: 'ProductCard',
    props: ['product', 'type'],
    data() {
        return {
            wishlist: this.type === 'wishlist',
        }
    }
}
</script>

<style scoped>

.card {
    margin: 10px;
    width: 350px;
}

.card-body a {
    text-decoration: none;
}

.card-title {
    display: flex;
    justify-content: space-between;
}

.card-name {
    font-family: 'Montserrat', sans-serif;
    color: black;
    font-weight: normal;
    font-size: 1em;
}

.card-price {
    text-decoration: none;
    font-family: 'Poppins', sans-serif;
    font-weight: bold;
    color: burlywood;
    font-size: 1em;
    margin-left: 5px;
}

.card-wishlist {
    font-family: 'Poppins', sans-serif;
    text-align: center;
    margin: 0;
    opacity: 0.5;
}

.card-wishlist:hover {
    color: red;
}

</style>