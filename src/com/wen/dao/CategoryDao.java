package com.wen.dao;

import java.util.List;

import com.wen.dao.pojo.Category;

public interface CategoryDao extends BaseDao<Category> {
	int countByName(String cname);
	List<Category> queryByName(String cname);
}
