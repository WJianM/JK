package cn.itcast.jk.action.cargo;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.service.ContractProductService;
import cn.itcast.jk.service.FactoryService;
import cn.itcast.jk.utils.Page;

public class ContractProductAction extends BaseAction implements ModelDriven<ContractProduct> {
	private ContractProductService contractProductService;

	public void setContractProductService(ContractProductService contractProductService) {
		this.contractProductService = contractProductService;
	}

	private FactoryService factoryService;

	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

	private ContractProduct model = new ContractProduct();

	@Override
	public ContractProduct getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	// page
	private Page page = new Page();

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	// contractProductAction_tocreate
	public String tocreate() throws Exception {
		// 查询该合同下 所有货物
		page = contractProductService.findPage("from ContractProduct where contract.id = ?", page,
				ContractProduct.class, new Object[] { model.getContract().getId() });
		page.setUrl("contractProductAction_tocreate");
		// 压栈
		push(page);
		// 查询厂家
		List<Factory> factoryList = factoryService.find("from Factory where state =1 and ctype='货物'", Factory.class,
				null);
		put("factoryList", factoryList);
		return "contractProduct_tocreate";
	}

	// contractProductAction_insert
	public String insert() throws Exception {
		// 保存货物
		contractProductService.saveOrUpdate(model);
		return tocreate();
	}

	// toupdate.action?id=${o.id} 修改
	public String toupdate() throws Exception {
		// 查询货物
		ContractProduct contractProduct = contractProductService.get(ContractProduct.class, model.getId());
		// 进行数据回显
		push(contractProduct);
		// 查询厂家
		List<Factory> factoryList = factoryService.find("from Factory where state =1 and ctype='货物'", Factory.class,
				null);
		put("factoryList", factoryList);
		return "contractProduct_toupdate";
	}

	// update 修改保存
	public String update() throws Exception {
		// 查询货物
		ContractProduct contractProduct = contractProductService.get(ContractProduct.class, model.getId());
		contractProduct.setFactory(model.getFactory());
		contractProduct.setProductNo(model.getProductNo());
		contractProduct.setProductImage(model.getProductImage());
		contractProduct.setCnumber(model.getCnumber());
		contractProduct.setPackingUnit(model.getPackingUnit());
		contractProduct.setLoadingRate(model.getLoadingRate());
		contractProduct.setBoxNum(model.getBoxNum());
		contractProduct.setPrice(model.getPrice());
		contractProduct.setOrderNo(model.getOrderNo());
		contractProduct.setProductDesc(model.getProductDesc());
		contractProduct.setProductRequest(model.getProductRequest());
		contractProductService.saveOrUpdate(contractProduct);
		return tocreate();
	}

	// delete 删除
	public String delete() throws Exception {
		// 得到对象
		contractProductService.deleteById(ContractProduct.class, model.getId());
		return tocreate();
	}

}
