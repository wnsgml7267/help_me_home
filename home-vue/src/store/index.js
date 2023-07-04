import Vue from "vue";
import Vuex from "vuex";
import houseStore from "./modules/houseStore";
import boardStore from "./modules/boardStore";
import commentStore from "./modules/commentStore";
import userStore from "./modules/userStore";
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    houseStore: houseStore,
    boardStore: boardStore,
    commentStore: commentStore,
    userStore: userStore,
  },
  plugins: [
    createPersistedState()
  ]
});
