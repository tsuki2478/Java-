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
 * Servlet		后台管理员登录
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
//		1:获取用户提交的登录数据
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
//		2：通过service中login方法效验是否登录成功
		
			ManagerBean manager = service.login(userName, password);		   
							HttpSession session = request.getSession();
//		3：根据效验的结果做出不同相应
				   	if (manager != null) {
//						表示登录成功
//				   		将当前登录的用户信息保存在sesssion作用域中，方便随时登录用户信息
						session.setAttribute("BACK_USER", manager);
		 	request.getRequestDispatcher("back/main.jsp").forward(request, response);
					}else{
//						表示登录失败
//						向session作用域中保存一个错误
				session.setAttribute("LOGIN_ERROR", "登录失败");
				response.sendRedirect("BackLogin.jsp");		
						
					}
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
