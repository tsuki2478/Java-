package cn.hd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.smartcardio.Card;

import cn.hd.model.McBean;
import cn.hd.service.IMcSerivce;
import cn.hd.service.impl.McServiceImpl;
import cn.hd.utils.ShopCar;
import cn.hd.utils.WebUtils;

/**
 * Servlet 处理购物车相关操作
 * 
 */
@WebServlet("/front/ShopCarServlet")
public class ShopCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IMcSerivce service = new McServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String task = request.getParameter("task");
		// 2：获取购物车对象（因为都要获取对象。所有放在外面）
		HttpSession session = request.getSession();
		ShopCar car = ShopCar.getShopCar(session);
		if ("clear".equals(task)) {
			// 表示清空购物车
			car.clear();
			// 跳转回购物车页面
			response.sendRedirect("shopcar.jsp");
		} else if ("delete".equals(task)) {
			// 表示根据id移除信息
			String mcid = request.getParameter("mcid");
			car.delete(WebUtils.parseInt(mcid));
			// 跳转回购物车页面
			response.sendRedirect("shopcar.jsp");

		} else if ("update".equals(task)) {
			// 表示修改商品购买数量
			String mcid = request.getParameter("mcid");
			String num = request.getParameter("num");
			// 调用购物车的修改方法
			car.update(WebUtils.parseInt(mcid), WebUtils.parseInt(num));
			// 跳转回购物车页面
			response.sendRedirect("shopcar.jsp");
		} else {
			// 1:获取商品信息。获取请求提交的商品编号
			String mcid = request.getParameter("mcid");
			// 获取商品信息
			McBean mc = service.queryById(WebUtils.parseInt(mcid));

			// 3：网购物车对象添加商品信息
			car.add(mc);
			// 4 服务给出相应
			PrintWriter out = response.getWriter();
			// 响应的信息不要是int类型 所有要+""
			out.write(car.getCount() + "");
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
