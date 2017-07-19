package model;

import java.sql.Timestamp;

public class Order {
	private int orderid;
	private Timestamp subtime;
	private float totalmoney;
	private String telphone;
	private String address;
	private int userid;
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	
	public Timestamp getSubtime() {
		return subtime;
	}
	public void setSubtime(Timestamp subtime) {
		this.subtime = subtime;
	}
	public float getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(float totalmoney) {
		this.totalmoney = totalmoney;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
}
