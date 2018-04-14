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
		// 1查询出需要显示的数据（null代表无条件）
		List<McTypeBean> typeList = typeService.query(null);
		// 获取分页的相关参数
		int currentPage = WebUtils.getCurrentPage(request, 1);
		int pageSize = WebUtils.getPageSize(request, 3);
		//获取分页查询条件
			String mcname = request.getParameter("mcname");
			McBean	mc = new McBean();
			if (!WebUtils.isEmpty(mcname)) {
			//将查询条件封装到Mcmean条件
				mc.setMcname(mcname);
			}
			
		//商品信息的 
		 BasePage<McBean> page = new BasePage<>(currentPage, pageSize);
		 	page	= mcService.queryByPage(mc, page);		 					
		//放到作用域。
		 request.setAttribute("typeList", typeList);
		request.setAttribute("page", page);
		request.setAttribute("mcname", mcname);
		// 2跳转到home.jsp页面
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
