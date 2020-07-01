package cn.itlzq.model;

import java.util.Objects;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/3 21:47
 * @email 邮箱:905866484@qq.com
 * @description 描述：购物车表
 */
public class Carts {
    //id
    private int id;
    //用户id
    private int userId;
    //商品id
    private int goodsId;
    //是否选中 1是选中 0是未选中
    private int isCheck;
    //商品数量
    private int cartNum;

    public Carts() {
    }

    public Carts(int goodsId) {
        this.goodsId = goodsId;
    }

    public Carts(int id, int userId, int isCheck, int goodsId, int cartNum) {
        this.id = id;
        this.userId = userId;
        this.isCheck = isCheck;
        this.goodsId = goodsId;
        this.cartNum = cartNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carts carts = (Carts) o;
        return goodsId == carts.goodsId;
    }

    @Override
    public String toString() {
        return "Carts{" +
                "id=" + id +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", isCheck=" + isCheck +
                ", cartNum=" + cartNum +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, goodsId, isCheck, cartNum);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }

    public int getCartNum() {
        return cartNum;
    }

    public void setCartNum(int cartNum) {
        this.cartNum = cartNum;
    }
}
