package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.service.ContractService;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;

public class ContractServiceImpl implements ContractService {
	
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Contract> find(String hql, Class<Contract> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}
	
	public Contract get(Class<Contract> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<Contract> findPage(String hql, Page<Contract> page, Class<Contract> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(Contract entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			entity.setState(0);
			entity.setTotalAmount(0d);
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Contract> entitys) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdateAll(entitys);
	}
	
	/**
	 * 采用递归删除的方式
	 */
	public void deleteById(Class<Contract> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}
	
	/**
	 * 批量删除
	 */
	public void delete(Class<Contract> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}
	
}
