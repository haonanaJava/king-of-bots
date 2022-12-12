<template>
  <a-menu v-model:selectedKeys="current" mode="horizontal" theme="dark">
    <a-menu-item key="pk">
      <Link class="nav-link" to="/pk">对战</Link>
    </a-menu-item>
    <a-menu-item key="record">
      <Link class="nav-link" to="/record">对局列表</Link>
    </a-menu-item>
    <a-menu-item key="rankList">
      <Link class="nav-link" to="/rankList">排行榜</Link>
    </a-menu-item>

    <a-sub-menu v-if="$store.state.user.is_login" key="user">
      <template #title>{{ $store.state.user.username }}</template>
      <a-menu-item-group>
        <a-menu-item key="myBots">
          <Link class="dropdown-item" to="/bot">我的BOTS</Link>
        </a-menu-item>
        <a-menu-item key="logout">
          <a class="dropdown-item" @click.prevent="logout">退出</a>
        </a-menu-item>
      </a-menu-item-group>
    </a-sub-menu>
    <a-menu-item v-if="!$store.state.user.is_login">
      <Link to="/login">登录</Link>
    </a-menu-item>
    <a-menu-item v-if="!$store.state.user.is_login">
      <Link to="/register">注册</Link>
    </a-menu-item>
  </a-menu>
</template>

<script setup>
import Link from "@/components/Link.vue";
import { useStore } from "vuex";
import { ref } from "vue";

const store = useStore();

const current = ref([]);
const logout = () => {
  store.dispatch("logout");
};
</script>

<style lang="scss" scoped></style>
