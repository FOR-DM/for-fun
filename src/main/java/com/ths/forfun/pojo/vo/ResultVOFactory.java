package com.ths.forfun.pojo.vo;


import com.ths.forfun.common.enums.ResultCodeEnum;

import java.util.Collections;

/**
 * 接口响应VO工厂类
 *
 */
public final class ResultVOFactory {

    private ResultVOFactory() {
    }

    public static <T> ResultVO<T> buildSuccess() {
        return build(ResultCodeEnum.SUCCESS, null);
    }

    public static <T> ResultVO<T> buildSuccess(T data) {
        return build(ResultCodeEnum.SUCCESS, data);
    }

    public static <T> ResultVO<T> build(ResultCodeEnum result) {
        return build(result.getCode(), result.getMsg(), null);
    }

    public static <T> ResultVO<T> build(ResultCodeEnum result, T data) {
        return build(result.getCode(), result.getMsg(), data);
    }

    public static <T> ResultVO<T> build(int code, String msg) {
        return build(code, msg, null);
    }

    @SuppressWarnings({"unchecked"})
    public static <T> ResultVO<T> build(int code, String msg, T data) {
        ResultVO resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(msg);

        if (data != null) {
            resultVO.setData(data);
        } else {
            resultVO.setData(Collections.emptyMap());
        }

        return resultVO;
    }
}
