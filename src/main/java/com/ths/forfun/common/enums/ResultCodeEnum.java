package com.ths.forfun.common.enums;

/**
 * 接口响应码
 *
 */
public enum ResultCodeEnum {

    /**
     * 成功
     **/
    SUCCESS(0, "成功");


    private int code;
    private String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
