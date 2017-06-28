package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.itcast.jk.domain.Mymenu;
import cn.itcast.jk.utils.Page;

/**
 * @Description:	MymenuService接口
 * @Author:			rent
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2017-6-12 15:09:34
 */

public interface MymenuService {

	public List<Mymenu> find(String hql, Class<Mymenu> entityClass, Object[] params);
	public Mymenu get(Class<Mymenu> entityClass, Serializable id);
	public Page<Mymenu> findPage(String hql, Page<Mymenu> page, Class<Mymenu> entityClass, Object[] params);
	
	public void saveOrUpdate(Mymenu entity);
	public void saveOrUpdateAll(Collection<Mymenu> entitys);
	
	public void deleteById(Class<Mymenu> entityClass, Serializable id);
	public void delete(Class<Mymenu> entityClass, Serializable[] ids);
}
