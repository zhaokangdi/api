package com.bookshop.bookshop.controller;

import com.bookshop.bookshop.entity.AssistantApplication;
import com.bookshop.bookshop.entity.Business;
import com.bookshop.bookshop.result.Result;
import com.bookshop.bookshop.result.ResultFactory;
import com.bookshop.bookshop.entity.User;
import com.bookshop.bookshop.service.ApplicationService;
import com.bookshop.bookshop.service.BusinessService;
import com.bookshop.bookshop.service.StoreService;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.bookshop.bookshop.service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    BusinessService businessService;

    @Autowired
    StoreService storeService;

    @CrossOrigin
    @PostMapping("/api/user/all")
    @ResponseBody
    public Result listUsers() {
        return ResultFactory.buildSuccessResult(userService.listAllUser(),"查找成功");
    }

    @CrossOrigin
    @PostMapping("/api/user/show")
    @ResponseBody
    public Result showUser(@RequestBody String user_id){
        return ResultFactory.buildSuccessResult(userService.listUser(user_id),"");
    }

    @CrossOrigin
    @PostMapping("/api/user/edit")
    @ResponseBody
    public Result editUser(@RequestBody User requestUser) {

        User user = userService.editUser(requestUser);
        return ResultFactory.buildSuccessResult(user,"修改用户信息成功！");
    }

    @CrossOrigin
    @PostMapping("/api/user/reset")
    @ResponseBody
    public Result resetPassword(@RequestBody User requestUser) {

        User user = userService.resetPassword(requestUser);
        return ResultFactory.buildSuccessResult(user,"修改密码成功！");
    }

    @CrossOrigin
    @PostMapping("/api/user/apply_assistant")
    @ResponseBody
    public Result AddAssistant(@RequestBody AssistantApplication requestApply) {

        if(storeService.checkIfExist(requestApply.getStoreId())){
            AssistantApplication application = applicationService.addApplication(requestApply);
            return ResultFactory.buildSuccessResult(application,"成功发送申请！");
        }
        else{
            return ResultFactory.buildFailResult("该店铺不存在，请重新输入！");
        }
    }

    @CrossOrigin
    @PostMapping("/api/user/delete")
    @ResponseBody
    public Result deleteUser(@RequestBody User requestUser) {

        System.out.println(requestUser.getId());
        userService.deleteUser(requestUser.getId());
        System.out.println(userService.listAllUser().size());
        return ResultFactory.buildSuccessResult(userService.listAllUser(),"注销用户成功！");
    }

    @CrossOrigin
    @PostMapping("/api/user/setting")
    @ResponseBody
    public Result userSetting(@RequestBody User requestUser) {

        System.out.println(requestUser.getId());
        userService.deleteUser(requestUser.getId());
        System.out.println(userService.listAllUser().size());
        return ResultFactory.buildSuccessResult(userService.listAllUser(),"注销用户成功！");
    }

    @CrossOrigin
    @PostMapping("/api/assistant/all")
    @ResponseBody
    //查看当前店铺的全部助理申请
    public Result allAssistantApply(@RequestBody Business business) {

        String phone = business.getPhone();
        String i = userService.indentification(phone);

        if(i.equals("商家")){

            Business business1 = businessService.checkInfo(phone);
            int store_id = business1.getStoreId();

            List<AssistantApplication> assistantApplications = applicationService.showAllApplication(store_id);
            return ResultFactory.buildSuccessResult(assistantApplications,"商家");
        }

        return ResultFactory.buildSuccessResult(null,i);
    }

}
