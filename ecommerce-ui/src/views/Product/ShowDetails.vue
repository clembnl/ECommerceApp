<template>
  <Navbar @toggle="toggle" />

  <div class="container">
    <div class="row">
      <!-- display image-->
      <div class="image">
        <img :src="product.imageURL" />
      </div>
      <!-- display product details-->
      <div class="display-info">
        <div class="display-top">
          <h2>{{ product.name }}</h2>
          <!--<h3 class="font-italic">{{ category.categoryName }}</h3>-->
          <p>
            {{ product.description }}
          </p>
          <h3>{{ product.price }}€</h3>
        </div>
        <div class="display-bottom" v-if="role !== 'ROLE_ADMIN'">
          <div>
            <div class="input-group">
              <div>
                <span class="input-group-text">Quantity :</span>
              </div>
              <input type="number" min="0" max="5" class="form-control" v-model="quantity" required/>
            </div>

            <div class="input-group">
              <button id="add-to-cart-button" @click="addToCart">
                Add to Cart
              </button>
            </div>
          </div>
          <button id="wishlist-button" @click="addToWishlist()">
            {{ wishListString }}
          </button>
        </div>
        <div class="display-edit" v-if="role === 'ROLE_ADMIN'">
          <form @submit="edit" class="edit-form">
            <div class="form-group">
              <label>Brand : </label>
              <input v-model="brand" type="texte" class="form-control" />
            </div>
            <div class="form-group">
              <label>Category : </label>
              <select v-model="categoryId" type="number" class="form-control">
                <option value=16>New Collection</option>
                <option value=17>Men</option>
                <option value=18>Women</option>
                <option value=0>Other</option>
              </select>
            </div>
            <div class="form-group">
                <label>Product Name : </label>
                <input v-model="name" type="text" class="form-control" />
            </div>
            <div class="form-group">
              <label>Price : </label>
              <input type="number" v-model="price" class="form-control" />
            </div>
            <div class="form-group">
              <label>Description : </label>
              <input type="text" v-model="description" class="form-control" />
            </div>
            <div class="form-group">
              <label>Image URL : </label>
              <input v-model="imageURL" type="text" class="form-control" />
            </div>
            <button class="btn">Edit Product</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from '../../components/Navbar.vue'
import swal from "sweetalert";
import axios from "axios";
import authHeader from "../../services/auth-header.js";

export default {
  name: 'ShowDetails',
  components: {
    Navbar
  },
  data() {
    return {
      id: null,
      product: {
        imageURL: '',
        name: '',
        price: 0,
        description: '',
        categoryId: 0,
      },
      quantity: 1,
      wishListString: "Add to wishlist",
      role: this.$route.params.role
    };
  },
  methods: {
    toggle(showNew, showMen, showWomen, showFiltered) {
      this.$router.push({name: "Home", params: {
          collection: showNew,
          men: showMen,
          women: showWomen,
          filter: showFiltered
      }})
    },
    addToWishlist() {
      if (!this.token) {
        // user is not logged in
        // show some error
        swal({
          text: "please login to add item to wishlist",
          icon: "error",
        });
        return;
      }

      if (this.wishListString !== "Added to Wishlist") {
        // add item to wishlist
        axios
          .post('api/wishlist/add', {
            id: this.product.id,
          }, { headers: authHeader() })
          .then((res) => {
            if (res.status === 201) {
              this.wishListString = "Added to Wishlist";
              swal({
                text: "Added to Wishlist",
                icon: "success",
              });
            }
          })
          .catch((err) => {
            console.log("err", err);
          });
      }
    },
    addToCart() {
      if (!this.token) {
        // user is not logged in
        // show some error
        swal({
          text: "please login to add item in cart",
          icon: "error",
        });
        return;
      }
      // add to cart
      axios
        .post('api/cart/add', {
          productId: this.id,
          quantity: this.quantity,
        }, { headers: authHeader() })
        .then((res) => {
          if (res.status == 201) {
            swal({
              text: "Product added in cart",
              icon: "success",
            });
          }
        })
        .catch((err) => console.log("err", err));
    },
    edit() {
      if (this.role === 'ROLE_ADMIN') {
        const product = {
          name: this.name,
          imageURL: this.imageURL,
          price: this.price,
          description: this.description,
          categoryId: this.categoryId,
          brand: this.brand
        };
        console.log(this.imageURL, product.imageURL);
        axios
          .put(`api/product/update/${this.product.id}`, product, { headers: authHeader() })
          .then(() => {
              swal({
                  text: "Product Updated Sucessfully",
                  icon: "success",
              });
          })
          .catch((err) => console.log("err", err));
      }
    },
    getRole() {
      if (this.token) {
          axios
          .get('/api/user/role', { headers: authHeader() })
          .then((res) => {
              this.role = res.data.role
          })
          .catch((err) => console.log("err", err));
      }
    }
  },
  mounted() {
    this.id = this.$route.params.id;
    axios
      .get(`api/product/${this.id}`, {productID: this.id})
      .then((res) => {
        this.product = res.data;
      })
      .catch((err) => console.log("err", err));
    /*
    this.category = this.categories.find(
      (category) => category.id == this.product.categoryId
    );
    */
    this.token = localStorage.getItem("token");
    /*
    if (!this.role) {
      this.getRole();
    }
    */
    if (this.role !== 'ROLE_ADMIN') {
      axios
        .get('api/wishlist/', { headers: authHeader() })
        .then((res) => {
          for (const wishlistProduct of res.data) {
              if (wishlistProduct.product.id == this.product.id) {
                this.wishListString = "Added to Wishlist";
              }
            }
        });
    }
  },
}
</script>

<style scoped>
.container {
  width: 1200px;
  margin: auto;
}
.row {
  margin-top: 20px;
  display: flex;
  justify-content: center;
  align-items: flex-start;
}

.display-info {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 500px;
}

img {
  width: 500px;
  height: 500px;
  margin-right: 80px;
}

h2 {
  font-size: 2em;
  font-family: 'Poppins', sans-serif;
  margin-top: 0;
}

h3 {
  font-size: 1.3em;
  font-family: 'Montserrat', sans-serif;
}

p {
  font-size: 1.2em;
  font-family: 'Montserrat', sans-serif;
}

.input-group {
  display: flex;

}
.input-group-text {
  font-family: 'Montserrat', sans-serif;
  font-size: 1.2em;
  font-style: italic;
  margin-right: 20px;
}

.form-control {
  width: 30px;
}

#add-to-cart-button {
  margin-top: 20px;
  padding: 10px;
  font-family: 'Montserrat', sans-serif;
  font-size: 1em;
  border: 2px solid black;
  border-radius: 25px;
  background-color: white;
}

#wishlist-button {
  margin-top: 20px;
  padding: 10px;
  font-family: 'Montserrat', sans-serif;
  font-size: 1em;
  border: 2px solid lightcoral;
  border-radius: 25px;
  background-color: white;
}

.edit-form label {
    font-family: 'Montserrat', sans-serif;
    margin-right: 15px;
    font-style: italic;
}

.edit-form .form-group {
    margin-top: 15px;
    margin-bottom: 15px;
}

.edit-form .btn {
    margin-top: 20px;
    margin-bottom: 10px;
    color: white;
    background-color: black;
    font-family: 'Montserrat', sans-serif;
    font-weight: 500;
    padding: 5px 12px 5px 12px;
    border-radius: 25px;
    width: 100%;
}

.edit-form .form-control {
    border-radius: 20px;
    border: solid grey 1px;
    font-family: 'Montserrat', sans-serif;
    padding: 5px;
    width: 100%;
    margin-top: 5px;
}
</style>