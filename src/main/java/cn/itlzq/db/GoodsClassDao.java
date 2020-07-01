package cn.itlzq.db;

import cn.itlzq.model.GoodsClass;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/5/23 17:15
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Mapper
@Component
public interface GoodsClassDao {
    /**
     * 用于查询所有分类
     * @return 商品分类表list
     */

    List<GoodsClass> findAll();

    /**
     * 查询商品分类
     * @param id 根据id
     * @return GoodsClass
     */
    @Select("SELECT * FROM JJ_CLASS WHERE ID = #{id}")
    GoodsClass findById(int id);



}
