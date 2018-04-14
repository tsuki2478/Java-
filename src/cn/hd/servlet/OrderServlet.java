package cn.hd.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.hd.model.McBean;
import cn.hd.model.OrderBean;
import cn.hd.model.OrderDetailBean;
import cn.hd.model.User;
import cn.hd.service.IOrderService;
import cn.hd.service.impl.OrderServiceImpl;
import cn.hd.utils.BasePage;
import cn.hd.utils.ShopCar;
import cn.hd.utils.WebUtils;

/**
 * 下订单
 * 
 * @param request
 * @param response
 * @throws IOException
 */
@WebServlet("/front/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IOrderService service = new OrderServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String task = request.getParameter("task");
		if ("query".equals(task)) {
			// 表示当分页查询订单主主表当前用户的信息
			int currentPage = WebUtils.getCurrentPage(request, 1);
			int pageSize = WebUtils.getPageSize(request, 10);
//		   获取当前用户的编号
					HttpSession session = request.getSession();
						User user = (User) session.getAttribute("USER");
						int userId = user.getUserid();
//						通过service方法查询分页数据
					BasePage<OrderBean> page = service.queryByPage(currentPage, pageSize, userId);	
						request.setAttribute("page",page);
						request.getRequestDispatcher("filterpage/showOrder.jsp").forward(request, response);
		} else {
			add(request, response);
		}

	}

	/**
	 * 下订单的方法
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/***************** 获取数据 ****************/
		// 1 获取表单中提交的数据
		String paytype = request.getParameter("paytype");
		String receivedtype = request.getParameter("receivedtype");
		String username = request.getParameter("username");
		String address = request.getParameter("address");
		String postcode = request.getParameter("postcode");
		String phoneno = request.getParameter("phoneno");
		String email = request.getParameter("email");
		// 2从购物车对象中获取统计信息
		HttpSession session = request.getSession();
		ShopCar car = (ShopCar) session.getAttribute("SESSION_SHOPCAR");
		int count = car.getCount();
		double totalPrice = car.getTotalPrice();
		int totalType = car.getTotalType();
		// 3从购物车对象中获取订单详情信息
		List<McBean> list = car.getList();
		// 保存所有詳情信息
		List<OrderDetailBean> listDetail = new ArrayList<>();
		for (McBean mc : list) {
			// 將詳情信息保存到OrderDetailBean中
			OrderDetailBean d = new OrderDetailBean();
			d.setMcid(mc.getMcid());
			d.setBuynum(mc.getShopNum());
			listDetail.add(d);
		}
		// 4从session获取登录用户的信息
		User user = (User) session.getAttribute("USER");
		int userId = user.getUserid();

		/***************** 将获取的数据保存到OrderBean中 ****************/
		// 将获取的数据保存到数据库中
		OrderBean order = new OrderBean();
		order.setAddress(address); // 收货地址
		order.setAlltype(totalType); // 商品总类别
		order.setEmail(email); // 邮箱
		order.setList(listDetail); // 详情信息
		order.setPaytype(paytype); // 付款方式
		order.setPhoneno(phoneno); // 收货电话
		order.setPostcode(postcode); // 收货邮编
		order.setQuantity(count); // 总的数量
		order.setReceivedtype(receivedtype); // 收货方式
		order.setTotalprice(totalPrice); // 总价
		order.setUserid(userId); // 购买的用户编号
		order.setUsername(username); // 收件人姓名

		/***************** 将OrderBean保存到数据库 ****************/
		service.add(order);

		// 清空购物车
		car.clear();
		response.sendRedirect("member.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
