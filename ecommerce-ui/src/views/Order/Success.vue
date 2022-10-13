<template>
    <div role="alert" id="message">
      Payment successful
    </div>
  </template>
  
  <script>
  const axios = require('axios')
  export default {
      name: 'Success',
      data() {
        return {
            token: null,
            sessionId: null,
        }
    },
    methods:{
        async saveOrder() {
            await axios
                .post("api/order/add/?token="+this.token+"&sessionId="+this.sessionId)
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
        console.log('here');
        this.token = localStorage.getItem("token");
        this.sessionId = localStorage.getItem("sessionId");
        await this.saveOrder()
    }
  }
  </script>