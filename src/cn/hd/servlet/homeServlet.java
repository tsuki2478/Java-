package cn.hd.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hd.model.McBean;
import cn.hd.model.McTypeBean;
import cn.hd.service.IMcSerivce;
import cn.hd.service.IMcTypeService;
import cn.hd.service.impl.McServiceImpl;
import cn.hd.service.impl.McTypeServiceImpl;
import cn.hd.utils.BasePage;
import cn.hd.utils.WebUtils;

@WebServlet("/front/homeServlet")
public class homeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    IMcSerivce	mcService  = new McServiceImpl();
	IMcTypeService typeService = new McTypeServiceImpl();
	    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1��ѯ����Ҫ��ʾ�����ݣ�null������������
		List<McTypeBean> typeList = typeService.query(null);
		// ��ȡ��ҳ����ز���
		int currentPage = WebUtils.getCurrentPage(request, 1);
		int pageSize = WebUtils.getPageSize(request, 3);
		//��ȡ��ҳ��ѯ����
			String mcname = request.getParameter("mcname");
			McBean	mc = new McBean();
			if (!WebUtils.isEmpty(mcname)) {
			//����ѯ������װ��Mcmean����
				mc.setMcname(mcname);
			}
			
		//��Ʒ��Ϣ�� 
		 BasePage<McBean> page = new BasePage<>(currentPage, pageSize);
		 	page	= mcService.queryByPage(mc, page);		 					
		//�ŵ�������
		 request.setAttribute("typeList", typeList);
		request.setAttribute("page", page);
		request.setAttribute("mcname", mcname);
		// 2��ת��home.jspҳ��
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
