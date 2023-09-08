<template>
  <div class="login-container">
    <el-card class="el-card">
      <el-form
        ref="loginFormRef"
        :class="{ 'login-form': isLogin, 'register-form': !isLogin }"
        :model="loginData"
        :rules="loginRules"
      >
        <div class="flex text-white items-center py-4 title-wrap">
          <span v-if="isLogin" class="text-2xl flex-1 text-center title">
            欢迎登录
          </span>
          <span v-else class="text-2xl flex-1 text-center title">
            欢迎注册
          </span>
          <span
            v-show="isLogin"
            class="text-white text-sm cursor-pointer"
            @click="openVerifyDialog"
          >
            学生注册
          </span>
          <lang-select class="text-white! cursor-pointer" />
        </div>
        <el-form-item prop="username">
          <div class="p-2 text-white">
            <svg-icon icon-class="user" />
          </div>
          <el-input
            ref="username"
            v-model="loginData.username"
            v-shake
            :placeholder="isLogin ? '用户名/邮箱' : '用户名'"
            class="flex-1"
            name="username"
            size="large"
            @blur="checkName"
          />
        </el-form-item>
        <el-tooltip
          :disabled="isCapslock === false"
          content="Caps lock is On"
          placement="right"
        >
          <el-form-item prop="password">
            <span class="p-2 text-white">
              <svg-icon icon-class="password" />
            </span>
            <el-input
              v-model="loginData.password"
              v-shake
              :type="passwordVisible === false ? 'password' : 'input'"
              class="flex-1"
              name="password"
              placeholder="密码"
              size="large"
              @keyup="checkCapslock"
              @keyup.enter="handleLogin"
            />
            <span class="mr-2" @click="passwordVisible = !passwordVisible">
              <svg-icon
                :icon-class="!passwordVisible ? 'eye' : 'eye-open'"
                class="text-white cursor-pointer"
              />
            </span>
          </el-form-item>
        </el-tooltip>

        <!-- 验证码 -->
        <el-form-item prop="verifyCode" v-if="isLogin">
          <span class="p-2 text-white">
            <svg-icon icon-class="verify_code" />
          </span>
          <el-input
            v-model="loginData.verifyCode"
            v-shake
            :placeholder="'请输入验证码'"
            auto-complete="off"
            class="w-[60%]"
            @keyup.enter="handleLogin"
          />

          <div class="captcha">
            <img :src="captchaBase64" alt="" @click="getCaptcha" />
          </div>
        </el-form-item>
        <template v-else>
          <el-form-item prop="email">
            <span class="p-2 text-white">
              <svg-icon icon-class="verify_code" />
            </span>
            <el-input
              v-model="loginData.email"
              v-shake
              :placeholder="'请输入邮箱'"
              auto-complete="off"
              class="w-[60%]"
              @keyup.enter="handleLogin"
            />
            <el-button
              :disabled="isDisposed"
              type="primary"
              @click="getEmail('REGISTER')"
            >
              {{ isDisposed ? `${emailTime}s后重新获取` : "获取验证码" }}
            </el-button>
          </el-form-item>
          <el-form-item prop="verifyCode">
            <el-input
              ref="verifyCode"
              v-model="loginData.verifyCode"
              v-shake
              placeholder="输入验证码"
              class="flex-1"
              name="emailCode"
              size="large"
            />
          </el-form-item>
        </template>
        <el-button
          :loading="loading"
          class="w-full"
          size="default"
          type="primary"
          @click.prevent="handleLogin"
          >{{ isLogin ? "登录" : "注册" }}
        </el-button>
        <div @click="openReset">
          <a style="float: right; color: #ffffff"> 忘记密码？ </a>
        </div>
      </el-form>
    </el-card>

    <el-dialog
      v-model="verifyDialog.visible"
      :title="verifyDialog.title"
      append-to-body
      width="600px"
      @close="closeVerifyDialog"
      style="background-color: #2d3a4b"
    >
      <template #header>
        <div style="color: #90caf9">
          {{ verifyDialog.title }}
        </div>
      </template>
      <el-form
        ref="studentVerifyFormRef"
        :model="studentVerifyForm"
        :rules="studentVerifyRules"
        label-width="80px"
      >
        <el-form-item label="学号" prop="id">
          <el-input
            v-model.number="studentVerifyForm.id"
            placeholder="请输入学号"
          />
        </el-form-item>
        <el-form-item label="学生姓名" prop="studentName">
          <el-input
            v-model="studentVerifyForm.studentName"
            placeholder="请输入学生姓名"
          />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input
            v-model.number="studentVerifyForm.phone"
            placeholder="请输入学生手机号"
          />
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input
            v-model.number="studentVerifyForm.age"
            placeholder="请输入学生年龄"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="verify">验 证</el-button>
          <el-button @click="closeVerifyDialog">取 消</el-button>
        </div>
      </template>
    </el-dialog>
    <el-dialog
      v-model="resetDialog.visible"
      :title="resetDialog.title"
      append-to-body
      width="500px"
      @close="closeResetDialog"
      style="background-color: #2d3a4b"
    >
      <template #header>
        <div style="color: #90caf9">
          {{ verifyDialog.title }}
        </div>
      </template>
      <el-form
        ref="resetFormRef"
        :model="resetForm"
        :rules="resetRules"
        label-width="40px"
      >
        <el-form-item prop="username">
          <div class="p-2 text-white">
            <svg-icon icon-class="user" />
          </div>
          <el-input
            ref="username"
            v-model="resetForm.username"
            v-shake
            placeholder=" 用户名"
            class="flex-1"
            name="username"
            size="large"
          />
        </el-form-item>
        <el-form-item prop="email">
          <span class="p-2 text-white">
            <svg-icon icon-class="verify_code" />
          </span>
          <el-input
            v-model="resetForm.email"
            v-shake
            :placeholder="'请输入邮箱'"
            auto-complete="off"
            class="w-[60%]"
            @keyup.enter="handleLogin"
          />
          <el-button
            :disabled="isDisposed"
            type="primary"
            @click="getEmail('RESET_PASSWORD')"
          >
            {{ isDisposed ? `${emailTime}s后重新获取` : "获取验证码" }}
          </el-button>
        </el-form-item>
        <el-form-item prop="verifyCode">
          <el-input
            ref="verifyCode"
            v-model="resetForm.verifyCode"
            v-shake
            style="margin-left: 35px"
            placeholder="输入验证码"
            class="flex-1"
            name="emailCode"
            size="large"
          />
        </el-form-item>
        <el-form-item prop="password">
          <span class="p-2 text-white">
            <svg-icon icon-class="password" />
          </span>
          <el-input
            v-model="resetForm.password"
            v-shake
            :type="passwordVisible === false ? 'password' : 'input'"
            class="flex-1"
            name="password"
            placeholder="新密码"
            size="large"
            @keyup="checkCapslock"
            @keyup.enter="handleLogin"
          />
          <span class="mr-2" @click="passwordVisible = !passwordVisible">
            <svg-icon
              :icon-class="!passwordVisible ? 'eye' : 'eye-open'"
              class="text-white cursor-pointer"
            />
          </span>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="reset">重 置</el-button>
          <el-button @click="closeResetDialog">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import router from "@/router";
