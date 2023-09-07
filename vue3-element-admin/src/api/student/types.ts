import {PayLog} from "@/api/Pay/types";
import {violationLog} from "@/api/violationLog/types";

export interface StudentQuery extends PageQuery {
  /** 学生姓名 */
  name?: string;
  /** 学生id */
  id?: number;
  /**班级*/
  classId?: string;
  /*宿舍id*/
  dormitoryId?: number;
  /** 性别 */
  gender?: number;
}

export interface Student {
  /** 学生id */
  id: number;
  /** 学生姓名 */
  studentName: string;
  /**性别*/
  gender: number;
  // 年龄
  age: number;
  // 电话
  phone: string;
  // 班级id
  classId: number;
  // 宿舍名称
  buildName: string;
  // 宿舍号
  dormitoryNumber: string;
}
export interface StudentInfo{
  /** 学生姓名 */
  name: string;
  /** 宿舍 */
  dormitory: string;
  electricity: number;
  water: number;
  payLogs: Array<PayLog>;
  violationLogs: Array<violationLog>;
}
