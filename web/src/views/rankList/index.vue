<template>
  <content-field>
    <a-list :data-source="data" :pagination="pagination">
      <template #renderItem="{ item }">
        <a-list-item :key="item.id">
          <template #extra>
            <a-typography-title :level="5" code>{{
              item.rating
            }}</a-typography-title>
          </template>
          <a-list-item-meta :description="item.description">
            <template #title>
              <a @click="handleTitleClick()">{{ item.username }}</a>
            </template>
            <template #avatar>
              <a-avatar :src="item.avatar" />
            </template>
          </a-list-item-meta>
        </a-list-item>
      </template>
    </a-list>
  </content-field>
</template>

<script setup>
import ContentField from "@/components/ContentField.vue";
import { computed, onMounted, ref } from "vue";
import { getRankList } from "@/api/rankList";

const data = ref([]);
const current = ref(1);
const pageSize = ref(10);
const total = ref(0);
const loading = ref(false);

const pagination = computed(() => {
  return {
    current: current.value,
    pageSize: pageSize.value,
    total: total.value,
    onChange: (page) => {
      current.value = page;
      fetchData({ page, limit: pageSize.value });
    },
  };
});

const fetchData = async ({ page, limit }) => {
  try {
    loading.value = true;
    const { data: pageList } = await getRankList({ page, limit });
    data.value = pageList.list;
  } catch (e) {
    console.log(e);
  } finally {
    loading.value = false;
  }
};

onMounted(() => {
  fetchData({ page: current.value, limit: pageSize.value });
});
</script>

<style scoped></style>
