package com.wen.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wen.dao.ProviderDao;
import com.wen.dao.db.DBUtil;
import com.wen.dao.pojo.Provider;

public class ProviderDaoImpl implements ProviderDao {

	public int countByKey(String key) {
		// TODO Auto-generated method stub
		String sql="select count(*) as count from provider where pname=? or pphone=?";
		int i=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setString(2, key);
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

	public List<Provider> queryByName(String pname) {
		// TODO Auto-generated method stub
		String sql="select * from provider where pname=?";
		List<Provider> list=new ArrayList<Provider>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, pname);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Provider provider=new Provider();
				provider.setPid(rs.getInt("pid"));
				provider.setPname(rs.getString("pname"));
				provider.setPphone(rs.getString("pphone"));
				provider.setPaddress(rs.getString("paddress"));
				provider.setPlinkman(rs.getString("plinkman"));
				list.add(provider);
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

	public List<Provider> queryByKey(String key) {
		// TODO Auto-generated method stub
		String sql="select * from provider where pname=? or pphone=?";
		List<Provider> list=new ArrayList<Provider>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setString(2, key);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Provider provider=new Provider();
				provider.setPid(rs.getInt("pid"));
				provider.setPname(rs.getString("pname"));
				provider.setPphone(rs.getString("pphone"));
				provider.setPaddress(rs.getString("paddress"));
				provider.setPlinkman(rs.getString("plinkman"));
				list.add(provider);
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

	public int add(Provider obj) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="insert into provider(pname,pphone,paddress,plinkman)values(?,?,?,?)";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, obj.getPname());
			pstmt.setString(2, obj.getPphone());
			pstmt.setString(3, obj.getPaddress());
			pstmt.setString(4, obj.getPlinkman());
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
		String sql="delete from provider where pid=?";
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

	public List<Provider> queryAll() {
		// TODO Auto-generated method stub
		String sql="select * from provider";
		List<Provider> list=new ArrayList<Provider>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Provider provider=new Provider();
				provider.setPid(rs.getInt("pid"));
				provider.setPname(rs.getString("pname"));
				provider.setPphone(rs.getString("pphone"));
				provider.setPaddress(rs.getString("paddress"));
				provider.setPlinkman(rs.getString("plinkman"));
				list.add(provider);
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
		String sql="select count(*) as count from provider";
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

	public Provider queryObject(String id) {
		// TODO Auto-generated method stub
		String sql="select * from provider where pid=?";
		Provider provider=new Provider(); 
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(id));
			rs=pstmt.executeQuery();
			while(rs.next()){
				provider.setPid(rs.getInt("pid"));
				provider.setPname(rs.getString("pname"));
				provider.setPphone(rs.getString("pphone"));
				provider.setPaddress(rs.getString("paddress"));
				provider.setPlinkman(rs.getString("plinkman"));
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
		return provider;
	}

	public List<Provider> queryPage(int offset, int size) {
		// TODO Auto-generated method stub
		String sql="select * from provider limit ?,?";
		List<Provider> list=new ArrayList<Provider>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, size);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Provider provider=new Provider();
				provider.setPid(rs.getInt("pid"));
				provider.setPname(rs.getString("pname"));
				provider.setPphone(rs.getString("pphone"));
				provider.setPaddress(rs.getString("paddress"));
				provider.setPlinkman(rs.getString("plinkman"));
				list.add(provider);
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

	public int update(Provider obj) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="update provider set pname=?,pphone=?,paddress=?,plinkman=? where pid=? ";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,obj.getPname());
			pstmt.setString(2, obj.getPphone());
			pstmt.setString(3, obj.getPaddress());
			pstmt.setString(4, obj.getPlinkman());
			pstmt.setInt(5, obj.getPid());
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

	public int countByName(String pname) {
		// TODO Auto-generated method stub
		String sql="select count(*) as count from provider where pname=?";
		int i=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, pname);
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
