<%@ page import="cn.itlzq.model.User" %>
<%@ page import="cn.itlzq.util.DataUtil" %>
<%@ page import="cn.itlzq.model.GoodsClass" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>>
<%--
  Created by IntelliJ IDEA.
  User: 李泽庆哇
  Date: 2020/2/6
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>

<head lang="en">
    <meta charset="utf-8"/>
</head>

<body>
<div class="head">
    <div class="wrapper clearfix">
        <div class="clearfix" id="top">
            <h1 class="fl">
                <a href="back/index.jsp">在线销售平台</a>
            </h1>
            <div class="fr clearfix" id="top1">
                <p class="fl">
                    <%
                        User user = (User) session.getAttribute("user");
                        if (user == null) {

                    %>
                    <a href="/tologin" id="login">登录</a>
                    <a href="/toreg" id="reg">注册</a>
                    <%} else {%>
                    <a href="/mygxin">
                        <%=user.getNickName()%>
                    </a>
                    <%}%>
                </p>
                <form action="findGoodsClass" method="get" class="fl">
                    <input type="text" name="goodsName" placeholder="热门搜索：干花花瓶"/>
                    <input type="hidden" name="type" value="3">
                    <input type="submit" value="">
                </form>
                <div class="btn fl clearfix">
                    <a href="/mygxin"><img src="img/grzx.png"/></a>
                    <a href="/cart"><img src="img/gwc.png"/></a>
                </div>
            </div>
        </div>
        <ul class="clearfix" id="bott">
            <li><a href="/index">首页</a></li>
            </li>
            <%

                List<HashMap<String, Object>> class1 = (List<HashMap<String, Object>>) DataUtil.data.get("class");
                for (int i = 0; i < class1.size(); i++) {
                    HashMap<String, Object> classMap = class1.get(i);
                    GoodsClass cla1 = (GoodsClass) classMap.get("class1");
                    List<GoodsClass> cla2List = (List<GoodsClass>) classMap.get("class2");
            %>
            <li>
                <a href="findGoodsClass?type=1&classId1=<%=cla1.getId()%>">
                    <%=cla1.getClassName()%>
                </a>
                <div class="sList2">
                    <div class="clearfix">
                        <%
                            for (GoodsClass goodsClass : cla2List) {
                        %>
                        <a href="findGoodsClass?type=2&classId2=<%=goodsClass.getId()%>">
                            <%=goodsClass.getClassName()%>
                        </a>
                        <%
                            }
                        %>
                    </div>
                </div>
            </li>
            <% } %>
        </ul>
    </div>
</div>
</body>

</html>