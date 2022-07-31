<template>
    <div>
        <i>({{ project.id }})</i> {{ project.text }}
        <span style="position: absolute; right: 0">
            <input type="button" value="Edit" @click="edit" />
            <input type="button" value="X" @click="del" />
        </span>
    </div>
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