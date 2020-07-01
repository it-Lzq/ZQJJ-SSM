<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
</head>
<body><!-------------------reg-------------------------->
<div class="reg">
    <form method="post"><h1><a href="/index">在线销售</a></h1>
        <p>用户注册</p>
        <p><input id="userPhone" type="text" name=""  placeholder="请输入手机号码"></p>
        <p><input id="password1" type="password" name=""  placeholder="请输入密码"></p>
        <p><input id="password2" type="password" name=""  placeholder="请确认密码"></p>
        <p class="txtL txt">
            <input id="smsCode" class="code" type="text" name="" value="" placeholder="验证码" class="smsinput">
            <span class="sendsms" onclick="sendSms(this)" title="点击获取验证码">获取验证码</span>
        </p>
        <p><input name="" type="button"  value="注册" onclick="reg()"></p>
        <p class="txtL txt">完成此注册，即表明您同意了我们的
            <a href="#"> < 使用条款和隐私策略 > </a>
        </p>
        <p class="txt"><a href="login.jsp"><span></span>已有账号登录</a></p>
        <!--<a href="#" class="off"><img src="img/temp/off.png"></a>--></form>
    <script src="js/jquery-1.12.4.min.js"></script>
    <script src="js/layer/layer.js"></script>
    <script>
        var smsButtonflag = true;
        var userphone = document.getElementById("userPhone");
        var password1 = document.getElementById("password1");
        var password2 = document.getElementById("password2");
        var smsCode = document.getElementById("smsCode");

        function sendSms(span){
            if(smsButtonflag){
                var phoneRe = /^1(3[0-9]|47|5[012356789]|66|7[0-9]|8[0-9]|9[89])[0-9]{8}$/g;
                if(!phoneRe.test(userPhone.value)){
                    layer.msg("手机号码有误, 请检查");
                    return;
                }

                //1.    更改按钮标记， 不允许再次点击
                smsButtonflag = false;
                //2.    改变样式
                span.style.color = "#999";
                span.title = "请等待倒计时结束 ，再次获取";
                var s = 30;
                var iv = setInterval(function(){
                    s--;
                    span.innerHTML = s+"s";
                    if(s == 0){
                        smsButtonflag = true;
                        clearInterval(iv);
                        span.style.color = "";
                        span.title = "点击获取验证码";
                        span.innerHTML = "获取验证码";
                    }
                },1000);
                //3.    发送短信
                //3.1   先转圈
                layer.load();
                $.post("sms","status=1&userPhone="+userPhone.value,function(data){
                    layer.closeAll();
                    //data: {status:200|-1,msg:"发送成功|发送失败"}
                   layer.msg(data.msg);

                });
            }
        }

        function reg(){
            var pass1 = password1.value;
            var pass2 = password2.value;
            if(pass1 != pass2){
                layer.msg("两次密码输入不一致");
                return;
            }

            layer.load();
            $.post("reg","userPhone="+userphone.value+"&password="+pass1+"&smsCode="+smsCode.value,function(data){
                layer.closeAll();
                layer.msg(data.msg);
                layer.msg("页面即将跳转到登录");
                setTimeout(function(){
                    window.location.href="/tologin";
                },1000);


            });
        }
    </script>

</div>
</body>
</html>