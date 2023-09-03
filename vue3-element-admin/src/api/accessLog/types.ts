export interface PassLogPageQuery extends PageQuery {
  type: string;
  startTime?: string;
  endTime?: string;
}
