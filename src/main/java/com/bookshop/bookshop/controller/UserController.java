package com.bookshop.bookshop.controller;

import com.bookshop.bookshop.entity.AssistantApplication;
import com.bookshop.bookshop.result.Result;
import com.bookshop.bookshop.result.ResultFactory;
import com.bookshop.bookshop.entity.User;
import com.bookshop.bookshop.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.bookshop.bookshop.service.UserService;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/api/user/all")
    public Result listUsers() {
        return ResultFactory.buildSuccessResult(userService.listAllUser(),"查找成功");
    }

    @GetMapping("/api/user/show")
    public Result showUser(@RequestBody String user_id){
        return ResultFactory.buildSuccessResult(userService.listUser(user_id),"");
    }

    @PostMapping("/api/user/edit")
    public Result editUser(@RequestBody User requestUser) {

        User user = userService.editUser(requestUser);
        return ResultFactory.buildSuccessResult(user,"修改用户信息成功！");
    }

    @PutMapping("/api/user/reset")
    public Result resetPassword(@RequestBody User requestUser) {

        User user = userService.resetPassword(requestUser);
        return ResultFactory.buildSuccessResult(user,"修改密码成功！");
    }

    @PutMapping("/api/user/apply_assistant")
    public Result AddAssistant(@RequestBody AssistantApplication requestApply) {

        AssistantApplication application = applicationService.addApplication(requestApply);
        return ResultFactory.buildSuccessResult(application,"申请助理成功！");
    }

}
