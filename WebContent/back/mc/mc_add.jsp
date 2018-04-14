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
<body onload="loadFatherType()">
<script type="text/javascript">
		//通过ajax获取所有商品大类
function loadFatherType(){
		//1 获取XMLHttpRequest对象
		var  http = new XMLHttpRequest();
		//2 发送请求
		http.open("GET","back/McTypeServlet?task=loadFatherType",true)
		http.send();
		//3接受相应的数据
		http.onreadystatechange= function(){
		if (http.readyState =='4'&&http.status=='200') {
		// 打印 ：alert(http.responseText);
		//1	将获取的json字符串转化为数组
		var types  =  eval(http.responseText);
		var father =  document.getElementById("fatherid");
		//	遍历数据 并一一添加到下拉菜单
		for (var i = 0; i< types.length; i++) {
			//获取对应的对象
			var type = types[i];
			//打印 ：alert(type.typeid+"--"+type.typename);
			var opt =  document.createElement("option");
			//给下来菜单选项赋值。
			opt.innerHTML =  type.typename;
			opt.value = type.typeid;
			father.appendChild(opt);
					}
				}				
			}
		}
		//通过大类编号获取对应的小类信息
		function  loadSmallType(id){
		var  http = new XMLHttpRequest();
			//2 发送请求
		http.open("GET","back/McTypeServlet?task=loadSmallType&id="+id,true)
		http.send();
			//3接受相应的数据
		http.onreadystatechange= function(){
		if (http.readyState =='4'&&http.status=='200') {
			// 打印 ：alert(http.responseText);
			//1	将获取的json字符串转化为数组
			var types  =  eval(http.responseText);
			var smallid =  document.getElementById("samllid");
			smallid.length =1;
			//	遍历数据 并一一添加到下拉菜单
			for (var i = 0; i< types.length; i++) {
				//获取对应的对象
				var type = types[i];
				//打印 ：alert(type.typeid+"--"+type.typename);
				var opt =  document.createElement("option");
				//给下来菜单选项赋值。
				opt.innerHTML =  type.typename;
				opt.value = type.typeid;
				smallid.appendChild(opt);
						}
				}
			}
		}
</script>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">商品信息</a></li>
		</ul>
	</div>

	<div class="formbody">
		<form action="back/mcServlet?task=add" method="post" enctype="multipart/form-data">
			<div class="formtitle">
				<span>添加商品信息</span>
			</div>
	
			<ul class="forminfo">
			<li>
			
				<label>商品名称</label>
				<input name="mcname" type="text"
				class="dfinput" /> </li>
				
				<li>
				<label>商品价格</label>
				<input name="price" type="text"
				class="dfinput" /> </li>
				
				<li>
				<label>是否缺货</label>
				<input name="flag" type="radio" value="1"/>否
				<input name="flag" type="radio" value="0"/>缺
				 </li>
				
				<li>
				<label>商品数量</label>
				<input name="quantity" type="text"
				class="dfinput" /> </li>
				
				<li>
				<label>商品图片</label>
				<input name="pic" type="file"
				  /> </li>
				
		 
				<li><label>商品大类</label>
					<div class="vocation">
						<select class="select2" 
				onchange= "loadSmallType(this.value);"		name="fatherid" id="fatherid">
							<option value="0">请选择大类</option>
							
						</select>
					</div></li>

				<li><label>商品小类</label>
					<div class="vocation">
						<select class="select2" name="samllid" id="samllid">
							<option value="0">请选择小类</option>
						</select>
					</div></li>

				<li>
				<label>商品描述</label>
				<textarea rows="10" cols="55" name="mcdecx" 
				style="border:#A7B5BBC 1px solid;"></textarea>
					 </li>	
	

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
