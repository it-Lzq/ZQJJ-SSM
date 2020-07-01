package cn.itlzq.db;

import cn.itlzq.model.Carts;
import cn.itlzq.model.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/5/23 19:34
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Mapper
@Component
public interface GoodsDao {
    /**
     * 查询所有商品
     * @return 查询结果
     */
    @Select("SELECT * FROM JJ_GOODS")
    List<Goods> findAll();

    /**
     * 查询一级分类商品(分页，排序)
     * @param classId1 一级分类编号
     * @param page  分页查询的页数
     * @param size 每页显示数目
     * @param flag 0 正序 1 倒序
     * @return 查询结果
     */
    List<Goods> findGoodsByClass1(@Param("classId1") int classId1,@Param("page") int page,@Param("size") int size,@Param("flag") Integer flag);

    /**
     * 查询二级分类商品(分页，排序)
     * @param classId2 一级分类编号
     * @param page  分页查询的页数
     * @param size 每页显示数目
     * @param flag 0 正序 1 倒序
     * @return 查询结果
     */
    List<Goods> findGoodsByClass2(@Param("classId2") int classId2,@Param("page") int page,@Param("size") int size,@Param("flag")Integer flag);
    /**
     * 根据用户输入的商品名称模糊查询
     * @param name 商品名称
     * @return 查询结果
     */
    List<Goods> findGoodsLikeName(@Param("name") String name,@Param("page") int page,@Param("size") int size,@Param("flag")Integer flag);
    /**
     * 根据商品id查询商品
     * @param id 商品id
     * @return 查询商品
     */
    @Select("SELECT * FROM JJ_GOODS WHERE ID = #{id}")
    Goods findGoodsById(int id);


    /**
     *  更改商品库存
     * @param id 传入id
     */
    @Update("UPDATE JJ_GOODS SET STOCK = #{num} WHERE ID = #{id}")
    void updateGoodsStock(int id,int num);
}
