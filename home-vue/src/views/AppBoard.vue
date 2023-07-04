<template>
	<div>
        <h3 class="underline-hotpink">QnA게시판</h3>
        <router-view></router-view>
    </div>
</template>

<script>
export default {
    name: 'AppBoard',
    components: {},
    data() {
        return {
            message: '',
        };
    },
    created() {},
    methods: {},
};
</script>

<style scoped>
.underline-hotpink {
    padding-top: 10px;
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 27, 139, 0.3) 30%);
}
</style>