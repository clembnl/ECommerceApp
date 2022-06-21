<template>
    <div class="container">
        <div class="row">
            <h3>Cart</h3>
        </div>

        <!-- Loop over the cart items and display -->
        <div v-for="cartItem in cartItems" :key="cartItem.id" class="row">
            <div class="cart-item">
                <div class="cart-item-img">
                    <img :src="cartItem.product.imageURL" alt="cart product image" />
                </div>

                <div class="cart-item-product">
                    <div class="cart-item-product-block">
                        <h6>{{ cardItem.product.name }}</h6>
                        <p>{{ cardItem.product.price }} per unit</p>
                        <p>Quanity:{{ cardItem.quantity }}</p>
                    </div>
                    <p>Total:
                        <span> {{ cartItem.product.price * cardItem.quantity }}</span>
                    </p>
                    <br><a href="#" @click="deleteItem(cartItem.id)">Remove From Cart</a>
                </div>
            </div>
        </div>

        <!-- Display total price -->
        <div class="cart-total-price">
            <h5>Total : $ {{ totalCost }}</h5>
            <button :disabled="isDisabled()" type="button" class="btn" @click="checkout">Confirm Order</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            cartItems: [],
            totalCost: 0,
            token: null,
            baseURL: "",
        }
    },
    methods: {
        isDisabled() {
            if (this.cartItems.lenght === 0) {
                return true;
            }
            return false;
        },
        //fetch all items in cart
        listCartItems() {
            axios
                .get(`${this.baseURL}cart/?token=${this.token}`)
                .then((res) => {
                    const result = res.data;
                    this.cartItems = result.cartItems;
                    this.totalCost = result.totalCost;
                })
                .catch((err) => {
                    console.log('err', err);
                });
        },
        deleteItem(itemId) {
            axios
                .delete(`${this.baseURL}cart/delete/${itemId}/?token=${this.token}`)
                .then((response) => {
                    if (response.status === 200) {
                        this.listCartItems();
                    }
                })
                .catch((error) => {
                    console.log('error', error);
                });
        },
        checkout() {
            this.$router.push({ name: 'Checkout' });
        },
    },
    mounted() {
        this.token = localStorage.getItem('token');
        this.listCartItems();
    },
}
</script>