export interface violationLog {
  /** 违规记录id */
  id?: number;
  /** 违规学生id */
  studentId?: number;
  /**
   * 违规学生姓名
   */
  name?: string;
  /** 违规类型 */
  type?: number;
  /** 状态(0-正常；1-申诉；) */
  flag?: number;
  /** 违规详情 */
  detail?: string;
  /** 违规时间 */
  create_time?: string;
}

export interface ViolationLogPageQuery extends PageQuery{
  /** 违规学生姓名 */
  name?: name;
  /** 违规类型 */
  type?: number;
  /** 状态(0-正常；1-申诉；) */
  flag?: number;
}
