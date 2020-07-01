<%@ page import="cn.itlzq.model.Goods" %>
<%@ page import="cn.itlzq.model.GoodsClass" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="cn.itlzq.util.DataUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>在线销售管理-后台</title>
	<link rel="stylesheet" href="../css/back/layui.css">
	<link rel="stylesheet" href="../css/back/public.css">
</head>
<%
    //从缓存中取出所有分类数据
    List<Goods> goodsList  = (List<Goods>) request.getAttribute("goodsList");
    String cname = (String) request.getAttribute("cname");
     List<HashMap<String, Object>> class1 = (List<HashMap<String, Object>>) DataUtil.data.get("class");
%>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<div class="layui-logo">在线销售后台</div>
		<!-- 头部区域（可配合layui已有的水平导航） -->
		<ul class="layui-nav layui-layout-left">
			<li class="layui-nav-item"><a href="">控制台</a></li>
		</ul>
		<ul class="layui-nav layui-layout-right">
			<li class="layui-nav-item">
				<a href="javascript:;">
					<img src="http://t.cn/RCzsdCq" class="layui-nav-img">
					泽庆
				</a>
				<dl class="layui-nav-child">
					<dd><a href="">基本资料</a></dd>
					<dd><a href="">安全设置</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item"><a href="">退出</a></li>
		</ul>
	</div>

	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<ul class="layui-nav layui-nav-tree"  lay-filter="test">
				<li class="layui-nav-item layui-nav-itemed layui-this">
					<a class="" href="/admin" >商品信息</a>
				</li>
				<li class="layui-nav-item">
					<a href="/admin/toOrder">订单管理</a>
				</li>
				<li class="layui-nav-item"><a href="/admin/user">用户管理</a></li>
			</ul>
		</div>
	</div>

	<div class="layui-body">
		<!-- 内容主体区域 -->
		<form class="layui-form">
			<blockquote class="layui-elem-quote quoteBox">
				<form class="layui-form ">
					<div class="layui-form-item ">
						<label class="layui-form-label">分类</label>
						<div class="layui-input-block">
							<select class="layui-input " style="display: block" onchange="selectClass(this)"   lay-verify="required">
								<option value="-1">
									<%=cname==null?"请选择分类":cname%>
								</option>
								<option value="0">
									所有分类
								</option>
								<%
									for (int i = 0; i < class1.size(); i++) {
										HashMap<String, Object> classMap = class1.get(i);
										GoodsClass cla1 = (GoodsClass) classMap.get("class1");
										List<GoodsClass> cla2List = (List<GoodsClass>) classMap.get("class2");
										for (GoodsClass goodsClass : cla2List) {
								%>
								<option value="<%=goodsClass.getId()%>">
									<%=goodsClass.getClassName()%>
								</option>
								<%}}%>

							</select>
						</div>
					</div>
				</form>
			</blockquote>
			<table class="layui-table " style="margin: 0 10px">
				<colgroup>
					<col width="150">
					<col width="200">
					<col>
				</colgroup>
				<thead>
				<tr>
					<th>ID</th>
					<th>名称</th>
					<th>价格</th>
					<th>库存</th>

				</tr>
				</thead>
				<tbody>
				<%
					for (Goods g : goodsList) {
				%>
				<tr>
					<td><%=g.getId()%></td>
					<td style="width: 300px"><%=g.getName()%></td>
					<td><%=g.getPrice()%></td>
					<td><%=g.getStock()%></td>

				</tr>

				<%}%>

				</tbody>
			</table>
			<!--审核状态-->
			<script type="text/html" id="newsStatus">
				{{#  if(d.newsStatus == "1"){ }}
				<span class="layui-red">等待审核</span>
				{{#  } else if(d.newsStatus == "0"){ }}
				<span class="layui-blue">已存草稿</span>
				{{#  } else { }}
				审核通过
				{{#  }}}
			</script>

			<!--操作-->
			<script type="text/html" id="newsListBar">
				<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
				<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
				<a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="look">预览</a>
			</script>
		</form>

	</div>

	<div class="layui-footer">
		<!-- 底部固定区域 -->
		© 泽庆家居
	</div>
</div>
<script src="../js/jquery-1.12.4.min.js"></script>
<script src="../js/layui.js"></script>

<script>
	//JavaScript代码区域
	layui.use('form', function(){
		var form = layui.form;

		//监听提交
		form.on('submit(formDemo)', function(data){
			layer.msg(JSON.stringify(data.field));
			return false;
		});
	});
	layui.use('element', function(){
		var element = layui.element;
	});
	var num = "";
	function selectClass(sp) {
		location.href="/admin/changeClass?classId2="+sp.value;
		// $.post("admin/changeClass","classId2="+sp.value)
	}
</script>
</body>