export interface DormitoryPageQuery extends PageQuery {
  buildingId?: string;
  floor?: string;
  dormitoryNumber?: string;
  buildType?: number;
}

export interface Dormitory{
  id?: string;
  buildingId?: string;
  buildingName?: string;
  dormitoryNumber?: string;
  buildType?: number;
  capacity?: number;
  electricity?: number;
  water?: number;
  eStatus?: number;
  wStatus?: number;
}
