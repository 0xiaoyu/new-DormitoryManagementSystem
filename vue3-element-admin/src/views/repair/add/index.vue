<script lang="ts" setup>
import { Maintenance } from "@/api/maintenance/types";
import { useStudentStore } from "@/store/modules/student";
import { useUserStore } from "@/store/modules/user";
import { addRapir } from "@/api/maintenance";

const form = ref<Maintenance>({ typeId: '' });
const student = useStudentStore();

function submit(){
  form.value.student = useUserStore().userId;
  addRapir(form.value).then(() => {
    ElMessage.success("提交成功");
  });
}
</script>

<template>
  <el-card style="margin: 5% 8%">
    <template #header>
      <div class="card-header">
        <span>宿舍报修</span>
      </div>
    </template>
    <el-form :model="form" label-width="120px">
      <el-form-item label="宿舍名称">
        <el-input v-model="student.dormitory" disabled/>
      </el-form-item>
      <el-form-item label="维修类型">
        <dictionary v-model="form.typeId" :typeCode="'maintain'"/>
      </el-form-item>
      <el-form-item label="维修类型">
        <el-input
          v-model="form.detail"
          :rows="5"
          placeholder="请描述维修详情"
          type="textarea"
        />
      </el-form-item>
      <el-form-item style="float: right">
        <el-button type="primary" @click="submit">提交</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<style scoped>
</style>
