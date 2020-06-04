package com.wen.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wen.dao.factory.DaoFactory;
import com.wen.dao.pojo.Vip;

public class VipServlet extends HttpServlet {

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
			int count=DaoFactory.getVipDao().queryCount();
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
			List<Vip> list=DaoFactory.getVipDao().queryPage((curPage-1)*size, size);
			request.setAttribute("list", list);
			request.getRequestDispatcher("vip/list.jsp").forward(request, response);
			
		}else if("delete".equals(action)){
			String vid=request.getParameter("vid");
			DaoFactory.getSaveRecordDao().deleteToVip(vid);
			int i=DaoFactory.getVipDao().delete(vid);
			if(i>0){
				request.getRequestDispatcher("VipServlet?action=list").forward(request, response);
			}
		}else if("add".equals(action)){
			Vip vip=new Vip();
			vip.setVid(request.getParameter("vid"));
			vip.setVname(request.getParameter("vname"));
			vip.setVphone(request.getParameter("vphone"));
			vip.setVrate(Float.parseFloat(request.getParameter("vrate")));
			try {
				vip.setVbirthday(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("vbirthday")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(DaoFactory.getVipDao().queryObject(vip.getVid()).getVid()==null){
				DaoFactory.getVipDao().add(vip);
				List<Vip> list=new ArrayList<Vip>();
				list.add(vip);
				request.setAttribute("showPage", false);
				request.setAttribute("list", list);
				request.setAttribute("count", 1);
				request.getRequestDispatcher("vip/list.jsp").forward(request, response);
			}else{
				request.setAttribute("error", "添加失败！");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}			
		}else if("update".equals(action)){
			String vid=request.getParameter("vid");
			Vip vip=DaoFactory.getVipDao().queryObject(vid);
			request.setAttribute("vip", vip);
			request.getRequestDispatcher("vip/update.jsp").forward(request, response);
		}else if("do_update".equals(action)){
			Vip vip=new Vip();
			vip.setVid(request.getParameter("vid"));
			vip.setVname(request.getParameter("vname"));
			vip.setVphone(request.getParameter("vphone"));
			vip.setVrate(Float.parseFloat(request.getParameter("vrate")));
			try {
				vip.setVbirthday(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("vbirthday")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int i=DaoFactory.getVipDao().update(vip);
			request.setAttribute("count", 1);
			request.setAttribute("showPage",false);
			List<Vip> list=new ArrayList<Vip>();
			list.add(DaoFactory.getVipDao().queryObject(vip.getVid()));
			request.setAttribute("list", list);
			if(i>0){
				request.getRequestDispatcher("vip/list.jsp").forward(request, response);
			}else{
				request.setAttribute("error", "修改失败！");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}
		}else if("queryByKey".equals(action)){
			String key=request.getParameter("key");
			if(key==""){
				key=null;
			}
			List<Vip> list=DaoFactory.getVipDao().queryByKey(key);
			request.setAttribute("showPage", false);
			request.setAttribute("list", list);
			request.setAttribute("count", DaoFactory.getVipDao().countByKey(key));
			request.getRequestDispatcher("vip/list.jsp").forward(request, response);
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
