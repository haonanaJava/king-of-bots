import { createStore } from "vuex";
import { login, getInfo as fetchUserInfo, register } from "@/api/auth";
import { setToken, saveUser, removeToken, removeUser } from "@/utils/token";
import router from "@/router/routes";
import PK from "./pk";
import record from "./record";

export const store = createStore({
  state: {
    user: {
      id: "",
      username: "",
      avatar: "",
      token: "",
      is_login: false,
    },
  },
  mutations: {
    M_user(state, data) {
      const { id, username, avatar } = data;
      state.user.id = id;
      state.user.username = username;
      state.user.avatar = avatar;
    },
    M_login(state, data) {
      state.user.is_login = data;
    },
    M_token(state, token) {
      state.user.token = token;
    },
  },
  actions: {
    login({ commit }, data) {
      return new Promise((resolve, reject) => {
        login(data)
          .then((res) => {
            if (res.status === 200) {
              setToken(res.data?.token);
              saveUser({
                id: res.data?.id,
                username: res.data?.username,
                avatar: res.data?.avatar,
              });
              commit("M_user", res.data);
              commit("M_login", true);
              commit("M_token", res.data.token);
              resolve(res);
            } else {
              reject(res);
            }
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    logout({ commit }) {
      commit("M_login", false);
      commit("M_token", "");
      commit("M_user", {});
      removeToken();
      removeUser();
      router.push("/login");
    },
    register({ commit }, data) {
      return new Promise((resolve, reject) => {
        register(data)
          .then((res) => {
            if (res.status === 200) {
              resolve(res);
            } else {
              reject(res);
            }
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    getInfo({ commit }, id) {
      return new Promise((resolve, reject) => {
        fetchUserInfo(id)
          .then((res) => {
            if (res.status === 200) {
              commit("M_user", res.data);
              commit("M_login", true);
              commit("M_token", res.data.token);
              resolve(res.data);
            } else {
              reject(res);
            }
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
  },
  modules: { pk: PK, record },
});
