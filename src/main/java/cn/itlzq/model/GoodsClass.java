package cn.itlzq.model;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/3 21:35
 * @email 邮箱:905866484@qq.com
 * @description 描述：商品分类
 */
public class GoodsClass {

    //id
    private int id;
    //父分类id
    private int parentId;
    //分类名称
    private String className;


    public GoodsClass() {
    }

    public GoodsClass(int id, int parentId, String className) {
        this.id = id;
        this.parentId = parentId;
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return  "编号=" + id +
                ", 父级编号=" + parentId +
                ", 类名='" + className + ";\n";
    }
}
