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
            <li>Price Range
                <div class="range_container">
                    <div class="sliders_control">
                        <input id="fromSlider" type="range" value="0" min="0" max="2000"/>
                        <input id="toSlider" type="range" value="1100" min="0" max="2000"/>
                    </div>
                    <div class="form_control">
                        <div class="form_control_container">
                            <div class="form_control_container__min">Min</div>
                            <input class="form_control_container__min__input" type="number" id="fromInput" value="0" min="0" max="2000"/>
                        </div>
                        <div class="form_control_container">
                            <div class="form_control_container__max">Max</div>
                            <input class="form_control_container__max__input" type="number" id="toInput" value="1100" min="0" max="2000"/>
                        </div>
                    </div>
                </div>
            </li>
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
            <li><button class="btn-filter hover-animation" @click="applyFilter()">Apply</button></li>
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
            const range1 = document.getElementById('fromSlider').value;
            const range2 = document.getElementById('toSlider').value;
            const range = new Array(range1, range2);
            select = document.getElementById('brands');
            const brand = select.options[select.selectedIndex].value;
            select = document.getElementById('sortBy');
            const sort = select.options[select.selectedIndex].value;
            const filters = {
                category: category,
                range: range,
                brand: brand,
                sort: sort
            }
            this.$emit('applyFilter', filters);
        },
        getParsed(currentFrom, currentTo) {
            const from = parseInt(currentFrom.value, 10);
            const to = parseInt(currentTo.value, 10);
            return [from, to];
        },
        fillSlider(from, to, sliderColor, rangeColor, controlSlider) {
            const rangeDistance = to.max-to.min;
            const fromPosition = from.value - to.min;
            const toPosition = to.value - to.min;
            controlSlider.style.background = `linear-gradient(
                to right,
                ${sliderColor} 0%,
                ${sliderColor} ${(fromPosition)/(rangeDistance)*100}%,
                ${rangeColor} ${((fromPosition)/(rangeDistance))*100}%,
                ${rangeColor} ${(toPosition)/(rangeDistance)*100}%, 
                ${sliderColor} ${(toPosition)/(rangeDistance)*100}%, 
                ${sliderColor} 100%)`;
        },
        setToggleAccessible(currentTarget) {
            const toSlider = document.querySelector('#toSlider');
            if (Number(currentTarget.value) <= 0 ) {
                toSlider.style.zIndex = 2;
            } else {
                toSlider.style.zIndex = 0;
            }
        },
        controlFromInput(fromSlider, fromInput, toInput, controlSlider) {
            const [from, to] = this.getParsed(fromInput, toInput);
            this.fillSlider(fromInput, toInput, '#C6C6C6', 'burlywood', controlSlider);
            if (from > to) {
                fromSlider.value = to;
                fromInput.value = to;
            } else {
                fromSlider.value = from;
            }
        },
        controlToInput(toSlider, fromInput, toInput, controlSlider) {
            const [from, to] = this.getParsed(fromInput, toInput);
            this.fillSlider(fromInput, toInput, '#C6C6C6', 'burlywood', controlSlider);
            this.setToggleAccessible(toInput);
            if (from <= to) {
                toSlider.value = to;
                toInput.value = to;
            } else {
                toInput.value = from;
            }
        },
        controlFromSlider(fromSlider, toSlider, fromInput) {
            const [from, to] = this.getParsed(fromSlider, toSlider);
            this.fillSlider(fromSlider, toSlider, '#C6C6C6', 'burlywood', toSlider);
            if (from > to) {
                fromSlider.value = to;
                fromInput.value = to;
            } else {
                fromInput.value = from;
            }
        },
        controlToSlider(fromSlider, toSlider, toInput) {
            const [from, to] = this.getParsed(fromSlider, toSlider);
            this.fillSlider(fromSlider, toSlider, '#C6C6C6', 'burlywood', toSlider);
            this.setToggleAccessible(toSlider);
            if (from <= to) {
                toSlider.value = to;
                toInput.value = to;
            } else {
                toInput.value = from;
                toSlider.value = from;
            }
        },
    },
    mounted() {
        const fromSlider = document.querySelector('#fromSlider');
        const toSlider = document.querySelector('#toSlider');
        const fromInput = document.querySelector('#fromInput'); 
        const toInput = document.querySelector('#toInput');
        this.fillSlider(fromSlider, toSlider, '#C6C6C6', 'burlywood', toSlider);
        this.setToggleAccessible(toSlider);
        fromSlider.oninput = () => this.controlFromSlider(fromSlider, toSlider, fromInput);
        toSlider.oninput = () => this.controlToSlider(fromSlider, toSlider, toInput);
        fromInput.oninput = () => this.controlFromInput(fromSlider, fromInput, toInput, toSlider);
        toInput.oninput = () => this.controlToInput(toSlider, fromInput, toInput, toSlider);
    }
}

