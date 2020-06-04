package com.wen.dao;

import java.util.List;

public interface BaseDao<T> {
	int add(T obj);
	
	int delete(String id);
	
	int update(T obj);
	
	List<T> queryAll();
		
	List<T> queryPage(int offset,int size);
	
	T queryObject(String id);
	
	int queryCount();
}
