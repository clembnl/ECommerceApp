<template>
    <div class="filters">
        <ul>
            <li><select name="categories" class="select">
                    <option value="">Categories</option>
                    <option value="new arrival">New Arrival</option>
                    <option value="men">Men</option>
                    <option value="women">Women</option>
                </select>
            </li>
            <li>Price range</li>
            <li><select name="brands" class="select">
                    <option value="">Brand</option>
                    <option value="Nike">Nike</option>
                    <option value="Yeezy">Yeezy</option>
                    <option value="Balenciaga">Balenciaga</option>
                    <option value="New Balance">New Balance</option>
                </select>
            </li>
            <li><select name="sortBy" class="select">
                    <option value="">Sort by</option>
                    <option value="price">Price</option>
                    <option value="brand">Brand</option>
                </select>
            </li>
            <li><button @click="closeFilters()" class="btn-filter">Close filter</button></li>
        </ul>
    </div>
</template>

<script>
export default {
    methods: {
        closeFilters() {
            this.$emit('closeFilters', false);
        }
    }
}

</script>

<style>
.filters {
    font-family: 'Montserrat', sans-serif;
    opacity: 0.5;
    margin: 10px 200px 30px 200px;
}

.filters ul {
    display: flex;
    justify-content: space-between;
    list-style: none;
    padding: 0 27px 0 27px;
}

.btn-filter {
    background-color: white;
    border: none;
    font-family: 'Montserrat', sans-serif;
    font-size: 1em;
}

.select {
    background-color: white;
    border: none;
    font-family: 'Montserrat', sans-serif;
    font-size: 1em;
}
</style>