package com.bookshop.bookshop.controller;

import com.bookshop.bookshop.entity.AssistantApplication;
import com.bookshop.bookshop.entity.Business;
import com.bookshop.bookshop.entity.Store;
import com.bookshop.bookshop.result.Result;
import com.bookshop.bookshop.result.ResultFactory;
import com.bookshop.bookshop.service.ApplicationService;
import com.bookshop.bookshop.service.BusinessService;
import com.bookshop.bookshop.service.StoreService;
import com.bookshop.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StoreController {

    @Autowired
    StoreService storeService;

    @Autowired
    BusinessService businessService;

    @Autowired
    UserService userService;

    @Autowired
    ApplicationService applicationService;

    @CrossOrigin
    @PostMapping("/api/store/all")
    @ResponseBody
    public Result showAllStore() {
        return ResultFactory.buildSuccessResult(storeService.showAllStore(),"");
    }

    @CrossOrigin
    @PostMapping("/api/store/agree")
    @ResponseBody
    public Result agreeApplication(@RequestBody Business business){

        String phone = business.getPhone();
        storeService.addStore(phone);
        return ResultFactory.buildSuccessResult(businessService.showAllBusinessW(),"成功添加店铺！");
    }

    @CrossOrigin
    @PostMapping("/api/store/reject")
    @ResponseBody
    public Result rejectApplication(@RequestBody Business business){

        String phone = business.getPhone();
        storeService.rejectBusiness(phone);
        return ResultFactory.buildSuccessResult(businessService.showAllBusinessW(),"已拒绝商家请求！");
    }

    @CrossOrigin
    @PostMapping("/api/store/delete")
    @ResponseBody
    public Result deleteStore(@RequestBody Store store){

        int id = store.getId();
        storeService.deleteStore(id);
        return ResultFactory.buildSuccessResult(businessService.showAllBusiness(),"注销店铺成功！");
    }

    @CrossOrigin
    @PostMapping("/api/store/addapply")
    @ResponseBody
    public Result addStore(@RequestBody Store store){

        storeService.addBusinessApplyStore(store);
        return ResultFactory.buildSuccessResult(null,"申请店铺成功！等待审核");
    }

    @CrossOrigin
    @PostMapping("/api/store/exist")
    @ResponseBody
    //判断店铺是否存在
    public Result checkStoreExist(@RequestBody Business business){

        String phone = business.getPhone();
        return ResultFactory.buildSuccessResult(null,businessService.checkIfExist(phone));
    }

    @CrossOrigin
    @PostMapping("/api/store/info")
    @ResponseBody
    public Result checkStoreInfo(@RequestBody Business business){

        String phone = business.getPhone();
        String identification = userService.indentification(phone);

        System.out.println(identification);

        if(identification.equals("普通用户")){
            return ResultFactory.buildSuccessResult(null,identification);
        }
        else{
            Store store = storeService.checkStoreIndentification(phone);
            return ResultFactory.buildSuccessResult(store,identification);
        }
    }

    @CrossOrigin
    @PostMapping("/api/store/update")
    @ResponseBody
    public Result updateStore(@RequestBody Store store){

        if(!storeService.editStore(store)){
            return ResultFactory.buildFailResult("联系方式已存在，请更换一个！");
        }
        else{
            //更换商家信息
            List<Business> businessList = businessService.showAllBusiness();
            for(Business business : businessList){
                if(business.getStoreId()==store.getId()){
                    business.setStoreName(store.getName());
                    business.setIntroduction(store.getIntroduction());
                    return ResultFactory.buildSuccessResult(businessService.saveUpdateStore(business),"修改成功！");
                }
            }
        }
        return ResultFactory.buildFailResult("错误！");
    }

    //显示当前全部通过的助理
    @CrossOrigin
    @PostMapping("/api/store/all_assistant")
    @ResponseBody
    public Result allStoreAssistant(@RequestBody Business business){

        List<AssistantApplication> assistants = applicationService.showAllAssistant(business);
        return ResultFactory.buildSuccessResult(assistants,null);
    }

    //显示当前还未审核/未通过的助理申请
    @CrossOrigin
    @PostMapping("/api/store/all_assistant_application")
    @ResponseBody
    public Result allStoreAssistantApplication(@RequestBody Business business){

        String identification = userService.indentification(business.getPhone());
        if(identification.equals("商家")){
            List<AssistantApplication> applications = applicationService.showAllAssistantN(business);
            return ResultFactory.buildSuccessResult(applications,identification);
        }
        else{
            return ResultFactory.buildSuccessResult(null,identification);
        }
    }

    //同意用户的助理请求
    @CrossOrigin
    @PostMapping("/api/store/assistant_agree")
    @ResponseBody
    public Result agreeApplication(@RequestBody AssistantApplication application){

        applicationService.addAssistant(application);

        int store_id = (applicationService.findAssistantApplication(application.getId())).getStoreId();
        return ResultFactory.buildSuccessResult(applicationService.showAllApplicationStore(store_id),"成功添加用户为助理！");
    }

    //删除/拒绝用户的助理请求
    @CrossOrigin
    @PostMapping("/api/store/assistant_refuse")
    @ResponseBody
    public Result refuseAssistant(@RequestBody AssistantApplication application){

        System.out.println(application.getId());
        applicationService.refuseAssistant(application);
        return ResultFactory.buildSuccessResult(applicationService.showAllApplicationStore(application.getStoreId()),null);
    }

    //删除/拒绝用户的助理请求
    @CrossOrigin
    @PostMapping("/api/store/assistant_delete")
    @ResponseBody
    public Result deleteAssistant(@RequestBody AssistantApplication application){

        applicationService.deleteAssistant(application);
        return ResultFactory.buildSuccessResult(applicationService.showAllApplicationStore(application.getStoreId()),null);
    }
}
