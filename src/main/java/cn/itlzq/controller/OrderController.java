package cn.itlzq.controller;

import cn.itlzq.db.CartsDao;
import cn.itlzq.model.*;
import cn.itlzq.service.*;
import cn.itlzq.service.impl.SqlCartService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/5/24 13:22
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Controller
@SuppressWarnings("all")
public class OrderController {

    @Resource
    private GoodsService goodsService;
    @Resource
    private OrderService orderService;
    @Resource
    private AddressService addressService;
    @Resource
    private OrderGoodsService orderGoodsService;
    @Resource
    private CartsDao cartsDao;
    @Resource
    private RegionService regionService;
    @Resource
    private TransportsService transportsService;
    @Resource
    private PayMentsService payMentsService;
    @Resource
    private SqlCartService sqlCartService;

    //单个商品直接购买
    @RequestMapping("/putOrder")
    public String order(HttpServletRequest req, HttpServletResponse resp){
        String goodsId = req.getParameter("goodsId");
        String num = req.getParameter("num");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Goods goods = goodsService.findGoodsById(Integer.parseInt(goodsId));
        //订单存储
        Order order = new Order();
        order.setUserId(user.getId());
        order.setAddressId(addressService.getDefault());
        order.setMoney(Double.parseDouble(num)*goods.getPrice());
        order.setPaymentId(1);
        order.setTransportId(1);
        order.setStatus(0);
        order.setExpressNo(UUID.randomUUID().toString());
        order.setCreateTime(new Date(System.currentTimeMillis()));
        orderService.insertOrder(order);
        //商品订单
        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setOrderId(order.getId());
        orderGoods.setGoodsId(goods.getId());
        orderGoods.setGoodsNum(Integer.parseInt(num));
        orderGoods.setGoodsPrice(goods.getPrice());
        orderGoods.setGoodsImg(goods.getImgs());
        orderGoods.setGoodsName(goods.getName());
        orderGoodsService.insert(orderGoods);
        //存储到session
        session.setAttribute("order",order);
        return "order";
    }

//购物车商品购买
    @RequestMapping("/putOrders")
    public  String orders(HttpServletRequest req, HttpServletResponse resp){
        String idMsg = req.getParameter("idMsg");
        String priceA = req.getParameter("priceA");
        String[] ids = idMsg.split("-");
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        List<Carts> cartsList =sqlCartService.getCartsList(user.getId());

        Order order = new Order();
        order.setUserId(user.getId());
        order.setAddressId(addressService.getDefault());
        order.setMoney(Double.parseDouble(priceA));
        order.setPaymentId(1);
        order.setTransportId(1);
        order.setStatus(0);
        order.setCreateTime(new Date(System.currentTimeMillis()));
        order.setExpressNo(UUID.randomUUID().toString());
        orderService.insertOrder(order);
        System.out.println(order);

        for (int i = 0; i < ids.length; i++) {
            Carts carts = new Carts(Integer.parseInt(ids[i]));
            int index = cartsList.indexOf(carts);
            carts = cartsList.get(index);
            Goods goods = goodsService.findGoodsById(Integer.parseInt(ids[i]));
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setOrderId(order.getId());
            orderGoods.setGoodsId(goods.getId());
            orderGoods.setGoodsNum(carts.getCartNum());
            orderGoods.setGoodsPrice(goods.getPrice());
            orderGoods.setGoodsImg(goods.getImgs());
            orderGoods.setGoodsName(goods.getName());
            orderGoodsService.insert(orderGoods);
            //移出购物车
            cartsDao.delCarts(user.getId(),goods.getId());

        }
        setAtt(req,user.getId(),order.getId());
        session.setAttribute("order",order);
        return "order";
    }

    public void setAtt(HttpServletRequest req,int userId,int orderId){
        List<CnRegion> provinces = regionService.findByLevel(1);
        List<CnRegion> citys = regionService.findByLevel(2);
        List<CnRegion> areas = regionService.findByLevel(3);
        List<Address> addressList = addressService.findByUserId(userId);
        List<Payments> paymentsList = payMentsService.getPayments();
        List<Transports> transportsList = transportsService.getTransports();
        List<OrderGoods> orderGoodsList = orderGoodsService.getByOrderId(orderId);
        req.setAttribute("orderGoodsList",orderGoodsList);
        req.setAttribute("provinces",provinces);
        req.setAttribute("citys",citys);
        req.setAttribute("areas",areas);
        req.setAttribute("addressList",addressList);
        req.setAttribute("paymentsList",paymentsList);
        req.setAttribute("transportsList",transportsList);
    }

//---------------------
    @RequestMapping("/getRegions")
    @ResponseBody
    public JSONObject getRegoin(HttpServletRequest req){
        String code = req.getParameter("code");
        CnRegion cnRegion = regionService.findByCode(code);
        JSONObject json = new JSONObject();
        if(cnRegion.getLevel()==4){
            json.put("status",-1);
        }else{
            List<CnRegion> citys = regionService.findByParentCode(code);
            json.put("citys",citys);
            json.put("status",200);
            json.put("level",cnRegion.getLevel()+1);
        }
        return json;
    }

    @RequestMapping("/Transport")
    public void Transport(HttpServletRequest req){
        String id = req.getParameter("id");
        Order order = (Order) req.getSession().getAttribute("order");
        order.setTransportId(Integer.parseInt(id));
        orderService.updateOrder(order);
    }

    @RequestMapping("/changePayMents")
    public void changePayMents(HttpServletRequest req){
        String id = req.getParameter("id");
        Order order = (Order) req.getSession().getAttribute("order");
        order.setPaymentId(Integer.parseInt(id));
        orderService.updateOrder(order);
    }

    @RequestMapping("/addressUp")
    public void addressUp(HttpServletRequest req){
        String id = req.getParameter("id");
        Order order = (Order) req.getSession().getAttribute("order");
        order.setAddressId(Integer.parseInt(id));
        orderService.updateOrder(order);
    }
/*address*/
    @RequestMapping("/setDefault")
    public String setDefault(HttpServletRequest req){
        String id = req.getParameter("id");
        addressService.setDefault(Integer.parseInt(id));
        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");
        User user = (User)session.getAttribute("user");
        setAtt(req,user.getId(),order.getId());
        return "order";
    }

    @RequestMapping("/delAddress")
    public String delAddress(HttpServletRequest req){
        String id = req.getParameter("id");
        addressService.deleteAddress(Integer.parseInt(id));
        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");
        User user = (User)session.getAttribute("user");
        setAtt(req,user.getId(),order.getId());
        return "order";
    }

    @RequestMapping("/addressUpdate")
    public String addressUpdate(HttpServletRequest req){
        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");
        User user = (User)req.getSession().getAttribute("user");
        String username = req.getParameter("username");
        String userphone = req.getParameter("userphone");
        int provinceCode = Integer.parseInt(req.getParameter("provinceCode"));
        int cityCode = Integer.parseInt(req.getParameter("cityCode"));
        int areaCode = Integer.parseInt(req.getParameter("areaCode"));
        int streeCode = Integer.parseInt(req.getParameter("streeCode"));
        String userAddress = req.getParameter("addMessage");
        int id =Integer.parseInt(req.getParameter("aid"));
        Address address= new Address(id,user.getId(),username,userphone,provinceCode,cityCode,areaCode,streeCode,userAddress,0,new Date(System.currentTimeMillis()));
        if(id == -1){
            //添加
            addressService.insertAddress(address);
        }else{
            //修改
            addressService.updateAddress(id,address);
        }
        setAtt(req,user.getId(),order.getId());
        return "order";
    }



}
