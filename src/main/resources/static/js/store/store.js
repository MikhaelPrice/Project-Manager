import Vue from 'vue'
import Vuex from 'vuex'
import projectsApi from 'api/projects'
import recordApi from 'api/record'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        projects,
        profile: frontendData.profile
    },
    getters: {
        sortedProjects: state => (state.projects || []).sort((a, b) => -(a.id - b.id))
    },
    mutations: {
        addProjectMutation(state, project) {
            state.projects = [
                ...state.projects,
                project
            ]
        },
        updateProjectMutation(state, project) {
            const updateIndex = state.projects.findIndex(item => item.id === project.id)

            state.projects = [
                ...state.projects.slice(0, updateIndex),
                project,
                ...state.projects.slice(updateIndex + 1)
            ]
        },
        removeProjectMutation(state, project) {
            const deletionIndex = state.projects.findIndex(item => item.id === project.id)

            if (deletionIndex > -1) {
                state.projects = [
                    ...state.projects.slice(0, deletionIndex),
                    ...state.projects.slice(deletionIndex + 1)
                ]
            }
        },
        addRecordMutation(state, record) {
            const updateIndex = state.projects.findIndex(item => item.id === record.project.id)
            const project = state.projects[updateIndex]

            state.projects = [
                ...state.projects.slice(0, updateIndex),
                {
                    ...project,
                    records: [
                        ...project.records,
                        record
                    ]
                },
                ...state.projects.slice(updateIndex + 1)
            ]
        },

    },
    actions: {
        async addProjectAction({commit, state}, project) {
            const result = await projectsApi.add(project)
            const data = await result.json()
            const index = state.projects.findIndex(item => item.id === data.id)

            if (index > -1) {
                commit('updateProjectMutation', data)
            } else {
                commit('addProjectMutation', data)
            }
        },
        async updateProjectAction({commit}, project) {
            const result = await projectsApi.update(project)
            const data = await result.json()
            commit('updateProjectMutation', data)
        },
        async removeProjectAction({commit}, project) {
            const result = await projectsApi.remove(project.id)

            if (result.ok) {
                commit('removeProjectMutation', project)
            }
        },
        async addRecordAction({commit, state}, record) {
            const response = await recordApi.add(record)
            const data = await response.json()
            commit('addRecordMutation', record)
        }
    }
})