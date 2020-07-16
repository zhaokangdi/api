package com.bookshop.bookshop.controller;

import com.bookshop.bookshop.entity.*;
import com.bookshop.bookshop.result.Result;
import com.bookshop.bookshop.result.ResultFactory;
import com.bookshop.bookshop.service.BusinessService;
import com.bookshop.bookshop.service.OrderService;
import com.bookshop.bookshop.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    BusinessService businessService;

    @Autowired
    RatingService ratingService;

    @CrossOrigin
    @PostMapping(value = "api/order/all")
    @ResponseBody
    //获取全部已支付和已发货的订单
    public Result checkIfStore(@RequestBody Business business) {

        String phone = business.getPhone();
        Business businessOld = businessService.checkInfo(phone);
        int store_id = businessOld.getStoreId();

        List<OrderInfo> orderInfoList = orderService.showAllOrderStore(store_id);
        System.out.println(orderInfoList.size());
        return ResultFactory.buildSuccessResult(orderInfoList,null);
    }

    @CrossOrigin
    @PostMapping(value = "api/order/info")
    @ResponseBody
    //获取全部已支付和已发货的订单
    public Result checkOrderInfo(@RequestBody Order order) {

        int id = order.getId();
        OrderInfo orderInfo = orderService.checkOrderInfo(id);
        return ResultFactory.buildSuccessResult(orderInfo,null);
    }

    @CrossOrigin
    @PostMapping(value = "api/order/cancel")
    @ResponseBody
    //取消订单
    public Result cancelOrder(@RequestBody Order order) {

        orderService.changeOrderStateD(order);
        String userId = orderService.checkOrder(order.getId()).getUserId();
        return ResultFactory.buildSuccessResult(orderService.showUserWeizhifu(userId),"成功取消！");
    }

    @CrossOrigin
    @PostMapping(value = "api/order/confirm")
    @ResponseBody
    //签收
    public Result confirmOrder(@RequestBody Order order) {

        orderService.changeOrderStateR(order);
        String userId = orderService.checkOrder(order.getId()).getUserId();
        return ResultFactory.buildSuccessResult(orderService.showUserWeizhifu(userId),"成功签收！");
    }

    @CrossOrigin
    @PostMapping(value = "api/order/score")
    @ResponseBody
    //评分
    public Result scoreOrder(@RequestBody Rating rating) {
        System.out.println("cddscdscdscds");
       ratingService.scoreBook(rating);
       return ResultFactory.buildSuccessResult(orderService.showUserWeizhifu(rating.getUserId()),"");
    }

    @CrossOrigin
    @PostMapping(value = "api/order/send")
    @ResponseBody
    //商家发货
    public Result sendOrder(@RequestBody Order order) {

        int id = order.getId();
        orderService.changeOrderStateB(order);
        OrderInfo orderInfo = orderService.checkOrderInfo(id);
        return ResultFactory.buildSuccessResult(orderInfo,"已发货");
    }

    @CrossOrigin
    @PostMapping(value = "api/order/pay")
    @ResponseBody
    public Result payOrder(@RequestBody Order order) {

       orderService.placeOrderNow(order);
       return ResultFactory.buildSuccessResult(null,null);
    }

    @CrossOrigin
    @PostMapping(value = "api/order/pay1")
    @ResponseBody
    public Result payOrder1(@RequestBody Order order) {

        orderService.placeOrder(order);
        String userId = orderService.checkOrder(order.getId()).getUserId();
        return ResultFactory.buildSuccessResult(orderService.showUserWeizhifu(userId),"支付成功!");
    }

    @CrossOrigin
    @PostMapping(value = "api/order/nopay")
    @ResponseBody
    public Result noPayOrder(@RequestBody Order order) {

        orderService.noPlaceOrder(order);
        return ResultFactory.buildSuccessResult(null,null);
    }

    @CrossOrigin
    @PostMapping(value = "api/order/yizhifu")
    @ResponseBody
    public Result showZhiFuStore(@RequestBody User user) {

        return ResultFactory.buildSuccessResult(orderService.showYiZhiFu(user.getId()),null);
    }

    @CrossOrigin
    @PostMapping(value = "api/order/yifahuo")
    @ResponseBody
    public Result showYiFaHuoStore(@RequestBody User user) {

        return ResultFactory.buildSuccessResult(orderService.showYiFaHuo(user.getId()),null);
    }


    @CrossOrigin
    @PostMapping(value = "api/order/yishouhuo")
    @ResponseBody
    public Result sendOrder(@RequestBody User user) {

        return ResultFactory.buildSuccessResult(orderService.showYiQianShou(user.getId()),null);
    }

    //某店铺未支付的订单
    @CrossOrigin
    @PostMapping(value = "api/order/weizhifu")
    @ResponseBody
    public Result showWeiZhiFuStore(@RequestBody Business business) {

        return ResultFactory.buildSuccessResult(orderService.showWeiZhifu(business.getPhone()),null);
    }

    //用户订单
    @CrossOrigin
    @PostMapping(value = "api/order/userweizhifu")
    @ResponseBody
    public Result showWeiZhiFuUser(@RequestBody Order order) {
        List<OrderInfo> lists = orderService.showUserWeizhifu(order.getUserId());
        return ResultFactory.buildSuccessResult(lists,null);
    }

    @CrossOrigin
    @PostMapping(value = "api/order/useryizhifu")
    @ResponseBody
    public Result showYiZhiFuUser(@RequestBody Order order) {
        return ResultFactory.buildSuccessResult(orderService.showUserYizhifu(order.getUserId()),null);
    }

    @CrossOrigin
    @PostMapping(value = "api/order/useryifahuo")
    @ResponseBody
    public Result showYiFaHuoUser(@RequestBody Order order) {

        return ResultFactory.buildSuccessResult(orderService.showUserYifahuo(order.getUserId()),null);
    }

    @CrossOrigin
    @PostMapping(value = "api/order/useryiqianshou")
    @ResponseBody
    public Result showYiQianShouUser(@RequestBody Order order) {

        return ResultFactory.buildSuccessResult(orderService.showUserYiqianshou(order.getUserId()),null);
    }


}
