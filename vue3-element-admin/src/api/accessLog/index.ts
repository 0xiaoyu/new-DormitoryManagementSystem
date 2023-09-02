import request from "@/utils/request";

const baseUrl = "/api/v1/accessLog";

export function authentication(token: String) {
  return request({
    url: baseUrl + "/authentication",
    method: "post",
    data: token,
  });
}
