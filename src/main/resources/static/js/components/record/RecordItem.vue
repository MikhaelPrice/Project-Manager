<template>
    <v-list-tile>
        <v-list-tile-content>
            <v-list-tile-title>{{record.text}}</v-list-tile-title>
        </v-list-tile-content>
    </v-list-tile>
</template>

<script>
    export default {
        name: 'RecordItem',
        props: ['record']
    }
</script>

<style scoped>
</style>