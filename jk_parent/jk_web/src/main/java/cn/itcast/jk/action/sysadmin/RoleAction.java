package cn.itcast.jk.action.sysadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.Module;
import cn.itcast.jk.domain.Role;
import cn.itcast.jk.domain.User;
import cn.itcast.jk.exception.SysException;
import cn.itcast.jk.service.ModuleService;
import cn.itcast.jk.service.RoleService;
import cn.itcast.jk.utils.Page;

public class RoleAction extends BaseAction implements ModelDriven<Role> {
	private Role role = new Role();

	public Role getModel() {
		return role;
	}

	// 住如roleService
	private RoleService roleService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	// 注入moduleService
	private ModuleService moduleService;

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	// 定义page
	private Page page = new Page();

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	// 定义moduleIds
	private String moduleIds;

	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}

	// 分页查询
	public String list() throws Exception {
		page = roleService.findPage("from Role", page, Role.class, null);
		page.setUrl("roleAction_list");
		// 压栈
		ActionContext.getContext().getValueStack().push(page);
		return "role_list";
	}

	// roleAction_toview 查看
	public String toview() throws Exception {
		Role newRole = roleService.get(Role.class, role.getId());
		// 压栈
		ActionContext.getContext().getValueStack().push(newRole);
		return "role_toview";
	}

	// 到添加角色页面 tocreate
	public String tocreate() throws Exception {

		return "role_tocreate";
	}

	// 添加角色
	public String insert() throws Exception {
		roleService.saveOrUpdate(role);
		return SUCCESS;
	}

	// 进行回显toupdate
	public String toupdate() throws Exception {
		Role newrole = roleService.get(Role.class, role.getId());
		// 压栈
		ActionContext.getContext().getValueStack().push(newrole);
		return "role_toupdate";

	}

	// update 保存角色
	public String update() throws Exception {
		// 先查询
		Role newrole = roleService.get(Role.class, role.getId());
		newrole.setName(role.getName());
		newrole.setRemark(role.getRemark());
		roleService.saveOrUpdate(newrole);
		return SUCCESS;
	}

	// 删除角色
	public String delete() throws Exception {
		String[] ids = role.getId().split(", ");
		roleService.delete(Role.class, ids);
		return SUCCESS;
	}

	// tomodule 到新增菜单页面
	public String tomodule() throws SysException{
		try {
			Role newRole = roleService.get(Role.class, role.getId());
			// 压栈
			push(newRole);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException("id,没有选择!!!");
		}

		return "role_tomodule";
	}

	// roleAction_roleModuleJsonStr.action 利用fastjson
	public String roleModuleJsonStr() throws Exception {
		// [{"id":"值","pId":"值","name":"菜单名称","checked":"true|false"},{"id":"值","pId":"值","name":"菜单名称","checked":"true|false"}]
		// 查询角色
		Role newRole = roleService.get(Role.class, role.getId());
		// 查询垓角色的菜单
		Set<Module> modules = newRole.getModules();
		// 查询所有菜单
		List<Module> moduleList = moduleService.find("from Module where state=1", Module.class, null);
		// 定义一个list集合
		List<Map<String, Object>> list = new ArrayList<>();
		// 遍历集合
		for (Module module : moduleList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", module.getId());
			map.put("pId", module.getParentId());
			map.put("name", module.getName());
			if (modules.contains(module)) {
				map.put("checked", true);
			} else {
				map.put("checked", false);
			}
			list.add(map);
		}
		// 利用fastjson
		String str = JSON.toJSONString(list);
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(str);
		return NONE;
	}

	// 拼接字符串 拼接json
	public String roleModuleJsonStr_1() throws Exception {
		// [{"id":"值","pId":"值","name":"菜单名称","checked":"true|false"},{"id":"值","pId":"值","name":"菜单名称","checked":"true|false"}]
		// 查询角色
		Role newRole = roleService.get(Role.class, role.getId());
		// 查询垓角色的菜单
		Set<Module> modules = newRole.getModules();
		// 查询所有菜单
		List<Module> moduleList = moduleService.find("from Module where state=1", Module.class, null);
		String str = "[";
		int count = moduleList.size();
		for (Module module : moduleList) {
			count--;
			str += "{\"id\":\"";
			str += module.getId();
			str += "\",\"pId\":\"";
			str += module.getParentId();
			str += "\",\"name\":\"";
			str += module.getName();
			str += "\",\"checked\":\"";
			if (modules.contains(module)) {
				str += true + "\"}";
			} else {
				str += false + "\"}";
			}
			if (count != 0) {
				str += ",";
			}

		}
		str += "]";
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(str);
		return NONE;
	}

	// roleAction_module
	public String module() throws Exception {
		Set<Module> set = new HashSet<>();
		// 查询角色
		String[] str = moduleIds.split(",");
		Role newRole = roleService.get(Role.class, role.getId());
		for (int i = 0; i < str.length; i++) {
			Module module = moduleService.get(Module.class, str[i]);
			set.add(module);
		}

		newRole.setModules(set);
		roleService.saveOrUpdate(newRole);
		return SUCCESS;
	}

}
