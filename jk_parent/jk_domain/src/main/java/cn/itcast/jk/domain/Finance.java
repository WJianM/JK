package cn.itcast.jk.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Description: FinanceService接口
 * @Author: rent
 * @Company: http://java.itcast.cn
 * @CreateDate: 2017-6-9 21:10:30
 */

public class Finance extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 财务单id
	private String id;
	// 装箱的Id
	private String PackingList_id;
	// 创建时间
	private Date inputDate;
	// 创建人
	private String inputBy;
	// 状态
	private int state;
	// 发票的id
	private String invoiceId;

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

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

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public String getInputBy() {
		return inputBy;
	}

	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
