package com.bookshop.bookshop.Shiro;

import com.bookshop.bookshop.entity.User;
import com.bookshop.bookshop.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.Set;


public class Realm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    // 重写获取授权信息方法


    // 获取认证信息，即根据 token 中的用户名从数据库中获取密码、盐等并返回
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String user_id = token.getPrincipal().toString();
        User user = userService.getUserById(user_id);
        if (ObjectUtils.isEmpty(user)) {
            throw new UnknownAccountException();
        }
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user_id, passwordInDB, ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
