import {Maintenance, MaintenPage} from "@/api/maintenance/types";
import request from "@/utils/request";

const baseUrl = "/api/v1/tbMaintenance"

export function addRapir(data:Maintenance){
  return request({
    url: baseUrl,
    method: 'post',
    data
  })
}

export function pageRapir(data: MaintenPage){
  return request({
    url: baseUrl + '/page/condition',
    method: 'post',
    data
  })
}
