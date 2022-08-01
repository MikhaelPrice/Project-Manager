<template>
    <v-app>
        <v-toolbar app>
            <v-toolbar-title>Projects</v-toolbar-title>
            <v-spacer></v-spacer>
            <span v-if="profile">{{profile.name}}</span>
            <v-btn v-if="profile" icon href="/logout">
                <v-icon>exit_to_app</v-icon>
            </v-btn>
        </v-toolbar>
        <v-content>
            <v-container v-if="!profile">
                Необходимо авторизоваться через
                <a href="/login">Google</a>
            </v-container>
            <v-container v-if="profile">
                <projects-list :projects="projects"></projects-list>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import ProjectsList from 'components/projects/ProjectList.vue'
    import { addHandler } from 'util/ws'
    import { getIndex } from 'util/collections'

    export default {
        components: {
            ProjectsList
        },
        data() {
            return {
                projects: frontendData.projects,
                profile: frontendData.profile
            }
        },
        created() {
            addHandler(data => {
                let index = getIndex(this.projects, data.id)
                if(index > -1) {
                    this.projects.splice(index,1,data)
                }else{
                    this.projects.push(data)
                }
            })
        }
    }
</script>

<style>

</style>