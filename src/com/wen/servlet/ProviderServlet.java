package com.wen.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wen.dao.factory.DaoFactory;
import com.wen.dao.pojo.Provider;

public class ProviderServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action=request.getParameter("action");
		if("list".equals(action)){
			int size=7;
			int curPage=1;
			String curStrPage=request.getParameter("page");
			if(curStrPage!=null&&curStrPage!=""){
				curPage=Integer.parseInt(curStrPage)<1?1:Integer.parseInt(curStrPage);
			}
			int count=DaoFactory.getProviderDao().queryCount();
			request.setAttribute("count",count);
			int pageCount=count%size==0?count/size:count/size+1;
			if(curPage>pageCount&&pageCount!=0){
				curPage=pageCount;
			}else if(pageCount==0){
				curPage=1;
				pageCount=1;
			}
			request.setAttribute("pageCount",pageCount);
			request.setAttribute("curPage", curPage);
			List<Provider> list=DaoFactory.getProviderDao().queryPage((curPage-1)*size, size);
			request.setAttribute("list", list);
			request.getRequestDispatcher("provider/list.jsp").forward(request, response);
			
		}else if("delete".equals(action)){
			String pid=request.getParameter("pid");
			DaoFactory.getStockDao().deleteToProvider(Integer.parseInt(pid));
			int i=DaoFactory.getProviderDao().delete(pid);
			if(i>0){
				request.getRequestDispatcher("ProviderServlet?action=list").forward(request, response);
			}
		}else if("add".equals(action)){
			Provider provider=new Provider();
			provider.setPname(request.getParameter("pname"));
			provider.setPphone(request.getParameter("pphone"));
			provider.setPaddress(request.getParameter("paddress"));
			provider.setPlinkman(request.getParameter("plinkman"));
			DaoFactory.getProviderDao().add(provider);
			List<Provider> list=DaoFactory.getProviderDao().queryByName(request.getParameter("pname"));
			request.setAttribute("showPage", false);
			request.setAttribute("list", list);
			request.setAttribute("count", DaoFactory.getProviderDao().countByName(request.getParameter("pname")));
			request.getRequestDispatcher("provider/list.jsp").forward(request, response);

		}else if("update".equals(action)){
			String pid=request.getParameter("pid");
			Provider provider=DaoFactory.getProviderDao().queryObject(pid);
			request.setAttribute("provider", provider);
			request.getRequestDispatcher("provider/update.jsp").forward(request, response);
		}else if("do_update".equals(action)){
			Provider provider=new Provider();
			provider.setPid(Integer.parseInt(request.getParameter("pid")));
			provider.setPname(request.getParameter("pname"));
			provider.setPphone(request.getParameter("pphone"));
			provider.setPaddress(request.getParameter("paddress"));
			provider.setPlinkman(request.getParameter("plinkman"));
			int i=DaoFactory.getProviderDao().update(provider);
			request.setAttribute("count", 1);
			request.setAttribute("showPage",false);
			List<Provider> list=new ArrayList<Provider>();
			list.add(DaoFactory.getProviderDao().queryObject(String.valueOf(provider.getPid())));
			request.setAttribute("list", list);
			if(i>0){
				request.getRequestDispatcher("provider/list.jsp").forward(request, response);
			}else{
				request.setAttribute("error", "修改失败！");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}
		}else if("queryByKey".equals(action)){
			String key=request.getParameter("key");
			if(key==""){
				key=null;
			}
			List<Provider> list=DaoFactory.getProviderDao().queryByKey(key);
			request.setAttribute("showPage", false);
			request.setAttribute("list", list);
			request.setAttribute("count", DaoFactory.getProviderDao().countByKey(key));
			request.getRequestDispatcher("provider/list.jsp").forward(request, response);
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
