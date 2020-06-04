package com.wen.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wen.dao.factory.DaoFactory;
import com.wen.dao.pojo.Unit;

public class UnitServlet extends HttpServlet {

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
			int count=DaoFactory.getUnitDao().queryCount();
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
			List<Unit> list=DaoFactory.getUnitDao().queryPage((curPage-1)*size, size);
			request.setAttribute("list", list);
			request.getRequestDispatcher("unit/list.jsp").forward(request, response);
			
		}else if("delete".equals(action)){
			String uid=request.getParameter("uid");
			DaoFactory.getProductDao().deleteToUnit(Integer.parseInt(uid));
			int i=DaoFactory.getUnitDao().delete(uid);
			if(i>0){
				request.getRequestDispatcher("UnitServlet?action=list").forward(request, response);
			}
		}else if("add".equals(action)){
			Unit unit=new Unit();
			unit.setUname(request.getParameter("uname"));
			DaoFactory.getUnitDao().add(unit);
			List<Unit> list=DaoFactory.getUnitDao().queryByName(request.getParameter("uname"));
			request.setAttribute("showPage", false);
			request.setAttribute("list", list);
			request.setAttribute("count", DaoFactory.getUnitDao().countByName(request.getParameter("uname")));
			request.getRequestDispatcher("unit/list.jsp").forward(request, response);

		}else if("update".equals(action)){
			String uid=request.getParameter("uid");
			Unit unit=DaoFactory.getUnitDao().queryObject(uid);
			request.setAttribute("unit", unit);
			request.getRequestDispatcher("unit/update.jsp").forward(request, response);
		}else if("do_update".equals(action)){
			Unit unit=new Unit();
			unit.setUid(Integer.parseInt(request.getParameter("uid")));
			unit.setUname(request.getParameter("uname"));
			int i=DaoFactory.getUnitDao().update(unit);
			request.setAttribute("count", 1);
			request.setAttribute("showPage",false);
			List<Unit> list=new ArrayList<Unit>();
			list.add(DaoFactory.getUnitDao().queryObject(String.valueOf(unit.getUid())));
			request.setAttribute("list", list);
			if(i>0){
				request.getRequestDispatcher("unit/list.jsp").forward(request, response);
			}else{
				request.setAttribute("error", "修改失败！");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}
		}else if("queryByName".equals(action)){
			String uname=request.getParameter("uname");
			List<Unit> list=DaoFactory.getUnitDao().queryByName(uname);
			request.setAttribute("showPage", false);
			request.setAttribute("list", list);
			request.setAttribute("count", DaoFactory.getUnitDao().countByName(uname));
			request.getRequestDispatcher("unit/list.jsp").forward(request, response);
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
