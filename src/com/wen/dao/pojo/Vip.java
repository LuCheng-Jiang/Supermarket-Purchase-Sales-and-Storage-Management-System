package com.wen.dao.pojo;

import java.util.Date;

public class Vip {
	private String vid;
	private String vname;
	private String vphone;
	private float vsum;
	private int vscore;
	private int vcount;
	private float vrate;
	private Date vbirthday;
	private float vbalance;
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public String getVphone() {
		return vphone;
	}
	public void setVphone(String vphone) {
		this.vphone = vphone;
	}
	public float getVsum() {
		return vsum;
	}
	public void setVsum(float vsum) {
		this.vsum = vsum;
	}
	public int getVscore() {
		return vscore;
	}
	public void setVscore(int vscore) {
		this.vscore = vscore;
	}
	public int getVcount() {
		return vcount;
	}
	public void setVcount(int vcount) {
		this.vcount = vcount;
	}
	public float getVrate() {
		return vrate;
	}
	public void setVrate(float vrate) {
		this.vrate = vrate;
	}
	public Date getVbirthday() {
		return vbirthday;
	}
	public void setVbirthday(Date vbirthday) {
		this.vbirthday = vbirthday;
	}
	public float getVbalance() {
		return vbalance;
	}
	public void setVbalance(float vbalance) {
		this.vbalance = vbalance;
	}
	
}
