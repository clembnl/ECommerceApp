<template>
    <div id="signin">
        <h2>Sign in to Sneakers</h2>
        <form @submit="signin" class="signin-form">
            <div class="form-group">
                <label>Your username :</label>
                <input v-model="username" type="text" class="form-control" required/>
            </div>
            <div class="form-group">
                <label>Your password :</label>
                <input v-model="password" type="password" class="form-control" required/>
            </div>
            <button class="btn">Sign In</button>
        </form>
        <button class="no-btn" @click="toggleSignUp">Sign Up</button>
    </div>
</template>

<script>
import axios from "axios";
import swal from "sweetalert";

export default {
    name: 'SignIn',
    data() {
        return {
            username: null,
            password: null
        }
    },
    methods: {
        async signin(e) {
            e.preventDefault();
            const body = {
                username: this.username,
                password: this.password,
            };
            await axios
                .post('user/signin', body)
                .then((res) => {
                    if (res.data.role === 'ROLE_ADMIN' && this.$route.name === 'Admin') {
                        localStorage.setItem("token", res.data.token);
                        this.$emit('signin', res.data.role);
                        swal({
                            text: "Login successful",
                            icon: "success",
                        });
                    }
                    else {
                        this.$router.replace("/");
                        console.log(res.data.token);
                        localStorage.setItem("token", res.data.token);
                        swal({
                            text: "Login successful",
                            icon: "success",
                        });
                    }

                })
                .catch((err) => {
                    console.log("err", err);
                    // show some error
                    swal({
                        text: "Invalid Credentials",
                        icon: "error",
                    });
                });
        },
        toggleSignUp() {
            this.$emit('toggleSignUp');
        }
    }
}
</script>

<style scoped>
    #signin h2 {
        font-family: 'Montserrat', sans-serif;
        font-weight: 500;
        font-size: 2em;
        text-align: center;
        font-weight: normal;
        margin-bottom: 50px;
    }

    #signin label {
        font-family: 'Montserrat', sans-serif;
        margin-right: 15px;
        font-style: italic;
    }

    .form-group {
        margin-top: 15px;
        margin-bottom: 15px;
    }

    .signin-form .btn {
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

    #signin .no-btn {
        margin-top: 10px;
        margin-bottom: 10px;
        color: black;
        border: none;
        background-color: white;
        font-family: 'Montserrat', sans-serif;
        font-weight: 500;
        padding: 5px 12px 5px 12px;
        border-radius: 25px;
        width: 100%;
    }

    .form-control {
        border-radius: 20px;
        border: solid grey 1px;
        font-family: 'Montserrat', sans-serif;
        padding: 5px;
    }
</style>