<script setup lang="ts">
import { useUserStore } from "@/store/modules/user";

const user = useUserStore();
const activeNames = ref(["1"]);
</script>

<template>
  <el-card>
    <template #header>
      <div class="card-header">
        <span>欢迎！！{{ user.nickname }}</span>
        <el-button class="button" text>Operation button</el-button>
      </div>
    </template>
    <el-collapse v-model="activeNames" @change="handleChange">
      <el-collapse-item title="宿舍通知" name="1">

      </el-collapse-item>
      <el-collapse-item title="电费" name="2">
        <div>
        </div>
      </el-collapse-item>
      <el-collapse-item title="水费" name="3">
        <div>
          Simplify the process: keep operating process simple and intuitive;
        </div>
      </el-collapse-item>
      <el-collapse-item title="违规记录" name="4">
        <div>
          Decision making: giving advices about operations is acceptable, but do
          not make decisions for the users;
        </div>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
