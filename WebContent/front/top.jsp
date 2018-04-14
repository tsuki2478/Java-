<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath(); // /ElectronicShop
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//  http://localhost:8888/ElectronicShop/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>电子商城</title>
<link href="css/work.css" rel="stylesheet" type="text/css" />
</head>
<%--  <jsp:include page="home.jsp">
 <jsp:param value="1" name="flag"></jsp:param>
 </jsp:include> --%>
<body>
	<!--头部-->
	<div id="header">
		<!--*****************我是分割线********************************-->

		<div id="header_top">
			<!--****************这是为了让header_top的颜色从框架延伸出去-->


			<!--top_left开始-->
			<div id="header_left">
				<span>您好<font style="color: red; font-weight: bold;">
						${USER.truename}</font>!
				</span> ${USER eq null?' <a href="FrontLogin.jsp">请登录</a>':'<a href="LogoutServlet">注销</a>' }
				<!-- 	 <a href="FrontLogin.jsp">请登录</a>
				 <a href="LogoutServlet">注销</a>		 -->
				<a href="BackLoginServlet">后台登录</a>
			</div>
			<!--top_left-->
			<!--*****************我是分割线********************************-->

			<!--top_right-->
			<div id="header_right">
				<ul>
					<li class="header_right_a"><a href="#">我的订单</a></li>
					<li class="header_right_a"><a href="#">我的一号店</a></li>
					<li class="header_right_a"><a href="#">帮助中心</a></li>
					<li class="header_right_a"><a href="#">联系客服</a></li>
					<li class="header_right_a"><a href="#">加入收藏</a></li>
					<li>服务热线:</li>
					<li><span
						style="color =: red; font-size: 16px; font-weight: bold;">
							400-8823-823</span></li>
				</ul>
			</div>

			<!--    这是top颜色的div-->

			<!--这个是header_top的div-->
		</div>
		<!--*****************我是分割线********************************-->
		<!--logo—****************************************-->
		<div id="header_mid">
			<div id="logo"></div>
			<!--这是logo右边-->
			<div id="mid_right">
				<ul>
					<li ${ param.flag eq  '1'?'class="nav_on"' :''}><a
						href="index.jsp">首页</a></li>
					<li><a href="home.jsp">积分兑换</a></li>
					<li ${ param.flag eq  '3'?'class="nav_on"' :''}><a
						href="front/member.jsp">会员中心</a></li>
					<li ${ param.flag eq  '4'?'class="nav_on"' :''}>><a
						href="front/shopcar.jsp">购物车</a></li>
				</ul>
			</div>
			<!--这是mid的div-->
		</div>
		<!--*****************我是分割线********************************-->
		<!--这是搜索栏设置**********************************-->
		<div id="march">
			<!--所有商品分类**********************************-->
			<div id="mall">
				<p>所有商品分类</p>
			</div>
			<!--这是搜索栏**********************************-->
			<div id="search">
				<form>
					<input id="set_text" style="" name="mcname" value="${mcname }" />
					<input id="sear" type="submit"
						style="background: url(img/btn_search.jpg) no-repeat; width: 85px; height: 34px; border: 0px;"
						value=" " />
				</form>
			</div>

			<!--这是热门搜索栏**********************************-->
			<div id="so">
				<ul>
					<li>热门搜索：</li>
					<li><a href="#">贝玲妃</a></li>
					<li><a href="#">SKII</a></li>
					<li><a href="#">阿芙</a></li>
				</ul>
			</div>

			<!--********结算*******************-->
			<div id="cart">
				<ul>
					<li class="cart_1"><a href="front/shopcar.jsp">购物车<span
							id="shopCount">${SESSION_SHOPCAR.count}</span>件
					</a></li>
					<li class="cart_2"><a href="front/shopcar.jsp">去结算</a></li>
				</ul>
			</div>
			<!--这是搜索类div-->
		</div>
		<!--活动-->
		<div id="active" style="height: 30px; line-height: 30px;">
			<marquee>前台登录账号:a 密码 :a</marquee>
		</div>

		<!--这是header的div-->
	</div>
	<!--*****************我是分割线********************************-->