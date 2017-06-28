package cn.itcast.jk.action.baseinfo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.Log;
import cn.itcast.jk.service.LogService;
import cn.itcast.jk.utils.Page;

public class logAction extends BaseAction implements ModelDriven<Log>{

	private Log log=new Log();
	@Override
	public Log getModel() {
		// TODO Auto-generated method stub
		return log;
	}
	
	/**
	 * 登录信息显示
	 * @return
	 */
	//分页查询
	private Page page=new Page<>();
	public void setPage(Page page) {
		this.page = page;
	}
	
	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}


	public String list(){
		System.out.println("11111111111111");
		try {
			page = logService.findPage("from Log", page, Log.class, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 设置url
		System.out.println("2222222222222222");
		page.setUrl("logAction_list");
		// 压栈
		ActionContext.getContext().getValueStack().push(page);

		return "page";
	}
}
