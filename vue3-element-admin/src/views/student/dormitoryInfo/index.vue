<script lang="ts" setup>
import { useUserStore } from "@/store/modules/user";
import { getStudentInfo } from "@/api/student";
import { StudentInfo } from "@/api/student/types";
import { PayLog } from "@/api/Pay/types";
import { violationLog } from "@/api/violationLog/types";
import { useStudentStore } from "@/store/modules/student";
import { getNoReadNotice, getNotice } from "@/api/recevieNotice";

const user = useUserStore();
const activeNames = ref(["2"]);

const student = useStudentStore();
const studentInfo = ref<Array<StudentInfo>>();
const payLog = ref<Array<PayLog>>(student.payLogs);
const violations = ref<Array<violationLog>>(student.violationLogs);

function getSInfo() {
  getStudentInfo(user.userId).then((res) => {
    const data: StudentInfo = res.data;
    studentInfo.value = [data];
  });
}

const noRead = ref<Number>(1);

function getNoRead() {
  getNoReadNotice(user.userId).then(({ data }) => {
    noRead.value = data;
    if (data !== 0) {
      getNotice(user.userId).then((res) => {
        const data = res.data;
        console.log(data);
      });
    }
  });
}

getSInfo();
</script>

<template>
  <el-card>
    <template #header>
      <div class="card-header">
        <span>欢迎！！{{ user.nickname }}</span>
        <el-button class="button" text>Operation button</el-button>
      </div>
    </template>
    <el-collapse v-model="activeNames">
      <el-collapse-item name="1">
        <el-card>
          <template #header>
            <div
              class="card-header"
              style="background-color: #97b4cb; border-radius: 10px"
            >
              <span>通知</span>
            </div>
          </template>
          <div>今天9-10点停水</div>
        </el-card>
        <template #title>
          <el-badge :value="noRead" class="item">
            <el-button>未读消息</el-button>
          </el-badge>
        </template>
        <el-card>
          <template #header>
            <div class="card-header">
              <span>通知</span>
            </div> </template
          >测试通知
        </el-card>
      </el-collapse-item>
      <el-collapse-item name="2" title="宿舍详情">
        <el-table :data="studentInfo">
          <el-table-column label="宿舍" prop="dormitory" />
          <el-table-column label="水费" prop="water" />
          <el-table-column label="电费" prop="electricity" />
          <el-table-column label="操作">
            <template #default>
              <el-button size="small" type="primary">缴费</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-collapse-item>
      <el-collapse-item name="3" title="缴费记录">
        <el-table :data="payLog">
          <el-table-column label="缴费单号" prop="id" />
          <el-table-column label="缴费项目" prop="type" />
          <el-table-column label="金额" prop="amount" />
          <el-table-column label="缴费时间" prop="createTime" />
          <el-table-column label="订单状态" prop="status" />
          <el-table-column label="操作">
            <template #default>
              <el-button size="small" type="primary">订单申诉</el-button>
              <el-button size="small" type="primary">缴费</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-collapse-item>
      <el-collapse-item name="4" title="违规记录">
        <el-table :data="violations">
          <el-table-column label="序号" type="index" width="50" />
          <el-table-column label="违规类型" prop="type" />
          <el-table-column label="违规时间" prop="createTime" />
          <el-table-column label="违规详情" prop="detail" />
          <el-table-column label="操作">
            <template #default>
              <el-button size="small" type="primary">申诉</el-button>
            </template>
          </el-table-column>
        </el-table>
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
