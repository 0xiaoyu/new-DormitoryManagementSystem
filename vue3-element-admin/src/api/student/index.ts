import request from "@/utils/request";
import { Student, StudentQuery } from "@/api/student/types";

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
