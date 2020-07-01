package cn.itlzq.service;


import cn.itlzq.db.GoodsDao;
import cn.itlzq.model.Carts;
import cn.itlzq.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/5 22:09
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Service
public class GoodsService {

    @Autowired
    private  GoodsDao dao;

    /**
     * 查询所有商品
     * @return 查询结果
     */
    public  List<Goods> fingAll(){
       return  dao.findAll();
    }
    /**
     * 查询一级分类商品(分页，排序)
     * @param classId1 一级分类编号
     * @param page  分页查询的页数
     * @param size 每页显示数目
     * @param flag 0 正序 1 倒序
     * @return 查询结果
     */
    public  List<Goods> findGoodsByClass1(int classId1,int page,int size,int flag){
        return dao.findGoodsByClass1(classId1,(page-1)*size,size,flag);
    }
    /**
     * 查询二级分类商品(分页，排序)
     * @param classId2 一级分类编号
     * @param page  分页查询的页数
     * @param size 每页显示数目
     * @param flag 0 正序 1 倒序
     * @return 查询结果
     */
    public  List<Goods> findGoodsByClass2(int classId2, int page, int size, int flag){
        return dao.findGoodsByClass2(classId2,(page-1)*size,size,flag);
    }

    /**
     * 根据用户输入的商品名称模糊查询
     * @param name 商品名称
     * @return 查询结果
     */
    public  List<Goods> findGoodsLikeName(String name,int page,int size,int flag){
        name = "%"+name+"%";
        return dao.findGoodsLikeName(name,(page-1)*size,size,flag);
    }
    /**
     * 根据商品id查询商品
     * @param id 商品id
     * @return 查询商品
     */
    public  Goods findGoodsById(int id){
        return dao.findGoodsById(id);
    }


    public  List<Goods> findGoodsByCart(List<Carts> cartsList){
        if(cartsList == null || cartsList.size() == 0) return null;
//0.创建一个list集合
        List<Goods> goodsList = new ArrayList<>();
        Goods goods = null;
        for (int i = 0; i < cartsList.size(); i++) {
            Carts carts = cartsList.get(i);
            goods = dao.findGoodsById(carts.getGoodsId());
            goodsList.add(goods);
        }
        return goodsList;
    }

    /**
     *  更改商品库存
     * @param id 传入id
     */
    void updateGoodsStock(int id,int num){dao.updateGoodsStock(id,num);}



}
