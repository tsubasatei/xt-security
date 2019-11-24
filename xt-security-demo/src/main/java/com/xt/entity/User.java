package com.xt.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.xt.validator.MyConstraint;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * @JsonView 使用步骤
 *  1. 使用接口来声明多个视图
 *  2. 在值对象的 get 方法上指定视图
 *  3. 在 Controller 方法上指定视图
 */
@Data
public class User implements Serializable {

    public interface UserSimpleView {};
    public interface UserDetailView extends UserSimpleView {};


    @JsonView(UserSimpleView.class)
    private Integer id;

    @MyConstraint(message = "这是一个测试约束")
    @JsonView(UserSimpleView.class)
    private String username;

    @NotBlank(message = "密码不能为空")
    @JsonView(UserDetailView.class)
    private String password;

    @Past(message = "生日必须为过去的一个时间")
    @JsonView(UserSimpleView.class)
    private Date birth;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }
}
