package com.czz.test.markdown.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author czz
 * @version 1.0
 * @date 2020/8/27 21:37
 */
@Data
@ApiModel(value = "客户实体")
public class User {
    @ApiModelProperty("客户用户名")
    private String name;
    @ApiModelProperty("客户id")
    private Integer id;
}
