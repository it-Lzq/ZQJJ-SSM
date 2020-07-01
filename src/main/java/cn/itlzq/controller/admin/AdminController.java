package cn.itlzq.controller.admin;

import cn.itlzq.model.*;
import cn.itlzq.service.*;
import cn.itlzq.util.DataUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/5/26 0:49
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Controller
public class AdminController {

    @Resource
    private GoodsClassService goodsClassService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private OrderService orderService;
    @Resource
    private PayMentsService payMentsService;
    @Resource
    private AddressService addressService;
    @Resource
    private OrderGoodsService orderGoodsService;
    @Resource
    private UserService userService;

    @RequestMapping("/admin")
    public String index(HttpServletRequest request){
        init();
        List<Goods> goodsList = goodsService.fingAll();
        request.setAttribute("goodsList",goodsList);
        request.setAttribute("goodsClassShow","All Goods");
        request.setAttribute("type","0");
        return "back/index";
    }

    @RequestMapping("/admin/changeClass")
    public String changeClass(HttpServletRequest request){
        String classId2 = request.getParameter("classId2");
        if (classId2.equals("0")){
            return "redirect:/admin";
        }

        GoodsClass aClass = goodsClassService.findById(Integer.parseInt(classId2));
        List<Goods> goodsList = goodsService.findGoodsByClass2(Integer.parseInt(classId2),1,100,0);
        request.setAttribute("goodsList",goodsList);
        request.setAttribute("cname",aClass.getClassName());
        return "/back/index";
    }
//----------------------order
    @RequestMapping("/admin/toOrder")
    public String toOrder(HttpServletRequest request){
        String status = request.getParameter("status");
        List<Order> orderList = null;
        System.out.println("status:"+status);
        if( status==null || status.equals("-1")){
            orderList  = orderService.getAll();
            request.setAttribute("status","-1");
        }else{
            orderList  = orderService.getByStatus(Integer.parseInt(status));
            request.setAttribute("status",status);
        }

        List<OrderShow> orderShowList = new ArrayList<>();
        for (Order order : orderList) {
            OrderShow orderShow = new OrderShow();
            orderShow.setId(order.getId());
            orderShow.setStatus(order.getStatus());
            orderShow.setExpressNo(order.getExpressNo());
            orderShow.setMoney(order.getMoney());
            orderShow.setCreateTime(order.getCreateTime());
            orderShow.setAddress(addressService.findAddressById(order.getAddressId()));
            orderShow.setPayments(payMentsService.getById(order.getPaymentId()));
            orderShow.setOrderGoodsList(orderGoodsService.getByOrderId(order.getId()));
            orderShowList.add(orderShow);
        }

        request.setAttribute("orderShowList",orderShowList);
        return "/back/order";
    }
    @RequestMapping("/admin/user")
    public String user(HttpServletRequest request){
        List<User> userList = userService.getAll();
        request.setAttribute("userList",userList);
        return "/back/user";
    }

    @RequestMapping("/admin/delOrder")
    public String delOrder(HttpServletRequest req){
        String id = req.getParameter("id");
        orderGoodsService.del(Integer.parseInt(id));
        orderService.del(Integer.parseInt(id));
        return "redirect:/admin/toOrder";
    }

    @RequestMapping("/admin/delUser")
    public String delUser(HttpServletRequest req){
        String id = req.getParameter("id");
        userService.del(Integer.parseInt(id));
        return "redirect:/admin/user";
    }

    @RequestMapping("/selectU")
    public String selectU(HttpServletRequest req){
        String name = req.getParameter("username");
        List<User> userList = userService.findByUserName("'%" + name + "%'");
        req.setAttribute("userList",userList);
        return "/back/user";
    }

    private void init(){
        //从缓存中取出所有分类数据
        List<HashMap<String, Object>> class1 = (List<HashMap<String, Object>>) DataUtil.data.get("class");
        if (class1 == null) {
            //从未存储过，从数据库查询
            List<GoodsClass> goodsClassList = goodsClassService.findAll();
            //创建存储一级分类集合 class1
            class1 = new ArrayList<>();
            //从所有分类中将一级分类取出
            for (int i = 0; i < goodsClassList.size(); i++) {
                //类对象
                GoodsClass goodsClass = goodsClassList.get(i);
                if (goodsClass.getParentId() == 0) {
                    HashMap<String, Object> map = new HashMap<>();
                    //存储二级分类的集合class2
                    List<GoodsClass> class2 = new ArrayList<>();
                    //遍历存储
                    for (int j = 0; j < goodsClassList.size(); j++) {
                        if (goodsClassList.get(j).getParentId() == goodsClass.getId()) {
                            class2.add(goodsClassList.get(j));
                        }
                    }
                    map.put("class1", goodsClass);
                    map.put("class2", class2);
                    class1.add(map);
                }
            }
            DataUtil.data.put("class", class1);
        }
    }
}
