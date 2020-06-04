package com.wen.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wen.dao.ProductDao;
import com.wen.dao.db.DBUtil;
import com.wen.dao.pojo.Category;
import com.wen.dao.pojo.Product;
import com.wen.dao.pojo.Unit;

public class ProductDaoImpl implements ProductDao {

	public int countByKey(String key) {
		// TODO Auto-generated method stub
		String sql="select count(*) as count from product p join category c on p.cid=c.cid " +
				"join unit u on p.uid=u.uid " +
				"where p.pid=? or p.pname=? or c.cname=?";
		int i=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,key);
			pstmt.setString(2, key);
			pstmt.setString(3, key);
			rs=pstmt.executeQuery();
			while(rs.next()){
				i=rs.getInt("count");
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
		return i;
	}

	public List<Product> queryByKey(String key) {
		// TODO Auto-generated method stub
		String sql="select p.*,c.cname,u.uname from product p join category c on p.cid=c.cid " +
				"join unit u on p.uid=u.uid " +
				"where p.pid=? or p.pname=? or c.cname=?";
		List<Product> list=new ArrayList<Product>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setString(2, key);
			pstmt.setString(3, key);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Product product=new Product();
				product.setPid(rs.getString("p.pid"));
				product.setPname(rs.getString("p.pname"));
				product.setPspec(rs.getString("p.pspec"));
				product.setPminNumber(rs.getInt("p.pminNumber"));
				product.setSalePrice(rs.getFloat("p.salePrice"));
				product.setVipPrice(rs.getFloat("p.vipPrice"));
				product.setPamount(rs.getInt("p.pamount"));
				product.setPic(rs.getString("p.pic"));
				Category category=new Category();
				category.setCname(rs.getString("c.cname"));
				product.setCategory(category);
				Unit unit=new Unit();
				unit.setUname(rs.getString("u.uname"));
				product.setUnit(unit);
				list.add(product);
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
		return list;
	}

	public int add(Product obj) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="insert into product(pid,cid,pname,pspec,uid,pminNumber,salePrice,vipPrice,pic) values(?,?,?,?,?,?,?,?,?)";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, obj.getPid());
			pstmt.setInt(2, obj.getCid());
			pstmt.setString(3, obj.getPname());
			pstmt.setString(4, obj.getPspec());
			pstmt.setInt(5, obj.getUid());
			pstmt.setInt(6, obj.getPminNumber());
			pstmt.setFloat(7, obj.getSalePrice());
			pstmt.setFloat(8, obj.getVipPrice());
			pstmt.setString(9, obj.getPic());
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
		int i=0;
		String sql="delete from product where pid=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
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

	public List<Product> queryAll() {
		// TODO Auto-generated method stub
		String sql="select * from product";
		List<Product> list=new ArrayList<Product>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Product product=new Product();
				product.setPid(rs.getString("pid"));
				product.setPname(rs.getString("pname"));
				product.setPspec(rs.getString("pspec"));
				product.setPminNumber(rs.getInt("pminNumber"));
				product.setSalePrice(rs.getFloat("salePrice"));
				product.setVipPrice(rs.getFloat("vipPrice"));
				product.setPamount(rs.getInt("pamount"));
				product.setCid(rs.getInt("cid"));
				product.setUid(rs.getInt("uid"));
				list.add(product);
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
		return list;
	}

	public int queryCount() {
		// TODO Auto-generated method stub
		String sql="select count(*) as count from product";
		int i=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				i=rs.getInt("count");
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
		return i;
	}

	public Product queryObject(String id) {
		// TODO Auto-generated method stub
		String sql="select p.*,c.cname,u.uname from product p join category c on p.cid=c.cid " +
		"join unit u on p.uid=u.uid " +
		"where pid=?";
		Product product=new Product();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				product.setPid(rs.getString("p.pid"));
				product.setPname(rs.getString("p.pname"));
				product.setPspec(rs.getString("p.pspec"));
				product.setPminNumber(rs.getInt("p.pminNumber"));
				product.setSalePrice(rs.getFloat("p.salePrice"));
				product.setVipPrice(rs.getFloat("p.vipPrice"));
				product.setPamount(rs.getInt("p.pamount"));
				product.setPic(rs.getString("p.pic"));
				Category category=new Category();
				category.setCid(rs.getInt("p.cid"));
				category.setCname(rs.getString("c.cname"));
				product.setCategory(category);
				Unit unit=new Unit();
				unit.setUid(rs.getInt("p.uid"));
				unit.setUname(rs.getString("u.uname"));
				product.setUnit(unit);
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
		return product;
	}

	public List<Product> queryPage(int offset, int size) {
		// TODO Auto-generated method stub
		String sql="select p.*,c.cname,u.uname from product p join category c on p.cid=c.cid " +
				"join unit u on p.uid=u.uid " +
				"limit ?,?";
		List<Product> list=new ArrayList<Product>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, size);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Product product=new Product();
				product.setPid(rs.getString("p.pid"));
				product.setPname(rs.getString("p.pname"));
				product.setPspec(rs.getString("p.pspec"));
				product.setPminNumber(rs.getInt("p.pminNumber"));
				product.setSalePrice(rs.getFloat("p.salePrice"));
				product.setVipPrice(rs.getFloat("p.vipPrice"));
				product.setPamount(rs.getInt("p.pamount"));
				
				product.setPic(rs.getString("p.pic"));
				
				Category category=new Category();
				category.setCname(rs.getString("c.cname"));
				product.setCategory(category);
				Unit unit=new Unit();
				unit.setUname(rs.getString("u.uname"));
				product.setUnit(unit);
				list.add(product);
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
		return list;
	}

	public int update(Product obj) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="update product set cid=?,pname=?,pspec=?,uid=?,pminNumber=?,salePrice=?,vipPrice=?,pic=? where pid=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, obj.getCid());
			pstmt.setString(2, obj.getPname());
			pstmt.setString(3, obj.getPspec());
			pstmt.setInt(4, obj.getUid());
			pstmt.setInt(5, obj.getPminNumber());
			pstmt.setFloat(6, obj.getSalePrice());
			pstmt.setFloat(7, obj.getVipPrice());
			pstmt.setString(8, obj.getPic());
			pstmt.setString(9, obj.getPid());
			
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

	public int deleteToCategory(int category) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="delete from product where cid=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, category);
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

	public int deleteToUnit(int unit) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="delete from product where uid=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, unit);
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

	public int updatePamount(String pid, int amount) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="update product set pamount=? where pid=? ";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, amount);
			pstmt.setString(2, pid);
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

}
