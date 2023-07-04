<template>
  <div
    class="modal fade"
    id="login"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">
            <i class="bi bi-chat-left-dots-fill text-info"> 로그인</i>
          </h4>
          <button
            type="button"
            class="close"
            data-dismiss="modal"
            aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form action="${root }/user" method="post">
            <input
              type="hidden"
              name="cmd"
              value="login"
              style="margin: auto" />
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
                id="loginId"
                name="id"
                v-model="loginId" />
            </div>
            <div class="mb-3">
              <i
                class="bi bi-patch-question"
                style="color: rgb(121, 2, 119)"></i>
              <label for="question" class="form-label font-italic"
                >비밀번호를 입력해주세요.</label
              >
              <input
                type="password"
                class="form-control"
                id="loginPw"
                placeholder="영문, 숫자 포함 6자리 이상"
                name="pw"
                v-model="loginPw" />
            </div>

            <div class="modal-footer">
              <b-button
                class="pb-2; lg=4"
                variant="outline-dark"
                id="btn-login"
                @click="login()">
                로그인
              </b-button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapActions } from "vuex";

export default {
  name: "LoginModal",
  components: {},
  data() {
    return {
      loginId: "",
      loginPw: "",
      modalShow: false,
    };
  },
  created() {},
  methods: {
    ...mapActions(["setLoginUser"]),
    login() {
      const vueThis = this;
      http.get(`/user/${this.loginId}`).then(({ data }) => {
        console.log(data);

        if (data != null) {
          if (data.pw == this.loginPw) {
            //로그인 성공 : 세션 처리
            sessionStorage.setItem("loginUser", JSON.stringify(data));
            console.log(data);
            vueThis.setLoginUser(data);
            location.reload();
            // $('#login').modal('hide');
          } else {
            //로그인 실패 : 비밀번호 틀림
            alert("로그인 실패! 비밀번호가 틀렸습니다");
          }
        } else {
          //로그인 실패 : 회원 정보 없음
          alert("로그인 실패!");
        }
      });
    },
  },
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
