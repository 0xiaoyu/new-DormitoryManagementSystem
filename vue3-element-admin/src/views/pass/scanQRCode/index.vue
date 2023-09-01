<template>
  <div class="content">
    <el-row>
      <el-col :span="12">
        <qr-stream @decode="onDecode" class="mb" style="width: 600px">
          <div style="color: red" class="frame"></div>
        </qr-stream>
        <qr-capture @decode="onDecode" class="mb">
          <div style="color: red" class="frame"></div>
        </qr-capture>
      </el-col>
      <el-col :span="12">
        <span>{{ name }}</span>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts" setup>
import { QrCapture, QrStream } from "vue3-qr-reader";
import { authentication } from "@/api/accessLog";

const name = ref();

function onDecode(token) {
  if (token === "") return;
  authentication(token)
    .then(({ data }) => {
      name.value = data;
    })
    .catch(({ msg }) => {
      name.value = msg;
    });
}
</script>

<style scoped></style>
