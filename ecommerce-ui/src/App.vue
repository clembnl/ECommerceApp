<template>
  <Navbar />
  <router-view/>
</template>

<script>
import Navbar from './components/Navbar.vue'
import axios from 'axios'

export default {
  components:
  {
    Navbar,
  },
  data() {
    return {
      products: [],
      categories: [],
    }
  },
  methods: {
    async fetchData() {
      await axios
        .get('api/category/')
        .then(response => {
          this.categories = response.data
        })
        .catch((err) => console.log("err", err));

      await axios
        .get('api/product/')
        .then(response => {
          this.products = response.data
        })
        .catch((err) => console.log("err", err));
    },
  },
  mounted() {
    this.fetchData();
  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500&family=Poppins:wght@600&display=swap');
</style>