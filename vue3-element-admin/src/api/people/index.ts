import {peoplePage} from "@/api/people/types";
import request from "@/utils/request";

const baseUrl = "/api/v1/user";


export function getHousemasterList(params: peoplePage) {
    return request({
        url: baseUrl + '/page/housemaster',
        method: "get",
        params
    })
}

export function getRepairList(params: peoplePage) {
    return request({
        url: baseUrl + '/page/repair',
        method: "get",
        params
    })
}
