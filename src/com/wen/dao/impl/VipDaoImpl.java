package com.wen.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wen.dao.VipDao;
import com.wen.dao.db.DBUtil;
import com.wen.dao.pojo.Vip;

public class VipDaoImpl implements VipDao {

	public int countByKey(String key) {
		// TODO Auto-generated method stub
		String sql="select count(*) as count from vip where vid=? or vname=? or vphone=?";
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

	public List<Vip> queryByKey(String key) {
		// TODO Auto-generated method stub
		String sql="select * from vip where vid=? or vname=? or vphone=?";
		List<Vip> list=new ArrayList<Vip>();
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
				Vip vip=new Vip();
				vip.setVid(rs.getString("vid"));
				vip.setVname(rs.getString("vname"));
				vip.setVphone(rs.getString("vphone"));
				vip.setVsum(rs.getFloat("vsum"));
				vip.setVscore(rs.getInt("vscore"));
				vip.setVcount(rs.getInt("vcount"));
				vip.setVrate(rs.getFloat("vrate"));
				vip.setVbirthday(rs.getDate("vbirthday"));
				vip.setVbalance(rs.getFloat("vbalance"));
				list.add(vip);
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

	public int add(Vip obj) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="insert into vip(vid,vname,vphone,vrate,vbirthday)values(?,?,?,?,?)";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, obj.getVid());
			pstmt.setString(2, obj.getVname());
			pstmt.setString(3, obj.getVphone());
			pstmt.setFloat(4, obj.getVrate());
			pstmt.setDate(5, new java.sql.Date(obj.getVbirthday().getTime()));
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
		String sql="delete from vip where vid=?";
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

	public List<Vip> queryAll() {
		// TODO Auto-generated method stub
		String sql="select * from vip";
		List<Vip> list=new ArrayList<Vip>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Vip vip=new Vip();
				vip.setVid(rs.getString("vid"));
				vip.setVname(rs.getString("vname"));
				vip.setVphone(rs.getString("vphone"));
				vip.setVsum(rs.getFloat("vsum"));
				vip.setVscore(rs.getInt("vscore"));
				vip.setVcount(rs.getInt("vcount"));
				vip.setVrate(rs.getFloat("vrate"));
				vip.setVbirthday(rs.getDate("vbirthday"));
				vip.setVbalance(rs.getFloat("vbalance"));
				list.add(vip);
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
		String sql="select count(*) as count from vip";
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

	public Vip queryObject(String id) {
		// TODO Auto-generated method stub
		String sql="select * from vip where vid=?";
		Vip vip=new Vip();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				vip.setVid(rs.getString("vid"));
				vip.setVname(rs.getString("vname"));
				vip.setVphone(rs.getString("vphone"));
				vip.setVsum(rs.getFloat("vsum"));
				vip.setVscore(rs.getInt("vscore"));
				vip.setVcount(rs.getInt("vcount"));
				vip.setVrate(rs.getFloat("vrate"));
				vip.setVbirthday(rs.getDate("vbirthday"));
				vip.setVbalance(rs.getFloat("vbalance"));
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
		return vip;
	}

	public List<Vip> queryPage(int offset, int size) {
		// TODO Auto-generated method stub
		String sql="select * from vip limit ?,?";
		List<Vip> list=new ArrayList<Vip>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, size);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Vip vip=new Vip();
				vip.setVid(rs.getString("vid"));
				vip.setVname(rs.getString("vname"));
				vip.setVphone(rs.getString("vphone"));
				vip.setVsum(rs.getFloat("vsum"));
				vip.setVscore(rs.getInt("vscore"));
				vip.setVcount(rs.getInt("vcount"));
				vip.setVrate(rs.getFloat("vrate"));
				vip.setVbirthday(rs.getDate("vbirthday"));
				vip.setVbalance(rs.getFloat("vbalance"));
				list.add(vip);
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

	public int update(Vip obj) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="update vip set vname=?,vphone=?,vrate=?,vbirthday=? where vid=? ";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, obj.getVname());
			pstmt.setString(2, obj.getVphone());
			pstmt.setFloat(3, obj.getVrate());
			pstmt.setDate(4, new java.sql.Date(obj.getVbirthday().getTime()));
			pstmt.setString(5, obj.getVid());
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

	public int updateVbalance(String vid, float money) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="update vip set vbalance=? where vid=? ";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setFloat(1, money);
			pstmt.setString(2, vid);
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

	public int updateVscore(String vid, int score) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="update vip set vscore=? where vid=? ";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, score);
			pstmt.setString(2, vid);
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

	public int updateNumber(Vip vip) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="update vip set vsum=?,vscore=?,vcount=?,vbalance=? where vid=? ";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setFloat(1, vip.getVsum());
			pstmt.setInt(2, vip.getVscore());
			pstmt.setInt(3, vip.getVcount());
			pstmt.setFloat(4, vip.getVbalance());
			pstmt.setString(5, vip.getVid());
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
