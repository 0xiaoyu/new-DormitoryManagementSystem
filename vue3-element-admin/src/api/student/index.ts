import request from "@/utils/request";
import {Student, StudentInfo, StudentQuery} from "@/api/student/types";
import {Axios} from "axios";

const baseUrl = "/api/v1/student";

export function getStudentPage(params: StudentQuery) {
  return request({
    url: `${baseUrl}/page`,
    method: "get",
    params,
  });
}

export function addOrUpdateStudent(data: Student) {
  return request({
    url: `${baseUrl}`,
    method: "patch",
    data,
  });
}

export function verifyStudent(data: Student) {
  return request({
    url: `${baseUrl}/verify`,
    method: "post",
    data,
  });
}

/**
 * 删除学生
 *
 * @param ids
 */
export function deleteStudent(ids: string) {
  return request({
    url: baseUrl + "/" + ids,
    method: "delete",
  });
}

export function getStudentCount(){
  return request({
    url: `${baseUrl}/count`,
    method: "get",
  })
}

/**
 * 查询学生详情
 * @param id 学生id
 */
export function getStudentInfo(id:number){
  return request({
    url: `${baseUrl}/info/${id}`,
    method: "get",
  })
}
