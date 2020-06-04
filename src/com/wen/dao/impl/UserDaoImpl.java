package com.wen.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wen.dao.UserDao;
import com.wen.dao.db.DBUtil;
import com.wen.dao.pojo.User;

public class UserDaoImpl implements UserDao {

	public User login(String uid, String upassword) {
		// TODO Auto-generated method stub
		String sql="select * from user where uid=? and upassword=?";
		User user=new User();//=null报错
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			pstmt.setString(2, upassword);
			rs=pstmt.executeQuery();
			while(rs.next()){
				user.setUid(rs.getString("uid"));
				user.setUpassword(rs.getString("upassword"));
				user.setUname(rs.getString("uname"));
				user.setUrole(rs.getInt("urole"));
				
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
		return user;
	}

	public int add(User obj) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="insert into user(uid,upassword,uname,urole)values(?,?,?,?)";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, obj.getUid());
			pstmt.setString(2, obj.getUpassword());
			pstmt.setString(3, obj.getUname());
			pstmt.setInt(4, obj.getUrole());
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
		String sql="delete from user where uid=?";
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

	public List<User> queryAll() {
		// TODO Auto-generated method stub
		String sql="select * from user";
		List<User> list=new ArrayList<User>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				User user=new User();
				user.setUid(rs.getString("uid"));
				user.setUpassword(rs.getString("upassword"));
				user.setUname(rs.getString("uname"));
				user.setUrole(rs.getInt("urole"));
				list.add(user);
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
		String sql="select count(*) as count from user";
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

	public User queryObject(String id) {
		// TODO Auto-generated method stub
		String sql="select * from user where uid=?";
		User user=new User();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				user.setUid(rs.getString("uid"));
				user.setUpassword(rs.getString("upassword"));
				user.setUname(rs.getString("uname"));
				user.setUrole(rs.getInt("urole"));
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
		return user;
	}

	public List<User> queryPage(int offset, int size) {
		// TODO Auto-generated method stub
		String sql="select * from user limit ?,?";
		List<User> list=new ArrayList<User>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, size);
			rs=pstmt.executeQuery();
			while(rs.next()){
				User user=new User();
				user.setUid(rs.getString("uid"));
				user.setUpassword(rs.getString("upassword"));
				user.setUname(rs.getString("uname"));
				user.setUrole(rs.getInt("urole"));
				list.add(user);
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

	public int update(User obj) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="update user set upassword=?,uname=?,urole=? where uid=? ";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,obj.getUpassword());
			pstmt.setString(2, obj.getUname());
			pstmt.setInt(3, obj.getUrole());
			pstmt.setString(4, obj.getUid());
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

	public int countById(String uid) {
		// TODO Auto-generated method stub
		String sql="select count(*) as count from user where uid=?";
		int i=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, uid);
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
}
