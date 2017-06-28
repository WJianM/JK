package cn.itcast.jk.action.sysadmin;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.jk.domain.Dept;
import cn.itcast.jk.service.DeptService;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;

public class DeptAction extends ActionSupport implements ModelDriven<Dept> {
	private Dept dept = new Dept();

	public Dept getModel() {
		return dept;
	}

	// 注入service
	private DeptService deptService;

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	// 定义page变量 因为分页传递属性用的是page.pageNum 表达式封装
	private Page<Dept> page = new Page<>();

	public Page<Dept> getPage() {
		return page;
	}

	public void setPage(Page<Dept> page) {
		this.page = page;
	}

	// 进行分页查询
	public String list() throws Exception {
		page = deptService.findPage("from Dept where state=1", page, Dept.class, null);
		page.setUrl("deptAction_list");
		return "dept_list";
	}

	// deptAction_toview
	public String toview() throws Exception {
		Dept dd = deptService.get(Dept.class, dept.getId());
		// 进行压zhan
		ActionContext.getContext().getValueStack().push(dd);
		return "dept_toview";
	}

	// 转到新增部门页面,将父部门展示出来
	public String tocreate() throws Exception {
		// 查询所有部门
		List<Dept> list = deptService.find("from Dept where state=1", Dept.class, null);
		// 压入context中
		ActionContext.getContext().put("deptList", list);
		return "dept_tocreate";
	}

	// deptAction_insert 新增部门
	public String insert() throws Exception {
		deptService.saveOrUpdate(dept);
		return list();
		/* return "dept_insert"; */
	}

	// toUpdate 修改
	public String toupdate() throws Exception {
		// 查询用户 并且查询所有部门
		Dept nDept = deptService.get(Dept.class, dept.getId());
		// 查询并且查询所有部门
		List<Dept> list = deptService.find("from Dept where state=1", Dept.class, null);
		// 去除自己的部门
		list.remove(nDept);
		// 进行压zhan
		ActionContext.getContext().getValueStack().push(nDept);
		// 压入map栈
		ActionContext.getContext().put("deptList", list);
		return "dept_toupdate";
	}

	// update 保存修改部门
	public String update() throws Exception {
		deptService.saveOrUpdate(dept);
		return "dept_update";
	}

	// delete 删除操作
	public String delete() throws Exception {
		String[] ids=dept.getId().split(", ");
		//数据库设置级联删除
		deptService.delete(Dept.class, ids);
		return "dept_delete";
	}
	/*
	 * // 批量 public String delete() throws Exception {
	 * deptService.delete(entityClass, ids);(dept); return "dept_delete"; }
	 */

}
