package com.wen.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wen.dao.factory.DaoFactory;

public class RecordServlet extends HttpServlet {

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

		String query=request.getParameter("query");
		String start="1970-01-01";
		if(request.getParameter("start")!=null&&request.getParameter("start")!=""){
			start=request.getParameter("start");
		}
		String end=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if(request.getParameter("end")!=null&&request.getParameter("end")!=""){
			end=request.getParameter("end");
		}
		
		if(start.compareTo(end)>-1){
			request.setAttribute("error", "起始日期大于等于结束日期！");
			request.getRequestDispatcher("error.jsp").forward(request,response);
		}else{
			request.setAttribute("start", start);
			request.setAttribute("end", end);
			request.setAttribute("query", query);
			if("saveRecord".equals(query)){
				request.setAttribute("list", DaoFactory.getSaveRecordDao().queryFromTo(start, end+" 24:00:00"));
				request.getRequestDispatcher("record/saveRecord.jsp").forward(request, response);
			}else if("sale".equals(query)){
				request.setAttribute("list", DaoFactory.getSaleDao().queryFromTo(start, end+" 24:00:00"));
				request.getRequestDispatcher("record/sale.jsp").forward(request, response);
			}else if("saleItem".equals(query)){
				request.setAttribute("list", DaoFactory.getSaleItemDao().queryFromTo(start, end+" 24:00:00"));
				request.getRequestDispatcher("record/saleItem.jsp").forward(request, response);
			}else if("stock".equals(query)){
				request.setAttribute("list", DaoFactory.getStockDao().queryFromTo(start, end+" 24:00:00"));
				request.getRequestDispatcher("record/stock.jsp").forward(request, response);
			}
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
