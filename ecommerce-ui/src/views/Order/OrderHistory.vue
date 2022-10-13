<template>
    <div class="container">
        <h4>Your Orders</h4>
        <div v-for="order in orderList" :key="order.id">
            <div>
                <img v-bind:src="order.imageURL" width="350" height="350">
            </div>
            <div>
                <div>
                    <h6 class="card-title">
                        Order No : {{order.id}}
                    </h6>
                    <p>{{order.totalItems}} item<span v-if="order.totalItems > 1">s</span></p>
                    <p id="item-price">Total Cost : ${{order.totalCost}}</p>
                    <p id="item-total-price">Ordered on : {{order.orderdate}}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
const axios = require('axios')
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
    },
    async mounted() {
        this.token = localStorage.getItem("token");
        await this.listOrders();
    },
};

</script>

<style scoped>

</style>