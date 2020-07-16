package com.bookshop.bookshop.controller;

import com.bookshop.bookshop.entity.Business;
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
    @PostMapping("/api/business/all")
    @ResponseBody
    public Result showAllBusiness() {
        return ResultFactory.buildSuccessResult(businessService.showAllBusiness(),"");
    }

    @CrossOrigin
    @PostMapping("/api/business/register")
    @ResponseBody
    public Result register(@RequestBody Business business) {

        int status = businessService.register(business);
        System.out.println(business.getIntroduction());

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
    @PostMapping("/api/business/edit")
    @ResponseBody
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
    @PostMapping("/api/business/resetPassword")
    @ResponseBody
    public Result resetPassword(@RequestBody Business business) {

        Business businessOld = businessService.resetPassword(business);

        if(businessOld != null)
            return ResultFactory.buildSuccessResult(businessOld,"重置密码成功！");
        else
            return ResultFactory.buildFailResult("重置密码失败，请重试！");
    }

    @CrossOrigin
    @PostMapping ("/api/business/delete")
    @ResponseBody
    public Result deleteBusiness(@RequestBody Business business) {

        String phone = business.getPhone();
        businessService.deleteBusiness(phone);
        return ResultFactory.buildSuccessResult(businessService.showAllBusiness(),"删除成功！");
    }

    @CrossOrigin
    @PostMapping("/api/business/applied")  //查询所有已经申请未通过的
    @ResponseBody
    public Result showAllStoreApplied() {
        return ResultFactory.buildSuccessResult(businessService.showAllBusinessW(),"");
    }

    @CrossOrigin
    @PostMapping("/api/business/info")  //查询所有已经申请未通过的
    @ResponseBody
    public Result showBusinessInfo(@RequestBody Business business) {

        String phone = business.getPhone();
        return ResultFactory.buildSuccessResult(businessService.checkInfo(phone),"详情");
    }

    @CrossOrigin
    @PostMapping("/api/business/state")  //查询所有已经申请未通过的
    @ResponseBody
    public Result checkIfStore(@RequestBody Business business) {

        String phone = business.getPhone();
        Business businessOld = businessService.checkInfo(phone);
        String state = businessOld.getState();

        if(state.equals("店铺删除")){
            return ResultFactory.buildSuccessResult(businessOld,"已有店铺");
        }
        else{
            return ResultFactory.buildSuccessResult(businessOld,"暂无店铺");
        }
    }
}
