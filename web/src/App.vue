<template>
  <a-layout class="app">
    <a-layout-header>
      <Link class="logo" to="/home">King Of Bots</Link>
      <NavBar />
    </a-layout-header>
    <a-layout-content>
      <router-view />
    </a-layout-content>
  </a-layout>
</template>

<script setup>
import NavBar from "@/components/NavBar.vue";
</script>

<style lang="scss" scoped>
.app {
  height: 100vh;
  background-image: url(@/assets/background.jpg);
  background-repeat: no-repeat;
  background-size: cover;
}

.logo {
  float: left;
  height: 64px;
  font-weight: 600;
  font-size: 1.5rem;
  margin-right: 30px;
}
</style>
