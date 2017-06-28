package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Invoice;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.service.InvoiceService;
import cn.itcast.jk.utils.UtilFuns;

/**
 * @Description:	InvoiceService接口
 * @Author:			rent
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2017-6-9 20:43:05
 */

public class InvoiceServiceImpl implements InvoiceService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Invoice> find(String hql, Class<Invoice> entityClass, Object[] params) {
		return baseDao.find(hql, Invoice.class, params);
	}

	public Invoice get(Class<Invoice> entityClass, Serializable id) {
		return baseDao.get(Invoice.class, id);
	}

	public Page<Invoice> findPage(String hql, Page<Invoice> page, Class<Invoice> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, Invoice.class, params);
	}

	public void saveOrUpdate(Invoice entity) {
			//代表新增
			Invoice invoice = baseDao.get(entity.getClass(), entity.getId());
			invoice.setBlNo(entity.getBlNo());
			invoice.setScNo(entity.getScNo());
			invoice.setTradeTerms(entity.getTradeTerms());
			invoice.setCreateBy(entity.getCreateBy());
			invoice.setCreateDept(entity.getCreateDept());
			invoice.setCreateTime(entity.getCreateTime());
		
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<Invoice> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Invoice> entityClass, Serializable id) {
		baseDao.deleteById(Invoice.class, id);
	}

	public void delete(Class<Invoice> entityClass, Serializable[] ids) {
		baseDao.delete(Invoice.class, ids);
	}

}

