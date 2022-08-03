<template>
    <v-app>
        <v-toolbar app>
            <v-toolbar-title>Sarafan</v-toolbar-title>
            <v-btn flat
                   v-if="profile"
                   :disabled="$route.path === '/'"
                   @click="showProjects">
                Projects
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn flat
                   v-if="profile"
                   :disabled="$route.path === '/profile'"
                   @click="showProfile">
                {{profile.name}}
            </v-btn>
            <v-btn v-if="profile" icon href="/logout">
                <v-icon>exit_to_app</v-icon>
            </v-btn>
        </v-toolbar>
        <v-content>
            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>
    import { mapState, mapMutations } from 'vuex'
    import { addHandler } from 'util/ws'
    export default {
        computed: mapState(['profile']),
        methods: {
            ...mapMutations(['addProjectMutation', 'updateProjectMutation', 'removeProjectMutation']),
            showProjects() {
                this.$router.push('/')
            },
            showProfile() {
                this.$router.push('/profile')
            }
        },
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
        },
        beforeMount() {
            if (!this.profile) {
                this.$router.replace('/auth')
            }
        }
    }
</script>

<style>
</style>