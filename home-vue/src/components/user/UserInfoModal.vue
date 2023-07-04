<template>
  <!-- 회원정보 modal start -->
  <div
    class="modal fade"
    id="info"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div
        class="modal-content"
        style="
          border-radius: 30px;
          box-shadow: 0px 0px 20px 0px rgb(240, 240, 240);
        ">
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">
            <i class="bi bi-chat-left-dots-fill text-info"> 회원정보</i>
          </h4>
          <button
            type="button"
            class="close"
            data-dismiss="modal"
            aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>

        <!-- Modal body : 정보만 출력 -->
        <div class="modal-body" id="userInfoDiv">
          <div class="mb-3">
            <i class="bi bi-patch-question" style="color: rgb(121, 2, 119)"></i>
            <label for="question" class="form-label font-italic"
              >아이디를 확인해주세요.</label
            >
            <input
              id="infoId"
              type="text"
              class="form-control"
              readonly="readonly"
              v-model="id" />
          </div>
          <div class="mb-3">
            <i class="bi bi-patch-question" style="color: rgb(121, 2, 119)"></i>
            <label for="question" class="form-label font-italic"
              >비밀번호를 확인해주세요.</label
            >
            <input
              id="infoPw"
              type="text"
              class="form-control"
              readonly="readonly"
              v-model="pw" />
          </div>
          <div class="mb-3">
            <i class="bi bi-patch-question" style="color: rgb(121, 2, 119)"></i>
            <label for="question" class="form-label font-italic"
              >이름을 확인해주세요.</label
            >
            <input
              id="infoName"
              type="text"
              class="form-control"
              readonly="readonly"
              v-model="name" />
          </div>
          <div class="mb-3">
            <i class="bi bi-patch-question" style="color: rgb(121, 2, 119)"></i>
            <label for="question" class="form-label font-italic"
              >주소를 확인해주세요.</label
            >
            <input
              id="infoAddr"
              type="text"
              class="form-control"
              readonly="readonly"
              v-model="addr" />
          </div>
          <div class="mb-3">
            <i class="bi bi-patch-question" style="color: rgb(121, 2, 119)"></i>
            <label for="question" class="form-label font-italic"
              >전화번호를 확인해주세요.</label
            >
            <input
              id="infoPhone"
              type="text"
              class="form-control"
              readonly="readonly"
              v-model="phone" />
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <b-button
              class="pb-2; lg=4"
              variant="outline-dark"
              @click="closeModal()">
              확인
            </b-button>

            <b-button
              class="pb-2; lg=4"
              variant="outline-dark"
              @click="goModifyInfo()">
              수정
            </b-button>

            <b-button
              class="pb-2; lg=4"
              variant="outline-dark"
              @click="outInfo()">
              탈퇴
            </b-button>
          </div>
        </div>
        <!-- Modal body : 수정 가능한 유저 정보 -->
        <div class="modal-body" style="display: none" id="userInfoUpdate">
          <input type="hidden" name="cmd" value="updateInfo" />

          <div class="mb-3">
            <i class="bi bi-patch-question" style="color: rgb(121, 2, 119)"></i>
            <label for="question" class="form-label font-italic"
              >아이디를 확인해주세요.</label
            >
            <input
              type="text"
              class="form-control"
              id="updateId"
              name="signId"
              readonly="readonly"
              v-model="id" />
          </div>
          <div class="mb-3">
            <i class="bi bi-patch-question" style="color: rgb(121, 2, 119)"></i>
            <label for="question" class="form-label font-italic"
              >비밀번호를 수정하시겠습니까?</label
            >
            <input
              type="password"
              class="form-control"
              id="updatePw"
              placeholder="영문, 숫자 포함 6자리 이상"
              name="signPw"
              v-model="pw" />
          </div>
          <div class="mb-3">
            <i class="bi bi-patch-question" style="color: rgb(121, 2, 119)"></i>
            <label for="question" class="form-label font-italic"
              >이름을 수정하시겠습니까?</label
            >
            <input
              type="text"
              class="form-control"
              id="updateName"
              placeholder="User name"
              name="signName"
              v-model="name" />
          </div>
          <div class="mb-3">
            <i class="bi bi-patch-question" style="color: rgb(121, 2, 119)"></i>
            <label for="question" class="form-label font-italic"
              >주소를 수정하시겠습니까?</label
            >
            <input
              type="text"
              class="form-control"
              id="updateAddr"
              placeholder="Address"
              name="signAddr"
              v-model="addr" />
          </div>
          <div class="mb-3">
            <i class="bi bi-patch-question" style="color: rgb(121, 2, 119)"></i>
            <label for="question" class="form-label font-italic"
              >전화번호를 수정하시겠습니까?</label
            >
            <input
              type="text"
              class="form-control"
              id="updatePhone"
              placeholder="010-xxxx-xxxx"
              name="signPhone"
              v-model="phone" />
          </div>

          <div class="modal-footer">
            <b-button
              class="pb-2; lg=4"
              variant="outline-dark"
              id="btn-info-update"
              @click="modifyInfo()">
              수정
            </b-button>
            <b-button
              class="pb-2; lg=4"
              variant="outline-dark"
              id="btn-info-cancel"
              @click="goInfo()">
              취소
            </b-button>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- <user-info-update-modal></user-info-update-modal> -->
