package com.wen.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wen.dao.StockDao;
import com.wen.dao.db.DBUtil;
import com.wen.dao.pojo.Product;
import com.wen.dao.pojo.Provider;
import com.wen.dao.pojo.Stock;

public class StockDaoImpl implements StockDao {

	public int deleteToProduct(String product) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="delete from stock where productId=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, product);
			i=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	public int deleteToProvider(int provider) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="delete from stock where providerId=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, provider);
			i=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	public List<Stock> queryFromTo(String start, String end) {
		// TODO Auto-generated method stub
		List<Stock> list=new ArrayList<Stock>();
		String sql="select s.*,pt.pname,pr.pname from stock s join product pt on s.productId=pt.pid " +
				"join provider pr on s.providerId=pr.pid " +
				"where s.stime> ? and s.stime< ?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, start);
			pstmt.setString(2, end);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Stock stock=new Stock();
				stock.setSid(rs.getString("s.sid"));
				stock.setStime(rs.getString("s.stime"));
				stock.setSamount(rs.getInt("s.samount"));
				stock.setSprice(rs.getFloat("s.sprice"));
				Product product=new Product();
				product.setPid(rs.getString("s.productId"));
				product.setPname(rs.getString("pt.pname"));
				stock.setProduct(product);
				Provider provider=new Provider();
				provider.setPid(rs.getInt("s.providerId"));
				provider.setPname(rs.getString("pr.pname"));
				stock.setProvider(provider);
				list.add(stock);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public int add(Stock obj) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="insert into stock(sid,productId,stime,samount,sprice,providerId)values(?,?,?,?,?,?)";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, obj.getSid());
			pstmt.setString(2, obj.getProductId());
			pstmt.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pstmt.setInt(4, obj.getSamount());
			pstmt.setFloat(5, obj.getSprice());
			pstmt.setInt(6, obj.getProviderId());
			i=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Stock> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int queryCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Stock queryObject(String id) {
		// TODO Auto-generated method stub
		Stock stock=new Stock();
		String sql="select s.*,pr.pname,pt.pname from stock s join provider pr on s.providerId=pr.pid " +
		"join product pt on s.productId=pt.pid " +
		"where sid=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				stock.setSid(rs.getString("s.sid"));
				stock.setStime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getDate("s.stime")));
				stock.setSamount(rs.getInt("s.samount"));
				stock.setSprice(rs.getFloat("s.sprice"));
				Product product=new Product();
				product.setPid(rs.getString("s.productId"));
				product.setPname(rs.getString("pt.pname"));
				stock.setProduct(product);
				Provider provider=new Provider();
				provider.setPid(rs.getInt("s.providerId"));
				provider.setPname(rs.getString("pr.pname"));
				stock.setProvider(provider);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stock;
	}

	public List<Stock> queryPage(int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public int update(Stock obj) {
		// TODO Auto-generated method stub
		return 0;
	}

}
