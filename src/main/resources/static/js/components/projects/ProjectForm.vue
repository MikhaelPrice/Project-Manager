<template>
    <v-layout row>
        <v-text-field
                label="New project"
                placeholder="Add a project"
                v-model="text">
        </v-text-field>
        <v-btn @click="save">
            Save
        </v-btn>
    </v-layout>
</template>

<script>
    import { mapActions } from 'vuex'

    export default {
        props: ['projectAttr'],
        data() {
            return {
                text: '',
                id: ''
            }
        },
        watch: {
            projectAttr(newVal, oldVal) {
                this.text = newVal.text
                this.id = newVal.id
            }
        },
        methods: {
            ...mapActions(['addProjectAction', 'updateProjectAction']),
            save() {
                const project = {
                    id: this.id,
                    text: this.text
                }
                if (this.id) {
                   this.updateProjectAction(project)
                } else {
                    this.addProjectAction(project)
                }
                this.text = ''
                this.id = ''
            }
        }
    }
</script>

<style>
</style>