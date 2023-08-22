create table access_log
(
    id          bigint auto_increment comment '进出id'
        primary key,
    user_id     int                                not null comment '进出id',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    a_type      tinyint                            not null comment '进出类型 0 进 1出'
)
    comment '进入记录';

create table d_building
(
    id         int auto_increment comment '楼栋id'
        primary key,
    build_name varchar(40) collate utf8mb4_general_ci null comment '楼名字,10字以内',
    latitude   double(10, 6)                          not null comment '经度',
    longitude  double(10, 6)                          not null comment '纬度',
    maxroom    tinyint default 10                     not null comment '一层的最大房间号'
)
    comment '宿舍楼栋';

create table ele_log
(
    id           bigint auto_increment
        primary key,
    dormitory_id int                                  not null comment '宿舍id',
    amount       int                                  not null comment '缴费金额',
    user_id      int                                  not null comment '缴费者id',
    create_time  timestamp  default CURRENT_TIMESTAMP not null comment '缴费时间',
    status       tinyint(1) default 0                 not null comment '订单状态 0 未支付，1完成'
)
    comment '电费日志';

create table receive_notice_msg
(
    id         bigint auto_increment comment '接收通知id'
        primary key,
    notice_id  bigint  not null comment '接收的通知id',
    receive_id int     not null comment '接收者id',
    r_status   tinyint not null comment '接收状态,0未读，1已读',
    constraint receive_notice_msg_列_name_receive_id_uindex
        unique (notice_id, receive_id)
)
    comment '接收通知';

create table sender_notice_msg
(
    id        bigint auto_increment comment '通知id'
        primary key,
    sender_id int           not null comment '发送者id，在tb_user表内的id',
    n_msg     varchar(1000) not null comment '通知内容',
    n_type    tinyint       not null comment '通知类型'
)
    comment '通知消息';

create table student_log
(
    id          bigint       not null comment '日志id'
        primary key,
    type        tinyint      not null comment '操作类型',
    detail      varchar(255) null comment '描述',
    create_time datetime     not null comment '创建时间'
)
    comment '学生管理日志';

create table sug_box
(
    id          int auto_increment
        primary key,
    student_id  int                                 null comment '学生id',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    status      tinyint   default 0                 not null comment '建议状态 0 未处理，1未恢复，2已回复,3已经处理',
    user_id     int                                 not null comment '回复人员id',
    type        tinyint                             null comment '建议类型是否匿名'
)
    comment '建议箱，反馈箱';

create table sug_text
(
    id     bigint auto_increment comment 'id'
        primary key,
    s_id   int     not null comment '建议箱id',
    s_text text    not null comment '内容',
    type   tinyint not null comment '0发起，1回复'
)
    comment '建议内容';

create table sys_dict
(
    id          bigint auto_increment comment '主键'
        primary key,
    type_code   varchar(64)             null comment '字典类型编码',
    name        varchar(50)  default '' null comment '字典项名称',
    value       varchar(50)  default '' null comment '字典项值',
    sort        int          default 0  null comment '排序',
    status      tinyint      default 0  null comment '状态(1:正常;0:禁用)',
    defaulted   tinyint      default 0  null comment '是否默认(1:是;0:否)',
    remark      varchar(255) default '' null comment '备注',
    create_time datetime                null comment '创建时间',
    update_time datetime                null comment '更新时间'
)
    comment '字典数据表' collate = utf8mb4_general_ci
                         row_format = DYNAMIC;

create table sys_dict_type
(
    id          bigint auto_increment comment '主键 '
        primary key,
    name        varchar(50) default '' null comment '类型名称',
    code        varchar(50) default '' null comment '类型编码',
    status      tinyint(1)  default 0  null comment '状态(0:正常;1:禁用)',
    remark      varchar(255)           null comment '备注',
    create_time datetime               null comment '创建时间',
    update_time datetime               null comment '更新时间',
    constraint type_code
        unique (code)
)
    comment '字典类型表' collate = utf8mb4_general_ci
                         row_format = DYNAMIC;

create table sys_menu
(
    id          bigint auto_increment
        primary key,
    parent_id   bigint                  not null comment '父菜单ID',
    tree_path   varchar(255)            null comment '父节点ID路径',
    name        varchar(64)  default '' not null comment '菜单名称',
    type        tinyint                 not null comment '菜单类型(1:菜单；2:目录；3:外链；4:按钮)',
    path        varchar(128) default '' null comment '路由路径(浏览器地址栏路径)',
    component   varchar(128)            null comment '组件路径(vue页面完整路径，省略.vue后缀)',
    perm        varchar(128)            null comment '权限标识',
    visible     tinyint(1)   default 1  not null comment '显示状态(1-显示;0-隐藏)',
    sort        int          default 0  null comment '排序',
    icon        varchar(64)  default '' null comment '菜单图标',
    redirect    varchar(128)            null comment '跳转路径',
    create_time datetime                null comment '创建时间',
    update_time datetime                null comment '更新时间'
)
    comment '菜单管理' collate = utf8mb4_general_ci
                       row_format = DYNAMIC;