</template>

<script>
// import UserInfoUpdateModal from './UserInfoUpdateModal.vue';
import http from "@/api/http";
export default {
  name: "UserInfoModal",
  components: {},
  data() {
    return {
      id: "",
      pw: "",
      name: "",
      addr: "",
      phone: "",
    };
  },
  created() {
    let loginUser = JSON.parse(sessionStorage.getItem("loginUser"));
    console.log("현재 login user: " + loginUser);
    // console.log(typeof(loginUser))
    if (loginUser != null) {
      this.id = loginUser.id;
      this.pw = loginUser.pw;
      this.name = loginUser.name;
      this.addr = loginUser.addr;
      this.phone = loginUser.phone;
    }
  },
  methods: {
    closeModal() {
      location.reload();
    },
    goModifyInfo() {
      document
        .querySelector(`#userInfoDiv`)
        .setAttribute("style", "display: none;");
      document
        .querySelector(`#userInfoUpdate`)
        .setAttribute("style", "display: ;");
    },
    goInfo() {
      document
        .querySelector(`#userInfoUpdate`)
        .setAttribute("style", "display: none;");
      document
        .querySelector(`#userInfoDiv`)
        .setAttribute("style", "display: ;");
    },
    outInfo() {
      http.delete(`/user/${this.id}`).then((response) => {
        switch (response.status) {
          case 200: //탈퇴성공
            alert("탈퇴 성공!!!");
            this.logout();
            break;
          case 204:
            throw new Error("회원탈퇴 실패!!");
          case 500:
            throw new Error("회원탈퇴 실패!!");
        }
      });
    },
    logout() {
      sessionStorage.clear();
      location.reload();
    },
    modifyInfo() {
      const user = {
        id: this.id,
        pw: this.pw,
        name: this.name,
        addr: this.addr,
        phone: this.phone,
      };
      http.put(`/user/${this.id}`, user).then((response) => {
        switch (response.status) {
          case 200:
            //세션에 정보 업데이트
            sessionStorage.clear();
            console.log("newUser");
            console.log(user);
            sessionStorage.setItem("loginUser", JSON.stringify(user));
            alert("회원정보 수정 성공!!");
            document
              .querySelector(`#userInfoDiv`)
              .setAttribute("style", "display: ;");
            document
              .querySelector(`#userInfoUpdate`)
              .setAttribute("style", "display: none ;");

            return true;
          case 204:
            alert("회원정보 수정  실패!!");
            return false;
          case 500:
            throw new Error("회원정보 수정 실패!!");
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
