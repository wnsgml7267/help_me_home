<template>
  <b-list-group-item class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">{{ input.userName }}</h5>
      <small class="text-muted">{{ input.commentTime }}</small>
    </div>

    <p :inner-html.prop="input.comment | enterToBr"></p>

    <div
      v-if="
        loginUser &&
        (loginUser.id === input.userName || loginUser.id === 'admin')
      "
      style="display: "
      class="text-right">
      <b-button-group>
        <b-button variant="secondary" @click="showModalModify">수정</b-button>
        <b-button variant="outline-secondary" @click="showModalDelete"
          >삭제</b-button
        >
      </b-button-group>
    </div>

    <div v-else style="display: none" class="text-right">
      <b-button-group>
        <b-button variant="secondary" @click="showModalModify">수정</b-button>
        <b-button variant="outline-secondary" @click="showModalDelete"
          >삭제</b-button
        >
      </b-button-group>
    </div>

    <!-- 수정 모달 창 작성 -->
    <b-modal
      :ref="`comment-${input.commentNo}`"
      title="댓글 수정"
      header-bg-variant="dark"
      header-text-variant="light"
      centered
      hide-footer>
      <!-- 수정 모달 창 Body 작성 -->
      <div>
        <b-input-group style="width: 240px" prepend="작성자">
          <b-form-input
            placeholder="작성자 입력 ..."
            readonly
            v-model="input.userName"></b-form-input>
        </b-input-group>
        <b-form-textarea
          placeholder="댓글 입력 ..."
          rows="3"
          max-rows="6"
          v-model="input.comment"></b-form-textarea>
      </div>

      <!-- 수정 모달 창 Footer 작성 -->
      <div class="text-right">
        <b-button-group>
          <b-button variant="outline-secondary">취소</b-button>
          <b-button variant="secondary" @click="modify">수정</b-button>
        </b-button-group>
      </div>
    </b-modal>
  </b-list-group-item>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  props: {
    articleno: Number,
    comment: Object,
  },
  methods: {
    ...mapActions(["modifyComment", "getComments", "deleteComment"]),

    showModalModify() {
      this.$refs[`comment-${this.input.commentNo}`].show();
    },
    hideModalModify() {
      this.$refs[`comment-${this.input.commentNo}`].hide();
    },
    modify() {
      const payload = {
        comment: {
          commentNo: this.input.commentNo,
          comment: this.input.comment,
        },
        callback: () => {
          // 수정한 내용 지우기
          this.input.userName = "";
          this.input.comment = "";

          // 모달 창 닫기
          this.hideModalModify();

          // 댓글 목록 갱신하기
          this.getComments({
            articleno: this.articleno,
          });

          // 수정 완료 Toast 출력
          this.$bvToast.toast("댓글이 수정되었습니다.", {
            title: "댓글 알림",
            variant: "success",
            toaster: "b-toaster-bottom-center",
            autoHideDelay: 3000,
            solid: true,
          });
        },
      };

      this.modifyComment(payload);
    },
    showModalDelete() {
      this.$bvModal
        .msgBoxConfirm("정말로 삭제 하시겠습니까?", {
          centered: true,
        })
        .then((value) => {
          console.log(value);
          if (value) {
            this.delete();
          }
        });
    },
    delete() {
      const payload = {
        commentNo: this.input.commentNo,
        callback: (status) => {
          if (status == 200) {
            // 댓글 목록 갱신
            this.getComments({
              articleno: this.articleno,
            });

            // 삭제 완료 Toast 출력
            setTimeout(() => {
              this.$bvToast.toast("댓글이 삭제되었습니다.", {
                title: "댓글 알림",
                variant: "success",
                toaster: "b-toaster-bottom-center",
                autoHideDelay: 3000,
                solid: true,
              });
            }, 500);
          } else if (status == 500) {
            // 서버 오류 Toast 출력
            this.$bvToast.toast("서버 오류 입니다.", {
              title: "댓글 알림",
              variant: "success",
              toaster: "b-toaster-bottom-center",
              autoHideDelay: 3000,
              solid: true,
            });
          }
        },
      };

      this.deleteComment(payload);
    },
  },
  computed: {
    input() {
      return { ...this.comment }; // 객체 복사 (props는 수정이 불가능하니까)
    },
    ...mapGetters(["loginUser"]),
  },
};
</script>
