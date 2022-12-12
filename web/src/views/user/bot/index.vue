<template>
  <div class="container">
    <a-row :gutter="24">
      <a-col :span="8">
        <a-card>
          <a-image :src="$store.state.user.avatar" alt="photo" width="100%" />
        </a-card>
      </a-col>
      <a-col :span="16">
        <a-card title="我的Bots">
          <template #extra>
            <a-button type="primary" @click="visible = true">Add</a-button>
          </template>
          <a-table
            :data-source="botList"
            :columns="columns"
            style="height: 400px"
          >
            <template #bodyCell="{ column, record }">
              <template v-if="column.key === 'action'">
                <span>
                  <a @click="handleEditClick(record)">编辑</a>
                  <a-divider type="vertical" />
                  <a @click="handleRemoveBot(record.id)">删除</a>
                </span>
              </template>
            </template>
          </a-table>
        </a-card>
      </a-col>
    </a-row>

    <a-modal
      v-model:visible="visible"
      title="编辑"
      :confirm-loading="confirmLoading"
      @ok="handleOk"
      width="800px"
      :afterClose="afterClose"
    >
      <bot-form v-model:value="bot" />
    </a-modal>
  </div>
</template>

<script setup>
import { onMounted, ref, reactive } from "vue";
import { getBotList, addBot, removeBot, updateBot } from "@/api/bot";
import BotForm from "./BotForm.vue";
import _ from "lodash";
import { message } from "ant-design-vue";

const visible = ref(false);
const botList = ref([]);
const columns = ref([
  {
    title: "标题",
    dataIndex: "title",
    key: "title",
  },
  {
    title: "描述",
    dataIndex: "description",
    key: "description",
  },
  {
    title: "rank",
    dataIndex: "rating",
    key: "rating",
  },
  {
    title: "创建时间",
    dataIndex: "createTime",
    key: "createTime",
  },
  {
    title: "Action",
    key: "action",
  },
]);

const pagination = reactive({
  page: 1,
  limit: 10,
  total: 0,
  totalCount: 0,
});

const confirmLoading = ref(false);
function afterClose() {
  bot.id = null;
  bot.title = "";
  bot.description = "";
  bot.content = "";
}
const bot = reactive({
  title: "",
  description: "",
  content: "",
});

const handleEditClick = (data) => {
  _.assign(bot, data);
  visible.value = true;
};

async function handleOk() {
  if (bot.id) {
    try {
      confirmLoading.value = true;
      const { status, message: msg } = await updateBot(bot);
      if (status == 200) {
        message.info(msg);
        fetchBotList();
      }
      visible.value = false;
    } catch (e) {
      message.error("更新失败");
      console.log(e);
    } finally {
      confirmLoading.value = false;
    }
  } else {
    const { title, description, content } = bot;
    try {
      confirmLoading.value = true;
      const { status, message: msg } = await addBot({
        title,
        description,
        content,
      });
      if (status === 200) {
        message.info(msg);
        fetchBotList();
        visible.value = false;
      }
    } catch (e) {
      message.error("添加失败");
      console.log(e);
    } finally {
      confirmLoading.value = false;
    }
  }
}

async function fetchBotList() {
  const { page, limit } = pagination;
  const { data } = await getBotList({ page, limit });
  const { list, totalPage, totalCount } = data;
  botList.value = list;
  pagination.total = totalPage;
  pagination.totalCount = totalCount;
}

async function handleRemoveBot(id) {
  if (id) {
    try {
      const { status, message } = await removeBot(id);
      if (status === 200) {
        fetchBotList();
        console.log(message);
      }
    } catch (e) {
      console.log(e);
    }
  }
}

onMounted(() => {
  fetchBotList();
});
</script>

<style lang="scss" scoped>
.container {
  width: 60vw;
  margin: 0 auto;
}
</style>
