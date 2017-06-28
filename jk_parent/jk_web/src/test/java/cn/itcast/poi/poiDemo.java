package cn.itcast.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class poiDemo {
	@Test
	public void run1() throws Exception {
		// 创建一个工作簿workbook
		Workbook wb = new HSSFWorkbook();
		// 创建一个工作表sheet
		Sheet sheet = wb.createSheet();
		// 创建一个行row（下标起始位置为0）
		Row row = sheet.createRow(0);
		// 创建一个单元格cell（下标起始位置为0）
		Cell cell = row.createCell(0);
		// 给单元格设置内容
		cell.setCellValue("你好,屈永旺");
		// 给单元格设置样式（例如：设置单元格的字体和大小） 保存，关闭流对象 下载
        CellStyle cellStyle = wb.createCellStyle();
        OutputStream stream=new FileOutputStream("C:\\Users\\qyw2\\Desktop\\a.xls");
		wb.write(stream);
		stream.close();
	}
}
