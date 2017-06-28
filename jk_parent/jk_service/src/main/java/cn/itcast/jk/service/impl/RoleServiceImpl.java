package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Role;
import cn.itcast.jk.service.RoleService;
import cn.itcast.jk.utils.Page;

public class RoleServiceImpl implements RoleService {
	// 注入baseDao
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Role> find(String hql, Class<Role> entityClass, Object[] params) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Role get(Class<Role> entityClass, Serializable id) {

		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Role> findPage(String hql, Page<Role> page, Class<Role> entityClass, Object[] params) {

		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(Role entity) {
		baseDao.saveOrUpdate(entity);

	}

	@Override
	public void saveOrUpdateAll(Collection<Role> entitys) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Class<Role> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);

	}

	@Override
	public void delete(Class<Role> entityClass, Serializable[] ids) {
		for (int i = 0; i < ids.length; i++) {
			deleteById(entityClass, ids[i]);
		}

	}

	@Override
	public void deleteDept(Role d) {
		// TODO Auto-generated method stub

	}

}
