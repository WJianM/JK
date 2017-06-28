package cn.itcast.jk.action.sysadmin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.util.UserDataElement;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.Dept;
import cn.itcast.jk.domain.Role;
import cn.itcast.jk.domain.User;
import cn.itcast.jk.service.DeptService;
import cn.itcast.jk.service.RoleService;
import cn.itcast.jk.service.UserService;
import cn.itcast.jk.utils.Encrypt;
import cn.itcast.jk.utils.Page;
import freemarker.template.utility.Execute;

public class UserAction extends BaseAction implements ModelDriven<User> {
	private User user = new User();

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	// 定义page属性
	private Page page = new Page<>();

	public void setPage(Page page) {
		this.page = page;
	}

	// 注入service
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 注入deptservice
	private DeptService deptService;

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	// 注入roleService
	private RoleService roleService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	// 定义roleIds 数组
	private String[] roleIds;

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	

	// 进行分页查询
	public String list() throws Exception {
		page = userService.findPage("from User where state=1", page, User.class, null);
		// 设置url
		page.setUrl("userAction_list");
		// 压栈
		ActionContext.getContext().getValueStack().push(page);
		return "user_list";
	}

	// tocreate 新增用户
	public String tocreate() throws Exception {
		// 查询所有用户
		List<User> userlist = userService.find("from User where state=1", User.class, null);
		// 压入map
		ActionContext.getContext().put("userList", userlist);
		// 查询所有部门
		List<Dept> deptList = deptService.find("from Dept where state=1", Dept.class, null);
		ActionContext.getContext().put("deptList", deptList);
		return "user_tocreate";
	}

	// 添加用户 insert
	public String insert() throws Exception {
		String pass=Encrypt.md5("1234", user.getUserName());
		user.setPassword(pass);
		userService.saveOrUpdate(user);
		return SUCCESS;
	}

	// toview 查看用户
	public String toview() throws Exception {
		ActionContext.getContext().getValueStack().push(userService.get(User.class, user.getId()));
		return "user_toview";
	}

	// toupdate 回显用户
	public String toupdate() throws Exception {
		ActionContext.getContext().getValueStack().push(userService.get(User.class, user.getId()));
		// 查询所有用户
		List<User> userlist = userService.find("from User where state=1", User.class, null);
		// 压入map
		ActionContext.getContext().put("userList", userlist);
		// 查询所有部门
		List<Dept> deptList = deptService.find("from Dept where state=1", Dept.class, null);
		ActionContext.getContext().put("deptList", deptList);

		return "user_toupdate";
	}

	// update 修改保存用户
	public String update() throws Exception {
		User newUser = userService.get(User.class, user.getId());
		newUser.setState(user.getState());
		newUser.getDept().setId(user.getDept().getId());
		newUser.setUserName(user.getUserName());
		newUser.getUserInfo().getManager().setId(newUser.getUserInfo().getManager().getId());
		userService.saveOrUpdate(newUser);
		return SUCCESS;
	}

	// 删除客户delete
	public String delete() throws Exception {
		String[] ids = user.getId().split(", ");
		userService.delete(User.class, ids);
		return SUCCESS;
	}

	// torole 到角色修改的页面
	public String torole() throws Exception{
		// 查询该用户
		User newUser = userService.get(User.class, user.getId());
		// 查询用户对应的角色
		Set<Role> set = newUser.getRoles();
		// 拼接一个字符串
		String str = "";
		for (Role role : set) {
			str += role.getName() + ",";
		}
		// 查询出所有的角色
		List<Role> roleList = roleService.find("from Role", Role.class, null);
		// 压入栈
		put("roleList", roleList);
		put("userRoleStr", str);

		return "user_torole";
	}

	// role
	public String role() throws Exception {
		// 定义一个set
		Set<Role> roles = new HashSet<>();
		// 遍历字符串数组
		for (int i = 0; i < roleIds.length; i++) {
			// 进行查询
			Role role = roleService.get(Role.class, roleIds[i]);
			roles.add(role);

		}
		// 查询用户
		User newUser = userService.get(User.class, user.getId());
        newUser.setRoles(roles);
        userService.Update(newUser);
		return SUCCESS;
	}

}
