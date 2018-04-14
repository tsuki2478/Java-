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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>电子商城</title>
<link href="css/work.css" rel="stylesheet" type="text/css" />

</head>
<%--  <jsp:include page="home.jsp">
 <jsp:param value="1" name="flag"></jsp:param>
 </jsp:include> --%>
<body>
 <jsp:include page="top.jsp">
 <jsp:param value="1" name="flag"></jsp:param>
 </jsp:include>

	<!--主体内容-->
	<div id="content">
		<!--左侧-*********************************************************-->
		<div id="content_left">

			<div id="l_notice">
				<div id="l_title">
					<div id="lan">公告栏</div>
				</div>
				<div id="l_content"></div>
			</div>
			<!--商品************************************-->

			<div id="r_type">
				<div id="r_title">
					<div id="shop">商品分类</div>
				</div>
				<div id="r_content">

					<c:forEach var="big" items="${typeList }">
						<c:if test="${big.fatherid == 0 }">
							<dl>
								<dt>${big.typename }</dt>
								<dd>
									<c:forEach var="small" items="${typeList }">
										<c:if test="${small.fatherid == big.typeid }">
											<li>${small.typename}</li>
										</c:if>
									</c:forEach>
								</dd>
							</dl>
						</c:if>
					</c:forEach>

				</div>
			</div>


			<!--左侧DIV-->
		</div>
		<!--右侧开始*******************************-->
		<div id="content_right">
			<c:forEach var="mc" items="${page.list }">
				<div class="mc_list">
					<div class="mc_image">
						<img src="upload/${mc.pic }"  />
						<p>
							<a href="#">查看大图</a>
						</p>
					</div>
					<div class="mc_info">
						<p>
							<span>${mc.mcname }</span>
						</p>
						<p>
						单价：<span style="color: red;">￥${mc.price}</span>
						</p>
						<p>是否缺货：${mc.flag eq 0?"否":"缺" }</p>
						<p class="mc_desc">${mc.mcdecx }</p>
						<p class="btn">
							<a href="#"><img src="img/detail.jpg" /></a> 
					<a href="javaScript:shopCar(${mc.mcid });"><img
							
								src="img/pay.jpg" /></a>
						</p>
					</div>
				</div>
			</c:forEach>
			<!--分割线***************************************-->

			<hr />
			<form action="front/homeServlet" method="post" id="myForm">
				<input type="hidden" name="currentPage" id="currentPage"
					value="${page.currentPage }"> 
					<input type="hidden"
			name="pageSize" id="pageSize" value="${page.pageSize }">
			</form>
			<div id="jquery" style="margin-top: 10px; margin-bottom: 10px;">
				<%@  include file="../BasePage.jsp"%>
			</div>

			<!--右侧div-->
		</div>
		<!--主体div-->
	</div>

	<!--底部-->
	
	<script type="text/javascript">
	//通过异步任务往购物车添加商品信息
	function shopCar(mcid){
		//alert(mcid);
		//1 获取XMLHttpRequest对象
	var http  = new XMLHttpRequest();
		//2发送请求。
	http.open("GET","front/ShopCarServlet?mcid="+mcid,true);	
	http.send();
		//3获取服务器相应
	http.onreadystatechange=function(){
	if(http.readyState=='4'&&http.status=='200'){
	//		alert(http.responseText);
			document.getElementById("shopCount").innerHTML = http.responseText;
			}		
		}	
	}
	
	</script>
	
	
	<div id="footer">
		<p>版权所有</p>
		<p>深圳科技有限公司</p>
	</div>
</body>
</html>
