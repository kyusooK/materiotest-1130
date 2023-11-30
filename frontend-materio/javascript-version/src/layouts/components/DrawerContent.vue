<template>
    <div class="nav-header">
        <RouterLink
            to="/"
            class="app-logo d-flex align-center gap-x-3 app-title-wrapper"
        >
            <Transition name="vertical-nav-app-title">
                <h1 class="font-weight-semibold leading-normal text-xl text-uppercase">
                Home
                </h1>
            </Transition>
        </RouterLink>
    </div>
    <ul>
        <v-list :opened="open" style="background-color: #F4F5FA;">
            <template v-slot:activator="{ props }">
                <v-list-item
                    v-bind="props"
                    prepend-icon="mdi-account-circle"
                    title="Users"
                >
                </v-list-item>
            </template>
            <v-list-group value="Basic">        
                <template v-slot:activator="{ props }">
                    <v-list-item
                    style="margin-left: 10px;"
                    v-bind="props"
                    title="Basic"
                    ></v-list-item>
                </template>
                <v-list-item
                    v-for="item in basic"
                    :key="item.key"
                    :title="item.name"
                    :value="item.name"
                    :to="item.url"
                ></v-list-item>
            </v-list-group>
            <v-list-group value="Sales">        
                <template v-slot:activator="{ props }">
                    <v-list-item
                    style="margin-left: 10px;"
                    v-bind="props"
                    title="Sales"
                    ></v-list-item>
                </template>
                <v-list-item
                    v-for="item in sales"
                    :key="item.key"
                    :title="item.name"
                    :value="item.name"
                    :to="item.url"
                ></v-list-item>
            </v-list-group>
        </v-list>
    </ul>
</template>

<script>
import { useTheme } from 'vuetify';

export default {
    name:'DrawerContent',
    components:{
    },
    data: () => ({
        drawer: true,
        sideBar: true,
        urlPath: null,
        open: ['Users'],
        basic: [
            { key: 'companies', url: '/companies', name: 'Company'},
        ],
        sales: [
            { key: 'salesOrders', url: '/salesOrders', name: 'SalesOrder'},
        ],
    }),
    created(){
    },
    computed:{
    },
    methods: {
        changeUrl(){
            const path = document.location.href.split("#/");
            urlPath.value = path[1];
        },
        goHome(){
            urlPath.value = null;
        }
    },
}
</script>