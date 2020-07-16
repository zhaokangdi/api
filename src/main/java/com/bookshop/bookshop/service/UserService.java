package com.bookshop.bookshop.service;

import com.bookshop.bookshop.dao.BusinessDAO;
import com.bookshop.bookshop.dao.UserDAO;
import com.bookshop.bookshop.entity.Business;
import com.bookshop.bookshop.entity.LoginEntity;
import com.bookshop.bookshop.entity.User;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    BusinessDAO businessDAO;

    public User getUserById(String id){
        return userDAO.findById(id);
    }

    //判断当前手机号是否已经使用
    public boolean idSate(String id){

        boolean state = true;
        User user = userDAO.findById(id);

        if(user!=null){
            state = false;
        }

        return state;
    }

    //注册
    public int register(User user){

        String id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();
        String address = user.getAddress();
        String role = user.getRole();

        int tag = 0;

        //html转义
        user.setId(id);
        user.setUsername(username);
        //user.setPassword(password);
        user.setAddress(address);
        user.setRole("普通用户");

        if(!idSate(id))
            tag = 0;
        else{
            // 默认生成 16 位盐
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            // 设置 hash 算法迭代次数
            int times = 2;

            // 得到 hash 后的密码
            String encodedPassword = new SimpleHash("md5", password, salt, times).toString();

            //存储数据
            user.setPassword(encodedPassword);
            user.setSalt(salt);

            User userSaved = (User) userDAO.save(user);
            if(userSaved!=null)
                tag = 1;
            else
                tag = -1;
        }
        return tag;
    }

    //登录时根据id和password返回user实体
    public User login(String id, String password){

        User user = userDAO.findById(id);
        if(user == null){
            return null;
        }
        else{
            //数据库中的密码
            String passwordM = user.getPassword();
            //获取盐
            String salt = user.getSalt();

            int times = 2;
            // 得到 hash 后的密码
            String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
            if(passwordM.equals(encodedPassword)){
                return user;
            }
            else{
                return null;
            }
        }
    }

    //列出全部User
    public List<User> listAllUser(){
        return userDAO.findAll();
    }

    //显示User的信息
    public User listUser(String id){ return userDAO.findById(id); }

    //修改或添加信息
    public User editUser(User edit_user){

        User user_edited = userDAO.findById(edit_user.getId());
        user_edited.setUsername(edit_user.getUsername());
        user_edited.setAddress(edit_user.getAddress());

        User user = (User) userDAO.save(user_edited);
        return user;
    }

    //重置密码
    public User resetPassword(User userNew) {

        User userOld = userDAO.findById(userNew.getId());
        userOld.setPassword(userNew.getPassword());
        return (User) userDAO.save(userOld);
    }


    //添加助理
    public User addAssistant(String id) {

        User userOld = userDAO.findById(id);
        userOld.setRole("助理");
        return (User) userDAO.save(userOld);
    }

    //注销用户
    public void deleteUser(String id) {
        userDAO.deleteById(id);
    }

    public String indentification(String phone){

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

    public LoginEntity userSettingInfo(String id){

        LoginEntity entity = new LoginEntity();
        entity.setId(id);

        String i = this.indentification(id);
        if(i.equals("商家")){
            entity.setRole("商家");
            Business business = businessDAO.findByPhone(id);
            entity.setName(business.getName());
            entity.setAddress(business.getAddress());
        }

        else{
            if(i.equals("普通用户")){
                entity.setRole("普通用户");
            }
            else{
                entity.setRole("助理");
            }
            User user = userDAO.findById(id);
            entity.setName(user.getUsername());
            entity.setAddress(user.getAddress());
        }
        return entity;
    }
}

