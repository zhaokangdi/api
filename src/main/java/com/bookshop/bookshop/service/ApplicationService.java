package com.bookshop.bookshop.service;

import com.bookshop.bookshop.dao.ApplicationDAO;
import com.bookshop.bookshop.entity.AssistantApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    ApplicationDAO applicationDao;

    @Autowired
    UserService userService;

    //添加助理申请
    public AssistantApplication addApplication(AssistantApplication application){

        application.setState("已申请");
        return (AssistantApplication) applicationDao.save(application);
    }

    //通过助理申请
    public AssistantApplication addAssistant(AssistantApplication application){

        AssistantApplication applicationNew = applicationDao.findByStoreIdAndUserId(
                application.getStoreId(),application.getUserId());

        applicationNew.setState("已通过");
        userService.addAssistant(application.getUserId());

        return (AssistantApplication) applicationDao.save(applicationNew);
    }

    //拒绝助理申请
    public AssistantApplication refuseAssistant(AssistantApplication application){

        AssistantApplication applicationNew = applicationDao.findByStoreIdAndUserId(
                application.getStoreId(),application.getUserId());

        applicationNew.setState("已拒绝");

        return (AssistantApplication) applicationDao.save(applicationNew);
    }

    //查找所有店铺的申请
    public List<AssistantApplication> showAllStoreApplication(){
        return applicationDao.findAll();
    }

    //查找某店铺全部申请
    public List<AssistantApplication> showAllApplication(String storeId){
        return applicationDao.findByStoreId(storeId);
    }

    //查找某店铺未通过的请求
    public List<AssistantApplication> showAllAssistantN(String storeId){
        return applicationDao.findByStoreIdAndState(storeId,"已申请");
    }

    public List<AssistantApplication> showAllAssistant(String storeId){
        return applicationDao.findByStoreIdAndState(storeId,"已通过");
    }

    public List<AssistantApplication> showAllAssistantR(String storeId){
        return applicationDao.findByStoreIdAndState(storeId,"已拒绝");
    }

}
