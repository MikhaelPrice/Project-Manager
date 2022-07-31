<template>
    <div style="position: relative; width: 300px;">
        <project-form :projects="projects" :projectAttr="project" />
        <project-row v-for="project in projects"
                     :key="project.id"
                     :project="project"
                     :editProject="editProject"
                     :deleteProject="deleteProject"
                     :projects="projects" />
    </div>
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