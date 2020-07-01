package cn.itlzq.db;


import cn.itlzq.model.Transports;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/21 0:05
 * @email 邮箱:905866484@qq.com
 * @description 描述：快递
 */
@Mapper
@Component
public interface TransportsDao {

    /**
     * 获取所有的快递方式
     * @return 快递
     */
    @Select("SELECT * FROM JJ_TRANSPORTS")
    List<Transports> getTransports();

    /**
     * 通过id取得支付
     * @param id id
     * @return 结果
     */
    @Select("SELECT * FROM JJ_TRANSPORTS WHERE ID = #{id}")
   Transports getById(int id);
}
