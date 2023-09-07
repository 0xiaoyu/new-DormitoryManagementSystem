<script lang="ts" setup>

import {MaintenList, MaintenPage} from "@/api/maintenance/types";
import {pageRapir} from "@/api/maintenance";
import {getDictOptions} from "@/api/dict";
import {buildOption} from "@/api/build";

const queryFormRef = ref(ElForm);
const dataFormRef = ref(ElForm);

const loading = ref(false);
const ids = ref<number[]>([]);
const total = ref(0);

const type = ref();

const buildList = ref();

buildOption(null).then(({data}) => {
  buildList.value = data;
});


getDictOptions("maintain").then(({data}) => {
  type.value = data;
});

const queryParams = reactive<MaintenPage>({
  pageNum: 1,
  pageSize: 10,
});

const dataList = ref<MaintenList[]>([]);

/** 行复选框选中  */
function handleSelectionChange(selection: any) {
  ids.value = selection.map((item: any) => item.id);
}

/**
 * 查询
 */
function handleQuery() {
  loading.value = true;
  pageRapir(queryParams)
    .then(({data}) => {
      const list = data.list.map((item) => {
        item.type = type.value.find(
          (type) => type.value === item.typeId
        )?.label;
        item.dormitory =
          buildList.value.find((build) => build.value === item.buildingId)?.label +
          item.dormitoryNumber;
        return item
      });
      dataList.value = list;
      total.value = data.total;
    })
    .finally(() => {
      loading.value = false;
    });
}

/** 重置查询 */
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
}

handleQuery();
</script>

<template>
  <div class="app-container">
    <div class="search-container">
      <el-form ref="queryFormRef" :inline="true" :model="queryParams">

        <el-form-item label="维修类型" prop="gender">
          <dictionary v-model="queryParams.typeId" type-code="maintain"/>
        </el-form-item>
        <el-form-item label="宿舍" prop="dormitoryId">
          <el-input
            v-model="queryParams.dormitoryId"
            clearable
            placeholder="宿舍id"
            @keyup.enter="handleQuery"
          />
        </el-form-item>
        <el-form-item label="维修人员" prop="dormitoryId">
          <el-input
            v-model="queryParams.maintenancePersonId"
            clearable
            placeholder="维修人员"
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
        <el-table-column align="center" type="selection" width="40"/>
        <el-table-column label="单号" prop="id" width="60"/>
        <el-table-column label="申报学生" prop="studentName"/>
        <el-table-column label="学生电话" prop="studentPhone"/>
        <el-table-column label="维修人员名字" prop="repairName"/>
        <el-table-column label="维修人员电话" prop="repairPhone"/>
        <el-table-column label="维修类型" prop="type"/>
        <el-table-column label="申报时间" prop="createTime"/>
        <el-table-column label="维修状态" prop="status"/>
        <el-table-column label="更新时间" prop="updateTime"/>
        <el-table-column label="维修宿舍" prop="dormitory"/>
        <el-table-column label="维修详情描述" prop="detail"/>
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

<style scoped>

</style>
