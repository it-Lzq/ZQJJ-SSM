package cn.itlzq.db;


import cn.itlzq.model.OrderGoods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/21 13:29
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Mapper
@Component
public interface OrderGoodsDao {
    /**
            * 增加一条商品订单
     * @param orderGoods 订单
     */
    @Insert("INSERT INTO JJ_ORDER_GOODS(ORDERID,GOODSID,GOODSNUM,GOODSPRICE,GOODSNAME,GOODSIMG)" +
            " VALUE(#{orderId},#{goodsId},#{goodsNum},#{goodsPrice},#{goodsName},#{goodsImg})")
    @Options(useGeneratedKeys=true,keyProperty="id")
    void insert(OrderGoods orderGoods);


    @Select("SELECT * FROM JJ_ORDER_GOODS WHERE ORDERID = #{orderId}")
    List<OrderGoods> getByOrderId(int orderId);

    @Delete("DELETE FROM JJ_ORDER_GOODS WHERE ORDERID = #{orderId}")
    void del(int orderId);
}
