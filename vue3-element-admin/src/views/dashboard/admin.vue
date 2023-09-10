<script setup lang="ts">
// 收入金额
import { TransitionPresets, useTransition } from "@vueuse/core";
import { getStudentCount } from "@/api/student";
import Sender from "@/views/notice/sender.vue";

const amount = ref(0);
const amountOutput = useTransition(amount, {
  duration: duration,
  transition: TransitionPresets.easeOutExpo,
});
amount.value = 2000;

// 访问数
const visitCount = ref(0);
const visitCountOutput = useTransition(visitCount, {
  duration: duration,
  transition: TransitionPresets.easeOutExpo,
});

getStudentCount().then(({ data }) => {
  visitCount.value = data;
});

const duration = 5000;
//消息数
const messageCount = ref(0);
const messageCountOutput = useTransition(messageCount, {
  duration: duration,
  transition: TransitionPresets.easeOutExpo,
});
messageCount.value = 106;

// 订单数
const orderCount = ref(0);
const orderCountOutput = useTransition(orderCount, {
  duration: duration,
  transition: TransitionPresets.easeOutExpo,
});
orderCount.value = 135;
</script>

<template>
  <!-- 数据卡片 -->
  <el-row :gutter="40" class="mb-4">
    <el-col :lg="6" :sm="12" :xs="24" class="mb-4">
      <div class="data-box">
        <div
          class="text-[#40c9c6] hover:!text-white hover:bg-[#40c9c6] p-3 rounded"
        >
          <svg-icon icon-class="uv" size="3em" />
        </div>
        <div class="flex flex-col space-y-3">
          <div class="text-[var(--el-text-color-secondary)]">学生人数</div>
          <div class="text-lg text-right">
            {{ Math.round(visitCountOutput) }}
          </div>
        </div>
      </div>
    </el-col>

    <!--消息数-->
    <el-col :lg="6" :sm="12" :xs="24" class="mb-4">
      <div class="data-box">
        <div
          class="text-[#36a3f7] hover:!text-white hover:bg-[#36a3f7] p-3 rounded"
        >
          <svg-icon icon-class="message" size="3em" />
        </div>
        <div class="flex flex-col space-y-3">
          <div class="text-[var(--el-text-color-secondary)]">消息数</div>
          <div class="text-lg text-right">
            {{ Math.round(messageCountOutput) }}
          </div>
        </div>
      </div>
    </el-col>

    <el-col :lg="6" :sm="12" :xs="24" class="mb-4">
      <div class="data-box">
        <div
          class="text-[#f4516c] hover:!text-white hover:bg-[#f4516c] p-3 rounded"
        >
          <svg-icon icon-class="money" size="3em" />
        </div>
        <div class="flex flex-col space-y-3">
          <div class="text-[var(--el-text-color-secondary)]">收入金额</div>
          <div class="text-lg text-right">
            {{ Math.round(amountOutput) }}
          </div>
        </div>
      </div>
    </el-col>
    <el-col :lg="6" :sm="12" :xs="24" class="mb-2">
      <div class="data-box">
        <div
          class="text-[#34bfa3] hover:!text-white hover:bg-[#34bfa3] p-3 rounded"
        >
          <svg-icon icon-class="shopping" size="3em" />
        </div>
        <div class="flex flex-col space-y-3">
          <div class="text-[var(&#45;&#45;el-text-color-secondary)]">
            缴费记录
          </div>
          <div class="text-lg text-right">
            {{ Math.round(orderCountOutput) }}
          </div>
        </div>
      </div>
    </el-col>
  </el-row>
  <sender />

  <!--  &lt;!&ndash; Echarts 图表 &ndash;&gt;
  <el-row :gutter="40">
    <el-col :lg="8" :sm="24" class="mb-2">
      <BarChart
          id="barChart"
          class="bg-[var(&#45;&#45;el-bg-color-overlay)]"
          height="400px"
          width="100%"
      />
    </el-col>

    <el-col :lg="8" :sm="12" :xs="24" class="mb-2">
      <PieChart
          id="pieChart"
          class="bg-[var(&#45;&#45;el-bg-color-overlay)]"
          height="400px"
          width="100%"
      />
    </el-col>

    <el-col :lg="8" :sm="12" :xs="24" class="mb-2">
      <RadarChart
          id="radarChart"
          class="bg-[var(&#45;&#45;el-bg-color-overlay)]"
          height="400px"
          width="100%"
      />
    </el-col>-->
  <!--  </el-row>-->
</template>

<style scoped>
.data-box {
  display: flex;
  justify-content: space-between;
  padding: 20px;
  font-weight: bold;
  color: var(--el-text-color-regular);
  background: var(--el-bg-color-overlay);
  border-color: var(--el-border-color);
  box-shadow: var(--el-box-shadow-dark);
}
</style>
