package com.wen.dao.pojo;

public class SaleItem {
	private int sid;
	private String saleId;
	private String pid;
	private int scount;
	private float sprice;
	private Sale sale=new Sale();
	private Product product=new Product();
	/**
	 * @return the sid
	 */
	public int getSid() {
		return sid;
	}
	/**
	 * @param sid the sid to set
	 */
	public void setSid(int sid) {
		this.sid = sid;
	}
	/**
	 * @return the saleId
	 */
	public String getSaleId() {
		return saleId;
	}
	/**
	 * @param saleId the saleId to set
	 */
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
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
	 * @return the scount
	 */
	public int getScount() {
		return scount;
	}
	/**
	 * @param scount the scount to set
	 */
	public void setScount(int scount) {
		this.scount = scount;
	}
	/**
	 * @return the sprice
	 */
	public float getSprice() {
		return sprice;
	}
	/**
	 * @param sprice the sprice to set
	 */
	public void setSprice(float sprice) {
		this.sprice = sprice;
	}
	/**
	 * @return the sale
	 */
	public Sale getSale() {
		return sale;
	}
	/**
	 * @param sale the sale to set
	 */
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
