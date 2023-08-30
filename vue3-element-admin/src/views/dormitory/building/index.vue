<template>
  <div class="app-container">
    <div class="search-container">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true">
        <el-form-item label="宿舍楼类型" prop="type">
          <dictionary :typeCode="'building'" v-model="queryParams.type" />
        </el-form-item>
        <el-form-item label="宿舍楼名称" prop="buildName" style="width: 200px">
          <el-input v-model="queryParams.buildName" />
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
              v-hasPerm="['sys:building:add']"
              type="success"
              @click="openDialog()"
            >
              <i-ep-plus />
              新增
            </el-button>
            <el-button
              v-hasPerm="['sys:building:delete']"
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
        :data="buildingList"
        border
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="楼层名称" prop="buildName" />
        <el-table-column label="楼层类型" prop="dormitoryNumber" width="200">
          <template #default="{ row }">
            <dictionary
              v-model="row.type"
              :disabled="true"
              :typeCode="'building'"
            />
          </template>
        </el-table-column>
        <el-table-column label="经度" prop="latitude" width="150" />
        <el-table-column label="纬度" prop="longitude" width="150" />
        <el-table-column label="最大楼层" width="150">
          <template #default="{ row }">
            {{ row.maxroom / 100 }}
          </template>
        </el-table-column>
        <el-table-column label="最大房间" width="150">
          <template #default="{ row }">
            {{ row.maxroom % 100 }}
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
    </el-card>

    <el-dialog
      v-model="dialog.visible"
      :title="dialog.title"
      width="800px"
      @close="closeDialog"
    >
      <el-form
        ref="dataFormRef"
        :model="formData"
        :rules="rules"
        label-width="80px"
        size="large"
      >
        <el-form-item label="楼层名称" prop="buildName">
          <el-input v-model="formData.buildName" />
        </el-form-item>
        <el-form-item label="宿舍类型" prop="type">
          <dictionary v-model="formData.type" :typeCode="'building'" />
          <el-button type="primary" size="small" @click="openMapDialog"
            >选择位置</el-button
          >
        </el-form-item>
        <el-form-item label="经度" prop="latitude">
          <el-input v-model="formData.latitude" disabled />
        </el-form-item>
        <el-form-item label="纬度" prop="longitude">
          <el-input v-model="formData.longitude" disabled />
        </el-form-item>
        <el-form-item label="最大楼层" prop="water">
          <el-input v-model="formData.maxFloor" placeholder="请输入宿舍楼" />
        </el-form-item>
        <el-form-item label="最大房间" prop="water">
          <el-input
            v-model="formData.maxroom"
            maxlength="2"
            placeholder="请输入宿舍楼"
          />
        </el-form-item>
      </el-form>
      <el-dialog
        v-model="mapDialog.visible"
        :title="mapDialog.title"
        width="700px"
        @close="closeMapDialog"
      >
        <tmap-map
          :mapKey="mapConfig.mapKey"
          :events="mapConfig.events"
          :center="mapConfig.center"
          :zoom="mapConfig.zoom"
          :doubleClickZoom="mapConfig.doubleClickZoom"
          :control="mapConfig.control"
        />
      </el-dialog>
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
import { Building } from "@/api/build/types";
import { getBuildList } from "@/api/build";

const dataFormRef = ref(ElForm);
const queryFormRef = ref(ElForm);

const queryParams = reactive<Building>({});
const buildingList = ref<Array<Building>>([]);
const loading = ref(false);
const ids = ref<number[]>([]);
const dialog = reactive<DialogOption>({
  visible: false,
});
const mapDialog = reactive<DialogOption>({
  visible: false,
});
const formData = ref<Building>({});
const mapConfig = ref({
  mapKey: "CGABZ-3MH66-6VGST-MEMS3-K6U3V-DGBKA",
  zoom: 10,
  doubleClickZoom: true,
  events: {
    dblclick: (e: unknown) => {
      console.log(e);
    },
  },
  center: { lat: 30.290756, lng: 120.074387 },
  control: {
    scale: {},
    zoom: {
      position: "bottomRight",
    },
  },
});

handleQuery();
function handleQuery() {
  getBuildList(queryParams).then(({ data }) => {
    buildingList.value = data;
    console.log(buildingList);
  });
}

function resetQuery() {}

function openMapDialog() {
  if (formData.value.id) {
    mapDialog.title = "新建位置";
  } else {
    mapDialog.title = "修改位置";
  }
  mapDialog.visible = true;
}

function closeMapDialog(){
  mapDialog.visible = false;
}
function openDialog(id?: number) {
  dialog.visible = true;
  if (id) {
    dialog.title = "编辑宿舍楼";
    Object.assign(
      formData.value,
      buildingList.value.find((item) => item.id === id) || {}
    );
  } else {
    dialog.title = "新增宿舍楼";
  }
}

function closeDialog() {
  dialog.visible = false;
  resetForm();
}

function resetForm() {
  formData.value = {};
}
</script>

<style scoped></style>
