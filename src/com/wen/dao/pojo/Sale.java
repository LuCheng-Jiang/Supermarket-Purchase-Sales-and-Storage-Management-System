package com.wen.dao.pojo;

public class Sale {
	private String sid;
	private String vid;
	private String stime;
	private float stotal;
	private int scount;
	private String uid;
	private Vip vip=new Vip();
	private User user=new User();
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
	 * @return the vid
	 */
	public String getVid() {
		return vid;
	}
	/**
	 * @param vid the vid to set
	 */
	public void setVid(String vid) {
		this.vid = vid;
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
	 * @return the stotal
	 */
	public float getStotal() {
		return stotal;
	}
	/**
	 * @param stotal the stotal to set
	 */
	public void setStotal(float stotal) {
		this.stotal = stotal;
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
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the vip
	 */
	public Vip getVip() {
		return vip;
	}
	/**
	 * @param vip the vip to set
	 */
	public void setVip(Vip vip) {
		this.vip = vip;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
}

