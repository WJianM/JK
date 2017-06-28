package cn.itcast.jk.action.cargo;

import java.util.List;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.domain.Finance;
import cn.itcast.jk.domain.Invoice;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.service.ExportService;
import cn.itcast.jk.service.FinanceService;
import cn.itcast.jk.service.InvoiceService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @Description: FinanceService接口
 * @Author: rent
 * @Company: http://java.itcast.cn
 * @CreateDate: 2017-6-9 21:10:30
 */

public class FinanceAction extends BaseAction implements ModelDriven<Finance> {
	// 注入财务service
	private FinanceService financeService;

	public void setFinanceService(FinanceService financeService) {
		this.financeService = financeService;
	}

	// 注入发票service
	private InvoiceService invoiceService;

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	// 注入货运单service
	private ExportService exportService;

	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}

	// model驱动
	private Finance model = new Finance();

	public Finance getModel() {
		return this.model;
	}

	// 作为属性驱动，接收并封装页面参数
	private Page page = new Page(); // 封装页面的参数，主要当前页参数

	public void setPage(Page page) {
		this.page = page;
	}

	// 列表展示
	public String list() {
		String hql = "from Finance"; // 查询所有内容
		// 给页面提供分页数据
		page.setUrl("financeAction_list"); // 配置分页按钮的转向链接
		page = financeService.findPage(hql, page, Finance.class, null);
		super.push(page);

		return "plist"; // page list
	}

	// 转向新增页面
	public String tocreate() {
		// 准备数据
		String hql = "from Invoice where state =1";
		// 分页显示
		page = invoiceService.findPage(hql, page, Invoice.class, null);
		// 设置分页路径
		page.setUrl("financeAction_tocreate");
		// 分页放入值栈
		super.push(page);
		return "pcreate";
	}

	// 新增保存
	public String insert() {

		// 根据选中的id查询出发票id
		Invoice invoice = invoiceService.get(Invoice.class, model.getInvoiceId());
		// 查询发票中的装箱号
		String packingList_id = invoice.getPackingList_id();
		// 通过发票设置 财务单的装箱号
		model.setPackingList_id(invoice.getId());
		// 设置状态
		model.setState(0);
		// 将财务单存储
		financeService.saveOrUpdate(model);
		// 设置财务单id
		// 发票单修改状态 避免重复出现
		invoice.setState(2);
		// 发票单保存
		invoiceService.saveOrUpdate(invoice);

		return "alist"; // 返回列表，重定向action_list
	}

	// 转向修改页面
	public String toupdate() {
		// 准备修改的数据
		Finance obj = financeService.get(Finance.class, model.getId());
		super.push(obj);
		// 现查找出来之前连接的发票
		Invoice invoice = invoiceService.get(Invoice.class, obj.getInvoiceId());
		// 将他的状态修改成1
		invoice.setState(1);
		// 然后保存
		invoiceService.saveOrUpdate(invoice);
		// 准备所有数据
		String hql = "from Invoice where state =1";
		// 分页显示
		page = invoiceService.findPage(hql, page, Invoice.class, null);
		// 设置分页路径
		page.setUrl("financeAction_toupdate");
		// 分页放入值栈
		super.push(page);

		return "pupdate";
	}

	// 修改保存
	public String update() {
		Finance finance = financeService.get(Finance.class, model.getId());

		// 设置修改的属性，根据业务去掉自动生成多余的属性
		finance.setId(model.getId());
		finance.setInputDate(model.getInputDate());
		finance.setInputBy(model.getInputBy());
		finance.setState(model.getState());
		finance.setCreateBy(model.getCreateBy());
		finance.setCreateDept(model.getCreateDept());
		finance.setCreateTime(model.getCreateTime());

		// 查找选择的发票
		Invoice invoice = invoiceService.get(Invoice.class, finance.getInvoiceId());
		// 将他的状态修改成2
		invoice.setState(2);
		finance.setPackingList_id(invoice.getPackingList_id());
		financeService.saveOrUpdate(finance);
		// 然后保存发票
		invoiceService.saveOrUpdate(invoice);

		return "alist";
	}

	// 删除一条
	public String deleteById() {
		financeService.deleteById(Finance.class, model.getId());

		// 查找选择的发票
		Invoice invoice = invoiceService.get(Invoice.class, model.getId());
		// 将他的状态修改成1
		invoice.setState(1);
		// 保存
		invoiceService.saveOrUpdate(invoice);

		return "alist";
	}

	// 删除多条
	public String delete() {

		String[] split = model.getId().split(", ");
		// 查找选择的财务
		for (String string : split) {
			Finance finance = financeService.get(Finance.class, string);
			//查询财务内的发票
			Invoice invoice = invoiceService.get(Invoice.class, finance.getInvoiceId());
			invoice.setState(1);
			// 保存
			invoiceService.saveOrUpdate(invoice);
			
		}
		financeService.delete(Finance.class, model.getId().split(", "));
		return "alist";
	}

	// 查看
	public String toview() {
		Finance obj = financeService.get(Finance.class, model.getId());
		super.push(obj);
		Invoice invoice = invoiceService.get(Invoice.class, model.getId());
		super.push(invoice);

		return "pview"; // 转向查看页面
	}

	// 提交数据
	public String submit() {
		Finance obj = financeService.get(Finance.class, model.getId());
		obj.setState(1);
		financeService.saveOrUpdate(obj);
		
		Export export = exportService.get(Export.class, obj.getPackingList_id());
		export.setState(6);
		exportService.saveOrUpdate(export);
		return SUCCESS; // 转向查看页面
	}

	// 取消数据
	public String cancel() {
		Finance obj = financeService.get(Finance.class, model.getId());
		obj.setState(0);
		financeService.saveOrUpdate(obj);
		
		Export export = exportService.get(Export.class, obj.getPackingList_id());
		export.setState(5);
		exportService.saveOrUpdate(export);
		return SUCCESS; // 转向查看页面
	}
}
