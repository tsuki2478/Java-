<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath(); // /ElectronicShop
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>下订单</title>
<style type="text/css">
table tr  td.td1 {
	text-align: right;
}
</style>
</head>
<body>
	<div align="center">
		<div style="margin: 20px; font-size: 24px;">下订单</div>
		<form action="front/OrderServlet" method="post">
			<table border="1" cellspacing="0" style="width: 500px color:#4472A7;"
				bordercolor="#CAF2FF">

				<tr>
					<td style="width: 33%;" class="td1">所购商品种类数</td>
					<td style="width: 33%;">${SESSION_SHOPCAR.totalType }</td>
					<td style="width: 33%;"></td>
				</tr>

				<tr>
					<td class="td1">所购商品总件数</td>
					<td>${SESSION_SHOPCAR.count }</td>
					<td></td>
				</tr>

				<tr>
					<td class="td1">价格总计</td>
					<td>${SESSION_SHOPCAR.totalPrice }</td>
					<td></td>
				</tr>

				<tr>
					<td class="td1">付款方式</td>
					<td><select name="paytype">
							<option value="在线支付">在线支付</option>
							<option value="货到付款">货到付款</option>
							<option value="招人支付">招人支付</option>
					</select></td>
					<td></td>
				</tr>

				<tr>
					<td class="td1">收货方式</td>
					<td><select name="receivedtype">
							<option value="平邮">平邮</option>
							<option value="快递">快递</option>
							<option value="EMS">EMS</option>
					</select></td>
					<td></td>
				</tr>

				<tr>
					<td class="td1">收货人姓名</td>
					<td><input name="username"   type="text" value="${USER.truename }"></td>
					<td></td>
				</tr>

				<tr>
					<td class="td1">收货人地址</td>
					<td><input  name="address" type="text" value="${USER.address }"></td>
					<td></td>
				</tr>

				<tr>
					<td class="td1">收货人邮编</td>
					<td><input   name="postcade" type="text" value="${USER.postcade }"></td>
					<td></td>
				</tr>

				<tr>
					<td class="td1">收货人电话</td>
					<td><input name="phoneno" type="text" value="${USER.phoneno }"></td>
					<td></td>
				</tr>

				<tr>
					<td class="td1">收货人邮箱</td>
					<td><input 	name="email"	type="text" value="${USER.email}"></td>
					<td></td>
				</tr>

				<tr>
				<tr align="center">
					<td colspan="3"><input type="submit" value="下订单"> <input
						type="reset" value="重置"></td>
				</tr>

			</table>
		</form>

	</div>
</body>
</html>