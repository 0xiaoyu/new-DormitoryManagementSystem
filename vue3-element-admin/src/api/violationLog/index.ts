import Request from '@/utils/request'

const baseUrl = "/api/v1/violationLog"


export function getViolationLogList(params: any) {
  return Request({
    url: baseUrl,
    method: 'get',
    params
  })
}
