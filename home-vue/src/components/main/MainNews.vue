<template>
  <b-container>
    <h3 class="text-right">HOT ISSUE</h3>
    <div>
      <!-- v-model="slide" 일단 뻄 -->
      <b-carousel
        id="carousel-1"
        :interval="4000"
        controls
        indicators
        background="#ababab"
        style="text-shadow: 1px 1px 2px #333"
        img-width="1024"
        img-height="480"
        @sliding-start="onSlideStart"
        @sliding-end="onSlideEnd">
        <div v-if="loaded">
          <b-carousel-slide
            class="idx"
            v-for="i in idx"
            :key="i"
            :img-src="image[i]"
            @click="moveimg(info[i])">
            <b-card>
              <b-card-text>
                <a
                  class="mt-4"
                  style="
                    font-size: 30px;
                    color: black;
                    opacity: 2;
                    text-align: center;
                  "
                  :href="info[i]"
                  >{{ title[i].substr(0, 30) }}...</a
                >
              </b-card-text>
              <b-card-text style="color: black"
                >{{ content[i].substr(0, 45) }}...</b-card-text
              >
            </b-card>
          </b-carousel-slide>
        </div>
      </b-carousel>
    </div>
  </b-container>
</template>
<script>
var axios = require("axios");
var cheerio = require("cheerio");
export default {
  name: "MainNews",
  data() {
    return {
      loaded: false,
      // slide: 0,
      // sliding: null,
      // 뉴스 제목, 내용, 이미지, 뉴스 기사 url, 인덱스
      number: "",
      title: [],
      content: [],
      image: [],
      info: [],
      idx: [0, 1, 2, 3, 4],
      ig: [],
    };
  },
  async created() {
    this.loaded = false;
    this.getData();
  },
  methods: {
    moveimg(url) {
      location.href = url;
    },
    onSlideStart() {
      this.sliding = true;
    },
    onSlideEnd() {
      this.sliding = false;
    },
    // 최근 부동산 뉴스 크롤링
    getData() {
      axios
        .get("https://realestate.daum.net/news/recent/hotissue/1155212")
        .then((res) => {
          var $ = cheerio.load(res.data);

          var number = 5;
          this.number = number;
          for (var i = 1; i <= number; i++) {
            this.title.push(
              $(
                "#mCenter > div.wrap_issue > ul > li:nth-child(" +
                  i +
                  ") > div > strong > a"
              ).text()
            );
          }
          for (var j = 1; j <= number; j++) {
            this.content.push(
              $(
                "#mCenter > div.wrap_issue > ul > li:nth-child(" +
                  j +
                  ") > div > p > a"
              ).text()
            );
          }
          for (var k = 1; k <= number; k++) {
            this.image.push(
              $(
                "#mCenter > div.wrap_issue > ul > li:nth-child(" +
                  k +
                  ") > a > img"
              ).attr("src")
            );
          }

          for (var l = 1; l <= number; l++) {
            this.info.push(
              $(
                "#mCenter > div.wrap_issue > ul > li:nth-child(" +
                  l +
                  ") > div > strong > a"
              ).attr("href")
            );
          }

          this.loaded = true;
        });
    },
  },
};
</script>
<style scoped>
/* . {
  top: 0;
  left: 0;
  min-width: 100%;
  min-height: 400px;
} */
.carousel-item {
  /* overflow: hidden; */
  /* max-width: 400px; */
  width: 100%;
  height: 400px;
  /* background-size: fill; */
  object-fit: fill;
  object-position: center center;
  background-size: contain;
}
.container {
  background-color: rgb(250, 243, 244);
  margin: 20px;
}
#__BVID__38 > img {
  opacity: 1;
}
</style>
