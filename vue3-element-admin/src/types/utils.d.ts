import {ElForm} from "@/types/auto-imports";

export interface RefType extends ElForm{
    clearValidate: Function,
    resetFields: Function,
    scrollToField: Function,
    validate: Function,
    validateField: Function
}
