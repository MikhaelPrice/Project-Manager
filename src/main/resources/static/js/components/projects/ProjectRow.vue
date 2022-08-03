<template>
    <v-card class="my-2">
        <v-card-text primary-title>
            <i>({{ project.id }})</i>
            {{ project.text }}
        </v-card-text>
        <v-card-actions>
            <v-btn value="Edit" @click="edit" small flat round>Edit</v-btn>
            <v-btn icon @click="del" small>
                <v-icon>delete</v-icon>
            </v-btn>
        </v-card-actions>
        <record-list
                :records="project.records"
                :project-id="project.id">
        </record-list>
    </v-card>
</template>

<script>
    import { mapActions } from 'vuex'
    import RecordList from '../record/RecordList.vue'
    export default {
        props: ['project', 'editProject'],
        components: { RecordList },
        methods: {
            ...mapActions(['removeProjectAction']),
            edit() {
                this.editProject(this.project)
            },
            del() {
                this.removeProjectAction(this.project)
            }
        }
    }
</script>

<style>
</style>