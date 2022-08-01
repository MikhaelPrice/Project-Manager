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
                if (data.objectType === 'PROJECT') {
                    const index = this.projects.findIndex(item => item.id === data.body.id)
                    switch (data.eventType) {
                        case 'CREATE':
                        case 'UPDATE':
                            if (index > -1) {
                                this.projects.splice(index, 1, data.body)
                            } else {
                                this.projects.push(data.body)
                            }
                            break
                        case 'REMOVE':
                            this.projects.splice(index, 1)
                            break
                        default:
                            console.error(`Looks like the event type if unknown "${data.eventType}"`)
                    }
                } else {
                    console.error(`Looks like the object type if unknown "${data.objectType}"`)
                }
            })
        }
    }
</script>

<style>

</style>