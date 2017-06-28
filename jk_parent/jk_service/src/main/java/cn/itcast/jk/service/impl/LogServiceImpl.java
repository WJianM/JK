package cn.itcast.jk.service.impl;



import java.io.Serializable;
import java.util.UUID;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.domain.Log;
import cn.itcast.jk.service.LogService;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;

public class LogServiceImpl implements LogService {
	
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public Page<Log> findPage(String hql, Page<Log> page, Class<Log> entityClass, Object[] params) {
		// TODO Auto-generated method stub
		return baseDao.findPage(hql, page, entityClass, params);
	}
	
	public void saveOrUpdate(Log entity) {
		/*if(UtilFuns.isEmpty(entity.getId())){
			
		}*/
		baseDao.saveOrUpdate(entity);
	}
	
	@Override
	public void save(Log entity) {
		
		baseDao.save(entity);

	}
	
	

	
	
}
