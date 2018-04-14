<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath(); // /ElectronicShop
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//  http://localhost:8888/homeWork/
%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<base href="<%=basePath%>">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>
<script type="text/javascript" src="editor/kindeditor.js"></script>

<script type="text/javascript">
	$(document).ready(function(e) {
		$(".select1").uedSelect({
			width : 345
		});
		$(".select2").uedSelect({
			width : 167
		});
		$(".select3").uedSelect({
			width : 100
		});
	});
</script>

</head>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">商品类别修改</a></li>
		</ul>
	</div>

	<div class="formbody">
		<form action="back/McTypeServlet?task=update" method="post">
			<div class="formtitle">
				<span>修改商品类别信息</span>
			</div>

			<ul class="forminfo">
			<input type="hidden" name="typeid" value="${type.typeid }">
			<!--hidden是隐藏.通过表单隐藏给服务器传递过去一个参数  -->
				<li>
				<label>类别名称
				</label><input name="typename" value="${type.typename }" 
				type="text" 	class="dfinput" /><i>标题不能超过15个字符</i></li>
		<!--
			if(father.typeid eq type.fatherid){
			selected="selected";
			}		else{
			selected="";
			}
			selected 是默认选中
		  -->
				<li><label>商品大类</label>
					<div class="vocation">
						<select class="select1" name="fatherid">
							<option value="0">无</option>
							<c:forEach var="father" items="${typelist}">
					<option value="${father.typeid}" 
					${father.typeid eq type.fatherid?'selected="selected"':''}	
						
					>${father.typename}	
								</option>
								
							</c:forEach>
						</select>
					</div></li>



				<li><label>&nbsp;</label><input type="submit" type="button"
					class="btn" value="确认保存" /></li>
			</ul>

		</form>
	</div>


	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>
</body>
</html>
