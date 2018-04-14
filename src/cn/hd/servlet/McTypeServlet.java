package cn.hd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import cn.hd.model.McTypeBean;
import cn.hd.service.IMcTypeService;
import cn.hd.service.impl.McTypeServiceImpl;
import cn.hd.utils.WebUtils;


/**
 * Servlet ��Ʒ��� ��� �޸� ��ѯ
 */
@WebServlet("/back/McTypeServlet")
public class McTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ��ȡserviceʵ��
	IMcTypeService service = new McTypeServiceImpl();

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		// System.out.println("winner");
		// 2��1��ȡ�ͻ��˴��ݹ����ı�־
		//back/McTypeServlet?task=queryFather
		//back/McTypeServlet?task=del
		String task = request.getParameter("task");
//		Sting task="queryFather";
		if ("queryFather".equals(task)) {
			// ��ʾ��ѯ������Ʒ����
			List<McTypeBean> list = service.queryFather();
			//�����ݱ�������������
			request.setAttribute("typelist", list);
			//��תmctype_add.jsp
			request.getRequestDispatcher("mctype/mctype_add.jsp").forward(request, response);
		}else if("add".equals(task)){
			String typeName = request.getParameter("typename");
			String fatherid = request.getParameter("fatherid");
//			System.out.println(typeName+""+fatherid);
//			2����ȡ�����ݱ��浽McTypeBean
			McTypeBean type = new McTypeBean(typeName,
							WebUtils.parseInt(fatherid));
//			3ͨ��service�е�add�������û��ύ������	���������ݿ�
			service.add(type);
			query(request, response);

		}else if("queryById".equals(task)){
//		����id��ȡ��Ʒ����Ϣ
			String typeid = request.getParameter("typeid");
			
			McTypeBean type = service.queryById(WebUtils.parseInt(typeid));
//			��ѯ�����е���Ʒ����
			List<McTypeBean> list = service.queryFather();
			request.setAttribute("type", type);
			request.setAttribute("typelist", list);
//			��ת���޸Ľ���
			request.getRequestDispatcher("/back/mctype/mctype_update.jsp").forward(request, response);
			query(request, response);
			
	}else if("delete".equals(task)){
//			��ʾ��Ҫ����idɾ������
//			��ȡɾ�����ݵı��
			String typeid = request.getParameter("typeid");
			 service.delete(WebUtils.parseInt(typeid));
//			 ɾ�����ݺ���ת����ѯ��������ҳ��
				query(request, response);
	}else if("update".equals(task)){
//		��ʾ��������
//		1����ȡ���ύ������
		String typeid = request.getParameter("typeid");
		String typeName = request.getParameter("typename");
		String fatherid = request.getParameter("fatherid");
		
		//		2��ͨ��service�����޸ļ�¼
 
			McTypeBean type = new McTypeBean(WebUtils.parseInt(typeid)
				,typeName,WebUtils.parseInt(fatherid));
		service.update(type);
		
//			3����ת���ѯ���м�¼�Ľ���
			query(request, response);
		
		}else if("loadFatherType".equals(task)){
			//ajax��ȡ���е���Ʒ����
			List<McTypeBean> list = service.queryFather();
			//ת��Ϊjson
			JSONArray  json = new JSONArray(list);
			//��Ӧ���û�
			PrintWriter out = response.getWriter();
				out.write(json.toString());
				out.close();
		}else if("loadSmallType".equals(task)){
			//��ʾ���ݴ����Ż�ȡ��ӦС����Ϣ
			String typeid = request.getParameter("id");
			    List<McTypeBean> list = service.querySmall(WebUtils.parseInt(typeid));
			//ת��Ϊjson
			JSONArray  json = new JSONArray(list);
			PrintWriter out = response.getWriter();
			out.write(json.toString());
			out.close();
		}else {
			query(request, response);

		}

	}

	private void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<McTypeBean> list = service.query(null);
		// 2��ת����Ӧ��jspҳ�沢�Ҵ�������
		request.setAttribute("list", list);
		request.getRequestDispatcher("mctype/mctype.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
