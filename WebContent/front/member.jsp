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
<meta charset="utf-8">
<title>恩</title>

<link href="css/member.css" rel="stylesheet" type="text/css">
</head>

<%-- <jsp:include page="member.jsp">
 <jsp:param value="3" name="flag"></jsp:param>
 </jsp:include> --%>
<body>
	<%-- <%@ include file="top.jsp" %> --%>
	<jsp:include page="top.jsp">
		<jsp:param value="3" name="flag" />
	</jsp:include>
	<div id="m_content">
		<div id="m_c_left">

			<div id="m_c_left_bottom">
				<p class="m_c_left_title">会员中心</p>

				<ul>
					<li><a href="#" target="myiframe">基本资料显示</a></li>
					<li><a href="#" target="myiframe">用户资料修改</a></li>
					<li><a href="#" target="myiframe">密码修改</a></li>
					<li><a href="front/OrderServlet?task=query" target="myiframe">我的订单</a></li>
				</ul>


			</div>
		</div>
		<div id="m_c_right">
			<iframe id="myiframe" name="myiframe" frameborder="0" width="700"
				height="500"> </iframe>
		</div>
	</div>


</body>
</html>
