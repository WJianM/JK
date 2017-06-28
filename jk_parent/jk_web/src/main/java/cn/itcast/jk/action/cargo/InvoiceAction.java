package cn.itcast.jk.action.cargo;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.domain.Invoice;
import cn.itcast.jk.domain.PackingList;
import cn.itcast.jk.domain.ShippingOrder;
import cn.itcast.jk.service.ExportService;
import cn.itcast.jk.service.InvoiceService;
import cn.itcast.jk.service.PackingListService;
import cn.itcast.jk.service.ShippingOrderService;
import cn.itcast.jk.utils.DownloadUtil;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;

/**
 * @Description: InvoiceService接口
 * @Author: rent
 * @Company: http://java.itcast.cn
 * @CreateDate: 2017-6-9 20:43:05
 */

public class InvoiceAction extends BaseAction implements ModelDriven<Invoice> {
	// 注入service
	private InvoiceService invoiceService;

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	private PackingListService packingListService;

	public void setPackingListService(PackingListService packingListService) {
		this.packingListService = packingListService;
	}

	private ShippingOrderService shippingOrderService;

	public void setShippingOrderService(ShippingOrderService shippingOrderService) {
		this.shippingOrderService = shippingOrderService;
	}

	private ExportService exportService;

	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}

	// model驱动
	private Invoice model = new Invoice();

	public Invoice getModel() {
		return this.model;
	}

	private String inputDate;

	public String getInputDate() {
		return inputDate;
	}

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	// 作为属性驱动，接收并封装页面参数
	private Page page = new Page(); // 封装页面的参数，主要当前页参数

	public void setPage(Page page) {
		this.page = page;
	}

	public Page getPage() {
		return page;
	}

	// 为查询添加字段
	private String ctradeTerms;

	public String getCtradeTerms() {
		return ctradeTerms;
	}

	public void setCtradeTerms(String ctradeTerms) {
		this.ctradeTerms = ctradeTerms;
	}

	// 列表展示和查询
	public String list() {
		String hql = "from Invoice where 1=1"; // 查询所有内容
		List<String> list = new ArrayList<String>();
		if (UtilFuns.isNotEmpty(ctradeTerms)) {
			hql += " and tradeTerms = ?";
			list.add(ctradeTerms);
		}
		// 给页面提供分页数据
		page = invoiceService.findPage(hql, page, Invoice.class, list.toArray());
		page.setUrl("invoiceAction_list"); // 配置分页按钮的转向链接
		super.push(page);

		return "plist"; // page list
	}

	// 转向新增页面
	public String tocreate() {
		// 准备数据 查询所有的委托单
		String hql = "from ShippingOrder where state=1";
		page = shippingOrderService.findPage(hql, page, ShippingOrder.class, null);
		page.setUrl("invoiceAction_tocreate");
		super.push(page);
		return "pcreate";
	}

	// 新增保存
	public String insert() {
		// 获取委托单id
		ShippingOrder shipp = shippingOrderService.get(ShippingOrder.class, model.getShippId());
		// 将委托单置为2 已发票状态
		shipp.setState(2);
		shippingOrderService.saveOrUpdate(shipp);
		// 将发票状态置为0 草稿状态
		model.setState(0);
		// 设置装箱单id
		model.setPackingList_id(shipp.getPackId());
		invoiceService.saveOrUpdate(model);

		return "alist"; // 返回列表，重定向action_list
	}

	// 转向修改页面
	public String toupdate() {
		// 查询修改的发票
		model = invoiceService.get(Invoice.class, model.getId());
		super.push(model);
		// 将委托单置为1 已上报状态
		ShippingOrder shipp = shippingOrderService.get(ShippingOrder.class, model.getShippId());
		shipp.setState(1);
		shippingOrderService.saveOrUpdate(shipp);
		// 准备数据 查询所有已上报的委托单
		String hql = "from ShippingOrder where state=1";
		page = shippingOrderService.findPage(hql, page, ShippingOrder.class, null);
		page.setUrl("invoiceAction_toupdate");
		super.push(page);

		return "pupdate";
	}

	// 修改保存
	public String update() {
		Invoice invoice = invoiceService.get(Invoice.class, model.getId());

		invoice.setScNo(model.getScNo());
		invoice.setBlNo(model.getBlNo());
		invoice.setTradeTerms(model.getTradeTerms());
		invoice.setCreateBy(model.getCreateBy());
		invoice.setCreateDept(model.getCreateDept());
		invoice.setCreateTime(model.getCreateTime());
		invoice.setShippId(model.getShippId());
		invoice.setState(0);
		// 设置装箱单id
		ShippingOrder shippingOrder = shippingOrderService.get(ShippingOrder.class, model.getShippId());
		// 设置委托单的状态
		shippingOrder.setState(2);
		shippingOrderService.saveOrUpdate(shippingOrder);
		invoice.setPackingList_id(shippingOrder.getPackId());
		invoiceService.saveOrUpdate(invoice);

		return "alist";
	}

	// 删除一条
	public String deleteById() {
		PackingList packingList = packingListService.get(PackingList.class, model.getPackingList_id());
		// 改变装箱单的日期和发票号
		packingList.setInvoiceDate(null);
		packingList.setInvoiceNo(null);
		packingListService.saveOrUpdate(packingList);
		invoiceService.deleteById(Invoice.class, model.getId());
		return "alist";
	}

	public String delete() {
		// 多条删除
		String[] ids = model.getId().split(", ");
		for (String string : ids) {
			// 查询发票
			Invoice invoice = invoiceService.get(Invoice.class, string);
			// 查询当前发票下的委托单id
			ShippingOrder shippingOrder = shippingOrderService.get(ShippingOrder.class, invoice.getShippId());
			// 将委托单状态置为1 已上报状态
			shippingOrder.setState(1);
			shippingOrderService.saveOrUpdate(shippingOrder);
		}

		invoiceService.delete(Invoice.class, ids);

		return "alist";
	}

	// 查看
	public String toview() {
		Invoice obj = invoiceService.get(Invoice.class, model.getId());
		super.push(obj);

		return "pview"; // 转向查看页面
	}

	// 提交
	public String submit() {
		Invoice obj = invoiceService.get(Invoice.class, model.getId());
		// 将发票状态置为已提交
		obj.setState(1);
		PackingList packingList = packingListService.get(PackingList.class, obj.getPackingList_id());
		Date date = obj.getCreateTime();
		packingList.setInvoiceDate(date);
		String invoiceNo = obj.getInvoiceNo();
		packingList.setInvoiceNo(invoiceNo);
		// 将报运单状态置为5 已开发票状态
		Export export = exportService.get(Export.class, packingList.getExportIds());
		export.setState(5);

		packingListService.saveOrUpdate(packingList);
		exportService.saveOrUpdate(export);
		invoiceService.saveOrUpdate(obj);
		return "plist";
	}

	// 取消
	public String cacle() {
		Invoice obj = invoiceService.get(Invoice.class, model.getId());
		// 取消设置为草稿状态
		obj.setState(0);
		PackingList packingList = packingListService.get(PackingList.class, obj.getPackingList_id());
		packingList.setInvoiceDate(null);
		packingList.setInvoiceNo(null);
		// 将报运单状态设置为已委托状态
		Export export = exportService.get(Export.class, packingList.getExportIds());
		export.setState(4);

		packingListService.saveOrUpdate(packingList);
		exportService.saveOrUpdate(export);
		invoiceService.saveOrUpdate(obj);
		return "plist";

	}

	public String print() throws Exception {
		// 快乐是自己创造的!
		// 将选中的发票单查询出来
		model = invoiceService.get(Invoice.class, model.getId());
		//查找到委托单
		ShippingOrder obj = shippingOrderService.get(ShippingOrder.class,model.getShippId());
		//查装箱单
		PackingList packingList = packingListService.get(PackingList.class, model.getPackingList_id());
		
		//获取模板
		String path = ServletActionContext.getServletContext().getRealPath("/make/xlsprint/tINVOICE.xls");
		// 文件输入流
		InputStream in = new FileInputStream(path);
		// 获取工作簿对象
		Workbook wb = new HSSFWorkbook(in);
		// 获取工作表对象
		// wb.getSheet("sheet1");
		Sheet sheet = wb.getSheetAt(0);
		//定义行
		Row row=null;
		//定义单元格
		Cell cell=null;
		//seller
		//获取行
		row = sheet.getRow(3);
		//获取单元格
		cell=row.getCell(0);
		cell.setCellValue(obj.getShipper());
		
		//buyer
		//获取行
		row=sheet.getRow(8);
		//获取单元格
		cell=row.getCell(0);
		cell.setCellValue(obj.getConsignee());
		
		//Invoice No.
		//获取行
		row=sheet.getRow(15);
		//获取单元格
		cell=row.createCell(0);
		cell.setCellValue(model.getId());
		
		//时间转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//Date
		row=sheet.getRow(15);
		//获取单元格
		cell=row.createCell(2);
		Date createTime = model.getCreateTime();
		String createTimeStr=sdf.format(createTime);
		cell.setCellValue(createTimeStr);
		
		//scNo
		//获取行
		row=sheet.getRow(15);
		//获取单元格
		cell=row.createCell(5);
		cell.setCellValue(model.getScNo());
		
		//blNo
		//获取行
		row=sheet.getRow(15);
		//获取单元格
		cell=row.createCell(9);
		cell.setCellValue(model.getBlNo());
		
		//TradeTerms
		row=sheet.getRow(19);
		//获取单元格
		cell=row.getCell(0);
		cell.setCellValue(model.getTradeTerms());
		
		//PackingListNo
		row=sheet.getRow(19);
		//获取单元格
		cell=row.getCell(2);
		cell.setCellValue(packingList.getId());
		
		//ShippOrderNo
		row=sheet.getRow(19);
		//获取单元格
		cell=row.getCell(7);
		cell.setCellValue(obj.getId());
		

		// 创建缓存区的流
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		// 写入数据
		wb.write(os);
		// 刷新
		os.flush();
		os.close();

		// 先下载
		DownloadUtil downloadUtil = new DownloadUtil();
		downloadUtil.download(os, ServletActionContext.getResponse(), "发票单.xls");
		
	
		
		return NONE;
	}
}
