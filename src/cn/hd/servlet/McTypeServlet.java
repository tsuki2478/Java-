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
 * Servlet 商品类别 添加 修改 查询
 */
@WebServlet("/back/McTypeServlet")
public class McTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 获取service实例
	IMcTypeService service = new McTypeServiceImpl();

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		// System.out.println("winner");
		// 2？1获取客户端传递过来的标志
		//back/McTypeServlet?task=queryFather
		//back/McTypeServlet?task=del
		String task = request.getParameter("task");
//		Sting task="queryFather";
		if ("queryFather".equals(task)) {
			// 表示查询所有商品大类
			List<McTypeBean> list = service.queryFather();
			//将数据保存在作用域中
			request.setAttribute("typelist", list);
			//跳转mctype_add.jsp
			request.getRequestDispatcher("mctype/mctype_add.jsp").forward(request, response);
		}else if("add".equals(task)){
			String typeName = request.getParameter("typename");
			String fatherid = request.getParameter("fatherid");
//			System.out.println(typeName+""+fatherid);
//			2将获取的数据保存到McTypeBean
			McTypeBean type = new McTypeBean(typeName,
							WebUtils.parseInt(fatherid));
//			3通过service中的add方法将用户提交的数据	保存在数据库
			service.add(type);
			query(request, response);

		}else if("queryById".equals(task)){
//		根据id获取商品类信息
			String typeid = request.getParameter("typeid");
			
			McTypeBean type = service.queryById(WebUtils.parseInt(typeid));
//			查询出所有的商品大类
			List<McTypeBean> list = service.queryFather();
			request.setAttribute("type", type);
			request.setAttribute("typelist", list);
//			跳转到修改界面
			request.getRequestDispatcher("/back/mctype/mctype_update.jsp").forward(request, response);
			query(request, response);
			
	}else if("delete".equals(task)){
//			表示需要根据id删除数据
//			获取删除数据的编号
			String typeid = request.getParameter("typeid");
			 service.delete(WebUtils.parseInt(typeid));
//			 删除数据后跳转到查询所有数据页面
				query(request, response);
	}else if("update".equals(task)){
//		表示更新数据
//		1：获取表单提交的数据
		String typeid = request.getParameter("typeid");
		String typeName = request.getParameter("typename");
		String fatherid = request.getParameter("fatherid");
		
		//		2：通过service方法修改记录
 
			McTypeBean type = new McTypeBean(WebUtils.parseInt(typeid)
				,typeName,WebUtils.parseInt(fatherid));
		service.update(type);
		
//			3：跳转会查询所有记录的界面
			query(request, response);
		
		}else if("loadFatherType".equals(task)){
			//ajax获取所有的商品大类
			List<McTypeBean> list = service.queryFather();
			//转化为json
			JSONArray  json = new JSONArray(list);
			//相应给用户
			PrintWriter out = response.getWriter();
				out.write(json.toString());
				out.close();
		}else if("loadSmallType".equals(task)){
			//表示根据大类编号获取对应小类信息
			String typeid = request.getParameter("id");
			    List<McTypeBean> list = service.querySmall(WebUtils.parseInt(typeid));
			//转化为json
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
		// 2跳转到对应的jsp页面并且传递数据
		request.setAttribute("list", list);
		request.getRequestDispatcher("mctype/mctype.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
