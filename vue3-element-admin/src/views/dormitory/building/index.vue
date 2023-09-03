<template>
  <div class="app-container">
    <div class="search-container">
      <el-form ref="queryFormRef" :inline="true" :model="queryParams">
        <el-form-item label="宿舍楼类型" prop="type">
          <dictionary v-model="queryParams.type" :typeCode="'building'" />
        </el-form-item>
        <el-form-item label="宿舍楼名称" prop="buildName" style="width: 200px">
          <el-input
            v-model="queryParams.buildName"
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
              v-hasPerm="['sys:building:add']"
              type="success"
              @click="openDialog()"
            >
              <i-ep-plus />
              新增
            </el-button>
            <el-button
              v-hasPerm="['sys:building:delete']"
              :disabled="ids.length === 0"
              type="danger"
              @click="handleDelete"
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
        :data="buildingList"
        border
        highlight-current-row
        @selection-change="handleSelectionChange"
      >
        <el-table-column align="center" type="selection" width="55" />
        <el-table-column label="楼层名称" prop="buildName" />
        <el-table-column label="楼层类型" prop="dormitoryNumber" width="200">
          <template #default="{ row }">
            {{
              buildingOption.filter((item) => item.value == row.type)[0].label
            }}
          </template>
        </el-table-column>
        <el-table-column label="经度" prop="latitude" width="150" />
        <el-table-column label="纬度" prop="longitude" width="150" />
        <el-table-column label="最大楼层" width="150">
          <template #default="{ row }">
            {{ (row.maxroom / 100).toFixed(0) }}
          </template>
        </el-table-column>
        <el-table-column label="最大房间" width="150">
          <template #default="{ row }">
            {{ row.maxroom % 100 }}
          </template>
        </el-table-column>

        <el-table-column align="center" fixed="right" label="操作" width="150">
          <template #default="scope">
            <el-button
              v-hasPerm="['sys:dormitory:edit']"
              link
              size="small"
              type="primary"
              @click.stop="openDialog(scope.row.id)"
            >
              <i-ep-edit />
              编辑
            </el-button>
            <el-popconfirm
              title="确定删除吗?"
              @confirm.stop="handleDelete(scope.row.id)"
            >
              <template #reference>
                <el-button
                  v-hasPerm="['sys:dormitory:delete']"
                  link
                  size="small"
                  type="primary"
                >
                  <i-ep-delete />
                  删除
                </el-button>
              </template>
            </el-popconfirm>
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
          <el-button type="primary" @click="openMapDialog">选择位置</el-button>
        </el-form-item>
        <el-form-item label="经度" prop="latitude">
          <el-input v-model="formData.latitude" disabled />
        </el-form-item>
        <el-form-item label="纬度" prop="longitude">
          <el-input v-model="formData.longitude" disabled />
        </el-form-item>
        <el-form-item label="最大楼层" prop="maxFloor">
          <el-input v-model="formData.maxFloor" placeholder="请输入宿舍楼" />
        </el-form-item>
        <el-form-item label="最大房间" prop="maxroom">
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
        <baidu-map
          :center="mapConfig.center"
          :scroll-wheel-zoom="mapConfig.scrollWheelZoom"
          :zoom="mapConfig.zoom"
          class="map"
          @click="mapConfig.click"
        >
          <bm-marker
            :dragging="true"
            :position="bmMark.position"
            animation="BMAP_ANIMATION_BOUNCE"
          />
        </baidu-map>
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
import { addBuild, deleteBuild, getBuildList } from "@/api/build";
import { getDictOptions } from "@/api/dict";
import { BmMark, RefType } from "@/types/utils";

const dataFormRef = ref<RefType>(ElForm);
const queryFormRef = ref<RefType>(ElForm);

const queryParams = reactive<Building>({});
const buildingList = ref<Array<Building>>([]);
const loading = ref(false);
const ids = ref<number[]>([]);
const buildingOption = ref<OptionType[]>([]);
getDictOptions("building").then((response) => {
  buildingOption.value = response.data;
});
const dialog = reactive<DialogOption>({
  visible: false,
});
const mapDialog = reactive<DialogOption>({
  visible: false,
});
const formData = ref<Building>({});
const mapConfig = ref({
  center: { lat: 28.225525204117517, lng: 112.92573134402001 },
  zoom: 18,
  scrollWheelZoom: true,
  click: (e: any) => {
    bmMark.value.position = e.point;
    formData.value.latitude = e.point.lat;
    formData.value.longitude = e.point.lng;
  },
});
const bmMark = ref<BmMark>({
  position: { lat: 28.225525204117517, lng: 112.92573134402001 },
});

const rules = reactive({
  buildName: [{ required: true, message: "请输入楼层名称", trigger: "blur" }],
  type: [{ required: true, message: "请输入楼层类型", trigger: "blur" }],
  latitude: [{ required: true, message: "请选择位置", trigger: "blur" }],
  maxFloor: [{ required: true, message: "请输入最大楼层数", trigger: "blur" }],
  maxroom: [{ required: true, message: "请输入最大房间号", trigger: "blur" }],
});

function handleSubmit() {
  dataFormRef.value.validate((valid: any) => {
    if (valid) {
      const maxroom = formData.value.maxroom;
      formData.value.maxroom = formData.value.maxFloor + maxroom;
      addBuild(formData.value).then(() => {
        ElMessage.success(formData.value.id ? "修改成功!!" : "新增成功!!");
        closeDialog();
        handleQuery();
      });
    }
  });
}

function handleDelete(id?: number) {
  ElMessageBox.confirm("你确定要删除吗?", "删除", {
    confirmButtonText: "删除",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      if (!id) {
        ids.value = ids.value.push(id);
      }
      const s: string = ids.value.join(",");
      deleteBuild(s).then(() => {
        ElMessage.success("删除成功");
        buildingList.value = buildingList.value.filter(
          (item) => !ids.value.includes(item.id)
        );
      });
    })
    .catch(() => {
      ElMessage.info("取消删除");
    });
}

function handleSelectionChange(selection: any) {
  ids.value = selection.map((item: any) => item.id);
}

handleQuery();

function handleQuery() {
  getBuildList(queryParams).then(({ data }) => {
    buildingList.value = data;
  });
}

function resetQuery() {
  queryFormRef.value.resetFields();
  console.log(queryFormRef.value);
  handleQuery();
}

function openMapDialog() {
  if (formData.value.id) {
    mapDialog.title = "新建位置";
  } else {
    mapDialog.title = "修改位置";
  }
  mapDialog.visible = true;
}

function closeMapDialog() {
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
    formData.value.maxFloor = (formData.value.maxroom / 100).toFixed(0);
    formData.value.maxroom = formData.value.maxroom % 100;
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

<style scoped>
.map {
  width: 100%;
  height: 300px;
}
</style>
