<template>
    <v-list two-line>
        <v-subheader>
            Records
        </v-subheader>

        <template
                v-for="(item, index) in records"
        >
            <v-divider
                    v-if="index > 0"
                    :key="index"
            ></v-divider>

            <record-item
                    :record="item"
                    :key="'item' + index"
            ></record-item>
        </template>

        <record-form :project-id="projectId"></record-form>
    </v-list>
</template>

<script>
    import RecordForm from './RecordForm.vue'
    import RecordItem from './RecordItem.vue'
    export default {
        name: 'RecordList',
        components: {RecordForm, RecordItem},
        props: ['records', 'projectId']
    }
</script>

<style scoped>
</style>