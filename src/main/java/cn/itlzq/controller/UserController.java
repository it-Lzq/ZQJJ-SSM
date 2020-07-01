package cn.itlzq.controller;

import cn.itlzq.model.*;
import cn.itlzq.service.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/5/25 11:51
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Controller
public class UserController {

    @Resource
    private OrderService orderService;
    @Resource
    private TransportsService transportsService;
    @Resource
    private PayMentsService payMentsService;
    @Resource
    private AddressService addressService;
    @Resource
    private OrderGoodsService orderGoodsService;
    @Resource
    private UserService userService;
    @Resource
    private RegionService regionService;

    @RequestMapping("/mygxin")
    public String mygxin(HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Map<Integer, List<Order>> orderMap = orderService.getOrderMap(user.getId());
        req.setAttribute("orderMap",orderMap);
        return "mygxin";
    }
    @RequestMapping("/myorderq")
    public String myorderq(HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Order> ordersList = orderService.getByUserId(user.getId());
        List<OrderShow> orderShowList = new ArrayList<>();
        for (Order order : ordersList) {
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
        req.setAttribute("orderShowList",orderShowList);

        return "myorderq";
    }

    @RequestMapping("/updateInfo")
    public String updateInfo(HttpServletRequest req){
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String userphone = req.getParameter("userphone");
        User user = new User();
        user.setUserphone(userphone);
        user.setEmail(email);
        user.setNickName(name);
        userService.updateUserInfo(user);
        user = userService.getUser(user.getUserphone());
        req.getSession().setAttribute("user",user);

        return "mygrxx";
    }

    @RequestMapping("/updatePass")
    @ResponseBody
    public JSONObject updatePass(HttpServletRequest req){
        System.out.println("updatePass");
        String pass = req.getParameter("pass");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        user.setPassword(pass);
        userService.update(user);
        JSONObject json = new JSONObject();
        json.put("msg","密码修改成功");
        return json;
    }

    @RequestMapping("/address")
    public String address(HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        List<CnRegion> provinces = regionService.findByLevel(1);
        List<CnRegion> citys = regionService.findByLevel(2);
        List<CnRegion> areas = regionService.findByLevel(3);
        List<Address> addressList = addressService.findByUserId(user.getId());
        req.setAttribute("provinces",provinces);
        req.setAttribute("citys",citys);
        req.setAttribute("areas",areas);
        req.setAttribute("addressList",addressList);
        return "address";
    }

    @RequestMapping("/addressUpdates")
    public String addressUpdates(HttpServletRequest req){
        HttpSession session = req.getSession();
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
        }
        return "redirect:/address";
    }
    @RequestMapping("/quitUser")
    @ResponseBody
    public JSONObject quitUser(HttpServletRequest req){
        HttpSession session = req.getSession();
        session.removeAttribute("user");

        JSONObject json = new JSONObject();
        json.put("msg","请重新登录");
        return json;
    }
}
