package com.bookshop.bookshop.service;

import com.bookshop.bookshop.dao.BookDAO;
import com.bookshop.bookshop.dao.ShoppingCartDAO;
import com.bookshop.bookshop.dao.StoreDAO;
import com.bookshop.bookshop.entity.Order;
import com.bookshop.bookshop.entity.ShoppingCart;
import com.bookshop.bookshop.entity.ShoppingCartInfo;
import com.bookshop.bookshop.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ShopCartService {

    @Autowired
    ShoppingCartDAO shoppingCartDAO;

    @Autowired
    StoreDAO storeDAO;

    @Autowired
    BookDAO bookDAO;

    //加入购物车
    public ShoppingCart addIntoShoppingCart(ShoppingCart shoppingCart){
        return shoppingCartDAO.save(shoppingCart);
    }

    //移出购物车
    public void deleteFromShoppingCart(ShoppingCart shoppingCart){
        shoppingCartDAO.deleteById(shoppingCart.getId());
    }

    //购物车——>支付
    public Order payShoppingCart(ShoppingCart shoppingCart){
        //从购物车中移除
        shoppingCartDAO.deleteById(shoppingCart.getId());
        //加入订单列表
        Order order = new Order();
        order.setBookId(shoppingCart.getBookId());
        order.setUserId(shoppingCart.getUserId());
        order.setNumber(shoppingCart.getNumber());
        order.setTotalPrice(shoppingCart.getTotalPrice());
        order.setState("已支付");

        return order;
    }

    //购物车——>不支付
    public Order noPayShoppingCart(ShoppingCart shoppingCart){
        //从购物车中移除
        shoppingCartDAO.deleteById(shoppingCart.getId());
        //加入订单列表
        Order order = new Order();
        order.setBookId(shoppingCart.getBookId());
        order.setUserId(shoppingCart.getUserId());
        order.setNumber(shoppingCart.getNumber());
        order.setTotalPrice(shoppingCart.getTotalPrice());
        order.setState("未支付");

        return order;
    }

    //显示用户的购物车
    public List<ShoppingCart> showShoppingCart(String user_id){
        return shoppingCartDAO.findByUserId(user_id);
    }

    //返回客户端的信息显示
    public List<ShoppingCartInfo> showShoppingCartInfo(String user_id){

        List<ShoppingCart> carts = showShoppingCart(user_id);
        ArrayList<ShoppingCartInfo> shoppingCarts = new ArrayList<>();

        for (ShoppingCart cart : carts){

            ShoppingCartInfo cartInfo = new ShoppingCartInfo();
            cartInfo.setShoppingCart(cart);
            cartInfo.setStoreName((storeDAO.findById(cart.getStoreId())).getName());
            cartInfo.setPicPath(bookDAO.findById(cart.getBookId()).getPicPath());
            shoppingCarts.add(cartInfo);
        }
        return shoppingCarts;
    }
}
