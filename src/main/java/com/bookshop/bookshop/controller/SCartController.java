package com.bookshop.bookshop.controller;

import com.bookshop.bookshop.entity.Order;
import com.bookshop.bookshop.entity.ShoppingCart;
import com.bookshop.bookshop.entity.User;
import com.bookshop.bookshop.result.Result;
import com.bookshop.bookshop.result.ResultFactory;
import com.bookshop.bookshop.service.OrderService;
import com.bookshop.bookshop.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SCartController {

    @Autowired
    ShopCartService shopCartService;

    @Autowired
    OrderService orderService;

    @CrossOrigin
    @PostMapping(value = "/api/cart/all")
    @ResponseBody
    //显示用户/商家全部购物车
    public Result findAllCartsUser(@RequestBody User user){
        return ResultFactory.buildSuccessResult(shopCartService.showShoppingCartInfo(user.getId()),null);
    }

    @CrossOrigin
    @PostMapping(value = "/api/cart/add")
    @ResponseBody
    //添加到购物车
    public Result addCart(@RequestBody ShoppingCart shoppingCart){

        shopCartService.addIntoShoppingCart(shoppingCart);
        return ResultFactory.buildSuccessResult(shopCartService.showShoppingCartInfo(shoppingCart.getUserId()),"成功加入购物车！");
    }

    @CrossOrigin
    @PostMapping(value = "/api/cart/delete")
    @ResponseBody
    //从购物车中移除
    public Result deleteCart(@RequestBody ShoppingCart shoppingCart){

        shopCartService.deleteFromShoppingCart(shoppingCart);
        return ResultFactory.buildSuccessResult(shopCartService.showShoppingCartInfo(shoppingCart.getUserId()),"成功移除购物车！");
    }

    @CrossOrigin
    @PostMapping(value = "/api/cart/pay")
    @ResponseBody
    //支付
    public Result payCart(@RequestBody ShoppingCart shoppingCart){

        Order order = shopCartService.payShoppingCart(shoppingCart);
        orderService.placeOrderNow(order);
        return ResultFactory.buildSuccessResult(shopCartService.showShoppingCartInfo(shoppingCart.getUserId()),"成功支付");
    }

    @CrossOrigin
    @PostMapping(value = "/api/cart/nopay")
    @ResponseBody
    //取消支付
    public Result noPayCart(@RequestBody ShoppingCart shoppingCart){

        System.out.println("aj");
        Order order = shopCartService.noPayShoppingCart(shoppingCart);
        orderService.noPlaceOrder(order);
        return ResultFactory.buildSuccessResult(shopCartService.showShoppingCartInfo(shoppingCart.getUserId()),"您尚未支付");
    }
}
