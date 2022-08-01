<template>
        <v-layout align-space-around justify-start column>
        <project-form :projects="projects" :projectAttr="project"></project-form>
        <project-row v-for="project in sortedProjects"
                     :key="project.id"
                     :project="project"
                     :editProject="editProject"
                     :deleteProject="deleteProject"
                     :projects="projects"></project-row>
    </v-layout>
</template>

<script>
    import ProjectRow from 'components/projects/ProjectRow.vue'
    import ProjectForm from 'components/projects/ProjectForm.vue'
    export default {
        props: ['projects'],
        components: {
            ProjectRow,
            ProjectForm
        },
        data() {
            return {
                project: null
            }
        },
        computed: {
            sortedProjects() {
                return this.projects.sort((a, b) => -(a.id - b.id))
            }
        },
        methods: {
            editProject(project) {
                this.project = project
            },
            deleteProject(project) {
                this.$resource('/project{/id}').remove({id: project.id}).then(result => {
                    if (result.ok) {
                        this.projects.splice(this.projects.indexOf(project), 1)
                    }
                })
            }
        }
    }
</script>

<style>

</style>