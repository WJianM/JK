package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Fastmenu;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.service.FastmenuService;
import cn.itcast.jk.utils.UtilFuns;

/**
 * @Description:	FastmenuService接口
 * @Author:			rent
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2017-6-11 20:53:17
 */

public class FastmenuServiceImpl implements FastmenuService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Fastmenu> find(String hql, Class<Fastmenu> entityClass, Object[] params) {
		return baseDao.find(hql, Fastmenu.class, params);
	}

	public Fastmenu get(Class<Fastmenu> entityClass, Serializable id) {
		return baseDao.get(Fastmenu.class, id);
	}

	public Page<Fastmenu> findPage(String hql, Page<Fastmenu> page, Class<Fastmenu> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, Fastmenu.class, params);
	}

	public void saveOrUpdate(Fastmenu entity) {
		if(entity.getId()==null){								//代表新增
			
		}
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<Fastmenu> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Fastmenu> entityClass, Serializable id) {
		baseDao.deleteById(Fastmenu.class, id);
	}

	public void delete(Class<Fastmenu> entityClass, Serializable[] ids) {
		baseDao.delete(Fastmenu.class, ids);
	}

}

