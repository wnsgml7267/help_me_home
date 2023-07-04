<template>
  <b-container>
    <b-card title="실시간 날씨">
      <div class="scroll_box">
        <table class="table">
          <tr class="detail">
            <th>온도</th>
            <td
              class="temperature"
              v-for="(Temp, i) in Temperature"
              :key="i">
              {{ Temp }}
            </td>
          </tr>
          <tr class="detail">
            <th>날씨</th>
            <td
              class="weather"
              v-for="(index, i) in idx"
              :key="i"
              :class="weather_img[index]">
              {{ weather[index] }}
            </td>
          </tr>
          <tr class="detail">
            <th>시간</th>
            <td class="time" v-for="(t, i) in time" :key="i">
              {{ t }}
            </td>
          </tr>
        </table>
      </div>
    </b-card>
  </b-container>
</template>

<script>
var axios = require("axios");
var cheerio = require("cheerio");
export default {
  name: "MainWeather",
  data() {
    return {
      // 날씨 개수, 날씨 개수(인덱스), 온도, 날씨, 날씨 이미지, 시간
      number: "",
      idx: [],
      Temperature: [],
      weather: [],
      weather_img: [],
      time: [],
    };
  },
  created() {
    this.getData();
  },
  methods: {
    getData() {
      axios
        .get(
          "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EB%B6%80%EC%82%B0+%EB%AA%85%EC%A7%80%EB%8F%99+%EB%82%A0%EC%94%A8&oquery=%EB%B6%80%EC%82%B0+%EB%AA%85%EC%A7%801%EB%8F%99+%EB%82%A0%EC%94%A8&tqi=ibSGbwp0J1sssEqSblKssssstvZ-491870"
        )
        .then((res) => {
          var $ = cheerio.load(res.data);

          var number = $(
            "#main_pack > section.sc_new.cs_weather_new._cs_weather > div._tab_flicking > div.content_wrap > div.open > div:nth-child(2) > div > div > div:nth-child(3) > div > div > div > div.graph_inner._hourly_weather > ul > li"
          ).length;
          this.number = number;

          for (var i = 1; i <= number; i++) {
            this.Temperature.push(
              $(
                "#main_pack > section.sc_new.cs_weather_new._cs_weather > div._tab_flicking > div.content_wrap > div.open > div:nth-child(2) > div > div > div:nth-child(3) > div > div > div > div.graph_inner._hourly_weather > ul > li:nth-child(" +
                  i +
                  ") > dl > dd.degree_point > div > div > span"
              ).text()
            );
          }

          for (var j = 1; j <= number; j++) {
            this.weather.push(
              $(
                "#main_pack > section.sc_new.cs_weather_new._cs_weather > div._tab_flicking > div.content_wrap > div.open > div:nth-child(2) > div > div > div:nth-child(3) > div > div > div > div.graph_inner._hourly_weather > ul > li:nth-child(" +
                  j +
                  ") > dl > dd.weather_box > i > span"
              ).text()
            );
          }
          for (var k = 1; k <= number; k++) {
            this.time.push(
              $(
                "#main_pack > section.sc_new.cs_weather_new._cs_weather > div._tab_flicking > div.content_wrap > div.open > div:nth-child(2) > div > div > div:nth-child(3) > div > div > div > div.graph_inner._hourly_weather > ul > li:nth-child(" +
                  k +
                  ") > dl > dt > em"
              ).text()
            );
          }
          for (var l = 1; l <= number; l++) {
            this.weather_img.push(
              $(
                "#main_pack > section.sc_new.cs_weather_new._cs_weather > div._tab_flicking > div.content_wrap > div.open > div:nth-child(2) > div > div > div:nth-child(3) > div > div > div > div.graph_inner._hourly_weather > ul > li:nth-child(" +
                  l +
                  ") > dl > dd.weather_box > i"
              ).attr("class")
            );
          }
          for (var m = 0; m < number; m++) {
            this.idx.push(m);
          }
          // console.log(this.weather);
        });
    },
  },
};
</script>

<style scoped>
.detail {
  display: flex;
  flex-direction: row;
}
.temperature,
.weather,
.time {
  /* width: 12.5%; */
  padding-top: 20px;
}

.scroll_box {
  overflow: hidden;
  overflow-x: scroll;
  width: 445px;
  height: 230px;
  -ms-overflow-style: none;
  scrollbar-width: none;
  margin: 0 10px;
  padding: 15px 20px;
  background-color: #fff;
  -webkit-box-shadow: 0 0 6px 0 rgba(0, 0, 0, 0.07);
  -moz-box-shadow: 0 0 6px 0 rgba(0, 0, 0, 0.07);
  -ms-box-shadow: 0 0 6px 0 rgba(0, 0, 0, 0.07);
  box-shadow: 0 0 6px 0 rgba(0, 0, 0, 0.07);
  -webkit-border-radius: 8px;
  -moz--border-radius: 8px;
  -ms-border-radius: 8px;
  border-radius: 8px;
}

td,
th {
  width: 100px;
}
.wt_icon.ico_wt1 {
  background-image: url(https://ssl.pstatic.net/sstatic/keypage/outside/scui/weather_new_new/img/weather_svg_v2/icon_flat_wt1.svg);
}
.wt_icon.ico_wt2 {
  background-image: url(https://ssl.pstatic.net/sstatic/keypage/outside/scui/weather_new_new/img/weather_svg_v2/icon_flat_wt2.svg);
}
.wt_icon.ico_wt3 {
  background-image: url(https://ssl.pstatic.net/sstatic/keypage/outside/scui/weather_new_new/img/weather_svg_v2/icon_flat_wt3.svg);
}
.wt_icon.ico_wt4 {
  background-image: url(https://ssl.pstatic.net/sstatic/keypage/outside/scui/weather_new_new/img/weather_svg_v2/icon_flat_wt4.svg);
}
.wt_icon.ico_wt5 {
  background-image: url(https://ssl.pstatic.net/sstatic/keypage/outside/scui/weather_new_new/img/weather_svg_v2/icon_flat_wt5.svg);
}
.wt_icon.ico_wt6 {
  background-image: url(https://ssl.pstatic.net/sstatic/keypage/outside/scui/weather_new_new/img/weather_svg_v2/icon_flat_wt6.svg);
}
.wt_icon.ico_wt7 {
  background-image: url(https://ssl.pstatic.net/sstatic/keypage/outside/scui/weather_new_new/img/weather_svg_v2/icon_flat_wt7.svg);
}
.wt_icon.ico_wt8 {
  background-image: url(https://ssl.pstatic.net/sstatic/keypage/outside/scui/weather_new_new/img/weather_svg_v2/icon_flat_wt8.svg);
}
.wt_icon.ico_wt9 {
  background-image: url(https://ssl.pstatic.net/sstatic/keypage/outside/scui/weather_new_new/img/weather_svg_v2/icon_flat_wt9.svg);
}
.wt_icon.ico_wt10 {
  background-image: url(https://ssl.pstatic.net/sstatic/keypage/outside/scui/weather_new_new/img/weather_svg_v2/icon_flat_wt10.svg);
}
.wt_icon {
  /* display: inline-block;
  vertical-align: top;
  width: 34px;
  height: 34px; */
  background-repeat: no-repeat;
  background-size: 34px 34px;
  -webkit-background-size: 34px 34px;
}
</style>
