<script lang="ts" setup>
import { useUserStoreHook } from "@/store/modules/user";
import QrcodeVue from "qrcode.vue";

const token = ref<String>(useUserStoreHook().token);
const size = ref<number>(500);
</script>

<template>
  <div class="qr-main">
    <qrcode-vue :size="size" :value="token" level="H" />
  </div>
</template>

<style scoped>
.qr-main {
  text-align: center;
  margin: auto;
}
</style>
