package com.bookshop.bookshop.controller;

import com.bookshop.bookshop.entity.Business;
import com.bookshop.bookshop.entity.User;
import com.bookshop.bookshop.result.Result;
import com.bookshop.bookshop.result.ResultFactory;
import com.bookshop.bookshop.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @CrossOrigin
    @GetMapping("/api/business/login")
    public Result login(@RequestBody Business business) {

        Business businessOld = businessService.login(business);
        if(businessOld == null)
            return ResultFactory.buildFailResult("用户密码不匹配！");
        else
            return ResultFactory.buildSuccessResult(businessOld,"登录！");
    }

    @CrossOrigin
    @PostMapping("/api/business/register")
    public Result register(@RequestBody Business business) {

        int status = businessService.register(business);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("该id已存在！");
            case 1:
                return ResultFactory.buildSuccessResult(business,"注册成功！");
            case -1:
                return ResultFactory.buildFailResult("注册失败！");
        }
        return ResultFactory.buildFailResult("未知错误");
    }

    @CrossOrigin
    @PutMapping("/api/business/edit")
    public Result edit(@RequestBody Business business) {

        int status = businessService.register(business);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("该id已存在！");
            case 1:
                return ResultFactory.buildSuccessResult(business,"注册成功！");
            case -1:
                return ResultFactory.buildFailResult("注册失败！");
        }
        return ResultFactory.buildFailResult("未知错误");
    }

    @CrossOrigin
    @PutMapping("/api/business/resetPassword")
    public Result resetPassword(@RequestBody Business business) {

        Business businessOld = businessService.resetPassword(business);

        if(businessOld != null)
            return ResultFactory.buildSuccessResult(businessOld,"重置密码成功！");
        else
            return ResultFactory.buildFailResult("重置密码失败，请重试！");
    }
}
