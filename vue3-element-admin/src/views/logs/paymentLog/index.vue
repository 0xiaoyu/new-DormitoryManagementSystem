<template>
  <div class="app-container">
    <div class="search-container">
      <el-form ref="queryFormRef" :inline="true" :model="queryParams">
        <el-form-item label="学生姓名" prop="dormitoryId">
          <el-input
            v-model="queryParams.name"
            clearable
            placeholder="宿舍id"
            @keyup.enter="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleQuery()">
            <i-ep-search/>
            搜索
          </el-button>
          <el-button @click="resetQuery()">
            <i-ep-refresh/>
            重置
          </el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-card shadow="never">
      <template #header>
        <div class="flex justify-between">
          <div>
            <el-dropdown split-button>
              导入
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item @click="downloadTemplate">
                    <i-ep-download/>
                    下载模板
                  </el-dropdown-item
                  >
                  <el-dropdown-item @click="openImportDialog">
                    <i-ep-top/>
                    导入数据
                  </el-dropdown-item
                  >
                </el-dropdown-menu>
              </template>
            </el-dropdown>
            <el-button class="ml-3" @click="handleExport">
              <template #icon>
                <i-ep-download/>
              </template>
              导出
            </el-button
            >
          </div>
        </div>
      </template>
      <el-table
        v-loading="loading"
        :cell-style="cellStyle"
        :data="dataList"
        border
        highlight-current-row
        @selection-change="handleSelectionChange"
      >
        <el-table-column label="序号" prop="id" width="50"/>
        <el-table-column label="宿舍" prop="dormitoryId">
          <template #default>
            testBuild-101
          </template>
        </el-table-column>
        <el-table-column label="缴费金额" prop="amount"/>
        <el-table-column label="缴费者" prop="userId">
          <template #default>
            测试学生
          </template>
        </el-table-column>
        <el-table-column label="缴费时间" prop="createTime"/>
        <el-table-column label="订单状态" prop="status">
          <template #default="{row}">
            {{ row.status === 0 ? '未支付' : '已支付' }}
          </template>
        </el-table-column>
        <el-table-column label="缴费类型" prop="type"/>
        <el-table-column align="center" fixed="right" label="操作" width="70">
          <template #default="scope">
            <el-button
              v-hasPerm="['sys:student:edit']"
              link
              size="small"
              type="primary"
            >
              <i-ep-edit/>
              编辑
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination
        v-if="total > 0"
        v-model:limit="queryParams.pageSize"
        v-model:page="queryParams.pageNum"
        v-model:total="total"
        @pagination="handleQuery"
      />
    </el-card>
  </div>
</template>


<script lang="ts" setup>
import {ViolationLogPageQuery} from "@/api/violationLog/types";
import {PayLog} from "@/api/Pay/types";
import {getPayLog} from "@/api/Pay";

const queryFormRef = ref(ElForm);
const dataFormRef = ref(ElForm);

const loading = ref(false);
const ids = ref<number[]>([]);
const total = ref(0);

const queryParams = reactive<ViolationLogPageQuery>({
  pageNum: 1,
  pageSize: 10,
});

const dataList = ref<PayLog[]>([]);

/** 行复选框选中  */
function handleSelectionChange(selection: any) {
  ids.value = selection.map((item: any) => item.id);
}

/**
 * 查询
 */
function handleQuery() {
  loading.value = true;
  getPayLog()
    .then(({data}) => {
      dataList.value = data.records;
      total.value = data.total;
    })
    .finally(() => {
      loading.value = false;
    });
}

/** 重置查询
 function resetQuery() {
 queryFormRef.value.resetFields();
 queryParams.pageNum = 1;
 handleQuery();
 }

 function resetForm() {
 dataFormRef.value.resetFields();
 dataFormRef.value.clearValidate();
 formData.id = undefined;
 formData.status = 1;
 }*/

handleQuery();
</script>

<style scoped>

</style>
