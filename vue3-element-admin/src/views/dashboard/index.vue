<script lang="ts" setup>
import { useUserStore } from "@/store/modules/user";

import admin from "./admin.vue";
import studentIofo from "@/views/student/dormitoryInfo/index.vue";
import { SysUser } from "@/api/user/types";
import type { UploadProps } from "element-plus";
import { getEmailCode, modifyInfo } from "@/api/user";

defineOptions({
  // eslint-disable-next-line
  name: "Dashboard",
  inheritAttrs: false,
});

const userStore = useUserStore();
const username = userStore.nickname;
const roles = userStore.roles;
const date: Date = new Date();
const form = ref<SysUser>({
  name: userStore.nickname,
  id: userStore.id,
  userId: userStore.userId,
  email: "",
  avatar: userStore.avatar,
  code: "",
});

const greetings = computed(() => {
  const hours = date.getHours();
  if (hours >= 6 && hours < 8) {
    return "晨起披衣出草堂，轩窗已自喜微凉🌅！";
  } else if (hours >= 8 && hours < 12) {
    return `上午好${username}🌞！`;
  } else if (hours >= 12 && hours < 18) {
    return `下午好${username}☕！`;
  } else if (hours >= 18 && hours < 24) {
    return `晚上好${username}🌃！`;
  } else if (hours >= 0 && hours < 6) {
    return `偷偷向银河要了一把碎星，只等你闭上眼睛撒入你的梦中，晚安${username}🌛！`;
  }
});

const dialogFormVisible = ref(false);

const headers = reactive({
  Authorization: userStore.token,
});

const email = ref();
const modifyE = ref(true);

const emailTime = ref(60);
const isDisposed = ref(false);

function getEmail() {
  const regEmail = /[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+/;
  if (!regEmail.test(email.value)) {
    ElMessage.error("电子邮件格式不正确");
    return;
  }
  startTime();
  if (isDisposed.value) {
    return;
  }
  getEmailCode(email.value, "RESET_PASSWORD").then(() => {
    ElMessage.success("验证码已发送至邮箱，请注意查收");
    modifyE.value = false;
    isDisposed.value = true;
    sessionStorage.setItem("startTimeLogin", String(new Date().getTime()));
    handleTimeChange();
  });
}

function startTime() {
  const startTime = Number(sessionStorage.getItem("startTimeLogin"));
  const nowTime = new Date().getTime();
  if (startTime) {
    const time = 60 - (nowTime - startTime) / 1000;
    if (time < 0) {
      isDisposed.value = false;
      emailTime.value = 60;
      sessionStorage.removeItem("startTimeLogin");
    } else {
      emailTime.value = Number(time.toFixed(0));
      isDisposed.value = true;
    }
  } else {
    emailTime.value = 60;
    isDisposed.value = false;
  }
}

const handleTimeChange = () => {
  if (emailTime.value <= 0) {
    isDisposed.value = false;
    emailTime.value = 60;
    sessionStorage.removeItem("startTimeLogin");
  } else {
    setTimeout(() => {
      emailTime.value--;
      handleTimeChange();
    }, 1000);
  }
};

const handleAvatarSuccess: UploadProps["onSuccess"] = (
  response,
  uploadFile
) => {
  form.value.avatar = response.data;
};

const imageType = ["image/jpeg", "image/png", "image/gif"];
const beforeAvatarUpload: UploadProps["beforeUpload"] = (rawFile) => {
  if (!rawFile.type) {
    ElMessage.error("错误文件");
    return false;
  } else if (!imageType.includes(rawFile.type)) {
    ElMessage.error("支支持jpeg/png/gif格式的图片");
    return false;
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error("图片大小不能大于2MB");
    return false;
  }
  return true;
};

function modifyUser() {
  if (form.value.name === "") {
    ElMessage.error("用户名不能为空");
    return;
  }
  if (!modifyE.value) {
    console.log(modifyE);
    if (
      form.value.email !== "" &&
      form.value.password !== "" &&
      form.value.code === ""
    ) {
      ElMessage.error("验证码不能为空");
      return;
    }
  }
  modifyInfo({ email: email.value, code: form.value.code }, form.value).then(
    () => {
      ElMessage.success("修改成功");
      dialogFormVisible.value = false;
      userStore.logout();
    }
  );
}

onMounted(() => {
  startTime();
  handleTimeChange();
});
</script>

<template>
  <div class="dashboard-container">
    <!-- github角标 -->
    <!--    <github-corner class="github-corner"/>-->

    <!-- 用户信息 -->
    <el-row class="mb-8">
      <el-card class="w-full">
        <div class="flex justify-between flex-wrap">
          <div class="flex items-center">
            <img :src="userStore.avatar" class="user-avatar"  alt=""/>
            <span class="ml-[10px] text-[16px]">
              {{ userStore.nickname }}
            </span>
          </div>

          <div class="leading-[40px]">
            {{ greetings }}
          </div>

          <div class="space-x-2 flex items-center justify-end">
            <!--            <el-link
                          target="_blank"
                          type="danger"
                          href="https://blog.csdn.net/u013737132/article/details/130191394"
                          >💥官方从零到一文档</el-link
                        >-->
            <el-divider direction="vertical" />
            <el-link
              target="_blank"
              type="success"
              @click="dialogFormVisible = true"
              >修改个人信息
            </el-link>
            <el-divider direction="vertical" />
            <!--            <el-link
                          target="_blank"
                          type="primary"
                          href="https://github.com/youlaitech"
                          >GitHub
                        </el-link>-->
          </div>
        </div>
      </el-card>
    </el-row>

    <el-dialog v-model="dialogFormVisible" title="Shipping address">
      <el-form :model="form">
        <el-form-item label="修改头像">
          <el-upload
            :before-upload="beforeAvatarUpload"
            :headers="headers"
            :on-success="handleAvatarSuccess"
            :show-file-list="false"
            action="http://localhost:8080/api/v1/files/avatar"
            class="avatar-uploader"
          >
            <img :src="form.avatar" class="avatar" />
          </el-upload>
        </el-form-item>
        <el-form-item label="用户名：">
          <el-input v-model="form.name" autocomplete="off" />
        </el-form-item>
        修改邮箱：
        <el-form-item label="原邮箱： ">
          <el-row style="width: 100%">
            <el-col :span="21">
              <el-input v-model="email" autocomplete="off" />
            </el-col>
            <el-col :span="2">
              <el-button
                :disabled="isDisposed"
                type="primary"
                @click="getEmail"
              >
                {{ isDisposed ? `${emailTime}s后重新获取` : "获取验证码" }}
              </el-button>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item label="验证码：">
          <el-input
            v-model="form.code"
            :disabled="modifyE"
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="修改邮箱：">
          <el-input
            v-model="form.email"
            :disabled="modifyE"
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="修改密码： ">
          <el-input
            v-model="form.password"
            :disabled="modifyE"
            autocomplete="off"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="modifyUser"> 确定修改 </el-button>
        </span>
      </template>
    </el-dialog>

    <admin v-if="roles.includes('ROOT')" />
    <studentIofo v-if="roles.includes('STUDENT')" />
  </div>
</template>

<style lang="scss" scoped>
.dashboard-container {
  position: relative;
  padding: 24px;

  .user-avatar {
    width: 40px;
    height: 40px;
    border-radius: 50%;
  }

  .github-corner {
    position: absolute;
    top: 0;
    right: 0;
    z-index: 99;
    border: 0;
  }

  .svg-icon {
    fill: currentcolor !important;
  }
}
</style>
