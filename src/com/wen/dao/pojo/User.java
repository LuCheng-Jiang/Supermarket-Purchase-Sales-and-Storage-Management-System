package com.wen.dao.pojo;

public class User {
	private String uid;
	private String upassword;
	private String uname;
	private int urole;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getUrole() {
		return urole;
	}
	public void setUrole(int urole) {
		this.urole = urole;
	}
}
