package cn.itlzq.service.impl;


import cn.itlzq.db.CartsDao;
import cn.itlzq.model.Carts;
import cn.itlzq.model.User;
import cn.itlzq.service.CartsService;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/14 1:35
 * @email 邮箱:905866484@qq.com
 * @description 描述：用户登录时数据存储到数据库
 */
@Service
public class SqlCartService {

    @Resource
    private  CartsDao dao;


    public void addCart(HttpSession session, int goodsId, int num) {
        System.out.println(dao);
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        Carts carts= dao.findUserCartsExist(user.getId(), goodsId);
        System.out.println(carts);
        if(carts != null){
            //购物车中有此商品，增加数量即可
            dao.updateCartNum(user.getId(),goodsId,num);
        }else{
            //新增商品到购物车中
            dao.addCarts(user.getId(),goodsId,num);
        }
    }


    public int updateCartNum(HttpSession session, int goodsId, int num) {
        User user = (User) session.getAttribute("user");
        int index =  dao.updateCartNum(user.getId(),goodsId,num);
        if(index == 0){
            Integer count = (Integer) session.getAttribute("cartsCount");
            session.setAttribute("cartsCount",count+num);
            return 200;
        }else{
            return -1;
        }
    }


    public int cartChecked(HttpSession session, int goodsId, int checked) {
        User user = (User) session.getAttribute("user");
        return dao.updateCartChecked(user.getId(),goodsId,checked);
    }


    public int delCarts(HttpSession session, int goodsId) {
        User user = (User) session.getAttribute("user");
        Carts carts = dao.findUserCartsExist(user.getId(), goodsId);
        int index = dao.delCarts(user.getId(),goodsId);
        if(index == 0){
            Integer count = (Integer) session.getAttribute("cartsCount");
            session.setAttribute("cartsCount",getCount(user.getId()));
            return 200;
        }else{
            return -1;
        }
    }

    public  List<Carts> getCartsList(int userId){
        return dao.findCartsByUserId(userId);
    }

    public  int getCount(int userId){
        List<Carts> cartsList = getCartsList(userId);
        int count = 0;
        for (Carts carts:cartsList) {
            count += carts.getCartNum();
        }
        return count;
    }
}
