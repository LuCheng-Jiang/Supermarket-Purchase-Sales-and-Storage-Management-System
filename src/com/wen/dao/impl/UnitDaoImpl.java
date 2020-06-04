package com.wen.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wen.dao.UnitDao;
import com.wen.dao.db.DBUtil;
import com.wen.dao.pojo.Unit;

public class UnitDaoImpl implements UnitDao {

	public int countByName(String uname) {
		// TODO Auto-generated method stub
		String sql="select count(*) as count from unit where uname=?";
		int i=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,uname);
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

	public int add(Unit obj) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="insert into unit(uname)values(?)";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, obj.getUname());
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
		String sql="delete from unit where uid=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(id));
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

	public List<Unit> queryAll() {
		// TODO Auto-generated method stub
		String sql="select * from unit";
		List<Unit> list=new ArrayList<Unit>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Unit Unit=new Unit();
				Unit.setUid(rs.getInt("uid"));
				Unit.setUname(rs.getString("uname"));
				list.add(Unit);
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
		String sql="select count(*) as count from unit";
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

	public Unit queryObject(String id) {
		// TODO Auto-generated method stub
		String sql="select * from unit where uid=?";
		Unit Unit=new Unit();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(id));
			rs=pstmt.executeQuery();
			while(rs.next()){
				Unit.setUid(rs.getInt("uid"));
				Unit.setUname(rs.getString("uname"));

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
		return Unit;
	}

	public List<Unit> queryPage(int offset, int size) {
		// TODO Auto-generated method stub
		String sql="select * from unit limit ?,?";
		List<Unit> list=new ArrayList<Unit>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, size);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Unit Unit=new Unit();
				Unit.setUid(rs.getInt("uid"));
				Unit.setUname(rs.getString("uname"));
				list.add(Unit);
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

	public int update(Unit obj) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="update unit set uname=? where uid=? ";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,obj.getUname());
			pstmt.setInt(2, obj.getUid());
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

	public List<Unit> queryByName(String uname) {
		// TODO Auto-generated method stub
		String sql="select * from unit where uname=?";
		List<Unit> list=new ArrayList<Unit>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, uname);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Unit unit=new Unit();
				unit.setUid(rs.getInt("uid"));
				unit.setUname(rs.getString("uname"));
				list.add(unit);

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

}
