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
 
//    ʵ����
    IMcSerivce service = new McServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String task = request.getParameter("task");
			if ("add".equals(task)) {
				//��ʾ�����Ʒ��Ϣ ��ͨ��Ϣ�������ݿ��С��ϴ��ļ����浽��������
	 	 DiskFileItemFactory factory = new DiskFileItemFactory();
	 	 ServletFileUpload upload  	 = new ServletFileUpload(factory); 
			//��������
 		 try {
 			 //���ύ�����ݶ���list����
			List<FileItem> list = upload.parseRequest(request);
			//ʵ����McBean�������ڱ����û��ύ��Ϣ
			McBean mc =  new McBean();
			for (FileItem file : list) {
			if (file.isFormField()) {
				//��ͨ���ı�	
				//��ȡ��ǵ�name���Ե�ֵ
				String filedName =file.getFieldName();
				//��ȡ��Ӧ��ֵ
				String value = file.getString("utf-8");
				//����Ҫ���浽���ݿ��
//				System.out.println(filedName+"---"+value);
				//�жϡ����ұ��浽��Ӧ�ĵط������ֹ�����
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
				//�ļ�
				// 1.��ȡ�ļ�������
//				file.getFieldName() �ǻ�ȡ��ǩname���Ե�ֵ��getName�ǻ�ȡ�ϴ��ļ�����
//				�ϴ�����ǰ�ļ�
				String fileName = file.getName();
				mc.setPic(fileName);
				// 2.	���ļ�������upload�ļ�����
				/*File f= new File();
				f.isDirectory();
				f.mkdir();֮���*/
				  String path = this.getServletContext().getRealPath("/upload");
				  	//System.out.println(path);this.getServletContext().getRealPathҲ����Ŀ¼��ַ
				  	file.write(new File(path+"/"+fileName));
			
				}
			}
			//����ͨ��Ϣ���������ݿ���
			service.add(mc);
			//��ҳ��ѯ����
			query(request, response);
 		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		 
			}else {
				//��ҳ��ѯ����
			query(request, response);
			}
		}
	/**
	 * ��ҳ��ѯ��Ʒ��Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*	String currentPageStr = request.getParameter("currentPage");
		int currentPage = 1;// Ĭ����ҳ
		if (currentPageStr != null && !"".equals(currentPageStr)) {
			// ��ʾ������ָ����ǰҳ
			currentPage = Integer.parseInt(currentPageStr);
		}
			int 	pageSize	= 5; 
		// ��ȡ���󴫵ݹ�����pageSize(��ȡÿҳ��ʾ����)
		String pageSizeStr = request.getParameter("pageSize");
		if (pageSizeStr != null && !"".equals(pageSizeStr)) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
*/			
//			��һ����ȡ��ҳ
			int currentPage	= WebUtils.getCurrentPage(request, 1);
			int pageSize	= WebUtils.getPageSize(request, 5);
//			��ȡ�û��ύ�Ĳ�ѯ����
			String mcName = request.getParameter("mcName");
			 McBean  mc = new  McBean(); 
			if (!WebUtils.isEmpty(mcName)) {
//					��ʾ�û�ϣ��ͨ���û�����������
					mc.setMcname(mcName);	
					}			
//			�ڶ������ݷ�ҳ������ѯ��ҳ���� service--��dao --->���ݿ����
//			��������ѯ���ܹ����ٲ���¼	   service  --��dao --->���ݿ�
//			(���һ��currentPage,pageSize������)					
			BasePage<McBean> page = new BasePage<>(currentPage,pageSize);
				page	=	service.queryByPage(mc, page);
				
			//			���Ĳ���������ݷŽ�������
	
			request.setAttribute("page", page);	
//			(��ѯ���ݷ���������)
			request.setAttribute("mcName", mcName);
	
			request.getRequestDispatcher("mc/mc.jsp").forward(request, response);
			
		
/*		// 1ͨ��service�����ѯ���е�����
		IMcSerivce service = new McServiceImpl();
		// 2����ѯ���ݱ�����request������
		List<McBean> list = service.query();
*/		// 3��ת����Ӧ��jspҳ��չʾ����
//		request.setAttribute("list", list);
		
		//4.��������תҳ�浽��Ʒ���չʾҳ��
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
 