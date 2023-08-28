import request from "@/utils/request";
import { AxiosPromise } from "axios";
import {
  RegistrationData,
  UserForm,
  UserInfo,
  UserPageVO,
  UserQuery,
} from "./types";
import { LoginData } from "@/api/auth/types";

const baseUrl = "/api/v1/users";

/**
 * 登录成功后获取用户信息（昵称、头像、权限集合和角色集合）
 */
export function getUserInfo(): AxiosPromise<UserInfo> {
  return request({
    url: baseUrl + "/me",
    method: "get",
  });
}

/**
 * 获取用户分页列表
 *
 * @param queryParams
 */
export function getUserPage(
  queryParams: UserQuery
): AxiosPromise<PageResult<UserPageVO[]>> {
  return request({
    url: baseUrl + "/page",
    method: "get",
    params: queryParams,
  });
}


/**
 * 添加用户
 *
 * @param data
 */
export function addUser(data: any) {
  return request({
    url: baseUrl,
    method: "post",
    data: data,
  });
}

/**
 * 修改用户
 *
 * @param id
 * @param data
 */
export function updateUser(id: number, data: UserForm) {
  return request({
    url: baseUrl + `/${id}`,
    method: "put",
    data: data,
  });
}

/**
 * 修改用户状态
 *
 * @param id
 * @param status
 */
export function updateUserStatus(id: number, status: number) {
  return request({
    url: baseUrl + `/${id}/status`,
    method: "patch",
    params: { status: status },
  });
}

/**
 * 修改用户密码
 *
 * @param id
 * @param password
 */
export function updateUserPassword(id: number, password: string) {
  return request({
    url: baseUrl + "/" + id + "/password",
    method: "patch",
    params: { password: password },
  });
}

/**
 * 删除用户
 *
 * @param ids
 */
export function deleteUsers(ids: string) {
  return request({
    url: baseUrl + "/" + ids,
    method: "delete",
  });
}

/**
 * 下载用户导入模板
 *
 * @returns
 */
export function downloadTemplateApi() {
  return request({
    url: baseUrl + "/template",
    method: "get",
    responseType: "arraybuffer",
  });
}

/**
 * 导出用户
 *
 * @param queryParams
 * @returns
 */
export function exportUser(queryParams: UserQuery) {
  return request({
    url: baseUrl + "/_export",
    method: "get",
    params: queryParams,
    responseType: "arraybuffer",
  });
}

/**
 * 导入用户
 *
 * @param file
 */
export function importUser(file: File) {
  const formData = new FormData();
  formData.append("file", file);
  return request({
    url: baseUrl + "/_import",
    method: "post",
    data: formData,
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
}

export function getEmailCode(email: string) {
  return request({
    url: baseUrl + "/getEmailVerifyCode",
    method: "get",
    params: { email: email, type: "REGISTER" },
  });
}

export function saveStudent(student: LoginData, emailCode: string) {
  return request({
    url: baseUrl + "/saveStudent",
    method: "post",
    params: { emailCode: emailCode },
    data: student,
  });
}

export function getByName(name: String) {
  return request({
    url: baseUrl + "/getByName",
    method: "get",
    params: { name: name },
  });
}

export function getRolesByUserId(userId: number) {
  return request({
    url: baseUrl + `/roles/${userId}`,
    method: "get",
  });
}
