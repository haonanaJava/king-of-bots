import { createRouter, createWebHistory } from "vue-router";
import { getToken } from "@/utils/token";
import { store } from "@/store";
import { getUser } from "../utils/token";

const routes = [
  {
    path: "/",
    name: "Home",
    redirect: "/pk",
  },
  {
    path: "/login",
    name: "Login",
    component: () => import("@/views/user/Login.vue"),
  },
  {
    path: "/bot",
    name: "Bot",
    component: () => import("@/views/user/bot/index.vue"),
  },
  {
    path: "/register",
    name: "Register",
    component: () => import("@/views/user/Register.vue"),
  },
  {
    path: "/pk",
    name: "pk",
    component: () => import("@/views/pk/index.vue"),
  },
  {
    path: "/rankList",
    name: "rankList",
    component: () => import("@/views/rankList/index.vue"),
  },
  {
    path: "/record",
    name: "record",
    component: () => import("@/views/record/index.vue"),
  },
  {
    path: "/videotape/:recordId",
    name: "videotape",
    component: () => import("@/views/record/RecordVideotape.vue"),
  },
  {
    path: "/:pathMatch(.*)*",
    name: "404",
    component: () => import("@/views/error/404.vue"),
  },
];

const router = createRouter({
  routes,
  history: createWebHistory(),
});

const whiteList = ["/login", "/404", "/401", "/register"]; // no redirect whitelist
// 拦截路由，判断是否登录，如果没有登录，跳转到登录页面
router.beforeEach((to, from, next) => {
  const hasToken = getToken();
  if (hasToken) {
    if (to.path === "/login") {
      next({ path: "/" });
    } else {
      const hasUser = getUser();
      if (hasUser) {
        store.commit("M_user", hasUser);
        store.commit("M_login", true);
        store.commit("M_token", hasToken);
        next();
      } else {
        store
          .dispatch("getInfo")
          .then(() => {
            next();
          })
          .catch(() => {
            next({ path: "/login" });
          });
      }
    }
  } else {
    if (whiteList.indexOf(to.path) !== -1) {
      next();
    } else {
      next(`/login?redirect=${to.path}`);
    }
  }
});

export default router;
