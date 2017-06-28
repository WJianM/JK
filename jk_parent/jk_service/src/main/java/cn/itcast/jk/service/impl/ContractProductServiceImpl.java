package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.domain.ExtCproduct;
import cn.itcast.jk.service.ContractProductService;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;

public class ContractProductServiceImpl implements ContractProductService {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<ContractProduct> find(String hql, Class<ContractProduct> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public ContractProduct get(Class<ContractProduct> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<ContractProduct> findPage(String hql, Page<ContractProduct> page, Class<ContractProduct> entityClass,
			Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(ContractProduct entity) {

		if (UtilFuns.isEmpty(entity.getId())) {
			if (UtilFuns.isNotEmpty(entity.getCnumber()) && UtilFuns.isNotEmpty(entity.getPrice())) {
				entity.setAmount(entity.getPrice() * entity.getCnumber());
				// 查询该货物对应的合同
				Contract contract = baseDao.get(Contract.class, entity.getContract().getId());
				contract.setTotalAmount(contract.getTotalAmount() + entity.getAmount());
				// 更新合同
				baseDao.saveOrUpdate(contract);
			}

		} else {
			if (UtilFuns.isNotEmpty(entity.getCnumber()) && UtilFuns.isNotEmpty(entity.getPrice())) {
				double money = entity.getAmount();
				entity.setAmount(entity.getPrice() * entity.getCnumber());
				// 查询该货物对应的合同
				Contract contract = baseDao.get(Contract.class, entity.getContract().getId());
				contract.setTotalAmount(contract.getTotalAmount() - money + entity.getAmount());
				// 更新合同
				baseDao.saveOrUpdate(contract);
			}

		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<ContractProduct> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	/**
	 * 采用递归删除的方式
	 */
	public void deleteById(Class<ContractProduct> entityClass, Serializable id) {
		// 查询出货物对象
		ContractProduct contractProduct = baseDao.get(ContractProduct.class, id);
		// 查询出合同对象
		Contract contract = contractProduct.getContract();
		double money = contract.getTotalAmount();
		// 获取附件
		Set<ExtCproduct> extCproducts = contractProduct.getExtCproducts();
		if (extCproducts != null && extCproducts.size() > 0) {
			for (ExtCproduct extCproduct : extCproducts) {
				money -= extCproduct.getAmount();
			}
		}
		money-=contractProduct.getAmount();
		contract.setTotalAmount(money);
		baseDao.saveOrUpdate(contract);
        baseDao.deleteById(entityClass, id);
	}

	/**
	 * 批量删除
	 */
	public void delete(Class<ContractProduct> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}

}
