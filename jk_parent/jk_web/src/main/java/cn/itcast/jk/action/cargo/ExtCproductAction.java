package cn.itcast.jk.action.cargo;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.ExtCproduct;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.service.ExtCproductService;
import cn.itcast.jk.service.FactoryService;
import cn.itcast.jk.utils.Page;

public class ExtCproductAction extends BaseAction implements ModelDriven<ExtCproduct> {
	private ExtCproduct model = new ExtCproduct();

	@Override
	public ExtCproduct getModel() {
		return model;
	}

	private ExtCproductService extCproductService;

	public void setExtCproductService(ExtCproductService extCproductService) {
		this.extCproductService = extCproductService;
	}

	private FactoryService factoryService;

	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

	private Page page = new Page<>();

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	// tocreate
	public String tocreate() throws Exception {
		// 查询所有附件的厂家
		List<Factory> factoryList = factoryService.find("from Factory where ctype='附件' and state=1", Factory.class,
				null);
		// 压栈
		put("factoryList", factoryList);
		// 分页查询
		page = extCproductService.findPage("from ExtCproduct where contractProduct.id=?", page, ExtCproduct.class, new Object[]{model.getContractProduct().getId()});
		// 设置url
		page.setUrl("extCproductAction_tocreate");
		push(page);
		return "extCproduct_tocreate";
	}

	// extCproductAction_insert 保存附件
	public String insert() throws Exception {
		extCproductService.saveOrUpdate(model);//ExtCproductService
		return tocreate();
	}

	// extCproductAction_toupdate.action 跳转修改页面 进行数据回显
	public String toupdate() throws Exception {
		// 查询
		ExtCproduct extCproduct = extCproductService.get(ExtCproduct.class, model.getId());
		// 压栈
		push(extCproduct);
		List<Factory> factoryList = factoryService.find("from Factory where ctype='附件' and state=1", Factory.class,
				null);
		// 压栈
		put("factoryList", factoryList);
		
		return "extCproduct_tocreate_toupdate";
	}

	// update 保存数据
	public String update() throws Exception {
		// 查询
		ExtCproduct extCproduct = extCproductService.get(ExtCproduct.class, model.getId());
		extCproduct.setFactory(model.getFactory());
		extCproduct.setFactoryName(model.getFactoryName());
		extCproduct.setProductNo(model.getProductNo());
		extCproduct.setProductImage(model.getProductImage());
		extCproduct.setCnumber(model.getCnumber());
		extCproduct.setPackingUnit(model.getPackingUnit());
		extCproduct.setPrice(model.getPrice());
		extCproduct.setOrderNo(model.getOrderNo());
		extCproduct.setProductDesc(model.getProductDesc());
		extCproduct.setProductRequest(model.getProductRequest());
		extCproductService.saveOrUpdate(extCproduct);
		return tocreate();
	}

	// delete.action? 删除
	public String delete() throws Exception {
		extCproductService.deleteById(ExtCproduct.class, model.getId());;
		return tocreate();
	}

}
