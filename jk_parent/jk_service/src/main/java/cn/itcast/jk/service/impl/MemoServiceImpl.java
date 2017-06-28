package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Memo;
import cn.itcast.jk.service.MemoService;
import cn.itcast.jk.utils.Page;

public class MemoServiceImpl implements MemoService {

	// 注入dao
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Memo> find(String hql, Class<Memo> entityClass, Object[] params) {
		
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Memo get(Class<Memo> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Memo> findPage(String hql, Page<Memo> page, Class<Memo> entityClass, Object[] params) {
		// TODO Auto-generated method stub
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(Memo entity) {
		baseDao.saveOrUpdate(entity);

	}

	@Override
	public void saveOrUpdateAll(Collection<Memo> entitys) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Class<Memo> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);

	}

	@Override
	public void delete(Class<Memo> entityClass, Serializable[] ids) {
		baseDao.delete(Memo.class, ids);

	}

	@Override
	public void deleteDept(Memo d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update(Memo entity) {
		// TODO Auto-generated method stub
		
	}

}
