package cn.itcast.jk.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Description: MessageService接口
 * @Author: rent
 * @Company: http://java.itcast.cn
 * @CreateDate: 2017-6-10 16:51:28
 */

public class Message extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id; // 留言id
	private String fromusername; // 留言用户name
	private String fromuserid; // 留言用户id
	private String touserid; // 留言给谁
	private String tousername; // 留言给用户姓名
	private String content; // 内容
	private Date messagetime; // 留言时间

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(String fromusername, String fromuserid, String touserid, String tousername, String content,
			Date messagetime) {
		super();
		this.fromusername = fromusername;
		this.fromuserid = fromuserid;
		this.touserid = touserid;
		this.tousername = tousername;
		this.content = content;
		this.messagetime = messagetime;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFromusername() {
		return fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	public String getFromuserid() {
		return fromuserid;
	}

	public void setFromuserid(String fromuserid) {
		this.fromuserid = fromuserid;
	}

	public String getTouserid() {
		return this.touserid;
	}

	public void setTouserid(String touserid) {
		this.touserid = touserid;
	}

	public String getTousername() {
		return this.tousername;
	}

	public void setTousername(String tousername) {
		this.tousername = tousername;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getMessagetime() {
		return this.messagetime;
	}

	public void setMessagetime(Date messagetime) {
		this.messagetime = messagetime;
	}
}
