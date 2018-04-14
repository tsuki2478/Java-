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
 * Servlet �����ﳵ��ز���
 * 
 */
@WebServlet("/front/ShopCarServlet")
public class ShopCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IMcSerivce service = new McServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String task = request.getParameter("task");
		// 2����ȡ���ﳵ������Ϊ��Ҫ��ȡ�������з������棩
		HttpSession session = request.getSession();
		ShopCar car = ShopCar.getShopCar(session);
		if ("clear".equals(task)) {
			// ��ʾ��չ��ﳵ
			car.clear();
			// ��ת�ع��ﳵҳ��
			response.sendRedirect("shopcar.jsp");
		} else if ("delete".equals(task)) {
			// ��ʾ����id�Ƴ���Ϣ
			String mcid = request.getParameter("mcid");
			car.delete(WebUtils.parseInt(mcid));
			// ��ת�ع��ﳵҳ��
			response.sendRedirect("shopcar.jsp");

		} else if ("update".equals(task)) {
			// ��ʾ�޸���Ʒ��������
			String mcid = request.getParameter("mcid");
			String num = request.getParameter("num");
			// ���ù��ﳵ���޸ķ���
			car.update(WebUtils.parseInt(mcid), WebUtils.parseInt(num));
			// ��ת�ع��ﳵҳ��
			response.sendRedirect("shopcar.jsp");
		} else {
			// 1:��ȡ��Ʒ��Ϣ����ȡ�����ύ����Ʒ���
			String mcid = request.getParameter("mcid");
			// ��ȡ��Ʒ��Ϣ
			McBean mc = service.queryById(WebUtils.parseInt(mcid));

			// 3�������ﳵ���������Ʒ��Ϣ
			car.add(mc);
			// 4 ���������Ӧ
			PrintWriter out = response.getWriter();
			// ��Ӧ����Ϣ��Ҫ��int���� ����Ҫ+""
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
