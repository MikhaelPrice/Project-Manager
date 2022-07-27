
function getIndex(list, id) {
    for(var i = 0; i<list.length; i++){
        if(list[i].id === id) {
            return i;
        }
    }
    return -1;
}

var projectApi = Vue.resource('/project{/id}');

Vue.component('project-form', {
    props: ['projects', 'projectAttr'],
    data: function() {
        return {
            text: '',
            id:''
        }
    },
    watch: {
        projectAttr: function(newVal, oldVal) {
            this.text = newVal.text;
            this.id = newVal.id;
        }
    },
    template:
        '<div>'+
            '<input type="text" placeholder="Name of project" v-model="text" />'+
            '<input type="button" value="Save" @click="save" />'+
        '</div>',
    methods: {
        save: function() {
            var project = { text: this.text };

                if(this.id) {
                projectApi.update({id: this.id}, project).then(result =>
                    result.json().then(data => {
                            var index = getIndex(this.projects, data.id);
                            this.projects.splice(index, 1, data);
                            this.text=''
                            this.id=''
                        })
                    )
                }else{
                    projectApi.save({}, project).then(result =>
                    result.json().then(data => {
                        this.projects.push(data);
                        this.text=''
                    })
                )
            }
        }
    }
});

Vue.component('project-row', {
    props: ['project', 'editMethod', 'projects'],
    template: '<div>' +
    '<i>({{ project.id }})</i> {{ project.text }}' +
    '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />'+
        '<input type="button" value="X" @click="del" />'+
    '</span>'+
    '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.project);
        },
        del: function() {
            projectApi.remove({id: this.project.id}).then(result => {
                if(result.ok) {
                    this.projects.splice(this.projects.indexOf(this.project), 1)
                }
            })
        }
    }
});

Vue.component('projects-list', {
    props: ['projects'],
    data: function(){
     return {
        project: null
     }
    },
  template:
   '<div style="position: relative; width: 300px;">'+
       '<project-form :projects="projects" :projectAttr="project" />'+
       '<project-row v-for="project in projects" :key="project.id" :project="project" ' +
        ':editMethod="editMethod" :projects="projects" />' +
   '</div>',
   created: function() {
       projectApi.get().then(result =>
        result.json().then(data =>
            data.forEach(project => this.projects.push(project))
        )
    )
   },
   methods: {
        editMethod: function(project) {
            this.project = project;
        }
   }
});

var app = new Vue({
  el: '#app',
  template: '<projects-list :projects="projects" />',
  data: {
    projects: []
  }
});