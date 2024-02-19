<template>
    <Navbar/>

    <!-- v-if="role === 'admin'" -->
    <div id="admin-panel-add">
        <div>
            <h1>Add product</h1>
        </div>
        <div class="container">
            <div class="display-edit" v-if="role === 'admin'">
                <form @submit="add" class="add-form">
                    <div class="form-group">
                    <label>Brand : </label>
                    <input v-model="brand" type="texte" class="form-control" required/>
                    </div>
                    <div class="form-group">
                    <label>Category : </label>
                    <select v-model="categoryId" class="form-control" required>
                        <option value=16>New Collection</option>
                        <option value=17>Men</option>
                        <option value=18>Women</option>
                        <option value=0>Other</option>
                    </select>
                    </div>
                    <div class="form-group">
                        <label>Product Name : </label>
                        <input v-model="name" type="text" class="form-control" required/>
                    </div>
                    <div class="form-group">
                    <label>Price : </label>
                    <input type="number" v-model="price" class="form-control" required/>
                    </div>
                    <div class="form-group">
                    <label>Description : </label>
                    <input type="text" v-model="description" class="form-control" required/>
                    </div>
                    <div class="form-group">
                    <label>Image URL : </label>
                    <input v-model="imageURL" type="text" class="form-control" required/>
                    </div>
                    <button class="btn">Add Product</button>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import router from '@/router';
import Navbar from '../../components/Navbar.vue';
import axios from 'axios';
import swal from "sweetalert";

export default {
    name: 'AddProduct',
    components: {
        Navbar,
    },
    data() {
        return {
            product: {
                imageURL: '',
                name: '',
                price: 0,
                description: '',
                categoryId: 0,
            },
            role: this.$route.params.role
        }
    },
    methods: {
        add() {
            if (this.role === 'admin') {
                const product = {
                    name: this.name,
                    imageURL: this.imageURL,
                    price: this.price,
                    description: this.description,
                    categoryId: this.categoryId,
                    brand: this.brand
                }
                console.log(this.imageURL, product.imageURL);
                axios
                    .post('api/product/add', product)
                    .then(() => {
                        router.push({ name: 'AdminProducts'});
                        swal({
                            text: "User signup successful",
                            icon: "success",
                        });
                    })
                    .catch((err) => console.log("err", err));
            }
        }
    },
}
</script>

<style scoped>
#admin-panel-add .container {
    width: 80%;
    margin: auto;
}

#admin-panel-add h1 {
    display: flex;
    justify-content: center;
    font-family: 'Poppins', sans-serif;
}

.add-form label {
    font-family: 'Montserrat', sans-serif;
    margin-right: 15px;
    font-style: italic;
}

.add-form .form-group {
    margin-top: 15px;
    margin-bottom: 15px;
}

.add-form .btn {
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

.add-form .form-control {
    border-radius: 20px;
    border: solid grey 1px;
    font-family: 'Montserrat', sans-serif;
    padding: 5px;
    width: 100%;
    margin-top: 5px;
}
</style>