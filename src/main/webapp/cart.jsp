
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="cn.itlzq.model.Carts" %>
<%@ page import="cn.itlzq.model.Goods" %>
<%@ page import="cn.itlzq.service.GoodsService" %>
<%@ page import="cn.itlzq.service.impl.SqlCartService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="utf-8" />
    <title>cart</title>
    <link rel="stylesheet" type="text/css" href="css/public.css" />
    <link rel="stylesheet" type="text/css" href="css/proList.css" />
    <script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/layer/layer.js" type="text/javascript"></script>
    <script src="js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/pro.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/cart.js" type="text/javascript" charset="utf-8"></script>
</head>

<body>
    <!--------------------------------------cart--------------------->
    <%@include file="include_header.jsp"%>
    <!------------------------------head----------------------------->
    <%
        List<Carts> cartsList = (List<Carts>) request.getAttribute("cartsList");
        List<Goods> goodsList = (List<Goods>) request.getAttribute("goodsList");

    %>
    <div class="cart mt">
        <!-----------------logo------------------->
        <!--<div class="logo"><h1 class="wrapper clearfix"><a href="index.jsp"><img class="fl" src="img/temp/logo.png"></a><img class="top" src="img/temp/cartTop01.png"></h1></div>-->
        <!-----------------site------------------->
        <div class="site">
            <p class=" wrapper clearfix"><span class="fl">购物车</span><img class="top" src="img/temp/cartTop01.png"><a href="index.jsp" class="fr">继续购物&gt;</a></p>
        </div>
        <!-----------------table------------------->
        <div class="table wrapper">
            <%
                //如果购物车为空则不显示购物车列表
                if(cartsList == null || cartsList.size() == 0 || goodsList.size()==0 || goodsList == null){
            %><div class="goOn">空空如也~<a href="index.jsp">去逛逛</a></div>
            <%}else{%>
            <div class="tr">
                <div>商品</div>
                <div>单价</div>
                <div>数量</div>
                <div>小计</div>
                <div>操作</div>
            </div>
            <%
                List<Goods> goodsList1 = new ArrayList<>();
                for (int i = 0; i < cartsList.size(); i++) {
                    Carts c = cartsList.get(i);
                    for (int j = 0; j < goodsList.size(); j++) {
                        if(c.getGoodsId() == goodsList.get(j).getId()){
                            goodsList1.add(goodsList.get(j));
                            break;
                        }
                    }
                }
                goodsList = goodsList1;
                double priceAll = 0.00;
                for (int i = goodsList.size()-1; i >=0; i--) {
                    Carts carts = cartsList.get(i);
                    Goods goods = goodsList.get(i);
                    String img = "errorGoodsImg.jpg" ;
                    Gson g = new Gson();
                    if("[".equals(goods.getImgs())){
                        img = "errorGoodsImg.jpg";
                    }else{
                        List imgs = g.fromJson(goods.getImgs(),List.class);
                        img = (String)imgs.get(0);
                    }

//                    System.out.println(img);
            %>
<%--        遍历购物车中的商品--%>
            <div class="th">
                <div class="pro clearfix"><label class="fl">
                    <input type="checkbox" value="<%=goods.getId()%>"  onclick="check(this,<%=goods.getId()%>)" <%=carts.getIsCheck()==1?"checked":""%>/><span></span></label>
                    <a class="fl" href="#">
                        <dl class="clearfix">
                            <dt class="fl"><img width="120px" height="120px" src="img/imgs/<%=img%>"></dt>
                            <dd class="fl">
                                <p ><%=goods.getName().length()>18?goods.getName().substring(0,16)+"...":goods.getName()%></p>
                                <p>商品暂无分类</p>
                            </dd>
                        </dl>
                    </a>
                </div>
                <div class="price" style="text-align: center"><%=goods.getPrice()%></div>
                <div class="number" >
                    <p class="num clearfix">
                        <img class="fl sub" onclick="cartNumUpdate(-1,<%=goods.getId()%>,this)" src="img/temp/sub.jpg">
                        <span class="fl num"><%=carts.getCartNum()%></span>
                        <img class="fl add" onclick="cartNumUpdate(1,<%=goods.getId()%>,this)" src="img/temp/add.jpg"></p>
                </div>
                <div class="price sAll" style="text-align: center"><%="￥"+goods.getPrice()*carts.getCartNum()%></div>
                <div class="price" style="text-align: center"><a class="del" href="javascript:myDel(<%=goods.getId()%>)">删除</a></div>
            </div>
<%}%>
            <div class="tr clearfix">
                <label class="fl"><input class="checkAll" type="checkbox"/><span></span></label>
                <p class="fl"><a href="#">全选</a><a href="" class="del">删除</a></p>
                <p class="fr">
                    <span>共<small id="sl"><%=cartsList.size()%></small>件商品</span>
                    <span>合计:&nbsp;<small id="all"><%=priceAll%></small></span>
                    <a href="javascript:buyInfo()" class="count">结算</a></p>
            </div>
        </div>
    </div><%}%>
    <div class="mask"></div>
    <div class="tipDel">
        <p>确定要删除该商品吗？</p>
        <p class="clearfix"><a class="fl cer" href="javascript:myDel2()">确定</a><a class="fr cancel" href="#">取消</a></p>
    </div>
    <!--返回顶部-->
    <jsp:include page="footer.jsp"/>
    <!----------------mask------------------->
    <div class="mask"></div>
    <!-------------------mask内容------------------->
    <div class="proDets"><img class="off" src="img/temp/off.jpg" />
        <div class="proCon clearfix">
            <div class="proImg fr"><img class="list" src="img/temp/proDet.jpg" />
                <div class="smallImg clearfix"><img src="img/temp/proDet01.jpg" data-src="img/temp/proDet01_big.jpg"><img src="img/temp/proDet02.jpg" data-src="img/temp/proDet02_big.jpg"><img src="img/temp/proDet03.jpg" data-src="img/temp/proDet03_big.jpg"><img src="img/temp/proDet04.jpg"
                        data-src="img/temp/proDet04_big.jpg"></div>
            </div>
            <div class="fl">
                <div class="proIntro change">
                    <p>颜色分类</p>
                    <div class="smallImg clearfix">
                        <p class="fl on"><img src="img/temp/prosmall01.jpg" alt="白瓷花瓶+20支快乐花" data-src="img/temp/proBig01.jpg"></p>
                        <p class="fl"><img src="img/temp/prosmall02.jpg" alt="白瓷花瓶+20支兔尾巴草" data-src="img/temp/proBig02.jpg"></p>
                        <p class="fl"><img src="img/temp/prosmall03.jpg" alt="20支快乐花" data-src="img/temp/proBig03.jpg"></p>
                        <p class="fl"><img src="img/temp/prosmall04.jpg" alt="20支兔尾巴草" data-src="img/temp/proBig04.jpg"></p>
                    </div>
                </div>
                <div class="changeBtn clearfix">
                    <a href="#2" class="fl">
                        <p class="buy">确认</p>
                    </a>
                    <a href="#2" class="fr">
                        <p class="cart">取消</p>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <div class="pleaseC">
        <p>请选择宝贝</p><img class="off" src="img/temp/off.jpg" />
    </div>
    <script type="text/javascript">
        var goodsid1 = null;

        function buyInfo() {
            var $sl = document.getElementById("sl");
            var $all = document.getElementById("all");
            if($sl.innerHTML > 0){
                var idMsg = "";
                $(".th input[type='checkbox']:checked").each(function () {
                    idMsg = idMsg +this.value +"-";
                });
                idMsg =idMsg.substring(0,idMsg.length-1);

                location.href = "/putOrders?priceA="+$all.innerHTML.substring(1)+"&idMsg="+idMsg;
            }else{

                layer.msg("请选择购买商品");
            }
        }

        function myDel(goodIsd2) {

            goodsid1 = goodIsd2;
        }
        //删除
        function  myDel2(){
            $.post("delCarts","goodsId="+goodsid1,function (result) {
                    $(".goCart>span").html(result.cartsCount==null?0:result.cartsCount);
                    b();
                    a();

            })
        }

        //修改商品数量
        function cartNumUpdate(type,goodsId,element) {
                var $e = $(element).parent().find(".num");
                if($e.html() == "1" && type == "-1"){
                    return;
                }
                var $countSpan = $(".goCart>span");
                var spanNum = Number($countSpan.html()) + Number(type);
                $countSpan.html(spanNum);
                //ajax跳转到服务类
                $.post("cartNumUpdate","goodsId="+goodsId+"&cartNum="+type,function (result) {

                });

        }
        //选中商品
        function check(input,goodsId){
            $.post("cartChecked","goodsId="+goodsId+"&checked="+(input.checked?1:0),function (result) {
            });
        }

        $(function () {

            if($("#sl").text() > 0){
                $(".count").css("background","#c10000");
            }else{
                $(".count").css("background","#8e8e8e");
            }
        });
        function a() {
            var c = 0;
            var d = $(".th input[type='checkbox']:checked").length;
            if (d == 0) {
                $("#all").text("￥" + parseFloat(0).toFixed(2))
            } else {
                $(".th input[type='checkbox']:checked").each(function () {
                    var e = $(this).parents(".pro").siblings(".sAll").text().substring(1);
                    c += parseFloat(e);
                    $("#all").text("￥" + c.toFixed(2))
                })
            }
        }

        function b() {
            var e = 0;
            var c = $(".th input[type='checkbox']:checked").parents(".th").find(".num span");
            var d = c.length;
            if (d == 0) {
                $("#sl").text(0.00)
            } else {
                c.each(function () {
                    e += parseInt($(this).text());
                    $("#sl").text(e)
                })
            }
            if ($("#sl").text() > 0) {
                $(".count").css("background", "#c10000")
            } else {
                $(".count").css("background", "#8e8e8e")
            }
        }


    </script>

</body>

</html>