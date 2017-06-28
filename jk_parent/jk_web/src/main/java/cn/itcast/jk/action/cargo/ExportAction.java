package cn.itcast.jk.action.cargo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.export.webservice.IEpService;
import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.domain.ExportProduct;
import cn.itcast.jk.service.ContractService;
import cn.itcast.jk.service.ExportProductService;
import cn.itcast.jk.service.ExportService;
import cn.itcast.jk.utils.FastJsonUtils;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;
import freemarker.template.utility.Execute;

public class ExportAction extends BaseAction implements ModelDriven<Export> {
	// 注入service
	private ExportService exportService;

	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}

	// 注入ExportProductService
	private ExportProductService exportProductService;

	public void setExportProductService(ExportProductService exportProductService) {
		this.exportProductService = exportProductService;
	}

	private Export model = new Export();

	@Override
	public Export getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	// page
	Page page = new Page();

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	// 注入合同service
	private ContractService contractService;

	// contractList 查询已上报的所有合同
	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
	//注入IEpService
	private IEpService exportClient;

	public void setExportClient(IEpService exportClient) {
		this.exportClient = exportClient;
	}

	public String contractList() throws Exception {
		page = contractService.findPage("from Contract where state=1", page, Contract.class, null);
		page.setUrl("exportAction_contractList");
		push(page);
		return "export_contractList";
	}

	// tocreate()
	public String tocreate() {
		return "export_tocreate";
	}

	// 插入报运单 insert
	public String insert() throws Exception {
		exportService.saveOrUpdate(model);
		return SUCCESS;
	}

	// .list() 分页查询报运单
	public String list() throws Exception {
		String hql = "from Export order by inputDate desc";
		page = exportService.findPage(hql, page, Export.class, null);
		page.setUrl("exportAction_list");
		// 压栈
		push(page);
		return "export_list";
	}

	// toview 查看报运单信息
	public String toview() throws Exception {
		// 查询报运单
		Export export = exportService.get(Export.class, model.getId());
		// 压栈
		push(export);
		return "export_toview";
	}

	// 删除 delete exportAction_delete
	public String delete() throws Exception {
		String[] ids = model.getId().split(", ");
		exportService.delete(Export.class, ids);
		return SUCCESS;
	}

	// exportAction_submit提交
	public String submit() throws Exception {
		// 提交状态设为1
		Export export = exportService.get(Export.class, model.getId());
		export.setState(1);
		exportService.saveOrUpdate(export);
		return SUCCESS;
	}

	// exportAction_cancel
	public String cancel() throws Exception {
		// 取消 状态设为0
		Export export = exportService.get(Export.class, model.getId());
		export.setState(0);
		exportService.saveOrUpdate(export);
		return SUCCESS;
	}

	// 修改 进行数据回显
	public String toupdate() throws Exception {
		// 查询报单
		Export export = exportService.get(Export.class, model.getId());
		push(export);
		return "export_toupdate";
	}

	// exportAction_getExportProducts 查询该合同下所有的货物
	public String getExportProducts() throws Exception {
		// 查询报运单
		Export export = exportService.get(Export.class, model.getId());
		Set<ExportProduct> set = export.getExportProducts();
		// 转为json
		FastJsonUtils.write_json(ServletActionContext.getResponse(), set);
		return NONE;
	}

	// exportAction_update
	public String update() throws Exception {
		// 保存数据
		Export export = exportService.get(Export.class, model.getId());
		export.setInputDate(model.getInputDate());
		export.setLcno(model.getLcno());
		export.setConsignee(model.getConsignee());
		export.setShipmentPort(model.getShipmentPort());
		export.setDestinationPort(model.getDestinationPort());
		export.setTransportMode(model.getTransportMode());
		export.setPriceCondition(model.getPriceCondition());
		export.setMarks(model.getMarks());
		export.setRemark(model.getRemark());
		// 定义存储商品明细的set集合
		Set<ExportProduct> sets = new HashSet<>();
		// 遍历id
		for (int i = 0; i < mr_id.length; i++) {
			ExportProduct exportProduct = exportProductService.get(ExportProduct.class, mr_id[i]);
			if ("1".equals(mr_changed[i])) {
				// 查询出该商品明细

				exportProduct.setCnumber(mr_cnumber[i]);
				exportProduct.setGrossWeight(mr_grossWeight[i]);
				exportProduct.setNetWeight(mr_netWeight[i]);
				exportProduct.setSizeLength(mr_sizeLength[i]);
				exportProduct.setSizeWidth(mr_sizeWidth[i]);
				exportProduct.setSizeHeight(mr_sizeHeight[i]);
				exportProduct.setExPrice(mr_exPrice[i]);
				exportProduct.setTax(mr_tax[i]);

			}
			sets.add(exportProduct);
			
		}
		export.setExportProducts(sets);
		exportService.saveOrUpdate(export);
		return SUCCESS;
	}
	
	//exportAction_wsEmport
	public String wsEmport() throws Exception {
	//查询报单
		Export export = exportService.get(Export.class, model.getId());
		String string = JSON.toJSONString(export);
		String newJson=exportClient.exportE(string);
		//解析json
		Export returnExport=JSON.parseObject(newJson,Export.class);
		exportService.update(returnExport);
		return SUCCESS;
	}

	// 定义数组属性
	private String[] mr_id;
	private String[] mr_changed;
	private Integer[] mr_cnumber;

	private Double[] mr_grossWeight;
	private Double[] mr_netWeight;
	private Double[] mr_sizeLength;

	private Double[] mr_sizeHeight;
	private Double[] mr_sizeWidth;
	private Double[] mr_exPrice;

	private Double[] mr_tax;

	public void setMr_id(String[] mr_id) {
		this.mr_id = mr_id;
	}

	public void setMr_changed(String[] mr_changed) {
		this.mr_changed = mr_changed;
	}

	public void setMr_cnumber(Integer[] mr_cnumber) {
		this.mr_cnumber = mr_cnumber;
	}

	public void setMr_grossWeight(Double[] mr_grossWeight) {
		this.mr_grossWeight = mr_grossWeight;
	}

	public void setMr_netWeight(Double[] mr_netWeight) {
		this.mr_netWeight = mr_netWeight;
	}

	public void setMr_sizeLength(Double[] mr_sizeLength) {
		this.mr_sizeLength = mr_sizeLength;
	}

	public void setMr_sizeHeight(Double[] mr_sizeHeight) {
		this.mr_sizeHeight = mr_sizeHeight;
	}

	public void setMr_sizeWidth(Double[] mr_sizeWidth) {
		this.mr_sizeWidth = mr_sizeWidth;
	}

	public void setMr_exPrice(Double[] mr_exPrice) {
		this.mr_exPrice = mr_exPrice;
	}

	public void setMr_tax(Double[] mr_tax) {
		this.mr_tax = mr_tax;
	}

}
