export interface PayLog{
  /**
   * 缴费记录id
   */
  id?: number;

  /**
   * 宿舍id
   */
  dormitoryId?: number;

  /**
   * 缴费金额
   */
  amount?:number;

  /**
   * 缴费者id
   */
  userId?: number

  /**
   * 创建时间
   */
  createTime?: number

  /**
   * 缴费时间
   */
  updateTime?: number

  /**
   * 订单状态 0 未支付，1完成
   */
  status?: number
  /**
   * 缴费类型
   */
  type?: string
}
