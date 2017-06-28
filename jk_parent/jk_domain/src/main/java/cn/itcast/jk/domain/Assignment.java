package cn.itcast.jk.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Description: AssignmentService接口
 * @Author: rent
 * @Company: http://java.itcast.cn
 * @CreateDate: 2017-6-10 19:17:06
 */

public class Assignment extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;
	private String userid;
	private String content;
	private Date overtime;
	private Double state;
	private String senderids;
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getId() {
		return id;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getOvertime() {
		return this.overtime;
	}

	public void setOvertime(Date overtime) {
		this.overtime = overtime;
	}

	public Double getState() {
		return this.state;
	}

	public void setState(Double state) {
		this.state = state;
	}

	public String getSenderids() {
		return this.senderids;
	}

	public void setSenderids(String senderids) {
		this.senderids = senderids;
	}
}
