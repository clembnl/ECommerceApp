<template>
  <Navbar @toggleMen="toggleMen"
          @toggleWomen="toggleWomen"
          @toggleNew="toggleNew" />

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
        <div class="display-bottom">
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
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from '../../components/Navbar.vue'
import swal from "sweetalert";
import axios from "axios";
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
    };
  },
  methods: {
    toggleNew() {
      this.$router.push({name: "Home", params: {
        showNew: true,
        showMen: false,
        showWomen: false
      }})
    },
    toggleMen() {
      this.$router.push({name: "Home", params: {
        showNew: false,
        showMen: true,
        showWomen: false
      }})
    },
    toggleWomen() {
      this.$router.push({name: "Home", params: {
        showNew: false,
        showMen: false,
        showWomen: true
      }})
    },
    addToWishlist() {
      if (!this.token) {
        // user is not logged in
        // show some error
        swal({
          text: "please login to add item in wishlist",
          icon: "error",
        });
        return;
      }
      // add item to wishlist
      axios
        .post(`api/wishlist/add?token=${this.token}`, {
          id: this.product.id,
        })
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
        .post(`api/cart/add?token=${this.token}`, {
          productId: this.id,
          quantity: this.quantity,
        })
        .then((res) => {
          if (res.status == 201) {
            swal({
              text: "Product added in cart",
              icon: "success",
            });
          }
        })
        .catch((err) => console.log("err", err));
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
</style>