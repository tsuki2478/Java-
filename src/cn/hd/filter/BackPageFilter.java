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
 * �������еķ��ʺ�̨���ܵ����� �����¼�Ź������û�е�¼��ô���ص�¼����
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
//		�ж��Ƿ��¼�� ���ж���ȫ�����ء�
//		���ݾ��ǵ�¼�ɹ�ʱ������������е�BACK_USER
//		��session��ȡ��BACK_USER
//		����ת�͡�	
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
			HttpSession session = req.getSession();
			Object obj = session.getAttribute("BACK_USER");
			     if (obj != null) {
					//��ʾ�Ѿ���¼�ˡ���������̨
			chain.doFilter(request, response);
				}else{
//					��ʾû�е�¼����¡�ֱ�ӵ��ء���ֹ���˲���¼ֱ�ӷ��ʺ�̨
					session.setAttribute("LOGIN_ERROR", "���ȵ�¼");
					res.sendRedirect("../BackLogin.jsp");
				}
		}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
