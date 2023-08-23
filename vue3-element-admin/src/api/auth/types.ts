/**
 * 登录请求参数
 */
export interface LoginData {
  /**
   * 用户名
   */
  username: string;
  /**
   * 密码
   */
  password: string;

  /**
   * 验证码缓存key
   */
  verifyCodeKey?: string;

  /**
   * 验证码
   */
  verifyCode?: string;
}

/**
 * 登录响应
 */
export interface LoginResult {
  /**
   * 访问token
   */
  accessToken?: string;
  /**
   * 过期时间(单位：毫秒)
   */
  expires?: number;
  /**
   * 刷新token
   */
  refreshToken?: string;
  /**
   * token 类型
   */
  tokenType?: string;
}

/**
 * 验证码响应
 */
export interface CaptchaResult {
  /**
   * 验证码缓存key
   */
  verifyCodeKey: string;
  /**
   * 验证码图片Base64字符串
   */
  verifyCodeBase64: string;
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
