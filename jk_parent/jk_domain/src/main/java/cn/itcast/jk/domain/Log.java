package cn.itcast.jk.domain;

import java.util.Date;

/**
 * 登录信息
 * 
 * @author Administrator
 */
public class Log extends BaseEntity {

	// 主键
	private String id;
	private String name;// 
	private String address;// 
	private Date time;// 
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
	

	
}
