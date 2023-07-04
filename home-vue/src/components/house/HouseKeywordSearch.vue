<template>
    <b-row class="mt-2 mb-4 text-center">
        <b-col class="sm-3">
            <b-input-group prepend="키워드 검색" class="mt-3">
                <b-form-input placeholder="아파트 이름" v-model="keyword"></b-form-input>
                <b-input-group-append>
                    <b-button variant="info" @click="searchKeyword()">검색</b-button>
                </b-input-group-append>
            </b-input-group>
        </b-col>
    </b-row>
</template>

<script>
import { mapActions } from 'vuex';
export default {
    name: 'HouseKeywordSearch',
    components: {},
    data() {
        return {
            keyword: '',
        };
    },
    created() {},
    methods: {
        ...mapActions(["getKeywordHouseList"]),
        searchKeyword(){
            console.log(this.keyword)
            if (this.keyword){
                this.toast('b-toaster-bottom-center', true, "keyword", "서칭이 수행되었습니다");
                this.getKeywordHouseList(this.keyword);
            }
            else{
                // alert("키워드를 입력해주세요")
                this.toast('b-toaster-bottom-center', true, "keyword", "검색어를 입력해주세요");
            }
        },

        toast(toaster, append = false, title, msg) {
            this.$bvToast.toast(msg, {
                title: title,
                toaster: toaster,
                solid: true,
                appendToast: append
            })
        }
    },
};
</script>

<style scoped></style>