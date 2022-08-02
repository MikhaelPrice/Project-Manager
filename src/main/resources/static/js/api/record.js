import Vue from 'vue'

const records = Vue.resource('/record{/id}')

export default {
    add: record => records.save({}, record),
}