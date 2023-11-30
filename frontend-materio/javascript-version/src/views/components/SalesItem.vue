<template>

    <div>
        <div class="detail-title">
        SalesItem
        </div>
        <v-col>
            <Number label="Qty" v-model="value.qty" :editMode="editMode"/>
            <Number label="Price" v-model="value.price" :editMode="editMode"/>
        </v-col>

        <v-card-actions v-if="inList">
            <slot name="actions"></slot>
        </v-card-actions>
    </div>
</template>

<script>
import BaseEntity from './base-ui/BaseEntity.vue'
import BasePicker from './base-ui/BasePicker.vue'

export default {
    name: 'SalesItem',
    mixins:[BaseEntity],
    components:{
        BasePicker
    },
    data: () => ({
        path: 'SalesItems',
    }),
    props: {
    },
    watch: {
        value(val){
            this.value = val;
            this.change();
        },
    },
    async created(){
        this.value = this.modelValue
        if (this.value && this.value.id !== undefined) {
            this.value = await this.repository.findById(this.value.id)
        }
    },
    methods: {
        pick(val){
            this.value = val;
            this.change();
        },
    }
}
</script>

