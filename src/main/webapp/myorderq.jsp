<%@ page import="cn.itlzq.service.OrderService" %>
<%@ page import="cn.itlzq.model.*" %>
<%@ page import="cn.itlzq.service.AddressService" %>
<%@ page import="cn.itlzq.service.PayMentsService" %>
<%@ page import="cn.itlzq.service.OrderGoodsService" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8" />
    <title>[泽庆家居]我的购物车</title>
    <link rel="stylesheet" type="text/css" href="css/public.css" />
    <link rel="stylesheet" type="text/css" href="css/myorder.css" />
</head>

<body>
<%
    User u = (User) session.getAttribute("user");
    List<OrderShow> orderShowList = (List<OrderShow>) request.getAttribute("orderShowList");
    Address address = null;
    Payments payments = null;
    Transports transports = null;
    List<OrderGoods> orderGoodsList = null;


%>
    <!------------------------------head------------------------------>
   <%@include file="include_header.jsp"%>
    <!------------------------------idea------------------------------>
    <div class="address mt">
        <div class="wrapper clearfix">
            <a href="/index" class="fl">首页</a>
            <span>/</span>
            <a href="/mygxin">个人中心</a>
            <span>/</span>
            <a href="/myorderq" class="on">我的订单</a>
        </div>
    </div>
    <!------------------------------Bott------------------------------>
    <div class="Bott">
        <div class="wrapper clearfix">
            <div class="zuo fl">
                <h3>
                    <a href="#"><img src="img/<%=u.getUserPhoto()%>" /></a>
                    <p class="clearfix">
                        <span class="fl"><%=u.getNickName()%></span>
                        <span class="fr" onclick="quit()" >[退出登录]</span></p>
                    <script type="text/javascript">
                        function quit() {
                            $.post("quitUser",function (data) {
                                layer.msg(data.msg);
                                location.href = "/index"
                            });

                        }
                    </script>
                    </p>
                </h3>
                <div>
                    <h4>我的交易</h4>
                    <ul>
                        <li><a href="cart.jsp">我的购物车</a></li>
                        <li class="on"><a href="/myorderq">我的订单</a></li>
                    </ul>
                    <h4>个人中心</h4>
                    <ul>
                        <li><a href="/mygxin">我的中心</a></li>
                        <li><a href="/address">地址管理</a></li>
                    </ul>
                    <h4>账户管理</h4>
                    <ul>
                        <li ><a href="mygrxx.jsp">个人信息</a></li>
                        <li><a href="remima.jsp">修改密码</a></li>
                    </ul>
                </div>
            </div>
            <div class="you fl">
                <div class="my clearfix">
                    <h2 class="fl">我的订单</h2><a href="#" class="fl">请谨防钓鱼链接或诈骗电话，了解更多&gt;</a></div>
                <div class="dlist clearfix">
                    <ul class="fl clearfix" id="wa">
                        <li class="on"><a href="javascript:showAddress(-1)">全部有效订单</a></li>
                        <li><a href="javascript:showAddress(0)">待发货</a></li>
                        <li><a href="javascript:showAddress(1)">已发货</a></li>
                        <li><a href="javascript:showAddress(2)">已取件</a></li>
                    </ul>
                    <form action="#" method="get" class="fr clearfix">
                        <input type="text" name=""  value=""  placeholder="请输入商品名称、订单号" />
                        <input type="button" name="" id="" value="" />
                    </form>
                </div>
                <%
                   // ordersList = orderMap.get(0);
                    if(orderShowList != null)
                    for (OrderShow order : orderShowList) {
                        address = order.getAddress();
                        System.out.println(address);
                        payments = order.getPayments();
                        orderGoodsList =order.getOrderGoodsList();

                %>
                <div class="dkuang  <%=order.getStatus()==2?"deng":""%> <%="status"+order.getStatus()%>">
                    <p class="one"><%=order.getStatus()==0?"待发货":(order.getStatus()==1?"已发货":"已取件")%></p>
                    <div class="word clearfix">
                        <ul class="fl clearfix">
                            <li><%=order.getCreateTime()%></li>
                            <li><%=address.getUserName()%></li>
                            <li>订单号:<%=order.getExpressNo()%></li>
                            <li><%=payments.getName()%></li>
                        </ul>
                        <p class="fr">订单金额：<span><%=order.getMoney()%></span>元</p>
                    </div>
                    <%
                        for (OrderGoods orderGoods : orderGoodsList) {
                            String img = "errorGoodsImg.jpg" ;
                            Gson g = new Gson();
                            if("[".equals(orderGoods.getGoodsImg())){
                                img = "errorGoodsImg.jpg";
                            }else{
                                List imgs = g.fromJson(orderGoods.getGoodsImg(),List.class);
                                img = (String)imgs.get(0);
                            }
                    %>
                    <div class="shohou clearfix">
                        <a href="#" class="fl"><img src="img/imgs/<%=img%>" /></a>
                        <p class="fl">
                            <a href="#"><%=orderGoods.getGoodsName()%></a>
                            <a href="#">¥<%=orderGoods.getGoodsPrice()%>×<%=orderGoods.getGoodsNum()%></a></p>
                        <p class="fr"><a href="myprod.html">待评价</a><a href="orderxq.jsp?orderId=<%=order.getId()%>">订单详情</a></p>
                    </div>
                    <% }%>
                </div>   <% }%>
                <div class="fenye clearfix">
                    <a href="#"><img src="img/zuo.jpg" /></a><a href="#">1</a>
                    <a href="#"><img src="img/you.jpg" /></a>
                </div>
            </div>
        </div>
    </div>
    <!--返回顶部-->
  <%@include file="footer.jsp"%>
<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/user.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        function showAddress(status) {
           if(status == -1){
               $(".status0").hidden = true;
               $(".status1").hidden = true;
               $(".status2").hidden = true;
           }
           if(status == 0){
               $(".status1").hidden = true;
               $(".status2").hidden = true;
               var $st = $(".status0");
               for (var i = 0; i < $st.length; i++) {
                   $st[i].style = null;
               }

           }
            if(status == 1){
                $(".status0").hidden = true;
                $(".status2").hidden = true;
                var $st = $(".status1");
                for (var i = 0; i < $st.length; i++) {
                    $st[i].style = null;
                }

            }
            if(status == 2){
                $(".status1").hidden = true;
                $(".status0").hidden = true;
                $(".status2").hidden = true;
                $(".status2").hidden = false;
                var $st = $(".status2");
                for (var i = 0; i < $st.length; i++) {
                    $st[i].style = null;
                }

            }
        }
    </script>

</body>

</html>