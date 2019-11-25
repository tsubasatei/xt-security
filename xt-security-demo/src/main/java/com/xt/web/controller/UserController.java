package com.xt.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.xt.entity.User;
import com.xt.entity.UserQueryCondition;
import com.xt.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails /*Authentication authentication*/) {
//        return SecurityContextHolder.getContext().getAuthentication();
        return userDetails;
    }

    @JsonView(User.UserSimpleView.class)
    @GetMapping
    @ApiOperation(value = "用户查询列表服务")
    public List<User> list(/*@RequestParam(name = "username", required = false, defaultValue = "xt") String username, */
                            UserQueryCondition userQueryCondition, @PageableDefault(page = 1, size = 10, sort = "username, asc") Pageable pageable) {
        System.out.println("查询条件：" + ReflectionToStringBuilder.toString(userQueryCondition, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println("每页记录数：" + pageable.getPageSize());
        System.out.println("页码：" + pageable.getPageNumber());
        System.out.println(pageable.getSort()); // age: DESC
        List<User> list = new ArrayList<>();
        list.add(new User("zhangsan", "123456"));
        list.add(new User("lisi", "123456"));
        list.add(new User("wangwu", "123456"));
        return list;
    }

    // id ：只能接收数字
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User findOne(@ApiParam("用户id") @PathVariable(value = "id") Integer id) {
        System.out.println("进入findOne服务");
        if (id == 0) {
            throw new UserNotExistException(id);
        }
        User user = new User("sanae", "123456");
        return user;
    }

    @PostMapping
    public User save(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getFieldErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        user.setId(1);
        System.out.println(user);
        return user;
    }

    @PutMapping
    public User update(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getFieldErrors().forEach(error ->
//                    System.out.println(error.getField() + " : " + error.getDefaultMessage())
                            System.out.println(error.getDefaultMessage())
            );
        }
        user.setId(1);
        System.out.println(user);
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable Integer id) {
        System.out.println("id: " + id);
    }
}
