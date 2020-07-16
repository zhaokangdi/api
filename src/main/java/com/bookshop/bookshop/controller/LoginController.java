package com.bookshop.bookshop.controller;

import com.bookshop.bookshop.entity.Business;
import com.bookshop.bookshop.entity.LoginEntity;
import com.bookshop.bookshop.result.Result;
import com.bookshop.bookshop.result.ResultFactory;
import com.bookshop.bookshop.entity.User;
import com.bookshop.bookshop.service.BookService;
import com.bookshop.bookshop.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.bookshop.bookshop.service.UserService;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    BusinessService businessService;

    @Autowired
    BookService bookService;

    @CrossOrigin
    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {

        String user_id;
        user_id = requestUser.getId();
        String password;
        password = requestUser.getPassword();

        if(user_id.equals("Admin")){
            if(password.equals("Admin"))
                return ResultFactory.buildSuccessResult(userService.listAllUser(),"系统管理员");
            else
                return ResultFactory.buildFailResult("用户名密码不匹配！");
        }
        else{
            User user = userService.login(user_id,password);

            if (null == user) {

                Business business = businessService.login(user_id,password);

                if(business == null)
                    return ResultFactory.buildFailResult("用户密码不匹配！");  //用户不存在
                else
                    return ResultFactory.buildSuccessResult(business,"登录成功！");
            } else {
                return ResultFactory.buildSuccessResult(user,"登录成功！");
            }
        }
    }

    @CrossOrigin
    @PostMapping("/api/register")
    @ResponseBody
    public Result register(@RequestBody User user) {

        int status = userService.register(user);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("该id已存在！");
            case 1:
                return ResultFactory.buildSuccessResult(user,"注册成功！");
            case -1:
                return ResultFactory.buildFailResult("注册失败！");
        }
        return ResultFactory.buildFailResult("未知错误");
    }

    @CrossOrigin
    @PostMapping("/api/entity")
    @ResponseBody
    //获取当前用户的个人信息
    public Result entity(@RequestBody User user) {

        LoginEntity entity = userService.userSettingInfo(user.getId());
        return ResultFactory.buildSuccessResult(entity,null);
    }

}

