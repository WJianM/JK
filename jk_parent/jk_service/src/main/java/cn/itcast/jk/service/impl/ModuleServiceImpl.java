package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Module;
import cn.itcast.jk.service.ModuleService;
import cn.itcast.jk.utils.Page;

public class ModuleServiceImpl implements ModuleService {
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Module> find(String hql, Class<Module> entityClass, Object[] params) {

		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Module get(Class<Module> entityClass, Serializable id) {

		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Module> findPage(String hql, Page<Module> page, Class<Module> entityClass, Object[] params) {

		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(Module entity) {
		baseDao.saveOrUpdate(entity);

	}

	@Override
	public void saveOrUpdateAll(Collection<Module> entitys) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Class<Module> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);

	}

	@Override
	public void delete(Class<Module> entityClass, Serializable[] ids) {
		for (int i = 0; i < ids.length; i++) {
			deleteById(entityClass, ids[i]);
		}

	}

	@Override
	public void deleteDept(Module d) {
		// TODO Auto-generated method stub

	}

}
