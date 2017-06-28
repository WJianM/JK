package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.MemoBg;
import cn.itcast.jk.domain.MemoIcon;
import cn.itcast.jk.service.MemoBgService;
import cn.itcast.jk.service.MemoIconService;

public class MemoIconServiceImpl implements MemoIconService {

	// 注入dao
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<MemoIcon> find(String hql, Class<MemoIcon> entityClass, Object[] params) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public MemoIcon get(Class<MemoIcon> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return baseDao.get(entityClass, id);
	}

}
