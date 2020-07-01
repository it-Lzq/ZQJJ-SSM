package cn.itlzq.model;


import java.sql.Date;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/3 22:15
 * @email 邮箱:905866484@qq.com
 * @description 描述：订单表
 */
public class Order {
    //自增id
    private int id;
    //用户id
    private int userId;
    //订单状态  0-待付款  1-待发货  2-已发货 3-已签收
    private int status;
    //总金额
    private double money;
    //支付方式id
    private int paymentId;
    //配送方式id
    private int transportId;
    //收获地址id
    private int addressId;
    //是否评价
    private int isAppraise;
    //快递单号
    private String expressNo;
    //下单时间
    private Date createTime;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", status=" + status +
                ", money=" + money +
                ", paymentId=" + paymentId +
                ", transportId=" + transportId +
                ", addressId=" + addressId +
                ", isAppraise=" + isAppraise +
                ", expressNo='" + expressNo + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Order() {
    }

    public Order(int id, int userId, int status, double money, int paymentId, int transportId, int addressId, int isAppraise, String expressNo, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.money = money;
        this.paymentId = paymentId;
        this.transportId = transportId;
        this.addressId = addressId;
        this.isAppraise = isAppraise;
        this.expressNo = expressNo;
        this.createTime = createTime;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getTransportId() {
        return transportId;
    }

    public void setTransportId(int transportId) {
        this.transportId = transportId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getIsAppraise() {
        return isAppraise;
    }

    public void setIsAppraise(int isAppraise) {
        this.isAppraise = isAppraise;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
