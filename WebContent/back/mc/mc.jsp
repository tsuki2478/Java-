<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath(); // /ElectronicShop
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>right</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$(".click").click(function() {
			$(".tip").fadeIn(200);
		});

		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});

		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		});

	});
</script>
</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">商品信息</a></li>
			<li><a href="#">商品信息展示</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">

			<ul class="toolbar">
				<li class="click"><a href="back/mc/mc_add.jsp">
						<span><img src="images/t01.png" /></span>添加</li>
				</a>
				<li class="click"><span><img src="images/t02.png" /></span>修改</li>
				<li><span><img src="images/t03.png" /></span>删除</li>
				<li><span><img src="images/t04.png" /></span>统计</li>
			</ul>

<!--  		根据商品名称模糊查询			-->
		<form action="back/mcServlet" method="post" id="myForm">
		<input type="hidden" name="currentPage" id="currentPage" 
		value="${page.currentPage }">
		
		<input type="hidden" name="pageSize" id="pageSize"
		 value="${page.pageSize }">
		<input type="text" 		 name="mcName"	value="${mcName }"
		style="width:120px; height:32px; border: #D3DBDE 1px solid;">
		<input type="submit" value="搜索  " 
		style="width:60px; height:30px; border: #D3DBDE 1px solid;
		background:#fff;"	
				onmousedown	="onchangeBackgroundDown(this);"
				onmouseup	="onchangeBackgroundUp(this);"> 
		</form> 
		</div>
		<script type="text/javascript">
		function onchangeBackgroundDown(obj){
			//修改标记的背景颜色
			obj.style.background="#123456";	
		}
		function onchangeBackgroundUp(obj){
			obj.style.background="#fff";
			
		}
		
		</script>


		<table class="tablelist">
			<thead>
				<tr>
					<th><input name="" type="checkbox" value="" checked="checked" /></th>
					<th>商品编号<i class="sort"><img src="images/px.gif" /></i></th>
					<th>商品名称</th>
					<th>商品价格</th>
					<th>商品图片</th>
					<th>商品数量</th>
					<th>商品操作</th>
				</tr>
			</thead>
			<tbody>




				<c:forEach var="mc" items="${page.list }">
					<tr>

						<td><input name="" type="checkbox" value="" /></td>
						<td>${mc.mcid }</td>
						<td>${ mc.mcname }</td>
						<td>${mc.price }</td>
						<td><img src="upload/${ mc.pic }" width="100px" height="100px"/></td>
						<td>${mc.quantity }</td>


						<td><a href="#" class="tablelink">查看 </a> 
						<a href	="#" class="tablelink"> 删除 </a> 
						<a href="#"  class="tablelink">修改 </a></td>
					</tr> 



				</c:forEach>

			</tbody>
		</table>
		 <div>

		<%@ include file="../../BasePage.jsp"%>
		 
		 </div>


	 

	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>

	<div style="margin-top:10px; margin-bottom:20px;">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>
</body>
</html>
