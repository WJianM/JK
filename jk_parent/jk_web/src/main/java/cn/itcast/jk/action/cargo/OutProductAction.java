package cn.itcast.jk.action.cargo;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.service.ContractProductService;
import cn.itcast.jk.service.ContractService;
import cn.itcast.jk.utils.DownloadUtil;
import cn.itcast.jk.utils.POIUtils;

public class OutProductAction extends BaseAction {
	private String inputDate;

	public String getInputDate() {
		return inputDate;
	}

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	// toedit
	public String toedit() throws Exception {

		return "OutProduct_toedit";
	}

	// 注入contractService
	private ContractProductService contractProductService;

	public void setContractProductService(ContractProductService contractProductService) {
		this.contractProductService = contractProductService;
	}

	// print
	public String print1() throws Exception {
		// 创建工作簿
		Workbook wb = new HSSFWorkbook();
		// 创建工作表
		Sheet sheet = wb.createSheet();
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 8));
		// 创建行
		int rowNum = 0;
		Row row = sheet.createRow(rowNum++);
		row.setHeightInPoints(36f);
		// 创建单元格
		int cellNum = 1;
		Cell cell = row.createCell(cellNum);
		// 设置内容
		String value = inputDate.replace("-0", "-").replace("-", "年") + "月份出货表";
		cell.setCellValue(value);
		// 设置样式

		CellStyle style = POIUtils.bigTitle(wb);
		cell.setCellStyle(style);
		// ========================小标题=============================
		// 设置第二行行高
		row = sheet.createRow(rowNum++);
		row.setHeightInPoints(26.25f);
		// 设置每列的 列宽
		sheet.setColumnWidth(0, 256 * 5);
		sheet.setColumnWidth(1, 256 * 26);
		sheet.setColumnWidth(2, 256 * 11);
		sheet.setColumnWidth(3, 256 * 29);
		sheet.setColumnWidth(4, 256 * 12);
		sheet.setColumnWidth(5, 256 * 15);
		sheet.setColumnWidth(6, 256 * 10);
		sheet.setColumnWidth(7, 256 * 10);
		sheet.setColumnWidth(8, 256 * 10);
		// 设置内容
		String[] str = { "客户", "订单号", "货号", "数量", "工厂", "工厂交期", "船期", "贸易条款" };
		for (int i = 0; i < str.length; i++) {
			cell = row.createCell(cellNum++);
			cell.setCellValue(str[i]);
			// 设置样式
			cell.setCellStyle(POIUtils.title(wb));
		}
		// ========================数据=============================
		// 查询该时间段的所有购销合同
		String hql = "from ContractProduct where to_char(contract.shipTime,'yyyy-MM') = ?";
		List<ContractProduct> list = contractProductService.find(hql, ContractProduct.class,
				new Object[] { inputDate });
		// 遍历集合
		for (ContractProduct contractProduct : list) {
			cellNum = 1;
			// 设置行高
			row = sheet.createRow(rowNum++);
			row.setHeightInPoints(24f);
			// 客户
			// 创建单元格
			cell = row.createCell(cellNum++);
			// 设置内容
			cell.setCellValue(contractProduct.getContract().getCustomName());
			// 设置样式
			cell.setCellStyle(POIUtils.text(wb));

			// 订单号
			// 创建单元格
			cell = row.createCell(cellNum++);
			// 设置内容
			cell.setCellValue(contractProduct.getContract().getContractNo());
			// 设置样式
			cell.setCellStyle(POIUtils.text(wb));

			// 货号
			// 创建单元格
			cell = row.createCell(cellNum++);
			// 设置内容
			cell.setCellValue(contractProduct.getProductNo());
			// 设置样式
			cell.setCellStyle(POIUtils.text(wb));

			// 数量
			// 创建单元格
			cell = row.createCell(cellNum++);
			// 设置内容
			cell.setCellValue(contractProduct.getCnumber());
			// 设置样式
			cell.setCellStyle(POIUtils.text(wb));

			// 工厂
			// 创建单元格
			cell = row.createCell(cellNum++);
			// 设置内容
			cell.setCellValue(contractProduct.getFactoryName());
			// 设置样式
			cell.setCellStyle(POIUtils.text(wb));

			// 工厂交期
			// 创建单元格
			cell = row.createCell(cellNum++);
			// 设置内容
			cell.setCellValue(contractProduct.getContract().getDeliveryPeriod());
			// 设置样式
			cell.setCellStyle(POIUtils.text(wb));

			// 船期
			// 创建单元格
			cell = row.createCell(cellNum++);
			// 设置内容
			cell.setCellValue(contractProduct.getContract().getShipTime());
			// 设置样式
			cell.setCellStyle(POIUtils.text(wb));

			// 贸易条款
			// 创建单元格
			cell = row.createCell(cellNum++);
			// 设置内容
			cell.setCellValue(contractProduct.getContract().getTradeTerms());
			// 设置样式
			cell.setCellStyle(POIUtils.text(wb));
		}

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		// 下载
		wb.write(stream);
		stream.close();
		DownloadUtil downLoad = new DownloadUtil();
		downLoad.download(stream, ServletActionContext.getResponse(), inputDate + "出货表.xls");
		return NONE;
	}

	// 使用模板
	public String print() throws Exception {
		// 制作模板
		// 加载模板文件，获取到工作簿对象
		InputStream is = ServletActionContext.getServletContext().getResourceAsStream("/make/xlsprint/tOUTPRODUCT.xls");
		Workbook wb = new HSSFWorkbook(is);
		// 获取工作表对象;
		Sheet sheet = wb.getSheetAt(0);
		// 获取行对象
		// 定义行坐标
		int rowNum = 0;
		Row row = sheet.getRow(rowNum++);
		// 获取单元格对象
		// 定义单元格坐标
		int cellNum = 1;
		Cell cell = row.getCell(cellNum);
		// 获取单元格的内容
		String value = inputDate.replace("-0", "-").replace("-", "年") + "月份出货表";
		cell.setCellValue(value);
		// ===========================小标题 不用写===================================
		rowNum++;
		// ------------------------------第三行-------------------------
		/*
		 * row=sheet.getRow(rowNum); cell = row.getCell(cellNum);
		 * cell.setCellValue("RIVIERA MAISON");
		 */
		// 获取模板中 第三行的样式
		// 定义一个集合用于存储样式
		List<CellStyle> cellStyList = new ArrayList<>();
		// 获取第三行
		row = sheet.getRow(rowNum);
		for (int i = 0; i < 8; i++) {
			cell = row.getCell(cellNum++);
			cellStyList.add(cell.getCellStyle());
		}
		// 查询
		String hql = "from ContractProduct where to_char(contract.shipTime,'yyyy-MM') = ?";
		List<ContractProduct> list = contractProductService.find(hql, ContractProduct.class,
				new Object[] { inputDate });
		for (ContractProduct contractProduct : list) {
			cellNum=1;
           //创建行
			row=sheet.createRow(rowNum++);
			row.setHeightInPoints(24f);
			// 客户
			// 创建单元格
			cell = row.createCell(cellNum++);
			// 设置内容
			cell.setCellValue(contractProduct.getContract().getCustomName());
			// 设置样式
			cell.setCellStyle(cellStyList.get(0));

			// 订单号
			// 创建单元格
			cell = row.createCell(cellNum++);
			// 设置内容
			cell.setCellValue(contractProduct.getContract().getContractNo());
			// 设置样式
			cell.setCellStyle(cellStyList.get(1));

			// 货号
			// 创建单元格
			cell = row.createCell(cellNum++);
			// 设置内容
			cell.setCellValue(contractProduct.getProductNo());
			// 设置样式
			cell.setCellStyle(cellStyList.get(2));

			// 数量
			// 创建单元格
			cell = row.createCell(cellNum++);
			// 设置内容
			cell.setCellValue(contractProduct.getCnumber());
			// 设置样式
			cell.setCellStyle(cellStyList.get(3));

			// 工厂
			// 创建单元格
			cell = row.createCell(cellNum++);
			// 设置内容
			cell.setCellValue(contractProduct.getFactoryName());
			// 设置样式
			cell.setCellStyle(cellStyList.get(4));

			// 工厂交期
			// 创建单元格
			cell = row.createCell(cellNum++);
			// 设置内容
			cell.setCellValue(contractProduct.getContract().getDeliveryPeriod());
			// 设置样式
			cell.setCellStyle(cellStyList.get(5));

			// 船期
			// 创建单元格
			cell = row.createCell(cellNum++);
			// 设置内容
			cell.setCellValue(contractProduct.getContract().getShipTime());
			// 设置样式
			cell.setCellStyle(cellStyList.get(6));

			// 贸易条款
			// 创建单元格
			cell = row.createCell(cellNum++);
			// 设置内容
			cell.setCellValue(contractProduct.getContract().getTradeTerms());
			// 设置样式
			cell.setCellStyle(cellStyList.get(7));
			
		}
		/* 保存关闭流 */
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		wb.write(stream);
		wb.close();
		/* 下载 */
		DownloadUtil downloadUtil = new DownloadUtil();
		downloadUtil.download(stream, ServletActionContext.getResponse(), value + ".xls");
		return NONE;
	}
}
