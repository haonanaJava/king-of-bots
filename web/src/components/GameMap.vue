<template>
  <div ref="parent" class="gameMap">
    <canvas ref="canvas" tabindex="0"></canvas>
  </div>
</template>

<script setup>
import GameMap from "@/script/GameMap.js";
import { ref, onMounted } from "vue";
import { useStore } from "vuex";

const canvas = ref(null);
const parent = ref(null);
const store = useStore();

onMounted(() => {
  let gameMap = new GameMap(canvas.value.getContext("2d"), parent.value);
  store.commit("updateGameObject", gameMap);
});
</script>

<style lang="scss" scoped>
.gameMap {
  height: 100%;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
