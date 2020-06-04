package com.wen.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wen.dao.SaleItemDao;
import com.wen.dao.db.DBUtil;
import com.wen.dao.pojo.Product;
import com.wen.dao.pojo.Sale;
import com.wen.dao.pojo.SaleItem;

public class SaleItemDaoImpl implements SaleItemDao {

	public int deleteToProduct(String product) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="delete from saleitem where pid=?";
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

	public int deleteToSale(String sale) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="delete from saleitem where saleId=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, sale);
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

	public List<SaleItem> queryFromTo(String start, String end) {
		// TODO Auto-generated method stub
		List<SaleItem> list=new ArrayList<SaleItem>();
		String sql="select s.*,p.pid,p.pname,p.pic,sale.stime from saleitem s join sale on s.saleId=sale.sid " +
				"join product p on s.pid=p.pid " +
				"where sale.stime> ? and sale.stime <? " +
				"order by s.sid";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, start);
			pstmt.setString(2, end);
			rs=pstmt.executeQuery();
			while(rs.next()){
				SaleItem saleItem=new SaleItem();
				saleItem.setSid(rs.getInt("s.sid"));
				saleItem.setSaleId(rs.getString("s.saleId"));
				saleItem.setScount(rs.getInt("s.scount"));
				saleItem.setSprice(rs.getFloat("s.sprice"));
				Product product=new Product();
				product.setPid(rs.getString("p.pid"));
				product.setPname(rs.getString("p.pname"));
				product.setPic(rs.getString("p.pic"));
				saleItem.setProduct(product);
				Sale sale=new Sale();
				sale.setStime(rs.getString("sale.stime"));
				saleItem.setSale(sale);
				list.add(saleItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return list;
	}

	public int add(SaleItem obj) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="insert into saleitem(saleId,pid,scount,sprice)values(?,?,?,?)";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, obj.getSaleId());
			pstmt.setString(2, obj.getPid());
			pstmt.setInt(3, obj.getScount());
			pstmt.setFloat(4, obj.getSprice());
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

	public List<SaleItem> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int queryCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public SaleItem queryObject(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SaleItem> queryPage(int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public int update(SaleItem obj) {
		// TODO Auto-generated method stub
		return 0;
	}

}
