<template>
  <div class="container">
    <div class="row">
      <!-- display image-->
      <div class="image">
        <img :src="product.imageURL" />
      </div>
      <!-- display product details-->
      <div>
        <h4>{{ product.name }}</h4>
        <h6 >{{ category.categoryName }}</h6>
        <h6 >{{ product.price }}€</h6>
        <p>
          {{ product.description }}
        </p>
        <div class="d-flex flex-row justify-content-between">
          <div class="input-group">
            <div class="input-group-prepend">
              <span class="input-group-text">Quantity</span>
            </div>
            <input type="number" class="form-control" v-model="quantity" />
          </div>

          <div class="input-group">
            <button class="btn" id="add-to-cart-button" @click="addToCart">
              Add to Cart
            </button>
          </div>
        </div>
        <div class="features">
            <h5><strong>Features</strong></h5>
            <p>{{ product.description }}</p>
        </div>
        <button id="wishlist-button">
          {{ wishListString }}
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      product: {},
      category: {},
      quantity: 1,
      wishListString: "Add to wishlist",
      baseURL: "http://localhost:8090/"
    };
  },
  props: ["products", "categories"],
}
</script>