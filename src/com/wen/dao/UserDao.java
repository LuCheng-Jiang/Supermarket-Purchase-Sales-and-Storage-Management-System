package com.wen.dao;

import com.wen.dao.pojo.User;

public interface UserDao extends BaseDao<User> {
	User login(String uid,String upassword);
	int countById(String uid);
}
