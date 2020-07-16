package com.bookshop.bookshop.service;

import com.bookshop.bookshop.dao.BusinessDAO;
import com.bookshop.bookshop.dao.StoreDAO;
import com.bookshop.bookshop.entity.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BusinessService {

    @Autowired
    BusinessDAO businessDAO;

    @Autowired
    StoreDAO storeDAO;

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
    public Business login(String user_id,String password){
        return businessDAO.findByPhoneAndPassword(user_id,password);
    }

    //商家注册
    public int register(Business business){

        Business businessIf = businessDAO.findByPhone(business.getPhone());


        if(businessIf == null){
            business.setState("已申请");
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

    //注销商家,同时注销商店
    public void deleteBusiness(String phone){

        Business business = businessDAO.findByPhone(phone);
        int store_id = business.getStoreId();

        businessDAO.deleteByPhone(phone);

        if(business.getState().equals("已通过")){
            //表明已经存在店铺
            //删除店铺
            storeDAO.deleteById(store_id);
        }
    }

    //注销商店
    public void deleteBusiness(int store_id){

        Business business = businessDAO.findByStoreId(store_id);
        business.setState("已删除");
        businessDAO.save(business);
    }

    //商家重新申请店铺
    public Business reApply(Business business){

        Business businessOld = businessDAO.findByPhone(business.getPhone());
        businessOld.setStoreName(business.getStoreName());
        businessOld.setIntroduction(business.getIntroduction());
        businessOld.setState("已申请");

        return businessDAO.save(businessOld);
    }

    //查询商家具体信息
    public Business checkInfo(String phone){
        return businessDAO.findByPhone(phone);
    }

    //查询商家是否已有店铺 以及店铺状态
    public String checkIfExist(String phone){
        Business business = businessDAO.findByPhone(phone);
        if((business.getState()).equals("已通过"))
            return "0";
        else{
            if((business.getState()).equals("已申请")){
                return "1";
            }
            else{//被拒绝或是店铺删除
                return "2";
            }
        }
    }

    //保存更改店铺后商家信息
    public Business saveUpdateStore(Business business){
        Business businessOld = businessDAO.findByPhone(business.getPhone());
        businessOld.setStoreName(business.getStoreName());
        businessOld.setIntroduction(business.getIntroduction());

        return businessDAO.save(businessOld);
    }

}
