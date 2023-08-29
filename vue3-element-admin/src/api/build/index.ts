import request from "@/utils/request";
import { Building } from "@/api/build/types";

const baseUrl = "/api/building";

export function option(params: Building) {
  return request({
    url: `${baseUrl}/option`,
    method: "get",
    params,
  });
}
