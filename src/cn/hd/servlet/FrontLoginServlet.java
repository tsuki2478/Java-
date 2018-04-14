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
 * Servlet 前台登录
 */
@WebServlet("/FrontLoginServlet")
public class FrontLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		IUserService service = new 	UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1：判断用户校验码是否正确
		String code = request.getParameter("Code");

		// 2：获取真实生成的校验码
		HttpSession session = request.getSession();
		String rand = (String) session.getAttribute("rand");
//			System.out.println(code+"" +rand); 
//		判断code和 session中rand 双方校验是否一致
		if (code.equals(rand)) {
//			表示校验码正确
	String userName	=		request.getParameter("UserName");
	String password =		request.getParameter("Password");
//		  效验账号密码是否正确。在读判断
				User user = service.login(userName, password);
			if (user != null) {
				session.setAttribute("USER", user);
//				表示登录成功（跳转到首页）
				response.sendRedirect("front/homeServlet");
			}else{
//				表示登录失败（跳转到登录界面）
				session.setAttribute("LOGIN_ERROR", "账号密码错误");
				response.sendRedirect("FrontLogin.jsp");
				}	
			}else{
//			校验码错误
//			直接跳转回登陆页码
				session.setAttribute("LOGIN_ERROR", "校验错误");
				response.sendRedirect("FrontLogin.jsp");
			
			
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
