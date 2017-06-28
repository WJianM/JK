package cn.itcast.jk.filter;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.dao.impl.BaseDaoImpl;
import cn.itcast.jk.domain.Fastmenu;
import cn.itcast.jk.domain.Module;
import cn.itcast.jk.domain.User;
import cn.itcast.jk.service.FastmenuService;
import cn.itcast.jk.service.ModuleService;
import cn.itcast.jk.service.impl.FastmenuServiceImpl;
import cn.itcast.jk.service.impl.ModuleServiceImpl;
import cn.itcast.jk.utils.SysConstant;

public class menufilter extends MethodFilterInterceptor {

	private ModuleService moduleService;
	private FastmenuService fastmenuService;

	public void setFastmenuService(FastmenuService fastmenuService) {
		this.fastmenuService = fastmenuService;
	}
 
	public void setModuleService(ModuleService moduleService) {
		
		this.moduleService = moduleService;
	}

	public String doIntercept(ActionInvocation arg0) throws Exception {
		String actionName = arg0.getProxy().getActionName();
		String namespace = arg0.getProxy().getNamespace();
		/*
		 * BaseDaoImpl baseDao1 = new BaseDaoImpl(); BaseDaoImpl baseDao2 = new
		 * BaseDaoImpl();
		 */
		// final ModuleServiceImpl moduleServiceImpl = new ModuleServiceImpl();
		// final FastmenuServiceImpl fastmenuServiceImpl = new
		// FastmenuServiceImpl();

		String uurl = namespace + "/" + actionName;
		String string = uurl.replace("//", "/").substring(1);
		if (string != null && string != "") {
			if (actionName.contains("list") || actionName.contains("List")) {
				List<Module> find = moduleService.find("from Module where curl = ?", Module.class,
						new Object[] { string });
				System.out.println();
				if (!find.isEmpty()) {
					String name = find.get(0).getName();
					User user = (User) ServletActionContext.getRequest().getSession()
							.getAttribute(SysConstant.CURRENT_USER_INFO);
					String murl = (namespace + "/" + actionName).replace("//",
							"/").substring(1);
					System.out.println("------------------____-----------------------------------------------------------------------------------------------------"+murl);
					
					List<Fastmenu> fast = fastmenuService.find("from Fastmenu where murl = ? and userid = ?", Fastmenu.class,
							new Object[] { murl ,user.getId()});
					if (fast.isEmpty()) {
						Fastmenu f = new Fastmenu();
						System.out.println(namespace);
						System.out.println(actionName);
						System.out.println("1-------------------------------------------------------------------"+murl);
						f.setMurl(murl);
						f.setCount(1.0);
						f.setUserid(user.getId());
						f.setMname(name);
						fastmenuService.saveOrUpdate(f);
					} else {
						Fastmenu fastmenu = fast.get(0);
						fastmenu.setCount(fastmenu.getCount() + 1);
						fastmenuService.saveOrUpdate(fastmenu);
					}
				}
			}
		}
		return arg0.invoke();
	}

}
