package cn.hd.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter 
 * 拦截所有的访问后台功能的请求。 如果登录放过。如果没有登录那么返回登录界面
 * http://localhost:8088/shop/back/..
 * 
 */
@WebFilter("/back/*")
public class BackPageFilter implements Filter {
 
    public BackPageFilter() {
        // TODO Auto-generated constructor stub
    }

 
	public void destroy() {
		// TODO Auto-generated method stub
	}

 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		判断是否登录。 不判断则全部拦截。
//		依据就是登录成功时候放入作用域中的BACK_USER
//		从session中取出BACK_USER
//		向下转型。	
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
			HttpSession session = req.getSession();
			Object obj = session.getAttribute("BACK_USER");
			     if (obj != null) {
					//表示已经登录了。给予进入后台
			chain.doFilter(request, response);
				}else{
//					表示没有登录情况下。直接调回。防止别人不登录直接访问后台
					session.setAttribute("LOGIN_ERROR", "请先登录");
					res.sendRedirect("../BackLogin.jsp");
				}
		}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
