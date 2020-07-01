package cn.itlzq.model;

import java.sql.Date;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/5/25 12:59
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public class OrderShow {
    private int id;
    private int status;
    //总金额
    private double money;
    //下单时间
    private Date createTime;
    //快递单号
    private String expressNo;
    private Address address;
    private Payments payments;
    private Transports transports;
    private List<OrderGoods> orderGoodsList;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public OrderShow() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Payments getPayments() {
        return payments;
    }

    public void setPayments(Payments payments) {
        this.payments = payments;
    }

    public Transports getTransports() {
        return transports;
    }

    public void setTransports(Transports transports) {
        this.transports = transports;
    }

    public List<OrderGoods> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<OrderGoods> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }

    @Override
    public String toString() {
        return "OrderShow{" +
                "id=" + id +
                ", address=" + address +
                ", payments=" + payments +
                ", transports=" + transports +
                ", orderGoodsList=" + orderGoodsList +
                '}';
    }
}
