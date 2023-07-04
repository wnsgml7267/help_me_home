<template>
  <b-container>
    <b-row>
      <b-container id="outline">
        <h3 class="text-center">Daily Info.</h3>
        <b-row>
          <b-col>
            <main-weather></main-weather>
          </b-col>
          <b-col>
            <main-apt-graph></main-apt-graph>
          </b-col>
        </b-row>
      </b-container>
    </b-row>
    <b-row>
        <main-apt-search></main-apt-search>
    </b-row>
    <b-row>
        <main-news></main-news>
    </b-row>

  </b-container>
</template>

<script>
import MainWeather from "@/components/main/MainWeather.vue";
import MainAptGraph from "@/components/main/MainAptGraph.vue";
import MainNews from "@/components/main/MainNews.vue";
import MainAptSearch from "@/components/main/MainAptSearch.vue";
export default {
  name: "AppMain",
  components: {
    MainNews,
    MainAptSearch,
    MainWeather,
    MainAptGraph,
  },

  data() {
    return {};
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
#outline{
  background-color: rgb(250, 253, 255);
  margin: 20px;
}
</style>
