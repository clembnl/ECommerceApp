<template>
    <Navbar @toggle="toggle" />

    <div class="container">
        <div class="row">
            <h2>My cart</h2>
        </div>

        <!-- Loop over the cart items and display -->
        <div v-for="cartItem in cartItems" :key="cartItem.id" class="row">
            <div class="cart-item">
                <div class="cart-item-img">
                    <img :src="cartItem.product.imageURL" alt="cart product image" width="350" height="350" />
                </div>

                <div class="cart-item-product">
                    <div class="cart-item-product-block">
                        <h4>{{ cartItem.product.name }}</h4>
                        <p>Quantity: {{ cartItem.quantity }}</p>
                    </div>
                    <p>Total:
                        <span>${{ cartItem.product.price * cartItem.quantity }}</span>
                    </p>
                    <a href="#" @click="deleteItem(cartItem.id)">Remove From Cart</a>
                </div>
            </div>
        </div>

        <!-- Display total price -->
        <div class="cart-total-price">
            <h3>Total : ${{ totalCost }}</h3>
            <button :disabled="isDisabled()" type="button" class="btn" @click="checkout">Confirm Order</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import Navbar from '../components/Navbar.vue';

export default {
    name: 'Cart',
    components: {
        Navbar
    },
    data() {
        return {
            cartItems: [],
            totalCost: 0,
            token: null,
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
        async listCartItems() {
            await axios
                .get(`api/cart/?token=${this.token}`)
                .then((res) => {
                    const result = res.data;
                    this.cartItems = result.cartItems;
                    this.totalCost = result.totalCost;
                })
                .catch((err) => {
                    console.log('err', err);
                });
        },
        async deleteItem(itemId) {
            await axios
                .delete(`api/cart/delete/${itemId}/?token=${this.token}`)
                .then(async (response) => {
                    if (response.status === 200) {
                        await this.listCartItems();
                    }
                })
                .catch((error) => {
                    console.log('error', error);
                });
        },
        checkout() {
            this.$router.push({ name: 'Checkout' });
        },
        toggle(showNew, showMen, showWomen, showFiltered) {
            this.$router.push({name: "Home", params: {
                collection: showNew,
                men: showMen,
                women: showWomen,
                filter: showFiltered
            }})
        }
    },
    async mounted() {
        this.token = localStorage.getItem('token');
        await this.listCartItems();
    }
}
</script>

<style scoped>
.container {
    width: 30%;
    margin: auto;
    padding: 1%;
    /*border: solid darkgray 2px;*/
    border-radius: 20px;
    box-shadow: 15px 15px 20px darkgray;
    margin-top: 2%;
    margin-bottom: 2%;
}

.container .row {
    margin-bottom: 35px;
}

.container h2 {
    font-family: 'Montserrat', sans-serif;
    font-weight: 500;
    font-size: 2em;
    text-align: center;
    font-weight: normal;
}

.container h3 {
    font-family: 'Poppins', sans-serif;
    font-size: 1.5em;
    text-align: left;
}

.container h4 {
    font-family: 'Poppins', sans-serif;
    font-size: 1.2em;
    text-align: center;
}

.container p {
    font-family: 'Montserrat', sans-serif;
    font-size: 1em;
    font-weight: normal;
    margin-top: 10px;
    margin-bottom: 7px;
}

.container a {
    text-decoration: none;
    font-family: 'Poppins', sans-serif;
    color: black;
    opacity: 0.7;
    font-size: 0.9em;
    font-weight: normal;
}

.container a:hover {
    color: red;
}

.container .btn {
        margin-top: 0;
        margin-bottom: 10px;
        color: white;
        background-color: black;
        font-family: 'Montserrat', sans-serif;
        font-weight: 500;
        padding: 5px 12px 5px 12px;
        border-radius: 25px;
        width: 100%;
}

.cart-item {
    border: 2px solid burlywood;
    border-radius: 20px;
    padding: 15px;
}

.cart-item-img {
    box-shadow: 15px 15px 25px darkgray;
    padding: 10px;
    border-radius: 20px;
    width: 350px;
    margin: auto;
}
</style>