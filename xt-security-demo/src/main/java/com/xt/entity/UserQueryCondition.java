package com.xt.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * user 查询条件
 */
@Data
public class UserQueryCondition implements Serializable {
    
    private String username;

    @ApiModelProperty(value = "用户年龄起始值")
    private Integer age;

    @ApiModelProperty(value = "用户年龄终止值")
    private Integer ageTo;
    private String xxx;
}
