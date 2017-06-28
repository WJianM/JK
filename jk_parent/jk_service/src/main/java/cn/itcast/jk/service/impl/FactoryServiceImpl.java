package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.service.FactoryService;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;

public class FactoryServiceImpl implements FactoryService {
	
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Factory> find(String hql, Class<Factory> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}
	
	public Factory get(Class<Factory> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<Factory> findPage(String hql, Page<Factory> page, Class<Factory> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(Factory entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Factory> entitys) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdateAll(entitys);
	}
	
	/**
	 * 采用递归删除的方式
	 */
	public void deleteById(Class<Factory> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}
	
	/**
	 * 批量删除
	 */
	public void delete(Class<Factory> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}
	
}
