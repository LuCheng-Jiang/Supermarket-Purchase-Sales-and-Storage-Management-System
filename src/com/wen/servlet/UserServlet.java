package com.wen.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wen.dao.factory.DaoFactory;
import com.wen.dao.pojo.User;

public class UserServlet extends HttpServlet {

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
		if("login".equals(action)){
			String uid=request.getParameter("uid");
			String upassword=request.getParameter("upassword");
			User user=DaoFactory.getUserDao().login(uid, upassword);
			if(user.getUid()==null){
				request.setAttribute("error", "登陆失败！");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}else{
				String uname=DaoFactory.getUserDao().queryObject(user.getUid()).getUname();
				HttpSession httpSession=request.getSession();
				httpSession.setAttribute("user", user);//设置过滤器
				switch (user.getUrole()) {
				case 0:
					response.sendRedirect("operator.jsp");
					break;
				case 1:
					response.sendRedirect("admin.jsp");
					break;
				default:
					break;
				}
			}
		}else if("list".equals(action)){
			int size=7;
			int curPage=1;
			String curStrPage=request.getParameter("page");
			if(curStrPage!=null&&curStrPage!=""){
				curPage=Integer.parseInt(curStrPage)<1?1:Integer.parseInt(curStrPage);
			}
			int count=DaoFactory.getUserDao().queryCount();
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
			List<User> list=list=DaoFactory.getUserDao().queryPage((curPage-1)*size, size);
			request.setAttribute("list", list);
			request.getRequestDispatcher("user/list.jsp").forward(request, response);
			
		}else if("delete".equals(action)){
			String uid=request.getParameter("uid");
			DaoFactory.getSaveRecordDao().deleteToUser(uid);
			int i=DaoFactory.getUserDao().delete(uid);
			if(i>0){
				request.getRequestDispatcher("UserServlet?action=list").forward(request, response);
			}
		}else if("add".equals(action)){
			User user=new User();
			user.setUid(request.getParameter("uid"));
			user.setUpassword(request.getParameter("upassword"));
			user.setUname(request.getParameter("uname"));
			user.setUrole(Integer.parseInt(request.getParameter("urole")));
			if(DaoFactory.getUserDao().queryObject(user.getUid()).getUid()==null){
				DaoFactory.getUserDao().add(user);
				List<User> list=new ArrayList<User>();
				list.add(user);
				request.setAttribute("showPage", false);
				request.setAttribute("list", list);
				request.setAttribute("count", 1);
				request.getRequestDispatcher("user/list.jsp").forward(request, response);
			}else{
				request.setAttribute("error", "添加失败！");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}
		}else if("update".equals(action)){
			String uid=request.getParameter("uid");
			User user=DaoFactory.getUserDao().queryObject(uid);
			request.setAttribute("user", user);
			request.getRequestDispatcher("user/update.jsp").forward(request, response);
		}else if("do_update".equals(action)){
			User user=new User();
			user.setUid(request.getParameter("uid"));
			user.setUpassword(request.getParameter("upassword"));
			user.setUname(request.getParameter("uname"));
			user.setUrole(Integer.parseInt(request.getParameter("urole")));
			int i=DaoFactory.getUserDao().update(user);
			request.setAttribute("count", 1);
			request.setAttribute("showPage",false);
			List<User> list=new ArrayList<User>();
			list.add(DaoFactory.getUserDao().queryObject(user.getUid()));
			request.setAttribute("list", list);
			if(i>0){
				request.getRequestDispatcher("user/list.jsp").forward(request, response);
			}else{
				request.setAttribute("error", "修改失败！");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}
		}else if("query_object".equals(action)){
			String uid=request.getParameter("uid");
			User user=DaoFactory.getUserDao().queryObject(uid);
			List<User> list=new ArrayList<User>();
			if(user.getUid()!=null){
				list.add(user);
			}
			request.setAttribute("showPage", false);
			request.setAttribute("list", list);
			request.setAttribute("count", DaoFactory.getUserDao().countById(user.getUid()));
			request.getRequestDispatcher("user/list.jsp").forward(request, response);
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
