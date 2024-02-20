<template>
    <Navbar @toggle="toggle" />

    <div id="orders">
        <h2>My Orders</h2>
        <div>
            <div v-for="order in orderList" :key="order.id" class="orders-list">
                <div class="orders-items">
                    <div class="card-img">
                        <img v-bind:src="order.imageURL" width="350" height="350">
                    </div>
                    <div>
                        <div>
                            <h5 class="card-title">
                                Order No : {{order.id}}
                            </h5>
                            <p>Ordered on : {{order.orderdate}}</p>
                            <p>{{order.totalItems}} item<span v-if="order.totalItems > 1">s</span></p>
                            <div id="item-price">Total Cost : ${{order.totalCost}}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import Navbar from '../../components/Navbar.vue';

export default {
    name: 'OrderHistory',
    components: {
        Navbar,
    },
    data() {
        return {
            token: null,
            orderList : [],
        }
    },
    methods: {
        // list of order histories
        async listOrders(){
            await axios.get(`api/order/?token=${this.token}`)
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
        await this.listOrders();
    },
};

</script>

<style scoped>
#orders {
    width: 30%;
    margin: auto;
    padding: 1%;
    /*border: solid darkgray 2px;*/
    border-radius: 20px;
    box-shadow: 15px 15px 20px darkgray;
    margin-top: 2%;
    margin-bottom: 50px;
}

#orders h2 {
    font-family: 'Montserrat', sans-serif;
    font-weight: 500;
    font-size: 2em;
    text-align: center;
    font-weight: normal;
    margin-bottom: 50px;
}

.orders-list {
    width: 400px;
    margin: auto;
}

.orders-items {
    margin-top: 10px;
    margin-bottom: 10px;
    border: 2px solid burlywood;
    border-radius: 20px;
    padding: 15px;
}

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

#orders h5 {
    font-family: 'Montserrat', sans-serif;
    color: black;
    font-weight: bold;
    font-size: 1.1em;
    text-align: center;
    width: 30%;
    margin: auto;
}

#orders p {
    text-decoration: none;
    font-family: 'Montserrat', sans-serif;
    font-size: 0.9em;
}

#item-price {
    text-decoration: none;
    font-family: 'Poppins', sans-serif;
    font-weight: bold;
    color: burlywood;
    font-size: 1em;
}
</style>