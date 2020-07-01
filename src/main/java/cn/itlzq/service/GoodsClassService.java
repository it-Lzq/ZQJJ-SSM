package cn.itlzq.service;


import cn.itlzq.db.GoodsClassDao;
import cn.itlzq.model.GoodsClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/4 21:27
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Service
public class GoodsClassService {

    @Resource
    private  GoodsClassDao dao;

    //查询所有分类
    public   List<GoodsClass> findAll(){
        return dao.findAll();
    }

    //查询单个分类
    public  GoodsClass findById(int id){
        return dao.findById(id);
    }
}
