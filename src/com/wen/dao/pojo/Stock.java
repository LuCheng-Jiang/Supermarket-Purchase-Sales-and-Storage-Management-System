package com.wen.dao.pojo;

public class Stock {
	private String sid;
	private String productId;
	private String stime;
	private int samount;
	private float sprice;
	private int providerId;
	private Product product=new Product();
	private Provider provider=new Provider();
	/**
	 * @return the sid
	 */
	public String getSid() {
		return sid;
	}
	/**
	 * @param sid the sid to set
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}
	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * @return the providerId
	 */
	public int getProviderId() {
		return providerId;
	}
	/**
	 * @param providerId the providerId to set
	 */
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	/**
	 * @return the stime
	 */
	public String getStime() {
		return stime;
	}
	/**
	 * @param stime the stime to set
	 */
	public void setStime(String stime) {
		this.stime = stime;
	}
	/**
	 * @return the samount
	 */
	public int getSamount() {
		return samount;
	}
	/**
	 * @param samount the samount to set
	 */
	public void setSamount(int samount) {
		this.samount = samount;
	}
	/**
	 * @return the sPrice
	 */
	public float getSprice() {
		return sprice;
	}
	/**
	 * @param sPrice the sPrice to set
	 */
	public void setSprice(float sprice) {
		this.sprice = sprice;
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
	/**
	 * @return the provider
	 */
	public Provider getProvider() {
		return provider;
	}
	/**
	 * @param provider the provider to set
	 */
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stock [product=" + product + ", productId=" + productId
				+ ", provider=" + provider + ", providerId=" + providerId
				+ ", samount=" + samount + ", sid=" + sid + ", sprice="
				+ sprice + ", stime=" + stime + "]";
	}
	
}
