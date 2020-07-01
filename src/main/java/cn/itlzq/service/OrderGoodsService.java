package cn.itlzq.service;


import cn.itlzq.db.OrderGoodsDao;
import cn.itlzq.model.OrderGoods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/21 13:37
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Service
public class OrderGoodsService {

    @Resource
    private OrderGoodsDao dao ;


    public  void insert(OrderGoods orderGoods){
        dao.insert(orderGoods);
    }

    public List<OrderGoods> getByOrderId(int orderId){
        return dao.getByOrderId(orderId);
    }

    public void del(int orderId){
        dao.del(orderId);
    }


}
