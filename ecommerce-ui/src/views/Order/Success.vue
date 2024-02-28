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
                .post(`order/add/?sessionId=${this.sessionId}`, null, { headers: authHeader() })
                    .then(()=>{
                    this.$router.push({ name: 'Account'})
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