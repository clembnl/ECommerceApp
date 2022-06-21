<template>
    <div class="ckeckout">
        <div class="redirect">
            <h3>You will be redirected  to payment page</h3>
            <div class="alert">
                While making payment use card number 4242 4242 4242 4242 and enter random  cvv (3 digit)
            </div>
            <button class="btn" @click="goToCheckout()">
                Make Payment
            </button>
        </div>
    </div>
</template>

<script>
const axios = require('axios')

export default {
    name: 'Checkout',
    data() {
        return {
            stripeAPIToken: 'pk_test_51KDyZCHbpACDZqsEPbtd09DHObPDpZDbf7LouqB9BJdbIgAPyrE6g228JRgu9bkwRBkFT0neqn2YYqztG4OuWrfa00Ufy7YkAk',
            stripe: '',
            token: null,
            sessionId: null,
            checkoutBodyArray: [],
            baseURL: '',
        }
    },
    methods: {
        /*
            Includes Stripe.js dynamically
        */
        includeStripe( URL, callback ){
            let documentTag = document, tag = 'script',
                object = documentTag.createElement(tag),
                scriptTag = documentTag.getElementsByTagName(tag)[0];
            object.src = '//' + URL;
            if (callback) { object.addEventListener('load', function (e) { callback(null, e); }, false); }
            scriptTag.parentNode.insertBefore(object, scriptTag);
        },
        /*
            Configures Stripe by setting up the elements and
            creating the card element.
        */
        configureStripe(){
            /* global Stripe */
            this.stripe = Stripe( this.stripeAPIToken );
        },

        // get all cart Items for the user
        getAllCartItems() {
            axios
                .get(`${this.baseURL}cart/?token=${this.token}`)
                .then((response) => {
                    if (response.status === 200) {
                        let cartItems = response.data.cartItems
                        //for each cart item populate the checkoutBodyArray array
                        cartItems.forEach((item) => {
                            this.checkoutBodyArray.push({
                                productName: item.product.name,
                                quatity: item.quantity,
                                price: item.product.price,
                                productId: item.product.id
                            })
                        })
                    }
                })
                .catch((err) => {
                    console.log('err', err);
                });
        },
        goToCheckout() {
            axios
                .post(`${this.baseURL}order/create-checkout-session`,
                        this.checkoutBodyArray)
                .then((response) => {
                    localStorage.setItem("sessionId", response.data.sessionId);
                    return response.data;
                })
                .then((session) => {
                    return this.stripe.redirecttoCheckout({ sessionId: session.sessionId })
                });
        },
    },
    mounted() {
        this.token = localStorage.getItem('token');

        this.includeStripe('js.stripe.com/v3/', function(){
            this.configureStripe();
        }.bind(this));

        this.getAllCartItems();
    }
}
</script>