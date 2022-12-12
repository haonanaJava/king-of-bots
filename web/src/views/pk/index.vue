<template>
  <play-ground v-if="$store.state.pk.status === 'playing'" />
  <matching v-else />
  <result-board v-if="$store.state.pk.loser != 'none'" />
  <a-alert
    class="pk-info"
    v-if="$store.state.pk.status === 'playing'"
    :message="
      parseInt($store.state.user.id) === $store.state.pk.a_id
        ? '你是蓝色方'
        : '你是红色方'
    "
    type="info"
    show-icon
    :banner="true"
    :closable="true"
  />
</template>

<script setup>
import ResultBoard from "@/components/ResultBoard.vue";
import PlayGround from "@/components/PlayGround.vue";
import Matching from "@/components/Matching.vue";
import { onMounted, onUnmounted } from "vue";
import { useStore } from "vuex";
import {
  START_MATCH,
  STOP_MATCH,
  MATCH_SUCCESS,
  MOVE,
  RESULT,
} from "@/utils/constant";

const store = useStore();
const socketUrl = `ws://localhost:8080/websocket/${store.state.user.token}`;
let socket = null;

store.commit("updateLoser", "none");

onMounted(() => {
  store.commit("updateOpponent", {
    username: "我的对手",
    photo:
      "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
  });
  socket = new WebSocket(socketUrl);
  socket.onopen = () => {
    console.log("websocket open");
    store.commit("updateSocket", socket);
  };

  socket.onmessage = (e) => {
    const { data, event, game, a_dir, b_dir, loser } = JSON.parse(e.data);
    console.log(event, a_dir, b_dir, loser);
    if (event === MATCH_SUCCESS) {
      store.commit("updateOpponent", {
        username: data.username,
        photo: data.avatar,
      });
      store.commit("updateStatus", "playing");
      store.commit("updateGame", game);
    } else if (event === MOVE) {
      const gameObject = store.state.pk.gameObject;
      const [snake0, snake1] = gameObject.snakes;
      snake0.set_direction(a_dir);
      snake1.set_direction(b_dir);
    } else if (event === RESULT) {
      const [snake0, snake1] = store.state.pk.gameObject.snakes;
      if (loser === "all" || loser === "A") {
        snake0.set_status("dead");
      } else if (loser === "all" || loser === "B") {
        snake1.set_status("dead");
      }
      store.commit("updateLoser", loser);
    }
  };

  socket.onclose = () => {
    console.log("websocket close");
    store.commit("updateSocket", null);
    store.commit("updateStatus", "matching");
  };
});

onUnmounted(() => {
  socket.close();
});
</script>

<style lang="scss" scoped>
.pk-info {
  position: absolute;
  top: 5%;
  left: 38%;
  width: 400px;
}
</style>
