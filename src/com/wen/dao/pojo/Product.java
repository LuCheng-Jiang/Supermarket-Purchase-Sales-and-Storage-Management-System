package com.wen.dao.pojo;


public class Product {
	private String pid;
	private int cid;
	private String pname;
	private String pspec;
	private String pic;      //二维码
	private int uid;
	private int pminNumber;
	private float salePrice;
	private float vipPrice;
	private int pamount;
	private Category category=new Category();
	private Unit unit=new Unit();
	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * @return the cid
	 */
	public int getCid() {
		return cid;
	}
	/**
	 * @param cid the cid to set
	 */
	public void setCid(int cid) {
		this.cid = cid;
	}
	/**
	 * @return the pname
	 */
	public String getPname() {
		return pname;
	}
	
	/**
	 * @param pname the pname to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}
	/**
	 * @return the pspec
	 */
	public String getPspec() {
		return pspec;
	}
	/**
	 * @param pspec the pspec to set
	 */
	public void setPspec(String pspec) {
		this.pspec = pspec;
	}
	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}
	/**
	 * @return the pminNumber
	 */
	public int getPminNumber() {
		return pminNumber;
	}
	/**
	 * @param pminNumber the pminNumber to set
	 */
	public void setPminNumber(int pminNumber) {
		this.pminNumber = pminNumber;
	}
	/**
	 * @return the salePrice
	 */
	public float getSalePrice() {
		return salePrice;
	}
	/**
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}
	/**
	 * @return the vipPrice
	 */
	public float getVipPrice() {
		return vipPrice;
	}
	/**
	 * @param vipPrice the vipPrice to set
	 */
	public void setVipPrice(float vipPrice) {
		this.vipPrice = vipPrice;
	}
	/**
	 * @return the pamount
	 */
	public int getPamount() {
		return pamount;
	}
	/**
	 * @param pamount the pamount to set
	 */
	public void setPamount(int pamount) {
		this.pamount = pamount;
	}
	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	/**
	 * @return the unit
	 */
	public Unit getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		return true;
	}
	
}
