import Vue from 'vue'
import VueRouter from 'vue-router'
import ProjectsList from 'pages/ProjectList.vue'
import Auth from 'pages/Auth.vue'
import Profile from 'pages/Profile.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/', component: ProjectsList },
    { path: '/auth', component: Auth },
    { path: '/profile', component: Profile },
    { path: '*', component: ProjectsList },
]

export default new VueRouter({
    mode: 'history',
    routes
})