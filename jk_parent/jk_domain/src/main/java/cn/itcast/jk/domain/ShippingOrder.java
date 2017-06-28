package cn.itcast.jk.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Description: ShippingOrderService接口
 * @Author: rent
 * @Company: http://java.itcast.cn
 * @CreateDate: 2017-6-9 20:37:58
 */

/**
 * @author Administrator
 *
 */
public class ShippingOrder extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;
	private String orderType;
	private String shipper;//货主
	private String consignee;//提单人
	private String notifyParty;//通知人
	private String lcNo;
	private String portOfLoading;
	private String portOfTrans;
	private String portOfDischarge;
	private Date loadingDate;
	private Date limitDate;
	private String isBatch;
	private String isTrans;
	private String copyNum;
	private String remark;
	private String specialCondition;
	private String freight;
	private String checkBy;
	private Integer state;
	//发票单号
	private String InvoiceNo;
	public String getInvoiceNo() {
		return InvoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		InvoiceNo = invoiceNo;
	}
	// 打断装箱
	private String packId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getShipper() {
		return shipper;
	}
	public void setShipper(String shipper) {
		this.shipper = shipper;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getNotifyParty() {
		return notifyParty;
	}
	public void setNotifyParty(String notifyParty) {
		this.notifyParty = notifyParty;
	}
	public String getLcNo() {
		return lcNo;
	}
	public void setLcNo(String lcNo) {
		this.lcNo = lcNo;
	}
	public String getPortOfLoading() {
		return portOfLoading;
	}
	public void setPortOfLoading(String portOfLoading) {
		this.portOfLoading = portOfLoading;
	}
	public String getPortOfTrans() {
		return portOfTrans;
	}
	public void setPortOfTrans(String portOfTrans) {
		this.portOfTrans = portOfTrans;
	}
	public String getPortOfDischarge() {
		return portOfDischarge;
	}
	public void setPortOfDischarge(String portOfDischarge) {
		this.portOfDischarge = portOfDischarge;
	}
	public Date getLoadingDate() {
		return loadingDate;
	}
	public void setLoadingDate(Date loadingDate) {
		this.loadingDate = loadingDate;
	}
	public Date getLimitDate() {
		return limitDate;
	}
	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
	public String getIsBatch() {
		return isBatch;
	}
	public void setIsBatch(String isBatch) {
		this.isBatch = isBatch;
	}
	public String getIsTrans() {
		return isTrans;
	}
	public void setIsTrans(String isTrans) {
		this.isTrans = isTrans;
	}
	public String getCopyNum() {
		return copyNum;
	}
	public void setCopyNum(String copyNum) {
		this.copyNum = copyNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSpecialCondition() {
		return specialCondition;
	}
	public void setSpecialCondition(String specialCondition) {
		this.specialCondition = specialCondition;
	}
	public String getFreight() {
		return freight;
	}
	public void setFreight(String freight) {
		this.freight = freight;
	}
	public String getCheckBy() {
		return checkBy;
	}
	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getPackId() {
		return packId;
	}
	public void setPackId(String packId) {
		this.packId = packId;
	}

	
	

}
