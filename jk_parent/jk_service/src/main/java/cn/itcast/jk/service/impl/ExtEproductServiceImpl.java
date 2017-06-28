package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.ExtEproduct;
import cn.itcast.jk.service.ExtEproductService;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;

public class ExtEproductServiceImpl implements ExtEproductService {
	
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public List<ExtEproduct> find(String hql, Class<ExtEproduct> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}
	
	public ExtEproduct get(Class<ExtEproduct> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}
	
	public Page<ExtEproduct> findPage(String hql, Page<ExtEproduct> page, Class<ExtEproduct> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}
	
	/**
	 * 保存或者更新用户
	 * @throws MailInfoException 
	 */
	public void saveOrUpdate(final ExtEproduct entity){
		if(UtilFuns.isEmpty(entity.getId())){
			
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<ExtEproduct> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}
	
	public void deleteById(Class<ExtEproduct> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}
	
	public void delete(Class<ExtEproduct> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}
}
