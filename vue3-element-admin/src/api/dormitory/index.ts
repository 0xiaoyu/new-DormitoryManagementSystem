import request from "@/utils/request";
import { DormitoryPageQuery } from "@/api/dormitory/types";

const baseUrl = "/api/v1/dormitory";

export function getDormitoryPage(params: DormitoryPageQuery) {
  return request({
    url: baseUrl + "/page",
    method: "get",
    params,
  });
}
export function updateWater(id: number, water: number) {
  return request({
    url: baseUrl + `/${id}/updateWater`,
    method: "get",
    params: { water },
  });
}

export function updateElectric(id: number, electricity: number) {
  return request({
    url: baseUrl + `/${id}/updateElectric`,
    method: "get",
    params: { electricity },
  });
}
