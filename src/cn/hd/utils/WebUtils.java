package cn.hd.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 常用操作工具类
 * @author dbp
 *将字符串类型的数据转化为整形
 *val  需要转化的数据
 *return 转化后数据
 */
public class WebUtils {

	
	public static  int parseInt(String val){
		int num = 0;
		try{
//			trim 去掉字符串左右两边的空格
			 num = Integer.parseInt(val.trim(	));
			}catch(Exception e){
				System.out.println(val+"转化为int类型出错！！");
			}
		return num;
	}
	
	/**
	 * 
	 * 常用操作工具类
	 * @author dbp
	 *将字符串类型的数据转化为整形
	 *val  需要转化的数据
	 *return 转化后数据
	 */
	
public static double parseDouble(String val){
	
	double num = 0;
	try{
//		trim 去掉字符串左右两边的空格
		 num = Double.parseDouble(val.trim());
		}catch(Exception e){
			System.out.println(val+"转化为Double类型出错！！");
		}
	return num;
}
	
public static boolean isEmpty(String val){
	if("".equals(val) || val == null){
		return true;
		
	}
	return false;
	
		}	

/**
 * 获取请求的当前页
 * @param request	 HttpServletRequest对象
 * @param defValue如果没有请求的数据 的默认值
 * @return 	当前页
 */
public static  int getCurrentPage(HttpServletRequest request,int defValue){
	String  cp  = request.getParameter("currentPage");
	 if(!isEmpty(cp)){
//		 表示有数据
		 

	 return parseInt(cp);

}
	return defValue;
}


/**
 * 
 * 获取请求提交的每一页显示的条数
 * @param request
 * @param defValue 	默认值
 * @return 		pagesaze
 */
public static int 	getPageSize(HttpServletRequest request,int defValue){
	String  cp  = request.getParameter("pageSize");
	 if(!isEmpty(cp)){
//		 表示有数据
		 

	 return parseInt(cp);

}
	
	return defValue;
	
	
	
}






}


