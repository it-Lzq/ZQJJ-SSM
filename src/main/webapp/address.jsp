
<%@ page import="java.util.List" %>
<%@ page import="cn.itlzq.service.RegionService" %>
<%@ page import="cn.itlzq.service.AddressService" %>
<%@ page import="cn.itlzq.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head lang="en">
    <meta charset="utf-8" />
    <title>渡一</title>
    <link rel="stylesheet" type="text/css" href="css/public.css" />
    <link rel="stylesheet" type="text/css" href="css/mygxin.css" />
</head>

<body>
<%
    User u = (User) session.getAttribute("user");
    List<CnRegion> provinces = (List<CnRegion>) request.getAttribute("provinces");
    List<CnRegion> citys = (List<CnRegion>) request.getAttribute("citys");
    List<CnRegion> areas = (List<CnRegion>) request.getAttribute("areas");
    List<Address> addressList = (List<Address>) request.getAttribute("addressList");
%>
    <!------------------------------head------------------------------>
    <%@include file="include_header.jsp"%>
    <!------------------------------idea------------------------------>
    <div class="address mt">
        <div class="wrapper clearfix">
            <a href="index.jsp" class="fl">首页</a>
            <span>/</span>
            <a href="mygxin.jsp">个人中心</a>
            <span>/</span>
            <a href="address.jsp" class="on">地址管理</a></div>
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
                        <li class="on"><a href="/address">地址管理</a></li>
                    </ul>
                    <h4>账户管理</h4>
                    <ul>
                        <li><a href="mygrxx.jsp">个人信息</a></li>
                        <li><a href="remima.jsp">修改密码</a></li>
                    </ul>
                </div>
            </div>
            <div class="you fl">
                <h2>收货地址</h2>

                <div class="add">
                    <div>
                        <a href="#2" id="addxad"><img src="img/jia.png" /></a><span>添加新地址</span>
                    </div>
                    <%
                        for (Address address : addressList) {
                        %>
                    <div class="dz">
                        <p><%=address.getUserName()%></p>
                        <p><%=address.getUserPhone()%></p>
                        <p> <%
                            String provincedName = "";
                            String cityName = "";
                            String areaName = "";
                            for (CnRegion province : provinces) {
                                if (province.getCode().equals(address.getProvinceId() + ""))
                                    provincedName = province.getName();
                            }
                            for (CnRegion city : citys) {
                                if (city.getCode().equals(address.getCityId() + ""))
                                    cityName = city.getName();

                            }
                            for (CnRegion area : areas) {
                                if (area.getCode().equals(address.getAreaId() + ""))
                                    areaName = area.getName();
                            }
                        %>

                            <%=provincedName%>&nbsp;
                            <%=cityName%>&nbsp;
                            <%=areaName%>&nbsp;</p>
                        <p><%=address.getUserAddress()%></p>
                    </div>
                   <% }%>
                </div>

            </div>
        </div>
    </div>
    <!--编辑弹框-->
    <!--遮罩-->
    <div class="mask"></div>
<div class="adddz editAddre">
    <form action="addressUpdates" method="post">
        <input type="hidden"  id="aid" name="aid" value="-1">
        <input type="text" placeholder="姓名" class="on" name="username" id="username" />
        <input type="text" placeholder="手机号" name="userphone" id="userphone"/>
        <div class="city">
            <select id="select1" onchange="selectChange(this)" name="provinceCode">
                <%
                    for (CnRegion r:provinces) {
                %>
                <option value="<%=r.getCode()%>" ><%=r.getName()%></option>
                <%  } %>
            </select>
            <select id="select2" onchange="selectChange(this)" name="cityCode">
                <option value="城市/地区">城市/地区</option>
            </select>
            <select id="select3" onchange="selectChange(this)" name="areaCode">
                <option value="区/县">区/县</option>
            </select>
            <select id="select4" name="streeCode">
                <option value="配送区域">配送区域</option>
            </select>
        </div>
        <textarea name="addMessage" rows="" cols="" placeholder="详细地址"></textarea>
        <div class="bc"><input id="changeAdd"  type="submit"  value="保存" /><input type="button" value="取消" /></div>
    </form>
</div>
   <%-- <div class="adddz">
        <form action="#" method="get"><input type="text" placeholder="姓名" class="on" /><input type="text" placeholder="手机号" />
            <div class="city"><select name="">
            <option value="省份/自治区">省份/自治区</option>
        </select><select>
            <option value="城市/地区">城市/地区</option>
        </select><select>
            <option value="区/县">区/县</option>
        </select><select>
            <option value="配送区域">配送区域</option>
        </select></div>
            <textarea name="" rows="" cols="" placeholder="详细地址"></textarea><input type="text" placeholder="邮政编码" />
            <div class="bc"><input type="button" value="保存" /><input type="button" value="取消" /></div>
        </form>
    </div>--%>
    <div class="readd">
        <form action="#" method="get"><input type="text" class="on" value="六六六" /><input type="text" value="157****0022" />
            <div class="city"><select name="">
            <option value="省份/自治区">河北省</option>
        </select><select>
            <option value="城市/地区">唐山市</option>
        </select><select>
            <option value="区/县">路北区</option>
        </select><select>
            <option value="配送区域">火炬路</option>
        </select></div>
            <textarea name="" rows="" cols="" placeholder="详细地址">高新产业园</textarea><input type="text" placeholder="邮政编码" value="063000" />
            <div class="bc"><input type="button" value="保存" /><input type="button" value="取消" /></div>
        </form>
    </div>
    <!--返回顶部-->
    <%@include file="footer.jsp"%>
<script>
    //递归显示地址选择
    function selectChange(ele) {
        change(ele.value);
    }
    function change(code){
        $.post("getRegions","code="+code,function (result) {
            if(result.status == 200){
                var citys = result.citys;
                var select = document.getElementById("select"+result.level);
                select.options.length = 0;
                for (var i = 0; i < citys.length; i++) {
                    var option = document.createElement("option");
                    option.innerText = citys[i].name;
                    option.value = citys[i].code;
                    select.appendChild(option);
                }
                if(result.level != 4) change(citys[0].code);
            }
        })
    }
</script>
    <script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/user.js" type="text/javascript" charset="utf-8"></script>
</body>

</html>