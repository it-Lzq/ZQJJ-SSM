package cn.itlzq.model;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/3 21:45
 * @email 邮箱:905866484@qq.com
 * @description 描述：配送方式
 */
public class Transports {
    //id
    private int id;
    //配送方式名称
    private String name;


    public Transports(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Transports() {
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

    @Override
    public String toString() {
        return "Transports{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
