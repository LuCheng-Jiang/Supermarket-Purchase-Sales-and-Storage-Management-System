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
import com.wen.dao.pojo.Product;
import com.wen.dao.pojo.Unit;
import com.wen.util.IDUtil;
import com.wen.util.QRCodeUtil;

public class ProductServlet extends HttpServlet {

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
			int count=DaoFactory.getProductDao().queryCount();
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
			List<Product> list=DaoFactory.getProductDao().queryPage((curPage-1)*size, size);
			request.setAttribute("list", list);
			request.getRequestDispatcher("product/list.jsp").forward(request, response);
			
		}else if("delete".equals(action)){
			String pid=request.getParameter("pid");
			DaoFactory.getStockDao().deleteToProduct(pid);
			int i=DaoFactory.getProductDao().delete(pid);
			if(i>0){
				request.getRequestDispatcher("ProductServlet?action=list").forward(request, response);
			}
		}else if("before_add".equals(action)){
			List<Category> category=DaoFactory.getCategoryDao().queryAll();
			List<Unit> unit=DaoFactory.getUnitDao().queryAll();
			request.setAttribute("category", category);
			request.setAttribute("unit", unit);
			request.getRequestDispatcher("product/add.jsp").forward(request, response);
		}else if("add".equals(action)){
			String path = "/images/qrcode/"+IDUtil.getId()+".png";
			Product product=new Product();
			product.setPid(request.getParameter("pid"));
			product.setPname(request.getParameter("pname"));
			product.setCid(Integer.parseInt(request.getParameter("cid")));
			product.setPspec(request.getParameter("pspec"));
			product.setUid(Integer.parseInt(request.getParameter("uid")));
			product.setPic(path);
			int pminNumber=0;
			if(request.getParameter("pminNumber")!=null&&request.getParameter("pminNumber")!=""){
				pminNumber=Integer.parseInt(request.getParameter("pminNumber"));
			}
			product.setPminNumber(pminNumber);
			float salePrice=0f;
			if(request.getParameter("salePrice")!=null&&request.getParameter("salePrice")!=""){
				salePrice=Float.parseFloat(request.getParameter("salePrice"));
			}
			product.setSalePrice(salePrice);
			float vipPrice=0f;
			if(request.getParameter("vipPrice")!=null&&request.getParameter("vipPrice")!=""){
				vipPrice=Float.parseFloat(request.getParameter("vipPrice"));
			}
			product.setVipPrice(vipPrice);
			if(DaoFactory.getProductDao().queryObject(product.getPid()).getPid()==null){
				DaoFactory.getProductDao().add(product);
				
				QRCodeUtil.Qrcode(product, request, path);
				
				List<Product> list=new ArrayList<Product>();
				list.add(DaoFactory.getProductDao().queryObject(product.getPid()));
				request.setAttribute("showPage", false);
				request.setAttribute("list", list);
				request.setAttribute("count", 1);
				request.getRequestDispatcher("product/list.jsp").forward(request, response);
			}else{
				request.setAttribute("error", "添加失败！");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}			
		}else if("update".equals(action)){
			List<Category> category=DaoFactory.getCategoryDao().queryAll();
			List<Unit> unit=DaoFactory.getUnitDao().queryAll();
			request.setAttribute("category", category);
			request.setAttribute("unit", unit);
			String pid=request.getParameter("pid");
			Product product=DaoFactory.getProductDao().queryObject(pid);
			request.setAttribute("product", product);
			request.getRequestDispatcher("product/update.jsp").forward(request, response);
		}else if("do_update".equals(action)){
			String path = "/images/qrcode/"+IDUtil.getId()+".png";
			
			Product product=new Product();
			product.setPid(request.getParameter("pid"));
			product.setPname(request.getParameter("pname"));
			product.setCid(Integer.parseInt(request.getParameter("cid")));
			product.setPspec(request.getParameter("pspec"));
			product.setUid(Integer.parseInt(request.getParameter("uid")));
			product.setPminNumber(Integer.parseInt(request.getParameter("pminNumber")));
			product.setSalePrice(Float.parseFloat(request.getParameter("salePrice")));
			product.setVipPrice(Float.parseFloat(request.getParameter("vipPrice")));
			product.setPic(path);
			int i=DaoFactory.getProductDao().update(product);
			request.setAttribute("count", 1);
			request.setAttribute("showPage",false);
			List<Product> list=new ArrayList<Product>();
			list.add(DaoFactory.getProductDao().queryObject(product.getPid()));
			request.setAttribute("list", list);
			
			QRCodeUtil.Qrcode(product, request, path);
			
			if(i>0){
				request.getRequestDispatcher("product/list.jsp").forward(request, response);
			}else{
				request.setAttribute("error", "修改失败！");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}
		}else if("queryByKey".equals(action)){
			String key=request.getParameter("key");
			if(key==""){
				key=null;
			}
			List<Product> list=DaoFactory.getProductDao().queryByKey(key);
			request.setAttribute("showPage", false);
			request.setAttribute("list", list);
			request.setAttribute("count", DaoFactory.getProductDao().countByKey(key));
			request.getRequestDispatcher("product/list.jsp").forward(request, response);
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
