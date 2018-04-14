package cn.hd.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * ���ò���������
 * @author dbp
 *���ַ������͵�����ת��Ϊ����
 *val  ��Ҫת��������
 *return ת��������
 */
public class WebUtils {

	
	public static  int parseInt(String val){
		int num = 0;
		try{
//			trim ȥ���ַ����������ߵĿո�
			 num = Integer.parseInt(val.trim(	));
			}catch(Exception e){
				System.out.println(val+"ת��Ϊint���ͳ�����");
			}
		return num;
	}
	
	/**
	 * 
	 * ���ò���������
	 * @author dbp
	 *���ַ������͵�����ת��Ϊ����
	 *val  ��Ҫת��������
	 *return ת��������
	 */
	
public static double parseDouble(String val){
	
	double num = 0;
	try{
//		trim ȥ���ַ����������ߵĿո�
		 num = Double.parseDouble(val.trim());
		}catch(Exception e){
			System.out.println(val+"ת��ΪDouble���ͳ�����");
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
 * ��ȡ����ĵ�ǰҳ
 * @param request	 HttpServletRequest����
 * @param defValue���û����������� ��Ĭ��ֵ
 * @return 	��ǰҳ
 */
public static  int getCurrentPage(HttpServletRequest request,int defValue){
	String  cp  = request.getParameter("currentPage");
	 if(!isEmpty(cp)){
//		 ��ʾ������
		 

	 return parseInt(cp);

}
	return defValue;
}


/**
 * 
 * ��ȡ�����ύ��ÿһҳ��ʾ������
 * @param request
 * @param defValue 	Ĭ��ֵ
 * @return 		pagesaze
 */
public static int 	getPageSize(HttpServletRequest request,int defValue){
	String  cp  = request.getParameter("pageSize");
	 if(!isEmpty(cp)){
//		 ��ʾ������
		 

	 return parseInt(cp);

}
	
	return defValue;
	
	
	
}






}


