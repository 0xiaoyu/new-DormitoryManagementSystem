import request from "@/utils/request";
import { PassLogPageQuery } from "@/api/accessLog/types";

const baseUrl = "/api/v1/accessLog";

export function authentication(token: String) {
  return request({
    url: baseUrl + "/authentication",
    method: "post",
    data: token,
  });
}

export function getPassPage(params: PassLogPageQuery) {
  return request({
    url: baseUrl + "/pageQuery",
    method: "get",
    params,
  });
}

export function deletePassLogByIds(ids: string) {
  return request({
    url: baseUrl,
    method: "delete",
    params: {
      ids,
    },
  });
}

export function getAccessTime() {
  return request({
    url: baseUrl + "/accessTime",
    method: "get",
  });
}

export function modifyTime(time: String) {
  return request({
    url: baseUrl + "/modifyTime",
    method: "put",
    params: {
      time: time,
    },
  });
}
