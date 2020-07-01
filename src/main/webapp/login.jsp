<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="css/public.css" />
    <link rel="stylesheet" type="text/css" href="css/login.css" />
</head>

<body>
    <!-------------------login-------------------------->
    <div class="login">
        <form method="post">
            <h1>
                <a href="/index">在线销售平台</a>
            </h1>
            <p></p>
            <div class="msg-warn hide"><b></b>公共场所不建议自动登录，以防账号丢失</div>
            <p><input type="text" name="userPhone" id="userPhone" placeholder="昵称/邮箱/手机号"></p>
            <p><input type="password" name="password" id="password" placeholder="密码"></p>
            <p><input name="" type="button" value="登  录" onclick="login()"></p>
            <p class="txt"><a class="" href="/toreg">免费注册</a><a href="#">忘记密码？</a></p>
        </form>
    </div>
    <script src="js/jquery-1.12.4.min.js"></script>
    <script src="js/layer/layer.js"></script>
    <script>
        var userPhone = document.getElementById("userPhone");
        var password = document.getElementById("password");

        function login() {

            layer.load();
            $.post("login", "userPhone=" + userPhone.value + "&password=" + password.value, function(data) {
                if (data.status == 200) {
                    layer.closeAll();
                    layer.msg("登录成功");
                    setTimeout(function() {
                        window.location.href = "/index";
                    }, 1000);
                } else {
                    layer.closeAll();
                    layer.msg("用户名或密码错误，请重新登录");
                }
            });
        }
    </script>

</body>

</html>