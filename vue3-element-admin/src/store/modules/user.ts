import {defineStore} from "pinia";

import {loginApi, logoutApi} from "@/api/auth";
import {getUserInfo} from "@/api/user";
import {resetRouter} from "@/router";
import {store} from "@/store";

import {LoginData} from "@/api/auth/types";
import {UserInfo} from "@/api/user/types";

import {useStorage} from "@vueuse/core";
import {useStudentStore} from "@/store/modules/student";

export const useUserStore = defineStore("user", () => {
    // state
    const id = ref()
    const userId = ref();
    const token = useStorage("accessToken", "");
    const nickname = ref("");
    const avatar = ref("");
    const roles = ref<Array<string>>([]); // 用户角色编码集合 → 判断路由权限
    const perms = ref<Array<string>>([]); // 用户权限编码集合 → 判断按钮权限

    /**
     * 登录调用
     *
     * @returns
     * @param loginData
     */
    function login(loginData: LoginData) {
        return new Promise<void>((resolve, reject) => {
            loginApi(loginData)
                .then((response) => {
                    const {tokenType, accessToken} = response.data;
                    token.value = tokenType + " " + accessToken; // Bearer eyJhbGciOiJIUzI1NiJ9.xxx.xxx
                    resolve();
                })
                .catch((error) => {
                    reject(error);
                });
        });
    }

    // 获取信息(用户昵称、头像、角色集合、权限集合)
    function getInfo() {
        return new Promise<UserInfo>((resolve, reject) => {
            getUserInfo()
                .then(({data}) => {
                    if (!data) {
                        return reject("Verification failed, please Login again.");
                    }
                    if (!data.roles || data.roles.length <= 0) {
                        reject("getUserInfo: roles must be a non-null array!");
                    }
                    id.value = data.id
                    userId.value = data.userId;
                    nickname.value = data.nickname;
                    avatar.value = 'https://yu-dormitory-avatar.oss-cn-beijing.aliyuncs.com/' + data.avatar;
                    roles.value = data.roles;
                    perms.value = data.perms;
                    if (roles.value.includes('STUDENT')) {
                      useStudentStore().getSInfo()
                    }
                    resolve(data);
                })
                .catch((error) => {
                    reject(error);
                });
        });
    }

    // 注销
    function logout() {
        return new Promise<void>((resolve, reject) => {
            logoutApi()
                .then(() => {
                    resetRouter();
                    resetToken();
                    location.reload(); // 清空路由
                    resolve();
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
        token,
        nickname,
        avatar,
        roles,
        perms,
        login,
        getInfo,
        logout,
        resetToken,
        id,
        /**
         * 当前登录用户ID
         */
        userId,
    };
});

// 非setup
export function useUserStoreHook() {
    return useUserStore(store);
}
