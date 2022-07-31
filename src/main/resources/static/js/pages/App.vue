<template>
    <div>
        <div v-if="!profile">Необходимо авторизоваться через
            <a href="/login">Google</a>
        </div>
        <div v-else>
            <div>{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>
            <projects-list :projects="projects"></projects-list>
        </div>
    </div>
</template>

<script>
    import ProjectsList from 'components/projects/ProjectList.vue'
    export default {
        components: {
            ProjectsList
        },
        data() {
            return {
                projects: frontendData.projects,
                profile: frontendData.profile
            }
        }
    }
</script>

<style>
</style>