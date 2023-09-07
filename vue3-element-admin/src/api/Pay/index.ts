import request from '@/utils/request'

const baseUrl = "/api/v1/payLog"


export function getPayLog(){
  return request({
    url: baseUrl + "/page",
    method: 'get'
  })
}
