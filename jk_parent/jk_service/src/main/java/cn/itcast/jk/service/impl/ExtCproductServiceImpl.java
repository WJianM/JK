package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.domain.ExtCproduct;
import cn.itcast.jk.service.ExtCproductService;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;

public class ExtCproductServiceImpl implements ExtCproductService {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<ExtCproduct> find(String hql, Class<ExtCproduct> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public ExtCproduct get(Class<ExtCproduct> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<ExtCproduct> findPage(String hql, Page<ExtCproduct> page, Class<ExtCproduct> entityClass,
			Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(ExtCproduct entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			if (UtilFuns.isNotEmpty(entity.getCnumber()) && UtilFuns.isNotEmpty(entity.getPrice())) {
				// 查询合同
				Contract contract = baseDao.get(Contract.class, entity.getContractProduct().getContract().getId());
				double money = contract.getTotalAmount() + entity.getCnumber() * entity.getPrice();
				contract.setTotalAmount(money);
				// 更新购销合同
				baseDao.saveOrUpdate(contract);

			}
		} else {
			if (UtilFuns.isNotEmpty(entity.getCnumber()) && UtilFuns.isNotEmpty(entity.getPrice())) {
				// 查询合同
				Contract contract = baseDao.get(Contract.class, entity.getContractProduct().getContract().getId());
				double oldmoney = contract.getTotalAmount();
				// 查询附件
				double Extmoney = baseDao.get(ExtCproduct.class, entity.getId()).getAmount();
				contract.setTotalAmount(oldmoney - Extmoney + entity.getCnumber() * entity.getPrice());
				// 更新购销合同
				baseDao.saveOrUpdate(contract);

			}

		}
		entity.setAmount(entity.getCnumber() * entity.getPrice());
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<ExtCproduct> entitys) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdateAll(entitys);
	}

	/**
	 * 
	 */
	public void deleteById(Class<ExtCproduct> entityClass, Serializable id) {
		//查询出附件
		ExtCproduct extCproduct = baseDao.get(ExtCproduct.class, id);
		ContractProduct contractProduct = extCproduct.getContractProduct();
		//查询合同 
		Contract contract =baseDao.get(Contract.class, contractProduct.getContract().getId());
		contract.setTotalAmount(contract.getTotalAmount()-extCproduct.getAmount());
		//更新合同
		baseDao.saveOrUpdate(contract);
		baseDao.deleteById(entityClass, id);
	}

	/**
	 * 批量删除
	 */
	public void delete(Class<ExtCproduct> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}

}
