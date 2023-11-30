<template>
    <div>
        <String label="Name" v-model="value.name" :editMode="editMode"/>
        <String label="Industry" v-model="value.industry" :editMode="editMode"/>
        <String label="PhoneNo" v-model="value.phoneNo" :editMode="editMode"/>
        <Weather offline label="Weather" v-model="value.weather" :editMode="editMode" @change="change"/>

        <v-divider class="border-opacity-100 my-divider"></v-divider>
        <v-layout row justify-end>
            <v-btn
                width="64px"
                color="primary"
                @click="save"
            >
                저장
            </v-btn>
        </v-layout>
    </div>
</template>


<script>

import BaseEntity from './base-ui/BaseEntity.vue'
import String from './primitives/String.vue'
import Weather from './vo/Weather.vue'

export default {
    name: 'Company',
    mixins:[BaseEntity],
    components:{
        String,
        Weather,
    },
    
    data: () => ({
        path: "companies"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
