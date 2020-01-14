package com.vicente.vicenteboot.enums;

/**
 * 异常信息枚举类
 *
 * @author shiweisen
 * @since 2019-10-30
 */
public enum  ExceptionEnum {
    UNKNOW_ERROR(-1,"未知错误"),
    USER_NOT_FIND(-101,"用户不存在");

    private Integer code;

    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
