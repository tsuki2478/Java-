<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath(); // /ElectronicShop
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//  http://localhost:8888/homeWork/
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
			<li><a href="#">数据表</a></li>
			<li><a href="#">基本内容</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">

			<ul class="toolbar">
				<li class="click"><a href="back/McTypeServlet?task=queryFather">
						<span><img src="images/t01.png" /></span>添加</li>
				</a>
				<li class="click"><span><img src="images/t02.png" /></span>修改</li>
				<li><span><img src="images/t03.png" /></span>删除</li>
				<li><span><img src="images/t04.png" /></span>统计</li>
			</ul>


			<ul class="toolbar1">
				<li><span><img src="images/t05.png" /></span>设置</li>
			</ul>

		</div>


		<table class="tablelist">
			<thead>
				<tr>
					<th><input name="" type="checkbox" value="" checked="checked" /></th>
					<th>类别编号<i class="sort"><img src="images/px.gif" /></i></th>
					<th>类别名称</th>
					<th>父类别编号</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="type" items="${list }">

					<c:if test="${type.fatherid eq 0 }">
						<tr>
							<td><input name="" type="checkbox" value="" /></td>
							<td>${type.typeid }</td>
							<td>${type.typename }</td>
							<td>${type.fatherid }</td>
							<td><a href="#" class="tablelink">查看 </a> <%-- <a href="back/McTypeServlet?task=delete&typeid=${type.typeid }"
		class="tablelink"> 删除 </a></td>--%> <a	href="javascript:deleteMcType(${type.typeid});"
								class="tablelink"> 删除 </a> <a
								href="back/McTypeServlet?task=queryById&typeid
	=${type.typeid }"	class="tablelink">修改 </a></td>
						</tr>
						<!-- 在循环一次，取出大类对应的小类-->
						<c:forEach var="small" items="${list }">
							<c:if test="${small.fatherid eq type.typeid }">
								<td><input name="" type="checkbox" value="" /></td>
								<td style="padding-left: 60px;">${small.typeid }</td>
								<td style="padding-left: 60px;">${small.typename }</td>
								<td style="padding-left: 60px;">${small.fatherid }</td>
								<td>
									<%--  <a href="back/McTypeServlet?task=delete&typeid=${small.typeid }" 
 class="tablelink"> --%> <a
									href="javascript:deleteMcType(${small.typeid});"
									class="tablelink"> 删除 </a> <a
									href="back/McTypeServlet?task=queryById&typeid
	=${small.typeid }"	class="tablelink">修改 </a>

								</td>
								</tr>
							</c:if>
						</c:forEach>
						<script type="text/javascript">
							function deleteMcType(typeid) {
								//alert(typeid);
								//弹出一个确认框
								var flag = window.confirm("真的要删除这条记录吗？");
								if (flag) {
									// 表示用户的选中是真的要删除数据？
									location.href = "back/McTypeServlet?task=delete&typeid="
											+ typeid;

								}

							}
						</script>

					</c:if>
				</c:forEach>

			</tbody>
		</table>
		<div class="pagin">
			<div class="message">
				共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页
			</div>
			<ul class="paginList">
				<li class="paginItem"><a href="javascript:;"><span
						class="pagepre"></span></a></li>
				<li class="paginItem"><a href="javascript:;">1</a></li>
				<li class="paginItem current"><a href="javascript:;">2</a></li>
				<li class="paginItem"><a href="javascript:;">3</a></li>
				<li class="paginItem"><a href="javascript:;">4</a></li>
				<li class="paginItem"><a href="javascript:;">5</a></li>
				<li class="paginItem more"><a href="javascript:;">...</a></li>
				<li class="paginItem"><a href="javascript:;">10</a></li>
				<li class="paginItem"><a href="javascript:;"><span
						class="pagenxt"></span></a></li>
			</ul>
		</div>


		<div class="tip">
			<div class="tiptop">
				<span>提示信息</span><a></a>
			</div>

			<div class="tipinfo">
				<span><img src="images/ticon.png" /></span>
				<div class="tipright">
					<p>是否确认对信息的修改 ？</p>
					<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
				</div>
			</div>

			<div class="tipbtn">
				<input name="" type="button" class="sure" value="确定" />&nbsp; <input
					name="" type="button" class="cancel" value="取消" />
			</div>

		</div>




	</div>

	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>

	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>
</body>
</html>
