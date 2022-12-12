<template>
  <a-form
    :model="bot"
    :label-col="{ span: 2 }"
    :wrapper-col="{ span: 22 }"
    autocomplete="off"
  >
    <a-form-item label="名称">
      <a-input v-model:value="title" />
    </a-form-item>
    <a-form-item label="描述">
      <a-input v-model:value="description" />
    </a-form-item>
    <a-form-item label="代码">
      <VAceEditor
        v-model:value="content"
        @init="editorInit"
        lang="c_cpp"
        theme="textmate"
        style="height: 300px"
      />
    </a-form-item>
  </a-form>
</template>

<script setup>
import { VAceEditor } from "vue3-ace-editor";
import { reactive, ref, computed } from "vue";
import ace from "ace-builds";

ace.config.set(
  "basePath",
  "https://cdn.jsdelivr.net/npm/ace-builds@" + ace.version + "/src-noconflict/"
);

const props = defineProps({
  value: {
    type: Object,
    default: () => {
      return {};
    },
  },
});

const emit = defineEmits(["update:value"]);
const bot = reactive(props.value);

const title = computed({
  get() {
    return bot.title;
  },
  set(value) {
    bot.title = value;
    emit("update:value", bot);
  },
});

const description = computed({
  get() {
    return bot.description;
  },
  set(value) {
    bot.description = value;
    emit("update:value", bot);
  },
});

const content = computed({
  get() {
    return bot.content;
  },
  set(value) {
    bot.content = value;
    emit("update:value", bot);
  },
});

const editorInit = reactive({});
</script>

<style lang="scss" scoped></style>
