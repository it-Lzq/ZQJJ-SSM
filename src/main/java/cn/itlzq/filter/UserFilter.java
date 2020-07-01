package cn.itlzq.filter;


import cn.itlzq.model.User;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/mygxin","/mygrxx.jsp","/order","/putOrders","/putOrder","/myorderq","/address","/orderxq.jsp","/remima.jsp"})
public class UserFilter extends HttpFilter {
    public void destroy() {
    }

    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null){
            chain.doFilter(req, resp);
        }else{
            resp.sendRedirect("/tologin");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