</script>

<style scoped>
.filters {
    font-family: 'Montserrat', sans-serif;
    opacity: 0.5;
    margin: 10px 130px 30px 130px;
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

.range_container {
  display: flex;
  flex-direction: column;
  width: 85%;
  margin-top: 20px;
}

.sliders_control {
  position: relative;
  height: 22px;
  width: 115%;
}

.form_control {
  position: relative;
  display: flex;
  justify-content: space-between;
  font-size: 0.9em;
  width: 120%;
}

.form_control_container__min {
    text-align: left;
}

.form_control_container__max{
    text-align: right;
}

#fromSlider::-webkit-slider-thumb {
  -webkit-appearance: none;
  pointer-events: all;
  width: 13px;
  height: 13px;
  background-color: burlywood;
  border-radius: 50%;
  cursor: pointer;
}

#fromSlider::-moz-range-thumb {
  -webkit-appearance: none;
  pointer-events: all;
  width: 13px;
  height: 13px;
  background-color: burlywood;
  border-radius: 50%;
  cursor: pointer;  
}

#toSlider::-webkit-slider-thumb {
  -webkit-appearance: none;
  pointer-events: all;
  width: 17px;
  height: 17px;
  background-color: burlywood;
  border-radius: 50%;
  cursor: pointer;
}

#toSlider::-moz-range-thumb {
  -webkit-appearance: none;
  pointer-events: all;
  width: 17px;
  height: 17px;
  background-color: burlywood;
  border-radius: 50%;
  cursor: pointer;  
}

input[type=range]::-webkit-slider-thumb:hover {
  background: gold;
}

input[type=range]::-webkit-slider-thumb:active {
  box-shadow: inset 0 0 2px rgba(0, 0, 0, 0.7), 0 0 7px rgba(0, 0, 0, 0.7);
  -webkit-box-shadow: inset 0 0 2px rgba(0, 0, 0, 0.7), 0 0 7px rgba(0, 0, 0, 0.7);
}

input[type="number"] {
  width: 42px;
  height: 30px;
  font-size: 0.8em;
  border: none;
  padding: 0;
}

input[type=number]::-webkit-inner-spin-button, 
input[type=number]::-webkit-outer-spin-button {  
   opacity: 1;
}


input[type="range"] {
  -webkit-appearance: none; 
  appearance: none;
  height: 2px;
  width: 100%;
  position: absolute;
  background-color: #C6C6C6;
  pointer-events: none;
}

#fromSlider {
  height: 0;
  z-index: 1;
}

.hover-animation {
  display: inline-block;
  position: relative;
}

.hover-animation::after {
  content: '';
  position: absolute;
  width: 85%;
  transform: scaleX(0);
  height: 2px;
  bottom: -2px;
  left: 3px;
  background-color: burlywood;
  transform-origin: bottom right;
  transition: transform 0.25s ease-out;
}

.hover-animation:hover::after {
  transform: scaleX(1);
  transform-origin: bottom left;
}
</style>