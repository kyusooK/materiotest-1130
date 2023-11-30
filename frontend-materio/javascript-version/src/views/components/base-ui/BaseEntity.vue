<template>
    <div></div>
</template>

<script>
import axios from 'axios'
import BaseRepository from '../repository/BaseRepository'
import { ref } from 'vue';

export default {
    name: 'BaseEntity',
    props: {
        offline: Boolean,
        modelValue: Object,
        editMode: Boolean,
        isNew: Boolean,
        inList: Boolean,
    },
    data() {
        return {
            values: [],
            newValue: {},
            isUpdating: false,
            path: '/path',
            repository: null,
            updateCompanyDiagram: false,
            openDialog : false,
            value: {},
            snackbar: {
                status: false,
                timeout: 5000,
                text: '',
                color: 'info',
            },
        };
    },
    async created() {
        this.newValue = this.modelValue
        this.repository = new BaseRepository(axios, this.path);
    },
    methods: {
        selectFile() {
            if(this.editMode == false) {
                return false;
            }
            var me = this
            var input = document.createElement("input");
            input.type = "file";
            input.accept = "image/*";
            input.id = "uploadInput";
            
            input.click();
            input.onchange = function (event) {
                var file = event.target.files[0]
                var reader = new FileReader();

                reader.onload = function () {
                    var result = reader.result;
                    me.imageUrl = result;
                    me.value.photo = result;
                    
                };
                reader.readAsDataURL( file );
            };
        },
        edit() {
            this.$emit("update:editMode", true)
        },
        async save() {
            try {
                var temp = null;
                console.log(this.newValue)
                if(!this.offline) {
                    
                    temp = await this.repository.save(this.value, this.isNew)
                    this.value = temp
                }
                if(this.value!=null) {
                    for(var k in temp.data) this.value[k]=temp.data[k];
                } else {
                    this.value = temp.data;
                }

                this.$emit('update:editMode', false);
                this.$emit('update:modelValue', this.value);

                if (this.isNew) {
                    this.$emit('add', this.value);
                } else {
                    this.$emit('edit', this.value);
                }

            } catch(e) {
                this.error(e)
            }
        },
        async deleteRow(selectedRow) {
            try {
                var temp = null
                if (!this.offline) {
                    temp = await this.repository.delete(selectedRow)
                    this.value = temp
                }

                this.$emit('update:modelValue', this.value);
                this.$emit('delete', this.value);
            } catch(e) {
                this.error(e)
            }
        },
        change() {
            this.$emit("update:modelValue", this.value);
        },
        closeDialog() {
            this.openDialog = false;
            this.$emit("update:editMode", false)
        },
        error(e){
            this.snackbar.status = true
            this.snackbar.color = 'error'
            if(e.response && e.response.data.message) {
                this.snackbar.text = e.response.data.message
            } else {
                this.snackbar.text = e
            }
        },
        success(msg){
            this.snackbar.color= 'info'
            this.snackbar.status = true
            this.snackbar.text = msg

        }
    },
};


</script>