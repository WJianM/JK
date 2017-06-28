package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Dept;
import cn.itcast.jk.service.DeptService;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;

public class DeptServiceImpl implements DeptService {

	// 注入dao
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Dept> find(String hql, Class<Dept> entityClass, Object[] params) {

		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Dept get(Class<Dept> entityClass, Serializable id) {

		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Dept> findPage(String hql, Page<Dept> page, Class<Dept> entityClass, Object[] params) {

		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(Dept entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			entity.setState(1);
			baseDao.saveOrUpdate(entity);
		} else {
			entity.setState(1);
			baseDao.saveOrUpdate(entity);
		}

	}

	@Override
	public void saveOrUpdateAll(Collection<Dept> entitys) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Class<Dept> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);

	}

	@Override
	public void delete(Class<Dept> entityClass, Serializable[] ids) {
		//遍历数组进行删除
		for (int i = 0; i < ids.length; i++) {
			deleteById(entityClass, ids[i]);
		}

	}

	// 递归删除
	public void deleteDept(Dept d) {
		// 查询parentid和Dept相等的部门
		List<Dept> list = baseDao.find("from Dept where parent_id = ?", Dept.class, new Object[] { d.getId() });
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				deleteDept(list.get(i));
			}
			baseDao.deleteById(Dept.class, d.getId());
		} else {
			baseDao.deleteById(Dept.class, d.getId());
		}
	}

}
