<template>
    <div class="filters">
        <ul>
            <li><select id="categories" class="select">
                    <option value="">Categories</option>
                    <option value="New collection">New Arrival</option>
                    <option value="Men">Men</option>
                    <option value="Women">Women</option>
                </select>
            </li>
            <li>Price range</li>
            <li><select id="brands" class="select">
                    <option value="">Brand</option>
                    <option value="Nike">Nike</option>
                    <option value="Yeezy">Yeezy</option>
                    <option value="Balenciaga">Balenciaga</option>
                    <option value="New Balance">New Balance</option>
                </select>
            </li>
            <li><select id="sortBy" class="select">
                    <option value="">Sort by</option>
                    <option value="price">Price</option>
                    <option value="brand">Brand</option>
                </select>
            </li>
            <li></li>
            <li></li>
            <li><button class="btn-filter" @click="applyFilter()">Apply</button></li>
            <li><button @click="closeFilters()" class="btn-filter">Close filter</button></li>
        </ul>
    </div>
</template>

<script>
export default {
    methods: {
        closeFilters() {
            this.$emit('closeFilters');
        },
        applyFilter() {
            let filtersList = [];
            let select = document.getElementById('categories');
            const category = select.options[select.selectedIndex].value;
            if (category && category !== '') {
                filtersList.push(category);
            }
            select = document.getElementById('brands');
            const brand = select.options[select.selectedIndex].value;
            if (brand && brand !== '') {
                filtersList.push(brand);
            }
            select = document.getElementById('sortBy');
            const sort = select.options[select.selectedIndex].value;
            if (sort && sort !== '') {
                filtersList.push(sort);
            }
            const filters = {
                category: category,
                brand: brand,
                sort: sort
            }
            this.$emit('applyFilter', filters);
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