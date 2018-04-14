<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath(); // /ElectronicShop
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车管理界面</title>
<link href="css/work.css" rel="stylesheet" type="text/css"/>
</head>
<body>
 <jsp:include page="top.jsp">
 <jsp:param value="4" name="flag"></jsp:param>
 </jsp:include>

	<div	align="center">
	<div style="margin:20px; font-size:24px;color:#1E5494">购物车管理</div>
	<div>
		<table border="1" 	cellspacing="0" width="600px;"
		bordercolor="#CAF2FF" >
			<tr height="40px;" bgcolor="#E4F1FA">
			<th>缩略图</th>
			<th>商品名称</th>
			<th>商品单价</th>
			<th>商品数量</th>
			<th>商品小计</th>
			<th>操作</th>
			</tr>
		<c:forEach var="mc"  items="${SESSION_SHOPCAR.list}">
			<tr height="35px;">
			<td><img  src="upload/${mc.pic }"></td>
			<td>${mc.mcname }</td>
			<td>${mc.price }</td>
			<td align="center"><input type="text" style="width:40px;"  
			value="${mc.shopNum }" onblur="onUpdatenNum(${mc.mcid},this.value)"
			></td>
			<td>${mc.shopNum*mc.price}</td>
		<td>
			<a href="javascript:deleteShop(${mc.mcid })">			
		删除</a>
	</td>
			
			</tr>
		</c:forEach>
		</table>
		
	</div style="margin-top:20px;" >
		商品总类数:${SESSION_SHOPCAR.totalType}|
		商品总个数:${SESSION_SHOPCAR.count}|
		商品总价:${SESSION_SHOPCAR.totalPrice}
		<!-- onclick是点击事件。	 -->
				<input type ="button" value="清空购物车" 
				onclick="location.href='front/ShopCarServlet?task=clear'">
				<input type ="button" value="继续购物"
	onclick="location.href='index.jsp'">
				<input type ="button" value="结算下订单"  
				onclick="location.href='front/filterpage/order.jsp'">
		
	 <div> 
	
	
	</div>
	<script type="text/javascript">
	function deleteShop(mcid){
		 //删除购物车中的商品   点击删除，把删除ID号发送过去
 location.href="front/ShopCarServlet?task=delete&mcid="+mcid;
		
	}
	// 根据id修改购买的数量
	function  onUpdatenNum(mcid,num){
		
location.href="front/ShopCarServlet?task=update&mcid="+mcid+"&num="+num;
		
	}
	</script>
	</div>
</body>
</html>