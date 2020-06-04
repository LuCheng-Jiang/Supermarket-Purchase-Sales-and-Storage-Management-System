package com.wen.dao;

import java.util.List;

import com.wen.dao.pojo.Product;

public interface ProductDao extends BaseDao<Product> {
	int countByKey(String key);
	List<Product> queryByKey(String key);
	int deleteToUnit(int unit);
	int deleteToCategory(int category);
	int updatePamount(String pid,int amount);
}
