import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Admin from '../views/Admin.vue'
import Cart from '../views/Cart.vue'
import Checkout from '../views/Checkout.vue'

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
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
