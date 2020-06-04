package com.wen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wen.dao.factory.DaoFactory;
import com.wen.dao.pojo.SaveRecord;

public class SaveRecordServlet extends HttpServlet {

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

		SaveRecord save=new SaveRecord();
		String vid=request.getParameter("vid");
		save.setVid(vid);
		Float smoney=0f;
		if(request.getParameter("smoney")!=""&&request.getParameter("smoney")!=null){
			smoney=Float.parseFloat(request.getParameter("smoney"));
		}
		save.setSmoney(smoney);
		String uid=request.getParameter("uid");
		save.setUid(uid);
		if(DaoFactory.getVipDao().queryObject(vid).getVid()==null){
			request.setAttribute("error", "会员卡号不存在！");
			request.getRequestDispatcher("error.jsp").forward(request,response);
		}else if(smoney==0f){
			request.setAttribute("error", "充值金额为0！");
			request.getRequestDispatcher("error.jsp").forward(request,response);
		}else{
			float money=smoney+DaoFactory.getVipDao().queryObject(vid).getVbalance();
			DaoFactory.getVipDao().updateVbalance(vid, money);
			DaoFactory.getSaveRecordDao().add(save);
			request.getRequestDispatcher("VipServlet?action=list").forward(request,response);
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
