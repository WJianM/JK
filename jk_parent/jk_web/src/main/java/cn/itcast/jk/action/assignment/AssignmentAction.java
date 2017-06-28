package cn.itcast.jk.action.assignment;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.expression.spel.ast.Assign;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.Assignment;
import cn.itcast.jk.domain.Fastmenu;
import cn.itcast.jk.domain.Module;
import cn.itcast.jk.domain.Mymenu;
import cn.itcast.jk.domain.User;
import cn.itcast.jk.utils.FastJsonUtils;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.SysConstant;
import cn.itcast.jk.service.AssignmentService;
import cn.itcast.jk.service.FastmenuService;
import cn.itcast.jk.service.ModuleService;
import cn.itcast.jk.service.MymenuService;
import cn.itcast.jk.service.UserService;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @Description:	AssignmentService接口
 * @Author:			rent
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2017-6-10 19:17:06
 */

public class AssignmentAction extends BaseAction implements ModelDriven<Assignment> {
	//注入service
	private AssignmentService assignmentService;
	public void setAssignmentService(AssignmentService assignmentService) {
		this.assignmentService = assignmentService;
	}
	private FastmenuService fastmenuService;
	
	public void setFastmenuService(FastmenuService fastmenuService) {
		this.fastmenuService = fastmenuService;
	}
	private MymenuService mymenuService;
	public void setMymenuService(MymenuService mymenuService) {
		this.mymenuService = mymenuService;
	}
	private ModuleService moduleService;
	
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	//model驱动
	private Assignment model = new Assignment();
	public Assignment getModel() {
		return this.model;
	}
	
	//作为属性驱动，接收并封装页面参数
	private Page page = new Page();			//封装页面的参数，主要当前页参数
	public void setPage(Page page) {
		this.page = page;
	}


	//列表展示
	public String list(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.CURRENT_USER_INFO);
		String hql = "from Assignment o where userid = ? order by state";			//查询所有内容
		//给页面提供分页数据
		page.setUrl("assignmentAction_list");		//配置分页按钮的转向链接
		page = assignmentService.findPage(hql, page, Assignment.class, new Object[]{user.getId()});
		super.push(page);
		
