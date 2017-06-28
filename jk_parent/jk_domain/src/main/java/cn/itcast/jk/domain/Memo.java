package cn.itcast.jk.domain;

import java.util.Date;

/**
 * 备忘录
 * @author DT
 *
 */
public class Memo {

	private String id;
	private String remark;
	private int state;
	private Date updatetime;
	
	private User user;
	private MemoBg memoBg;
	private MemoIcon memoIcon;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public MemoBg getMemoBg() {
		return memoBg;
	}
	public void setMemoBg(MemoBg memoBg) {
		this.memoBg = memoBg;
	}
	public MemoIcon getMemoIcon() {
		return memoIcon;
	}
	public void setMemoIcon(MemoIcon memoIcon) {
		this.memoIcon = memoIcon;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
}