create table sys_role
(
    id          bigint auto_increment comment '用户id'
        primary key,
    name        varchar(64) collate utf8mb4_general_ci not null comment '角色名称',
    code        varchar(32) collate utf8mb4_general_ci null comment '角色编码',
    sort        int                                    null comment '显示顺序',
    status      tinyint(1) default 1                   null comment '角色状态(1-正常；0-停用)',
    deleted     tinyint(1) default 0                   not null comment '逻辑删除标识(0-未删除；1-已删除)',
    data_scope  tinyint                                null comment '数据权限',
    create_time datetime                               null comment '创建时间',
    update_time datetime                               null comment '更新时间',
    constraint name
        unique (name)
);

create table sys_role_menu
(
    role_id bigint not null comment '角色ID',
    menu_id bigint not null comment '菜单ID',
    primary key (role_id, menu_id)
)
    comment '角色和菜单关联表' collate = utf8mb4_general_ci
                               row_format = DYNAMIC;

create table sys_user
(
    id          bigint auto_increment comment '用户id'
        primary key,
    name        varchar(80)          not null comment '用户名',
    password    varchar(100)         not null comment '密码,最多30字符',
    role        int                  not null comment '角色 0管理员',
    avatar      varchar(255)         null comment '用户头像',
    email       varchar(100)         null comment '邮箱',
    status      tinyint(1) default 1 null comment '用户状态(1:正常;0:禁用)',
    user_id     bigint               null comment '绑定id',
    deleted     tinyint(1) default 0 null comment '逻辑删除标志(0:未删除，1:已删除)',
    create_time datetime             null comment '创建时间',
    update_time datetime             null comment '更新时间',
    constraint login_name
        unique (name)
)
    collate = utf8mb4_general_ci;

create table sys_user_role
(
    user_id bigint not null comment '用户ID',
    role_id bigint not null comment '角色ID',
    primary key (user_id, role_id)
)
    comment '用户和角色关联表' collate = utf8mb4_general_ci
                               row_format = DYNAMIC;

create table tb_dormitory
(
    id               bigint auto_increment comment '宿舍id'
        primary key,
    building_id      int                       not null comment '楼层栋',
    dormitory_number int                       not null comment '宿舍号后2位为宿舍号，前为楼层',
    capacity         tinyint                   not null comment '宿舍容量',
    electricity      double(6, 2) default 0.00 not null comment '电费',
    water            double(6, 2) default 0.00 not null comment '水费',
    e_status         tinyint      default 0    not null comment '电的状态(0-正常；1-停用;2-送水)',
    w_status         tinyint      default 0    not null comment '水状态(0-正常;1-停用;2-送水)',
    constraint tb_dormitory_building_id_dormitory_number_uindex
        unique (building_id, dormitory_number)
);

create table tb_maintenance
(
    id                    bigint auto_increment comment '维修id'
        primary key,
    dormitory_id          int      not null comment '宿舍号',
    detail                int      null comment '维修详情',
    create_time           datetime not null comment '创建时间',
    student               int      not null comment '请求的学生,通过学生获取电话',
    status                tinyint  not null comment '维修状态，0待支付，1待维修，2完成，3异常',
    maintenance_person_id int      null comment '维修人员id,通过维修人员表获取电话',
    type_id               int      not null comment '维修的类型id'
);

create table tb_student
(
    id           char(10)             not null comment '学生ID'
        primary key,
    student_name varchar(20)          not null comment '学生名字',
    gender       tinyint(1) default 1 null comment '性别 1男 2女',
    age          tinyint unsigned     null comment '年龄',
    phone        char(11)             null comment '手机号',
    dormitory_id int                  null comment '宿舍号，外键',
    deleted      tinyint    default 0 not null comment '逻辑删除标识(0-未删除；1-已删除)',
    class_id     int                  null comment '班级'
);

create table tb_user
(
    id      bigint auto_increment comment '人员id'
        primary key,
    name    varchar(25)       not null comment '宿管名字',
    gender  tinyint(1)        not null comment '性别0 男 1女',
    phone   char(11)          not null comment '手机号',
    age     tinyint           null comment '年龄',
    deleted tinyint default 0 not null comment '逻辑删除标识(0-未删除；1-已删除)'
)
    comment '人员表';

create table violation_log
(
    id         bigint auto_increment
        primary key,
    student_id int               not null comment '违规学生id',
    type       int               not null comment '违规类型',
    flag       tinyint default 0 not null comment '状态(0-正常；1-申诉；)',
    deleted    tinyint default 0 not null comment '逻辑删除标识(1:已删除;0:未删除)',
    detail     varchar(1000)     null comment '违规详情'
)
    comment '违规记录';


