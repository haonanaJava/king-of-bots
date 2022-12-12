<template>
  <div class="playground">
    <game-map />
  </div>
</template>

<script setup>
import GameMap from "./GameMap.vue";
</script>

<style lang="scss" scoped>
.playground {
  width: 60vw;
  height: 70vh;
  margin: 40px auto;
}
</style>
