package com.wen.dao.factory;

import com.wen.dao.CategoryDao;
import com.wen.dao.ProductDao;
import com.wen.dao.ProviderDao;
import com.wen.dao.SaleDao;
import com.wen.dao.SaleItemDao;
import com.wen.dao.SaveRecordDao;
import com.wen.dao.StockDao;
import com.wen.dao.UnitDao;
import com.wen.dao.UserDao;
import com.wen.dao.VipDao;
import com.wen.dao.impl.CategoryDaoImpl;
import com.wen.dao.impl.ProductDaoImpl;
import com.wen.dao.impl.ProviderDaoImpl;
import com.wen.dao.impl.SaleDaoImpl;
import com.wen.dao.impl.SaleItemDaoImpl;
import com.wen.dao.impl.SaveRecordDaoImpl;
import com.wen.dao.impl.StockDaoImpl;
import com.wen.dao.impl.UnitDaoImpl;
import com.wen.dao.impl.UserDaoImpl;
import com.wen.dao.impl.VipDaoImpl;

public class DaoFactory {
	public static UserDao getUserDao(){
		return new UserDaoImpl();
	}
	public static CategoryDao getCategoryDao(){
		return new CategoryDaoImpl();
	}
	public static UnitDao getUnitDao(){
		return new UnitDaoImpl();
	}
	public static ProviderDao getProviderDao(){
		return new ProviderDaoImpl();
	}
	public static VipDao getVipDao(){
		return new VipDaoImpl();
	}
	public static ProductDao getProductDao(){
		return new ProductDaoImpl();
	}
	public static SaveRecordDao getSaveRecordDao(){
		return new SaveRecordDaoImpl();
	}
	public static StockDao getStockDao(){
		return new StockDaoImpl();
	}
	public static SaleDao getSaleDao(){
		return new SaleDaoImpl();
	}
	public static SaleItemDao getSaleItemDao(){
		return new SaleItemDaoImpl();
	}
}
