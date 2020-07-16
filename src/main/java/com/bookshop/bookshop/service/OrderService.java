package com.bookshop.bookshop.service;

import com.bookshop.bookshop.dao.*;
import com.bookshop.bookshop.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class OrderService {

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    BookDAO bookDAO;

    @Autowired
    StoreDAO storeDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    BusinessDAO businessDAO;

    public Order checkOrder(int id){
        return orderDAO.findById(id);
    }

    //用户所有的订单
    public List<OrderInfo> showAllOrderUser(String userId){
        List<Order> orders = orderDAO.findByUserId(userId);
        ArrayList<OrderInfo> orderInfoList = new ArrayList<>();
        for(Order order : orders){
            int order_id = order.getId();
            orderInfoList.add(this.checkOrderInfo(order_id));
        }
        return orderInfoList;
    }

    //用户未支付的订单
    public List<OrderInfo> showUserWeizhifu(String userId){

        System.out.println(userId);
        List<Order> orders = orderDAO.findByUserIdAndState(userId,"未支付");
        System.out.println(orders.size());
        ArrayList<OrderInfo> orderInfoList = new ArrayList<>();
        for(Order order : orders){
            int order_id = order.getId();
            orderInfoList.add(this.checkOrderInfo(order_id));
        }
        return orderInfoList;
    }

    //用户已支付的订单
    public List<OrderInfo> showUserYizhifu(String userId){
        List<Order> orders = orderDAO.findByUserIdAndState(userId,"已支付");
        ArrayList<OrderInfo> orderInfoList = new ArrayList<>();
        for(Order order : orders){
            int order_id = order.getId();
            orderInfoList.add(this.checkOrderInfo(order_id));
        }
        return orderInfoList;
    }

    //用户已发货的订单
    public List<OrderInfo> showUserYifahuo(String userId){
        List<Order> orders = orderDAO.findByUserIdAndState(userId,"已发货");
        ArrayList<OrderInfo> orderInfoList = new ArrayList<>();
        for(Order order : orders){
            int order_id = order.getId();
            orderInfoList.add(this.checkOrderInfo(order_id));
        }
        return orderInfoList;
    }

    //用户已签收的订单
    public List<OrderInfo> showUserYiqianshou(String userId){

        List<Order> orders = orderDAO.findByUserIdAndState(userId,"已签收");

        ArrayList<OrderInfo> orderInfoList = new ArrayList<>();
        for(Order order : orders){
            int order_id = order.getId();
            orderInfoList.add(this.checkOrderInfo(order_id));
        }
        return orderInfoList;
    }

    //用户下单
    public Order placeOrder(Order order){

        Order orderOlder = orderDAO.findById(order.getId());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        orderOlder.setState("已支付");

        return orderDAO.save(orderOlder);
    }

    //用户下单
    public Order placeOrderNow(Order order){

        return orderDAO.save(order);
    }

    //用户取消下单
    public Order noPlaceOrder(Order order){

        return orderDAO.save(order);
    }

    //变化订单状态:发货
    public Order changeOrderStateB(Order order){

       Order orderOld = orderDAO.findById(order.getId());
       orderOld.setState("已发货");

        return orderDAO.save(orderOld);
    }

    //变化订单状态:签收
    public Order changeOrderStateR(Order order){

        Order orderOld = orderDAO.findById(order.getId());
        orderOld.setState("已签收");

        return orderDAO.save(orderOld);
    }

    //变化订单状态:取消
    public Order changeOrderStateD(Order order){

        Order orderOld = orderDAO.findById(order.getId());
        orderOld.setState("已取消");

        return orderDAO.save(orderOld);
    }

    //查找订单的详情
    public OrderInfo checkOrderInfo(int orderId){

        Order order = orderDAO.findById(orderId);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrder(order);

        int  book_id = order.getBookId();
        Book book = bookDAO.findById(book_id);
        orderInfo.setBook(book);

        int store_id = book.getStoreId();
        Store store = storeDAO.findById(store_id);
        System.out.println(store.getAddress());
        orderInfo.setStore(store);

        String userId = order.getUserId();
        User user = userDAO.findById(userId);

        if(user!=null){
            orderInfo.setUser(user);
        }
        else{
            System.out.println("用户是空的");
            Business business = businessDAO.findByPhone(userId);
            User userNew = new User();
            userNew.setId(userId);
            userNew.setUsername(business.getName());
            userNew.setAddress(business.getAddress());
            userNew.setRole("商家");
            orderInfo.setUser(userNew);
        }
        return orderInfo;
    }

    //店铺中某本书所有的订单
    public List<OrderInfo> showAllOrderBook(int bookId){
        List<Order> orders1 = orderDAO.findByBookIdAndState(bookId,"已支付");
        ArrayList<OrderInfo> orderInfoList = new ArrayList<>();
        for(Order order : orders1){
            int order_id = order.getId();
            orderInfoList.add(checkOrderInfo(order_id));
        }

        List<Order> orders2 = orderDAO.findByBookIdAndState(bookId,"已发货");
        for(Order order : orders2){
            int order_id = order.getId();
            orderInfoList.add(checkOrderInfo(order_id));
        }
        return orderInfoList;
    }

    //某店铺的全部订单
    public List<OrderInfo> showAllOrderStore(int store_id){
        List<Book> books = bookDAO.findByStoreId(store_id);
        ArrayList<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
        for(Book book: books){
            int bookId = book.getId();
            if(showAllOrderBook(bookId).size()>0)
                orderInfoList.addAll(showAllOrderBook(bookId));
        }
        return orderInfoList;
    }

    //店铺中用户未支付的全部订单
    public List<OrderInfo> showWeiZhifu(String phone){

        int store_id = (businessDAO.findByPhone(phone)).getStoreId();
        return showAllOrderStore(store_id);
    }

    //店铺中用户支付的全部订单
    public List<OrderInfo> showYiZhiFu(String userId){

        List<Order> orders = orderDAO.findByUserIdAndState(userId,"已支付");
        ArrayList<OrderInfo> orderInfoList = new ArrayList<>();
        for(Order order : orders){
            int order_id = order.getId();
            orderInfoList.add(this.checkOrderInfo(order_id));
        }
        return orderInfoList;
    }

    //店铺中已发货的全部订单
    public List<OrderInfo> showYiFaHuo(String userId){

        List<Order> orders = orderDAO.findByUserIdAndState(userId,"已发货");
        ArrayList<OrderInfo> orderInfoList = new ArrayList<>();
        for(Order order : orders){
            int order_id = order.getId();
            orderInfoList.add(this.checkOrderInfo(order_id));
        }
        return orderInfoList;
    }

    //店铺中已签收的全部订单
    public List<OrderInfo> showYiQianShou(String userId){

        List<Order> orders = orderDAO.findByUserIdAndState(userId,"已签收");
        ArrayList<OrderInfo> orderInfoList = new ArrayList<>();
        for(Order order : orders){
            int order_id = order.getId();
            orderInfoList.add(this.checkOrderInfo(order_id));
        }
        return orderInfoList;
    }
}
