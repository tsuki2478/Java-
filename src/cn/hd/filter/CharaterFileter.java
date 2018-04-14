package cn.hd.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 处理中文乱码
 */
@WebFilter("*")
public class CharaterFileter implements Filter {
 
    public CharaterFileter() {
             }
 
	public void destroy() {
 	}
 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
//		1请求处理中文乱码
		request.setCharacterEncoding("utf-8");
//		2处理响应的中文乱码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		 
		chain.doFilter(request, response);
	}
 
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
