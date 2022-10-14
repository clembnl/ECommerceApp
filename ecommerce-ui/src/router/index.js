import { createRouter, createWebHashHistory } from 'vue-router';
import Home from '../views/Home.vue';
import Admin from '../views/Admin.vue';
import Cart from '../views/Cart.vue';
import Checkout from '../views/Checkout.vue';
import ShowDetails from "../views/Product/ShowDetails";
import User from '../views/User';
import Success from '../views/Order/Success.vue';
import Failed from '../views/Order/Failed.vue';
import OrderHistory from '../views/Order/OrderHistory.vue';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    props: true
  },
  //admin home page
  {
    path: "/admin",
    name: "Admin",
    component: Admin,
  },
  //cart page
  {
    path: "/cart",
    name: "Cart",
    component: Cart
  },
  //checkout page
  {
    path: "/checkout",
    name: "Checkout",
    component: Checkout
  },
  //Show product details
  {
    path: "/product/show/:id",
    name: "ShowDetails",
    component: ShowDetails,
    props: true
  },
  //user page
  {
    path: "/user",
    name: "User",
    component: User
  },
  //Payment success page
  {
    path: "/payment/success",
    name: "Success",
    component: Success
  },
  //Payment failed page
  {
    path: "/payment/failed",
    name: "Failed",
    component: Failed
  },
  //Order history page
  {
    path: '/orders',
    name: 'OrderHistory',
    component: OrderHistory
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router