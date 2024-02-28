<template>
    <div class="ckeckout">
        <div class="redirect">
            <h3>You will be redirected  to payment page</h3>
            <div class="alert">
                While making payment use card number 4242 4242 4242 4242 and enter random  cvv (3 digit)
            </div>
            <button class="no-btn" @click="goToCheckout()">
                Make Payment
            </button>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import authHeader from "../services/auth-header.js";

export default {
    name: 'Checkout',
    data() {
        return {
            stripeAPIToken: 'pk_test_51KDyZCHbpACDZqsEPbtd09DHObPDpZDbf7LouqB9BJdbIgAPyrE6g228JRgu9bkwRBkFT0neqn2YYqztG4OuWrfa00Ufy7YkAk',
            stripe: '',
            sessionId: null,
            checkoutBodyArray: [],
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
        async getAllCartItems() {
            await axios
                .get('api/cart/', { headers: authHeader() })
                .then((response) => {
                    if (response.status === 200) {
                        let cartItems = response.data.cartItems
                        //for each cart item populate the checkoutBodyArray array
                        cartItems.forEach((item) => {
                            this.checkoutBodyArray.push({
                                productName: item.product.name,
                                quantity: item.quantity,
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
        async goToCheckout() {
            await axios
                .post(`api/order/create-checkout-session`,
                        this.checkoutBodyArray, { headers: authHeader() })
                .then((response) => {
                    localStorage.setItem("sessionId", response.data.sessionId);
                    return response.data;
                })
                .then((session) => {
                    console.log(session);
                    return this.stripe.redirectToCheckout({ sessionId: session.sessionId })
                });
        },
    },
    async mounted() {
        this.includeStripe('js.stripe.com/v3/', function(){
            this.configureStripe();
        }.bind(this));

        await this.getAllCartItems();
    }
}
</script>

<style scoped>
.ckeckout .redirect {
    font-family: 'Montserrat', sans-serif;
    font-size: 1em;
}
.ckeckout .redirect h3 {
    font-family: 'Poppins', sans-serif;
    font-size: 1.2em;
}

.ckeckout .redirect {
    font-family: 'Montserrat', sans-serif;
    font-size: 1em;
}

.no-btn {
        margin-top: 20px;
        margin-bottom: 10px;
        margin-left: 0;
        color: black;
        border: 1px solid black;
        background-color: white;
        font-family: 'Montserrat', sans-serif;
        font-weight: 500;
        font-size: 1.2em;
        padding: 5px 12px 5px 12px;
        border-radius: 25px;
    }
</style>