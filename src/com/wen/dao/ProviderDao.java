package com.wen.dao;

import java.util.List;

import com.wen.dao.pojo.Provider;

public interface ProviderDao extends BaseDao<Provider> {
	int countByKey(String key);
	List<Provider> queryByKey(String key);
	List<Provider> queryByName(String pname);
	int countByName(String pname);
}
