import request from "@/utils/request";
import { Building } from "@/api/build/types";

const baseUrl = "/api/v1/building";

export function buildOption(params: Building | null) {
  return request({
    url: `${baseUrl}/option`,
    method: "get",
    params,
  });
}

export function getBuildBy(params: Building) {
  return request({
    url: `${baseUrl}/get`,
    method: "get",
    params,
  });
}

export function getBuildList(params: Building) {
  return request({
    url: baseUrl,
    method: "get",
    params,
  });
}

export function addBuild(data: Building) {
  return request({
    url: baseUrl,
    method: "post",
    data,
  });
}

export function deleteBuild(id: number) {
  return request({
    url: `baseUrl/${id}`,
    method: "delete",
  });
}
