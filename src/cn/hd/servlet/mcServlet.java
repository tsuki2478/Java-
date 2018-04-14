package cn.hd.servlet;

import java.io.File;
import java.io.IOException;



import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.hd.model.McBean;
import cn.hd.service.IMcSerivce;
import cn.hd.service.impl.McServiceImpl;
import cn.hd.utils.BasePage;
import cn.hd.utils.WebUtils;
 
@WebServlet("/back/mcServlet")
public class mcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
//    实例化
    IMcSerivce service = new McServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String task = request.getParameter("task");
			if ("add".equals(task)) {
				//表示添加商品信息 普通信息保存数据库中。上传文件保存到服务器中
	 	 DiskFileItemFactory factory = new DiskFileItemFactory();
	 	 ServletFileUpload upload  	 = new ServletFileUpload(factory); 
			//解析请求
 		 try {
 			 //表单提交的数据都在list这里
			List<FileItem> list = upload.parseRequest(request);
			//实例化McBean对象用于保存用户提交信息
			McBean mc =  new McBean();
			for (FileItem file : list) {
			if (file.isFormField()) {
				//普通的文本	
				//获取标记的name属性的值
				String filedName =file.getFieldName();
				//获取对应的值
				String value = file.getString("utf-8");
				//最终要保存到数据库里。
//				System.out.println(filedName+"---"+value);
				//判断。并且保存到对应的地方。名字归名字
				    if ("mcname".equals(filedName)) {
					mc.setMcname(value);
				}else if("price".equals(filedName)){
					mc.setPrice(WebUtils.parseDouble(value));
				}else if("flag".equals(filedName)){
					mc.setFlag(value);
				}else if("quantity".equals(filedName)){
					mc.setQuantity(WebUtils.parseInt(value));
				}else if("samllid".equals(filedName)){	
					mc.setSmalltypeid(WebUtils.parseInt(value));
				}else if("mcdecx".equals(filedName)){				
					mc.setMcdecx(value);	
				}
			}else{	
				//文件
				// 1.获取文件的名称
//				file.getFieldName() 是获取标签name属性的值。getName是获取上传文件名称
//				上传到当前文件
				String fileName = file.getName();
				mc.setPic(fileName);
				// 2.	将文件保存在upload文件夹中
				/*File f= new File();
				f.isDirectory();
				f.mkdir();之类的*/
				  String path = this.getServletContext().getRealPath("/upload");
				  	//System.out.println(path);this.getServletContext().getRealPath也就是目录地址
				  	file.write(new File(path+"/"+fileName));
			
				}
			}
			//将普通信息保存在数据库中
			service.add(mc);
			//分页查询方法
			query(request, response);
 		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		 
			}else {
				//分页查询方法
			query(request, response);
			}
		}
	/**
	 * 分页查询商品信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*	String currentPageStr = request.getParameter("currentPage");
		int currentPage = 1;// 默认首页
		if (currentPageStr != null && !"".equals(currentPageStr)) {
			// 表示请求有指定当前页
			currentPage = Integer.parseInt(currentPageStr);
		}
			int 	pageSize	= 5; 
		// 获取请求传递过来的pageSize(获取每页显示条数)
		String pageSizeStr = request.getParameter("pageSize");
		if (pageSizeStr != null && !"".equals(pageSizeStr)) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
*/			
//			第一步获取分页
			int currentPage	= WebUtils.getCurrentPage(request, 1);
			int pageSize	= WebUtils.getPageSize(request, 5);
//			获取用户提交的查询条件
			String mcName = request.getParameter("mcName");
			 McBean  mc = new  McBean(); 
			if (!WebUtils.isEmpty(mcName)) {
//					表示用户希望通过用户名查找数据
					mc.setMcname(mcName);	
					}			
//			第二部根据分页参数查询分页数据 service--》dao --->数据库操作
//			第三部查询出总共多少部记录	   service  --》dao --->数据库
//			(缝合一下currentPage,pageSize这两个)					
			BasePage<McBean> page = new BasePage<>(currentPage,pageSize);
				page	=	service.queryByPage(mc, page);
				
			//			第四部将相关数据放进作用域
	
			request.setAttribute("page", page);	
//			(查询数据放入作用域)
			request.setAttribute("mcName", mcName);
	
			request.getRequestDispatcher("mc/mc.jsp").forward(request, response);
			
		
/*		// 1通过service对象查询所有的数据
		IMcSerivce service = new McServiceImpl();
		// 2将查询数据保存在request作用域
		List<McBean> list = service.query();
*/		// 3跳转到对应的jsp页面展示数据
//		request.setAttribute("list", list);
		
		//4.服务器跳转页面到商品类别展示页面
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
 