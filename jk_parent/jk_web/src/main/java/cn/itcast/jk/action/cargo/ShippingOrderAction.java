package cn.itcast.jk.action.cargo;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.domain.PackingList;
import cn.itcast.jk.domain.ShippingOrder;
import cn.itcast.jk.utils.DownloadUtil;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.service.ExportService;
import cn.itcast.jk.service.PackingListService;
import cn.itcast.jk.service.ShippingOrderService;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @Description: ShippingOrderService接口
 * @Author: rent
 * @Company: http://java.itcast.cn
 * @CreateDate: 2017-6-9 20:37:58
 */

public class ShippingOrderAction extends BaseAction implements ModelDriven<ShippingOrder> {
	// 注入出口报运单的service
	private ExportService exportService;

	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}

	// 注入装箱service
	private PackingListService packingListService;

	public void setPackingListService(PackingListService packingListService) {
		this.packingListService = packingListService;
	}

	public void setModel(ShippingOrder model) {
		this.model = model;
	}

	// 注入service
	private ShippingOrderService shippingOrderService;

	public void setShippingOrderService(ShippingOrderService shippingOrderService) {
		this.shippingOrderService = shippingOrderService;
	}

	// model驱动
	private ShippingOrder model = new ShippingOrder();

	public ShippingOrder getModel() {
		return this.model;
	}

	// 作为属性驱动，接收并封装页面参数
	private Page page = new Page(); // 封装页面的参数，主要当前页参数

	public void setPage(Page page) {
		this.page = page;
	}

	// 列表展示
	public String list() {
		String hql = "from ShippingOrder o"; // 查询所有内容
		// 给页面提供分页数据
		page.setUrl("shippingOrderAction_list"); // 配置分页按钮的转向链接
		page = shippingOrderService.findPage(hql, page, ShippingOrder.class, null);
		super.push(page);
		return "plist"; // page list
	}

	// 转向新增页面
	public String tocreate() {
		// 准备数据
		// 查询所有的装箱数据
		page = packingListService.findPage("from PackingList where state=1", page, PackingList.class, null);
		// 压入值栈
		page.setUrl("shippingOrderAction_tocreate");
		super.push(page);
		return "pcreate";
	}

	// 新增保存
	public String insert() {
		// 查询出选中的装箱单
		String packId = model.getPackId();
		PackingList packingList = packingListService.get(PackingList.class, packId);

		// 保存后将装箱的状态改为2
		packingList.setState(2);
		packingListService.saveOrUpdate(packingList);
		// 默认草稿状态
		model.setState(0);
		shippingOrderService.saveOrUpdate(model);
		return "alist"; // 返回列表，重定向action_list
	}

	// 转向修改页面
	public String toupdate() {
		ShippingOrder obj = shippingOrderService.get(ShippingOrder.class, model.getId());
		String packId = obj.getPackId();
		PackingList packingList = packingListService.get(PackingList.class, packId);
		//将报运单状态修改为1 报运状态
		packingList.setState(1);
		packingListService.saveOrUpdate(packingList);
		// 准备数据
		page = packingListService.findPage("from PackingList where state=1", page, PackingList.class, null); // 页面就可以访问shippingOrderList
		// 压入值栈
		page.setUrl("shippingOrderAction_tocreate");
		super.push(page);
		// 准备修改的数据

		super.push(obj);
		return "pupdate";
	}

	// 修改保存
	public String update() {
		ShippingOrder shippingOrder = shippingOrderService.get(ShippingOrder.class, model.getId());

		// 设置修改的属性，根据业务去掉自动生成多余的属性
		shippingOrder.setId(model.getId());
		shippingOrder.setOrderType(model.getOrderType());
		shippingOrder.setShipper(model.getShipper());
		shippingOrder.setConsignee(model.getConsignee());
		shippingOrder.setNotifyParty(model.getNotifyParty());
		shippingOrder.setLcNo(model.getLcNo());
		shippingOrder.setPortOfLoading(model.getPortOfLoading());
		shippingOrder.setPortOfTrans(model.getPortOfTrans());
		shippingOrder.setPortOfDischarge(model.getPortOfDischarge());
		shippingOrder.setLoadingDate(model.getLoadingDate());
		shippingOrder.setLimitDate(model.getLimitDate());
		shippingOrder.setIsBatch(model.getIsBatch());
		shippingOrder.setIsTrans(model.getIsTrans());
		shippingOrder.setCopyNum(model.getCopyNum());
		shippingOrder.setRemark(model.getRemark());
		shippingOrder.setSpecialCondition(model.getSpecialCondition());
		shippingOrder.setFreight(model.getFreight());
		shippingOrder.setCheckBy(model.getCheckBy());
		shippingOrder.setPackId(model.getPackId());
		/* shippingOrder.setState(model.getState()); */
		/*
		 * shippingOrder.setCreateBy(model.getCreateBy());
		 * shippingOrder.setCreateDept(model.getCreateDept());
		 * shippingOrder.setCreateTime(model.getCreateTime());
		 */
		String packId = shippingOrder.getPackId();
		PackingList packingList = packingListService.get(PackingList.class, packId);
		packingList.setState(2);
		packingListService.saveOrUpdate(packingList);
		shippingOrderService.saveOrUpdate(shippingOrder);

		return "alist";
	}

	// 删除一条
	public String deleteById() {
		shippingOrderService.deleteById(ShippingOrder.class, model.getId());

		return "alist";
	}

	// 删除多条
	public String delete() {
		String[] modelIds = model.getId().split(", ");
		for (String string : modelIds) {
			ShippingOrder shippingOrder = shippingOrderService.get(ShippingOrder.class, string);
			// 查询装箱单
			String packId = shippingOrder.getPackId();

			// 查询装箱单 修改状态
			PackingList packingList = packingListService.get(PackingList.class, packId);
			packingList.setState(1);
			packingListService.saveOrUpdate(packingList);

		}

		shippingOrderService.delete(ShippingOrder.class, model.getId().split(", "));
		// 查询委托单

		return "alist";
	}

	// 查看
	public String toview() {
		ShippingOrder obj = shippingOrderService.get(ShippingOrder.class, model.getId());
		super.push(obj);

		return "pview"; // 转向查看页面
	}

	// 提交
	public String commit() {
		// 改变委托单的状态
		ShippingOrder obj = shippingOrderService.get(ShippingOrder.class, model.getId());
		obj.setState(1);
		String packId = obj.getPackId();
		PackingList packingList = packingListService.get(PackingList.class, packId);
		// 改变出口货物状态
		String exportIds = packingList.getExportIds();
		Export export = exportService.get(Export.class, exportIds);
		export.setState(4);
		exportService.saveOrUpdate(export);
		shippingOrderService.saveOrUpdate(obj);
		return "alist";
	}

	// 取消
	public String cancle() {
		// 改变委托单的状态
		ShippingOrder obj = shippingOrderService.get(ShippingOrder.class, model.getId());
		obj.setState(0);
		String packId = obj.getPackId();
		PackingList packingList = packingListService.get(PackingList.class, packId);
		// 改变装箱单状态
		String exportIds = packingList.getExportIds();
		Export export = exportService.get(Export.class, exportIds);
		export.setState(3);
		exportService.saveOrUpdate(export);
		shippingOrderService.saveOrUpdate(obj);
		return "alist";
	}

	// 打印
	public void print() throws Exception {
		// 将选中的委托单查询出来
		model = shippingOrderService.get(ShippingOrder.class, model.getId());
		// 快乐是自己创造的!
		// 获取目标文件的路径
		String path = ServletActionContext.getServletContext().getRealPath("/make/xlsprint/tSHIPPINGORDER.xls");
		// 文件输入流
		InputStream in = new FileInputStream(path);
		// 获取工作簿对象
		Workbook wb = new HSSFWorkbook(in);
		// 获取工作表对象
		// wb.getSheet("sheet1");
		Sheet sheet = wb.getSheetAt(0);
		// 定义行
		Row row = null;
		// 定义单元格
		Cell cell = null;
		// 定义行下标
		// 定义行
		int rowNo = 0;
		// 定义列
		int cellNo = 0;
		// 获取第一行对象
		// 直接去第四行
		rowNo = rowNo + 3;
		row = sheet.getRow(rowNo);
		cell = row.getCell(0);
		// 填入货主
		cell.setCellValue(model.getShipper());
		// 填入提单抬头（坐标锁定在8,0）
		row = sheet.getRow(rowNo + 5);
		cell = row.getCell(0);
		cell.setCellValue(model.getConsignee());
		// 通知人
		row = sheet.getRow(15);
		cell = row.getCell(0);
		cell.setCellValue(model.getNotifyParty());
		// 发票号
		// 获取单元格赋值发票号（如果为空，去找发票，说明他没给赋值）
		row = sheet.getRow(19);
		cell = row.getCell(0);
		cell.setCellValue("发票号");
		// 时间date(未设置，一会就用保存时的时间就可以了，放在service的逻辑中)
		cell = row.getCell(3);
		Date createTime = model.getCreateTime();
		if (createTime != null) {
			cell.setCellValue(createTime);
		}
		// 信用证
		cell = row.getCell(6);
		cell.setCellValue(model.getLcNo());
		// 装船港
		row = sheet.getRow(23);
		cell = row.getCell(0);
		cell.setCellValue(model.getPortOfLoading());
		// 转船港
		cell = row.getCell(3);
		cell.setCellValue(model.getPortOfTrans());
		// 卸货港
		cell = row.getCell(6);
		cell.setCellValue(model.getPortOfDischarge());
		// 装期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		row = sheet.getRow(27);
		cell = row.getCell(0);
		Date loadingDate = model.getLoadingDate();
		String loadingDateStr = sdf.format(loadingDate);
		cell.setCellValue(loadingDateStr);
		// 有效期
		cell = row.createCell(2);
		Date limitDate = model.getLimitDate();
		String limitDateStr = sdf.format(limitDate);
		cell.setCellValue(limitDateStr);

		// 分批
		cell = row.getCell(3);
		String isBatch = model.getIsBatch();
		if ("1".equals(isBatch)) {
			cell.setCellValue("是");
		} else {
			cell.setCellValue("否");
		}

		// 转船
		cell = row.getCell(5);
		String isTrans = model.getIsTrans();
		if ("1".equals(isTrans)) {
			cell.setCellValue("是");
		} else {
			cell.setCellValue("否");
		}
		// 份数
		cell = row.createCell(7);
		cell.setCellValue(model.getCopyNum());
		// 运输要求
		row = sheet.getRow(31);
		cell = row.getCell(2);
		cell.setCellValue(model.getSpecialCondition());

		// 创建缓存区的流
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		// 写入数据
		wb.write(os);
		// 刷新
		os.flush();
		os.close();
		// 先下载
		DownloadUtil downloadUtil = new DownloadUtil();
		downloadUtil.download(os, ServletActionContext.getResponse(), "委托表.xls");

	}

}
