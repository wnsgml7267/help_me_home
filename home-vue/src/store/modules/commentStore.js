import http from "@/api/http";
export default {
  state: {
    articles: [],
    article: {},
    comments: [],
  },
  getters: {
    comments(state) {
      return state.comments;
    },
    articles(state) {
      return state.articles;
    },
    article(state) {
      return state.article;
    },
  },
  mutations: {
    COMMENTS(state, payload) {
      state.comments = payload.comments;
    },

    ARTICLE(state, payload) {
      state.article = payload.article;
    },
  },

  actions: {
    getBoard(context, payload) {
      http.get(`/board/${payload.articleno}`).then((response) => {
        context.commit({
          type: "ARTICLE",
          book: response.data,
        });
      });
    },
    deleteComment(context, payload) {
      http
        .delete(`/comment/${payload.commentNo}`)
        .then((response) => {
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
    modifyComment(context, payload) {
      http
        .put(`/comment/${payload.comment.commentNo}`, payload.comment)
        .then((response) => {
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
    getComments({ commit }, payload) {
      http.get(`/comment/${payload.articleno}`).then((response) => {
        commit({
          type: "COMMENTS",
          comments: response.data,
        });
      });
    },
    createComment(context, payload) {
      http
        .post("/comment", payload.comment)
        .then((response) => {
          console.log(response);
          payload.callback(response.status);
        })
        .catch((response) => {
          payload.callback(response.status);
        });
    },
  },
  modules: {},
  plugins: [],
};
