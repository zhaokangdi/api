package com.bookshop.bookshop.service;

import com.bookshop.bookshop.dao.OrderDAO;
import com.bookshop.bookshop.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderDAO orderDAO;

    //用户所有的订单
    public List<Order> showAllOrderUser(String userId){
        return orderDAO.findByUserId(userId);
    }

    //用户已下单
    public List<Order> showAllOrderState(String userId, String state){
        return orderDAO.findByUserIdAndAndState(userId,state);
    }

    //用户下单
    public Order placeOrder(Order order){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

        order.setPlaceDate(df.format(new Date())); //设置下单时间
        order.setState("已下单"); //设置下单时间

        return orderDAO.save(order);
    }

    //变化订单状态:发货
    public Order changeOrderStateB(Order order){

       Order orderOld = orderDAO.findById(order.getId());
        orderOld.setState("已发货");

        return orderDAO.save(order);
    }

    //变化订单状态:签收
    public Order changeOrderStateR(Order order){

        Order orderOld = orderDAO.findById(order.getId());
        orderOld.setState("已签收");

        return orderDAO.save(order);
    }

    //变化订单状态:取消
    public Order changeOrderStateD(Order order){

        Order orderOld = orderDAO.findById(order.getId());
        orderOld.setState("已取消");

        return orderDAO.save(order);
    }

}
