<%@ page import="cn.itlzq.model.User" %>
<%@ page import="cn.itlzq.service.impl.SqlCartService" %>
<%--
  Created by IntelliJ IDEA.
  User: 李泽庆哇
  Date: 2020/2/13
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="gotop">
    <a href="/cart">
        <dl class="goCart">
            <dt><img src="img/gt1.png"/></dt>
            <dd>去购<br/>物车</dd>
            <%
                User usr = (User) session.getAttribute("user");
                if(usr == null){%>
                    <%if(session.getAttribute("cartsCount") != null && session.getAttribute("carts")!=null){%>
            <span>${ sessionScope.cartsCount }</span>
                <%}}else{
//                    SqlCartService sqlCartService = new SqlCartService();
//                    int count = sqlCartService.getCount(usr.getId());
                    int count = 0;
                    if(count !=0){
                %>
            <span><%=count%></span>
            <%}}%>
        </dl>
    </a>
    <a href="#" class="dh">
        <dl>
            <dt><img src="img/gt2.png"/></dt>
            <dd>联系<br/>客服</dd>
        </dl>
    </a>
    <a href="/mygxin">
        <dl>
            <dt><img src="img/gt3.png"/></dt>
            <dd>个人<br/>中心</dd>
        </dl>
    </a>
    <a href="#" class="toptop" style="display: none;">
        <dl>
            <dt><img src="img/gt4.png"/></dt>
            <dd>返回<br/>顶部</dd>
        </dl>
    </a>
    <p>15034344690</p>
</div>
<!--footer-->
<div class="footer">
    <div class="top">
        <div class="wrapper">
            <div class="clearfix">
                <a href="#2" class="fl"><img src="img/foot1.png" /></a><span class="fl">7天无理由退货</span>
            </div>
            <div class="clearfix">
                <a href="#2" class="fl"><img src="img/foot2.png" /></a><span class="fl">15天免费换货</span>
            </div>
            <div class="clearfix">
                <a href="#2" class="fl"><img src="img/foot3.png" /></a><span class="fl">满599包邮</span>
            </div>
            <div class="clearfix">
                <a href="#2" class="fl"><img src="img/foot4.png" /></a><span class="fl">手机特色服务</span>
            </div>
        </div>
    </div>
    <p class="dibu">泽庆家居&copy;为您的家庭增添一抹温馨<br/> 本网站所列数据仅作为学生作品展示，如有侵权，请联系：15034344690</p>
</div>
</body>
</html>
