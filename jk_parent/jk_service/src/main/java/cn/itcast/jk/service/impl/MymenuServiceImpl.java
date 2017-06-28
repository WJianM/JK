package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Mymenu;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.service.MymenuService;
import cn.itcast.jk.utils.UtilFuns;

/**
 * @Description:	MymenuService接口
 * @Author:			rent
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2017-6-12 15:09:34
 */

public class MymenuServiceImpl implements MymenuService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Mymenu> find(String hql, Class<Mymenu> entityClass, Object[] params) {
		return baseDao.find(hql, Mymenu.class, params);
	}

	public Mymenu get(Class<Mymenu> entityClass, Serializable id) {
		return baseDao.get(Mymenu.class, id);
	}

	public Page<Mymenu> findPage(String hql, Page<Mymenu> page, Class<Mymenu> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, Mymenu.class, params);
	}

	public void saveOrUpdate(Mymenu entity) {
		if(entity.getId()==null){								//代表新增
			
		}
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<Mymenu> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Mymenu> entityClass, Serializable id) {
		baseDao.deleteById(Mymenu.class, id);
	}

	public void delete(Class<Mymenu> entityClass, Serializable[] ids) {
		baseDao.delete(Mymenu.class, ids);
	}

}

