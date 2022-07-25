<template>
    <div class="card" v-show="product.categoryId === 16">
        <div class="card-img">
            <img 
                class="card-img-top"
                :src="product.imageURL"
                width="300"
                height="300"
                alt="card image cap"
            />
        </div>

        <div class="card-body">
            <!--
            <router-link :to=" { name: 'ShowDetails', params: { id: product.id } }">
                <h5 class="card-title">{{ product.name }}</h5>
            </router-link>
            <p class="card-text">{{ product.description.substring(0,65) }}...</p>
            <router-link id="edit-product" :to="{ name: 'EditProduct', params: { id: product.id, product: product } }" v-show="$route.name == 'AdminProduct'">
                <button class="btn">Edit</button>
            </router-link>
            -->
        </div>
    </div>
</template>

<script>
export default {
    name: 'ProductCard',
    props: ['product'],
}
</script>