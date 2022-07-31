<template>
    <div>
        <input type="text" placeholder="Enter a project" v-model="text" />
        <input type="button" value="Save" @click="save" />
    </div>
</template>

<script>
    function getIndex(list, id) {
        for (var i = 0; i < list.length; i++ ) {
            if (list[i].id === id) {
                return i
            }
        }
        return -1
    }
    export default {
        props: ['projects', 'projectAttr'],
        data() {
            return {
                text: '',
                id: ''
            }
        },
        watch: {
            projectAttr(newVal, oldVal) {
                this.text = newVal.text
                this.id = newVal.id
            }
        },
        methods: {
            save() {
                const project = { text: this.text }
                if (this.id) {
                    this.$resource('/project{/id}').update({id: this.id}, project).then(result =>
                        result.json().then(data => {
                            const index = getIndex(this.projects, data.id)
                            this.projects.splice(index, 1, data)
                            this.text = ''
                            this.id = ''
                        })
                    )
                } else {
                    this.$resource('/project{/id}').save({}, project).then(result =>
                        result.json().then(data => {
                            this.projects.push(data)
                            this.text = ''
                        })
                    )
                }
            }
        }
    }
</script>

<style>
</style>