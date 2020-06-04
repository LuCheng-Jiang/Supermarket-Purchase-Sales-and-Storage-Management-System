package com.wen.dao;

import java.util.List;

import com.wen.dao.pojo.SaveRecord;

public interface SaveRecordDao extends BaseDao<SaveRecord> {
	List<SaveRecord> queryFromTo(String start,String end);
	int deleteToVip(String vip);
	int deleteToUser(String user);
}