import LangSelect from "@/components/LangSelect/index.vue";
import SvgIcon from "@/components/SvgIcon/index.vue";

// 状态管理依赖
import { useUserStore } from "@/store/modules/user";

// API依赖
import { LocationQuery, LocationQueryValue, useRoute } from "vue-router";
import { getCaptchaApi } from "@/api/auth";
import { LoginData } from "@/api/auth/types";
import {
  getByName,
  getEmailCode,
  resetPassword,
  saveStudent,
} from "@/api/user";
import { Student } from "@/api/student/types";
import { verifyStudent } from "@/api/student";
import { RefType } from "@/types/utils";

const userStore = useUserStore();
const route = useRoute();

const isLogin = ref(true);
/**
 * 按钮loading
 */
const loading = ref(false);
/**
 * 是否大写锁定
 */
const isCapslock = ref(false);
/**
 * 密码是否可见
 */
const passwordVisible = ref(false);
/**
 * 验证码图片Base64字符串
 */
const captchaBase64 = ref();

/**
 * 登录表单引用
 */
const loginFormRef = ref<RefType>(ElForm);
const resetFormRef = ref<RefType>(ElForm);

const loginData = ref<LoginData>({
  username: "",
  password: "",
});

const studentVerifyFormRef = ref<RefType>(ElForm); // 用户表单
const studentVerifyForm = reactive<Student>({});
const resetForm = reactive<LoginData>({});
const verifyDialog = reactive<DialogOption>({
  title: "学生认证",
  visible: false,
});
const studentVerifyRules = reactive({
  id: [
    { required: true, trigger: "blur", message: "请输入学号" },
    { type: "number", trigger: "change", message: "请输入数字" },
  ],
  studentName: [{ required: true, trigger: "blur", message: "请输入学生姓名" }],
  phone: [
    { required: true, trigger: "blur", message: "请输入学生手机号" },
    {
      pattern: /^1[3456789][0-9]\d{8}$/,
      type: "number",
      trigger: "change",
      message: "请输入正确的手机号",
    },
  ],
  age: [
    { required: true, trigger: "blur", message: "请输入学生年龄" },
    { type: "number", trigger: "change", message: "请输入数字" },
    { pattern: /^[12][0-9]$/, trigger: "blur", message: "请输入正常的年龄" },
  ],
});
const loginRules = {
  username: [{ required: true, trigger: "blur", message: "请输入用户名" }],
  password: [
    {
      required: true,
      trigger: "blur",
      validator: passwordValidator,
      message: "请输入密码",
    },
  ],
  email: [
    {
      required: true,
      trigger: "blur",
      validator: emailValidator,
      message: "请输入邮箱",
    },
  ],
  verifyCode: [{ required: true, trigger: "blur", message: "请输入验证码" }],
};
const resetRules = {};
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

