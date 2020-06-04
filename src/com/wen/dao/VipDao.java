package com.wen.dao;

import java.util.List;

import com.wen.dao.pojo.Vip;

public interface VipDao extends BaseDao<Vip> {
	int countByKey(String key);
	List<Vip> queryByKey(String key);
	int updateVscore(String vid,int score);
	int updateVbalance(String vid,float money);
	int updateNumber(Vip vip);
}
