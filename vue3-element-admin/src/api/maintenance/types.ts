export interface Maintenance{
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
}


export interface MaintenPage extends PageQuery{
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

