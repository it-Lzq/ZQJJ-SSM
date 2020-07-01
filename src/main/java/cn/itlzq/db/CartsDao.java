package cn.itlzq.db;

import cn.itlzq.model.Carts;
import cn.itlzq.model.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/5/24 9:30
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Mapper
@Component
public interface CartsDao {

    /**
     * 将用户购物车商品保存到数据库添加一行数据
     * @param userId 用户id
     * @param goodsId 商品id
     * @param num 商品数量
     */
    @Insert("INSERT INTO JJ_CARTS(USERID,ISCHECK,GOODSID,CARTNUM) VALUES(#{userId},0,#{goodsId},#{num})")
    void addCarts(@Param("userId") int userId,@Param("goodsId") int goodsId,@Param("num") int num);

    /**
     * 查询命苦个用户的购物车中是否拥有此商品
     * @param userId 用户id
     * @param goodsId 商品id
     * @return 购物
     */
    @Select("SELECT * FROM JJ_CARTS WHERE USERID = #{userId} AND GOODSID = #{goodsId}")
    Carts findUserCartsExist(@Param("userId") int userId, @Param("goodsId") int goodsId);

    /**
     * 查询某个用户的购物车
     * @param userId 用户id
     * @return 购物车商品集合
     */
    @Select("SELECT * FROM JJ_CARTS WHERE USERID = #{userId}")
    List<Carts> findCartsByUserId(int userId);


    /**
     * 改变用户购物车的数量
     * @param userId 用户id
     * @param goodsId 商品id
     * @param num +1 或 -1
     * @return 是否成功
     */
    @Update("UPDATE JJ_CARTS SET CARTNUM = CARTNUM + #{num} WHERE USERID = #{userId} AND GOODSID = #{goodsId}")
    int updateCartNum(@Param("userId") int userId,@Param("goodsId") int goodsId,@Param("num") int num);
    /**
     * 改变用户购物车的选中效果
     * @param userId 用户id
     * @param goodsId 商品id
     * @param checked 0 或 -1
     * @return 是否成功
     */
    @Update("UPDATE JJ_CARTS SET ISCHECK = #{checked} WHERE USERID = #{userId} AND GOODSID = #{goodsId}")
    int updateCartChecked(int userId,int goodsId,int checked);


    /**
     * 删除商品
     * @param userId 用户id
     * @param goodsId 商品id
     * @return 删除结果
     */
    @Delete("DELETE FROM JJ_CARTS WHERE USERID = #{userId} AND GOODSID = #{goodsId}")
    int delCarts(@Param("userId") int userId,@Param("goodsId") int goodsId);


}
