package cn.itcast.jk.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Description:	MymenuService接口
 * @Author:			rent
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2017-6-12 15:09:34
 */

public class Mymenu extends BaseEntity {
	private static final long serialVersionUID = 1L;

			private String id;			
			private String userid;			
			private String menuname;			
			private String murl;			
			private Double sort;			

	
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	
	public String getUserid() {
		return this.userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}	
	
	public String getMenuname() {
		return this.menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}	
	
	public String getMurl() {
		return this.murl;
	}
	public void setMurl(String murl) {
		this.murl = murl;
	}	
	
	public Double getSort() {
		return this.sort;
	}
	public void setSort(Double sort) {
		this.sort = sort;
	}	
}
