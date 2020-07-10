package com.bookshop.bookshop.controller;

import com.bookshop.bookshop.result.Result;
import com.bookshop.bookshop.result.ResultFactory;
import com.bookshop.bookshop.entity.User;
import com.bookshop.bookshop.service.BookService;
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
    BookService bookService;

    @CrossOrigin
    @GetMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {

        String userid;
        userid = requestUser.getId();

        User user = userService.getUser(userid,requestUser.getPassword());

        if (null == user) {
            System.out.println("无");
            return ResultFactory.buildFailResult("用户密码不匹配！");  //用户不存在
        } else {
            System.out.println("有");
            return ResultFactory.buildSuccessResult(user,"注册成功！");
        }
    }

    @CrossOrigin
    @PostMapping("/api/register")
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

}

