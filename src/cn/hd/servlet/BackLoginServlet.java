package cn.hd.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.hd.model.ManagerBean;
import cn.hd.service.IManagerService;
import cn.hd.service.impl.ManagerServiceImpl;
/**
 * 
 * Servlet		��̨����Ա��¼
 * @author Administrator
 *	
 */
@WebServlet("/BackLoginServlet")
public class BackLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	  IManagerService service = new ManagerServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		// TODO Auto-generated method stub
//		1:��ȡ�û��ύ�ĵ�¼����
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
//		2��ͨ��service��login����Ч���Ƿ��¼�ɹ�
		
			ManagerBean manager = service.login(userName, password);		   
							HttpSession session = request.getSession();
//		3������Ч��Ľ��������ͬ��Ӧ
				   	if (manager != null) {
//						��ʾ��¼�ɹ�
//				   		����ǰ��¼���û���Ϣ������sesssion�������У�������ʱ��¼�û���Ϣ
						session.setAttribute("BACK_USER", manager);
		 	request.getRequestDispatcher("back/main.jsp").forward(request, response);
					}else{
//						��ʾ��¼ʧ��
//						��session�������б���һ������
				session.setAttribute("LOGIN_ERROR", "��¼ʧ��");
				response.sendRedirect("BackLogin.jsp");		
						
					}
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
