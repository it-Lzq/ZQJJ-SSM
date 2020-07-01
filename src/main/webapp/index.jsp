<%@ page import="cn.itlzq.model.User" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="cn.itlzq.util.DataUtil" %>
<%@ page import="cn.itlzq.model.GoodsClass" %>
<%@ page import="cn.itlzq.service.GoodsClassService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.xml.transform.Source" %>
<%@ page import="cn.itlzq.model.Goods" %>
<%@ page import="com.google.gson.Gson" %>
<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>在线销售平台</title>
    <link href="css/index.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/public.css" />
    <link rel="stylesheet" href="css/proList.css">
    <link rel="stylesheet" href="css/layui.css">
</head>
<body>
<%
    //从缓存中取出所有分类数据
    List<Goods> goodsList  = (List<Goods>) request.getAttribute("goodsList");
    GoodsClass goodsClassShow = new GoodsClass(0,0,"All Goods");
    GoodsClass goodsClassShow2 = new GoodsClass(0,0,"");
    String type="0";
%>
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
<!------------------------------banner------------------------------>
<div class="banner">
    <a href="#"><img src="img/temp/banner1.jpg" /></a>
</div>
<!-----------------address------------------------------->
<%--<div class="address">--%>
<%--    <div class="wrapper clearfix"><a href="index.jsp">首页</a><span>/</span>--%>
<%--        <a href="flowerDer.jsp?type=<%=type%>&classId1=<%=goodsClassShow.getId()%>&goodsName=<%=goodsClassShow.getClassName()%>" class="on">--%>
<%--            <%=goodsClassShow.getClassName()%>--%>
<%--        </a>--%>
<%--    </div>--%>
<%--</div>--%>
<!-------------------current---------------------->
<div class="current">
    <div class="wrapper clearfix">
        <h3 class="fl">
            <a href="/findGoodsClass?type=<%=type%>&classId2=<%=goodsClassShow2.getId()%>">
                <%=goodsClassShow2.getClassName()%>
            </a>
        </h3>

        <div class="fr choice ">
    <%--        <li class="layui-nav-item">
                <a href="javascript:;">解决方案</a>
                <span class="layui-nav-more"></span>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">修改信息</a></dd>
                    <dd><a href="javascript:;">安全管理</a></dd>
                    <dd><a href="javascript:;">退了</a></dd>
                </dl>
            </li>--%>
            <p class="default">排序方式</p>
            <ul class="select">
                <li onclick="orderBy(1)">价格从高到低</li>
                <li onclick="orderBy(0)">价格从低到高</li>
                <script>
                    function orderBy(type) {
                        var url = window.location.href;
                        url = url.replace("&orderBy=1", "");
                        url = url.replace("&orderBy=0", "");
                        if (type == 1) {
                            url = url + "&orderBy=1";
                        } else {
                            url = url + "&orderBy=0";
                        }
                        if (url != window.location.href) {
                            window.location.replace(url);
                        }

                    }
                </script>
            </ul>
        </div>
    </div>
</div>
<!----------------proList------------------------->
<ul class="proList wrapper clearfix">

    <%

        if(goodsList == null || goodsList.size() == 0){
            //没有商品
    %>
    <h1 align="center">很遗憾, 商品不存在</h1>
    <%
    }else{
        for(Goods g:goodsList){

            //是一个JSON格式的数组
            String imgs = g.getImgs();//['xxx.jpg','xxxx.jpg']
            String imgPath = null;
            if("[".equals(imgs)){
                imgPath = "errorGoodsImg.jpg";
            }else{
                Gson gson = new Gson();
                List<String> imgsList = gson.fromJson(imgs, List.class);
                imgPath = imgsList.get(0);
            }



    %>
    <li>
        <a href="/findGoods?goodsId=<%=g.getId()%>">
            <dl>
                <dt><img src="img/imgs/<%=imgPath%>"></dt>
                <dd>
                    <%
                        if(g.getName().length() > 20){
                    %>
                    <%=g.getName().substring(0,20)+"..."%>
                    <%}else{%>
                    <%=g.getName()%>
                    <%}%>
                </dd>
                <dd>￥
                    <%=g.getPrice()%>
                </dd>
            </dl></a>
    </li>
    <%

            }

        }
    %>




</ul>
<!----------------mask------------------->
<div class="mask"></div>
<!-------------------mask内容------------------->
<div class="proDets"><img class="off" src="img/temp/off.jpg" />
    <div class="tit clearfix">
        <h4 class="fl">【渡一】非洲菊仿真花干花</h4><span class="fr">￥17.90</span></div>
    <div class="proCon clearfix">
        <div class="proImg fl"><img class="list" src="img/temp/proDet.jpg" />
            <div class="smallImg clearfix"><img src="img/temp/proDet01.jpg" data-src="img/temp/proDet01_big.jpg"><img src="img/temp/proDet02.jpg" data-src="img/temp/proDet02_big.jpg"><img src="img/temp/proDet03.jpg" data-src="img/temp/proDet03_big.jpg">
                <img
                        src="img/temp/proDet04.jpg" data-src="img/temp/proDet04_big.jpg"></div>
        </div>
        <div class="fr">
            <div class="proIntro">
                <p>颜色分类</p>
                <div class="smallImg clearfix categ">
                    <p class="fl"><img src="img/temp/prosmall01.jpg" alt="白瓷花瓶+20支快乐花" data-src="img/temp/proBig01.jpg"></p>
                    <p class="fl"><img src="img/temp/prosmall02.jpg" alt="白瓷花瓶+20支兔尾巴草" data-src="img/temp/proBig02.jpg"></p>
                    <p class="fl"><img src="img/temp/prosmall03.jpg" alt="20支快乐花" data-src="img/temp/proBig03.jpg"></p>
                    <p class="fl"><img src="img/temp/prosmall04.jpg" alt="20支兔尾巴草" data-src="img/temp/proBig04.jpg"></p>
                </div>
                <p>数量</p>
                <div class="num clearfix"><img class="fl sub" src="img/temp/sub.jpg"><span class="fl" contentEditable="true">1</span><img class="fl add" src="img/temp/add.jpg">
                    <p class="please fl">请选择商品属性!</p>
                </div>
            </div>
            <div class="btns clearfix">
                <a href="#2">
                    <p class="buy fl">立即购买</p>
                </a>
                <a href="#2">
                    <p class="cart fr">
                        加入购物车</p>
                </a>
            </div>
        </div>
    </div>
    <a class="more" href="proDetail.html">查看更多细节</a></div>
<jsp:include page="footer.jsp"/>
<script src="js/jquery-1.12.4.min.js"></script>
<script  src="js/public.js"></script>
<script src="js/layui.all.js"></script>
<script src="js/pro.js" type="text/javascript" charset="utf-8"></script>
<script src="js/cart.js" type="text/javascript" charset="utf-8"></script>
<script>
    layui.use('element', function(){
        var element = layui.element;
    });
</script>
</body>
</html>