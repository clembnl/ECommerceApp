import { createRouter, createWebHashHistory } from 'vue-router';
import Home from '../views/Home.vue';
import Admin from '../views/Admin.vue';
import Account from '../views/Account.vue';
import Checkout from '../views/Checkout.vue';
import ShowDetails from "../views/Product/ShowDetails";
import User from '../views/User';
import Success from '../views/Order/Success.vue';
import Failed from '../views/Order/Failed.vue';
import Products from '../views/Product/Products.vue';
import AddProduct from '../views/Product/AddProduct.vue';

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
  //admin products page
  {
    path: "/admin/products",
    name: "AdminProducts",
    component: Products,
    props: true
  },
  //admin add product
  {
    path: "/admin/product/add",
    name: "AddProduct",
    component: AddProduct,
    props: true
  },
  //cart page
  {
    path: "/account",
    name: "Account",
    component: Account
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
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router