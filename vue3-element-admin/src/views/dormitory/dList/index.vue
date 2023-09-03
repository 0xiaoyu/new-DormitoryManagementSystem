<template>
  <div class="app-container">
    <div class="search-container">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true">
        <el-form-item label="宿舍楼类型" prop="buildType">
          <dictionary :typeCode="'building'" v-model="queryParams.buildType" />
        </el-form-item>
        <el-form-item label="宿舍楼" prop="buildingId" style="width: 200px">
          <el-select
            v-model="queryParams.buildingId"
            placeholder="请选择宿舍楼"
            clearable
            filterable
          >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="楼层" prop="floor">
          <el-input
            v-model.number="queryParams.floor"
            placeholder="楼层"
            clearable
            :disabled="queryParams.buildingId === undefined"
          />
        </el-form-item>
        <el-form-item label="宿舍号" prop="dormitoryNumber">
          <el-input
            v-model.number="queryParams.dormitoryNumber"
            placeholder="编号"
            clearable
            :disabled="queryParams.buildingId === undefined"
          />
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
        </el-form-item>
      </el-form>
    </div>

    <el-card shadow="never">
      <template #header>
        <div class="flex justify-between">
          <div>
            <el-button
              v-hasPerm="['sys:dict_type:add']"
              type="success"
              @click="openDialog()"
            >
              <i-ep-plus />
              新增
            </el-button>
            <el-button
              type="danger"
              :disabled="ids.length === 0"
              @click="handleDelete()"
            >
              <i-ep-delete />
              删除
            </el-button>
          </div>
          <div>
            <el-dropdown split-button>
              导入
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item @click="downloadTemplate">
                    <i-ep-download />
                    下载模板
                  </el-dropdown-item>
                  <el-dropdown-item @click="openImportDialog">
                    <i-ep-top />
                    导入数据
                  </el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
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
        :data="dormitoryList"
        border
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="楼层栋" prop="buildingId" width="200">
          <template #default="{ row }">
            {{ getBuildById(row.buildingId) }}
          </template>
        </el-table-column>
        <el-table-column label="宿舍号" prop="dormitoryNumber" width="200" />
        <el-table-column label="宿舍容量" prop="capacity" width="100" />
        <el-table-column label="电费" prop="electricity" align="center">
          <template #default="{ row }">
            <el-tag :type="type[row.estatus]">{{ row.electricity }}元</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="水费" prop="electricity" align="center">
          <template #default="{ row }">
            <el-tag :type="type[row.wstatus]">{{ row.water }}元</el-tag>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" align="center" width="150">
          <template #default="scope">
            <el-button
              v-hasPerm="['sys:dormitory:edit']"
              type="primary"
              link
              size="small"
              @click.stop="openDialog(scope.row.id)"
            >
              <i-ep-edit />
              编辑
            </el-button>
            <el-button
              v-hasPerm="['sys:dormitory:delete']"
              type="primary"
              link
              size="small"
              @click.stop="handleDelete(scope.row.id)"
            >
              <i-ep-delete />
              删除
            </el-button>
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
        <el-form-item label="楼层栋" prop="buildingId">
          <el-select
            v-model="formData.buildingId"
            placeholder="请选择宿舍楼"
            clearable
            filterable
          >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
          <!--          <el-input v-model="formData.buildingId" placeholder="请输入姓名" />-->
        </el-form-item>
        <el-form-item label="宿舍号" prop="dormitoryNumber">
          <el-input
            v-model="formData.dormitoryNumber"
            placeholder="请输入学生年龄"
          />
        </el-form-item>
        <el-form-item label="宿舍容量" prop="capacity">
          <el-input v-model="formData.capacity" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="电费" prop="electricity">
          <el-input
            v-model="formData.electricity"
            placeholder="请输入学生班级"
          />
        </el-form-item>
        <el-form-item label="水费" prop="water">
          <el-input v-model="formData.water" placeholder="请输入宿舍楼" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="handleSubmit">确 定</el-button>
          <el-button @click="closeDialog">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!--    &lt;!&ndash;字典数据弹窗&ndash;&gt;
    <el-dialog
      v-model="dictDataDialog.visible"
      :title="dictDataDialog.title"
      width="1000px"
      @close="closeDictDialog"
    >
      <dict-data
        v-model:typeCode="selectedDictType.typeCode"
        v-model:typeName="selectedDictType.typeName"
      />
    </el-dialog>-->
  </div>
</template>

<script lang="ts" setup>
import { Dormitory, DormitoryPageQuery } from "@/api/dormitory/types";
import { buildOption } from "@/api/build";
import { getDormitoryPage } from "@/api/dormitory";
import { Building } from "@/api/build/types";

const queryParams = reactive<DormitoryPageQuery>({
  pageNum: 1,
  pageSize: 20,
});
const dataFormRef = ref(ElForm);
const queryFormRef = ref(ElForm);

const options = ref();
const total = ref<Number>(0);
const dormitoryList = ref([]);
const formData = ref<Dormitory>({});
const rules = reactive({
  buildingId: [
    { required: true, message: "请输入宿舍楼名称", trigger: "blur" },
  ],
  dormitoryNumber: [
    { required: true, message: "请输入宿舍号", trigger: "blur" },
  ],
  capacity: [{ required: true, message: "请输入宿舍容量", trigger: "blur" }],
  electricity: [{ required: true, message: "请输入电费", trigger: "blur" }],
  water: [{ required: true, message: "请输入水费", trigger: "blur" }],
});

const dialog = reactive<DialogOption>({
  visible: false,
});
const loading = ref(false);
const ids = ref<number[]>([]);

const handleQuery = () => {
  getDormitoryPage(queryParams).then(({ data }) => {
    dormitoryList.value = data.list;
    total.value = data.total;
  });
};
watch(
  () => queryParams.buildType,
  (value) => {
    buildOption({ type: value }).then(({ data }) => {
      options.value = data;
    });
  }
);

const type = {
  0: "warn",
  1: "success",
  2: "danger",
  3: "info",
};

function handleSelectionChange(selection: any) {
  ids.value = selection.map((item: any) => item.id);
}
function openDialog(id?: number) {
  dialog.visible = true;
  if (id) {
    dialog.title = "编辑宿舍";
    Object.assign(
      formData.value,
      dormitoryList.value.find((item) => item.id === id) || {}
    );
  } else {
    dialog.title = "新增宿舍";
  }
}

function closeDialog() {
  dialog.visible = false;
  resetForm();
}

function resetForm() {
  formData.value = {};
}
function getBuildById(id: number) {
  const build = options.value.find((item: Building) => item.value === id);
  return build ? build.label : "";
}

buildOption(null).then(({ data }) => {
  options.value = data;
});
handleQuery();
</script>

<style scoped></style>
