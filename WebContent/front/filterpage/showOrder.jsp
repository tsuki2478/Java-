<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String path = request.getContextPath(); // /ElectronicShop
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//  http://localhost:8888/ElectronicShop/
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center"
		style="border: #157128 1px solid; width: 600px; height: 400px;">
		<div style="margin: 10px; font-size: 20px; color: #157128;">
			我的订单</div>

		<div>
			<table border="1" cellspacing="0" bordercolor="#157128"
				width="500px;">
				<tr>
					<th style="width: 25%">订单编号</th>
					<th style="width: 25%">订单总价</th>
					<th style="width: 25%">收件人姓名</th>
					<th style="width: 25%">地址</th>
				</tr>
			<c:forEach var="order" items="${page.list }">
			<tr>
			<td>${order.orderid }</td>
			<td>${order.totalprice }</td>
			<td>${order.username }</td>
			<td>${order.address }</td>
			</tr>
			
			</c:forEach>
			</table>
		</div>
	
	<form action="front/OrderServlet?task=query" method="post" id="myForm">
	<input type="hidden" name="currentPage" id="currentPage">
	<input type="hidden"name="pageSize" id="pageSize" >
	</form>
	<div id="jquery" style="margin-top: 10px; margin-bottom: 10px;">
		<%@  include file="../../BasePage.jsp"%>
	</div>
	</div>
</body>
</html>