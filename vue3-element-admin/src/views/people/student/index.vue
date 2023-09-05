<template>
  <div class="app-container">
    <div class="search-container">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true">
        <el-form-item label="学生姓名" prop="name">
          <el-input
            v-model="queryParams.name"
            placeholder="学生姓名"
            clearable
            @keyup.enter="handleQuery"
          />
        </el-form-item>
        <el-form-item label="班级" prop="classId">
          <el-input
            v-model="queryParams.classId"
            placeholder="班级"
            clearable
            @keyup.enter="handleQuery"
          />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <dictionary v-model="queryParams.gender" type-code="gender" />
        </el-form-item>
        <el-form-item label="宿舍id" prop="dormitoryId">
          <el-input
            v-model="queryParams.dormitoryId"
            placeholder="宿舍id"
            clearable
            @keyup.enter="handleQuery"
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
              v-hasPerm="['sys:student:add']"
              type="success"
              @click="openDialog()"
            >
              <i-ep-plus />
              新增
            </el-button>
            <el-button
              type="danger"
              v-hasPerm="['sys:student:delete']"
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
                    <i-ep-download />下载模板</el-dropdown-item
                  >
                  <el-dropdown-item @click="openImportDialog">
                    <i-ep-top />导入数据</el-dropdown-item
                  >
                </el-dropdown-menu>
              </template>
            </el-dropdown>
            <el-button class="ml-3" @click="handleExport">
              <template #icon><i-ep-download /></template>
              导出</el-button
            >
          </div>
        </div>
      </template>
      <el-table
        v-loading="loading"
        highlight-current-row
        :data="studentList"
        :cell-style="cellStyle"
        border
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="学号" prop="id" width="100" />
        <el-table-column label="学生姓名" prop="studentName" width="200" />
        <el-table-column label="性别" prop="gender">
          <template #default="{ row }">
            {{ row.gender === 1 ? "男" : "女" }}
          </template>
        </el-table-column>
        <el-table-column label="年龄" prop="age" width="100" />
        <el-table-column label="手机号" prop="phone" align="center" />
        <el-table-column label="班级" prop="classId" align="center" />
        <el-table-column label="宿舍" align="center">
          <template #default="{ row }">
            {{ filterNone(row.buildName + row.dormitoryNumber) }}
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" align="center" width="150">
          <template #default="scope">
            <el-button
              v-hasPerm="['sys:student:edit']"
              type="primary"
              link
              size="small"
              @click.stop="openDialog(scope.row.id)"
            >
              <i-ep-edit />
              编辑
            </el-button>
            <el-button
              v-hasPerm="['sys:student:delete']"
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
        <el-form-item label="学号" prop="id">
          <el-input v-model="formData.id" disabled />
        </el-form-item>
        <el-form-item label="学生名称" prop="studentName">
          <el-input v-model="formData.studentName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="formData.gender">
            <el-radio :label="1">男</el-radio>
            <el-radio :label="2">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="formData.age" placeholder="请输入学生年龄" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="formData.phone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="班级" prop="classId">
          <el-input v-model="formData.classId" placeholder="请输入学生班级" />
        </el-form-item>
        <el-form-item label="宿舍楼" prop="buildName">
          <el-input v-model="formData.buildName" placeholder="请输入宿舍楼" />
        </el-form-item>
        <el-form-item label="宿舍号" prop="dormitoryNumber">
          <el-input
            v-model="formData.dormitoryNumber"
            placeholder="请输入宿舍号"
          />
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

<script setup lang="ts">
import { Student, StudentQuery } from "@/api/student/types";
import {
  addOrUpdateStudent,
  deleteStudent,
  getStudentPage,
} from "@/api/student";

const queryFormRef = ref(ElForm);
const dataFormRef = ref(ElForm);

const loading = ref(false);
const ids = ref<number[]>([]);
const total = ref(0);

const queryParams = reactive<StudentQuery>({
  pageNum: 1,
  pageSize: 10,
});

const dialog = reactive<DialogOption>({
  visible: false,
});

const studentList = ref<Student[]>([]);
const formData = reactive<Student>({});

const rules = reactive({
  studentName: [{ required: true, message: "请输入学生姓名", trigger: "blur" }],
  phone: [{ required: true, message: "请输入手机号", trigger: "blur" }],
});

const cellStyle = ({ row, column, rowIndex, columnIndex }) => {
  if (row[column.property] === null) {
    row[column.property] = "--";
  }
};

function filterNone(value: string) {
  return value ? value : "--";
}

/** 行复选框选中  */
function handleSelectionChange(selection: any) {
  ids.value = selection.map((item: any) => item.id);
}

/**
 * 查询
 */
function handleQuery() {
  loading.value = true;
  getStudentPage(queryParams)
    .then(({ data }) => {
      studentList.value = data.list;
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

function openDialog(id?: number) {
  dialog.visible = true;
  if (id) {
    dialog.title = "编辑学生";
    Object.assign(
      formData,
      studentList.value.find((item) => item.id === id) || {}
    );
  } else {
    dialog.title = "新增学生";
  }
}

function closeDialog() {
  dialog.visible = false;
  resetForm();
}

const handleSubmit = useThrottleFn(() => {
  dataFormRef.value.validate((valid: any) => {
    if (valid) {
      loading.value = true;
      addOrUpdateStudent(formData)
        .then(() => {
          ElMessage.success("新增/更新学生成功");
          closeDialog();
          resetQuery();
        })
        .finally(() => (loading.value = false));
    }
  });
}, 3000);

/**  重置字典类型表单 */
function resetForm() {
  dataFormRef.value.resetFields();
  dataFormRef.value.clearValidate();

  formData.id = undefined;
  formData.status = 1;
}

/** 删除用户 */
function handleDelete(id?: number) {
  const userIds = [id || ids.value].join(",");
  if (!userIds) {
    ElMessage.warning("请勾选删除项");
    return;
  }

  ElMessageBox.confirm("确认删除学生?", "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  }).then(function () {
    deleteStudent(userIds).then(() => {
      ElMessage.success("删除成功");
      resetQuery();
    });
  });
}

handleQuery();
</script>

<style scoped></style>
