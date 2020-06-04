package com.wen.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wen.dao.factory.DaoFactory;
import com.wen.dao.pojo.Product;
import com.wen.dao.pojo.Sale;
import com.wen.dao.pojo.SaleItem;
import com.wen.dao.pojo.User;
import com.wen.dao.pojo.Vip;

public class CheckoutServlet extends HttpServlet {

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
		HttpSession session=request.getSession();
		List<Product> list=new ArrayList<Product>();
		if(session.getAttribute("list")!=null){
			list=(ArrayList<Product>)session.getAttribute("list");
		}
		Map<String, Integer> map=new HashMap<String, Integer>();
		if(session.getAttribute("map")!=null){
			map=(HashMap<String, Integer>)session.getAttribute("map");
		}
		Map<String,Float> sum=new HashMap<String, Float>();
		if(session.getAttribute("sum")!=null){
			sum=(HashMap<String, Float>) session.getAttribute("sum");
		}
		
		if("list".equals(action)){
			String addPid=request.getParameter("addPid");
			int addAmount=1;
			if(request.getParameter("addAmount")!=null&&request.getParameter("addAmount")!=""){
				addAmount=Integer.parseInt(request.getParameter("addAmount"));
			}
			if(addPid!=null){
				if(DaoFactory.getProductDao().queryObject(addPid).getPid()!=null){
					if(map.get(addPid)!=null){
						map.put(addPid, map.get(addPid)+addAmount);
					}else{
						map.put(addPid, addAmount);
						list.add(DaoFactory.getProductDao().queryObject(addPid));
					}
					if(session.getAttribute("vip")!=null){
						sum.put(addPid,Float.parseFloat(new DecimalFormat("0.00").format(map.get(addPid)*DaoFactory.getProductDao().queryObject(addPid).getVipPrice())));
					}else{
						sum.put(addPid, Float.parseFloat(new DecimalFormat("0.00").format(map.get(addPid)*DaoFactory.getProductDao().queryObject(addPid).getSalePrice())));
					}
				}
			}
			session.setAttribute("list", list);
			session.setAttribute("map", map);
			session.setAttribute("sum", sum);
			request.getRequestDispatcher("checkout/list.jsp").forward(request, response);
		}else if("delete".equals(action)){
			String pid=request.getParameter("pid");
			list.remove(DaoFactory.getProductDao().queryObject(pid));
			map.remove(pid);
			sum.remove(pid);
			session.setAttribute("list", list);
			session.setAttribute("map", map);
			session.setAttribute("sum", sum);
			request.getRequestDispatcher("checkout/list.jsp").forward(request, response);
		}else if("vip".equals(action)){
			String vid=request.getParameter("vid");
			if(DaoFactory.getVipDao().queryObject(vid).getVid()!=null){
				session.setAttribute("vip", DaoFactory.getVipDao().queryObject(vid));
				for (Product product : list) {
					sum.put(product.getPid(), Float.parseFloat(new DecimalFormat("0.00").format(product.getVipPrice()*map.get(product.getPid()))));
				}
			}else{
				session.removeAttribute("vip");
				for (Product product : list) {
					sum.put(product.getPid(), Float.parseFloat(new DecimalFormat("0.00").format(product.getSalePrice()*map.get(product.getPid()))));
				}
			}
			session.setAttribute("sum", sum);
			request.getRequestDispatcher("checkout/list.jsp").forward(request, response);
		}else if("balance".equals(action)){
			if(session.getAttribute("vip")==null){
				request.setAttribute("error", "请先输入会员卡号！");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}else{
				if(list.size()>0){
					Sale sale=new Sale();
					String sid="s"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
					sale.setSid(sid);//
					Vip vip=(Vip) session.getAttribute("vip");
					sale.setVid(vip.getVid());//
					float summary=0.00f;
					Set<String> set=sum.keySet();
					for(String s:set){
						summary+=sum.get(s);
					}
					sale.setStotal(summary);//
					sale.setScount(list.size());//
					User user=(User) session.getAttribute("user");
					sale.setUid(user.getUid());//
					DaoFactory.getSaleDao().add(sale);         //1
					
					vip.setVsum(vip.getVsum()+summary);//
					vip.setVscore(vip.getVscore()+(int)(summary*vip.getVrate()));//
					vip.setVcount(vip.getVcount()+1);//
					vip.setVbalance(vip.getVbalance()-summary);//
					DaoFactory.getVipDao().updateNumber(vip);        //2
					
					for(Product product:list){
						SaleItem saleItem=new SaleItem();
						saleItem.setSaleId(sid);//
						saleItem.setPid(product.getPid());//
						saleItem.setScount(map.get(product.getPid()));//
						saleItem.setSprice(product.getVipPrice());//
						DaoFactory.getSaleItemDao().add(saleItem);         //3
						
						DaoFactory.getProductDao().updatePamount(product.getPid(), product.getPamount()-map.get(product.getPid()));         //4
					}
					session.removeAttribute("list");
					session.removeAttribute("map");
					session.removeAttribute("sum");
					session.removeAttribute("vip");
				}
				request.getRequestDispatcher("checkout/list.jsp").forward(request, response);
			}
		}else if("cash".equals(action)){
			Vip vip=new Vip();
			if(session.getAttribute("vip")!=null){
				vip=(Vip) session.getAttribute("vip");
			}
			Float cash=0f;
			if(request.getParameter("cash")!=null&&request.getParameter("cash")!=""){
				cash=Float.parseFloat(request.getParameter("cash"));
				if(list.size()>0){
					Sale sale=new Sale();
					String sid="s"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
					sale.setSid(sid);//
					if(vip.getVid()!=null){
						sale.setVid(vip.getVid());//
					}
					float summary=0.00f;
					Set<String> set=sum.keySet();
					for(String s:set){
						summary+=sum.get(s);
					}
					sale.setStotal(summary);//
					sale.setScount(list.size());//
					User user=(User) session.getAttribute("user");
					sale.setUid(user.getUid());//
					DaoFactory.getSaleDao().add(sale);         //1
					
					for(Product product:list){
						SaleItem saleItem=new SaleItem();
						saleItem.setSaleId(sid);//
						saleItem.setPid(product.getPid());//
						saleItem.setScount(map.get(product.getPid()));//
						if(vip.getVid()!=null){
							saleItem.setSprice(product.getVipPrice());
						}else{
							saleItem.setSprice(product.getSalePrice());//
						}
						DaoFactory.getSaleItemDao().add(saleItem);         //2
						
						DaoFactory.getProductDao().updatePamount(product.getPid(), product.getPamount()-map.get(product.getPid()));         //3
					}
					request.setAttribute("cash", cash);
					request.setAttribute("change", new DecimalFormat("0.00").format(cash-summary));
					session.removeAttribute("list");
					session.removeAttribute("map");
					session.removeAttribute("sum");
					session.removeAttribute("vip");
				}
				request.getRequestDispatcher("checkout/list.jsp").forward(request, response);
			}else{
				request.setAttribute("error", "请输入收款金额！");
				request.getRequestDispatcher("error.jsp").forward(request,response);
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
