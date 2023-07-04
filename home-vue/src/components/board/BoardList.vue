<template>
  <b-container class="bv-example-row mt-3">
    <div class="col-md-7 offset-3 mb-2">
      <form class="d-flex" id="form-search" action="">
        <input type="hidden" name="action" value="list" />
        <input type="hidden" name="pgno" value="1" />
        <select
          name="key"
          id="key"
          class="form-select form-select-sm ms-5 me-1 w-50"
          aria-label="검색조건">
          <option selected>검색조건</option>
          <option value="article_no">글번호</option>
          <option value="subject">제목</option>
          <option value="user_id">작성자</option>
        </select>
        <div class="input-group input-group-sm">
          <div>
            <input
              type="text"
              name="word"
              id="word"
              class="form-control"
              placeholder="검색어..." />
          </div>
          <div>
            <button id="btn-search" class="btn btn-dark" type="button">
              검색
            </button>
          </div>
        </div>
      </form>
    </div>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          striped
          hover
          :items="articles"
          :fields="fields"
          @row-clicked="viewArticle">
          <template #cell(subject)="data">
            <router-link
              :to="{
                name: 'boardview',
                params: { articleno: data.item.articleno },
              }">
              {{ data.item.subject }}
            </router-link>
          </template>
        </b-table>
      </b-col>
    </b-row>
    <div class="col-md-7 offset-4 mb-2">
      <div class="mt-3">
        <b-pagination size="lg"></b-pagination>
      </div>
    </div>
  </b-container>
</template>

<script>
import http from "@/api/http";
import { mapGetters } from "vuex";

export default {
  name: "BoardList",
  data() {
    return {
      articles: [],
      fields: [
        { key: "articleno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    http.get(`/board`).then(({ data }) => {
      this.articles = data;
    });
  },
  methods: {
    moveWrite() {
      console.log("list: ", this.loginUser);
      this.$router.push({ name: "boardwrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "boardview",
        params: { articleno: article.articleno },
      });
    },
    postSearch() {},
  },
  computed: {
    ...mapGetters(["loginUser"]),
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
