export interface Maintenance {
  // 维修id
  id?: number,
  // 宿舍id
  dormitoryId?: number,
  // 详情
  detail?: string,
  // 学生id
  student: number,
  // 维修人员id
  maintenancePersonId?: number,
  // 维修类型
  typeId: number,
  // 创建时间
  createTime?: string,
  // 维修时间
  updateTime?: string,
}


export interface MaintenPage extends PageQuery {
  // 宿舍id
  dormitoryId?: number,
  // 详情
  detail?: string,
  // 学生id
  student?: number,
  // 维修人员id
  maintenancePersonId?: number,
  // 维修类型
  typeId?: number,
}

export interface MaintenList {
  /** 维修单号 */
  id?: number;
  /** 学生名字 */
  studentName?: string;
  /** 维修人员名字 */
  repairName?: string;
  /** 维修人员电话 */
  repairPhone?: string;
  /** 学生电话 */
  studentPhone?: string;
  /** 维修类型 */
  type?: string;
  /** 创建时间 */
  createTime?: string;
  /** 维修状态 */
  status?: string;
  /** 更新时间 */
  updateTime?: string;
  /** 维修宿舍 */
  dormitory?: string;
  /** 维修宿舍楼id */
  buildingId?: number;
  /** 维修详情描述 */
  detail?: string;
}
