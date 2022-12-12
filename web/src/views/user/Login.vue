<template>
  <content-field>
    <a-form
      class="form"
      :model="data"
      autocomplete="off"
      @submit.prevent="login"
      :label-col="{ span: 8 }"
      :wrapper-col="{ span: 16 }"
    >
      <a-form-item
        :rules="[{ required: true, message: 'Please input your username!' }]"
        label="用户名"
      >
        <a-input
          type="text"
          v-model:value="data.username"
          placeholder="请输入用户名"
        />
      </a-form-item>
      <a-form-item
        v-model="data.password"
        label="密码"
        :rules="[{ required: true, message: 'Please input your password!' }]"
      >
        <a-input
          type="password"
          v-model:value="data.password"
          placeholder="请输入密码"
        />
      </a-form-item>
      <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
        <a-button style="width: 100%" type="primary" html-type="submit">
          登录
        </a-button>
      </a-form-item>
    </a-form>
  </content-field>
</template>

<script setup>
import { onMounted, ref, reactive } from "vue";
import ContentField from "@/components/ContentField.vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

const data = reactive({
  username: "",
  password: "",
});

const errorMessage = ref("");
const store = useStore();
const router = useRouter();
const login = async () => {
  errorMessage.value = "";
  try {
    const res = await store.dispatch("login", data);
    router.push({ name: "Home" });
  } catch (e) {
    errorMessage.value = "用户名或密码错误";
  }
};
</script>

<style lang="scss" scoped>

.ant-form {
  width: 400px;
  margin: 0 auto;
}
</style>
