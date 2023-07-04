<template>
  <div>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <!-- <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
      crossorigin="anonymous"></script> -->
    <!-- Google fonts-->
    <link
      href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
      rel="stylesheet"
      type="text/css" />
    <link
      href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700"
      rel="stylesheet"
      type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <!-- <link href="@/assets/css/styles.css" rel="stylesheet" /> -->
    <!-- <link rel="shortcut icon" href="./assets/img/favicon.ico" />
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
        crossorigin="anonymous" />
    <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" /> -->

    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
      integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
      crossorigin="anonymous" />

    <!--네비게이션-->
    <b-navbar
      toggleable="lg"
      type="light"
      class="fixed-top"
      variant=""
      id="navbar"
      style="
        /* height: 50px; */
        border: ;
        background-color: rgba(255, 255, 255, 0.8);
      ">
      <b-navbar-brand>
        <router-link :to="{ name: 'main' }" class="nav-link">
          <!-- <img src="https://www.flaticon.com/free-icons/real-estate.png" class="d-inline-block align-top" alt="home"> -->
          <b-icon icon="house" aria-hidden="true"></b-icon>
          구해줘 HOME
        </router-link>
      </b-navbar-brand>

      <!--토글 버튼-->
      <b-navbar-toggle target="nav-text-collapse">
        <template #default="{ expanded }">
          <b-icon v-if="expanded" icon="chevron-bar-up"></b-icon>
          <b-icon v-else icon="chevron-bar-down"></b-icon>
        </template>
      </b-navbar-toggle>

      <!--토글 목록-->
      <!-- Right aligned nav items -->
      <b-collapse id="nav-text-collapse" is-nav>
        <!--비회원-->
        <div id="noUserStorage" style="display: " class="ml-auto">
          <b-navbar-nav>
            <!--부동산 정보 및 게시판 라우터 링크-->
            <b-navbar-brand style="margin: auto"
              ><b-icon icon="house" aria-hidden="true"></b-icon
            ></b-navbar-brand>
            <b-nav-text
              ><router-link :to="{ name: 'house' }" class="nav-link"
                >부동산</router-link
              ></b-nav-text
            >
            <b-nav-text
              ><router-link :to="{ name: 'board' }" class="nav-link"
                >게시판</router-link
              ></b-nav-text
            >
            <!--회원-->
            <b-navbar-brand style="margin: auto"
              ><b-icon icon="person" aria-hidden="true"></b-icon
            ></b-navbar-brand>
            <b-nav-text
              ><a
                class="nav-link"
                href="#"
                data-toggle="modal"
                data-target="#userInModal"
                >회원가입</a
              ></b-nav-text
            >
            <b-nav-text
              ><a
                class="nav-link"
                href="#"
                data-toggle="modal"
                data-target="#login"
                >로그인</a
              ></b-nav-text
            >
            <b-nav-text
              ><a
                class="nav-link"
                href="#"
                data-toggle="modal"
                data-target="#findPw"
                >비밀번호찾기</a
              ></b-nav-text
            >
          </b-navbar-nav>
        </div>

        <!--회원-->
        <div id="UserStorage" style="display: none" class="ml-auto">
          <b-navbar-nav>
            <!--부동산 정보 및 게시판 라우터 링크-->
            <b-navbar-brand style="margin: auto"
              ><b-icon icon="house" aria-hidden="true"></b-icon
            ></b-navbar-brand>
            <b-nav-text
              ><router-link :to="{ name: 'house' }" class="nav-link"
                >부동산</router-link
              ></b-nav-text
            >
            <b-nav-text
              ><router-link :to="{ name: 'board' }" class="nav-link"
                >게시판</router-link
              ></b-nav-text
            >
            <!--회원-->
            <b-navbar-brand style="margin: auto"
              ><b-icon icon="person" aria-hidden="true"></b-icon
            ></b-navbar-brand>
            <b-nav-text
              ><a
                class="nav-link"
                href="#"
                data-toggle="modal"
                data-target="#info"
                >회원정보</a
              ></b-nav-text
            >
            <b-nav-text
              ><a class="nav-link" href="#" @click="logout()"
                >로그아웃</a
              ></b-nav-text
            >
          </b-navbar-nav>
        </div>
        <!-- 관리자 기능
                <li class="nav-item"><a class="nav-link"
                  href="${root}/user?cmd=goAdminPage">회원정보관리</a></li>
                <li class="nav-item"><a class="nav-link"
                  href="${root}/home?cmd=goAdminPage">부동산정보관리</a></li>  -->
      </b-collapse>
    </b-navbar>

    <!-- Masthead-->
    <header class="masthead"></header>

    <!--modal-->
    <login-modal></login-modal>
    <user-in-modal></user-in-modal>
    <user-password-find-modal></user-password-find-modal>
    <user-info-modal></user-info-modal>
  </div>
</template>

<script
  src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
  integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
  crossorigin="anonymous"></script>
<script
  src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
  crossorigin="anonymous"></script>
<script>
import LoginModal from "@/components/user/LoginModal";
import UserInModal from "@/components/user/UserInModal.vue";
import UserPasswordFindModal from "../user/UserPasswordFindModal.vue";
import UserInfoModal from "../user/UserInfoModal.vue";
import { mapActions } from "vuex";
// import * as bootstrap from 'bootstrap';
// import $ from 'jquery';

export default {
  name: "TheHeader",
  components: {
    LoginModal,
    UserInModal,
    UserPasswordFindModal,
    UserInfoModal,
  },
  data() {
    return {
      message: "",
    };
  },
  mounted() {
    //상단바
    let loginUser = sessionStorage.getItem("loginUser");
    if (loginUser == null) {
      console.log(loginUser);
      this.showNoUserStorage();
    } else {
      console.log(loginUser);
      this.showUserStorage();
    }
  },
  methods: {
    ...mapActions(["setLoginUser"]),
    showNoUserStorage() {
      document
        .querySelector("#noUserStorage")
        .setAttribute("style", "display: ;");
      document
        .querySelector("#UserStorage")
        .setAttribute("style", "display: none;");
    },

    showUserStorage() {
      document
        .querySelector("#noUserStorage")
        .setAttribute("style", "display: none ;");
      document
        .querySelector("#UserStorage")
        .setAttribute("style", "display: ;");
    },
    logout() {
      this.setLoginUser({
        id: "Guest",
      });
      sessionStorage.clear();
      location.reload();
    },
  },
};
</script>

<style scoped>
header.masthead {
  padding: 100px;
  text-align: center;
  color: #fff;
}

#navbar {
  box-shadow: 0px 0px 20px 0px rgb(240, 240, 240);
}
</style>
