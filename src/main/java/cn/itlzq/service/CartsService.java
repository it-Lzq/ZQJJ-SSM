package cn.itlzq.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/14 1:08
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */

public interface CartsService {

    /**
     * 对于商品的增加
     * @param session session域
     * @param goodsId 商品id
     * @param num 商品增加数量
     */
    void addCart(HttpSession session, int goodsId, int num);

    /**
     * 对于商品数量的修改
     * @param session session域对象
     * @param goodsId 商品id
     * @param num 商品修改数量
     */
    int updateCartNum(HttpSession session, int goodsId, int num);


    /**
     * 用于选中或取消选中购物车
     * @param session session域对象
     * @param goodsId 商品id
     * @param checked 选中1 未选中0
     * @return 状态参数
     */
    int cartChecked(HttpSession session, int goodsId, int checked);


    int delCarts(HttpSession session, int goodsId);
}
