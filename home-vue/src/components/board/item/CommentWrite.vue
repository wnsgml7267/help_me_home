<template>
  <div class="box p-3">
    <b-input-group style="width: 240px" prepend="작성자">
      <b-form-input
        placeholder="작성자 입력 ..."
        v-model="userName"
        disabled></b-form-input>
    </b-input-group>
    <b-input-group v-if="userName === 'Guest'">
      <b-form-textarea
        placeholder="로그인 후 이용해주세요 ..."
        readonly
        rows="3"
        max-rows="6"
        v-model="comment"></b-form-textarea>
      <b-input-group-append>
        <b-button variant="secondary" @click="write">등록</b-button>
      </b-input-group-append>
    </b-input-group>

    <b-input-group v-else>
      <b-form-textarea
        placeholder="댓글 입력 ..."
        rows="3"
        max-rows="6"
        v-model="comment"></b-form-textarea>
      <b-input-group-append>
        <b-button variant="secondary" @click="write">등록</b-button>
      </b-input-group-append>
    </b-input-group>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  props: {
    articleno: Number,
  },
  data() {
    return {
      userName: "Guest",
      comment: "",
    };
  },
  mounted() {
    if (this.loginUser) {
      console.log(this.loginUser);
      this.userName = this.loginUser.id;
    }
  },
  methods: {
    ...mapActions(["createComment", "getComments"]),
    write() {
      console.log(this.articleno);
      const payload = {
        comment: {
          articleno: this.articleno,
          userName: this.userName,
          comment: this.comment,
        },

        callback: (status) => {
          console.log("callback", this);
          if (status == 200) {
            // Vuex의 Action 호출 후 서버의 응답이 왔을 때 실행

            // 기존 입력한 내용 지우기
            // this.userName = "";
            this.comment = "";

            // 댓글 목록 갱신
            this.getComments({
              articleno: this.articleno,
            });

            // Bootstrap Vue를 이용하여 메시지 출력
            this.$bvToast.toast("댓글이 등록되었습니다.", {
              title: "댓글 알림",
              variant: "success", // 색상
              toaster: "b-toaster-bottom-center", // 위치
              autoHideDelay: 3000, // 3초 뒤 사라짐
              solid: true, // 불투명처리
            });
          } else {
            // Bootstrap Vue를 이용하여 메시지 출력
            this.$bvToast.toast("서버 오류 입니다.", {
              title: "댓글 알림",
              variant: "danger", // 색상
              toaster: "b-toaster-bottom-center", // 위치
              autoHideDelay: 3000, // 3초 뒤 사라짐
              solid: true, // 불투명처리
            });
          }
        },
      };

      //this.$store.dispatch("createComment", payload);
      this.createComment(payload);
    },
  },
  computed: {
    // book() {
    //   return this.$store.getters.book;
    // },
    ...mapGetters(["article", "loginUser"]),
  },
};
</script>

<style scoped></style>