		return "plist";						//page list
	}
	
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	//转向新增页面
	public String tocreate(){
		//准备数据
		//List<Assignment> assignmentList = assignmentService.assignmentList();
		//super.put("assignmentList", assignmentList);		//页面就可以访问assignmentList
		//需要所有用户的复选框
		List<User> userList = userService.find("from User", User.class, null);
		super.put("userList", userList);
			
		return "pcreate";
	}
	
	//新增保存
	public String insert(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.CURRENT_USER_INFO);
		
		String[] ids = model.getSenderids().split(", ");
		for (String id : ids) {
			Assignment aa = new Assignment();
			aa.setUserid(id);
			aa.setContent(model.getContent());
			aa.setState(0.0);
			aa.setOvertime(model.getOvertime());
			aa.setUsername(user.getUserName());
			assignmentService.saveOrUpdate(aa);
		}
		return "alist";			//返回列表，重定向action_list
	}

	//转向修改页面
	public String toupdate(){
		//准备数据
		//List<Assignment> assignmentList = assignmentService.assignmentList();
		//super.put("assignmentList", assignmentList);		//页面就可以访问assignmentList
				
		//准备修改的数据
		//Assignment obj = assignmentService.get(Assignment.class, model.getId());
		//super.getValueStack().push(obj);
		
		//修改状态为已读
		String[] ids = model.getId().split(", ");
		for (String id : ids) {
			Assignment assignment = assignmentService.get(Assignment.class, id);
			assignment.setState(1.0);
			assignmentService.saveOrUpdate(assignment);
		}
		return "alist";
	}
	public String toupdate2(){
		//准备数据
		//List<Assignment> assignmentList = assignmentService.assignmentList();
		//super.put("assignmentList", assignmentList);		//页面就可以访问assignmentList
		
		//准备修改的数据
		//Assignment obj = assignmentService.get(Assignment.class, model.getId());
		//super.getValueStack().push(obj);
		
		//修改状态为已读
		String[] ids = model.getId().split(", ");
		for (String id : ids) {
			Assignment assignment = assignmentService.get(Assignment.class, id);
			assignment.setState(2.0);
			assignmentService.saveOrUpdate(assignment);
		}
		return "alist";
	}
	
	//修改保存
	public String update(){
		Assignment assignment = assignmentService.get(Assignment.class, model.getId());
		
		//设置修改的属性，根据业务去掉自动生成多余的属性
		assignment.setId(model.getId());
		assignment.setUserid(model.getUserid());
		assignment.setContent(model.getContent());
		assignment.setOvertime(model.getOvertime());
		assignment.setState(model.getState());
		assignment.setSenderids(model.getSenderids());
		
		assignmentService.saveOrUpdate(assignment);
		
		return "alist";
	}
	
	//删除一条
	public String deleteById(){
		assignmentService.deleteById(Assignment.class, model.getId());
		
		return "alist";
	}
	
	
	//删除多条
	public String delete(){
		assignmentService.delete(Assignment.class, model.getId().split(", "));
		
		return "alist";
	}
	
	//查看
	public String toview(){
		Assignment obj = assignmentService.get(Assignment.class, model.getId());
		super.push(obj);
		if (obj.getState()==0.0) {
			obj.setState(1.0);
		}
		assignmentService.saveOrUpdate(obj);
		return "pview";			//转向查看页面
	}
	//获取未读信息长度,
	public void getlength() throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.CURRENT_USER_INFO);
		String hql = "from Assignment o where userid = ? and state = 0.0";			//查询所有内容
		//给页面提供分页数据
		List<Assignment> find = assignmentService.find(hql, Assignment.class, new Object[]{user.getId()});
		//FastJsonUtils.write_json(ServletActionContext.getResponse(), find.size());
		ServletActionContext.getResponse().getWriter().println(find.size());
	}
	
	//getFastMenu快捷菜单
	public void getFastMenu() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.CURRENT_USER_INFO);
		
		List<Fastmenu> find = fastmenuService.find("from Fastmenu f where userid = ? order by f.count desc", Fastmenu.class,new Object[]{user.getId()});
		if (find.size()<5) {
			FastJsonUtils.write_json(ServletActionContext.getResponse(), find);
		}else {
			List<Fastmenu> subList = find.subList(0, 5);
			FastJsonUtils.write_json(ServletActionContext.getResponse(), subList);
		}
	}
	//getFastMenu自定义菜单
	public void getMyMenu() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.CURRENT_USER_INFO);
		
		List<Mymenu> find = mymenuService.find("from Mymenu f where userid = ?", Mymenu.class,new Object[]{user.getId()});
			FastJsonUtils.write_json(ServletActionContext.getResponse(), find);
	}
	
	
	//clearFastMenu清空常用列表
	public void clearFastMenu(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.CURRENT_USER_INFO);
		List<Fastmenu> find = fastmenuService.find("from Fastmenu where userid = ?", Fastmenu.class,new Object[]{ user.getId()});
		for (Fastmenu fastmenu : find) {
			fastmenuService.deleteById(Fastmenu.class, fastmenu.getId());
		}
	}
	
	
	//mymennucreate跳转到创建新增自定义菜单页面
	
	public String mymennucreate() throws Exception {
		List<Module> moduleList = moduleService.find("from Module where curl is not null and curl like ?", Module.class, new Object[]{"%"+"list"+"%"});
		super.put("moduleList", moduleList);
		System.out.println(moduleList);
		return "mymennucreate";
	}
	/**
	 * 创建自定义菜单
	 */
	private String murl;
	public String getMurl() {
		return murl;
	}
	public void setMurl(String murl) {
		this.murl = murl;
	}


	public String insertmymenu() throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.CURRENT_USER_INFO);
		List<Mymenu> list = mymenuService.find("from Mymenu where userid = ?", Mymenu.class, new Object[]{user.getId()});
		if (!list.isEmpty()) {
			for (Mymenu mymenu : list) {
				Mymenu mymenu2 = mymenuService.get(Mymenu.class, mymenu.getId());
				if (mymenu2!=null) {
					mymenuService.deleteById(Mymenu.class, mymenu2.getId());
				}
			}
		}
		
		String[] murlids = murl.split(", ");
		for (String m : murlids) {
			Mymenu mm = new Mymenu();
			List<Module> find = moduleService.find("from Module where curl = ? ", Module.class, new Object[]{m});
			mm.setMenuname(find.get(0).getName());
			mm.setMurl(m);
			mm.setUserid(user.getId());
			mymenuService.saveOrUpdate(mm);
		}
		return "insertmymenu";
	}
	
	
}
