package com.bookshop.bookshop.service;

import com.bookshop.bookshop.dao.ShoppingCartDAO;
import com.bookshop.bookshop.dao.StoreDAO;
import com.bookshop.bookshop.entity.ShoppingCart;
import com.bookshop.bookshop.entity.ShoppingCartInfo;
import com.bookshop.bookshop.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCartService {

    @Autowired
    ShoppingCartDAO shoppingCartDAO;

    @Autowired
    StoreDAO storeDAO;

    //加入购物车
    public ShoppingCart addIntoShoppingCart(ShoppingCart shoppingCart){
        return shoppingCartDAO.save(shoppingCart);
    }

    //移出购物车
    public void deleteFromShoppingCart(ShoppingCart shoppingCart){
        shoppingCartDAO.delete(shoppingCart);
    }

    //显示用户的购物车
    public List<ShoppingCart> showShoppingCart(String user_id){
        return shoppingCartDAO.findByUserId(user_id);
    }

    //返回客户端的信息显示
    public List<ShoppingCartInfo> showShoppingCartInfo(String user_id){

        List<ShoppingCart> carts = showShoppingCart(user_id);
        List<ShoppingCartInfo> shoppingCarts = null;

        for (ShoppingCart cart : carts){

            ShoppingCartInfo cartInfo = new ShoppingCartInfo();
            cartInfo.setShoppingCart(cart);
            cartInfo.setStore_name((storeDAO.findById(cart.getStoreId())).getName());

            shoppingCarts.add(cartInfo);
        }
        return shoppingCarts;
    }
}
