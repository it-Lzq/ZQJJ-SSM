package cn.itlzq.db;


import cn.itlzq.model.Payments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/20 23:56
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Mapper
@Component
public interface PayMentsDao {

    /**
     * 获取所有支付方式
     * @return 支付方式集合
     */
    @Select("SELECT * FROM JJ_PAYMENTS")
    List<Payments> getPayments();



    /**
     * 通过id取得支付
     * @param id id
     * @return 结果
     */
    @Select("SELECT * FROM JJ_PAYMENTS WHERE ID = #{id}")
    Payments getById(int id);


}
