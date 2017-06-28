package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.MemoBg;
import cn.itcast.jk.service.MemoBgService;

public class MemoBgServiceImpl implements MemoBgService {

	// 注入dao
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<MemoBg> find(String hql, Class<MemoBg> entityClass, Object[] params) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public MemoBg get(Class<MemoBg> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return baseDao.get(entityClass, id);
	}
}
