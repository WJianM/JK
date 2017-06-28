package cn.itcast.jk.domain;

import java.util.Date;

/**
 * @Description:	InvoiceService接口
 * @Author:			rent
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2017-6-9 20:43:05
 */

public class Invoice extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private static final int INVOICENUM =1;
	private String id;	  			//发票的Id
	private String PackingList_id;	//装箱的iD
	private String scNo;			//报运的合同号
	private String blNo;			//提单号
	private String InvoiceNo;       //发票号
	private String tradeTerms;		//贸易条款
	private Integer state;			//状态
	private String createDept;		//创建部门
	
	private String createBy;		//创建人
	private Date createTime;		//创建时间
	private String shippId;			//委托的id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPackingList_id() {
		return PackingList_id;
	}
	public void setPackingList_id(String packingList_id) {
		PackingList_id = packingList_id;
	}
	public String getScNo() {
		return scNo;
	}
	public void setScNo(String scNo) {
		this.scNo = scNo;
	}
	public String getBlNo() {
		return blNo;
	}
	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}
	public String getInvoiceNo() {
		return InvoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		InvoiceNo = invoiceNo;
	}
	public String getTradeTerms() {
		return tradeTerms;
	}
	public void setTradeTerms(String tradeTerms) {
		this.tradeTerms = tradeTerms;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCreateDept() {
		return createDept;
	}
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getShippId() {
		return shippId;
	}
	public void setShippId(String shippId) {
		this.shippId = shippId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static int getInvoicenum() {
		return INVOICENUM;
	}
	
	
	
}
