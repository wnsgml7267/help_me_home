<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="moveList">목록</b-button>
      </b-col>
      <b-col
        class="text-right"
        v-if="article.userid === loginUser.id || loginUser.id === 'admin'">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.articleno}.
          ${article.subject} [${article.hit}]</h3><div><h6>${article.userid}</div><div>${article.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body>
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <comment-write :articleno="article.articleno"></comment-write>
    <b-list-group>
      <comment-row
        v-for="comment in comments"
        :key="comment.commentNo"
        :articleno="article.articleno"
        :comment="comment"></comment-row>
    </b-list-group>
  </b-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import http from "@/api/http";

export default {
  components: {
    "comment-write": () => import("@/components/board/item/CommentWrite"),
    "comment-row": () => import("@/components/board/item/CommentRow"),
  },

  name: "BoardDetail",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapGetters(["comments", "loginUser"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    http.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
      this.article = data;
      console.log(this.article);
      //해당 글을 불러오면, 해당 글의 댓글들을 불러옴
      this.getComments({
        articleno: this.article.articleno,
      });
    });
    // this.getBoard({
    //   articleno,
    // });
  },
  methods: {
    ...mapActions(["getComments"]),
    moveModifyArticle() {
      this.$router.replace({
        name: "boardmodify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "boarddelete",
          params: { articleno: this.article.articleno },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
};
</script>

<style></style>
