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
    </v-card>
</template>

<script>
    export default {
        props: ['project', 'editProject', 'deleteProject', 'projects'],
        methods: {
            edit() {
                this.editProject(this.project)
            },
            del() {
                this.deleteProject(this.project)
            }
        }
    }
</script>

<style>
</style>