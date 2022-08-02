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
                <projects-list></projects-list>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import { mapState, mapMutations } from 'vuex'
    import ProjectsList from 'components/projects/ProjectList.vue'
    import { addHandler } from 'util/ws'

    export default {
        components: {
            ProjectsList
        },
        computed: mapState(['profile']),
        methods: mapMutations(['addProjectMutation', 'updateProjectMutation', 'removeProjectMutation']),
        created() {
            addHandler(data => {
                if (data.objectType === 'PROJECT') {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addProjectMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updateProjectMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removeProjectMutation(data.body)
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