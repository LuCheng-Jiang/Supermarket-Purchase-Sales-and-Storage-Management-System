package com.wen.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wen.dao.factory.DaoFactory;
import com.wen.dao.pojo.Category;

public class CategoryServlet extends HttpServlet {

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
			int count=DaoFactory.getCategoryDao().queryCount();
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
			List<Category> list=DaoFactory.getCategoryDao().queryPage((curPage-1)*size, size);
			request.setAttribute("list", list);
			request.getRequestDispatcher("category/list.jsp").forward(request, response);
			
		}else if("delete".equals(action)){
			String cid=request.getParameter("cid");
			DaoFactory.getProductDao().deleteToCategory(Integer.parseInt(cid));
			int i=DaoFactory.getCategoryDao().delete(cid);
			if(i>0){
				request.getRequestDispatcher("CategoryServlet?action=list").forward(request, response);
			}
		}else if("add".equals(action)){
			Category category=new Category();
			category.setCname(request.getParameter("cname"));
			DaoFactory.getCategoryDao().add(category);
			List<Category> list=DaoFactory.getCategoryDao().queryByName(request.getParameter("cname"));
			request.setAttribute("showPage", false);
			request.setAttribute("list", list);
			request.setAttribute("count", DaoFactory.getCategoryDao().countByName(request.getParameter("cname")));
			request.getRequestDispatcher("category/list.jsp").forward(request, response);

		}else if("update".equals(action)){
			String cid=request.getParameter("cid");
			Category category=DaoFactory.getCategoryDao().queryObject(cid);
			request.setAttribute("category", category);
			request.getRequestDispatcher("category/update.jsp").forward(request, response);
		}else if("do_update".equals(action)){
			Category category=new Category();
			category.setCid(Integer.parseInt(request.getParameter("cid")));
			category.setCname(request.getParameter("cname"));
			int i=DaoFactory.getCategoryDao().update(category);
			request.setAttribute("count", 1);
			request.setAttribute("showPage",false);
			List<Category> list=new ArrayList<Category>();
			list.add(DaoFactory.getCategoryDao().queryObject(String.valueOf(category.getCid())));
			request.setAttribute("list", list);
			if(i>0){
				request.getRequestDispatcher("category/list.jsp").forward(request, response);
			}else{
				request.setAttribute("error", "修改失败！");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}
		}else if("queryByName".equals(action)){
			String cname=request.getParameter("cname");
			List<Category> list=DaoFactory.getCategoryDao().queryByName(cname);
			request.setAttribute("showPage", false);
			request.setAttribute("list", list);
			request.setAttribute("count", DaoFactory.getCategoryDao().countByName(cname));
			request.getRequestDispatcher("category/list.jsp").forward(request, response);
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
