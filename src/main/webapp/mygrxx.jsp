<%@ page import="cn.itlzq.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head lang="en">
    <meta charset="utf-8" />
    <title>个人信息</title>
    <link rel="stylesheet" type="text/css" href="css/public.css" />
    <link rel="stylesheet" type="text/css" href="css/mygrxx.css" />
</head>

<body>
    <%User user = (User) session.getAttribute("user");%>
        <!------------------------------head------------------------------>
        <jsp:include page="include_header.jsp" />
        <!------------------------------idea------------------------------>
        <div class="address mt">
            <div class="wrapper clearfix"><a href="index.jsp" class="fl">首页</a><span>/</span><a href="mygxin.jsp" class="on">个人信息</a>
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
                            <li><a href="/mygxin">我的中心</a></li>
                            <li><a href="/address">地址管理</a></li>
                        </ul>
                        <h4>账户管理</h4>
                        <ul>
                            <li class="on"><a href="mygrxx.jsp">个人信息</a></li>
                            <li><a href="remima.jsp">修改密码</a></li>
                        </ul>
                    </div>
                </div>
                <div class="you fl">
                    <h2>个人信息</h2>
                    <div class="gxin">
                        <div class="tx"><a href="#"><img src="img/<%=user.getUserPhoto()%>"/>
            <p id="avatar">修改头像</p></a></div>
                        <div class="xx">
                            <h3 class="clearfix"><strong class="fl">基础资料</strong>
                                <a href="#" class="fr" id="edit1">编辑</a>
                            </h3>
                            <div>姓名：
                                <%=user.getNickName()%>
                            </div>
                            <div>邮箱：
                                <%=user.getEmail()%>
                            </div>
                            <div>手机号码：
                                <%=user.getUserphone()%>
                            </div>
                            <h3>高级设置</h3>
                            <!--<div><span class="fl">银行卡</span><a href="#" class="fr">管理</a></div>-->
                            <div><span class="fl">无</span><%--<a href="#" class="fr" id="edit2">修改</a>--%></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--遮罩-->
        <div class="mask"></div>
        <!--编辑弹框-->
        <div class="bj">
            <div class="clearfix">
                <a href="#" class="fr gb"><img src="img/icon4.png" /></a>
            </div>
            <h3>编辑基础资料</h3>
            <form action="/updateInfo" method="post">
                <p><label>姓名：</label><input id="username" name="name" type="text" value="<%=user.getNickName()%>" /></p>
                <p><label>邮箱：</label><input id="userEmail" name="email" type="text" value="<%=user.getEmail()%>"></p>
                <input name="userphone" type="hidden" value="<%=user.getUserphone()%>">
                <div class="bc">
                    <input name="" type="submit" value="保存"  />
                    <input type="button" value="取消" /></div>
            </form>
        </div>
        <!--高级设置修改-->
        <div class="xg">
            <div class="clearfix">
                <a href="#" class="fr gb"><img src="img/icon4.png" /></a>
            </div>
            <h3>切换账号地区</h3>
            <form action="#" method="get">
                <p><label>姓名：</label>
                    <input type="text" value="<%=user.getNickName()%>" />
                </p>
                <div class="bc">
                    <input type="button" value="保存" />
                    <input type="button" value="取消" />
                </div>
            </form>
        </div>
        <!--修改头像-->
        <div class="avatar">
            <div class="clearfix">
                <a href="#" class="fr gb"><img src="img/icon4.png" /></a>
            </div>
            <h3>修改头像</h3>
            <form action="#" method="get">
                <h4>请上传图片</h4><input type="button" value="上传头像" />
                <p>jpg或png，大小不超过2M</p><input type="submit" value="提交" /></form>
        </div>
    <!--返回顶部-->
    <jsp:include page="footer.jsp"/>
    <script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/user.js" type="text/javascript" charset="utf-8"></script>


</body>

</html>