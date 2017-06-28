package cn.itcast.jk.action.cargo;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.domain.PackingList;
import cn.itcast.jk.service.ExportService;
import cn.itcast.jk.service.PackingListService;
import cn.itcast.jk.utils.DownloadUtil;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;

/**
 * @Description: PackingListService接口
 * @Author: rent
 * @Company: http://java.itcast.cn
 * @CreateDate: 2017-6-8 18:26:49
 */

public class PackingListAction extends BaseAction implements ModelDriven<PackingList> {
	// 注入service
	private PackingListService packingListService;

	public void setPackingListService(PackingListService packingListService) {
		this.packingListService = packingListService;
	}

	// 注入exportservice
	private ExportService exportService;

	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}

	// model驱动
	private PackingList model = new PackingList();

	public PackingList getModel() {
		return this.model;
	}

	// 作为属性驱动，接收并封装页面参数
	private Page page = new Page(); // 封装页面的参数，主要当前页参数
	private Date invoiceDate;
	private String[] split;

	public void setPage(Page page) {
		this.page = page;
	}

	public Page getPage() {
		return page;
	}

	// 列表展示
	public String list() {
		String hql = "from PackingList o"; // 查询所有内容
		// 给页面提供分页数据
		page.setUrl("packingListAction_list"); // 配置分页按钮的转向链接
		page = packingListService.findPage(hql, page, PackingList.class, null);

		return "plist"; // page list
	}

	// 转向新增页面
	public String tocreate() {
		// 查询已经审批的海关报运单
		page = exportService.findPage("from Export where state=2", page, Export.class, null);
		page.setUrl("packingListAction_tocreate");
		push(page);
		return "pcreate";
	}

	// 新增保存
	public String insert() {
		// 查询出选中选中报运单id
		String[] exportIds = model.getExportIds().split(", ");
		for (String exportId : exportIds) {
			Export export = exportService.get(Export.class, exportId);
			export.setState(3);
			exportService.saveOrUpdate(export);
		}
		model.setState(0);
		packingListService.saveOrUpdate(model);
		return "alist"; // 返回列表，重定向action_list
	}

	// 转向修改页面
	public String toupdate() {
		PackingList obj = packingListService.get(PackingList.class, model.getId());
		super.push(obj);
		// 将报运单id存进值栈
		List<String> exportList = new ArrayList<>();
		String[] exportIds = obj.getExportIds().split(", ");
		for (int i = 0; i < exportIds.length; i++) {
			exportList.add(exportIds[i]);
			Export export = exportService.get(Export.class, exportIds[i]);
			export.setState(2);
			exportService.saveOrUpdate(export);
		}
		
		page = exportService.findPage("from Export where state=2", page, Export.class, null);
		page.setUrl("packingListAction_tocreate");
		push(page);

		// 存进域中
		ServletActionContext.getRequest().setAttribute("exportList", exportList);

		return "pupdate";
	}

	// 修改保存
	public String update() {
		PackingList packingList = packingListService.get(PackingList.class, model.getId());

		// 设置修改的属性，根据业务去掉自动生成多余的属性
		/* packingList.setPackingListId(model.getPackingListId()); */
		packingList.setSeller(model.getSeller());
		packingList.setBuyer(model.getBuyer());
		packingList.setInvoiceNo(model.getInvoiceNo());
		packingList.setInvoiceDate(model.getInvoiceDate());
		packingList.setMarks(model.getMarks());
		packingList.setDescriptions(model.getDescriptions());
		packingList.setExportIds(model.getExportIds());
		packingList.setExportNos(model.getExportNos());

		String[] exportIds = model.getExportIds().split(", ");
		for (String exportId : exportIds) {
			Export export = exportService.get(Export.class, exportId);
			export.setState(3);
			exportService.saveOrUpdate(export);
		}
		packingList.setState(0);
		packingListService.saveOrUpdate(packingList);

		return "alist";
	}

	// 删除一条
	public String deleteById() {
		packingListService.deleteById(PackingList.class, model.getId());
		// 查询出该装箱单内的报运单
		PackingList packingList = packingListService.get(PackingList.class, model.getId());
		String[] split = packingList.getExportIds().split(", ");
		for (String string : split) {
			Export export = exportService.get(Export.class, string);
			export.setState(2);
			exportService.saveOrUpdate(export);
		}
		return "alist";
	}

	// 删除多条
	public String delete() {

		// 查询报运单
		String[] modelIds = model.getId().split(", ");
		for (String string : modelIds) {
			PackingList packingList = packingListService.get(PackingList.class, string);
			String[] split = packingList.getExportIds().split(", ");
			for (String str : split) {
				Export export = exportService.get(Export.class, str);
				export.setState(2);
				exportService.saveOrUpdate(export);
			}
		}
		packingListService.delete(PackingList.class, model.getId().split(", "));

		return "alist";
	}

	// 查看
	public String toview() {
		PackingList obj = packingListService.get(PackingList.class, model.getId());
		super.push(obj);
		String[] ids = obj.getExportIds().split(", ");
		List<Export> list = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			Export export = exportService.get(Export.class, ids[i]);
			list.add(export);
		}
		// 压栈
		ServletActionContext.getServletContext().setAttribute("exportList", list);

		return "pview"; // 转向查看页面
	}

	// packingListAction_submit 上报
	public String submit() throws Exception {
		PackingList obj = packingListService.get(PackingList.class, model.getId());
		obj.setState(1);
		packingListService.saveOrUpdate(obj);
		return "alist";
	}

	// 取消状态
	public String cancel() throws Exception {
		PackingList obj = packingListService.get(PackingList.class, model.getId());
		/*
		 * String[] exportIds = obj.getExportIds().split(", "); for (String
		 * exportId : exportIds) { Export export =
		 * exportService.get(Export.class, exportId); export.setState(2);
		 * exportService.saveOrUpdate(export); }
		 */
		obj.setState(0);
		packingListService.saveOrUpdate(obj);
		return "alist";
	}

	// 打印
	public String print() throws Exception {
		// 查询出装箱单对象
		PackingList obj = packingListService.get(PackingList.class, model.getId());
		// 查询报运单
		String[] ids = obj.getExportIds().split(", ");
		List<Export> list = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			Export export = exportService.get(Export.class, ids[i]);
			list.add(export);
		}
		String path = ServletActionContext.getServletContext().getRealPath("/make/xlsprint/tPARKINGLIST.xls");
		InputStream in = new FileInputStream(path);
		// 获取工作簿对象
		Workbook wb = new HSSFWorkbook(in);
		// 获取工作表
		Sheet sheet = wb.getSheetAt(0);
		Row row = null;
		// 定义单元格
		// 卖方
		Cell cell = null;
		row = sheet.getRow(3);
		cell = row.getCell(0);
		cell.setCellValue(obj.getSeller());
		// 买房
		row = sheet.getRow(8);
		cell = row.createCell(0);
		cell.setCellValue(obj.getBuyer());
		// 发票号
		row = sheet.getRow(15);
		cell = row.createCell(0);
		if (obj.getInvoiceNo() != null) {
			cell.setCellValue(obj.getInvoiceNo());
		}

		// 发票日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		row = sheet.getRow(15);
		cell = row.getCell(3);
		if (obj.getInvoiceDate() != null) {
			Date invoiceDate = obj.getInvoiceDate();
			String invoiceDateStr = sdf.format(invoiceDate);
			cell.setCellValue(invoiceDateStr);
		}
		//唛头
		row=sheet.getRow(19);
		cell=row.getCell(0);
		cell.setCellValue(obj.getMarks());
		//描述
		cell=row.getCell(2);
		cell.setCellValue(obj.getDescriptions());
		//报运单单号
		cell=row.getCell(5);
		String[] expotIds = obj.getExportIds().split(", ");
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < expotIds.length; i++) {
			sb.append(expotIds[i]+"\r\n");
		}
		cell.setCellValue(sb.toString());
		

		// 创建缓存区的流
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		// 写入数据
		wb.write(os);
		// 刷新
		os.flush();
		os.close();

		// 先下载
		DownloadUtil downloadUtil = new DownloadUtil();
		downloadUtil.download(os, ServletActionContext.getResponse(), "装箱单.xls");

		return NONE;
	}

}
