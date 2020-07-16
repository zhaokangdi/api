package com.bookshop.bookshop.service;

import com.bookshop.bookshop.dao.ApplicationDAO;
import com.bookshop.bookshop.dao.BusinessDAO;
import com.bookshop.bookshop.dao.UserDAO;
import com.bookshop.bookshop.entity.AssistantApplication;
import com.bookshop.bookshop.entity.Business;
import com.bookshop.bookshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class ApplicationService {

    @Autowired
    ApplicationDAO applicationDao;

    @Autowired
    UserService userService;

    @Autowired
    BusinessDAO businessDAO;

    @Autowired
    UserDAO userDAO;

    //添加助理申请
    public AssistantApplication addApplication(AssistantApplication application){

        String userId = application.getUserId();
        User user = userService.getUserById(userId);

        application.setUsername(user.getUsername());
        application.setUseraddress(user.getAddress());
        application.setState("已申请");

        return (AssistantApplication) applicationDao.save(application);
    }

    //通过助理申请
    public AssistantApplication addAssistant(AssistantApplication application){

        AssistantApplication applicationNew = applicationDao.findById(application.getId());

        applicationNew.setState("已通过");
        userService.addAssistant(applicationNew.getUserId());

        return (AssistantApplication) applicationDao.save(applicationNew);
    }

    //删除拒绝助理申请
    public void refuseAssistant(AssistantApplication application){
        applicationDao.deleteById(application.getId());
    }

    //删除拒绝助理申请
    public void deleteAssistant(AssistantApplication application){
        AssistantApplication assistantApplicationOld = applicationDao.findById(application.getId());
        User user = userDAO.findById(assistantApplicationOld.getUserId());
        user.setRole("普通用户");
        userDAO.save(user);
        applicationDao.deleteById(application.getId());
    }

    //查找所有店铺的申请
    public List<AssistantApplication> showAllStoreApplication(){
        return applicationDao.findAll();
    }

    //查找某店铺全部申请
    public List<AssistantApplication> showAllApplication(int storeId){
        return applicationDao.findByStoreId(storeId);
    }

    //查找某店铺未通过的请求
    public List<AssistantApplication> showAllAssistantN(Business business){

        String phone = business.getPhone();
        Business business1 = businessDAO.findByPhone(phone);

        int storeId = business1.getStoreId();
        return applicationDao.findByStoreIdAndState(storeId,"已申请");
    }

    public List<AssistantApplication> showAllAssistant(Business business){

        Business business1 = businessDAO.findByPhone(business.getPhone());
        return applicationDao.findByStoreIdAndState(business1.getStoreId(),"已通过");
    }

    //通过店铺id来查看全部的未通过
    public List<AssistantApplication> showAllApplicationStore(int storeId){
        return applicationDao.findByStoreIdAndState(storeId,"已申请");
    }

    public AssistantApplication findAssistantApplication(int id){
        return applicationDao.findById(id);
    }

    public AssistantApplication findAssistant(String user_id){
        return applicationDao.findByUserId(user_id);
    }
}
