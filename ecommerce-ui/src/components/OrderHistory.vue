<template>
    <div class="container">
        <div class="row">
            <h2>My Orders</h2>
        </div>

        <div v-for="order in orderList" :key="order.id" class="row">
            <div class="order-item">

                <div class="card-img">
                    <img v-bind:src="order.imageURL" width="350" height="350">
                </div>

                <div class="order-item-product">
                    <div>
                        <h4 class="card-title">
                            Order No : {{order.id}}
                        </h4>
                        <p>Ordered on : {{order.orderdate}}</p>
                        <p>{{order.totalItems}} item<span v-if="order.totalItems > 1">s</span></p>
                        <div id="item-price">Total Cost : ${{order.totalCost}}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import authHeader from "../services/auth-header.js";

export default {
    name: 'OrderHistory',
    data() {
        return {
            token: null,
            orderList : [],
        }
    },
    methods: {
        // list of order histories
        async listOrders(){
            await axios.get('api/order/', { headers: authHeader() })
                .then((response) => {
                    if(response.status === 200){
                        // for each order populate orderList
                        response.data.forEach((order) => {
                            this.orderList.push({
                                id: order.id,
                                totalCost: order.totalPrice,
                                // get short date
                                orderdate: order.createdDate.substring(0,10),
                                // get image of the first orderItem of the order
                                imageURL: order.orderItems[0].product.imageURL,
                                // get total items
                                totalItems: order.orderItems.length
                            })
                        })
                    }
                })
                .catch((error)=>{
                    console.log(error)
                });
        },
        toggle(showNew, showMen, showWomen, showFiltered) {
            this.$router.push({name: "Home", params: {
                collection: showNew,
                men: showMen,
                women: showWomen,
                filter: showFiltered
            }});
        }
    },
    async mounted() {
        this.token = localStorage.getItem("token");
        if (this.token) {
            await this.listOrders();
        }
    },
};

</script>

<style scoped>
.container {
    width: 30%;
    margin: auto;
    padding: 1%;
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

.order-item {
    border: 2px solid burlywood;
    border-radius: 20px;
    padding: 15px;
}

.order-item-product {
    margin: 10px;
    width: 350px;
}

.card-img {
    box-shadow: 15px 15px 25px darkgray;
    padding: 10px;
    border-radius: 20px;
    width: 350px;
    margin: auto;
}

#item-price {
    text-decoration: none;
    font-family: 'Poppins', sans-serif;
    font-weight: bold;
    color: burlywood;
    font-size: 1em;
}
</style>