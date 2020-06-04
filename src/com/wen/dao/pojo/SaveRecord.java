package com.wen.dao.pojo;

public class SaveRecord {
	private int sid;
	private String vid;
	private String stime;
	private float smoney;
	private String uid;
	private Vip vip=new Vip();
	private User user=new User();
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
	 * @return the smoney
	 */
	public float getSmoney() {
		return smoney;
	}
	/**
	 * @param smoney the smoney to set
	 */
	public void setSmoney(float smoney) {
		this.smoney = smoney;
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
	public void setVip(Vip vip) {
		this.vip = vip;
	}
	public Vip getVip() {
		return vip;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	
}
