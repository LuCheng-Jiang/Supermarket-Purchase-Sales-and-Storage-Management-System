package com.wen.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wen.dao.factory.DaoFactory;
import com.wen.dao.pojo.Product;
import com.wen.dao.pojo.Provider;
import com.wen.dao.pojo.Stock;

public class StockServlet extends HttpServlet {

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
		if("before_add".equals(action)){
			List<Product> product=DaoFactory.getProductDao().queryAll();
			List<Provider> provider=DaoFactory.getProviderDao().queryAll();
			request.setAttribute("product", product);
			request.setAttribute("provider", provider);
			request.getRequestDispatcher("stock/add.jsp").forward(request, response);
		}else if("add".equals(action)){
			Stock stock =new Stock();
			String sid=request.getParameter("sid");
			String providerId=request.getParameter("providerId");
			String productId=request.getParameter("productId");
			int samount=0;
			if(request.getParameter("samount")!=""&&request.getParameter("samount")!=null){
				samount=Integer.parseInt(request.getParameter("samount"));
			}
			Float sprice=0f;
			if(request.getParameter("sprice")!=""&&request.getParameter("sprice")!=null){
				sprice=Float.parseFloat(request.getParameter("sprice"));
			}
			stock.setSid(sid);
			stock.setProductId(productId);
			stock.setSamount(samount);
			stock.setSprice(sprice);
			stock.setProviderId(Integer.parseInt(providerId));
			if(DaoFactory.getStockDao().queryObject(sid).getSid()!=null){
				request.setAttribute("error", "进货流水号重复！");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}
			else if(samount==0){
				request.setAttribute("error", "进货数量为0！");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}else{
				int amount=samount+DaoFactory.getProductDao().queryObject(productId).getPamount();
				DaoFactory.getProductDao().updatePamount(productId, amount);
				DaoFactory.getStockDao().add(stock);
				List<Product> list=new ArrayList<Product>();
				list.add(DaoFactory.getProductDao().queryObject(productId));
				request.setAttribute("list", list);
				request.getRequestDispatcher("product/list.jsp").forward(request, response);
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
