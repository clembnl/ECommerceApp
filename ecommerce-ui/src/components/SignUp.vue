<template>
    <div id="signup">
        <h2>Sign up to Sneakers</h2>
        <form @submit="signup" class="signup-form">
            <div class="form-group">
                <label>Your email :</label>
                <input v-model="email" type="email" class="form-control" required/>
            </div>
            <div class="form-row">
                <div class="col">
                    <div class="form-group">
                    <label>Your username :</label>
                    <input
                        type="text"
                        v-model="username"
                        class="form-control"
                        required
                    />
                    </div>
                </div>
            </div>
                <div class="form-group">
                <label>Your password :</label>
                <input v-model="password" type="password" class="form-control" />
            </div>
            <!-- confirm password -->
            <div class="form-group">
                <label for="Password">Please confirm password :</label>
                <input
                    type="password"
                    v-model="confirmPassword"
                    class="form-control"
                    required
                />
            </div>
            <button class="btn">Sign Up</button>
        </form>
        <button class="no-btn" @click="toggleSignIn">Sign In</button>
    </div>
</template>

<script>
import axios from "axios";
import swal from "sweetalert";

export default {
    name: 'SignUp',
    data() {
        return {
            email: null,
            username: null,
            password: null,
            confirmPassword: null
        }
    },
    methods: {
        async signup(e) {
            e.preventDefault();
            if (/^[^@]+@\w+(\.\w+)+\w$/.test(this.email)) {
                if (this.password === this.confirmPassword) {
                    // call signup api
                    const user = {
                    email: this.email,
                    username: this.firstName,
                    password: this.password,
                    };
                    await axios
                        .post('api/user/signup', user)
                        .then(async () => {
                            await axios
                                    .post('api/user/signin', {email: this.email, password: this.password})
                                    .then((res) => {
                                        this.$router.replace("/");
                                        localStorage.setItem("token", JSON.stringify(res.data.token));
                                        swal({
                                            text: "User signup successful",
                                            icon: "success",
                                        });
                                    })
                                    .catch((err) => console.log("err", err));
                        })
                        .catch((err) => console.log("err", err));
                } else {
                    // show some error
                    swal({
                        text: "passwords don't match",
                        icon: "error",
                    });
                }
            }
            else {
                // show some error
                swal({
                    text: "Invalid email",
                    icon: "error",
                });
            }
        },
        toggleSignIn() {
            this.$emit('toggleSignIn');
        }
    }
}
</script>

<style scoped>
    #signup h2 {
        font-family: 'Montserrat', sans-serif;
        font-weight: 500;
        font-size: 2em;
        text-align: center;
        font-weight: normal;
        margin-bottom: 50px;
    }

    #signup label {
        font-family: 'Montserrat', sans-serif;
        margin-right: 15px;
        font-style: italic;
    }

    .form-group {
        margin-top: 15px;
        margin-bottom: 15px;
    }

    .signup-form .btn {
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

    #signup .no-btn {
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