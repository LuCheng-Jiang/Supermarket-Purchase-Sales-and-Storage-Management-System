package com.wen.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wen.dao.SaleDao;
import com.wen.dao.db.DBUtil;
import com.wen.dao.pojo.Sale;

public class SaleDaoImpl implements SaleDao {

	public int add(Sale obj) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="insert into sale(sid,vid,stime,stotal,scount,uid)values(?,?,?,?,?,?)";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, obj.getSid());
			pstmt.setString(2, obj.getVid());
			pstmt.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pstmt.setFloat(4, obj.getStotal());
			pstmt.setInt(5, obj.getScount());
			pstmt.setString(6, obj.getUid());
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

	public List<Sale> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int queryCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Sale queryObject(String id) {
		// TODO Auto-generated method stub
		Sale sale=new Sale();
		String sql="select * from sale where sid=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				sale.setSid(rs.getString("sid"));
				sale.setVid(rs.getString("vid"));
				sale.setStime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getDate("stime")));
				sale.setStotal(rs.getFloat("stotal"));
				sale.setScount(rs.getInt("scount"));
				sale.setUid(rs.getString("uid"));
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
		return sale;
	}

	public List<Sale> queryPage(int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public int update(Sale obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteToUser(String user) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="delete from sale where uid=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user);
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

	public int deleteToVip(String vip) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="delete from sale where vid=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vip);
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

	public List<Sale> queryFromTo(String start, String end) {
		// TODO Auto-generated method stub
		List<Sale> list=new ArrayList<Sale>();
		String sql="select * from sale where stime> ? and stime< ?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, start);
			pstmt.setString(2, end);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Sale sale=new Sale();
				sale.setSid(rs.getString("sid"));
				sale.setVid(rs.getString("vid"));
				sale.setStime(rs.getString("stime"));
				sale.setStotal(rs.getFloat("stotal"));
				sale.setScount(rs.getInt("scount"));
				sale.setUid(rs.getString("uid"));
				list.add(sale);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
