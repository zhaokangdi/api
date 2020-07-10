package com.bookshop.bookshop.service;

import com.bookshop.bookshop.dao.BusinessDAO;
import com.bookshop.bookshop.entity.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    @Autowired
    BusinessDAO businessDAO;

    //查询全部状态的所有商家
    public List<Business> showAllBusiness(){
        return businessDAO.findAll();
    }

    //查询已通过申请的所有商家
    public List<Business> showAllBusinessY(){
        return businessDAO.findByState("已通过");
    }

    //查询被拒绝的所有商家
    public List<Business> showAllBusinessN(){
        return businessDAO.findByState("已拒绝");
    }

    //查询等待通过申请的所有商家
    public List<Business> showAllBusinessW(){
        return businessDAO.findByState("已申请");
    }

    //商家登录
    public Business login(Business business){
        return businessDAO.findByPhoneAndPassword(business.getPhone(),business.getPassword());
    }

    //商家注册
    public int register(Business business){

        Business businessIf = businessDAO.findByPhone(business.getPhone());
        if(businessIf == null){
            Business businessNew = businessDAO.save(business);
            if(businessNew!=null)
                return 1;
            else
                return -1;
        }
        else{
            return 0;
        }
    }


    //提交申请
    public int applyStore(Business business){

        Business businessIf = businessDAO.findByPhone(business.getPhone());
        if(businessIf != null){
            //此时store_id为空
            business.setState("已申请");
            Business businessIn = businessDAO.save(business);
            if(businessIn!=null)
                return 1;
            else
                return -1;    //申请失败
        }
        else{
            return 0;  //该商家已经存在
        }
    }

    //编辑更新信息
    public boolean editBusiness(Business business){

        Business businessOld = businessDAO.findByPhone(business.getPhone());
        businessOld.setName(business.getName());
        businessOld.setAddress(business.getAddress());

        //修改phone
        if(businessDAO.findByPhone(business.getPhone())==null){//可以更改
            businessOld.setPhone(business.getPhone());
            businessDAO.save(businessOld);
            return true;
        }
        else{
            return false;
        }
    }

    //重置密码
    public Business resetPassword(Business business){

        Business businessOld = businessDAO.findByPhone(business.getPhone());

        businessOld.setPassword(business.getPassword());
        return businessDAO.save(businessOld);
    }

}
