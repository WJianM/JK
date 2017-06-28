package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Assignment;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.service.AssignmentService;
import cn.itcast.jk.utils.UtilFuns;

/**
 * @Description:	AssignmentService接口
 * @Author:			rent
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2017-6-10 19:17:06
 */

public class AssignmentServiceImpl implements AssignmentService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Assignment> find(String hql, Class<Assignment> entityClass, Object[] params) {
		return baseDao.find(hql, Assignment.class, params);
	}

	public Assignment get(Class<Assignment> entityClass, Serializable id) {
		return baseDao.get(Assignment.class, id);
	}

	public Page<Assignment> findPage(String hql, Page<Assignment> page, Class<Assignment> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, Assignment.class, params);
	}

	public void saveOrUpdate(Assignment entity) {
		if(entity.getId()==null){								//代表新增
			
		}else {
		}
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<Assignment> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Assignment> entityClass, Serializable id) {
		baseDao.deleteById(Assignment.class, id);
	}

	public void delete(Class<Assignment> entityClass, Serializable[] ids) {
		baseDao.delete(Assignment.class, ids);
	}

}

