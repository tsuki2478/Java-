package cn.hd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.hd.model.User;
import cn.hd.service.IUserService;
import cn.hd.service.impl.UserServiceImpl;

/**
 * Servlet ǰ̨��¼
 */
@WebServlet("/FrontLoginServlet")
public class FrontLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		IUserService service = new 	UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1���ж��û�У�����Ƿ���ȷ
		String code = request.getParameter("Code");

		// 2����ȡ��ʵ���ɵ�У����
		HttpSession session = request.getSession();
		String rand = (String) session.getAttribute("rand");
//			System.out.println(code+"" +rand); 
//		�ж�code�� session��rand ˫��У���Ƿ�һ��
		if (code.equals(rand)) {
//			��ʾУ������ȷ
	String userName	=		request.getParameter("UserName");
	String password =		request.getParameter("Password");
//		  Ч���˺������Ƿ���ȷ���ڶ��ж�
				User user = service.login(userName, password);
			if (user != null) {
				session.setAttribute("USER", user);
//				��ʾ��¼�ɹ�����ת����ҳ��
				response.sendRedirect("front/homeServlet");
			}else{
//				��ʾ��¼ʧ�ܣ���ת����¼���棩
				session.setAttribute("LOGIN_ERROR", "�˺��������");
				response.sendRedirect("FrontLogin.jsp");
				}	
			}else{
//			У�������
//			ֱ����ת�ص�½ҳ��
				session.setAttribute("LOGIN_ERROR", "У�����");
				response.sendRedirect("FrontLogin.jsp");
			
			
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
