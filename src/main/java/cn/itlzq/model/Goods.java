package cn.itlzq.model;


import java.util.Date;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/3 21:29
 * @email 邮箱:905866484@qq.com
 * @description 描述：商品
 */
public class Goods {
    //id
    private int id;
    //商品名称
    private String name;
    //商品图片
    private String imgs;
    //商品价格
    private double price;
    //商品简述
    private String gdesc;
    //商品库存
    private int stock;
    //评价数
    private int appraiseNum;
    //商品详情
    public String info;
    //创建时间
    private Date createTime;
    //一级分类
    private int classid1;
    //二级分类
    private int classid2;

    public Goods() {
    }

    public Goods(int id, String name, String imgs, double price, String gdesc, int stock, int appraiseNum, String info, Date createTime, int classid1, int classid2) {
        this.id = id;
        this.name = name;
        this.imgs = imgs;
        this.price = price;
        this.gdesc = gdesc;
        this.stock = stock;
        this.appraiseNum = appraiseNum;
        this.info = info;
        this.createTime = createTime;
        this.classid1 = classid1;
        this.classid2 = classid2;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgs='" + imgs + '\'' +
                ", price=" + price +
                ", gdesc='" + gdesc + '\'' +
                ", stock=" + stock +
                ", appraiseNum=" + appraiseNum +
                ", info='" + info + '\'' +
                ", createTime=" + createTime +
                ", classid1=" + classid1 +
                ", classid2=" + classid2 +"\n"+
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGdesc() {
        return gdesc;
    }

    public void setGdesc(String gdesc) {
        this.gdesc = gdesc;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getAppraiseNum() {
        return appraiseNum;
    }

    public void setAppraiseNum(int appraiseNum) {
        this.appraiseNum = appraiseNum;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getClassid1() {
        return classid1;
    }

    public void setClassid1(int classid1) {
        this.classid1 = classid1;
    }

    public int getClassid2() {
        return classid2;
    }

    public void setClassid2(int classid2) {
        this.classid2 = classid2;
    }
}
