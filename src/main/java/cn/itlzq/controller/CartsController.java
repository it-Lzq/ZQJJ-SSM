package cn.itlzq.controller;

import cn.itlzq.model.Carts;
import cn.itlzq.model.Goods;
import cn.itlzq.model.User;
import cn.itlzq.service.CartsService;
import cn.itlzq.service.GoodsService;
import cn.itlzq.service.impl.SessionCartsServiceImpl;
import cn.itlzq.service.impl.SqlCartService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/5/24 13:16
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Controller
public class CartsController {
    CartsService cartsService = null;

    @Resource
    private GoodsService goodsService;
    @Resource
    private SqlCartService sqlCartService;

    //添加到购物车
    @RequestMapping("/addCart")
    @ResponseBody
    public JSONObject addCart(HttpServletRequest req, HttpServletResponse resp){
        String goodsId = req.getParameter("goodsId");
        String num = req.getParameter("num");
        HttpSession session = req.getSession();
        JSONObject json = new JSONObject();
        System.out.println(goodsId);
        if(session.getAttribute("user") == null){
            //用户未登录
            cartsService = new SessionCartsServiceImpl();
            cartsService.addCart(session,Integer.parseInt(goodsId),Integer.parseInt(num));
        }else{
            //用户已登录
            sqlCartService.addCart(session,Integer.parseInt(goodsId),Integer.parseInt(num));
        }
        //更改商品数量
        Integer count = (Integer) session.getAttribute("cartsCount");
        System.out.println(count);
        if(count == null){
            count = 0;
        }
        count += Integer.parseInt(num);
        session.setAttribute("cartsCount",count);
        //返回json
        json.put("statua",200);
        json.put("msg","添加成功");
        return json;
    }

    //到购物车页面
    @RequestMapping("/cart")
    public String carts(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        List<Carts> cartsList;
        List<Goods> goodsList = null;
        User u = (User) session.getAttribute("user");
        if(u == null){
            cartsList = (List<Carts>) session.getAttribute("carts");
            if(cartsList!=null){
                goodsList = goodsService.findGoodsByCart(cartsList);
            }
        }else{
            cartsList = sqlCartService.getCartsList(u.getId());
            if(cartsList!=null){
                goodsList = goodsService.findGoodsByCart(cartsList);
            }
        }
        req.setAttribute("cartsList",cartsList);
        req.setAttribute("goodsList",goodsList);
        return "cart";
    }

    //删除购物车商品
    @RequestMapping("/delCarts")
    @ResponseBody
    public JSONObject delCarts(HttpServletRequest req){
        String goodsId = req.getParameter("goodsId");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        JSONObject json = new JSONObject();
        System.out.println(goodsId+user);
        int status = -1;
        if(user == null){
            //未登录
            cartsService = new SessionCartsServiceImpl();
            //删除商品
            status = cartsService.delCarts(session, Integer.parseInt(goodsId));
            json.put("cartsCount",session.getAttribute("cartsCount"));
        }else{
            //已登录
            status = sqlCartService.delCarts(session, Integer.parseInt(goodsId));
            json.put("cartsCount",sqlCartService.getCount(user.getId()));
        }
        //设置响应json
        json.put("status",status);
        //返回json
        return json;
    }

    //修改商品数量
    @RequestMapping("/cartNumUpdate")
    @ResponseBody
    public JSONObject cartNumUpdate(HttpServletRequest req){
        //获取商品id，修改数量，session域对象
        String goodsId = req.getParameter("goodsId");
        String cartNum = req.getParameter("cartNum");
        HttpSession session = req.getSession();
        JSONObject json = new JSONObject();
        int status;
        if(session.getAttribute("user")==null){
            //用户未登录
            cartsService = new SessionCartsServiceImpl();
            status = cartsService.updateCartNum(session,Integer.parseInt(goodsId),Integer.parseInt(cartNum));
        }else{
            //用户已登录
            status = sqlCartService.updateCartNum(session,Integer.parseInt(goodsId),Integer.parseInt(cartNum));
        }

        //设置响应json
        json.put("status",status);
        //返回json
        return json;
    }

    //是否选中
    @RequestMapping("/cartChecked")
    @ResponseBody
    public JSONObject cartChecked(HttpServletRequest req){
        String goodsId = req.getParameter("goodsId");
        String checked = req.getParameter("checked");
        HttpSession session = req.getSession();
        JSONObject json = new JSONObject();
        int status;
        if(session.getAttribute("user") == null){
            //未登录
            cartsService = new SessionCartsServiceImpl();
            status = cartsService.cartChecked(session, Integer.parseInt(goodsId), Integer.parseInt(checked));
        }else{
            //已登录
            status = sqlCartService.cartChecked(session, Integer.parseInt(goodsId), Integer.parseInt(checked));
        }
        //设置响应json
        json.put("status",status);
        //返回json
        return json;
    }


}
