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
 * �¶���
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
			// ��ʾ����ҳ��ѯ����������ǰ�û�����Ϣ
			int currentPage = WebUtils.getCurrentPage(request, 1);
			int pageSize = WebUtils.getPageSize(request, 10);
//		   ��ȡ��ǰ�û��ı��
					HttpSession session = request.getSession();
						User user = (User) session.getAttribute("USER");
						int userId = user.getUserid();
//						ͨ��service������ѯ��ҳ����
					BasePage<OrderBean> page = service.queryByPage(currentPage, pageSize, userId);	
						request.setAttribute("page",page);
						request.getRequestDispatcher("filterpage/showOrder.jsp").forward(request, response);
		} else {
			add(request, response);
		}

	}

	/**
	 * �¶����ķ���
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/***************** ��ȡ���� ****************/
		// 1 ��ȡ�����ύ������
		String paytype = request.getParameter("paytype");
		String receivedtype = request.getParameter("receivedtype");
		String username = request.getParameter("username");
		String address = request.getParameter("address");
		String postcode = request.getParameter("postcode");
		String phoneno = request.getParameter("phoneno");
		String email = request.getParameter("email");
		// 2�ӹ��ﳵ�����л�ȡͳ����Ϣ
		HttpSession session = request.getSession();
		ShopCar car = (ShopCar) session.getAttribute("SESSION_SHOPCAR");
		int count = car.getCount();
		double totalPrice = car.getTotalPrice();
		int totalType = car.getTotalType();
		// 3�ӹ��ﳵ�����л�ȡ����������Ϣ
		List<McBean> list = car.getList();
		// ��������Ԕ����Ϣ
		List<OrderDetailBean> listDetail = new ArrayList<>();
		for (McBean mc : list) {
			// ��Ԕ����Ϣ���浽OrderDetailBean��
			OrderDetailBean d = new OrderDetailBean();
			d.setMcid(mc.getMcid());
			d.setBuynum(mc.getShopNum());
			listDetail.add(d);
		}
		// 4��session��ȡ��¼�û�����Ϣ
		User user = (User) session.getAttribute("USER");
		int userId = user.getUserid();

		/***************** ����ȡ�����ݱ��浽OrderBean�� ****************/
		// ����ȡ�����ݱ��浽���ݿ���
		OrderBean order = new OrderBean();
		order.setAddress(address); // �ջ���ַ
		order.setAlltype(totalType); // ��Ʒ�����
		order.setEmail(email); // ����
		order.setList(listDetail); // ������Ϣ
		order.setPaytype(paytype); // ���ʽ
		order.setPhoneno(phoneno); // �ջ��绰
		order.setPostcode(postcode); // �ջ��ʱ�
		order.setQuantity(count); // �ܵ�����
		order.setReceivedtype(receivedtype); // �ջ���ʽ
		order.setTotalprice(totalPrice); // �ܼ�
		order.setUserid(userId); // ������û����
		order.setUsername(username); // �ռ�������

		/***************** ��OrderBean���浽���ݿ� ****************/
		service.add(order);

		// ��չ��ﳵ
		car.clear();
		response.sendRedirect("member.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
