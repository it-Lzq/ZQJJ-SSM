package cn.itlzq.service.impl;


import cn.itlzq.model.Carts;
import cn.itlzq.service.CartsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/14 1:20
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */

public class SessionCartsServiceImpl implements CartsService {

    @Override
    public void addCart(HttpSession session, int id, int num) {
        //未登录情况
        ArrayList<Carts> cartsList = (ArrayList<Carts>) session.getAttribute("carts");
        if(cartsList == null){
            cartsList = new ArrayList<>();
        }
        //通过商品id查询商品
        Carts carts = new Carts(id);
        int index = cartsList.indexOf(carts);
        if(index != -1){
            //购物车有此商品
            Carts oldCarts = cartsList.get(index);
            oldCarts.setCartNum(oldCarts.getCartNum()+num);
        }else{
            //购物车不存在，将商品存入购物车
            carts.setCartNum(num);
            carts.setIsCheck(0);
            cartsList.add(carts);
        }
        session.setAttribute("carts",cartsList);


    }

    @Override
    public int updateCartNum(HttpSession session, int goodsId, int num) {
        ArrayList<Carts> cartsList = (ArrayList<Carts>) session.getAttribute("carts");
        Carts c = new Carts(goodsId);
        int index = cartsList.indexOf(c);
        if(index != -1){
            //商品存在，更改商品数量
            cartsList.get(index).setCartNum(cartsList.get(index).getCartNum()+num);
            Integer count = (Integer) session.getAttribute("cartsCount");
            session.setAttribute("cartsCount",count+num);
            return 200;
        }else{
          return -1;
        }
    }

    @Override
    public int cartChecked(HttpSession session, int goodsId, int checked) {
        ArrayList<Carts> cartsList = (ArrayList<Carts>) session.getAttribute("carts");
        Carts c = new Carts(goodsId);
        int index = cartsList.indexOf(c);
        if(index != -1){
            cartsList.get(index).setIsCheck(checked);
            return 200;
        }else{
            return -1;
        }
    }

    @Override
    public int delCarts(HttpSession session, int goodsId) {
        ArrayList<Carts> cartsList = (ArrayList<Carts>) session.getAttribute("carts");
        Integer cartsCount = (Integer) session.getAttribute("cartsCount");
        Carts c = new Carts(goodsId);
        int index = cartsList.indexOf(c);
        if(index != -1){
            Carts remove = cartsList.remove(index);
            cartsCount = cartsCount - remove.getCartNum();
            session.setAttribute("cartsCount",cartsCount==0?null:cartsCount);
            return 200;
        }else{
            return -1;
        }
    }


}
