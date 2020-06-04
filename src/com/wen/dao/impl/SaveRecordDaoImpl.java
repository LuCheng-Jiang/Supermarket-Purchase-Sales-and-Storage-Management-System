package com.wen.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wen.dao.SaveRecordDao;
import com.wen.dao.db.DBUtil;
import com.wen.dao.pojo.SaveRecord;

public class SaveRecordDaoImpl implements SaveRecordDao {

	public List<SaveRecord> queryFromTo(String start, String end) {
		// TODO Auto-generated method stub
		List<SaveRecord> list=new ArrayList<SaveRecord>();
		String sql="select * from save where stime> ? and stime< ?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, start);
			pstmt.setString(2, end);
			rs=pstmt.executeQuery();
			while(rs.next()){
				SaveRecord saveRecord=new SaveRecord();
				saveRecord.setSid(rs.getInt("sid"));
				saveRecord.setVid(rs.getString("vid"));
				saveRecord.setStime(rs.getString("stime"));
				saveRecord.setSmoney(rs.getFloat("smoney"));
				saveRecord.setUid(rs.getString("uid"));
				list.add(saveRecord);
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

	public int add(SaveRecord obj) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="insert into save(vid,stime,smoney,uid)values(?,?,?,?)";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, obj.getVid());
			pstmt.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pstmt.setFloat(3, obj.getSmoney());
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

	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<SaveRecord> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int queryCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public SaveRecord queryObject(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SaveRecord> queryPage(int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public int update(SaveRecord obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteToUser(String user) {
		// TODO Auto-generated method stub
		int i=0;
		String sql="delete from save where uid=?";
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
		String sql="delete from save where vid=?";
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

}
