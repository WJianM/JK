package cn.itcast.jk.action.baseinfo;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.domain.User;
import cn.itcast.jk.service.FactoryService;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;

public class factoryAction extends BaseAction implements ModelDriven<Factory>{

	private Factory factory=new Factory();
	@Override
	public Factory getModel() {
		// TODO Auto-generated method stub
		return factory;
	}
	
	/**
	 * 厂家信息显示
	 * @return
	 */
	//分页查询
	private Page page=new Page<>();
	public void setPage(Page page) {
		this.page = page;
	}
	
	private FactoryService factoryService;
	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

	public String list(){
		System.out.println("11111111111111");
		try {
			page = factoryService.findPage("from Factory", page, Factory.class, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 设置url
		System.out.println("2222222222222222");
		page.setUrl("factoryAction_list");
		// 压栈
		ActionContext.getContext().getValueStack().push(page);

		return "page";
	}
	
	
	/**
	 * 详细信息
	 * @return
	 * @throws Exception
	 */
	public String toview() throws Exception {
		ActionContext.getContext().getValueStack().push(factoryService.get(Factory.class, factory.getId()));
		return "factory_toview";
	}
	
	
	
	/**
	 * tocreate 新增工厂页面
	 * @return
	 * @throws Exception
	 */
	public String tocreate() throws Exception {
		return "factory_tocreate";
	}
	
	/**
	 * 添加工厂
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		factoryService.saveOrUpdate(factory);
		return SUCCESS;
	}
	
	
	/**
	 * toupdate 回显工厂
	 * @return
	 * @throws Exception
	 */
	public String toupdate() throws Exception {
		ActionContext.getContext().getValueStack().push(factoryService.get(Factory.class, factory.getId()));
		return "factory_toupdate";
	}

	/**
	 * update 修改保存工厂
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		Factory newFactory = factoryService.get(Factory.class, factory.getId());
		/*
		 * private String id;
	private String ctype;			//厂家类型：货物/附件
	private String fullName;		//厂家全称
	private String factoryName;		//厂家简称
	private String contacts;		//联系人
	private String phone;			//电话
	private String mobile;			//手机
	private String fax;				//传真
	private String address;			//地址
	private String inspector;		//验货员，杰信代表
	private String remark;			//说明
	private Integer orderNo;		//排序号
	private Integer state;			//状态：1正常0停用(伪删除)
		 */
		newFactory.setCtype(factory.getCtype());
		newFactory.setFullName(factory.getFullName());
		newFactory.setFactoryName(factory.getFactoryName());
		newFactory.setContacts(factory.getContacts());
		newFactory.setPhone(factory.getPhone());
		newFactory.setMobile(factory.getMobile());
		newFactory.setFax(factory.getFax());
		newFactory.setAddress(factory.getAddress());
		newFactory.setInspector(factory.getInspector());
		newFactory.setRemark(factory.getRemark());
		newFactory.setOrderNo(factory.getOrderNo());
		newFactory.setState(factory.getState());
		
		factoryService.saveOrUpdate(newFactory);
		return SUCCESS;
	}
	
	
	/**
	 * 删除工厂delete
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String[] ids = factory.getId().split(", ");
		factoryService.delete(Factory.class, ids);
		return SUCCESS;
	}
	
	
	/**
	 * 查询工厂delete
	 * @return
	 * @throws Exception
	 */
	private String name;
	public void setName(String name) {
		this.name = name;
	}

	public String search() throws Exception {
		/*String p = ServletActionContext.getRequest().getParameter("search");
		System.out.println(p);
		
		List<Factory> list = factoryService.find("from Factory where factoryName = "+p, Factory.class, null);
		System.out.println(list);*/
		String hql ="";
		List<Object> list = new ArrayList<>();
		if (!UtilFuns.isEmpty(name)) {
			hql = "from Factory where factoryName like ?";
			list.add("%"+name+"%");
		}
		page=factoryService.findPage(hql ,page, Factory.class, list.toArray());
		super.push(name);
		push(page);

		return "page";
	}


}
