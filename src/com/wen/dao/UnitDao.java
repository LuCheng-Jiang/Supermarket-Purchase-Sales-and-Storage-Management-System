package com.wen.dao;

import java.util.List;

import com.wen.dao.pojo.Unit;

public interface UnitDao extends BaseDao<Unit> {
	int countByName(String uname);
	List<Unit> queryByName(String uname);
}
