package com.ths.forfun.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接口响应VO
 *
 */
@Data
@ApiModel
public final class ResultVO<T> {

    @ApiModelProperty("响应码")
    private Integer code;

    @ApiModelProperty("响应消息")
    private String msg;

    @ApiModelProperty("数据")
    private T data;
}
