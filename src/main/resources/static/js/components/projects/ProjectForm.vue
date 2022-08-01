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
    import projectsApi from 'api/projects'

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
                const project = {
                id: this.id,
                text: this.text
                }
                if (this.id) {
                   projectsApi.update(project).then(result =>
                        result.json().then(data => {
                            const index = this.projects.findIndex(item => item.id === data.id)
                            this.projects.splice(index, 1, data)
                        })
                    )
                } else {
                    projectsApi.add(project).then(result =>
                        result.json().then(data => {
                            const index = this.projects.findIndex(item => item.id === data.id)

                            if (index > -1) {
                                this.projects.splice(index, 1,data)
                            } else {
                                this.projects.push(data)
                            }
                        })
                    )
                }
                this.text = ''
                this.id = ''
            }
        }
    }
</script>

<style>
</style>