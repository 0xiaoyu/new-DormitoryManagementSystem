/**
 * 登录用户信息
 */
export interface UserInfo {
  id:number
  userId: number;
  nickname: string;
  avatar: string;
  roles: string[];
  perms: string[];
}

/**
 * 用户查询对象类型
 */
export interface UserQuery extends PageQuery {
  keywords?: string;
  status?: number;
  deptId?: number;
}

/**
 * 用户分页对象
 */
export interface UserPageVO {
  /**
   * 用户头像地址
   */
  avatar?: string;
  /**
   * 创建时间
   */
  createTime?: Date;
  /**
   * 用户邮箱
   */
  email?: string;
  /**
   * 性别
   */
  genderLabel?: string;
  /**
   * 用户ID
   */
  id?: number;
  /**
   * 手机号
   */
  mobile?: string;
  /**
   * 角色名称，多个使用英文逗号(,)分割
   */
  roleNames?: string;
  /**
   * 用户状态(1:启用;0:禁用)
   */
  status?: number;
  /**
   * 用户名
   */
  name?: string;
}

/**
 * 用户表单类型
 */
export interface UserForm {
  /**
   * 用户头像
   */
  avatar?: string;
  /**
   * 邮箱
   */
  email?: string;
  /**
   * 性别
   */
  gender?: number;
  /**
   * 用户ID
   */
  id?: number;
  mobile?: string;
  /**
   * 角色ID集合
   */
  roleIds?: number[];
  /**
   * 用户状态(1:正常;0:禁用)
   */
  status?: number;
  /**
   * 用户名
   */
  name?: string;
}

// 注册
export interface RegistrationData {
  /** 学生姓名 */
  sName: string;
  /** 用户名 */
  name: string;
  /** 密码 */
  password: string;
  /** 邮箱 */
  email: string;
  /** 头像地址 */
  avatar: string;
  /** 用户id */
  userId: string;
  /** 手机号 */
  phone: string;
}

export interface SysUser{
  /** 用户id */
  id?: number;
  /** 用户名 */
  name?: string;
  /** 密码 */
  password?: string;
  /** 邮箱 */
  email?: string;
  /** 头像地址 */
  avatar?: string;
  /** 绑定id */
  userId?: string;
  /**
   * 验证码
   */
  code?: string;

}
