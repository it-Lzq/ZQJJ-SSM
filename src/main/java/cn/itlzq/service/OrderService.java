package cn.itlzq.service;


import cn.itlzq.db.OrderDao;
import cn.itlzq.model.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/21 13:22
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Service
public class OrderService {
    @Resource
    private OrderDao dao;

    public  void insertOrder(Order order){
        dao.insertOrder(order);
    }

    public  void updateOrder(Order order){dao.updateOrder(order);}

    /**
     * 查询用户订单
     * @param userId 用户id
     * @return 订单列表
     */
    public List<Order> getByUserId(int userId){return dao.getByUserId(userId); }


    public  Map<Integer,List<Order>> getOrderMap(int userId){
        List<Order> orderList = getByUserId(userId);
        Map<Integer,List<Order>> orderMap = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            List<Order> list = new ArrayList<>();
            for (Order order : orderList) {
                if(order.getStatus() == i){
                    list.add(order);
                }
            }
            orderMap.put(i,list);
        }
        ;
        return orderMap;
    }


    public  Order getById(int id){
        return dao.getById(id);
    }


    public List<Order> getByStatus(int status){
       return dao.getByStatus(status);
    }

    public List<Order> getAll(){
        return dao.getAll();
    }

    public void del(int id){
        dao.del(id);
    }
}
