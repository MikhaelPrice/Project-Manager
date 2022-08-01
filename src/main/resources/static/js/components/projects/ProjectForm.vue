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
    import { sendProject } from 'util/ws'

    export default {
        props: ['projects', 'projectAttr'],
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
            save() {
                sendProject({id: this.id, text: this.text})
                this.text = ''
                this.id = ''
                /*const project = { text: this.text }
                if (this.id) {
                    this.$resource('/project{/id}').update({id: this.id}, project).then(result =>
                        result.json().then(data => {
                            const index = getIndex(this.projects, data.id)
                            this.projects.splice(index, 1, data)
                            this.text = ''
                            this.id = ''
                        })
                    )
                } else {
                    this.$resource('/project{/id}').save({}, project).then(result =>
                        result.json().then(data => {
                            this.projects.push(data)
                            this.text = ''
                        })
                    )
                }*/
            }
        }
    }
</script>

<style>
</style>