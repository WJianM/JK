package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.ExportProduct;
import cn.itcast.jk.service.ExportProductService;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;

public class ExportProductServiceImpl implements ExportProductService {
	
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public List<ExportProduct> find(String hql, Class<ExportProduct> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}
	
	public ExportProduct get(Class<ExportProduct> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}
	
	public Page<ExportProduct> findPage(String hql, Page<ExportProduct> page, Class<ExportProduct> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}
	
	/**
	 * 保存或者更新用户
	 * @throws MailInfoException 
	 */
	public void saveOrUpdate(final ExportProduct entity){
		if(UtilFuns.isEmpty(entity.getId())){
			
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<ExportProduct> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}
	
	public void deleteById(Class<ExportProduct> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}
	
	public void delete(Class<ExportProduct> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}
}
