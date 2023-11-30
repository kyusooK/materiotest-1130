<template>

    <v-card outlined>
        <v-card-title>
            Weather
        </v-card-title>

        <v-card-text>
            <Number label="Degree" v-model="value.degree" :editMode="editMode"/>
            <Number label="Precipitation" v-model="value.precipitation" :editMode="editMode"/>
            <Number label="Humidity" v-model="value.humidity" :editMode="editMode"/>
            <Number label="Wind" v-model="value.wind" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions v-if="inList">
            <slot name="actions"></slot>
        </v-card-actions>
    </v-card>
</template>

<script>

    export default {
        name: 'Weather',
        components:{},
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
            inList: Boolean,
            label: String,
        },
        data: () => ({
        }),
        async created() {
            if(!Object.values(this.value)[0]) {
                this.$emit('input', {});
                this.newValue = {
                    'degree': '',
                    'precipitation': '',
                    'humidity': '',
                    'wind': '',
                }
            }
            if(typeof this.value === 'object') {
                if(!('degree' in this.value)) {
                    this.value.degree = 0;
                }
                if(!('precipitation' in this.value)) {
                    this.value.precipitation = 0;
                }
                if(!('humidity' in this.value)) {
                    this.value.humidity = 0;
                }
                if(!('wind' in this.value)) {
                    this.value.wind = 0;
                }
            }
        },
        watch: {
            value(val) {
                this.$emit('input', val);
            },
            newValue(val) {
                this.$emit('input', val);
            },
        },

        methods: {
            edit() {
                this.editMode = true;
            },
            async add() {
                this.editMode = false;
                this.$emit('input', this.value);

                if(this.isNew){
                    this.$emit('add', this.value);
                } else {
                    this.$emit('edit', this.value);
                }
            },
            async remove(){
                this.editMode = false;
                this.isDeleted = true;

                this.$emit('input', this.value);
                this.$emit('delete', this.value);
            },
            change(){
                this.$emit('change', this.value);
            },
        }
    }
</script>

