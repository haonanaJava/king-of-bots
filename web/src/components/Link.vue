<template>
  <router-link v-bind="props">
    <slot></slot>
  </router-link>
</template>

<script setup>
import { RouterLink } from "vue-router";

const props = defineProps({
  ...RouterLink.props,
});
const emit = defineEmits(["click"]);
</script>

<style lang="scss" scoped>
.router-link-active {
  color: #42b983;
}
.route-link {
  text-decoration: none;
  font-size: 1.2rem;
}
</style>
