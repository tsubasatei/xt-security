package com.xt.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * user 查询条件
 */
@Data
public class UserQueryCondition implements Serializable {
    
    private String username;
    private Integer age;
    private Integer ageTo;
    private String xxx;
}
