package cn.itcast.jk.action.sysadmin;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.Module;
import cn.itcast.jk.service.ModuleService;
import cn.itcast.jk.utils.Page;

public class ModuleAction extends BaseAction implements ModelDriven<Module> {
	private Module module = new Module();

	public Module getModel() {
		return module;
	}

	// 注入moduleService
	private ModuleService moduleService;

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	// page
	private Page page = new Page();

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String list() throws Exception {
		// 进行分页查询
		page = moduleService.findPage("from Module", page, Module.class, null);
		page.setUrl("moduleAction_list");
		push(page);
		return "module_list";
	}

	// toview 查看
	public String toview() throws Exception {
		push(moduleService.get(Module.class, module.getId()));
		return "module_toview";
	}
	// tocreate
	public String tocreate() throws Exception {
		List<Module> list=moduleService.find("from Module", Module.class, null);
		put("moduleList", list);
		return "module_tocreate";
	}
	public String insert() throws Exception {
		moduleService.saveOrUpdate(module);
		return SUCCESS;
	}
	//进行回显
	public String toupdate() throws Exception {
		push(moduleService.get(Module.class, module.getId()));
		return "module_toupdate";
	}
	public String update() throws Exception {
		moduleService.saveOrUpdate(module);
		return SUCCESS;
	}
	public String delete() throws Exception {
		String[] ids=module.getId().split(", ");
		moduleService.delete(Module.class, ids);
		return SUCCESS;
	}
	
	

}
