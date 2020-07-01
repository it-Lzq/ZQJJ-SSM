package cn.itlzq.db;

import cn.itlzq.model.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/5/24 13:25
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Mapper
@Component
public interface OrderDao {
    /**
     * 添加订单
     * @param order
     */
    @Insert("INSERT INTO JJ_ORDERS(USERID,STATUS,MONEY,PAYMENTID,TRANSPORTID,ADDRESSID,EXPRESSNO,CREATETIME) " +
            "VALUES(#{userId},#{status},#{money},#{paymentId},#{transportId},#{addressId},#{expressNo},#{createTime})")
    @Options(useGeneratedKeys=true,keyProperty="id")
    void insertOrder(Order order);

    /**
     * 更改订单
     * @param order
     */
    @Update("UPDATE JJ_ORDERS SET STATUS=#{status},PAYMENTID=#{paymentId},TRANSPORTID=#{transportId},ADDRESSID=#{addressId} WHERE ID = #{id}")
    void updateOrder(Order order);

    /**
     * 查询用户订单
     * @param userId 用户id
     * @return 订单列表
     */
    @Select("SELECT * FROM JJ_ORDERS WHERE  USERID = #{userId}")
    List<Order> getByUserId(int userId);


    @Select("SELECT * FROM JJ_ORDERS WHERE  ID = #{id}")
    Order getById(int id);

    @Select("SELECT * FROM JJ_ORDERS WHERE STATUS = #{status}")
    List<Order> getByStatus(int status);

    @Select("SELECT * FROM JJ_ORDERS ")
    List<Order> getAll();

    @Delete("delete from jj_orders where id = #{id}")
    void del(int id);
}