const emailTime = ref(60);
const isDisposed = ref(false);
const resetDialog = reactive<DialogOption>({
  title: "找回密码",
  visible: false,
});

function openReset() {
  resetDialog.visible = true;
}

function reset() {
  resetFormRef.value.validate((valid) => {
    if (valid) {
      resetPassword(resetForm).then(() => {
        loginData.value.username = resetForm.username;
        loginData.value.password = resetForm.password;
        resetDialog.visible = false;
      });
    }
  });
}

function openVerifyDialog() {
  verifyDialog.visible = true;
}

function resetVerifyForm() {
  studentVerifyFormRef.value.resetFields();
  studentVerifyFormRef.value.clearValidate();
}

function closeVerifyDialog() {
  verifyDialog.visible = false;
  resetVerifyForm();
}

function closeResetDialog() {
  resetDialog.visible = false;
  console.log(resetFormRef.value);
  resetFormRef.value.resetFields();
  resetFormRef.value.clearValidate();
}

function checkName() {
  if (isLogin) return;
  getByName(loginData.value.username).then(({ data }) => {
    if (data) {
      ElMessage.error("用户名已存在");
    }
  });
}

function verify() {
  studentVerifyFormRef.value.validate((valid: boolean) => {
    if (valid) {
      const loading = ElLoading.service({
        lock: true,
        text: "Loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      verifyStudent(studentVerifyForm)
        .then(({ data }) => {
          if (data) {
            ElMessage.success("认证成功,请输入你的账号信息");
            isLogin.value = false;
            loginData.value.userId = studentVerifyForm.id;
          }
        })
        .finally(() => {
          verifyDialog.visible = false;
          loading.close();
        });
    }
  });
}

/**
 * 密码校验器
 */
function passwordValidator(rule: any, value: any, callback: any) {
  if (value.length < 6) {
    callback(new Error("The password can not be less than 6 digits"));
  } else {
    callback();
  }
}

/**
 * 邮箱验证器
 */
function emailValidator(rule: any, value: any, callback: any) {
  console.log(value);
  const regEmail = /[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+/;
  if (!regEmail.test(value)) {
    callback(new Error("电子邮件格式不正确"));
  } else {
    callback();
  }
}

/**
 * 检查输入大小写状态
 */
function checkCapslock(e: any) {
  const { key } = e;
  isCapslock.value = key && key.length === 1 && key >= "A" && key <= "Z";
}

/**
 * 获取验证码
 */
function getCaptcha() {
  getCaptchaApi().then(({ data }) => {
    const { verifyCodeBase64, verifyCodeKey } = data;
    loginData.value.verifyCodeKey = verifyCodeKey;
    captchaBase64.value = verifyCodeBase64;
  });
}

function register() {
  saveStudent(loginData.value, <string>loginData.value.verifyCode).then(() => {
    ElMessage.success("注册成功");
    isLogin.value = true;
  });
}

/**
 * 登录
 */
function handleLogin() {
  loginFormRef.value.validate((valid: boolean) => {
    if (valid) {
      loading.value = true;
      if (!isLogin.value) {
        register();
        return;
      }
      userStore
        .login(loginData.value)
        .then(() => {
          const query: LocationQuery = route.query;

          const redirect = (query.redirect as LocationQueryValue) ?? "/";

          const otherQueryParams = Object.keys(query).reduce(
            (acc: any, cur: string) => {
              if (cur !== "redirect") {
                acc[cur] = query[cur];
              }
              return acc;
            },
            {}
          );

          router.push({ path: redirect, query: otherQueryParams });
        })
        .catch(() => {
          // 验证失败，重新生成验证码
          getCaptcha();
        })
        .finally(() => {
          loading.value = false;
        });
    }
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

function getEmail(type: string) {
  let email = "";
  if (type === "RESET_PASSWORD") {
    email = resetForm.email || "";
  } else {
    email = loginData.value.email || "";
  }
  const regEmail = /[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+/;
  if (!regEmail.test(email)) {
    ElMessage.error("电子邮件格式不正确");
    return;
  }
  startTime();
  if (isDisposed.value) {
    return;
  }
  getEmailCode(email, type).then(() => {
    ElMessage.success("验证码已发送");
    isDisposed.value = true;
    sessionStorage.setItem("startTimeLogin", String(new Date().getTime()));
    handleTimeChange();
  });
}

onMounted(() => {
  startTime();
  handleTimeChange();
  getCaptcha();
});
</script>

<style lang="scss" scoped>
/*
  进入和离开动画可以使用不同
  持续时间和速度曲线。
*/
.slide-fade-enter-active {
  transition: all 0.8s ease-out;
}

.slide-fade-leave-active {
  transition: all 0.3s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter-from,
.slide-fade-leave-to {
  transform: translateX(20px);
  opacity: 0;
}

.login-container {
  width: 100%;
  min-height: 100%;
  overflow: hidden;
  background-color: #2d3a4b;

  .el-card {
    margin: 10% 15%;
    border-radius: 25px;
    background-color: rgba(255, 255, 255, 0.1);
  }

  .title-wrap {
    filter: contrast(30);

    .title {
      letter-spacing: 4px;
      animation: showup 3s forwards;
    }

    @keyframes showup {
      0% {
        letter-spacing: -20px;
      }

      100% {
        letter-spacing: 4px;
      }
    }
  }

  .login-form {
    width: 510px;
    max-width: 100%;
    padding: 10% 35px 8%;
    margin: 0 auto;
    overflow: hidden;

    .captcha {
      height: 100%;
      position: absolute;
      top: 0;
      right: 0;

      img {
        width: 120px;
        height: 48px;
        cursor: pointer;
      }
    }
  }

  .register-form {
    width: 510px;
    max-width: 100%;
    padding: 0 35px 8%;
    margin: 0 auto;
    overflow: hidden;
  }
}

.el-form-item {
  background: rgb(0 0 0 / 10%);
  border: 1px solid rgb(255 255 255 / 10%);
  border-radius: 5px;
}

.el-input {
  background: transparent;

  // 子组件 scoped 无效，使用 :deep
  :deep(.el-input__wrapper) {
    padding: 0;
    background: transparent;
    box-shadow: none;

    .el-input__inner {
      color: #fff;
      background: transparent;
      border: 0;
      border-radius: 0;
      caret-color: #fff;

      &:-webkit-autofill {
        box-shadow: 0 0 0 1000px transparent inset !important;
        -webkit-text-fill-color: #fff !important;
      }

      // 设置输入框自动填充的延迟属性
      &:-webkit-autofill,
      &:-webkit-autofill:hover,
      &:-webkit-autofill:focus,
      &:-webkit-autofill:active {
        transition: color 99999s ease-out, background-color 99999s ease-out;
        transition-delay: 99999s;
      }
    }
  }
}
</style>
