package cn.itcast.jk.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Description: FastmenuService接口
 * @Author: rent
 * @Company: http://java.itcast.cn
 * @CreateDate: 2017-6-11 20:53:17
 */

public class Fastmenu extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;
	private String mname;
	private String murl;
	private Double count;
	private String userid;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMname() {
		return this.mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMurl() {
		return this.murl;
	}

	public void setMurl(String murl) {
		this.murl = murl;
	}

	public Double getCount() {
		return this.count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}
