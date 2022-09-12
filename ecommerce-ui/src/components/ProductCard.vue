<template>
    <div class="card">
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
            <router-link :to=" { name: 'ShowDetails', params: { id: product.id } }" >
                <div class="card-title">
                    <h5 class="card-name">{{ product.name }}</h5>
                    <h5 class="card-price">{{ product.price }}€</h5>
                </div>
            </router-link>
            <!--
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

<style scoped>

.card {
    margin: 10px;
}

.card-body a {
    text-decoration: none;
}

.card-title {
    display: flex;
    justify-content: space-between;
}

.card-name {
    font-family: 'Montserrat', sans-serif;
    color: black;
    font-weight: normal;
    font-size: 1em;
}

.card-price {
    text-decoration: none;
    font-family: 'Poppins', sans-serif;
    font-weight: bold;
    color: burlywood;
    font-size: 1em;
}

</style>