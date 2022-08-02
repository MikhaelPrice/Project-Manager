<template>
    <v-container>
        <v-layout align-space-around justify-start column>
        <project-form :projectAttr="project"></project-form>
        <project-row v-for="project in sortedProjects"
                     :key="project.id"
                     :project="project"
                     :editProject="editProject"
                      ></project-row>
        </v-layout>
    </v-container>
</template>

<script>
    import { mapGetters} from 'vuex'
    import ProjectRow from 'components/projects/ProjectRow.vue'
    import ProjectForm from 'components/projects/ProjectForm.vue'

    export default {
        components: {
            ProjectRow,
            ProjectForm
        },
        data() {
            return {
               project: null
            }
        },
        computed: mapGetters(['sortedProjects']),
        methods: {
            editProject(project) {
                this.project = project
            }
        }
    }
</script>

<style>

</style>