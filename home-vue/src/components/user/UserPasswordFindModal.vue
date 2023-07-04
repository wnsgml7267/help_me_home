<template>
  <!-- 비밀번호찾기 modal start -->
  <div
    class="modal fade"
    id="findPw"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">
            <i class="bi bi-chat-left-dots-fill text-info"> 비밀번호찾기</i>
          </h4>
          <button
            type="button"
            class="close"
            data-dismiss="modal"
            aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>

        <!-- Modal body -->
        <div class="modal-body">
          <form action="${root }/user" method="post">
            <input type="hidden" name="cmd" value="findPw" />
            <div class="mb-3">
              <i
                class="bi bi-patch-question"
                style="color: rgb(121, 2, 119)"></i>
              <label for="question" class="form-label font-italic"
                >아이디를 입력해주세요.</label
              >
              <input
                type="text"
                class="form-control"
                placeholder="ID"
                name="signId" />
            </div>
            <div class="mb-3">
              <i
                class="bi bi-patch-question"
                style="color: rgb(121, 2, 119)"></i>
              <label for="question" class="form-label font-italic"
                >이름을 입력해주세요.</label
              >
              <input
                type="text"
                class="form-control"
                placeholder="User name"
                name="signName" />
            </div>
            <div class="mb-3">
              <i
                class="bi bi-patch-question"
                style="color: rgb(121, 2, 119)"></i>
              <label for="question" class="form-label font-italic"
                >전화번호를 입력해주세요.</label
              >
              <input
                type="text"
                class="form-control"
                placeholder="010-xxxx-xxxx"
                name="signPhone" />
            </div>

            <div class="modal-footer">
              <b-button
                class="pb-2; lg=4"
                variant="outline-dark"
                type="submit"
                id="btn-info-search">
                찾기
              </b-button>
            </div>
          </form>
        </div>
        <!-- Modal footer -->
      </div>
    </div>
  </div>
  <!-- 비밀번호찾기 modal end -->
</template>

<script>
export default {
  name: "UserPasswordFindModal",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
.form-control {
  width: 450px;
  margin: auto;
}
.form-label {
  position: relative;
  left: 60px;
  text-align: left;
  float: left;
}
.modal-content {
  border-radius: 30px;
  box-shadow: 0px 0px 20px 0px rgb(240, 240, 240);
  width: 600px;
  margin: auto;
}
</style>
