import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Admin from '../views/Admin.vue'
import Cart from '../views/Cart.vue'
import Checkout from '../views/Checkout.vue'
import ShowDetails from "../views/Product/ShowDetails"

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
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
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router