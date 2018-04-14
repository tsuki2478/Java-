<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	 			<!-- 通过失去焦点事件改变每行页数 -->
		  每页显示 <input type="number" style="width: 50px;"
		 onblur="changePageSize(this.value);" value="${page.pageSize }">条
	 	 <a href="javascript:firstPage();">首页</a>	 
	 	 <a href="javascript:backPage();">上一页</a>	 
		 <a href="javascript:nextPage();">下一页</a>	 
	 	 <a href="javascript:endPage();">尾页</a>	 
	 	 总共有<font style="color: red; font-weight: bold;">${page.totalPage } 
	 	 </font>页
	 	  到第 <select style="width: 50px;" onchange="changeCurrentPage(this.value)">
			<c:forEach var="i" begin="1" end="${page.totalPage}">
				<option value="${i}" 
				${i eq page.currentPage?'selected="selectted"':''}>${i}
					</option>
			</c:forEach>
		</select>
	 页
				
	 </div>
	 <script type="text/javascript">
	 function firstPage(){		 	
		 //设置currentPage的值
		 document.getElementById("currentPage").value =1 ;
		 //提交表单
		 document.getElementById("myForm").submit();	 
	 }
	 function backPage(){
		 //获取上一页数据		
		 //设置currentPage的值
		 document.getElementById("currentPage").value =
		 ${page.currentPage == 1?1: page.currentPage-1};
		 //提交表单
		 document.getElementById("myForm").submit();		 
	 }
	 
	 function nextPage(){
		 //获取下一页数据		
		 //设置currentPage的值
		 document.getElementById("currentPage").value =
	${page.currentPage==page.totalPage?page.totalPage:page.currentPage+1};
		 //提交表单
		 document.getElementById("myForm").submit();
	 }	 
	 
	 function endPage(){
		 //获取尾页数据		
		 //设置currentPage的值
		 document.getElementById("currentPage").value =${page.totalPage};
		 //提交表单
		 document.getElementById("myForm").submit();
	 }
	 
		// 直接改变当前页
	function changeCurrentPage(page){
			 
			// 设置currentPage的值
		document.getElementById("currentPage").value = page;
			// 提交表单
		document.getElementById("myForm").submit();
		}
	 //改变每页显示的条数
	 function changePageSize(size){
		 	
		 //设置currentPage的值
		 document.getElementById("currentPage").value =1;
		 document.getElementById("pageSize").value =size;
		 //提交表单
		 document.getElementById("myForm").submit();		 
	 }
	 </script>
	 
</body>
</html>