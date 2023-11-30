<template>
</template>

<script>
import BaseEntity from './BaseEntity.vue';
import BaseSecurity from './BaseSecurity.vue';

export default {
    name: 'BaseGrid',
    data: () => ({
        flex: null,
        tick : true,
        openDialog : false,
        selectedRow: null,
        path: 'path',
        repository: null,
        menu: [],
        
    }),
    mixins:[
        BaseEntity,
        BaseSecurity
    ],
    async created(){
        this.value = this.search()
    }, 
    methods:{
        addNewRow() {
            this.newValue = null
            this.openDialog = true;
        },
        append() {
            this.tick = false;
            this.openDialog = false
            
            if (!this.value) {
                this.value = [];
            }
            const newItem = { ...this.newValue};

            this.value.push(newItem);
            this.$emit('update:modelValue', this.value);
            this.$nextTick(() => {
                this.tick = true;
            });
        },
        remove(value){
            var where = -1;
            for(var i=0; i<this.value.length; i++){
                if(this.value[i]._links.self.href == value._links.self.href){
                    where = i;
                    break;
                }
            }
            if(where > -1){
                this.value.splice(i, 1);
                this.$emit('input', this.value);
            }
        },


        async search(query) {
            var me = this;
            if(me.offline){
                if(!me.value) me.value = [];
                return;
            } 
            var temp = null;

            if(!me.offline){
                temp = await this.repository.find(query)
                this.value = temp
            }

            return this.value;
        },
        edit(){
            for(var i = 0; i < this.value.length; i++){
                if(this.value[i].index == this.itemToEdit.index){
                    this.value[i] = this.itemToEdit
                    break;
                }
            }
            this.openDialog = false;
        },
        async generateMenu(){
            let menuVal = await this.repository.generate("menus")
            let menuGroupVal = await this.repository.generate("menuGroups")

            for(var i = 0; i < menuVal.length; i++){
                if(!menuVal[i].parentId){
                    if(!this.menu.firstMenu){
                        this.menu.firstMenu = []
                    }
                    this.menu.firstMenu.push(menuVal[i])
                }
            }
            for(var j = 0; j < this.menu.firstMenu.length; j++){
                if(!this.menu.firstMenu[j].secondMenu){
                    this.menu.firstMenu[j].secondMenu = []
                }
                this.menu.firstMenu[j].id = this.findId(this.menu.firstMenu[j])
                for(var i = 0; i < menuVal.length; i++){
                    if(menuVal[i].parentId && menuVal[i].parentId.id){
                        if(this.menu.firstMenu[j].id == menuVal[i].parentId.id){
                            this.menu.firstMenu[j].secondMenu.push(menuVal[i])
                        }
                    }
                }
                for(var i = 0; i < menuGroupVal.length; i++){
                    if(menuGroupVal[i].parentId){
                        for(var k = 0; k < this.menu.firstMenu[j].secondMenu.length; k ++){
                            if(!this.menu.firstMenu[j].secondMenu[k].thirdMenu){
                                this.menu.firstMenu[j].secondMenu[k].thirdMenu = []
                            }
                            this.menu.firstMenu[j].secondMenu[k].id = this.findId(this.menu.firstMenu[j].secondMenu[k])
                            if(this.menu.firstMenu[j].secondMenu[k].id == menuGroupVal[i].parentId.id){
                                this.menu.firstMenu[j].secondMenu[k].thirdMenu.push(menuGroupVal[i])
                            }                   
                        }
                    }
                }
            }
            return this.menu
        },
        findId(val){
            let id = val._links.self.href.split('/');
            return id.pop()
        },
        changeSelectedRow(val){
            this.selectedRow = val
        },
        
       
    },
}
</script>

<style>
</style>