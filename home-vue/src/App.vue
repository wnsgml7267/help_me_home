<template>
  <div id="app">
    <the-header-navbar></the-header-navbar>
    <div class="content">
      <router-view />
    </div>
    <the-footer></the-footer>
  </div>
</template>

<script>
import TheHeaderNavbar from "./components/common/TheHeaderNavbar.vue";
import TheFooter from "./components/common/TheFooter.vue";
export default {
  name: "App",
  components: {
    TheHeaderNavbar,
    TheFooter,
  },
  created() {
    var scripts = [
      "https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js",
      // "https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js",
    ];
    scripts.forEach((script) => {
      let tag = document.createElement("script");
      tag.setAttribute("src", script);
      document.head.appendChild(tag);
    });
  },
  data() {
    return {
      message: "",
    };
  },
};
</script>

<style>
#app {
  font-family: 'SUITE-Regular';
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;

  background-image: url("@/assets/header-bg.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: top;
  background-size: 100% 40%;
}

nav {
  /* padding: 30px; */
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link {
  font-weight: bold;
  color: #2c3e50;
}

.content {
  margin: 90px 100px;
  padding: 30px;
  background-color: white;
  border-radius: 1em;
  box-shadow: 0px 0px 10px 0px rgb(240, 240, 240);
}
@font-face {
    font-family: 'SUITE-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-2@1.0/SUITE-Regular.woff2') format('woff2');
    font-weight: 400;
    font-style: normal;
}
</style>
