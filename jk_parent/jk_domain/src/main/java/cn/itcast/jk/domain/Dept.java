package cn.itcast.jk.domain;

import java.io.Serializable;

/**
 * 部门
 * @author Administrator
 */
public class Dept implements Serializable {
	
	private static final long serialVersionUID = 3770865855127377531L;
	
	// 主键
	private String id;
	// 部门的名称
	private String deptName;
	//父部门   自关联   子部门与父部门   多对一关系
	private Dept parent;
	//状态  1启用0停用 默认为1
	private Integer state;  
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Dept getParent() {
		return parent;
	}
	public void setParent(Dept parent) {
		this.parent = parent;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	 
}
