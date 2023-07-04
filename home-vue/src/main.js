import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import "@/api/fontAwesomeIcon.js"; // fontAwesomeIcon.js 불러옴
import "@/api/vueBootstrap.js";

//jquery
// import 'expose-loader?$!expose-loader?jQuery!jquery'
import 'bootstrap'

import vuetify from './plugins/vuetify'

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
