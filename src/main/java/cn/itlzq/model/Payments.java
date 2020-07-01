package cn.itlzq.model;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/3 21:43
 * @email 邮箱:905866484@qq.com
 * @description 描述：支付方式
 */
public class Payments {
    //id
    private int id;
    //支付方式名称
    private String name;
    //图片地址
    private String img;



    public Payments() {
    }

    public Payments(int id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
