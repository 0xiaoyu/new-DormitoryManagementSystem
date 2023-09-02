import { ElForm } from "@/types/auto-imports";
import { PointerType } from "@vueuse/core";

export interface RefType extends ElForm {
  clearValidate: Function;
  resetFields: Function;
  scrollToField: Function;
  validate: Function;
  validateField: Function;
}

export interface Positions {
  lat: Number;
  lng: Number;
}

export interface MapConfig {
  center: PointerType;
  scrollWheelZoom?: Boolean;
  zoom?: Number;
  click?: Function;
}

export interface BmMark {
  position: Positions; //标注的位置
  title?: string; // 鼠标移到marker上的显示内容
  click?: Function; // 点击标注图标后会触发此事件
  dblclick?: Function; // 双击标注图标后会触发此事件
  mousedown?: Function; // 鼠标在标注图上按下触发此事件
  mouseup?: Function; // 鼠标在标注图上释放触发此事件
}
