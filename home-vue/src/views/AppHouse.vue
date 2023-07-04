<template>
  <!-- Services-->
  <div>
    <div class="text-center">
      <h3 class="underline-hotpink">아파트 매매정보</h3>
    </div>
    <b-card>
      <b-row>
        <b-col class="text-left">
          <house-search-bar></house-search-bar>
        </b-col>
        <b-col class="text-right">
          <house-keyword-search></house-keyword-search>
        </b-col>
      </b-row>
      <kakao-map id="kakaoMap"></kakao-map>
    </b-card>
    <b-card>
      <b-row>
        <house-card-list v-on:scrollMap="goMapd"></house-card-list>
      </b-row>
    </b-card>
    <!-- <house-list></house-list> -->
  </div>
</template>

<script>
import KakaoMap from "@/components/house/KakaoMap.vue";
import HouseSearchBar from "@/components/house/HouseSearchBar";
// import HouseList from '@/components/house/HouseList.vue';
import HouseKeywordSearch from "@/components/house/HouseKeywordSearch.vue";
import HouseCardList from "@/components/house/HouseCardList.vue";

export default {
  name: "AppHouse",
  data() {
    return {
      sidoName: "null",
      gugunName: "null",
    };
  },
  props: {
    msg: String,
  },
  components: {
    KakaoMap,
    HouseSearchBar,
    // HouseList,
    HouseKeywordSearch,
    HouseCardList,
  },
  methods: {
    goMapd: function(){
      console.log("go : dd")
      const element = document.getElementById("kakaoMap");
      element.scrollIntoView({behavior: 'smooth'});
    }
  },
};
</script>

<style scoped>
.underline-hotpink {
  padding-top: 10px;
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
</style>
