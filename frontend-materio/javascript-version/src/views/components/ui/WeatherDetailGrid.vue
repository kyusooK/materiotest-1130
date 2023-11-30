<template>
    <div class="panel">
        <div class="label-title">{{label}}</div>
        <div class="table-responsive">
            <v-btn v-if="editMode" @click="addDetailRow()">추가</v-btn>
            <v-btn v-if="editMode" style="margin-left: 3px;" @click="detailDeleteRow()">삭제</v-btn>
            <v-table v-if="!editMode">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>degree</th>
                        <th>precipitation</th>
                        <th>humidity</th>
                        <th>wind</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(detailVal, idx) in selectedRow.salesItem" :key="detailVal" >
                        <td class="font-semibold">{{ idx + 1 }}</td>
                        <td class="whitespace-nowrap">{{ val.degree }}</td>
                        <td class="whitespace-nowrap">{{ val.precipitation }}</td>
                        <td class="whitespace-nowrap">{{ val.humidity }}</td>
                        <td class="whitespace-nowrap">{{ val.wind }}</td>
                        <td class="whitespace-nowrap">
                            <Icon icon="mi:delete" @click="deleteRow(detailVal)" />
                        </td>
                    </tr>
                </tbody>
            </v-table>
            <v-table v-else>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>degree</th>
                        <th>precipitation</th>
                        <th>humidity</th>
                        <th>wind</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(addVal, idx) in newValue" :key="addVal" >
                        <td class="font-semibold">{{ idx + 1 }}</td>
                        <td class="whitespace-nowrap">
                            <Number style="margin-left: -5px; width: 150px;" :editMode="editMode" v-model="addVal.degree"/>
                        </td>
                        <td class="whitespace-nowrap">
                            <Number style="margin-left: -5px; width: 150px;" :editMode="editMode" v-model="addVal.precipitation"/>
                        </td>
                        <td class="whitespace-nowrap">
                            <Number style="margin-left: -5px; width: 150px;" :editMode="editMode" v-model="addVal.humidity"/>
                        </td>
                        <td class="whitespace-nowrap">
                            <Number style="margin-left: -5px; width: 150px;" :editMode="editMode" v-model="addVal.wind"/>
                        </td>
                    </tr>
                </tbody>
            </v-table>
        </div>
    </div>
</template>
<script>
import BaseDetailGrid from '../base-ui/BaseDetailGrid.vue';
import Number from '../primitives/Number.vue'
import Precipitation from '../Precipitation.vue'
import Humidity from '../Humidity.vue'
import Wind from '../Wind.vue'

export default {
    name: 'Weather',
    mixins: [BaseDetailGrid],
    components: {
        Number,
        Precipitation,
        Humidity,
        Wind,
    },
    props: {
        label: String,
        editMode: Boolean,
        selectedRow: Object,
    },
    data: ()=>({
    }),
    created(){
    },
    methods: {
    }
}
</script>
