<template>
    <v-layout row>
        <v-text-field
                label="Add project description"
                v-model="text"
                @keyup.enter="save"
        />
        <v-btn @click="save">
            Add
        </v-btn>
    </v-layout>
</template>

<script>
    import { mapActions } from 'vuex'
    export default {
        name: 'RecordForm',
        props: ['projectId'],
        data() {
            return {
                text: ''
            }
        },
        methods: {
            ...mapActions(['addRecordAction']),
            async save() {
                await this.addRecordAction({
                    text: this.text,
                    project: {
                        id: this.projectId
                    }
                })
                this.text = ''
            }
        }
    }
</script>

<style scoped>
</style>