<%@ page import="cn.itlzq.model.User" %>
<%@ page import="cn.itlzq.service.OrderService" %>
<%@ page import="cn.itlzq.model.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head lang="en">
    <meta charset="utf-8" />
    <title>个人信息</title>
    <link rel="stylesheet" type="text/css" href="css/public.css" />
    <link rel="stylesheet" type="text/css" href="css/mygxin.css" />
    <script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/layer/layer.js" type="text/javascript"></script>
    <script src="js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/user.js" type="text/javascript" charset="utf-8"></script>
</head>

<body>
    <%

        User user = (User) session.getAttribute("user");
        Map<Integer, List<Order>> orderMap = (Map<Integer, List<Order>>) request.getAttribute("orderMap");
    %>

        <!------------------------------head------------------------------>
        <jsp:include page="include_header.jsp" />
        <!------------------------------idea------------------------------>
        <div class="address mt" id="add">
            <div class="wrapper clearfix"><a href="index.jsp" class="fl">首页</a><span>/</span>
                <a href="/mygxin" class="on">个人中心</a>
            </div>
        </div>
        <!------------------------------Bott------------------------------>
        <div class="Bott">
            <div class="wrapper clearfix">
                <div class="zuo fl">
                    <h3><a href="#"><img src="img/<%=user.getUserPhoto()%>"/></a>
                        <p class="clearfix"><span class="fl"><%=user.getNickName()%></span>
                            <span class="fr" onclick="quit()" >[退出登录]</span></p>
                        <script type="text/javascript">
                            function quit() {
                                $.post("quitUser",function (data) {
                                    layer.msg(data.msg);
                                    location.href = "/index"
                                });

                            }
                        </script>
                    </h3>
                    <div>
                        <h4>我的交易</h4>
                        <ul>
                            <li><a href="/cart">我的购物车</a></li>
                            <li><a href="/myorderq">我的订单</a></li>
                        </ul>
                        <h4>个人中心</h4>
                        <ul>
                            <li class="on"><a href="/mygxin">我的中心</a></li>
                            <li><a href="/address">地址管理</a></li>
                        </ul>
                        <h4>账户管理</h4>
                        <ul>
                            <li><a href="mygrxx.jsp">个人信息</a></li>
                            <li><a href="remima.jsp">修改密码</a></li>
                        </ul>
                    </div>
                </div>
                <div class="you fl">
                    <div class="tx clearfix">
                        <div class="fl clearfix">
                            <a href="#" class="fl"><img src="img/<%=user.getUserPhoto()%>"/></a>
                            <p class="fl">
                                <span><%%></span>
                                <a href="mygrxx.jsp">修改个人信息></a>
                            </p>
                        </div>
                        <div class="fr">
                            <%if(user.getEmail()==null){%>未绑定邮箱
                                <%}else{%>
                                    <%=user.getEmail()%>
                                        <%}%>
                        </div>
                    </div>
                    <div class="bott">
                        <div class="clearfix">
                            <a href="#" class="fl"><img src="img/gxin1.jpg" /></a>
                            <p class="fl"><span>待发货的订单：<strong><%=orderMap.get(0).size()%></strong></span><a href="myorderq.jsp">查看待支付订单></a></p>
                        </div>
                        <div class="clearfix">
                            <a href="#" class="fl"><img src="img/gxin2.jpg" /></a>
                            <p class="fl"><span>待收货的订单：<strong><%=orderMap.get(1).size()%></strong></span><a href="myorderq.jsp">查看待收货订单></a></p>
                        </div>
                        <div class="clearfix">
                            <a href="#" class="fl"><img src="img/gxin3.jpg" /></a>
                            <p class="fl"><span>待评价的订单：<strong><%=orderMap.get(2).size()%></strong></span><a href="myprod.html">查看待评价订单></a></p>
                        </div>
                        <div class="clearfix">
                            <a href="#" class="fl"><img src="img/gxin4.jpg" /></a>
                            <p class="fl"><span>喜欢的商品：<strong>0</strong></span><a href="#">查看喜欢的商品></a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <!--返回顶部-->
    <jsp:include page="footer.jsp"/>

</body>

</html>