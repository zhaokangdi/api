package com.bookshop.bookshop.service;

import com.bookshop.bookshop.dao.*;
import com.bookshop.bookshop.entity.Book;
import com.bookshop.bookshop.entity.Business;
import com.bookshop.bookshop.entity.Store;
import com.bookshop.bookshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StoreService {

    @Autowired
    BusinessDAO businessDAO;

    @Autowired
    BookDAO bookDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    StoreDAO storeDAO;

    @Autowired
    ApplicationDAO applicationDAO;

    //查看全部商店
    public List<Store> showAllStore(){
        return storeDAO.findAll();
    }

    //模糊查询店铺
    public List<Store> showLIKE(String condition){
        return storeDAO.findByPhoneContainsOrNameContainsOrAddressContainsOrIntroductionContains(condition,
                condition,condition,condition);
    }

    //管理员添加商店
    public Store addStore(String phone){

        Business businessOld = businessDAO.findByPhone(phone);
        businessOld.setState("已通过");

        Store store = new Store();
        store.setName(businessOld.getStoreName());
        store.setPhone(businessOld.getPhone());
        store.setAddress(businessOld.getAddress());
        store.setIntroduction(businessOld.getIntroduction());

        Store storeNew  = storeDAO.save(store);
        businessOld.setStoreId(storeNew.getId());
        businessDAO.save(businessOld);

        return storeNew;
    }

    //管理员拒绝商店
    public Business rejectBusiness(String phone){

        Business businessOld = businessDAO.findByPhone(phone);
        businessOld.setState("已拒绝");
        return businessDAO.save(businessOld);
    }

    //管理员删除商店
    public boolean deleteStore(int store_id){

        Store storeOld = storeDAO.findById(store_id);
        storeDAO.delete(storeOld);

        //更改商家信息
        Business businessOld = businessDAO.findByStoreId(store_id);
        businessOld.setStoreId(-1);
        businessOld.setStoreName("");
        businessOld.setState("店铺删除");
        businessDAO.save(businessOld);

        return true;
    }

    //编辑店铺信息
    public boolean editStore(Store store){

        Store storeOld = storeDAO.findByPhone(store.getPhone());
        storeOld.setName(store.getName());
        storeOld.setAddress(store.getAddress());
        storeOld.setIntroduction(store.getIntroduction());

        //修改phone
        if(storeDAO.findByPhone(store.getPhone()).getId()==store.getId()){  //可以修改
            storeOld.setPhone(store.getPhone());
            storeDAO.save(store);
            return true;
        }
        else{//不可以修改
            return false;
        }
    }

    //判断店铺是否存在
    public boolean checkIfExist(int store_id){
        Store store = storeDAO.findById(store_id);
        if(store==null){
            return false;
        }else {
            return true;
        }
    }

    //查看是否是商家/助理 —— 商家是否有店铺，助理是否通过申请
    public List<Book> checkAllBooksIfHave(String phone){

        Business business =  businessDAO.findByPhone(phone);
        if(business!=null){//是商家
            if((business.getState()).equals("已通过")){//有店铺
                int store_id = business.getStoreId();
                return bookDAO.findByStoreId(store_id);
            }
            else
                return null;
        }
        else{//用户
            User user = userDAO.findById(phone);
            if((user.getRole()).equals("助理")){ //已是助理
                int store_id = (applicationDAO.findByUserId(user.getId())).getStoreId();
                return bookDAO.findByStoreId(store_id);
            }
            else{
                return null;
            }
        }
    }

    public String checkIdentification(String phone){
        Business business =  businessDAO.findByPhone(phone);
        if(business!=null){//是商家
            return "商家";
        }
        else{//用户
            User user = userDAO.findById(phone);
            if((user.getRole()).equals("助理")){ //已是助理
                return "助理";
            }
            else{
                return "普通用户";
            }
        }
    }

    public Store checkStoreIndentification(String phone){

        Business business =  businessDAO.findByPhone(phone);
        if(business!=null){//是商家
            if((business.getState()).equals("已通过")){//有店铺
                int store_id = business.getStoreId();
                return storeDAO.findById(store_id);
            }
            else
                return null;
        }
        else{//用户
            User user = userDAO.findById(phone);
            if((user.getRole()).equals("助理")){ //已是助理
                int store_id = (applicationDAO.findByUserId(user.getId())).getStoreId();
                return storeDAO.findById(store_id);
            }
            else{
                return null;
            }
        }
    }

    public void addBusinessApplyStore(Store store){

        String phone = store.getPhone();
        String store_name = store.getName();
        String introduction = store.getIntroduction();

        Business business = businessDAO.findByPhone(phone);
        business.setState("已申请");
        business.setStoreName(store_name);
        business.setIntroduction(introduction);

        businessDAO.save(business);
    }

    public Store checkStoreInfo(Store store){
        return storeDAO.findById(store.getId());
    }
}
