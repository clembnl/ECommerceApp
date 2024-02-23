<template>
    <div role="alert" id="message">
      Payment successful
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import authHeader from "../../services/auth-header.js";

  export default {
      name: 'Success',
      data() {
        return {
            sessionId: null,
        }
    },
    methods:{
        async saveOrder() {
            await axios
                .post("api/order/add/sessionId="+this.sessionId, { headers: authHeader() })
                    .then(()=>{
                    //window.location.href = '/order'
                    //document.getElementById("message").innerHTML = "order placed";
                    this.$router.push({ name: 'OrderHistory'})
                    //router.push()
                    })
                    .catch((error)=>{
                        console.log(error);
                    });
        }
    },
    async mounted() {
        this.sessionId = localStorage.getItem("sessionId");
        await this.saveOrder()
    }
  }
  </script>