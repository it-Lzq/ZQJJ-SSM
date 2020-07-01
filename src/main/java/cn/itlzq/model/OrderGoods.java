package cn.itlzq.model;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/3 22:22
 * @email 邮箱:905866484@qq.com
 * @description 描述：订单商品表
 */
public class OrderGoods {
    //id
    private int id;
    //订单id
    private int orderId;
    //商品id
    private int goodsId;
    //商品数量
    private int goodsNum;
    //价格
    private double goodsPrice;
    //图片
    private String goodsImg;
    private String goodsName;

    public OrderGoods() {
    }

    public OrderGoods(int id, int orderId, int goodsId, int goodsNum, double goodsPrice, String goodsImg, String goodsName) {
        this.id = id;
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.goodsNum = goodsNum;
        this.goodsPrice = goodsPrice;
        this.goodsImg = goodsImg;
        this.goodsName = goodsName;
    }

    @Override
    public String toString() {
        return "OrderGoods{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", goodsId=" + goodsId +
                ", goodsNum=" + goodsNum +
                ", goodsPrice=" + goodsPrice +
                ", goodsImg='" + goodsImg + '\'' +
                '}';
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }
}
