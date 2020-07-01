<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head lang="en">
    <meta charset="utf-8" />
    <title>个人信息</title>
    <link rel="stylesheet" type="text/css" href="css/public.css" />
    <link rel="stylesheet" type="text/css" href="css/mygrxx.css" />
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
</head>

<body>
    <% User u = (User)session.getAttribute("user");%>
    <!------------------------------head------------------------------>
    <%@include file="include_header.jsp"%>
    <!------------------------------idea------------------------------>
    <div class="address mt">
        <div class="wrapper clearfix">
            <a href="index.jsp" class="fl">首页</a>
            <span>/</span>
            <a href="mygxin.jsp" class="on">个人信息</a>
        </div>
    </div>
    <!------------------------------Bott------------------------------>
    <div class="Bott">
        <div class="wrapper clearfix">
            <div class="zuo fl">
                <h3>
                    <a href="#"><img src="img/<%=u.getUserPhoto()%>" /></a>
                    <p class="clearfix"><span class="fl"><%=u.getNickName()%></span>
                        <span class="fr" onclick="quit()" >[退出登录]</span></p>
                    <script type="text/javascript">
                        function quit() {
                            $.post("quitUser",function (data) {
                                layer.msg(data.msg);
                                location.href = "/index"
                            });

                        }
                    </script></p>
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
                        <li><a href="mygrxx.jsp">个人信息</a></li>
                        <li class="on"><a href="remima.jsp">修改密码</a></li>
                    </ul>
                </div>
            </div>
            <div class="you fl">
                <h2>修改密码</h2>
                <form  method="get" class="remima">
                    <p><span>绑定手机号：</span><%=u.getUserphone()%></p>
                    <p><span>原密码：</span><input type="text" id="oldPass" value="" /></p>
                    <p class="op">输入原密码</p>
                    <p><span>新密码：</span><input type="password" id="newPass1" value=""/></p>
                    <p class="op">6-16 个字符，需使用字母、数字或符号组合，不能使用纯数字、纯字母、纯符号</p>
                    <p><span>重复新密码：</span><input type="password" id="newPass2" value="" /></p>
                    <p class="op">请再次输入密码</p>
                    <p><span>验证码：</span>
                        <input type="text" id="code" value="" />
                        <button type="button" onclick="sendSms(this)" class="btn btn-primary" value="获取验证码">获取验证码</button>
                    </p>
                    <p class="op">请注意查收短信</p><input type="button"  class="btn btn-success" value="提交" onclick="changePass()" /></form>
            </div>
        </div>
    </div>
    <!--返回顶部-->
   <%@include file="footer.jsp"%>
    <script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/layer/layer.js" type="text/javascript" charset="UTF-8"></script>
    <script src="js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/user.js" type="text/javascript" charset="utf-8"></script>
    <script>
        var smsButtonflag = true;
        var $oldPass = document.getElementById("oldPass");
        var $newPass1 = document.getElementById("newPass1");
        var $newPass2 = document.getElementById("newPass2");
        var $code = document.getElementById("code");
        var realCode = "";
        function sendSms(span) {
            if(smsButtonflag){
                //1.    更改按钮标记， 不允许再次点击
                smsButtonflag = false;
                //2.    改变样式
                span.style.color = "#999";
                span.title = "请等待倒计时结束 ，再次获取";
                var s = 30;
                var timer = setInterval(function () {
                    s--;
                    span.innerHTML = s+"s";
                    if(s == 0){
                        smsButtonflag = true;
                        clearInterval(timer);
                        span.style.color = "";
                        span.title = "点击获取验证码";
                        span.innerHTML = "获取验证码";
                    }
                },1000);
                //3.    发送短信
                //3.1   先转圈
                layer.load();
                $.post("sms","status=1&userPhone="+<%=u.getUserphone()%>,function(data){
                    layer.closeAll();
                    //data: {status:200|-1,msg:"发送成功|发送失败"}
                    layer.msg(data.msg);
                    realCode = data.realCode;
                });
            }
        }
        function changePass() {
            if("" == $oldPass.value ){
                layer.msg("请输入密码");return;
            }
            if("" == $newPass1.value  &&"" ==  $newPass2.value){
                layer.msg("请输入新密码");return;
            }
            if(""== $code.value ){
                layer.msg("请输入验证码");return;
            }
            if($oldPass.value != '<%=u.getPassword()%>'){
                layer.msg("密码输入错误");
                return;
            }
            if($newPass1.value != $newPass2.value) {
                layer.msg("两次密码输入不一致");
                return;
            }
            if(realCode != $code.value){
                layer.msg("验证码输入错误");
                return;
            }
            $.post("updatePass","pass="+$newPass1.value,function (data) {
                layer.msg(data.msg);
                 $oldPass.value = "";
                 $newPass1.value = "";
                 $newPass2.value = "";
                 $code.value = "";
                 location.href = "/mygxin";
            })
        }
    </script>
</body>

</html>