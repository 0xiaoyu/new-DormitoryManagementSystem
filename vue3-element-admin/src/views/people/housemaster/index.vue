<template>
  <div class="app-container">
    <div class="search-container">
      <el-form ref="queryFormRef" :inline="true" :model="queryParams">
        <el-form-item label="姓名" prop="name">
          <el-input
            v-model="queryParams.name"
            clearable
            placeholder="姓名"
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
            <el-button
              v-hasPerm="['sys:housemaster:add']"
              type="success"
              @click="openDialog()"
            >
              <i-ep-plus/>
              新增
            </el-button>
            <el-button
              v-hasPerm="['sys:housemaster:delete']"
              :disabled="ids.length === 0"
              type="danger"
              @click="handleDelete()"
            >
              <i-ep-delete/>
              删除
            </el-button>
          </div>
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
        <el-table-column align="center" type="selection" width="55"/>
        <el-table-column label="工号" prop="id" width="100"/>
        <el-table-column label="姓名" prop="name" width="200"/>
        <el-table-column label="性别" prop="gender"/>
        <el-table-column label="年龄" prop="age" width="100"/>
        <el-table-column align="center" label="手机号" prop="phone"/>
        <el-table-column label="宿舍楼" prop="type"/>
        <el-table-column align="center" fixed="right" label="操作" width="150">
          <template #default="scope">
            <el-button
              v-hasPerm="['sys:housemaster:edit']"
              link
              size="small"
              type="primary"
              @click.stop="openDialog(scope.row.id)"
            >
              <i-ep-edit/>
              编辑
            </el-button>
            <el-button
              v-hasPerm="['sys:housemaster:delete']"
              link
              size="small"
              type="primary"
              @click.stop="handleDelete(scope.row.id)"
            >
              <i-ep-delete/>
              删除
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
    <el-dialog
      v-model="dialog.visible"
      :title="dialog.title"
      width="500px"
      @close="closeDialog"
    >
      <el-form
        ref="dataFormRef"
        :model="formData"
        :rules="rules"
        label-width="80px"
      >
        <el-form-item label="工号" prop="id">
          <el-input v-model="formData.id" disabled/>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="formData.name" placeholder="请输入姓名"/>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="formData.gender">
            <el-radio :label="'男'">男</el-radio>
            <el-radio :label="'女'">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="formData.age" placeholder="请输入年龄"/>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="formData.phone" placeholder="请输入手机号"/>
        </el-form-item>
        <el-form-item label="宿舍楼" prop="type">
          <dictionary v-model="formData.type" type-code="maintain"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="handleSubmit">确 定</el-button>
          <el-button @click="closeDialog">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>

import {housemaster, peoplePage} from "@/api/people/types";
import {getHousemasterList} from "@/api/people";

const queryFormRef = ref(ElForm);
const dataFormRef = ref(ElForm);

const ids = ref<number[]>([]);
const queryParams = reactive<peoplePage>({
  pageNum: 1,
  pageSize: 10,
});
const loading = ref(false);
const dataList = ref<housemaster[]>([]);
const total = ref(0);

const dialog = reactive<DialogOption>({
  visible: false,
});
const formData = reactive<housemaster>({});
const rules = reactive({
  name: [{required: true, message: "请输入学生姓名", trigger: "blur"}],
  phone: [{required: true, message: "请输入手机号", trigger: "blur"}],
});

/**
 * 查询
 */
function handleQuery() {
  loading.value = true;
  getHousemasterList(queryParams)
    .then(({data}) => {
      dataList.value = data.list;
      total.value = data.total;
    })
    .finally(() => {
      loading.value = false;
    });
}

/** 重置查询 */
function resetQuery() {
  queryParams.name = "";
  queryParams.pageNum = 1;
  handleQuery();
}

/** 行复选框选中  */
function handleSelectionChange(selection: any) {
  ids.value = selection.map((item: any) => item.id);
}

const cellStyle = ({row, column, rowIndex, columnIndex}) => {
  if (row[column.property] === null) {
    row[column.property] = "--";
  }
};

function filterNone(value: string) {
  return value ? value : "--";
}

function openDialog(id?: number) {
  console.log(dataFormRef)
  dialog.visible = true;
  if (id) {
    dialog.title = "编辑宿管";
    nextTick(() => {
      Object.assign(
        formData,
        dataList.value.find((item) => item.id === id) || {}
      );
    });
  } else {
    dialog.title = "新增宿管";
  }
}

function closeDialog() {
  dialog.visible = false;
  resetForm();
}

function resetForm() {
  dataFormRef.value.resetFields();
  dataFormRef.value.clearValidate();
}

handleQuery()
</script>

<style scoped>
</style>
