

<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.itlzq.model.Goods" %>
<%@ page import="cn.itlzq.model.GoodsClass" %>
<%@ page import="cn.itlzq.model.User" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="cn.itlzq.util.DataUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Goods goods = (Goods) request.getAttribute("goods");
    GoodsClass class1 = (GoodsClass) request.getAttribute("class1");
    GoodsClass class2 = (GoodsClass) request.getAttribute("class2");

    if(goods == null || class1 == null || class2 == null){

        response.sendError(404,"哈哈哈, 商品找不到了.");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= goods.getName()%></title>
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/proList.css"/>
    <script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/jquery.SuperSlide.2.1.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/nav.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/pro.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/cart.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/layer/layer.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body><!------------------------------head------------------------------>
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
                List<HashMap<String, Object>> classes1 = (List<HashMap<String, Object>>) DataUtil.data.get("class");
                for (int i = 0; i < classes1.size(); i++) {
                    HashMap<String, Object> classMap = classes1.get(i);
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
<!-----------------address------------------------------->
<div class="address">
    <div class="wrapper clearfix"><a href="back/index.jsp">首页</a><span>/</span><a
            href="#"><%=class1.getClassName()%></a><span>/</span>
        <a href="#"><%=class2.getClassName()%></a><span>/</span>
        <a class="on">【泽庆】<%= goods.getName()%></a>
    </div>
</div><!-----------------------Detail------------------------------>
<div class="detCon">
    <div class="proDet wrapper">
        <div class="proCon clearfix">
            <div class="proImg fl">
                <%
                    //商品图片
                    String imgs = goods.getImgs();//['xxx.jpg','xxxx.jpg']
                    List<String> imgList = null;
                    if("[".equals(imgs)){
                        imgList = new ArrayList<>();
                        imgList.add("errorGoodsImg.jpg");
                    }else{
                        Gson gson = new Gson();
                        imgList = gson.fromJson(imgs, List.class);
                    }
                %>

                <img class="det" src="img/imgs/<%=imgList.get(0)%>"/>
                <div class="smallImg clearfix">
                    <%  //1.jpg
                        for(String imgPath:imgList){
                    %>
                    <img src="img/small/<%=imgPath%>" data-src="img/imgs/<%=imgPath%>">
                    <%
                        }
                    %>
                </div>
            </div>
            <div class="fr intro">
                <div class="title"><h4>【泽庆】<%=goods.getName()%></h4>
                    <p><%=goods.getGdesc()%></p>
                    <span>￥<%=goods.getPrice()%></span></div>
                <div class="proIntro">
                    <p>数量&nbsp;&nbsp;库存<span><%=goods.getStock()%></span>件</p>
                    <div class="num clearfix">
                        <img class="fl sub" src="img/temp/sub.jpg">
                        <span class="fl" id="addNum" contentEditable="true">1</span>
                        <img class="fl add" src="img/temp/add.jpg">
                        <p class="please fl">请选择商品属性!</p></div>
                </div>
                <div class="btns clearfix">
                    <a  href="javascript:putOrder(<%=goods.getId()%>)"><p  class="buy fl">立即购买</p></a>
                    <a href="javascript:add()"><p class="cart fr">加入购物车</p></a>
                    <script>
                        function putOrder(id) {
                            var num = $("#addNum").html();
                            location.href = "/putOrder?goodsId="+id+"&num="+num;
                            // $.post("putOrder.do","id="+id+"&num="+num,function (date) {
                            //
                            // })
                        }

                        function add(){
                            layer.load();
                            var num = $("#addNum").html();
                            $.post("addCart","goodsId=<%=goods.getId()%>&num="+num,function (data) {
                                layer.closeAll();
                                layer.msg(data.msg);
                                var $countSpan = $(".goCart>span");
                                if($countSpan.length == 0){
                                    $countSpan = $("<span>"+num+"</span>");
                                    $(".goCart").append($countSpan);
                                }else{
                                    var spanNum = Number($countSpan.html())+Number(num);
                                    $countSpan.html(spanNum);
                                }
                            })
                        }
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="introMsg wrapper clearfix">
    <div class="msgL fl">
        <div class="msgTit clearfix"><a class="on">商品详情</a><a>所有评价</a></div>
        <div class="msgAll">
            <div class="msgImgs">
                <%
                    //商品图片
                    String imgs2 = goods.getInfo();//['xxx.jpg','xxxx.jpg']
                    List<String> imgList2 = null;
                    if("[".equals(imgs2)){
                        imgList2 = new ArrayList<>();
                        imgList2.add("errorGoodsImg.jpg");
                    }else{
                        Gson gson = new Gson();
                        imgList2 = gson.fromJson(imgs2, List.class);
                    }
                    for(String imgPath:imgList2){
                %>
                <img src="img/imgs/<%=imgPath%>">
                <%
                    }
                %>


            </div>
            <div class="eva">
                <div class="per clearfix"><img class="fl" src="img/temp/per01.jpg">
                    <div class="perR fl"><p>馨***呀</p>
                        <p>不好意思评价晚了，产品很好，价格比玻璃品便宜，没有我担心的杂色，发货快，包装好，全5分</p>
                        <div class="clearfix"><p><img src="img/temp/eva01.jpg"></p>
                            <p><img src="img/temp/eva02.jpg"></p>
                            <p><img src="img/temp/eva03.jpg"></p>
                            <p><img src="img/temp/eva04.jpg"></p>
                            <p><img src="img/temp/eva05.jpg"></p></div>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p></div>
                </div>
                <div class="per clearfix"><img class="fl" src="img/temp/per02.jpg">
                    <div class="perR fl"><p>么***周</p>
                        <p>花瓶超级棒，我看图以为是光面的，收货发现是磨砂，但感觉也超有质感，很喜欢。磨砂上面还有点纹路，不过觉得挺自然的，不影响美观。包装也很好，绝对不会磕碎碰坏，好评！</p>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p></div>
                </div>
                <div class="per clearfix"><img class="fl" src="img/temp/per01.jpg">
                    <div class="perR fl"><p>馨***呀</p>
                        <p>不好意思评价晚了，产品很好，价格比玻璃品便宜，没有我担心的杂色，发货快，包装好，全5分</p>
                        <div class="clearfix"><p><img src="img/temp/eva01.jpg"></p>
                            <p><img src="img/temp/eva02.jpg"></p>
                            <p><img src="img/temp/eva03.jpg"></p>
                            <p><img src="img/temp/eva04.jpg"></p>
                            <p><img src="img/temp/eva05.jpg"></p></div>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p></div>
                </div>
                <div class="per clearfix"><img class="fl" src="img/temp/per02.jpg">
                    <div class="perR fl"><p>么***周</p>
                        <p>花瓶超级棒，我看图以为是光面的，收货发现是磨砂，但感觉也超有质感，很喜欢。磨砂上面还有点纹路，不过觉得挺自然的，不影响美观。包装也很好，绝对不会磕碎碰坏，好评！</p>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p></div>
                </div>
                <div class="per clearfix"><img class="fl" src="img/temp/per01.jpg">
                    <div class="perR fl"><p>馨***呀</p>
                        <p>不好意思评价晚了，产品很好，价格比玻璃品便宜，没有我担心的杂色，发货快，包装好，全5分</p>
                        <div class="clearfix"><p><img src="img/temp/eva01.jpg"></p>
                            <p><img src="img/temp/eva02.jpg"></p>
                            <p><img src="img/temp/eva03.jpg"></p>
                            <p><img src="img/temp/eva04.jpg"></p>
                            <p><img src="img/temp/eva05.jpg"></p></div>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p></div>
                </div>
                <div class="per clearfix"><img class="fl" src="img/temp/per02.jpg">
                    <div class="perR fl"><p>么***周</p>
                        <p>花瓶超级棒，我看图以为是光面的，收货发现是磨砂，但感觉也超有质感，很喜欢。磨砂上面还有点纹路，不过觉得挺自然的，不影响美观。包装也很好，绝对不会磕碎碰坏，好评！</p>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p></div>
                </div>
                <div class="per clearfix"><img class="fl" src="img/temp/per01.jpg">
                    <div class="perR fl"><p>馨***呀</p>
                        <p>不好意思评价晚了，产品很好，价格比玻璃品便宜，没有我担心的杂色，发货快，包装好，全5分</p>
                        <div class="clearfix"><p><img src="img/temp/eva01.jpg"></p>
                            <p><img src="img/temp/eva02.jpg"></p>
                            <p><img src="img/temp/eva03.jpg"></p>
                            <p><img src="img/temp/eva04.jpg"></p>
                            <p><img src="img/temp/eva05.jpg"></p></div>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p></div>
                </div>
                <div class="per clearfix"><img class="fl" src="img/temp/per02.jpg">
                    <div class="perR fl"><p>么***周</p>
                        <p>花瓶超级棒，我看图以为是光面的，收货发现是磨砂，但感觉也超有质感，很喜欢。磨砂上面还有点纹路，不过觉得挺自然的，不影响美观。包装也很好，绝对不会磕碎碰坏，好评！</p>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p></div>
                </div>
                <div class="per clearfix"><img class="fl" src="img/temp/per01.jpg">
                    <div class="perR fl"><p>馨***呀</p>
                        <p>不好意思评价晚了，产品很好，价格比玻璃品便宜，没有我担心的杂色，发货快，包装好，全5分</p>
                        <div class="clearfix"><p><img src="img/temp/eva01.jpg"></p>
                            <p><img src="img/temp/eva02.jpg"></p>
                            <p><img src="img/temp/eva03.jpg"></p>
                            <p><img src="img/temp/eva04.jpg"></p>
                            <p><img src="img/temp/eva05.jpg"></p></div>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p></div>
                </div>
                <div class="per clearfix"><img class="fl" src="img/temp/per02.jpg">
                    <div class="perR fl"><p>么***周</p>
                        <p>花瓶超级棒，我看图以为是光面的，收货发现是磨砂，但感觉也超有质感，很喜欢。磨砂上面还有点纹路，不过觉得挺自然的，不影响美观。包装也很好，绝对不会磕碎碰坏，好评！</p>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p></div>
                </div>
            </div>
        </div>
    </div>
    <div class="msgR fr"><h4>为你推荐</h4>
        <div class="seeList"><a href="#">
            <dl>
                <dt><img src="img/temp/see01.jpg"></dt>
                <dd>【渡一】复古文艺风玻璃花瓶</dd>
                <dd>￥193.20</dd>
            </dl>
        </a><a href="#">
            <dl>
                <dt><img src="img/temp/see02.jpg"></dt>
                <dd>【渡一】复古文艺风玻璃花瓶</dd>
                <dd>￥193.20</dd>
            </dl>
        </a><a href="#">
            <dl>
                <dt><img src="img/temp/see03.jpg"></dt>
                <dd>【渡一】复古文艺风玻璃花瓶</dd>
                <dd>￥193.20</dd>
            </dl>
        </a><a href="#">
            <dl>
                <dt><img src="img/temp/see04.jpg"></dt>
                <dd>【渡一】复古文艺风玻璃花瓶</dd>
                <dd>￥193.20</dd>
            </dl>
        </a></div>
    </div>
</div>
<div class="like"><h4>猜你喜欢</h4>
    <div class="bottom">
        <div class="hd"><span class="prev"><img src="img/temp/prev.png"></span><span class="next"><img
                src="img/temp/next.png"></span></div>
        <div class="imgCon bd">
            <div class="likeList clearfix">
                <div><a href="proDetail.html">
                    <dl>
                        <dt><img src="img/temp/like01.jpg"></dt>
                        <dd>【渡一】复古文艺风玻璃花瓶</dd>
                        <dd>￥193.20</dd>
                    </dl>
                </a><a href="proDetail.html">
                    <dl>
                        <dt><img src="img/temp/like02.jpg"></dt>
                        <dd>【渡一】复古文艺风玻璃花瓶</dd>
                        <dd>￥193.20</dd>
                    </dl>
                </a><a href="proDetail.html">
                    <dl>
                        <dt><img src="img/temp/like03.jpg"></dt>
                        <dd>【渡一】复古文艺风玻璃花瓶</dd>
                        <dd>￥193.20</dd>
                    </dl>
                </a><a href="proDetail.html">
                    <dl>
                        <dt><img src="img/temp/like04.jpg"></dt>
                        <dd>【渡一】复古文艺风玻璃花瓶</dd>
                        <dd>￥193.20</dd>
                    </dl>
                </a><a href="proDetail.html" class="last">
                    <dl>
                        <dt><img src="img/temp/like05.jpg"></dt>
                        <dd>【渡一】复古文艺风玻璃花瓶</dd>
                        <dd>￥193.20</dd>
                    </dl>
                </a></div>
                <div><a href="proDetail.html">
                    <dl>
                        <dt><img src="img/temp/like01.jpg"></dt>
                        <dd>【渡一】复古文艺风玻璃花瓶</dd>
                        <dd>￥193.20</dd>
                    </dl>
                </a><a href="proDetail.html">
                    <dl>
                        <dt><img src="img/temp/like02.jpg"></dt>
                        <dd>【渡一】复古文艺风玻璃花瓶</dd>
                        <dd>￥193.20</dd>
                    </dl>
                </a><a href="proDetail.html">
                    <dl>
                        <dt><img src="img/temp/like03.jpg"></dt>
                        <dd>【渡一】复古文艺风玻璃花瓶</dd>
                        <dd>￥193.20</dd>
                    </dl>
                </a><a href="proDetail.html">
                    <dl>
                        <dt><img src="img/temp/like04.jpg"></dt>
                        <dd>【渡一】复古文艺风玻璃花瓶</dd>
                        <dd>￥193.20</dd>
                    </dl>
                </a><a href="proDetail.html" class="last">
                    <dl>
                        <dt><img src="img/temp/like05.jpg"></dt>
                        <dd>【渡一】复古文艺风玻璃花瓶</dd>
                        <dd>￥193.20</dd>
                    </dl>
                </a></div>
            </div>
        </div>
    </div>
</div>
<!--返回顶部-->
<jsp:include page="footer.jsp"/>

<script type="text/javascript">jQuery(".bottom").slide({
    titCell: ".hd ul",
    mainCell: ".bd .likeList",
    autoPage: true,
    autoPlay: false,
    effect: "leftLoop",
    autoPlay: true,
    vis: 1
});</script>
</body>
</html>

