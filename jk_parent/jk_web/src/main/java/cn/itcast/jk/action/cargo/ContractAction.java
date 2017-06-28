package cn.itcast.jk.action.cargo;

import java.io.ByteArrayOutputStream;
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
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.domain.User;
import cn.itcast.jk.service.ContractProductService;
import cn.itcast.jk.service.ContractService;
import cn.itcast.jk.utils.DownloadUtil;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.SysConstant;
import cn.itcast.jk.utils.UtilFuns;

public class ContractAction extends BaseAction implements ModelDriven<Contract> {
	private Contract model = new Contract();

	public Contract getModel() {
		return model;
	}

	// 注入service
	private ContractService contractService;

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	// 定义page
	private Page page = new Page<>();

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	// 注入货物
	private ContractProductService contractProductService;

	public void setContractProductService(ContractProductService contractProductService) {
		this.contractProductService = contractProductService;
	}

	
	
	
	/**
	 * 在list中添加模糊查询
	 * @return
	 * @throws Exception
	 */
	private String cname;
	private String ctradeTerms;
	private Date cshipTime;
	
	public String getCtradeTerms() {
		return ctradeTerms;
	}

	public void setCtradeTerms(String ctradeTerms) {
		this.ctradeTerms = ctradeTerms;
	}

	public Date getCshipTime() {
		return cshipTime;
	}

	public void setCshipTime(Date cshipTime) {
		this.cshipTime = cshipTime;
	}


	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	// 购销合同分页查询
	public String list() throws Exception {
		// 查询所登录用户的级别
		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
		Integer degree = user.getUserInfo().getDegree();
		String hql = "from Contract where 1=1 ";
		List<Object> list = new ArrayList<>();
		if (degree == 4) {
			hql += " and createBy = ? ";
			list.add(user.getId());
		} else if (degree == 3) {
			hql += " and createDept=? ";
			list.add(user.getDept().getId());

		} else if (degree == 2) {

		} else if (degree == 1) {

		} else if (degree == 0) {

		}
		if (!UtilFuns.isEmpty(cname)) {
			hql += " and customName like ?";
			list.add("%"+cname+"%");
		}
		if (!UtilFuns.isEmpty(ctradeTerms)) {
			hql += " and tradeTerms = ?";
			list.add(ctradeTerms);
		}
		if (cshipTime!=null) {
			hql += " and shipTime < ? ";
			list.add(cshipTime);
		}
		page = contractService.findPage(hql, page, Contract.class, list.toArray());
		page.setUrl("contractAction_list");
		// 进行压栈
		super.push(cname);
		super.push(ctradeTerms);
		super.push(cshipTime);
		push(page);
		return "contract_list";
	}

	// 查看合同 contractAction_toview
	public String toview() throws Exception {
		// 查询合同
		model = contractService.get(Contract.class, model.getId());
		// 查询货物
		Set<ContractProduct> contractProducts = model.getContractProducts();
		// 压栈
		/* push(contract); */
		super.push(model);
		put("contractProducts", contractProducts);
		return "contract_toview";
	}

	// 到新增页面 contractAction_tocreate
	public String tocreate() throws Exception {

		return "contract_tocreate";
	}

	// 插入合同 contractAction_insert
	public String insert() throws Exception {
		// 插入合同时 插入部门 插入用户id
		// 获取session
		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
		model.setCreateBy(user.getId());
		model.setCreateDept(user.getDept().getId());
		contractService.saveOrUpdate(model);
		return SUCCESS;
	}

	// contractAction_toupdate 修改
	public String toupdate() throws Exception {
		// 先查询出该购销合同 进行回显
		Contract contract = contractService.get(Contract.class, model.getId());
		// 进行压栈
		push(contract);
		return "contract_toupdate";
	}

	// 修改合同 update
	public String update() throws Exception {
		// 先获取要修改的购销合同
		Contract newcontract = contractService.get(Contract.class, model.getId());
		newcontract.setCustomName(model.getCustomName());
		newcontract.setPrintStyle(model.getPrintStyle());
		newcontract.setContractNo(model.getContractNo());
		newcontract.setOfferor(model.getOfferor());
		newcontract.setInputBy(model.getInputBy());
		newcontract.setCheckBy(model.getCheckBy());
		newcontract.setInspector(model.getInspector());
		newcontract.setSigningDate(model.getSigningDate());
		newcontract.setImportNum(model.getImportNum());
		newcontract.setShipTime(model.getShipTime());
		newcontract.setTradeTerms(model.getTradeTerms());
		newcontract.setDeliveryPeriod(model.getDeliveryPeriod());
		newcontract.setCrequest(model.getCrequest());
		newcontract.setRemark(model.getRemark());
		// 保存
		contractService.saveOrUpdate(newcontract);
		return SUCCESS;
	}

	// 删除 contractAction_delete 批量删除
	public String delete() throws Exception {
		String[] ids = model.getId().split(", ");
		contractService.delete(Contract.class, ids);
		return SUCCESS;
	}

