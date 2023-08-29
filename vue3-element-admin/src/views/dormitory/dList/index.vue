<template>
  <div class="app-container">
    <div class="search-container">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true">
        <el-form-item label="宿舍楼" prop="buildingId">
          <el-option-group>
        </el-form-item>
        <el-form-item label="楼层" prop="floor">
          <el-input-number
            v-model="queryParams.floor"
            placeholder="楼层"
            clearable
            :max="floorMax"
          />
        </el-form-item>
        <el-form-item label="宿舍号" prop="dormitoryNumber">
          <el-input-number
            v-model="queryParams.dormitoryNumber"
            placeholder="编号"
            clearable
            :max="numberMax"
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
<!--
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
    </el-dialog>-->

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
import {DormitoryPageQuery} from "@/api/dormitory/types";

const queryParams = reactive<DormitoryPageQuery>({
  pageNum: 1,
  pageSize: 20,
})


</script>

<style scoped></style>
