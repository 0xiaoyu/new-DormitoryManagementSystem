<template>
  <div class="app-container">
    <div class="search-container">
      <el-form :inline="true" :model="queryParams">
        <el-form-item label="查询时间" prop="data">
          <el-date-picker
            v-model="date"
            :shortcuts="shortcuts"
            end-placeholder="End date"
            range-separator="To"
            start-placeholder="Start date"
            type="datetimerange"
          />
        </el-form-item>
        <el-form-item label="查询类型">
          <el-select v-model="queryParams.type" placeholder="请选择查询类型">
            <el-option :key="0" :value="'0,1,2,3'" label="全部" />
            <el-option :key="1" :value="'0,1'" label="学生进出" />
            <el-option :key="2" :value="'2,3'" label="其他人员" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleQuery()">
            <i-ep-search />
            搜索
          </el-button>
          <el-button @click="resetQuery()">
            <i-ep-refresh />
            重置
          </el-button>
          <el-popover
            :visible="timeVisible"
            placement="bottom"
            :width="160"
            trigger="click"
          >
            <template #reference>
              <el-button @click="timeVisible = true">
                <i-ep-refresh />
                设置门禁时间：{{ accessTime }}
              </el-button>
            </template>
            <div>
              <el-time-picker
                v-model="accessSetTime"
                placeholder="门禁时间"
                style="width: 130px"
              />
            </div>
            <div style="text-align: right; margin: 0">
              <el-button size="small" text @click="timeVisible = false"
                >取消
              </el-button>
              <el-button size="small" type="primary" @click="modifyTimes"
                >修改
              </el-button>
            </div>
          </el-popover>
        </el-form-item>
      </el-form>
    </div>
    <el-card shadow="never">
      <template #header>
        <div class="flex justify-between">
          <el-popconfirm title="确认删除吗?" @confirm="deletePass">
            <template #reference>
              <div>
                <el-button
                  type="danger"
                  v-hasPerm="['sys:accessLog:delete']"
                  :disabled="ids.length === 0"
                >
                  <i-ep-delete />
                  删除
                </el-button>
              </div>
            </template>
          </el-popconfirm>
          <div>
            <el-button class="ml-3" @click="handleExport">
              <template #icon>
                <i-ep-download />
              </template>
              导出
            </el-button>
          </div>
        </div>
      </template>
      <el-table
        v-loading="loading"
        highlight-current-row
        :data="passList"
        border
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="人员名字" prop="name" />
        <el-table-column label="时间" prop="createTime" />
        <el-table-column label="类型" prop="atype" />
        <el-table-column label="宿舍" prop="dormitory" />
        <el-table-column label="电话" prop="phone" />
        <el-table-column fixed="right" label="操作" align="center" width="150">
          <template #default="scope">
            <el-popconfirm
              title="确定删除吗?"
              @confirm,stop="deletePass(scope.row.id)"
            >
              <template #reference>
                <el-button
                  v-hasPerm="['sys:accessLog:delete']"
                  type="primary"
                  link
                  size="small"
                >
                  <i-ep-delete />
                  删除
                </el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>

      <pagination
        v-if="total > 0"
        v-model:total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="handleQuery"
      />
    </el-card>
  </div>
</template>

<script lang="ts" setup>
import { PassLogPageQuery } from "@/api/accessLog/types";
import {
  deletePassLogByIds,
  getAccessTime,
  getPassPage,
  modifyTime,
} from "@/api/accessLog";
import dayjs from "dayjs";

const date = ref<Array<Date>>();
const queryParams = reactive<PassLogPageQuery>({
  type: "0,1,2,3",
  pageNum: 1,
  pageSize: 10,
});
const passList = ref([]);
const total = ref<number>(0);
const ids = ref<number[]>([]);
const accessTime = ref<string>();
const accessSetTime = ref();
const timeVisible = ref(false);

const shortcuts = [
  {
    text: "Last week",
    value: () => {
      const end = new Date();
      const start = new Date();
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
      return [start, end];
    },
  },
  {
    text: "Last month",
    value: () => {
      const end = new Date();
      const start = new Date();
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
      return [start, end];
    },
  },
  {
    text: "Last 3 months",
    value: () => {
      const end = new Date();
      const start = new Date();
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
      return [start, end];
    },
  },
];
const loading = ref(false);

function modifyTimes() {
  const time = dayjs(accessSetTime.value).format("HH:mm:ss");
  modifyTime(time).then(() => {
    ElMessage.success("修改成功");
    timeVisible.value = false;
    accessTime.value = time;
  });
}

function handleQuery() {
  loading.value = true;
  queryParams.startTime = date.value?.[0];
  queryParams.endTime = date.value?.[1];
  console.log(queryParams.startTime);
  getPassPage(queryParams)
    .then(({ data }) => {
      passList.value = data.list;
      total.value = data.total;
    })
    .finally(() => {
      loading.value = false;
    });
}

function resetQuery() {
  date.value = [];
  queryParams.type = "0,1,2,3";
  handleQuery();
}

function handleSelectionChange(selection: any) {
  ids.value = selection.map((item: any) => item.id);
}

function getTime() {
  getAccessTime().then(({ data }) => {
    accessTime.value = data;
  });
}

function deletePass(id?: number) {
  loading.value = true;
  if (id != null) {
    ids.value[0] = id;
  }
  const sids = ids.value.join(",");
  deletePassLogByIds(sids)
    .then(() => {
      passList.value = passList.value.filter(
        (item: any) => !ids.value.includes(item.id)
      );
      ElMessage.success("删除成功");
    })
    .finally(() => {
      loading.value = false;
    });
}

handleQuery();
getTime();
</script>
<style scoped></style>
