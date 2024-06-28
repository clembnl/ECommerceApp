<template>
    <Navbar @toggle="toggle" />

    <div id="account">
        <Cart id="cart"></Cart>
        <OrderHistory id="order"></OrderHistory>
    </div>
</template>

<script>
import Navbar from '../components/Navbar';
import Cart from '../components/Cart';
import OrderHistory from '../components/OrderHistory';

export default {
    name: 'User',
    components: {
        Navbar,
        Cart,
        OrderHistory
    },
    data() {
        return {
            token: null
        }
    },
    methods: {
        toggle(showNew, showMen, showWomen, showFiltered) {
            this.$router.push({name: "Home", params: {
                collection: showNew,
                men: showMen,
                women: showWomen,
                filter: showFiltered
            }})
        }
    },
    mounted() {
        this.token = localStorage.getItem("token");
    }
}
</script>

<style scoped>
#account {
    display: flex;
    justify-content: space-between;
    margin: 20px 40px;
}

#cart {
    width: 50%;
}

#order {
    width: 33%;
    height: min-content;
}

@media (max-width: 620px) {
    #account {
        flex-direction: column !important;
    }

    #cart {
        width: 80%;
        margin-bottom: 30px;
    }

    #order {
        width: 80%;
    }
}
</style>