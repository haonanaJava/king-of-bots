<template>
  <div class="result-board">
    <span class="result">{{ result }}</span>
    <a-button class="result-btn" block type="danger" @click="rematch"
      >再来一局</a-button
    >
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { useStore } from "vuex";

const store = useStore();

const result = computed(() => {
  let loser = store.state.pk.loser;
  if (loser == "all") {
    return "Draw";
  } else if (loser == "A" && store.state.user.id == store.state.pk.a_id) {
    return "You lose";
  } else if (loser == "B" && store.state.user.id == store.state.pk.b_id) {
    return "You lose";
  } else {
    return "You win";
  }
});

function rematch() {
  store.commit("updateStatus", "matching");
  store.commit("updateLoser", "none");
  store.commit("updateOpponent", {
    username: "我的对手",
    photo:
      "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
  });
}
</script>

<style lang="scss" scoped>
.result-board {
  width: 30vw;
  height: 30vh;
  background-color: rgba(50, 50, 50, $alpha: 0.3);
  position: absolute;
  top: 35vh;
  left: 35vw;

  .result {
    display: block;
    text-align: center;
    font-size: 2.5rem;
    color: white;
    font-style: italic;
    font-weight: 600;
  }
  .result-btn {
    margin: 0 auto;
    margin-top: 10rem;
  }
}
</style>
