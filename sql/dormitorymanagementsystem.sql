/*
 Navicat Premium Data Transfer

 Source Server         : yu
 Source Server Type    : MySQL
 Source Server Version : 80027 (8.0.27)
 Source Host           : localhost:3306
 Source Schema         : dormitorymanagementsystem

 Target Server Type    : MySQL
 Target Server Version : 80027 (8.0.27)
 File Encoding         : 65001

 Date: 04/09/2023 07:33:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for access_log
-- ----------------------------
DROP TABLE IF EXISTS `access_log`;
CREATE TABLE `access_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '进出id',
  `user_id` bigint NOT NULL COMMENT '进出id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `a_type` tinyint NOT NULL COMMENT '进出类型 0 进 1出 2 其他进 3其他出',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '进入记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of access_log
-- ----------------------------
INSERT INTO `access_log` VALUES (1, 1, '2023-09-01 22:15:23', 1, 1);
INSERT INTO `access_log` VALUES (2, 1, '2023-09-03 15:38:50', 3, 0);
INSERT INTO `access_log` VALUES (3, 1, '2023-09-01 22:15:23', 2, 1);
INSERT INTO `access_log` VALUES (4, 1, '2023-09-01 22:15:23', 2, 0);
INSERT INTO `access_log` VALUES (5, 1, '2023-09-01 22:15:23', 1, 0);
INSERT INTO `access_log` VALUES (6, 1, '2023-09-03 21:17:27', 1, 0);

-- ----------------------------
-- Table structure for d_building
-- ----------------------------
DROP TABLE IF EXISTS `d_building`;
CREATE TABLE `d_building`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '楼栋id',
  `build_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '楼名字,10字以内',
  `type` tinyint NOT NULL DEFAULT 0 COMMENT '楼层类型',
  `latitude` double(10, 6) NOT NULL COMMENT '经度',
  `longitude` double(10, 6) NOT NULL COMMENT '纬度',
  `maxroom` int NOT NULL DEFAULT 10 COMMENT '最大房间号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '宿舍楼栋' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of d_building
-- ----------------------------
INSERT INTO `d_building` VALUES (1, 'testBuildName', 0, 50.000000, 50.000000, 713);
INSERT INTO `d_building` VALUES (2, '男一', 0, 28.226114, 112.925264, 612);
INSERT INTO `d_building` VALUES (3, 'testName', 0, 28.226210, 112.925004, 720);
INSERT INTO `d_building` VALUES (4, '女1', 1, 28.228230, 112.924788, 515);
INSERT INTO `d_building` VALUES (5, 't3', 0, 28.227061, 112.926791, 514);
INSERT INTO `d_building` VALUES (6, 'new1', 2, 28.227085, 112.926118, 618);
INSERT INTO `d_building` VALUES (10, '在建', 2, 28.227005, 112.926019, 720);
INSERT INTO `d_building` VALUES (11, '女2', 1, 28.228708, 112.924546, 514);

-- ----------------------------
-- Table structure for pay_log
-- ----------------------------
DROP TABLE IF EXISTS `pay_log`;
CREATE TABLE `pay_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dormitory_id` bigint NOT NULL COMMENT '宿舍id',
  `amount` double(15, 2) NOT NULL COMMENT '缴费金额',
  `user_id` bigint NOT NULL COMMENT '缴费者id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间/缴费时间',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '订单状态 0 未支付，1完成,2取消',
  `type` tinyint NULL DEFAULT NULL COMMENT '缴费类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '缴费日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pay_log
-- ----------------------------

-- ----------------------------
-- Table structure for receive_notice_msg
-- ----------------------------
DROP TABLE IF EXISTS `receive_notice_msg`;
CREATE TABLE `receive_notice_msg`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '接收通知id',
  `notice_id` bigint NOT NULL COMMENT '接收的通知id',
  `receive_id` int NOT NULL COMMENT '接收者id',
  `r_status` tinyint NOT NULL COMMENT '接收状态,0未读，1已读',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `receive_notice_msg_列_name_receive_id_uindex`(`notice_id` ASC, `receive_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '接收通知' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of receive_notice_msg
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_setting
-- ----------------------------
DROP TABLE IF EXISTS `schedule_setting`;
CREATE TABLE `schedule_setting`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `bean_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'bean名称',
  `method_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '方法名称',
  `method_params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '方法参数',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `job_status` int NULL DEFAULT NULL COMMENT '状态(1正常 0暂停)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule_setting
-- ----------------------------
INSERT INTO `schedule_setting` VALUES (8, 'taskDemo', 'taskByParams', 'task测试', '0/5 * * * * ?', '没有备注', 0, '2023-08-10 09:57:37', '2023-08-10 09:57:42');
INSERT INTO `schedule_setting` VALUES (9, 'taskDemo', 'taskNoParams', NULL, '0/10 * * * * ?', NULL, 0, '2023-08-10 09:58:25', '2023-08-10 09:58:27');

-- ----------------------------
-- Table structure for sender_notice_msg
-- ----------------------------
DROP TABLE IF EXISTS `sender_notice_msg`;
CREATE TABLE `sender_notice_msg`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '通知id',
  `sender_id` int NOT NULL COMMENT '发送者id，在tb_user表内的id',
  `n_msg` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '通知内容',
  `n_type` tinyint NOT NULL COMMENT '通知类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '通知消息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sender_notice_msg
-- ----------------------------

-- ----------------------------
-- Table structure for student_log
-- ----------------------------
DROP TABLE IF EXISTS `student_log`;
CREATE TABLE `student_log`  (
  `id` bigint NOT NULL COMMENT '日志id',
  `type` tinyint NOT NULL COMMENT '操作类型',
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生管理日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student_log
-- ----------------------------

-- ----------------------------
-- Table structure for sug_box
-- ----------------------------
DROP TABLE IF EXISTS `sug_box`;
CREATE TABLE `sug_box`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NULL DEFAULT NULL COMMENT '学生id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '建议状态 0 未处理，1未恢复，2已回复,3已经处理',
  `user_id` int NOT NULL COMMENT '回复人员id',
  `type` tinyint NULL DEFAULT NULL COMMENT '建议类型是否匿名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '建议箱，反馈箱' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sug_box
-- ----------------------------

-- ----------------------------
-- Table structure for sug_text
-- ----------------------------
DROP TABLE IF EXISTS `sug_text`;
CREATE TABLE `sug_text`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `s_id` int NOT NULL COMMENT '建议箱id',
  `s_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `type` tinyint NOT NULL COMMENT '0发起，1回复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '建议内容' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sug_text
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典类型编码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典项名称',
  `value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典项值',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint NULL DEFAULT 0 COMMENT '状态(1:正常;0:禁用)',
  `defaulted` tinyint NULL DEFAULT 0 COMMENT '是否默认(1:是;0:否)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'gender', '男', '1', 1, 1, 0, NULL, '2023-08-19 20:43:58', '2023-08-19 20:43:58');
INSERT INTO `sys_dict` VALUES (2, 'gender', '女', '2', 2, 1, 0, NULL, '2023-08-19 20:43:58', '2023-08-19 20:43:58');
INSERT INTO `sys_dict` VALUES (3, 'gender', '未知', '0', 1, 1, 0, NULL, '2023-08-19 20:43:58', '2023-08-19 20:43:58');
INSERT INTO `sys_dict` VALUES (4, 'building', '男生宿舍', '0', 1, 1, 0, '男生宿舍', NULL, NULL);
INSERT INTO `sys_dict` VALUES (5, 'building', '女生宿舍', '1', 1, 1, 0, '女生宿舍', NULL, NULL);
INSERT INTO `sys_dict` VALUES (6, 'building', '在建楼', '2', 2, 1, 0, '在建楼', NULL, NULL);
INSERT INTO `sys_dict` VALUES (7, 'building', '待拆楼', '3', 3, 1, 0, '待拆楼', NULL, NULL);
INSERT INTO `sys_dict` VALUES (8, 'building', '维修楼', '4', 2, 1, 0, '维修楼', NULL, NULL);
INSERT INTO `sys_dict` VALUES (9, 'eStatus', '正常', '0', 1, 1, 0, '', NULL, NULL);
INSERT INTO `sys_dict` VALUES (10, 'eStatus', '停用', '1', 2, 1, 0, '', NULL, NULL);
INSERT INTO `sys_dict` VALUES (11, 'eStatus', '临时启动', '3', 3, 1, 0, '', NULL, NULL);
INSERT INTO `sys_dict` VALUES (12, 'eStatus', '异常', '4', 4, 1, 0, '', NULL, NULL);
INSERT INTO `sys_dict` VALUES (13, 'pay', '水费比例', '2.2', 1, 1, 0, '1块钱对应的水量', NULL, NULL);
INSERT INTO `sys_dict` VALUES (14, 'pay', '电费比例', '1.5', 2, 1, 0, '一快钱对应多少度电', NULL, NULL);
INSERT INTO `sys_dict` VALUES (15, 'maintain', '水电', '1', 1, 1, 0, '包括修理水龙头、水管漏水、热水器故障、电线故障等与水电设施相关的问题。', NULL, NULL);
INSERT INTO `sys_dict` VALUES (16, 'maintain', '家具', '2', 1, 1, 0, '包括修理床、桌子、椅子、衣柜等宿舍内的家具，如破损、松动、脱胶等问题', NULL, NULL);
INSERT INTO `sys_dict` VALUES (17, 'maintain', '空调', '3', 1, 1, 0, '修理空调故障、清洗空调滤网、添加制冷剂等与空调设备相关的问题', NULL, NULL);
INSERT INTO `sys_dict` VALUES (18, 'maintain', '网络', '4', 1, 1, 0, '修复宿舍内的网络连接问题，如无法上网、信号不稳定等。', NULL, NULL);
INSERT INTO `sys_dict` VALUES (19, 'maintain', '窗户', '5', 1, 1, 0, '修复窗户玻璃破损、窗框松动、窗户无法关闭等问题。', NULL, NULL);
INSERT INTO `sys_dict` VALUES (20, 'maintain', '卫生间', '7', 1, 1, 0, '包括修理马桶堵塞、水管漏水、淋浴花洒故障等与卫生间设施相关的问题。', NULL, NULL);
INSERT INTO `sys_dict` VALUES (21, 'maintain', '门锁', '8', 1, 1, 0, '包括修理宿舍门锁无法开启、无法关闭、钥匙无法插入等问题。', NULL, NULL);
INSERT INTO `sys_dict` VALUES (22, 'maintain', '照明', '9', 1, 1, 0, '包括修理宿舍内的灯具故障、更换灯泡等问题。', NULL, NULL);
INSERT INTO `sys_dict` VALUES (23, 'maintain', '其他', '10', 1, 1, 0, '', NULL, NULL);
INSERT INTO `sys_dict` VALUES (24, 'Violation', '晚归', '1', 1, 1, 0, '晚归名单', NULL, NULL);
INSERT INTO `sys_dict` VALUES (25, 'UserType', '宿管', '1', 1, 1, 0, '宿舍管理员\n', NULL, NULL);
INSERT INTO `sys_dict` VALUES (26, 'UserType', '维修人员', '2', 1, 1, 0, '', NULL, NULL);
INSERT INTO `sys_dict` VALUES (27, 'UserType', '保洁人员', '3', 1, 1, 0, '', NULL, NULL);
INSERT INTO `sys_dict` VALUES (28, 'UserType', '学习领导干部', '4', 0, 1, 0, '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '类型名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '类型编码',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态(1正常;0禁用)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `type_code`(`code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '性别', 'gender', 1, NULL, '2023-08-19 20:43:57', '2023-08-26 15:22:36');
INSERT INTO `sys_dict_type` VALUES (2, '维修类型', 'maintain', 1, '记录各种维修的类型', '2023-08-19 21:20:32', '2023-09-02 15:14:05');
INSERT INTO `sys_dict_type` VALUES (91, '通知类型', 'notice', 1, '用于通知的枚举', '2023-08-29 09:05:15', '2023-08-29 09:05:15');
INSERT INTO `sys_dict_type` VALUES (92, '楼层类型', 'building', 1, '记录该楼的类型，如男，女，在建等', '2023-08-29 11:29:49', '2023-08-29 11:29:49');
INSERT INTO `sys_dict_type` VALUES (93, '寝室消费状态', 'eStatus', 1, '记录宿舍电量，水量的状态\n', '2023-08-29 22:06:40', '2023-08-29 22:08:11');
INSERT INTO `sys_dict_type` VALUES (94, '缴费相关', 'pay', 1, '记录各种缴费的自定义值', '2023-09-02 13:28:12', '2023-09-02 13:28:12');
INSERT INTO `sys_dict_type` VALUES (95, '违规类型', 'Violation', 1, '记录各种违规类型', '2023-09-03 20:20:10', '2023-09-03 20:20:10');
INSERT INTO `sys_dict_type` VALUES (96, '职工人员类型', 'UserType', 1, '记录各类职工人员的类型', '2023-09-03 22:10:45', '2023-09-03 22:10:57');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint NOT NULL COMMENT '父菜单ID',
  `tree_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父节点ID路径',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `type` tinyint NOT NULL COMMENT '菜单类型(1:菜单；2:目录；3:外链；4:按钮)',
  `path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由路径(浏览器地址栏路径)',
  `component` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径(vue页面完整路径，省略.vue后缀)',
  `perm` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `visible` tinyint(1) NOT NULL DEFAULT 1 COMMENT '显示状态(1-显示;0-隐藏)',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单图标',
  `redirect` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转路径',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '0', '系统管理', 2, '/system', 'Layout', NULL, 1, 1, 'system', '/system/user', '2023-08-19 21:12:21', '2023-08-19 21:12:21', 0);
INSERT INTO `sys_menu` VALUES (2, 1, '0,1', '用户管理', 1, 'user', 'system/user/index', NULL, 1, 1, 'user', NULL, '2023-08-19 21:12:21', '2023-08-26 23:23:57', 0);
INSERT INTO `sys_menu` VALUES (3, 1, '0,1', '角色管理', 1, 'role', 'system/role/index', NULL, 1, 2, 'role', NULL, '2023-08-19 21:12:21', '2023-08-19 21:12:21', 0);
INSERT INTO `sys_menu` VALUES (4, 1, '0,1', '菜单管理', 1, 'menu', 'system/menu/index', NULL, 1, 3, 'menu', NULL, '2023-08-19 21:12:21', '2023-08-19 21:12:21', 0);
INSERT INTO `sys_menu` VALUES (5, 2, '0,1,2', '用户新增', 4, '', NULL, 'sys:user:add', 1, 1, '', '', '2023-08-19 21:12:21', '2023-08-19 21:12:21', 0);
INSERT INTO `sys_menu` VALUES (6, 2, '0,1,2', '用户编辑', 4, '', NULL, 'sys:user:edit', 1, 2, '', '', '2023-08-19 21:12:21', '2023-08-19 21:12:21', 0);
INSERT INTO `sys_menu` VALUES (7, 2, '0,1,2', '用户删除', 4, '', NULL, 'sys:user:delete', 1, 3, '', '', '2023-08-19 21:12:21', '2023-08-19 21:12:21', 0);
INSERT INTO `sys_menu` VALUES (8, 36, '0,36', '图片上传', 1, 'upload', 'demo/upload', NULL, 1, 2, '', '', '2023-08-19 21:12:21', '2023-08-19 21:12:21', 1);
INSERT INTO `sys_menu` VALUES (9, 3, '0,1,3', '角色新增', 4, '', NULL, 'sys:role:add', 1, 1, '', NULL, '2023-08-19 21:12:21', '2023-08-19 21:12:21', 0);
INSERT INTO `sys_menu` VALUES (10, 3, '0,1,3', '角色编辑', 4, '', NULL, 'sys:role:edit', 1, 2, '', NULL, '2023-08-19 21:12:21', '2023-08-19 21:12:21', 0);
INSERT INTO `sys_menu` VALUES (11, 3, '0,1,3', '角色删除', 4, '', NULL, 'sys:role:delete', 1, 3, '', NULL, '2023-08-19 21:12:21', '2023-08-19 21:12:21', 0);
INSERT INTO `sys_menu` VALUES (12, 4, '0,1,4', '菜单新增', 4, '', NULL, 'sys:menu:add', 1, 1, '', NULL, '2023-08-19 21:12:21', '2023-08-19 21:12:21', 0);
INSERT INTO `sys_menu` VALUES (13, 4, '0,1,4', '菜单编辑', 4, '', NULL, 'sys:menu:edit', 1, 3, '', NULL, '2023-08-19 21:12:21', '2023-08-19 21:12:21', 0);
INSERT INTO `sys_menu` VALUES (14, 4, '0,1,4', '菜单删除', 4, '', NULL, 'sys:menu:delete', 1, 3, '', NULL, '2023-08-19 21:12:21', '2023-08-19 21:12:21', 0);
INSERT INTO `sys_menu` VALUES (15, 2, '0,1,2', '重置密码', 4, '', NULL, 'sys:user:reset_pwd', 1, 4, '', NULL, '2023-08-19 21:12:21', '2023-08-19 21:12:21', 0);
INSERT INTO `sys_menu` VALUES (16, 0, '0', '人员管理', 2, '/people', 'Layout', NULL, 1, 2, 'uv', NULL, '2023-08-26 10:41:15', '2023-08-27 15:05:17', 0);
INSERT INTO `sys_menu` VALUES (17, 16, '0,16', '学生人员', 1, 'student', 'people/student/index', '', 1, 1, 'student', '', '2023-08-26 10:43:10', '2023-08-31 23:00:46', 0);
INSERT INTO `sys_menu` VALUES (18, 16, '0,16', '宿管人员', 1, 'housemaster', 'people/housemaster/index', NULL, 1, 2, 'cangkuyonghu', '', '2023-08-26 10:46:08', '2023-08-30 21:14:50', 0);
INSERT INTO `sys_menu` VALUES (19, 16, '0,16', '维修人员', 1, 'repair', 'people/repair/index', NULL, 1, 3, 'repair', '', '2023-08-26 10:46:09', '2023-08-26 10:46:10', 0);
INSERT INTO `sys_menu` VALUES (20, 0, '0', '日志管理', 2, '/log', 'Layout', NULL, 1, 6, 'log', '', '2023-08-26 10:48:13', '2023-08-26 10:48:13', 0);
INSERT INTO `sys_menu` VALUES (21, 20, '0,20', '缴费日志', 1, 'paymentLog', 'logs/paymentLog/index', NULL, 1, 1, 'pay', NULL, '2023-08-26 10:52:26', '2023-08-30 21:18:44', 0);
INSERT INTO `sys_menu` VALUES (22, 20, '0,20', '违纪日志', 1, 'violation', 'logs/violation/index', NULL, 1, 2, 'violation', NULL, '2023-08-26 10:52:28', '2023-08-30 21:18:29', 0);
INSERT INTO `sys_menu` VALUES (23, 0, '0', '宿舍管理', 2, '/dormitory', 'Layout', NULL, 1, 4, 'client', NULL, '2023-08-26 10:53:55', '2023-08-27 15:05:51', 0);
INSERT INTO `sys_menu` VALUES (24, 23, '0,23', '宿舍列表', 1, 'dList', 'dormitory/dList/index', NULL, 1, 1, 'dormitory', NULL, '2023-08-26 10:54:06', '2023-08-30 21:21:55', 0);
INSERT INTO `sys_menu` VALUES (25, 23, '0,23', '门禁管理', 1, 'access', 'dormitory/access/index', NULL, 1, 2, 'accessManage', NULL, '2023-08-26 11:00:38', '2023-08-30 21:20:51', 0);
INSERT INTO `sys_menu` VALUES (26, 1, '0,1', '字典管理', 1, 'dict', 'system/dict/index', '', 1, 5, 'dict', NULL, '2023-08-26 15:09:05', '2023-08-26 15:09:05', 0);
INSERT INTO `sys_menu` VALUES (79, 6, '0,1,2,6', '字典类型新增', 4, '', NULL, 'sys:dict_type:add', 1, 1, '', NULL, '2023-08-26 15:09:05', '2023-08-26 23:23:18', 0);
INSERT INTO `sys_menu` VALUES (81, 6, '0,1,6', '字典类型编辑', 4, '', NULL, 'sys:dict_type:edit', 1, 2, '', NULL, '2023-08-26 15:09:05', '2023-08-26 15:09:05', 0);
INSERT INTO `sys_menu` VALUES (84, 6, '0,1,6', '字典类型删除', 4, '', NULL, 'sys:dict_type:delete', 1, 3, '', NULL, '2023-08-26 15:09:05', '2023-08-26 15:09:05', 0);
INSERT INTO `sys_menu` VALUES (85, 6, '0,1,6', '字典数据新增', 4, '', NULL, 'sys:dict:add', 1, 4, '', NULL, '2023-08-26 15:09:05', '2023-08-26 15:09:05', 0);
INSERT INTO `sys_menu` VALUES (86, 6, '0,1,6', '字典数据编辑', 4, '', NULL, 'sys:dict:edit', 1, 5, '', NULL, '2023-08-26 15:09:05', '2023-08-26 15:09:05', 0);
INSERT INTO `sys_menu` VALUES (87, 6, '0,1,6', '字典数据删除', 4, '', NULL, 'sys:dict:delete', 1, 6, '', NULL, '2023-08-26 15:09:05', '2023-08-26 15:09:05', 0);
INSERT INTO `sys_menu` VALUES (95, 17, '0,16', '新增学生', 4, '', NULL, 'sys:student:add', 1, 1, '', NULL, '2023-08-26 10:43:10', '2023-08-26 10:43:11', 1);
INSERT INTO `sys_menu` VALUES (96, 0, '0', '接口管理', 2, '/api', 'Layout', NULL, 1, 7, 'api', 'api-doc', '2023-08-29 08:42:47', '2023-08-29 08:59:16', 0);
INSERT INTO `sys_menu` VALUES (99, 96, '0', '接口文档', 1, 'api-doc', 'demo/api-doc', NULL, 1, 1, 'api', NULL, '2023-08-29 08:51:02', '2023-08-29 08:51:02', 1);
INSERT INTO `sys_menu` VALUES (100, 96, '0,96', '接口文档', 1, 'api-doc', 'demo/api-doc', NULL, 1, 1, 'api', NULL, '2023-08-29 08:58:52', '2023-08-29 08:58:52', 0);
INSERT INTO `sys_menu` VALUES (101, 23, '0,23', '楼管理', 1, 'building', 'dormitory/building/index', NULL, 1, 3, 'homepage', NULL, '2023-08-30 21:10:18', '2023-08-30 21:49:35', 0);
INSERT INTO `sys_menu` VALUES (102, 17, '0,16,17', '添加学生', 4, '', NULL, 'sys:student:add', 1, 1, 'student', NULL, '2023-08-31 23:01:02', '2023-08-31 23:01:02', 0);
INSERT INTO `sys_menu` VALUES (103, 17, '0,16,17', '删除学生', 4, '', NULL, 'sys:student:delete', 1, 2, 'student', NULL, '2023-08-31 23:01:26', '2023-08-31 23:01:26', 0);
INSERT INTO `sys_menu` VALUES (104, 18, '0,16,18', '添加宿管', 4, '', NULL, 'sys:housemaster:add', 1, 1, 'student', NULL, '2023-08-31 23:02:23', '2023-08-31 23:02:23', 0);
INSERT INTO `sys_menu` VALUES (105, 18, '0,16,18', '删除宿管', 4, '', NULL, 'sys:housemaster:delete', 1, 3, 'student', NULL, '2023-08-31 23:02:38', '2023-08-31 23:02:38', 0);
INSERT INTO `sys_menu` VALUES (106, 107, '0,107', '宿舍地图', 1, '/navigation-map', 'student/navigation-map/index', NULL, 1, 8, 'map', NULL, '2023-08-31 23:11:57', '2023-09-01 20:30:43', 0);
INSERT INTO `sys_menu` VALUES (107, 0, '0', '宿舍导航', 2, '/student', 'Layout', NULL, 1, 8, 'publish', '/navigation-map', '2023-09-01 20:30:24', '2023-09-01 20:30:31', 0);
INSERT INTO `sys_menu` VALUES (108, 0, '0', '进出控制', 2, '/pass', 'Layout', NULL, 1, 9, 'pass', '/passQRCode', '2023-09-01 20:31:25', '2023-09-01 20:31:25', 0);
INSERT INTO `sys_menu` VALUES (109, 108, '0,108', '进出二维码', 1, '/passQRCode', 'pass/passQRCode/index', NULL, 1, 1, 'passQR', NULL, '2023-09-01 20:31:48', '2023-09-01 20:31:48', 0);
INSERT INTO `sys_menu` VALUES (110, 108, '0,108', '扫描二维码', 1, '/scanQRCode', 'pass/scanQRCode/index', NULL, 1, 2, 'scan', NULL, '2023-09-01 20:32:11', '2023-09-01 20:32:11', 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `sort` int NULL DEFAULT NULL COMMENT '显示顺序',
  `tableName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '表名',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '角色状态(1-正常；0-停用)',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标识(0-未删除；1-已删除)',
  `data_scope` tinyint NULL DEFAULT NULL COMMENT '数据权限',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'ROOT', 1, NULL, 1, 0, 0, '2023-08-19 21:12:21', '2023-08-19 21:12:21');
INSERT INTO `sys_role` VALUES (2, '系统管理员', 'ADMIN', 2, NULL, 1, 1, 1, '2023-08-19 21:12:21', '2023-08-19 21:12:21');
INSERT INTO `sys_role` VALUES (3, '学生', 'STUDENT', 5, 'tb_student', 1, 0, 5, '2023-08-22 23:51:03', '2023-08-22 23:51:04');
INSERT INTO `sys_role` VALUES (4, '宿管人员', 'housemaster', 3, 'tb_dormitory', 1, 0, 3, '2023-08-25 20:56:48', '2023-08-25 20:56:47');
INSERT INTO `sys_role` VALUES (5, '维修人员', 'maintenance', 4, 'tb_maintenance', 1, 0, 4, '2023-08-25 20:57:44', '2023-08-25 20:57:47');
INSERT INTO `sys_role` VALUES (6, '宿管管理者', 'hm-leader', 2, 'tb_dormitory', 1, 0, 2, '2023-08-25 21:04:58', '2023-08-25 21:05:02');
INSERT INTO `sys_role` VALUES (7, '人事', 'maintenance', 2, 'tb_dormitory', 1, 0, 2, '2023-08-25 21:05:00', '2023-08-25 21:05:04');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (3, 106);
INSERT INTO `sys_role_menu` VALUES (3, 107);
INSERT INTO `sys_role_menu` VALUES (3, 108);
INSERT INTO `sys_role_menu` VALUES (3, 109);
INSERT INTO `sys_role_menu` VALUES (3, 110);
INSERT INTO `sys_role_menu` VALUES (4, 23);
INSERT INTO `sys_role_menu` VALUES (4, 24);
INSERT INTO `sys_role_menu` VALUES (4, 25);
INSERT INTO `sys_role_menu` VALUES (7, 16);
INSERT INTO `sys_role_menu` VALUES (7, 18);
INSERT INTO `sys_role_menu` VALUES (7, 19);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码,最多30字符',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '用户状态(1:正常;0:禁用)',
  `user_id` bigint NULL DEFAULT NULL COMMENT '绑定id',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除标志(0:未删除，1:已删除)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', '/img/default.gif', NULL, 1, NULL, 0, '2023-08-20 20:20:19', '2023-08-20 20:20:20');
INSERT INTO `sys_user` VALUES (2, 'root', '1', NULL, NULL, 1, 0, 0, '2023-08-26 13:47:37', NULL);
INSERT INTO `sys_user` VALUES (5, '张三', '$2a$10$h7kEg3K.iaXGVwRl4HqMQuv4h.XXQ9CTImN5GpaKx3A1swcZCetp2', '/img/default.gif', '1621@qq.com', 1, 1, 0, '2023-08-23 00:15:04', '2023-08-23 00:15:04');
INSERT INTO `sys_user` VALUES (6, 'yu', '$2a$10$h7kEg3K.iaXGVwRl4HqMQuv4h.XXQ9CTImN5GpaKx3A1swcZCetp2', NULL, '1647022985@qq.com', 1, 12, 0, '2023-09-02 22:01:53', '2023-09-02 22:01:58');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (5, 3);

-- ----------------------------
-- Table structure for tb_dormitory
-- ----------------------------
DROP TABLE IF EXISTS `tb_dormitory`;
CREATE TABLE `tb_dormitory`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '宿舍id',
  `building_id` bigint NOT NULL COMMENT '楼层栋',
  `dormitory_number` int NOT NULL COMMENT '宿舍号后2位为宿舍号，前为楼层',
  `capacity` tinyint NOT NULL COMMENT '宿舍容量',
  `electricity` double(6, 2) NOT NULL DEFAULT 0.00 COMMENT '电费',
  `water` double(6, 2) NOT NULL DEFAULT 0.00 COMMENT '水费',
  `e_status` tinyint NOT NULL DEFAULT 0 COMMENT '电的状态(0-正常；1-停用;2-送水)',
  `w_status` tinyint NOT NULL DEFAULT 0 COMMENT '水状态(0-正常;1-停用;2-送水)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `tb_dormitory_building_id_dormitory_number_uindex`(`building_id` ASC, `dormitory_number` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 463 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_dormitory
-- ----------------------------
INSERT INTO `tb_dormitory` VALUES (1, 1, 101, 8, 37.71, 67.13, 0, 0);
INSERT INTO `tb_dormitory` VALUES (2, 1, 102, 8, -10.00, 61.67, 1, 0);
INSERT INTO `tb_dormitory` VALUES (3, 1, 103, 8, 78.27, -10.20, 0, 1);
INSERT INTO `tb_dormitory` VALUES (4, 1, 104, 8, 3.80, 13.51, 0, 0);
INSERT INTO `tb_dormitory` VALUES (5, 1, 105, 8, 96.43, 64.78, 0, 0);
INSERT INTO `tb_dormitory` VALUES (6, 1, 106, 8, 74.11, 14.81, 0, 0);
INSERT INTO `tb_dormitory` VALUES (7, 1, 107, 8, 92.14, 12.24, 0, 0);
INSERT INTO `tb_dormitory` VALUES (8, 1, 108, 8, 105.34, 16.75, 0, 0);
INSERT INTO `tb_dormitory` VALUES (9, 1, 109, 8, 3.15, 25.41, 0, 0);
INSERT INTO `tb_dormitory` VALUES (10, 1, 110, 8, 110.30, 77.56, 0, 0);
INSERT INTO `tb_dormitory` VALUES (11, 1, 111, 8, 80.90, 28.66, 0, 0);
INSERT INTO `tb_dormitory` VALUES (12, 1, 112, 8, 4.97, 34.08, 0, 0);
INSERT INTO `tb_dormitory` VALUES (13, 1, 113, 8, 6.23, 64.88, 0, 0);
INSERT INTO `tb_dormitory` VALUES (14, 1, 201, 8, 50.44, 33.77, 0, 0);
INSERT INTO `tb_dormitory` VALUES (15, 1, 202, 8, 70.92, 66.81, 0, 0);
INSERT INTO `tb_dormitory` VALUES (16, 1, 203, 8, 104.93, 8.38, 0, 0);
INSERT INTO `tb_dormitory` VALUES (17, 1, 204, 8, 65.90, 90.71, 0, 0);
INSERT INTO `tb_dormitory` VALUES (18, 1, 205, 8, 119.97, 68.97, 0, 0);
INSERT INTO `tb_dormitory` VALUES (19, 1, 206, 8, 37.14, 46.26, 0, 0);
INSERT INTO `tb_dormitory` VALUES (20, 1, 207, 8, 80.17, 81.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (21, 1, 208, 8, 39.27, 36.01, 0, 0);
INSERT INTO `tb_dormitory` VALUES (22, 1, 209, 8, 11.08, 56.67, 0, 0);
INSERT INTO `tb_dormitory` VALUES (23, 1, 210, 8, 70.47, 3.21, 0, 0);
INSERT INTO `tb_dormitory` VALUES (24, 1, 211, 8, 64.69, 64.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (25, 1, 212, 8, 9.79, 80.19, 0, 0);
INSERT INTO `tb_dormitory` VALUES (26, 1, 213, 8, 26.46, 92.48, 0, 0);
INSERT INTO `tb_dormitory` VALUES (27, 1, 301, 8, 50.44, 33.77, 0, 0);
INSERT INTO `tb_dormitory` VALUES (28, 1, 302, 8, 70.92, 66.81, 0, 0);
INSERT INTO `tb_dormitory` VALUES (29, 1, 303, 8, 104.93, 8.38, 0, 0);
INSERT INTO `tb_dormitory` VALUES (30, 1, 304, 8, 65.90, 90.71, 0, 0);
INSERT INTO `tb_dormitory` VALUES (31, 1, 305, 8, 119.97, 68.97, 0, 0);
INSERT INTO `tb_dormitory` VALUES (32, 1, 306, 8, 37.14, 46.26, 0, 0);
INSERT INTO `tb_dormitory` VALUES (33, 1, 307, 8, 80.17, 81.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (34, 1, 308, 8, 39.27, 36.01, 0, 0);
INSERT INTO `tb_dormitory` VALUES (35, 1, 309, 8, 11.08, 56.67, 0, 0);
INSERT INTO `tb_dormitory` VALUES (36, 1, 310, 8, 70.47, 3.21, 0, 0);
INSERT INTO `tb_dormitory` VALUES (37, 1, 311, 8, 64.69, 64.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (38, 1, 312, 8, 9.79, 80.19, 0, 0);
INSERT INTO `tb_dormitory` VALUES (39, 1, 313, 8, 26.46, 92.48, 0, 0);
INSERT INTO `tb_dormitory` VALUES (40, 1, 401, 8, 50.44, 33.77, 0, 0);
INSERT INTO `tb_dormitory` VALUES (41, 1, 402, 8, 70.92, 66.81, 0, 0);
INSERT INTO `tb_dormitory` VALUES (42, 1, 403, 8, 104.93, 8.38, 0, 0);
INSERT INTO `tb_dormitory` VALUES (43, 1, 404, 8, 65.90, 90.71, 0, 0);
INSERT INTO `tb_dormitory` VALUES (44, 1, 405, 8, 119.97, 68.97, 0, 0);
INSERT INTO `tb_dormitory` VALUES (45, 1, 406, 8, 37.14, 46.26, 0, 0);
INSERT INTO `tb_dormitory` VALUES (46, 1, 407, 8, 80.17, 81.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (47, 1, 408, 8, 39.27, 36.01, 0, 0);
INSERT INTO `tb_dormitory` VALUES (48, 1, 409, 8, 11.08, 56.67, 0, 0);
INSERT INTO `tb_dormitory` VALUES (49, 1, 410, 8, 70.47, 3.21, 0, 0);
INSERT INTO `tb_dormitory` VALUES (50, 1, 411, 8, 64.69, 64.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (51, 1, 412, 8, 9.79, 80.19, 0, 0);
INSERT INTO `tb_dormitory` VALUES (52, 1, 413, 8, 26.46, 92.48, 0, 0);
INSERT INTO `tb_dormitory` VALUES (53, 1, 501, 8, 50.44, 33.77, 0, 0);
INSERT INTO `tb_dormitory` VALUES (54, 1, 502, 8, 70.92, 66.81, 0, 0);
INSERT INTO `tb_dormitory` VALUES (55, 1, 503, 8, 104.93, 8.38, 0, 0);
INSERT INTO `tb_dormitory` VALUES (56, 1, 504, 8, 65.90, 90.71, 0, 0);
INSERT INTO `tb_dormitory` VALUES (57, 1, 505, 8, 119.97, 68.97, 0, 0);
INSERT INTO `tb_dormitory` VALUES (58, 1, 506, 8, 37.14, 46.26, 0, 0);
INSERT INTO `tb_dormitory` VALUES (59, 1, 507, 8, 80.17, 81.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (60, 1, 508, 8, 39.27, 36.01, 0, 0);
INSERT INTO `tb_dormitory` VALUES (61, 1, 509, 8, 11.08, 56.67, 0, 0);
INSERT INTO `tb_dormitory` VALUES (62, 1, 510, 8, 70.47, 3.21, 0, 0);
INSERT INTO `tb_dormitory` VALUES (63, 1, 511, 8, 64.69, 64.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (64, 1, 512, 8, 9.79, 80.19, 0, 0);
INSERT INTO `tb_dormitory` VALUES (65, 1, 513, 8, 26.46, 92.48, 0, 0);
INSERT INTO `tb_dormitory` VALUES (66, 1, 601, 8, 50.44, 33.77, 0, 0);
INSERT INTO `tb_dormitory` VALUES (67, 1, 602, 8, 70.92, 66.81, 0, 0);
INSERT INTO `tb_dormitory` VALUES (68, 1, 603, 8, 104.93, 8.38, 0, 0);
INSERT INTO `tb_dormitory` VALUES (69, 1, 604, 8, 65.90, 90.71, 0, 0);
INSERT INTO `tb_dormitory` VALUES (70, 1, 605, 8, 119.97, 68.97, 0, 0);
INSERT INTO `tb_dormitory` VALUES (71, 1, 606, 8, 37.14, 46.26, 0, 0);
INSERT INTO `tb_dormitory` VALUES (72, 1, 607, 8, 80.17, 81.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (73, 1, 608, 8, 39.27, 36.01, 0, 0);
INSERT INTO `tb_dormitory` VALUES (74, 1, 609, 8, 11.08, 56.67, 0, 0);
INSERT INTO `tb_dormitory` VALUES (75, 1, 610, 8, 70.47, 3.21, 0, 0);
INSERT INTO `tb_dormitory` VALUES (76, 1, 611, 8, 64.69, 64.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (77, 1, 612, 8, 9.79, 80.19, 0, 0);
INSERT INTO `tb_dormitory` VALUES (78, 1, 613, 8, 26.46, 92.48, 0, 0);
INSERT INTO `tb_dormitory` VALUES (79, 1, 701, 8, 50.44, 33.77, 0, 0);
INSERT INTO `tb_dormitory` VALUES (80, 1, 702, 8, 70.92, 66.81, 0, 0);
INSERT INTO `tb_dormitory` VALUES (81, 1, 703, 8, 104.93, 8.38, 0, 0);
INSERT INTO `tb_dormitory` VALUES (82, 1, 704, 8, 65.90, 90.71, 0, 0);
INSERT INTO `tb_dormitory` VALUES (83, 1, 705, 8, 119.97, 68.97, 0, 0);
INSERT INTO `tb_dormitory` VALUES (84, 1, 706, 8, 37.14, 46.26, 0, 0);
INSERT INTO `tb_dormitory` VALUES (85, 1, 707, 8, 80.17, 81.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (86, 1, 708, 8, 39.27, 36.01, 0, 0);
INSERT INTO `tb_dormitory` VALUES (87, 1, 709, 8, 11.08, 56.67, 0, 0);
INSERT INTO `tb_dormitory` VALUES (88, 1, 710, 8, 70.47, 3.21, 0, 0);
INSERT INTO `tb_dormitory` VALUES (89, 1, 711, 8, 64.69, 64.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (90, 1, 712, 8, 9.79, 80.19, 0, 0);
INSERT INTO `tb_dormitory` VALUES (91, 1, 713, 8, 26.46, 92.48, 0, 0);
INSERT INTO `tb_dormitory` VALUES (92, 2, 101, 8, 65.54, 42.03, 0, 0);
INSERT INTO `tb_dormitory` VALUES (93, 2, 102, 8, 7.33, 76.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (94, 2, 103, 8, 11.90, 87.24, 0, 0);
INSERT INTO `tb_dormitory` VALUES (95, 2, 104, 8, 68.44, 73.98, 0, 0);
INSERT INTO `tb_dormitory` VALUES (96, 2, 105, 8, 108.85, 77.78, 0, 0);
INSERT INTO `tb_dormitory` VALUES (97, 2, 106, 8, 119.97, 68.97, 0, 0);
INSERT INTO `tb_dormitory` VALUES (98, 2, 107, 8, 26.21, 30.95, 0, 0);
INSERT INTO `tb_dormitory` VALUES (99, 2, 108, 8, 29.71, 32.79, 0, 0);
INSERT INTO `tb_dormitory` VALUES (100, 2, 109, 8, 84.56, 71.60, 0, 0);
INSERT INTO `tb_dormitory` VALUES (101, 2, 110, 8, 1.89, 69.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (102, 2, 111, 8, 68.00, 42.50, 0, 0);
INSERT INTO `tb_dormitory` VALUES (103, 2, 112, 8, 70.47, 3.21, 0, 0);
INSERT INTO `tb_dormitory` VALUES (104, 2, 201, 8, 65.54, 42.03, 0, 0);
INSERT INTO `tb_dormitory` VALUES (105, 2, 202, 8, 7.33, 76.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (106, 2, 203, 8, 11.90, 87.24, 0, 0);
INSERT INTO `tb_dormitory` VALUES (107, 2, 204, 8, 68.44, 73.98, 0, 0);
INSERT INTO `tb_dormitory` VALUES (108, 2, 205, 8, 108.85, 77.78, 0, 0);
INSERT INTO `tb_dormitory` VALUES (109, 2, 206, 8, 119.97, 68.97, 0, 0);
INSERT INTO `tb_dormitory` VALUES (110, 2, 207, 8, 26.21, 30.95, 0, 0);
INSERT INTO `tb_dormitory` VALUES (111, 2, 208, 8, 29.71, 32.79, 0, 0);
INSERT INTO `tb_dormitory` VALUES (112, 2, 209, 8, 84.56, 71.60, 0, 0);
INSERT INTO `tb_dormitory` VALUES (113, 2, 210, 8, 1.89, 69.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (114, 2, 211, 8, 68.00, 42.50, 0, 0);
INSERT INTO `tb_dormitory` VALUES (115, 2, 212, 8, 70.47, 3.21, 0, 0);
INSERT INTO `tb_dormitory` VALUES (116, 2, 301, 8, 65.54, 42.03, 0, 0);
INSERT INTO `tb_dormitory` VALUES (117, 2, 302, 8, 7.33, 76.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (118, 2, 303, 8, 11.90, 87.24, 0, 0);
INSERT INTO `tb_dormitory` VALUES (119, 2, 304, 8, 68.44, 73.98, 0, 0);
INSERT INTO `tb_dormitory` VALUES (120, 2, 305, 8, 108.85, 77.78, 0, 0);
INSERT INTO `tb_dormitory` VALUES (121, 2, 306, 8, 119.97, 68.97, 0, 0);
INSERT INTO `tb_dormitory` VALUES (122, 2, 307, 8, 26.21, 30.95, 0, 0);
INSERT INTO `tb_dormitory` VALUES (123, 2, 308, 8, 29.71, 32.79, 0, 0);
INSERT INTO `tb_dormitory` VALUES (124, 2, 309, 8, 84.56, 71.60, 0, 0);
INSERT INTO `tb_dormitory` VALUES (125, 2, 310, 8, 1.89, 69.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (126, 2, 311, 8, 68.00, 42.50, 0, 0);
INSERT INTO `tb_dormitory` VALUES (127, 2, 312, 8, 70.47, 3.21, 0, 0);
INSERT INTO `tb_dormitory` VALUES (128, 2, 401, 8, 65.54, 42.03, 0, 0);
INSERT INTO `tb_dormitory` VALUES (129, 2, 402, 8, 7.33, 76.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (130, 2, 403, 8, 11.90, 87.24, 0, 0);
INSERT INTO `tb_dormitory` VALUES (131, 2, 404, 8, 68.44, 73.98, 0, 0);
INSERT INTO `tb_dormitory` VALUES (132, 2, 405, 8, 108.85, 77.78, 0, 0);
INSERT INTO `tb_dormitory` VALUES (133, 2, 406, 8, 119.97, 68.97, 0, 0);
INSERT INTO `tb_dormitory` VALUES (134, 2, 407, 8, 26.21, 30.95, 0, 0);
INSERT INTO `tb_dormitory` VALUES (135, 2, 408, 8, 29.71, 32.79, 0, 0);
INSERT INTO `tb_dormitory` VALUES (136, 2, 409, 8, 84.56, 71.60, 0, 0);
INSERT INTO `tb_dormitory` VALUES (137, 2, 410, 8, 1.89, 69.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (138, 2, 411, 8, 68.00, 42.50, 0, 0);
INSERT INTO `tb_dormitory` VALUES (139, 2, 412, 8, 70.47, 3.21, 0, 0);
INSERT INTO `tb_dormitory` VALUES (140, 2, 501, 8, 65.54, 42.03, 0, 0);
INSERT INTO `tb_dormitory` VALUES (141, 2, 502, 8, 7.33, 76.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (142, 2, 503, 8, 11.90, 87.24, 0, 0);
INSERT INTO `tb_dormitory` VALUES (143, 2, 504, 8, 68.44, 73.98, 0, 0);
INSERT INTO `tb_dormitory` VALUES (144, 2, 505, 8, 108.85, 77.78, 0, 0);
INSERT INTO `tb_dormitory` VALUES (145, 2, 506, 8, 119.97, 68.97, 0, 0);
INSERT INTO `tb_dormitory` VALUES (146, 2, 507, 8, 26.21, 30.95, 0, 0);
INSERT INTO `tb_dormitory` VALUES (147, 2, 508, 8, 29.71, 32.79, 0, 0);
INSERT INTO `tb_dormitory` VALUES (148, 2, 509, 8, 84.56, 71.60, 0, 0);
INSERT INTO `tb_dormitory` VALUES (149, 2, 510, 8, 1.89, 69.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (150, 2, 511, 8, 68.00, 42.50, 0, 0);
INSERT INTO `tb_dormitory` VALUES (151, 2, 512, 8, 70.47, 3.21, 0, 0);
INSERT INTO `tb_dormitory` VALUES (152, 2, 601, 8, 65.54, 42.03, 0, 0);
INSERT INTO `tb_dormitory` VALUES (153, 2, 602, 8, 7.33, 76.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (154, 2, 603, 8, 11.90, 87.24, 0, 0);
INSERT INTO `tb_dormitory` VALUES (155, 2, 604, 8, 68.44, 73.98, 0, 0);
INSERT INTO `tb_dormitory` VALUES (156, 2, 605, 8, 108.85, 77.78, 0, 0);
INSERT INTO `tb_dormitory` VALUES (157, 2, 606, 8, 119.97, 68.97, 0, 0);
INSERT INTO `tb_dormitory` VALUES (158, 2, 607, 8, 26.21, 30.95, 0, 0);
INSERT INTO `tb_dormitory` VALUES (159, 2, 608, 8, 29.71, 32.79, 0, 0);
INSERT INTO `tb_dormitory` VALUES (160, 2, 609, 8, 84.56, 71.60, 0, 0);
INSERT INTO `tb_dormitory` VALUES (161, 2, 610, 8, 1.89, 69.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (162, 2, 611, 8, 68.00, 42.50, 0, 0);
INSERT INTO `tb_dormitory` VALUES (163, 2, 612, 8, 70.47, 3.21, 0, 0);
INSERT INTO `tb_dormitory` VALUES (178, 3, 101, 6, 37.14, 17.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (179, 3, 102, 6, 73.25, 82.52, 0, 0);
INSERT INTO `tb_dormitory` VALUES (180, 3, 103, 6, 43.24, 78.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (181, 3, 104, 6, 103.47, 76.14, 0, 0);
INSERT INTO `tb_dormitory` VALUES (182, 3, 105, 6, 40.10, 78.10, 0, 0);
INSERT INTO `tb_dormitory` VALUES (183, 3, 106, 6, 62.34, 37.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (184, 3, 107, 6, 53.28, 42.88, 0, 0);
INSERT INTO `tb_dormitory` VALUES (185, 3, 108, 6, 11.39, 55.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (186, 3, 109, 6, 106.66, 2.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (187, 3, 110, 6, 87.94, 86.91, 0, 0);
INSERT INTO `tb_dormitory` VALUES (188, 3, 111, 6, 80.29, 25.15, 0, 0);
INSERT INTO `tb_dormitory` VALUES (189, 3, 112, 6, 49.08, 37.55, 0, 0);
INSERT INTO `tb_dormitory` VALUES (190, 3, 113, 6, 42.17, 98.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (191, 3, 114, 6, 47.86, 6.49, 0, 0);
INSERT INTO `tb_dormitory` VALUES (192, 3, 115, 6, 56.10, 93.41, 0, 0);
INSERT INTO `tb_dormitory` VALUES (193, 3, 116, 6, 71.79, 40.40, 0, 0);
INSERT INTO `tb_dormitory` VALUES (194, 3, 117, 6, 17.55, 36.05, 0, 0);
INSERT INTO `tb_dormitory` VALUES (195, 3, 118, 6, 104.79, 58.94, 0, 0);
INSERT INTO `tb_dormitory` VALUES (196, 3, 119, 6, 76.67, 31.77, 0, 0);
INSERT INTO `tb_dormitory` VALUES (197, 3, 120, 6, 11.04, 30.26, 0, 0);
INSERT INTO `tb_dormitory` VALUES (198, 3, 201, 6, 37.14, 17.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (199, 3, 202, 6, 73.25, 82.52, 0, 0);
INSERT INTO `tb_dormitory` VALUES (200, 3, 203, 6, 43.24, 78.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (201, 3, 204, 6, 103.47, 76.14, 0, 0);
INSERT INTO `tb_dormitory` VALUES (202, 3, 205, 6, 40.10, 78.10, 0, 0);
INSERT INTO `tb_dormitory` VALUES (203, 3, 206, 6, 62.34, 37.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (204, 3, 207, 6, 53.28, 42.88, 0, 0);
INSERT INTO `tb_dormitory` VALUES (205, 3, 208, 6, 11.39, 55.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (206, 3, 209, 6, 106.66, 2.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (207, 3, 210, 6, 87.94, 86.91, 0, 0);
INSERT INTO `tb_dormitory` VALUES (208, 3, 211, 6, 80.29, 25.15, 0, 0);
INSERT INTO `tb_dormitory` VALUES (209, 3, 212, 6, 49.08, 37.55, 0, 0);
INSERT INTO `tb_dormitory` VALUES (210, 3, 213, 6, 42.17, 98.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (211, 3, 214, 6, 47.86, 6.49, 0, 0);
INSERT INTO `tb_dormitory` VALUES (212, 3, 215, 6, 56.10, 93.41, 0, 0);
INSERT INTO `tb_dormitory` VALUES (213, 3, 216, 6, 71.79, 40.40, 0, 0);
INSERT INTO `tb_dormitory` VALUES (214, 3, 217, 6, 17.55, 36.05, 0, 0);
INSERT INTO `tb_dormitory` VALUES (215, 3, 218, 6, 104.79, 58.94, 0, 0);
INSERT INTO `tb_dormitory` VALUES (216, 3, 219, 6, 76.67, 31.77, 0, 0);
INSERT INTO `tb_dormitory` VALUES (217, 3, 220, 6, 11.04, 30.26, 0, 0);
INSERT INTO `tb_dormitory` VALUES (218, 3, 301, 6, 37.14, 17.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (219, 3, 302, 6, 73.25, 82.52, 0, 0);
INSERT INTO `tb_dormitory` VALUES (220, 3, 303, 6, 43.24, 78.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (221, 3, 304, 6, 103.47, 76.14, 0, 0);
INSERT INTO `tb_dormitory` VALUES (222, 3, 305, 6, 40.10, 78.10, 0, 0);
INSERT INTO `tb_dormitory` VALUES (223, 3, 306, 6, 62.34, 37.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (224, 3, 307, 6, 53.28, 42.88, 0, 0);
INSERT INTO `tb_dormitory` VALUES (225, 3, 308, 6, 11.39, 55.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (226, 3, 309, 6, 106.66, 2.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (227, 3, 310, 6, 87.94, 86.91, 0, 0);
INSERT INTO `tb_dormitory` VALUES (228, 3, 311, 6, 80.29, 25.15, 0, 0);
INSERT INTO `tb_dormitory` VALUES (229, 3, 312, 6, 49.08, 37.55, 0, 0);
INSERT INTO `tb_dormitory` VALUES (230, 3, 313, 6, 42.17, 98.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (231, 3, 314, 6, 47.86, 6.49, 0, 0);
INSERT INTO `tb_dormitory` VALUES (232, 3, 315, 6, 56.10, 93.41, 0, 0);
INSERT INTO `tb_dormitory` VALUES (233, 3, 316, 6, 71.79, 40.40, 0, 0);
INSERT INTO `tb_dormitory` VALUES (234, 3, 317, 6, 17.55, 36.05, 0, 0);
INSERT INTO `tb_dormitory` VALUES (235, 3, 318, 6, 104.79, 58.94, 0, 0);
INSERT INTO `tb_dormitory` VALUES (236, 3, 319, 6, 76.67, 31.77, 0, 0);
INSERT INTO `tb_dormitory` VALUES (237, 3, 320, 6, 11.04, 30.26, 0, 0);
INSERT INTO `tb_dormitory` VALUES (238, 3, 401, 6, 37.14, 17.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (239, 3, 402, 6, 73.25, 82.52, 0, 0);
INSERT INTO `tb_dormitory` VALUES (240, 3, 403, 6, 43.24, 78.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (241, 3, 404, 6, 103.47, 76.14, 0, 0);
INSERT INTO `tb_dormitory` VALUES (242, 3, 405, 6, 40.10, 78.10, 0, 0);
INSERT INTO `tb_dormitory` VALUES (243, 3, 406, 6, 62.34, 37.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (244, 3, 407, 6, 53.28, 42.88, 0, 0);
INSERT INTO `tb_dormitory` VALUES (245, 3, 408, 6, 11.39, 55.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (246, 3, 409, 6, 106.66, 2.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (247, 3, 410, 6, 87.94, 86.91, 0, 0);
INSERT INTO `tb_dormitory` VALUES (248, 3, 411, 6, 80.29, 25.15, 0, 0);
INSERT INTO `tb_dormitory` VALUES (249, 3, 412, 6, 49.08, 37.55, 0, 0);
INSERT INTO `tb_dormitory` VALUES (250, 3, 413, 6, 42.17, 98.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (251, 3, 414, 6, 47.86, 6.49, 0, 0);
INSERT INTO `tb_dormitory` VALUES (252, 3, 415, 6, 56.10, 93.41, 0, 0);
INSERT INTO `tb_dormitory` VALUES (253, 3, 416, 6, 71.79, 40.40, 0, 0);
INSERT INTO `tb_dormitory` VALUES (254, 3, 417, 6, 17.55, 36.05, 0, 0);
INSERT INTO `tb_dormitory` VALUES (255, 3, 418, 6, 104.79, 58.94, 0, 0);
INSERT INTO `tb_dormitory` VALUES (256, 3, 419, 6, 76.67, 31.77, 0, 0);
INSERT INTO `tb_dormitory` VALUES (257, 3, 420, 6, 11.04, 30.26, 0, 0);
INSERT INTO `tb_dormitory` VALUES (258, 3, 501, 6, 37.14, 17.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (259, 3, 502, 6, 73.25, 82.52, 0, 0);
INSERT INTO `tb_dormitory` VALUES (260, 3, 503, 6, 43.24, 78.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (261, 3, 504, 6, 103.47, 76.14, 0, 0);
INSERT INTO `tb_dormitory` VALUES (262, 3, 505, 6, 40.10, 78.10, 0, 0);
INSERT INTO `tb_dormitory` VALUES (263, 3, 506, 6, 62.34, 37.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (264, 3, 507, 6, 53.28, 42.88, 0, 0);
INSERT INTO `tb_dormitory` VALUES (265, 3, 508, 6, 11.39, 55.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (266, 3, 509, 6, 106.66, 2.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (267, 3, 510, 6, 87.94, 86.91, 0, 0);
INSERT INTO `tb_dormitory` VALUES (268, 3, 511, 6, 80.29, 25.15, 0, 0);
INSERT INTO `tb_dormitory` VALUES (269, 3, 512, 6, 49.08, 37.55, 0, 0);
INSERT INTO `tb_dormitory` VALUES (270, 3, 513, 6, 42.17, 98.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (271, 3, 514, 6, 47.86, 6.49, 0, 0);
INSERT INTO `tb_dormitory` VALUES (272, 3, 515, 6, 56.10, 93.41, 0, 0);
INSERT INTO `tb_dormitory` VALUES (273, 3, 516, 6, 71.79, 40.40, 0, 0);
INSERT INTO `tb_dormitory` VALUES (274, 3, 517, 6, 17.55, 36.05, 0, 0);
INSERT INTO `tb_dormitory` VALUES (275, 3, 518, 6, 104.79, 58.94, 0, 0);
INSERT INTO `tb_dormitory` VALUES (276, 3, 519, 6, 76.67, 31.77, 0, 0);
INSERT INTO `tb_dormitory` VALUES (277, 3, 520, 6, 11.04, 30.26, 0, 0);
INSERT INTO `tb_dormitory` VALUES (278, 3, 601, 6, 37.14, 17.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (279, 3, 602, 6, 73.25, 82.52, 0, 0);
INSERT INTO `tb_dormitory` VALUES (280, 3, 603, 6, 43.24, 78.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (281, 3, 604, 6, 103.47, 76.14, 0, 0);
INSERT INTO `tb_dormitory` VALUES (282, 3, 605, 6, 40.10, 78.10, 0, 0);
INSERT INTO `tb_dormitory` VALUES (283, 3, 606, 6, 62.34, 37.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (284, 3, 607, 6, 53.28, 42.88, 0, 0);
INSERT INTO `tb_dormitory` VALUES (285, 3, 608, 6, 11.39, 55.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (286, 3, 609, 6, 106.66, 2.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (287, 3, 610, 6, 87.94, 86.91, 0, 0);
INSERT INTO `tb_dormitory` VALUES (288, 3, 611, 6, 80.29, 25.15, 0, 0);
INSERT INTO `tb_dormitory` VALUES (289, 3, 612, 6, 49.08, 37.55, 0, 0);
INSERT INTO `tb_dormitory` VALUES (290, 3, 613, 6, 42.17, 98.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (291, 3, 614, 6, 47.86, 6.49, 0, 0);
INSERT INTO `tb_dormitory` VALUES (292, 3, 615, 6, 56.10, 93.41, 0, 0);
INSERT INTO `tb_dormitory` VALUES (293, 3, 616, 6, 71.79, 40.40, 0, 0);
INSERT INTO `tb_dormitory` VALUES (294, 3, 617, 6, 17.55, 36.05, 0, 0);
INSERT INTO `tb_dormitory` VALUES (295, 3, 618, 6, 104.79, 58.94, 0, 0);
INSERT INTO `tb_dormitory` VALUES (296, 3, 619, 6, 76.67, 31.77, 0, 0);
INSERT INTO `tb_dormitory` VALUES (297, 3, 620, 6, 11.04, 30.26, 0, 0);
INSERT INTO `tb_dormitory` VALUES (298, 3, 701, 6, 37.14, 17.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (299, 3, 702, 6, 73.25, 82.52, 0, 0);
INSERT INTO `tb_dormitory` VALUES (300, 3, 703, 6, 43.24, 78.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (301, 3, 704, 6, 103.47, 76.14, 0, 0);
INSERT INTO `tb_dormitory` VALUES (302, 3, 705, 6, 40.10, 78.10, 0, 0);
INSERT INTO `tb_dormitory` VALUES (303, 3, 706, 6, 62.34, 37.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (304, 3, 707, 6, 53.28, 42.88, 0, 0);
INSERT INTO `tb_dormitory` VALUES (305, 3, 708, 6, 11.39, 55.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (306, 3, 709, 6, 106.66, 2.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (307, 3, 710, 6, 87.94, 86.91, 0, 0);
INSERT INTO `tb_dormitory` VALUES (308, 3, 711, 6, 80.29, 25.15, 0, 0);
INSERT INTO `tb_dormitory` VALUES (309, 3, 712, 6, 49.08, 37.55, 0, 0);
INSERT INTO `tb_dormitory` VALUES (310, 3, 713, 6, 42.17, 98.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (311, 3, 714, 6, 47.86, 6.49, 0, 0);
INSERT INTO `tb_dormitory` VALUES (312, 3, 715, 6, 56.10, 93.41, 0, 0);
INSERT INTO `tb_dormitory` VALUES (313, 3, 716, 6, 71.79, 40.40, 0, 0);
INSERT INTO `tb_dormitory` VALUES (314, 3, 717, 6, 17.55, 36.05, 0, 0);
INSERT INTO `tb_dormitory` VALUES (315, 3, 718, 6, 104.79, 58.94, 0, 0);
INSERT INTO `tb_dormitory` VALUES (316, 3, 719, 6, 76.67, 31.77, 0, 0);
INSERT INTO `tb_dormitory` VALUES (317, 3, 720, 6, 11.04, 30.26, 0, 0);
INSERT INTO `tb_dormitory` VALUES (318, 4, 101, 4, 37.14, 17.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (319, 4, 102, 4, 73.25, 82.52, 0, 0);
INSERT INTO `tb_dormitory` VALUES (320, 4, 103, 4, 43.24, 78.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (321, 4, 104, 4, 103.47, 76.14, 0, 0);
INSERT INTO `tb_dormitory` VALUES (322, 4, 105, 4, 40.10, 78.10, 0, 0);
INSERT INTO `tb_dormitory` VALUES (323, 4, 106, 4, 62.34, 37.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (324, 4, 107, 4, 53.28, 42.88, 0, 0);
INSERT INTO `tb_dormitory` VALUES (325, 4, 108, 4, 11.39, 55.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (326, 4, 109, 4, 106.66, 2.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (327, 4, 110, 4, 87.94, 86.91, 0, 0);
INSERT INTO `tb_dormitory` VALUES (328, 4, 111, 4, 80.29, 25.15, 0, 0);
INSERT INTO `tb_dormitory` VALUES (329, 4, 112, 4, 49.08, 37.55, 0, 0);
INSERT INTO `tb_dormitory` VALUES (330, 4, 113, 4, 42.17, 98.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (331, 4, 114, 4, 47.86, 6.49, 0, 0);
INSERT INTO `tb_dormitory` VALUES (332, 4, 115, 4, 56.10, 93.41, 0, 0);
INSERT INTO `tb_dormitory` VALUES (333, 4, 201, 4, 37.14, 17.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (334, 4, 202, 4, 73.25, 82.52, 0, 0);
INSERT INTO `tb_dormitory` VALUES (335, 4, 203, 4, 43.24, 78.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (336, 4, 204, 4, 103.47, 76.14, 0, 0);
INSERT INTO `tb_dormitory` VALUES (337, 4, 205, 4, 40.10, 78.10, 0, 0);
INSERT INTO `tb_dormitory` VALUES (338, 4, 206, 4, 62.34, 37.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (339, 4, 207, 4, 53.28, 42.88, 0, 0);
INSERT INTO `tb_dormitory` VALUES (340, 4, 208, 4, 11.39, 55.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (341, 4, 209, 4, 106.66, 2.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (342, 4, 210, 4, 87.94, 86.91, 0, 0);
INSERT INTO `tb_dormitory` VALUES (343, 4, 211, 4, 80.29, 25.15, 0, 0);
INSERT INTO `tb_dormitory` VALUES (344, 4, 212, 4, 49.08, 37.55, 0, 0);
INSERT INTO `tb_dormitory` VALUES (345, 4, 213, 4, 42.17, 98.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (346, 4, 214, 4, 47.86, 6.49, 0, 0);
INSERT INTO `tb_dormitory` VALUES (347, 4, 215, 4, 56.10, 93.41, 0, 0);
INSERT INTO `tb_dormitory` VALUES (348, 4, 301, 4, 37.14, 17.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (349, 4, 302, 4, 73.25, 82.52, 0, 0);
INSERT INTO `tb_dormitory` VALUES (350, 4, 303, 4, 43.24, 78.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (351, 4, 304, 4, 103.47, 76.14, 0, 0);
INSERT INTO `tb_dormitory` VALUES (352, 4, 305, 4, 40.10, 78.10, 0, 0);
INSERT INTO `tb_dormitory` VALUES (353, 4, 306, 4, 62.34, 37.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (354, 4, 307, 4, 53.28, 42.88, 0, 0);
INSERT INTO `tb_dormitory` VALUES (355, 4, 308, 4, 11.39, 55.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (356, 4, 309, 4, 106.66, 2.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (357, 4, 310, 4, 87.94, 86.91, 0, 0);
INSERT INTO `tb_dormitory` VALUES (358, 4, 311, 4, 80.29, 25.15, 0, 0);
INSERT INTO `tb_dormitory` VALUES (359, 4, 312, 4, 49.08, 37.55, 0, 0);
INSERT INTO `tb_dormitory` VALUES (360, 4, 313, 4, 42.17, 98.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (361, 4, 314, 4, 47.86, 6.49, 0, 0);
INSERT INTO `tb_dormitory` VALUES (362, 4, 315, 4, 56.10, 93.41, 0, 0);
INSERT INTO `tb_dormitory` VALUES (363, 4, 401, 4, 37.14, 17.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (364, 4, 402, 4, 73.25, 82.52, 0, 0);
INSERT INTO `tb_dormitory` VALUES (365, 4, 403, 4, 43.24, 78.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (366, 4, 404, 4, 103.47, 76.14, 0, 0);
INSERT INTO `tb_dormitory` VALUES (367, 4, 405, 4, 40.10, 78.10, 0, 0);
INSERT INTO `tb_dormitory` VALUES (368, 4, 406, 4, 62.34, 37.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (369, 4, 407, 4, 53.28, 42.88, 0, 0);
INSERT INTO `tb_dormitory` VALUES (370, 4, 408, 4, 11.39, 55.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (371, 4, 409, 4, 106.66, 2.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (372, 4, 410, 4, 87.94, 86.91, 0, 0);
INSERT INTO `tb_dormitory` VALUES (373, 4, 411, 4, 80.29, 25.15, 0, 0);
INSERT INTO `tb_dormitory` VALUES (374, 4, 412, 4, 49.08, 37.55, 0, 0);
INSERT INTO `tb_dormitory` VALUES (375, 4, 413, 4, 42.17, 98.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (376, 4, 414, 4, 47.86, 6.49, 0, 0);
INSERT INTO `tb_dormitory` VALUES (377, 4, 415, 4, 56.10, 93.41, 0, 0);
INSERT INTO `tb_dormitory` VALUES (378, 4, 501, 4, 37.14, 17.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (379, 4, 502, 4, 73.25, 82.52, 0, 0);
INSERT INTO `tb_dormitory` VALUES (380, 4, 503, 4, 43.24, 78.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (381, 4, 504, 4, 103.47, 76.14, 0, 0);
INSERT INTO `tb_dormitory` VALUES (382, 4, 505, 4, 40.10, 78.10, 0, 0);
INSERT INTO `tb_dormitory` VALUES (383, 4, 506, 4, 62.34, 37.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (384, 4, 507, 4, 53.28, 42.88, 0, 0);
INSERT INTO `tb_dormitory` VALUES (385, 4, 508, 4, 11.39, 55.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (386, 4, 509, 4, 106.66, 2.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (387, 4, 510, 4, 87.94, 86.91, 0, 0);
INSERT INTO `tb_dormitory` VALUES (388, 4, 511, 4, 80.29, 25.15, 0, 0);
INSERT INTO `tb_dormitory` VALUES (389, 4, 512, 4, 49.08, 37.55, 0, 0);
INSERT INTO `tb_dormitory` VALUES (390, 4, 513, 4, 42.17, 98.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (391, 4, 514, 4, 47.86, 6.49, 0, 0);
INSERT INTO `tb_dormitory` VALUES (392, 4, 515, 4, 56.10, 93.41, 0, 0);
INSERT INTO `tb_dormitory` VALUES (393, 5, 101, 4, 37.14, 17.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (394, 5, 102, 4, 73.25, 82.52, 0, 0);
INSERT INTO `tb_dormitory` VALUES (395, 5, 103, 4, 43.24, 78.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (396, 5, 104, 4, 103.47, 76.14, 0, 0);
INSERT INTO `tb_dormitory` VALUES (397, 5, 105, 4, 40.10, 78.10, 0, 0);
INSERT INTO `tb_dormitory` VALUES (398, 5, 106, 4, 62.34, 37.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (399, 5, 107, 4, 53.28, 42.88, 0, 0);
INSERT INTO `tb_dormitory` VALUES (400, 5, 108, 4, 11.39, 55.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (401, 5, 109, 4, 106.66, 2.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (402, 5, 110, 4, 87.94, 86.91, 0, 0);
INSERT INTO `tb_dormitory` VALUES (403, 5, 111, 4, 80.29, 25.15, 0, 0);
INSERT INTO `tb_dormitory` VALUES (404, 5, 112, 4, 49.08, 37.55, 0, 0);
INSERT INTO `tb_dormitory` VALUES (405, 5, 113, 4, 42.17, 98.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (406, 5, 114, 4, 47.86, 6.49, 0, 0);
INSERT INTO `tb_dormitory` VALUES (407, 5, 201, 4, 37.14, 17.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (408, 5, 202, 4, 73.25, 82.52, 0, 0);
INSERT INTO `tb_dormitory` VALUES (409, 5, 203, 4, 43.24, 78.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (410, 5, 204, 4, 103.47, 76.14, 0, 0);
INSERT INTO `tb_dormitory` VALUES (411, 5, 205, 4, 40.10, 78.10, 0, 0);
INSERT INTO `tb_dormitory` VALUES (412, 5, 206, 4, 62.34, 37.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (413, 5, 207, 4, 53.28, 42.88, 0, 0);
INSERT INTO `tb_dormitory` VALUES (414, 5, 208, 4, 11.39, 55.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (415, 5, 209, 4, 106.66, 2.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (416, 5, 210, 4, 87.94, 86.91, 0, 0);
INSERT INTO `tb_dormitory` VALUES (417, 5, 211, 4, 80.29, 25.15, 0, 0);
INSERT INTO `tb_dormitory` VALUES (418, 5, 212, 4, 49.08, 37.55, 0, 0);
INSERT INTO `tb_dormitory` VALUES (419, 5, 213, 4, 42.17, 98.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (420, 5, 214, 4, 47.86, 6.49, 0, 0);
INSERT INTO `tb_dormitory` VALUES (421, 5, 301, 4, 37.14, 17.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (422, 5, 302, 4, 73.25, 82.52, 0, 0);
INSERT INTO `tb_dormitory` VALUES (423, 5, 303, 4, 43.24, 78.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (424, 5, 304, 4, 103.47, 76.14, 0, 0);
INSERT INTO `tb_dormitory` VALUES (425, 5, 305, 4, 40.10, 78.10, 0, 0);
INSERT INTO `tb_dormitory` VALUES (426, 5, 306, 4, 62.34, 37.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (427, 5, 307, 4, 53.28, 42.88, 0, 0);
INSERT INTO `tb_dormitory` VALUES (428, 5, 308, 4, 11.39, 55.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (429, 5, 309, 4, 106.66, 2.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (430, 5, 310, 4, 87.94, 86.91, 0, 0);
INSERT INTO `tb_dormitory` VALUES (431, 5, 311, 4, 80.29, 25.15, 0, 0);
INSERT INTO `tb_dormitory` VALUES (432, 5, 312, 4, 49.08, 37.55, 0, 0);
INSERT INTO `tb_dormitory` VALUES (433, 5, 313, 4, 42.17, 98.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (434, 5, 314, 4, 47.86, 6.49, 0, 0);
INSERT INTO `tb_dormitory` VALUES (435, 5, 401, 4, 37.14, 17.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (436, 5, 402, 4, 73.25, 82.52, 0, 0);
INSERT INTO `tb_dormitory` VALUES (437, 5, 403, 4, 43.24, 78.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (438, 5, 404, 4, 103.47, 76.14, 0, 0);
INSERT INTO `tb_dormitory` VALUES (439, 5, 405, 4, 40.10, 78.10, 0, 0);
INSERT INTO `tb_dormitory` VALUES (440, 5, 406, 4, 62.34, 37.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (441, 5, 407, 4, 53.28, 42.88, 0, 0);
INSERT INTO `tb_dormitory` VALUES (442, 5, 408, 4, 11.39, 55.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (443, 5, 409, 4, 106.66, 2.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (444, 5, 410, 4, 87.94, 86.91, 0, 0);
INSERT INTO `tb_dormitory` VALUES (445, 5, 411, 4, 80.29, 25.15, 0, 0);
INSERT INTO `tb_dormitory` VALUES (446, 5, 412, 4, 49.08, 37.55, 0, 0);
INSERT INTO `tb_dormitory` VALUES (447, 5, 413, 4, 42.17, 98.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (448, 5, 414, 4, 47.86, 6.49, 0, 0);
INSERT INTO `tb_dormitory` VALUES (449, 5, 501, 4, 37.14, 17.43, 0, 0);
INSERT INTO `tb_dormitory` VALUES (450, 5, 502, 4, 73.25, 82.52, 0, 0);
INSERT INTO `tb_dormitory` VALUES (451, 5, 503, 4, 43.24, 78.96, 0, 0);
INSERT INTO `tb_dormitory` VALUES (452, 5, 504, 4, 103.47, 76.14, 0, 0);
INSERT INTO `tb_dormitory` VALUES (453, 5, 505, 4, 40.10, 78.10, 0, 0);
INSERT INTO `tb_dormitory` VALUES (454, 5, 506, 4, 62.34, 37.80, 0, 0);
INSERT INTO `tb_dormitory` VALUES (455, 5, 507, 4, 53.28, 42.88, 0, 0);
INSERT INTO `tb_dormitory` VALUES (456, 5, 508, 4, 11.39, 55.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (457, 5, 509, 4, 106.66, 2.46, 0, 0);
INSERT INTO `tb_dormitory` VALUES (458, 5, 510, 4, 87.94, 86.91, 0, 0);
INSERT INTO `tb_dormitory` VALUES (459, 5, 511, 4, 80.29, 25.15, 0, 0);
INSERT INTO `tb_dormitory` VALUES (460, 5, 512, 4, 49.08, 37.55, 0, 0);
INSERT INTO `tb_dormitory` VALUES (461, 5, 513, 4, 42.17, 98.72, 0, 0);
INSERT INTO `tb_dormitory` VALUES (462, 5, 514, 4, 47.86, 6.49, 0, 0);

-- ----------------------------
-- Table structure for tb_maintenance
-- ----------------------------
DROP TABLE IF EXISTS `tb_maintenance`;
CREATE TABLE `tb_maintenance`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '维修id',
  `dormitory_id` int NOT NULL COMMENT '宿舍号',
  `detail` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '维修详情',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `student` int NOT NULL COMMENT '请求的学生,通过学生获取电话',
  `status` tinyint NOT NULL COMMENT '维修状态，0待支付，1待维修，2完成，3异常',
  `maintenance_person_id` int NULL DEFAULT NULL COMMENT '维修人员id,通过维修人员表获取电话',
  `type_id` int NOT NULL COMMENT '维修的类型id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '维修详情表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_maintenance
-- ----------------------------

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '学生ID',
  `student_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生名字',
  `gender` tinyint(1) NULL DEFAULT 1 COMMENT '性别 1男 2女',
  `age` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '年龄',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `dormitory_id` int NULL DEFAULT NULL COMMENT '宿舍号，外键',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除标识(0-未删除；1-已删除)',
  `class_id` int NULL DEFAULT NULL COMMENT '班级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1004 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES (1, '王', 1, 20, '19024521315', 1, 0, 202301001);
INSERT INTO `tb_student` VALUES (2, 'Fujiwara Tsubasa', 2, 21, '19981901207', 1, 0, 202301002);
INSERT INTO `tb_student` VALUES (3, 'Sugawara Momoka', 2, 24, '19738385142', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (4, '顾詩涵', 1, 24, '76009748710', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (5, 'Abe Miu', 2, 22, '2850333713', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (6, 'Aoki Misaki', 1, 19, '18522282649', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (7, 'Saito Mio', 1, 23, '14051135684', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (8, 'Miyamoto Yuna', 1, 18, '18694133631', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (9, '杜宇宁', 2, 18, '76901108607', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (10, 'Saito Sakura', 2, 19, '2845721958', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (11, 'Otsuka Riku', 1, 22, '15355912382', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (12, '戴璐', 1, 20, '19430952053', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (13, 'Ueda Daisuke', 1, 21, '217051218', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (14, 'Noguchi Akina', 1, 20, '19275755598', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (15, 'Hirano Tsubasa', 2, 25, '13967807404', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (16, 'Watanabe Minato', 2, 20, '19495674734', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (17, 'Tanaka Ayano', 1, 20, '14628255834', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (18, 'Yamaguchi Yota', 1, 22, '15147866296', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (19, 'Yamada Kenta', 1, 22, '76096196086', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (20, 'Iwasaki Rin', 1, 23, '16759851315', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (21, 'Ono Minato', 2, 19, '1004155218', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (22, '宋嘉伦', 2, 24, '7601355029', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (23, 'Ikeda Yota', 2, 18, '280227251', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (24, 'Kaneko Kasumi', 2, 23, '7693057169', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (25, 'Ueda Yuto', 2, 19, '7606438091', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (26, '张秀英', 1, 21, '19760826217', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (27, 'Yoshida Kenta', 2, 24, '1024771116', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (28, '韩璐', 1, 19, '100975378', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (29, '史秀英', 2, 19, '212680548', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (30, '姜致远', 1, 20, '102458739', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (31, '田杰宏', 2, 22, '7606166417', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (32, 'Ono Momoe', 1, 25, '15052055755', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (33, '胡致远', 1, 21, '2813327401', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (34, 'Saito Yuna', 2, 23, '15117414340', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (35, 'Nakajima Yota', 1, 24, '15012559239', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (36, 'Miyazaki Kazuma', 1, 24, '216365531', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (37, '陶睿', 1, 22, '18533024515', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (38, 'Yamada Akina', 2, 21, '213691763', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (39, 'Harada Yuito', 2, 21, '13371707660', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (40, '龚震南', 1, 21, '13938167336', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (41, '侯子异', 1, 25, '14463188714', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (42, 'Nakayama Kenta', 1, 23, '2021341814', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (43, 'Ikeda Kenta', 1, 22, '17363571197', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (44, '魏子韬', 2, 24, '18458156487', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (45, 'Ichikawa Yuto', 1, 23, '14344729048', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (46, 'Takeuchi Hana', 2, 18, '201853866', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (47, '韦云熙', 2, 25, '2155617679', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (48, '向安琪', 2, 21, '7606320117', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (49, '谭震南', 2, 21, '14983000627', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (50, '周璐', 1, 19, '16427051966', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (51, '姚岚', 1, 21, '211386488', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (52, 'Nakamori Airi', 1, 22, '14999309323', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (53, '孙詩涵', 1, 25, '76088778215', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (54, 'Ishida Hikari', 1, 23, '18072845032', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (55, 'Yamada Yuito', 1, 19, '18426135228', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (56, '刘子韬', 2, 21, '13580904744', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (57, 'Fukuda Mitsuki', 1, 19, '2155640267', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (58, '石宇宁', 1, 20, '215863575', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (59, '张安琪', 1, 23, '214983174', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (60, '马宇宁', 1, 19, '7690530194', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (61, '吴云熙', 2, 19, '15150057102', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (62, 'Noguchi Ayano', 2, 25, '15759162092', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (63, 'Murata Kenta', 1, 24, '13889514136', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (64, 'Hasegawa Misaki', 2, 22, '14596935364', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (65, '秦杰宏', 1, 22, '283576753', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (66, 'Yamazaki Aoshi', 2, 23, '16888896875', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (67, '苏睿', 1, 19, '19176946792', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (68, 'Watanabe Sakura', 2, 18, '1020019574', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (69, '董岚', 2, 24, '13951077150', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (70, 'Wada Hikaru', 2, 21, '76955222300', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (71, '何子韬', 1, 21, '2163331452', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (72, 'Matsui Akina', 1, 23, '15019541538', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (73, 'Suzuki Nanami', 1, 24, '18571838998', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (74, '严嘉伦', 2, 23, '102588036', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (75, '李晓明', 1, 20, '102481218', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (76, '谭晓明', 1, 19, '2091676151', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (77, 'Miyazaki Hina', 1, 24, '16872298670', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (78, 'Murakami Yamato', 2, 23, '13467950653', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (79, 'Matsuda Kasumi', 1, 25, '1020958340', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (80, '武致远', 2, 20, '75515781271', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (81, '罗安琪', 1, 22, '76993440296', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (82, '钟岚', 1, 20, '13805674151', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (83, '戴子异', 1, 21, '209828557', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (84, 'Tamura Nanami', 1, 20, '76050040755', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (85, '郝晓明', 2, 21, '2193474999', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (86, '阎睿', 2, 20, '18921394074', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (87, '冯宇宁', 1, 23, '15914740695', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (88, '孙安琪', 1, 19, '14255310457', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (89, 'Kudo Aoi', 2, 18, '7695299965', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (90, '吴宇宁', 1, 18, '7550956829', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (91, 'Kikuchi Sara', 2, 20, '19489669659', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (92, '卢宇宁', 1, 18, '14347002187', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (93, '陶云熙', 1, 20, '13535384968', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (94, '崔宇宁', 1, 23, '212920561', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (95, '孔杰宏', 1, 21, '13620025679', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (96, 'Hashimoto Momoe', 1, 22, '18188957640', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (97, '韦宇宁', 1, 21, '1074314300', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (98, 'Kinoshita Hina', 1, 20, '109453438', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (99, 'Matsumoto Akina', 2, 22, '18596838594', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (100, 'Ota Kasumi', 2, 23, '13603950856', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (101, '魏嘉伦', 1, 24, '1086807311', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (102, '雷詩涵', 2, 23, '15161178767', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (103, 'Murakami Mitsuki', 1, 19, '76045024723', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (104, '贺致远', 1, 22, '13390268409', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (105, '孔云熙', 1, 21, '16142315152', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (106, 'Shimizu Hazuki', 2, 22, '18841015015', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (107, 'Kudo Rena', 1, 21, '13993266996', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (108, '宋云熙', 1, 22, '2067319000', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (109, '周詩涵', 1, 20, '16867272888', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (110, '程岚', 1, 24, '76970879191', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (111, 'Kikuchi Airi', 1, 23, '212306426', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (112, 'Ishida Momoe', 2, 22, '15656862979', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (113, '卢璐', 1, 24, '1037435060', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (114, 'Kojima Tsubasa', 1, 24, '2094063596', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (115, '叶嘉伦', 1, 20, '7694776376', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (116, 'Morita Akina', 2, 24, '2165413373', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (117, '方岚', 2, 19, '14595016492', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (118, 'Sasaki Yuna', 1, 23, '76080168489', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (119, '史安琪', 1, 21, '109355289', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (120, 'Koyama Miu', 2, 22, '2824608052', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (121, '陆震南', 2, 21, '209933320', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (122, '尹睿', 1, 21, '16554079857', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (123, 'Miyazaki Yuto', 1, 21, '76036329981', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (124, '吕詩涵', 2, 20, '16110699295', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (125, '周杰宏', 1, 22, '217579956', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (126, '董璐', 2, 22, '13458542450', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (127, '常睿', 2, 22, '2037344026', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (128, '梁璐', 2, 22, '17571770832', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (129, 'Wada Ren', 2, 22, '13928449690', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (130, 'Takeda Mitsuki', 1, 19, '2148779911', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (131, '于秀英', 2, 21, '18315517716', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (132, 'Matsuda Yota', 2, 20, '201557485', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (133, 'Takahashi Sara', 1, 24, '2874814799', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (134, 'Sugawara Hana', 1, 19, '18337943009', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (135, 'Murakami Ayato', 2, 18, '7558749659', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (136, 'Yokoyama Shino', 2, 23, '13843616315', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (137, '韦秀英', 1, 22, '219922299', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (138, 'Kaneko Nanami', 1, 21, '109399369', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (139, 'Sugiyama Shino', 1, 22, '7609689453', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (140, '许子韬', 1, 22, '7607527849', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (141, 'Kono Mio', 2, 21, '289441064', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (142, '邓杰宏', 1, 25, '2882129064', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (143, '贾子韬', 1, 24, '2152502990', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (144, 'Arimura Sakura', 1, 25, '13322661523', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (145, 'Maeda Ryota', 1, 24, '17780460112', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (146, '邓子异', 1, 18, '18630646419', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (147, 'Tamura Kaito', 1, 21, '18351820664', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (148, 'Sakurai Ayano', 1, 22, '207099691', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (149, '姚詩涵', 1, 25, '13514899807', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (150, '孟睿', 1, 19, '7602057567', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (151, '孙子异', 1, 23, '13243790895', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (152, 'Nakano Miu', 1, 20, '7602351027', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (153, 'Fukuda Momoe', 2, 21, '15564866320', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (154, 'Noguchi Yota', 1, 20, '218048588', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (155, '赵子异', 1, 22, '7559091714', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (156, 'Taniguchi Yuito', 1, 22, '7558172786', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (157, '周睿', 2, 23, '2168120881', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (158, 'Sato Hazuki', 1, 23, '13717694054', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (159, '尹子韬', 2, 23, '14199801423', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (160, 'Takahashi Miu', 1, 19, '13523959598', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (161, 'Murakami Hikaru', 2, 19, '76085979925', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (162, 'Goto Hina', 1, 22, '76930169904', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (163, 'Mori Rin', 2, 24, '13013718837', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (164, 'Arai Yamato', 2, 21, '7608600554', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (165, '卢子韬', 2, 20, '284986581', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (166, '钱詩涵', 1, 21, '13982694160', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (167, '蔡云熙', 2, 23, '106928105', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (168, '史岚', 1, 24, '16359564524', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (169, 'Murata Aoi', 2, 22, '15516034796', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (170, 'Nakayama Sakura', 2, 19, '14726122177', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (171, 'Fujita Kenta', 1, 23, '19875247342', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (172, '方震南', 2, 22, '104000306', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (173, '钱嘉伦', 1, 18, '76972619518', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (174, 'Kojima Shino', 2, 19, '17044980955', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (175, '徐云熙', 2, 22, '15071797018', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (176, 'Shimada Mitsuki', 1, 21, '218792763', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (177, 'Kaneko Mai', 1, 19, '19942748349', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (178, '唐璐', 2, 25, '18733574016', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (179, 'Sano Sakura', 1, 19, '76057121044', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (180, 'Yoshida Ryota', 2, 21, '18954895646', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (181, '马詩涵', 2, 23, '200977005', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (182, 'Shimada Nanami', 2, 23, '16108629784', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (183, '邓震南', 2, 23, '19882363573', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (184, '韩秀英', 2, 20, '17232219739', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (185, 'Endo Hazuki', 1, 25, '15977017743', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (186, 'Nomura Miu', 2, 18, '75589263696', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (187, 'Fujiwara Momoka', 2, 23, '288514526', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (188, '魏睿', 1, 23, '16423346960', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (189, '高子异', 2, 19, '16546049099', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (190, '马璐', 2, 22, '280438420', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (191, 'Otsuka Shino', 1, 19, '16071378254', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (192, 'Matsui Tsubasa', 1, 23, '7692776236', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (193, 'Goto Hana', 1, 20, '17160595879', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (194, 'Endo Ayato', 2, 22, '17951372054', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (195, '于晓明', 1, 25, '13284394803', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (196, '董晓明', 1, 25, '16797986869', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (197, '雷子异', 1, 25, '16258534192', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (198, 'Takahashi Kazuma', 1, 20, '17772555774', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (199, 'Yamaguchi Hikari', 2, 22, '18240279302', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (200, 'Hasegawa Kaito', 1, 23, '19960389560', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (201, '陆子异', 1, 19, '13428973645', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (202, 'Miyamoto Rin', 1, 24, '14951608406', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (203, 'Saito Hina', 2, 22, '218434594', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (204, '毛安琪', 2, 21, '200557404', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (205, '丁云熙', 2, 19, '17465787852', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (206, 'Harada Kazuma', 2, 18, '13735269784', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (207, '杜睿', 1, 25, '75582164067', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (208, 'Yamashita Daisuke', 1, 24, '14576687809', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (209, '严震南', 2, 24, '7557178024', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (210, '杜嘉伦', 1, 25, '205140927', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (211, 'Harada Hina', 2, 20, '207017820', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (212, 'Kobayashi Riku', 1, 21, '17484476094', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (213, 'Ono Misaki', 1, 20, '15824736527', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (214, 'Morita Ren', 2, 22, '1067815688', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (215, '潘岚', 2, 19, '1016807590', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (216, 'Hashimoto Aoshi', 1, 20, '2092584836', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (217, '夏秀英', 2, 23, '109429306', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (218, 'Ichikawa Yamato', 1, 19, '212346791', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (219, '谢安琪', 2, 21, '18534433882', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (220, '邓云熙', 2, 24, '18337350957', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (221, '吕致远', 1, 21, '19963259602', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (222, '杨嘉伦', 2, 19, '16260542096', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (223, '顾震南', 1, 24, '2149284389', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (224, '蒋睿', 2, 23, '15632666467', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (225, '段云熙', 1, 19, '13769504696', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (226, '程震南', 2, 19, '19642732843', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (227, 'Sato Rena', 1, 24, '13401711587', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (228, 'Maruyama Aoi', 1, 23, '13457306652', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (229, 'Sano Hazuki', 2, 19, '2190866190', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (230, '刘晓明', 1, 19, '2871251982', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (231, '杨睿', 2, 22, '204881663', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (232, '袁安琪', 2, 20, '17776053236', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (233, '薛嘉伦', 1, 22, '19673550787', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (234, 'Yamaguchi Hana', 1, 22, '7556311590', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (235, '史詩涵', 2, 20, '14904740992', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (236, 'Murakami Mai', 1, 24, '15484066890', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (237, 'Hara Ryota', 2, 22, '16767218607', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (238, 'Takada Sakura', 2, 20, '15965313300', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (239, 'Ishikawa Hikaru', 2, 20, '7607792190', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (240, 'Saito Eita', 1, 21, '202762424', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (241, 'Sato Hikaru', 2, 24, '104846854', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (242, 'Kono Akina', 1, 20, '19588174790', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (243, '江安琪', 2, 18, '286079761', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (244, '傅晓明', 2, 21, '1032977254', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (245, '陆岚', 1, 20, '13551076110', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (246, 'Yamazaki Sakura', 1, 23, '7554595469', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (247, 'Kaneko Sakura', 1, 21, '19797184116', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (248, '钟杰宏', 2, 23, '2865683548', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (249, '刘睿', 1, 19, '19816335828', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (250, '龙子异', 1, 24, '211565121', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (251, '郝子韬', 1, 18, '287148142', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (252, 'Arai Nanami', 1, 23, '19269464829', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (253, '金子韬', 2, 22, '18120178935', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (254, '沈杰宏', 2, 18, '14170089413', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (255, '于云熙', 1, 18, '17792653154', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (256, '韦安琪', 2, 25, '15812872633', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (257, 'Ito Mai', 2, 20, '18307359126', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (258, '萧安琪', 2, 24, '7604472502', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (259, '廖云熙', 1, 21, '17847677267', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (260, '薛子韬', 1, 19, '18233124480', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (261, 'Shimizu Hikaru', 1, 23, '1078012264', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (262, 'Kono Rena', 1, 23, '281483561', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (263, '蔡宇宁', 2, 23, '7607059639', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (264, 'Ueda Aoi', 1, 23, '76020288189', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (265, 'Shibata Ayato', 2, 19, '7555207110', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (266, '田震南', 1, 21, '76024176366', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (267, '徐致远', 2, 20, '280999791', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (268, 'Watanabe Yuito', 1, 24, '2164970218', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (269, 'Yoshida Kazuma', 2, 20, '205047448', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (270, 'Ishii Akina', 2, 19, '2078810796', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (271, '尹震南', 1, 22, '7600861628', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (272, '史云熙', 1, 18, '19197578141', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (273, 'Sasaki Yota', 2, 20, '7605937353', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (274, '阎岚', 1, 25, '17311687509', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (275, 'Nakayama Daisuke', 2, 24, '19586397351', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (276, 'Sakurai Tsubasa', 2, 24, '16155747651', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (277, 'Miyazaki Minato', 2, 23, '76910103782', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (278, 'Sugiyama Miu', 1, 24, '205837556', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (279, '郭晓明', 1, 19, '18882943647', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (280, '孟宇宁', 2, 19, '17014481378', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (281, '邱岚', 1, 24, '16917937907', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (282, '徐子异', 1, 19, '15308559745', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (283, '余子韬', 2, 21, '212480303', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (284, 'Harada Rena', 2, 19, '2077341865', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (285, 'Maruyama Mai', 2, 18, '76962881860', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (286, '潘震南', 1, 22, '219046534', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (287, '尹嘉伦', 1, 18, '7559532616', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (288, '梁震南', 1, 19, '76910448035', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (289, '张嘉伦', 1, 20, '106602241', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (290, 'Ogawa Sara', 1, 24, '2034819479', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (291, 'Saito Aoshi', 2, 22, '76989877014', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (292, 'Ikeda Mai', 1, 22, '2888106507', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (293, 'Kikuchi Hikari', 2, 22, '14838430803', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (294, 'Kojima Sara', 2, 20, '101390617', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (295, '汪宇宁', 2, 22, '18955000531', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (296, '吴睿', 2, 19, '15168545308', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (297, 'Kobayashi Yuna', 2, 22, '14625607085', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (298, '赵嘉伦', 2, 19, '7556230292', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (299, 'Takahashi Kenta', 2, 20, '19996006482', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (300, 'Sakamoto Takuya', 2, 21, '14154839037', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (301, '范宇宁', 1, 18, '15025572507', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (302, '陆晓明', 1, 20, '104222473', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (303, 'Murata Kasumi', 2, 24, '100071048', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (304, 'Suzuki Mio', 1, 19, '16318183965', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (305, 'Fujii Hikari', 1, 20, '17716148901', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (306, 'Hashimoto Daichi', 2, 19, '1084769662', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (307, 'Fujita Ren', 2, 22, '15463393061', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (308, '陈云熙', 1, 23, '14286752367', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (309, 'Tanaka Mai', 1, 20, '17814565508', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (310, 'Ota Miu', 2, 25, '18956517604', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (311, '任安琪', 2, 21, '75525388201', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (312, '程致远', 1, 24, '7551316083', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (313, 'Kaneko Rin', 2, 23, '19523729125', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (314, '曹岚', 2, 23, '2001681425', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (315, 'Ono Kenta', 2, 23, '75573036821', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (316, 'Sasaki Tsubasa', 2, 18, '200132058', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (317, '龙秀英', 2, 24, '1064426318', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (318, 'Murata Mai', 2, 24, '13618665771', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (319, '陶璐', 2, 19, '18368652708', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (320, 'Nakayama Yuto', 2, 25, '14198578415', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (321, 'Ishida Hazuki', 2, 19, '286274470', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (322, 'Ando Sakura', 1, 20, '16608960463', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (323, 'Inoue Hana', 2, 20, '14024076335', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (324, '侯安琪', 1, 21, '7556043267', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (325, 'Inoue Miu', 1, 23, '14585711467', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (326, '武嘉伦', 1, 21, '7608986435', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (327, '向璐', 2, 23, '17698948377', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (328, 'Maruyama Mio', 1, 24, '15499484054', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (329, 'Ueda Aoshi', 2, 24, '7554160220', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (330, '郑璐', 2, 22, '17856574114', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (331, 'Otsuka Miu', 2, 18, '15973728921', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (332, '袁秀英', 1, 24, '14335099494', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (333, 'Fujii Yuto', 1, 21, '15472214534', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (334, 'Matsui Sakura', 2, 19, '288176630', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (335, 'Koyama Hikaru', 2, 23, '15320177731', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (336, 'Ono Ayano', 1, 24, '287811333', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (337, '戴睿', 1, 22, '19575200832', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (338, 'Nakagawa Yuito', 1, 21, '16248426305', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (339, '邵杰宏', 1, 20, '1003044075', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (340, 'Wada Akina', 1, 19, '76946297138', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (341, 'Ueda Momoe', 1, 23, '7604676332', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (342, '叶子异', 2, 19, '287792365', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (343, '莫震南', 2, 21, '2835645130', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (344, '尹宇宁', 1, 20, '217141999', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (345, 'Murakami Misaki', 1, 23, '17010416200', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (346, '于詩涵', 1, 19, '215096588', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (347, '韩震南', 1, 24, '107835926', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (348, 'Ishii Aoi', 2, 23, '2841137884', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (349, 'Nakajima Mai', 2, 19, '19683324835', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (350, 'Nakano Ren', 2, 23, '75525861469', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (351, 'Koyama Kaito', 2, 21, '2065513544', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (352, 'Ishikawa Momoka', 2, 24, '15195524466', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (353, 'Morita Takuya', 2, 20, '13381802822', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (354, '范子异', 1, 19, '7551805765', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (355, 'Arai Hikari', 1, 23, '18425929968', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (356, 'Fujii Nanami', 2, 24, '101720888', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (357, 'Kudo Kenta', 1, 20, '19500435118', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (358, '孔晓明', 2, 25, '76951704078', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (359, '郑晓明', 2, 19, '13921899709', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (360, '何晓明', 2, 18, '2021492994', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (361, 'Shimada Momoka', 1, 24, '19948095027', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (362, 'Kudo Eita', 1, 19, '103350312', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (363, 'Takahashi Rena', 2, 20, '2809633629', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (364, '钟子异', 2, 19, '18547779050', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (365, '方子韬', 2, 24, '15474363124', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (366, 'Kimura Yuito', 2, 18, '76087230914', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (367, '余詩涵', 2, 21, '17101690824', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (368, '龙宇宁', 2, 25, '284590779', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (369, 'Sato Minato', 2, 23, '283109759', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (370, 'Ichikawa Mitsuki', 2, 19, '17162988727', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (371, 'Chiba Ikki', 1, 19, '18646671697', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (372, 'Kondo Momoe', 1, 20, '14155626331', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (373, 'Nomura Mitsuki', 2, 24, '2052168245', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (374, 'Aoki Miu', 2, 19, '15570278900', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (375, '廖睿', 1, 21, '13132711714', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (376, '王杰宏', 1, 20, '7695883321', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (377, 'Kono Momoka', 2, 24, '75506103563', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (378, 'Takahashi Sakura', 1, 22, '280274840', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (379, '朱云熙', 1, 20, '201495205', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (380, 'Kato Takuya', 2, 21, '2016769727', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (381, '武晓明', 2, 25, '15946776877', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (382, '贾岚', 1, 20, '18261230464', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (383, '叶子韬', 1, 20, '18625711989', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (384, '沈致远', 1, 22, '7698697448', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (385, 'Aoki Daisuke', 2, 22, '76988388572', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (386, 'Tanaka Minato', 2, 23, '16652906481', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (387, '韩致远', 2, 24, '16076493068', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (388, '彭晓明', 2, 23, '16246525181', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (389, '曹秀英', 1, 21, '201955586', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (390, '蒋秀英', 2, 23, '76951969154', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (391, 'Nakayama Ikki', 1, 22, '15830132685', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (392, '宋子异', 2, 21, '17431362464', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (393, 'Nakamura Hana', 1, 25, '1049016157', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (394, '夏嘉伦', 2, 20, '2816161495', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (395, '丁宇宁', 1, 20, '14925679483', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (396, 'Sugawara Ayano', 2, 21, '7607298897', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (397, 'Matsuda Hana', 2, 22, '2129542012', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (398, 'Fukuda Sakura', 1, 21, '15989070677', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (399, '姚杰宏', 1, 24, '14985421253', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (400, '向云熙', 1, 22, '16423364873', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (401, 'Nishimura Nanami', 2, 24, '76954205303', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (402, 'Wada Daichi', 1, 23, '7697387988', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (403, '韦睿', 2, 22, '281996867', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (404, '姚晓明', 1, 24, '1006516861', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (405, '江睿', 2, 24, '200856046', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (406, '董震南', 2, 18, '2855943532', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (407, '尹璐', 1, 24, '19189842434', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (408, 'Kojima Ayano', 2, 19, '18256572696', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (409, 'Iwasaki Mio', 2, 19, '76980886099', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (410, '冯秀英', 2, 24, '16078314757', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (411, '林宇宁', 1, 21, '201528245', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (412, '郭子韬', 2, 22, '13265921559', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (413, 'Saito Miu', 1, 23, '19089970700', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (414, '史睿', 2, 25, '17791483752', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (415, '贾安琪', 2, 24, '2139747357', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (416, 'Shibata Riku', 1, 23, '16553542205', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (417, 'Taniguchi Sara', 2, 23, '14578867519', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (418, '孟云熙', 2, 19, '1054384560', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (419, '向秀英', 1, 20, '13866169584', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (420, 'Takada Eita', 1, 21, '211846489', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (421, 'Ishii Kazuma', 2, 22, '105102180', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (422, 'Harada Aoshi', 1, 21, '17850156652', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (423, 'Hirano Ryota', 1, 20, '18052211491', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (424, '孔安琪', 1, 22, '18853172546', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (425, '龙杰宏', 1, 22, '7554176475', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (426, 'Matsuda Yuito', 1, 21, '1074418545', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (427, 'Kimura Takuya', 2, 25, '76979055945', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (428, '丁詩涵', 2, 23, '204823617', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (429, '龚嘉伦', 2, 20, '13573720622', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (430, '钱璐', 2, 24, '218400476', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (431, '许睿', 2, 22, '1092568017', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (432, '钟嘉伦', 1, 20, '75529466055', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (433, 'Sakurai Mitsuki', 1, 18, '14440284807', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (434, 'Mori Hikaru', 1, 20, '14433811287', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (435, 'Murata Mitsuki', 2, 23, '283238453', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (436, 'Kono Takuya', 2, 20, '2068633639', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (437, 'Imai Mio', 1, 24, '105182150', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (438, '向致远', 1, 23, '101123826', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (439, 'Nakagawa Yuna', 2, 24, '2135869230', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (440, 'Shimizu Eita', 2, 25, '16467866564', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (441, '范震南', 2, 23, '16375042115', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (442, 'Matsui Aoi', 2, 25, '17327393511', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (443, 'Sano Airi', 1, 22, '14351600322', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (444, 'Arimura Minato', 1, 20, '19614517618', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (445, '张璐', 1, 22, '1078439536', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (446, 'Yamashita Tsubasa', 1, 22, '19732658930', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (447, '毛岚', 2, 24, '19411041364', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (448, 'Sato Itsuki', 1, 18, '16713515367', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (449, 'Takagi Minato', 1, 21, '7600560142', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (450, 'Sano Daichi', 1, 22, '75562011604', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (451, 'Sato Sakura', 2, 18, '7608850109', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (452, '余秀英', 1, 19, '19802492670', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (453, '夏致远', 1, 23, '1041204195', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (454, 'Kojima Aoi', 2, 21, '19824634041', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (455, '龙子韬', 1, 25, '16037113253', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (456, 'Chiba Daisuke', 2, 21, '13738734742', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (457, '胡璐', 1, 24, '17319857326', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (458, 'Ito Kenta', 2, 20, '75536463523', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (459, 'Shibata Aoi', 1, 23, '76957946143', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (460, '罗宇宁', 1, 24, '19343642244', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (461, 'Kimura Mio', 1, 20, '19948114291', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (462, '董秀英', 2, 22, '76996168825', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (463, 'Takeda Yota', 2, 21, '14045758740', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (464, '段宇宁', 1, 21, '285626354', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (465, '周秀英', 2, 23, '287164791', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (466, 'Hayashi Aoshi', 2, 24, '76978178368', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (467, 'Fujiwara Aoshi', 2, 24, '19678290452', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (468, 'Nakamura Daichi', 1, 22, '18788421989', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (469, '侯睿', 1, 23, '15290859881', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (470, '胡子韬', 2, 22, '18574323852', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (471, 'Tanaka Eita', 2, 22, '19389693465', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (472, '钟安琪', 1, 21, '13524944782', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (473, '史晓明', 2, 20, '13964432354', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (474, 'Arai Rena', 1, 21, '18065320984', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (475, 'Uchida Hazuki', 1, 24, '13541295723', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (476, 'Hayashi Hana', 2, 21, '16689624916', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (477, '方秀英', 2, 22, '15063597047', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (478, '龚詩涵', 2, 19, '14158918829', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (479, 'Taniguchi Momoe', 1, 20, '2836780311', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (480, '吴晓明', 1, 19, '203276002', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (481, 'Hashimoto Ryota', 1, 20, '76078362452', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (482, 'Hara Rena', 1, 21, '7605622676', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (483, 'Nakamori Kenta', 1, 25, '7693286263', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (484, 'Ogawa Mio', 2, 24, '206406191', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (485, 'Kimura Mitsuki', 2, 18, '76987253099', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (486, 'Takahashi Takuya', 1, 21, '2895225987', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (487, 'Nishimura Hana', 2, 20, '101250356', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (488, '任嘉伦', 1, 21, '13861924717', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (489, '高詩涵', 2, 19, '16355259216', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (490, 'Sugawara Sakura', 2, 21, '15153949329', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (491, 'Maruyama Tsubasa', 1, 21, '19004161301', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (492, '毛睿', 2, 20, '17521483025', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (493, 'Arai Hana', 2, 19, '75526565543', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (494, '杜云熙', 2, 20, '7609804615', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (495, 'Sakurai Minato', 1, 20, '284064994', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (496, '黄子韬', 1, 21, '2025941579', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (497, 'Mori Ayato', 1, 24, '2095606252', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (498, '沈睿', 1, 23, '2127854155', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (499, 'Yamamoto Kenta', 1, 22, '76094435629', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (500, '莫嘉伦', 1, 18, '2868617524', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (501, '钟秀英', 2, 22, '212839260', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (502, 'Yamazaki Daichi', 1, 23, '76905089914', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (503, 'Tanaka Yota', 2, 19, '76900907103', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (504, 'Suzuki Yuto', 1, 20, '7554413872', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (505, '金子异', 1, 23, '17209439303', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (506, 'Okamoto Kaito', 1, 22, '210181566', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (507, 'Miura Momoka', 1, 18, '207223852', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (508, '魏云熙', 1, 25, '105368586', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (509, '郭璐', 2, 20, '18417744905', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (510, 'Tanaka Akina', 2, 20, '205051191', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (511, 'Sato Ayato', 1, 24, '19647056843', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (512, 'Takeuchi Itsuki', 1, 18, '107041458', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (513, '龙嘉伦', 2, 21, '15777678017', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (514, 'Kikuchi Itsuki', 2, 23, '104560928', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (515, 'Yamamoto Rin', 1, 18, '2178955723', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (516, '陆安琪', 1, 24, '7557518512', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (517, '贺杰宏', 1, 20, '7554759576', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (518, '韩晓明', 2, 18, '7693087407', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (519, '魏安琪', 2, 25, '76002137393', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (520, 'Nakano Daisuke', 2, 23, '206090741', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (521, 'Takagi Mio', 1, 24, '13539172792', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (522, '钱宇宁', 1, 23, '7692533978', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (523, '萧璐', 2, 18, '76011317492', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (524, 'Nakamura Akina', 1, 22, '19745089773', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (525, '彭杰宏', 2, 21, '13263132550', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (526, 'Otsuka Momoka', 1, 22, '201245232', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (527, 'Kato Hana', 2, 22, '76003879635', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (528, 'Shimizu Sakura', 1, 22, '7602010429', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (529, 'Fujita Daichi', 2, 24, '76905038808', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (530, '贺岚', 1, 19, '19157277777', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (531, 'Ito Akina', 1, 25, '104390255', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (532, '宋震南', 2, 19, '7697446041', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (533, '薛秀英', 2, 18, '15284323796', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (534, '赵杰宏', 1, 18, '16293737319', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (535, 'Yamamoto Hina', 2, 20, '76030270216', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (536, '邹杰宏', 2, 22, '15195856927', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (537, 'Ando Mitsuki', 1, 19, '14947568765', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (538, '谭云熙', 2, 22, '15359344140', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (539, '孟震南', 2, 23, '2039381587', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (540, 'Ota Hikaru', 2, 23, '15652602836', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (541, 'Sugiyama Itsuki', 1, 18, '287420158', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (542, '江晓明', 2, 25, '13389103412', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (543, 'Takeuchi Daisuke', 2, 24, '7695850771', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (544, '梁致远', 1, 21, '1001460535', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (545, 'Hashimoto Hikari', 1, 22, '2127082304', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (546, '郑子异', 1, 19, '281374210', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (547, '程安琪', 2, 20, '7696878934', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (548, 'Kobayashi Ayato', 2, 20, '17217169917', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (549, '范杰宏', 1, 24, '18547511244', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (550, 'Harada Shino', 1, 23, '19145477637', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (551, 'Yamashita Mitsuki', 2, 25, '19950303404', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (552, '贺睿', 2, 20, '19808670391', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (553, '黄震南', 2, 19, '2189565742', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (554, 'Nakajima Minato', 2, 21, '75592282786', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (555, '赵致远', 2, 19, '16704162838', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (556, 'Matsui Aoshi', 1, 21, '17494985416', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (557, '李秀英', 1, 22, '13425944946', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (558, 'Takada Aoi', 2, 21, '2875871806', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (559, 'Okamoto Hikari', 2, 21, '17934186307', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (560, '余璐', 1, 24, '13707780564', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (561, '雷璐', 2, 21, '14220504729', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (562, 'Tanaka Hina', 2, 18, '16295105229', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (563, 'Iwasaki Sara', 2, 23, '100709637', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (564, 'Fujita Hazuki', 1, 23, '102288611', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (565, '董詩涵', 2, 18, '15615629385', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (566, '曾致远', 1, 20, '15700148059', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (567, 'Sakai Ayano', 1, 22, '19013095164', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (568, 'Inoue Tsubasa', 2, 20, '19867277692', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (569, '李云熙', 1, 19, '76020866321', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (570, 'Shibata Shino', 1, 21, '14614318736', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (571, 'Yamashita Miu', 1, 23, '18125260529', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (572, 'Noguchi Nanami', 2, 23, '108022582', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (573, '姜晓明', 2, 22, '75529674229', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (574, '夏震南', 1, 25, '15190248826', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (575, '钱睿', 2, 24, '76974970597', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (576, '莫晓明', 2, 19, '16976644812', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (577, '钟震南', 1, 20, '7551867382', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (578, 'Kono Kasumi', 2, 18, '14280673328', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (579, '于岚', 1, 21, '16009172986', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (580, 'Yamashita Aoi', 1, 20, '2018116176', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (581, 'Kudo Daisuke', 1, 23, '2165671406', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (582, '魏晓明', 1, 20, '16412913348', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (583, 'Ogawa Hazuki', 2, 23, '76029350434', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (584, 'Nakamori Nanami', 2, 23, '76049559507', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (585, 'Koyama Ikki', 1, 18, '289034292', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (586, 'Miyazaki Ikki', 2, 22, '14201067558', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (587, '毛致远', 2, 21, '13197034412', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (588, '马嘉伦', 2, 21, '1006725281', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (589, 'Harada Itsuki', 2, 24, '13498271808', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (590, 'Hasegawa Mitsuki', 2, 18, '218573047', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (591, 'Masuda Miu', 2, 21, '16682409507', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (592, 'Ishii Rin', 2, 24, '7607669070', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (593, '唐岚', 1, 23, '15482420200', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (594, '廖岚', 1, 20, '13360976098', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (595, '郝岚', 2, 21, '16746561508', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (596, 'Nakajima Nanami', 1, 19, '19491107371', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (597, '梁云熙', 1, 23, '283761414', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (598, '杜子韬', 1, 23, '282208873', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (599, 'Imai Yuto', 2, 24, '2030703638', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (600, 'Hara Seiko', 1, 20, '109984131', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (601, 'Watanabe Yuto', 1, 21, '201242146', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (602, '孟安琪', 2, 21, '18151614677', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (603, '沈詩涵', 2, 24, '16389576598', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (604, 'Yamaguchi Hina', 2, 23, '76036326081', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (605, '任杰宏', 2, 23, '17942564719', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (606, 'Nakamura Mai', 2, 21, '16381733077', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (607, 'Sakurai Mio', 1, 19, '7692068576', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (608, 'Hayashi Sara', 1, 22, '287250343', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (609, 'Yamazaki Seiko', 2, 23, '2885119592', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (610, 'Sakamoto Ayano', 2, 22, '2815189484', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (611, 'Ichikawa Momoe', 1, 23, '13350014684', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (612, '史震南', 1, 21, '204089252', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (613, 'Maeda Hikaru', 2, 23, '17707644833', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (614, 'Tanaka Ren', 2, 21, '18805673665', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (615, '吕子韬', 2, 22, '2853530377', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (616, '曾子异', 1, 20, '2046845724', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (617, '袁詩涵', 1, 21, '17093108164', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (618, 'Yamaguchi Minato', 2, 19, '1028065727', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (619, '高晓明', 1, 22, '14423026467', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (620, 'Okamoto Nanami', 2, 21, '18488325025', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (621, 'Kobayashi Ryota', 2, 23, '19898662190', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (622, 'Uchida Shino', 2, 19, '18752059998', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (623, 'Nakayama Aoshi', 1, 21, '16662985444', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (624, 'Murata Tsubasa', 2, 24, '7698973100', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (625, 'Suzuki Shino', 1, 18, '7690542886', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (626, 'Maruyama Airi', 2, 18, '209285581', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (627, '彭嘉伦', 2, 19, '19017722518', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (628, '金岚', 1, 22, '16543706655', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (629, '曾秀英', 2, 20, '2052946797', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (630, 'Fujiwara Kenta', 1, 21, '281587493', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (631, 'Hasegawa Eita', 1, 20, '7691589539', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (632, '蔡璐', 1, 22, '108214828', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (633, 'Imai Miu', 2, 18, '2114942641', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (634, 'Sakai Yuna', 2, 24, '76907934070', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (635, 'Tamura Yuto', 2, 21, '2819095894', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (636, 'Maeda Rena', 1, 22, '212313110', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (637, 'Takagi Misaki', 1, 21, '203493962', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (638, '郭岚', 2, 18, '288975332', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (639, 'Takada Tsubasa', 2, 19, '212531188', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (640, '苏秀英', 1, 19, '76088123334', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (641, '徐睿', 2, 19, '16602775915', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (642, '罗岚', 1, 23, '15582378113', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (643, 'Matsumoto Yuto', 1, 21, '17335398855', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (644, 'Hirano Miu', 2, 23, '100645246', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (645, 'Kimura Daichi', 2, 24, '76025955731', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (646, 'Sugiyama Minato', 1, 23, '102263617', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (647, 'Nakamori Eita', 1, 23, '18964282307', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (648, 'Kato Ayato', 1, 24, '16083787335', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (649, 'Yamashita Kasumi', 2, 20, '19178337381', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (650, '林子韬', 2, 19, '18919347115', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (651, 'Kono Hazuki', 2, 18, '76903610594', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (652, '姜秀英', 1, 21, '19897785112', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (653, '秦璐', 2, 21, '2843766862', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (654, '黎晓明', 1, 20, '109591806', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (655, '曾云熙', 2, 20, '76977961020', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (656, '孙嘉伦', 1, 19, '15328335046', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (657, 'Kato Ayano', 1, 23, '7692973942', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (658, 'Koyama Hazuki', 2, 25, '76915037555', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (659, 'Takagi Ayano', 2, 24, '76921726870', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (660, 'Kimura Ren', 1, 21, '7696318787', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (661, 'Iwasaki Airi', 2, 20, '19635106211', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (662, 'Sano Ryota', 1, 22, '17058576499', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (663, 'Aoki Kenta', 1, 21, '205480547', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (664, 'Sugawara Itsuki', 2, 18, '2898703987', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (665, 'Sato Shino', 1, 24, '15769078936', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (666, '李致远', 2, 21, '19990838055', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (667, '袁杰宏', 2, 23, '17419399666', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (668, '廖杰宏', 1, 19, '282245322', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (669, '莫睿', 2, 18, '15035903684', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (670, 'Arimura Ayano', 1, 22, '19534066933', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (671, '阎宇宁', 1, 20, '19510222669', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (672, '郝嘉伦', 1, 21, '19977227177', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (673, 'Yamada Momoka', 2, 20, '16001082874', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (674, 'Ito Ren', 1, 21, '13421571739', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (675, '谭秀英', 1, 20, '207432228', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (676, 'Miyazaki Kaito', 1, 24, '105237176', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (677, 'Hirano Daichi', 1, 24, '18201196573', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (678, 'Shibata Ikki', 1, 20, '15694975040', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (679, 'Fujii Hazuki', 1, 24, '7553404481', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (680, 'Sato Yuito', 1, 24, '76056680804', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (681, '唐嘉伦', 1, 23, '1084271047', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (682, 'Yamaguchi Daisuke', 1, 24, '18061669979', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (683, 'Ishida Ayato', 2, 20, '18404702387', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (684, '毛震南', 2, 24, '76990879588', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (685, 'Ito Seiko', 2, 19, '2873265574', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (686, '沈秀英', 1, 20, '2843044547', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (687, 'Hasegawa Seiko', 1, 23, '15249694521', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (688, '韦致远', 2, 21, '19606656907', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (689, '冯震南', 1, 20, '13051178990', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (690, 'Koyama Daisuke', 1, 20, '2162835852', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (691, 'Ueda Rin', 2, 25, '15934647606', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (692, '江震南', 2, 24, '2044412635', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (693, 'Kono Kazuma', 1, 25, '7550979474', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (694, 'Nomura Yuna', 1, 21, '283348209', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (695, 'Yamamoto Misaki', 1, 25, '13080291169', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (696, 'Kinoshita Rin', 1, 22, '15507243873', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (697, '曾睿', 1, 21, '13835383263', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (698, 'Ishikawa Kenta', 1, 22, '76909870573', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (699, '叶晓明', 1, 24, '17197648180', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (700, 'Ito Rena', 1, 24, '15182720679', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (701, 'Hayashi Daichi', 1, 25, '76948163464', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (702, 'Nakano Ryota', 1, 24, '13369706795', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (703, 'Chiba Yuto', 2, 24, '7559462311', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (704, 'Matsuda Rena', 2, 23, '19630838354', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (705, 'Ono Hikaru', 2, 22, '76081424405', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (706, '孙致远', 2, 24, '105835363', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (707, 'Kimura Tsubasa', 1, 19, '7552386981', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (708, '刘云熙', 1, 21, '18795234219', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (709, 'Yamashita Itsuki', 2, 21, '15097032670', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (710, '莫璐', 1, 19, '17787648109', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (711, 'Okada Yuna', 2, 21, '13343558049', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (712, '任子异', 2, 24, '1057374060', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (713, '傅子异', 1, 22, '16825250224', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (714, '薛璐', 1, 20, '1013765052', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (715, 'Ono Aoi', 1, 21, '13516493262', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (716, '陶晓明', 1, 18, '16616343386', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (717, '武秀英', 2, 20, '15616423917', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (718, '蒋璐', 1, 20, '2131597398', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (719, '卢秀英', 2, 21, '75556056514', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (720, 'Kondo Kasumi', 1, 24, '16951216710', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (721, 'Takahashi Yamato', 2, 22, '7606429603', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (722, 'Matsumoto Hana', 1, 21, '75556618275', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (723, '余岚', 1, 18, '75569196791', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (724, '邵子异', 2, 24, '76042401330', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (725, 'Ikeda Rin', 2, 24, '19698074447', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (726, '吴璐', 2, 19, '75529656953', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (727, '孔子异', 1, 20, '19527720450', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (728, '邹致远', 2, 21, '102687379', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (729, '陈詩涵', 1, 19, '76028272012', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (730, '谭子异', 1, 20, '201953471', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (731, 'Yokoyama Miu', 1, 19, '2137602484', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (732, '董致远', 1, 24, '14436685194', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (733, 'Sato Aoshi', 1, 22, '1086919049', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (734, '廖璐', 2, 23, '2062677503', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (735, 'Fukuda Hana', 2, 19, '17587938691', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (736, 'Sugiyama Hikaru', 1, 23, '1020450687', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (737, 'Ono Rin', 2, 24, '19427285802', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (738, 'Goto Akina', 2, 21, '286948748', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (739, '陆宇宁', 2, 22, '2827741113', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (740, 'Tamura Momoka', 2, 23, '7559308502', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (741, 'Miyazaki Misaki', 1, 19, '15842311669', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (742, 'Arimura Rena', 2, 21, '13842510134', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (743, 'Okamoto Mitsuki', 1, 21, '15853528609', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (744, '冯子韬', 2, 19, '13557418254', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (745, '顾秀英', 2, 22, '76055299883', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (746, '罗睿', 2, 19, '19093586932', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (747, '孟璐', 2, 19, '18927245941', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (748, '魏宇宁', 1, 22, '17459341260', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (749, '傅安琪', 1, 24, '219395743', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (750, 'Endo Yota', 1, 20, '13368710946', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (751, 'Okada Yamato', 2, 20, '13970817650', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (752, 'Ueno Rena', 2, 19, '13805086559', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (753, 'Koyama Ryota', 1, 24, '19659450932', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (754, 'Kikuchi Kaito', 1, 22, '2111352770', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (755, '雷嘉伦', 1, 25, '19654465474', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (756, 'Kimura Aoshi', 1, 21, '16768357927', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (757, 'Saito Tsubasa', 1, 23, '205209620', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (758, '张云熙', 2, 21, '13456761206', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (759, '曹子异', 1, 23, '16117003478', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (760, 'Yamazaki Itsuki', 2, 24, '76094643902', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (761, '梁秀英', 2, 24, '19971451417', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (762, 'Yoshida Seiko', 2, 22, '18447696483', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (763, '田詩涵', 2, 20, '2815708355', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (764, 'Wada Yuto', 2, 21, '15656075825', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (765, 'Ishikawa Ryota', 1, 23, '17394581070', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (766, 'Nakano Kenta', 1, 24, '1017486048', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (767, 'Maeda Itsuki', 2, 24, '75567940579', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (768, 'Ogawa Ikki', 1, 21, '7550311442', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (769, '朱睿', 1, 22, '14706590999', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (770, '沈子异', 1, 25, '76077455445', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (771, '傅杰宏', 1, 21, '16754248843', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (772, 'Takada Ryota', 1, 20, '17155468284', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (773, 'Kobayashi Shino', 2, 23, '108368551', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (774, '周子异', 2, 18, '14741200050', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (775, 'Hayashi Yota', 1, 25, '13554765582', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (776, 'Ota Rin', 2, 19, '2054586848', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (777, 'Watanabe Riku', 2, 23, '2114874477', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (778, '邹璐', 1, 24, '2007025869', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (779, 'Kato Eita', 1, 19, '75505811138', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (780, '常子异', 1, 23, '13178422432', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (781, '周子韬', 1, 21, '18966311617', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (782, '武岚', 1, 18, '16686631046', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (783, '赵宇宁', 1, 18, '13822314018', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (784, 'Maeda Hana', 1, 22, '16277741701', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (785, 'Taniguchi Kaito', 2, 24, '2067272673', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (786, '苏安琪', 1, 23, '19120797785', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (787, 'Tanaka Mitsuki', 2, 18, '15041221781', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (788, '薛岚', 2, 21, '15900207892', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (789, 'Ueno Daichi', 2, 21, '216928448', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (790, '秦詩涵', 1, 20, '17540773239', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (791, 'Otsuka Tsubasa', 1, 22, '18460213161', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (792, 'Yamada Ikki', 2, 23, '19550710743', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (793, '段睿', 2, 18, '7699595357', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (794, '蔡致远', 2, 18, '213088615', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (795, '钟詩涵', 1, 22, '19333046468', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (796, 'Sakai Sakura', 1, 19, '14239503076', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (797, 'Morita Kazuma', 1, 23, '16210897884', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (798, 'Ishii Sara', 1, 22, '19930140012', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (799, 'Kato Kazuma', 1, 20, '17834599153', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (800, '李睿', 1, 23, '17960938462', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (801, 'Ikeda Takuya', 2, 18, '18686626657', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (802, 'Shimada Yota', 1, 20, '7600731170', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (803, 'Noguchi Rin', 1, 20, '2014449722', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (804, 'Ishikawa Momoe', 2, 25, '18530643185', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (805, 'Sakurai Daisuke', 1, 20, '15944794912', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (806, 'Ueda Yota', 2, 23, '14232532102', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (807, 'Hayashi Hikaru', 1, 24, '76965216540', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (808, '何秀英', 1, 20, '7693254219', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (809, 'Shimada Mio', 1, 19, '13702994114', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (810, '吴詩涵', 1, 19, '14023870472', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (811, 'Kono Kaito', 2, 22, '15152365089', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (812, 'Wada Rin', 1, 22, '212341147', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (813, 'Yamamoto Miu', 1, 23, '286300408', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (814, 'Sakurai Shino', 2, 20, '14398224758', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (815, '杨晓明', 2, 18, '216840361', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (816, 'Miura Rin', 2, 23, '1046128976', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (817, 'Saito Hikari', 2, 23, '19517071065', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (818, 'Uchida Yota', 1, 24, '15644268665', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (819, 'Koyama Nanami', 2, 19, '14471392109', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (820, 'Koyama Aoshi', 1, 24, '218210367', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (821, '林云熙', 2, 25, '2806693588', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (822, 'Morita Hikaru', 2, 23, '7557042961', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (823, 'Sugiyama Yuna', 1, 23, '14454884695', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (824, 'Kinoshita Aoi', 1, 23, '16838846199', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (825, 'Fujita Ayato', 2, 23, '13187438855', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (826, 'Ando Seiko', 2, 19, '15885271283', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (827, '孔睿', 2, 21, '14638028998', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (828, 'Murata Seiko', 1, 20, '15288283562', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (829, '雷秀英', 1, 20, '7691717983', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (830, 'Nakajima Riku', 1, 19, '200765586', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (831, '唐震南', 1, 18, '16455774589', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (832, 'Fujiwara Hana', 2, 25, '7555269128', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (833, '马睿', 2, 21, '14735900791', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (834, '贾云熙', 2, 21, '2171702099', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (835, '萧詩涵', 1, 24, '219771742', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (836, 'Watanabe Tsubasa', 1, 25, '14700743115', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (837, '汪安琪', 1, 21, '13412074285', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (838, '李詩涵', 2, 20, '17993107930', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (839, 'Hashimoto Ayano', 1, 22, '7550739864', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (840, 'Sasaki Itsuki', 2, 24, '18349698760', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (841, '陶秀英', 1, 22, '287041463', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (842, '钟晓明', 2, 23, '76017016282', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (843, '彭岚', 2, 18, '16856394248', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (844, '雷宇宁', 2, 23, '15175372014', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (845, '沈岚', 2, 19, '7693121760', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (846, '邱杰宏', 2, 24, '200713932', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (847, 'Ueda Shino', 2, 18, '205869623', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (848, '段璐', 1, 20, '7696280208', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (849, '程睿', 1, 22, '18213179066', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (850, 'Shibata Itsuki', 2, 20, '213185280', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (851, '蔡子异', 2, 21, '18069681213', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (852, '梁安琪', 2, 19, '7601754280', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (853, 'Kinoshita Misaki', 1, 19, '76922783519', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (854, 'Shibata Eita', 1, 23, '1008186946', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (855, 'Nakano Misaki', 2, 23, '284861609', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (856, 'Ogawa Tsubasa', 2, 19, '19069192198', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (857, 'Otsuka Nanami', 2, 22, '18699664140', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (858, '戴震南', 2, 18, '16581821253', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (859, '许云熙', 1, 21, '13999731869', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (860, 'Nakamura Tsubasa', 1, 19, '1063492568', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (861, 'Ogawa Airi', 2, 20, '13824348723', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (862, 'Koyama Airi', 1, 25, '76018372187', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (863, '卢晓明', 2, 20, '108073283', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (864, '武云熙', 1, 20, '16463738866', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (865, 'Otsuka Rena', 2, 24, '17324588451', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (866, 'Morita Ryota', 2, 19, '14029544515', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (867, 'Nakamori Yuito', 2, 19, '14696391778', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (868, '廖詩涵', 2, 19, '2862907890', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (869, 'Abe Rena', 2, 20, '288460167', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (870, '尹子异', 2, 21, '16361503199', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (871, 'Okada Ayato', 2, 24, '18889301727', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (872, '雷子韬', 1, 24, '106120148', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (873, '陆杰宏', 2, 18, '17964803620', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (874, '莫岚', 2, 20, '16340671423', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (875, '吴安琪', 2, 19, '7559120605', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (876, '姜詩涵', 1, 24, '2089702959', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (877, '沈嘉伦', 2, 21, '15156721859', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (878, 'Okamoto Miu', 1, 23, '2174065091', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (879, '邓嘉伦', 2, 22, '108194086', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (880, '冯睿', 1, 23, '18422522075', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (881, '范致远', 2, 21, '7601619322', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (882, 'Saito Hana', 2, 25, '14563416180', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (883, '江嘉伦', 1, 24, '76995665831', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (884, 'Masuda Takuya', 1, 25, '7609437239', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (885, '严致远', 1, 19, '7604387704', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (886, '郝安琪', 2, 19, '17871447812', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (887, '朱震南', 1, 21, '75501126696', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (888, 'Ota Momoe', 2, 22, '18216515285', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (889, 'Koyama Momoka', 2, 19, '15796688038', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (890, 'Ogawa Aoi', 2, 21, '7553944607', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (891, 'Fujita Tsubasa', 1, 19, '15308144059', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (892, 'Otsuka Aoshi', 2, 25, '14385826340', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (893, 'Nishimura Hikari', 2, 23, '17454021436', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (894, '邹子异', 1, 19, '18853907771', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (895, '郝璐', 2, 23, '7559512762', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (896, 'Kobayashi Hikari', 2, 20, '15504317201', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (897, 'Okamoto Rin', 1, 22, '1093901706', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (898, 'Mori Ikki', 2, 25, '2807459777', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (899, '高云熙', 2, 24, '16164472876', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (900, 'Okamoto Hana', 1, 24, '209154611', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (901, 'Matsuda Ayano', 1, 19, '76069381747', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (902, '郑震南', 2, 22, '2090113848', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (903, 'Matsuda Ryota', 2, 19, '7550014267', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (904, 'Sato Miu', 1, 18, '2110916625', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (905, '王子韬', 2, 19, '2179344553', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (906, '侯詩涵', 2, 20, '17536705628', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (907, '夏詩涵', 2, 18, '13331874356', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (908, 'Ishida Tsubasa', 2, 19, '19667963351', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (909, '林秀英', 1, 21, '215070731', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (910, 'Okamoto Ikki', 1, 24, '76018400565', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (911, 'Ueda Miu', 1, 24, '17116206016', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (912, 'Nakano Aoi', 2, 24, '18782169147', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (913, '黄云熙', 1, 19, '289375180', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (914, '孙岚', 1, 24, '18040084784', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (915, 'Nishimura Eita', 2, 19, '15204711904', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (916, 'Sugawara Takuya', 1, 24, '19384218603', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (917, 'Matsui Minato', 1, 21, '2042961234', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (918, '马子韬', 2, 20, '7552886630', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (919, '张杰宏', 1, 20, '16171106300', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (920, '龙晓明', 1, 22, '18290932026', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (921, '苏致远', 2, 18, '16628611094', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (922, 'Chiba Hikari', 1, 23, '200573413', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (923, '方致远', 2, 24, '205120576', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (924, '常岚', 2, 19, '7556853604', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (925, 'Murata Momoe', 2, 21, '15928047588', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (926, 'Hara Tsubasa', 2, 24, '15213912228', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (927, '武杰宏', 1, 22, '76923298674', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (928, 'Ito Yuna', 2, 22, '13366992533', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (929, 'Abe Yota', 2, 24, '216909979', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (930, '石晓明', 1, 19, '17034750694', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (931, '范詩涵', 1, 24, '19947874268', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (932, '高致远', 1, 23, '15237556704', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (933, '袁致远', 1, 24, '13741979943', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (934, 'Kaneko Tsubasa', 2, 23, '19063018159', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (935, 'Suzuki Akina', 2, 25, '7691735595', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (936, 'Tamura Hazuki', 1, 20, '7691303320', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (937, 'Matsuda Aoshi', 1, 21, '2016319891', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (938, 'Hirano Misaki', 2, 18, '19508586545', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (939, '于杰宏', 2, 25, '15588736128', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (940, 'Aoki Mitsuki', 1, 22, '17116960467', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (941, '于嘉伦', 2, 23, '18426088167', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (942, '韩宇宁', 2, 18, '18645647791', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (943, 'Noguchi Aoshi', 2, 20, '13122478784', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (944, 'Kojima Daichi', 2, 21, '14606380445', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (945, 'Nakagawa Rin', 1, 18, '7694625026', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (946, 'Sano Kasumi', 2, 23, '217386882', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (947, 'Ishikawa Yuna', 2, 23, '18934945175', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (948, '周晓明', 2, 22, '18437620276', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (949, '邱安琪', 2, 24, '75558674095', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (950, 'Yamazaki Momoka', 2, 20, '14597364245', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (951, 'Miyazaki Mai', 2, 20, '13072818210', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (952, 'Sato Yota', 2, 20, '2072680007', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (953, 'Ueno Tsubasa', 1, 18, '2099690281', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (954, '冯致远', 1, 19, '19077105360', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (955, 'Sasaki Ikki', 2, 22, '15292421192', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (956, 'Murakami Ayano', 2, 24, '16203415938', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (957, 'Sato Hina', 1, 19, '19063508682', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (958, '卢睿', 2, 25, '16533885077', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (959, '曹致远', 2, 20, '76098307162', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (960, 'Nakajima Yuto', 1, 20, '17894030285', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (961, 'Fukuda Ayano', 1, 22, '76068469275', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (962, 'Shimizu Ren', 1, 23, '14091737739', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (963, '朱秀英', 2, 22, '1003377037', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (964, '邹睿', 2, 23, '16629102217', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (965, 'Nakamura Ikki', 1, 22, '14898291471', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (966, 'Hara Rin', 2, 21, '7604316597', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (967, 'Fujii Hina', 2, 22, '7698139672', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (968, 'Shibata Yuna', 2, 22, '15071117176', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (969, '杜岚', 2, 21, '17395529221', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (970, '金璐', 2, 19, '1046337729', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (971, '江璐', 2, 23, '213323632', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (972, 'Maeda Mai', 1, 24, '15991357749', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (973, 'Ono Itsuki', 1, 25, '17679638588', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (974, 'Arai Sara', 1, 24, '16207420463', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (975, 'Kato Sakura', 2, 20, '13953482146', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (976, '林杰宏', 2, 18, '217285579', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (977, 'Matsuda Kenta', 1, 21, '14360538643', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (978, '贾致远', 2, 18, '7603886680', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (979, 'Hasegawa Yuna', 1, 20, '108795708', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (980, 'Nakayama Akina', 1, 19, '13955031309', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (981, '邵安琪', 2, 19, '16377146086', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (982, 'Arai Takuya', 1, 21, '2887945782', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (983, '赵璐', 2, 24, '2828989013', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (984, 'Miyamoto Aoi', 1, 19, '19339918077', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (985, 'Masuda Rena', 1, 22, '15771513962', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (986, 'Nakamura Airi', 2, 18, '2091142301', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (987, 'Endo Mai', 2, 22, '75557536133', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (988, '严子韬', 1, 23, '15067265017', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (989, 'Maeda Ayato', 1, 24, '7600602867', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (990, 'Kikuchi Tsubasa', 1, 19, '2839276393', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (991, 'Kobayashi Kazuma', 1, 23, '14023961233', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (992, '蔡詩涵', 1, 23, '19850825153', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (993, 'Endo Hikaru', 1, 19, '19600160947', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (994, 'Kinoshita Rena', 1, 20, '7698647059', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (995, '唐晓明', 2, 19, '15223064172', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (996, 'Nakano Yuto', 1, 24, '282794934', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (997, 'Wada Kazuma', 2, 19, '16748724268', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (998, '熊震南', 1, 24, '17491600765', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (999, 'Endo Akina', 2, 24, '16972084439', NULL, 1, NULL);
INSERT INTO `tb_student` VALUES (1000, 'Wada Ryota', 1, 20, '2841040112', NULL, 1, NULL);
INSERT INTO `tb_student` VALUES (1001, '宋致远', 1, 21, '17097849903', NULL, 0, NULL);
INSERT INTO `tb_student` VALUES (1003, '李小龙', 1, 20, '1760819842', 1, 0, NULL);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '人员id',
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '宿管名字',
  `role` bigint NOT NULL COMMENT '角色',
  `type_id` bigint NULL DEFAULT NULL COMMENT '类型id',
  `gender` tinyint(1) NOT NULL COMMENT '性别1男 2女',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `age` tinyint NULL DEFAULT NULL COMMENT '年龄',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除标识(0-未删除；1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '人员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', 0, NULL, 1, '17608440747', 20, 0);
INSERT INTO `tb_user` VALUES (2, '刘捞', 1, 1, 1, '15573280315', 30, 0);
INSERT INTO `tb_user` VALUES (3, '水维修', 2, 1, 1, '15415415115', 50, 0);

-- ----------------------------
-- Table structure for violation_log
-- ----------------------------
DROP TABLE IF EXISTS `violation_log`;
CREATE TABLE `violation_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_id` bigint NOT NULL COMMENT '违规学生id',
  `type` int NOT NULL COMMENT '违规类型',
  `flag` tinyint NOT NULL DEFAULT 0 COMMENT '状态(0-正常；1-申诉；)',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除标识(1:已删除;0:未删除)',
  `detail` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '违规详情',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '违规记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of violation_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