	// contractAction_submit 提交
	public String submit() throws Exception {
		// 获得对象
		Contract contract = contractService.get(Contract.class, model.getId());
		contract.setState(1);
		contractService.saveOrUpdate(contract);
		return SUCCESS;
	}

	// contractAction_cancel 取消
	public String cancel() throws Exception {
		// 获得对象
		Contract contract = contractService.get(Contract.class, model.getId());
		contract.setState(0);
		contractService.saveOrUpdate(contract);
		return SUCCESS;
	}

	// contractAction_print 进行打印
	public String print1() throws Exception {
		// 获取合同
		model = contractService.get(Contract.class, model.getId());
		// 设置工作表的下标
		/* int sheetIndex = 0; */
		InputStream is = ServletActionContext.getServletContext().getResourceAsStream("/make/xlsprint/tCONTRACTVO.xls");
		// 获取工作簿
		Workbook wb = new HSSFWorkbook(is);
		/* for (String string : cid) { */
		// 设置行的初始值
		int rowNum = 6;
		// 得到工作表
		Sheet sheet = wb.getSheetAt(0);
		// 查询出合同对象
		Contract contract = contractService.get(Contract.class, model.getId());
		// 查询出所有对应的货物
		Set<ContractProduct> set = contract.getContractProducts();
		// 定义一个存储工厂的set集合
		Set<Factory> factorySet = new HashSet<>();
		for (ContractProduct cp : set) {
			Factory factory = cp.getFactory();
			factorySet.add(factory);

		}
		// 遍历集合
		for (Factory factory : factorySet) {
			// 得到第七行
			Row row = sheet.getRow(rowNum++);
			row.setHeightInPoints(20f);
			// 获取收购方 第四个单元格
			Cell cell = row.createCell(3);
			// 设置内容
			cell.setCellValue(contract.getOfferor());
			// 设置生产工厂 第七列
			cell = row.createCell(7);
			// 工厂?
			cell.setCellValue(factory.getFactoryName());

			// 获取8行
			row = sheet.getRow(rowNum++);
			row.setHeightInPoints(20f);
			// 获取合同号 3
			cell = row.createCell(3);
			cell.setCellValue(contract.getContractNo());
			// 获取联系人
			cell = row.createCell(7);
			cell.setCellValue(factory.getContacts());

			// 获取第9行
			row = sheet.getRow(rowNum++);
			// 获取签单日期 3
			cell = row.createCell(3);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = simpleDateFormat.format(model.getSigningDate());
			cell.setCellValue(date);
			// 电话工厂
			cell.setCellValue(factory.getPhone());
			rowNum++;
			// 遍历该工厂内的货物
			String hql = "from ContractProduct where contract.id=? and factory.id=?";
			List<ContractProduct> productList = contractProductService.find(hql, ContractProduct.class,
					new Object[] { model.getId(), factory.getId() });
			// 定义存储工厂金额的值
			double money = 0;
			for (ContractProduct contractProduct : productList) {
				// 获取11行
				row = sheet.getRow(rowNum++);
				row.setHeightInPoints(96f);
				// 产品图片 1
				cell = row.createCell(1);
				cell.setCellValue(contractProduct.getProductImage());

				// 获取4 单元格
				cell = row.createCell(4);
				cell.setCellValue(contractProduct.getProductDesc());

				// 获取 5 单元格
				cell = row.createCell(5);
				cell.setCellValue(contractProduct.getCnumber());
				// 获取6 packingUnit
				cell = row.createCell(6);
				cell.setCellValue(contractProduct.getPackingUnit());
				// 获取7 单价
				cell = row.createCell(7);
				cell.setCellValue(contractProduct.getPrice());
				// 获取8 总金额
				cell = row.createCell(8);
				cell.setCellValue(contractProduct.getAmount());
				// 创建12行
				row = sheet.getRow(rowNum++);
				cell = row.createCell(1);
				cell.setCellValue(contractProduct.getOrderNo());
				money += contractProduct.getAmount();

			}
			row = sheet.getRow(rowNum++);
			// 签单人
			cell = row.createCell(2);
			cell.setCellValue(contract.getInputBy());
			// 验货员:
			cell = row.createCell(5);
			cell.setCellValue(contract.getInspector());
			// 获取8 总金额
			cell = row.createCell(8);
			cell.setCellValue(money);

			// 下载
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			wb.write(stream);
			stream.close();
			DownloadUtil downLoad = new DownloadUtil();
			downLoad.download(stream, ServletActionContext.getResponse(), "合同.xls");
			/* } */
		}

		return NONE;
	}
	//打印
	public String print() throws Exception {
		//获取id
		Contract contract = contractService.get(Contract.class,model.getId());
		String realPath = ServletActionContext.getServletContext().getRealPath("/");
		//查询所有的货物
		List<ContractProduct> list = contractProductService.find("from ContractProduct where contract.id=? order by factoryName desc", ContractProduct.class, new Object[]{contract.getId()});
		ContractPrint print = new ContractPrint();
		print.print(contract, list, realPath, ServletActionContext.getResponse());
		return NONE;
	}

}
