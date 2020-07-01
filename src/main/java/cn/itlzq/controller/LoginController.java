package cn.itlzq.controller;

import cn.itlzq.model.Carts;
import cn.itlzq.model.User;
import cn.itlzq.service.CartsService;
import cn.itlzq.service.UserService;
import cn.itlzq.service.impl.SqlCartService;
import cn.itlzq.util.SendSms;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/5/24 9:02
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Controller
public class LoginController {
    @Resource
    private UserService userService;

    @Resource
    private SqlCartService sqlCartService;

    //发送短信
    @RequestMapping("/sms")
    @ResponseBody
    public JSONObject sms(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String status = req.getParameter("status");
        String userPhone = req.getParameter("userPhone");
        int code = SendSms.random();
        System.out.println(status+"-"+userPhone+"  "+code);
        JSONObject json = new JSONObject();
        if(status.equals("1")){
            boolean flag =SendSms.send(userPhone, code);
            //boolean flag = true;
            if(flag){
                json.put("status",200);
                json.put("msg","验证码发送成功");
                json.put("realCode",code);
                req.getSession().setAttribute("userPhone",userPhone);
                req.getSession().setAttribute("code",code);

            }else{
                json.put("status",-1);
                json.put("msg","验证码发送失败，请检查您的手机号");
            }
        }
        return json;
    }
    //进行注册
    @RequestMapping("/reg")
    public JSONObject reg(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String userPhone = req.getParameter("userPhone");
        String password = req.getParameter("password");
        String smsCode = req.getParameter("smsCode");
        int realCode = (int)req.getSession().getAttribute("code");
        String realUserPhone = (String)req.getSession().getAttribute("userPhone");
        JSONObject josn = new JSONObject();
        if(smsCode.equals(String.valueOf(realCode)) && userPhone.equals(realUserPhone)){
            //开始注册
            User user = new User();
            user.setUserphone(userPhone);
            user.setPassword(password);
            user.setCreateTime(new Date(System.currentTimeMillis()));
            boolean flag = userService.regist(user);
            if(flag){
                josn.put("status",200);
                josn.put("msg","注册成功");

            }else{
                josn.put("status",-1);
                josn.put("msg","该手机号已注册");
            }
        }else{
            josn.put("status",-1);
            josn.put("msg","验证码错误");
        }
        return josn;
    }

    @RequestMapping("/login")
    @ResponseBody
    public JSONObject login(HttpServletRequest req){
        String userphone = req.getParameter("userPhone");
        String password = req.getParameter("password");
        User user = new User();
        user.setUserphone(userphone);
        user.setPassword(password);

        boolean login = userService.login(user);
        JSONObject json = new JSONObject();
        System.out.println(login);
        if(login){
            json.put("status",200);
            HttpSession session = req.getSession();
            user = userService.getUser(userphone);
            session.setAttribute("user",user);
            //面向切面 合并购物车操作
            if(session.getAttribute("carts") != null){
                this.updateCartStatus(session);
            }
        }else{
            json.put("status",-1);
        }
        return json;
    }

    private void updateCartStatus(HttpSession session){
        List<Carts> cartsList = (List<Carts>) session.getAttribute("carts");
        if(cartsList != null){
            //将session中购物车商品添加到数据库中
            new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i < cartsList.size(); i++) {
                        Carts carts = cartsList.get(i);
                        sqlCartService.addCart(session,carts.getGoodsId(),carts.getCartNum());
                    }

                    session.removeAttribute("carts");
                    session.removeAttribute("cartsCount");
                }
            }.run();
        }
    }
}
