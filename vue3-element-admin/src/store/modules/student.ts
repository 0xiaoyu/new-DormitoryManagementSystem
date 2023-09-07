import {defineStore} from "pinia";

import {loginApi, logoutApi} from "@/api/auth";
import {getUserInfo} from "@/api/user";
import {resetRouter} from "@/router";
import {store} from "@/store";

import {LoginData} from "@/api/auth/types";
import {UserInfo} from "@/api/user/types";

import {useStorage} from "@vueuse/core";
import {PayLog} from "@/api/Pay/types";
import {violationLog} from "@/api/violationLog/types";
import {getStudentInfo} from "@/api/student";
import {useUserStore} from "@/store/modules/user";

export const useStudentStore = defineStore("student", () => {
  // state
  const name = ref()
  const dormitory = ref();
  const electricity = ref("");
  const water = ref("");
  const payLogs = ref<Array<PayLog>>([]);
  const violationLogs = ref<Array<violationLog>>([]);

  // 获取信息(用户昵称、头像、角色集合、权限集合)
  function getSInfo() {
    return new Promise<UserInfo>((resolve, reject) => {
      getStudentInfo(useUserStore().userId)
        .then(({data}) => {
          name.value = data.name;
          dormitory.value = data.dormitory;
          electricity.value = data.electricity;
          water.value = data.water;
          payLog.value = data.payLogs;
          violations.value = data.violationLogs;
          resolve(data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  }


  // 重置
  function resetToken() {
    token.value = "";
    nickname.value = "";
    avatar.value = "";
    roles.value = [];
    perms.value = [];
  }

  return {
    name,
    dormitory,
    electricity,
    water,
    payLogs,
    violationLogs,
    getSInfo,
    resetToken,
  };
});

// 非setup
export function useUserStoreHook() {
  return useUserStore(store);
}
